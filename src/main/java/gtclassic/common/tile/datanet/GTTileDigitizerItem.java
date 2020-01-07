package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class GTTileDigitizerItem extends GTTileInputNodeBase implements IGTDebuggableTile {

	public GTTileDigitizerItem() {
		super(0);
	}

	@Override
	public boolean onDataNetTick(GTTileOutputNodeBase node) {
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(this.pos.offset(this.getFacing())), true);
		if (slave == null) {
			return false;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(world.getTileEntity(node.getPos().offset(node.getFacing())), true);
		if (controller == null) {
			return false;
		}
		int limit = controller.getSizeInventory(getFacing());
		boolean found = false;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = slave.removeItem(CommonFilters.Anything, this.getFacing().getOpposite(), 64, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = controller.addItem(stack, EnumFacing.UP, true);
			if (added.getCount() <= 0) {
				break;
			}
			slave.removeItem(new BasicItemFilter(added), this.getFacing().getOpposite(), added.getCount(), true);
			found = true;
		}
		return found;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found", false);
		}
	}
}
