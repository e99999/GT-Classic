package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDataNetBlock;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.GTBlocks;
import gtclassic.common.container.GTContainerComputerCube;
import gtclassic.common.util.datanet.GTBlockFilterDataAll;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.util.helpers.AabbUtil;
import ic2.core.util.helpers.AabbUtil.BoundingBox;
import ic2.core.util.helpers.AabbUtil.Processor;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileComputerCube extends TileEntityElecMachine
		implements IHasGui, IGTDebuggableTile, ITickable, IGTDataNetBlock {

	int nodeCount;
	boolean isOnlyComputer = true;
	private Processor task = null;
	private AabbUtil.IBlockFilter filter = new GTBlockFilterDataAll();

	public GTTileComputerCube() {
		super(0, 512);
		this.maxEnergy = 10000;
		this.nodeCount = 0;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerComputerCube(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		// nothing for now
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public void update() {
		this.setActive(this.isOnlyComputer && this.energy > this.nodeCount);
		if (world.getTotalWorldTime() % GTDataNet.RESET_RATE == 0) {
			this.isOnlyComputer = true;
		}
		if (this.nodeCount > 0) {
			this.useEnergy(this.nodeCount);
		}
		if (this.isOnlyComputer) {
			if (task != null && world.isAreaLoaded(pos, 16)) {
				task.update();
				if (!task.isFinished()) {
					return;
				}
				this.nodeCount = 0;
				for (BlockPos pPos : task.getResults()) {
					if (world.getBlockState(pPos) != GTBlocks.dataCable.getDefaultState()) {
						this.nodeCount++;
					}
					if (this.nodeCount > 256) {
						break;
					}
					TileEntity worldTile = world.getTileEntity(pPos);
					if (worldTile != this && worldTile instanceof GTTileComputerCube) {
						((GTTileComputerCube) worldTile).isOnlyComputer = false;
					}
					if (this.energy > 0) {
						if (worldTile instanceof GTTileDigitizerBase) {
							((GTTileDigitizerBase) worldTile).hasComputer = true;
						}
						if (worldTile instanceof GTTileConstructorBase) {
							((GTTileConstructorBase) worldTile).hasComputer = true;
						}
					}
				}
			}
			if (world.getTotalWorldTime() % GTDataNet.SEARCH_RATE == 0) {
				if (!world.isAreaLoaded(pos, 16))
					return;
				task = AabbUtil.createBatchTask(world, new BoundingBox(this.pos, 256), this.pos, RotationList.ALL, filter, 64, false, false, true);
				task.update();
			}
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Connected nodes: " + this.nodeCount, false);
		data.put("Power Drain: " + this.nodeCount + " EU", false);
	}
}
