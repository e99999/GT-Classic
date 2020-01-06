package gtclassic.common.tile.pipeline;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.GTBlocks;
import ic2.core.RotationList;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.util.math.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class GTTilePipelineItem extends GTTilePipelineBase implements IGTDebuggableTile {

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
	public Block getBlockDrop() {
		return GTBlocks.pipelineItem;
	}

	@Override
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
	public boolean onPipelineImport(EnumFacing side) {
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(this.pos.offset(side)), true);
		if (slave != null) {
			IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
			int limit = controller.getSizeInventory(side);
			for (int i = 0; i < limit; ++i) {
				ItemStack stack = slave.removeItem(CommonFilters.Anything, side.getOpposite(), 1, false);
				if (stack.isEmpty()) {
					break;
				}
				ItemStack added = controller.addItem(stack, side, true);
				if (added.getCount() <= 0) {
					break;
				}
				slave.removeItem(CommonFilters.Anything, side.getOpposite(), 1, true);
			}
		}
		return false;
	}

	@Override
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
		data.put("Will Import: " + this.getActive(), false);
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
