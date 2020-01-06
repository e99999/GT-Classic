package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.RotationList;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.util.math.MathUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class GTTileDataImportItem extends GTTileDataImportBase implements IGTDebuggableTile {

	public GTTileDataImportItem() {
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
	public boolean onPipelineTick(BlockPos nodePos) {
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(this.pos.offset(this.getFacing())), true);
		if (slave == null) {
			return false;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(world.getTileEntity(nodePos), true);
		if (controller == null) {
			return false;
		}
		boolean found = false;
		int limit = controller.getSizeInventory(getFacing());
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = slave.removeItem(CommonFilters.Anything, this.getFacing().getOpposite(), 64, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = controller.addItem(stack, EnumFacing.UP, true);
			if (added.getCount() <= 0) {
				found = true;
				break;
			}
			slave.removeItem(new BasicItemFilter(added), this.getFacing().getOpposite(), added.getCount(), true);
		}
		return found;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Active: " + this.getActive(), false);
		if (this.outputNodes.isEmpty()) {
			data.put("No Endpoint Attached", false);
			return;
		}
		data.put("Endpoints found: " + this.outputNodes.size(), false);
	}
}
