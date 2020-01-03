package gtclassic.common.tile;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import ic2.core.RotationList;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.util.math.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class GTTilePipelineItem extends GTTileBaseRecolorableTile implements ITickable, IGTDebuggableTile {

	public BlockPos targetPos;
	private static final String NBT_TARGETPOS = "targetPos";
	public static final int TICK_RATE = 10;

	public GTTilePipelineItem() {
		super(4);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		int[] array = MathUtil.fromTo(0, this.inventory.size());
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, array);
		handler.registerDefaultSlotsForSide(RotationList.ALL, array);
		handler.registerSlotType(SlotType.Storage, array);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.targetPos != null) {
			nbt.setTag(NBT_TARGETPOS, NBTUtil.createPosTag(targetPos));
		}
		return nbt;
	}

	@Override
	public Block getBlockDrop() {
		return GTBlocks.pipelineItem;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	public boolean isEmpty() {
		int empty = 0;
		for (int j = 0; j < this.inventory.size(); ++j) {
			if (this.inventory.get(j).isEmpty()) {
				empty++;
			}
		}
		return empty == this.inventory.size();
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 126 == 0) {
			this.targetPos = null;
		}
		if (world.getTotalWorldTime() % TICK_RATE == 0) {
			if (this.targetPos == null || this.targetPos == this.pos) {
				return;
			}
			if (!world.isBlockLoaded(this.targetPos)) {
				return;
			}
			if (isEmpty()) {
				return;
			}
			onPipelineTick();
		}
	}

	public void onPipelineTick() {
		TileEntity tile = world.getTileEntity(this.targetPos);
		IItemTransporter slave = TransporterManager.manager.getTransporter(tile, false);
		if (slave != null) {
			for (int i = 0; i < this.inventory.size(); ++i) {
				int added = slave.addItem(this.getStackInSlot(i).copy(), EnumFacing.UP, true).getCount();
				if (added > 0) {
					this.getStackInSlot(i).shrink(added);
				}
			}
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		boolean empty = true;
		for (int i = 0; i < this.inventory.size(); ++i) {
			ItemStack stack = this.getStackInSlot(i).copy();
			if (!stack.isEmpty()) {
				data.put(stack.getDisplayName() + " x " + stack.getCount(), false);
				empty = false;
			}
		}
		if (empty) {
			data.put("Pipe is empty", false);
		}
		if (this.targetPos == null) {
			data.put("No Endpoint Attached", false);
			return;
		}
		String block = world.isBlockLoaded(this.targetPos)
				? "Destination: " + world.getBlockState(this.targetPos).getBlock().getLocalizedName()
				: "Destination is not loaded!";
		data.put(block, false);
	}
}
