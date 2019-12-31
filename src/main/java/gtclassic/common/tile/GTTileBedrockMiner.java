package gtclassic.common.tile;

import java.util.Random;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.world.GTBedrockOreHandler;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerBedrockMiner;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBedrockMiner extends TileEntityElecMachine implements ITickable, IHasGui, IGTDisplayTickTile {

	ItemStack output;
	public static final ItemStack pipe = GTMaterialGen.get(GTBlocks.miningPipe);
	public static final IFilter filter = new BasicItemFilter(pipe);
	static final int[] INPUTS = { 0, 1 };
	static final int[] OUTPUTS = { 2, 3 };
	static final int[] ALL = { 0, 1, 2, 3 };
	static final int FUEL = 4;

	public GTTileBedrockMiner() {
		super(5, 512);
		this.setFuelSlot(FUEL);
		maxEnergy = 1000000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, 4);
		handler.registerDefaultSlotAccess(AccessRule.Import, INPUTS);
		handler.registerDefaultSlotAccess(AccessRule.Export, OUTPUTS);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), ALL);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), FUEL);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, FUEL);
		handler.registerSlotType(SlotType.Fuel, FUEL);
		handler.registerSlotType(SlotType.Input, INPUTS);
		handler.registerSlotType(SlotType.Output, OUTPUTS);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.BEDROCK_MINER;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBedrockMiner(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer arg0) {
	}

	public int[] getInputSlots() {
		return INPUTS;
	}

	public int[] getOutputSlots() {
		return OUTPUTS;
	}

	@Override
	public void update() {
		this.handleChargeSlot(this.maxEnergy);
		checkForBedrockOre();
		if (this.output == null) {
			this.setActive(false);
			return;
		}
		boolean pipeFound = false;
		for (int j : getInputSlots()) {
			if (GTHelperStack.isEqual(pipe, this.inventory.get(j))) {
				pipeFound = true;
				break;
			}
		}
		if (!pipeFound) {
			this.setActive(false);
			return;
		}
		for (int i : getOutputSlots()) {
			if (GTHelperStack.canMerge(this.output, this.getStackInSlot(i)) && this.energy >= 4096
					&& !this.redstoneEnabled()) {
				if (world.rand.nextInt(31) == 0) {
					int count = this.getStackInSlot(i).getCount();
					this.setStackInSlot(i, GTHelperStack.copyWithSize(this.output, count + 1));
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 0.4F, 1.0F);
					tryDamagePipe();
					tryExport();
				}
				this.energy = this.energy - 4096;
				this.getNetwork().updateTileGuiField(this, "energy");
				this.setActive(true);
				break;
			} else {
				this.setActive(false);
			}
		}
	}

	public void tryDamagePipe() {
		if (world.rand.nextInt(23) == 0) {
			for (int i : getInputSlots()) {
				if (GTHelperStack.isEqual(pipe, this.inventory.get(i))) {
					// check for lubricant here
					this.inventory.get(i).shrink(1);
					world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.4F, 1.0F);
					tryAndBeNice();
					tryRemoveOre();
					break;
				}
			}
		}
	}

	public void tryAndBeNice() {
		if (world.rand.nextInt(2) == 0) {
			for (int j : getOutputSlots()) {
				if (GTHelperStack.canMerge(GTMaterialGen.getDust(GTMaterial.Tungsten, 1), this.getStackInSlot(j))) {
					int count = this.getStackInSlot(j).getCount();
					this.setStackInSlot(j, GTHelperStack.copyWithSize(GTMaterialGen.getDust(GTMaterial.Tungsten, 1), count
							+ 1));
				}
			}
		}
	}

	public void tryRemoveOre() {
		if (world.rand.nextInt(15) == 0) {
			for (int i = 0; i < 6; ++i) {
				Block block = world.getBlockState(pos.offset(EnumFacing.DOWN, i)).getBlock();
				if (GTBedrockOreHandler.isBedrockOre(block)) {
					world.setBlockState(pos.offset(EnumFacing.DOWN, i), Blocks.BEDROCK.getDefaultState());
					this.output = null;
					break;
				}
			}
		}
	}

	public void checkForBedrockOre() {
		if (world.getTotalWorldTime() % 100 == 0) {
			for (int i = 0; i < 6; ++i) {
				Block block = world.getBlockState(pos.offset(EnumFacing.DOWN, i)).getBlock();
				if (GTBedrockOreHandler.isBedrockOre(block)) {
					this.output = GTBedrockOreHandler.getResource(block).copy();
					break;
				} else {
					this.output = null;
				}
			}
		}
	}

	public void tryExport() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(pos.up()), false);
		if (slave != null) {
			for (int i : getOutputSlots()) {
				int added = slave.addItem(this.getStackInSlot(i).copy(), EnumFacing.DOWN, true).getCount();
				if (added > 0) {
					this.getStackInSlot(i).shrink(added);
				}
			}
		}
	}

	public boolean redstoneEnabled() {
		return this.world.isBlockPowered(this.getPos());
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isActive) {
			for (int i = 0; i < 3; ++i) {
				double d0 = (double) pos.getX() + rand.nextDouble();
				double d1 = (double) pos.getY() + .5D + rand.nextDouble() * 0.5D + 0.5D;
				double d2 = (double) pos.getZ() + rand.nextDouble();
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}
}
