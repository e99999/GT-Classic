package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.datanet.GTDataNet.DataType;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.item.ItemStack;

public class GTTileDigitizerItem extends GTTileInputNodeBase implements IGTDebuggableTile {

	/** Transmits Items from the facing pos to valid output nodes on the network **/
	public GTTileDigitizerItem() {
		super(0);
	}

	@Override
	public boolean onDataNetTick(GTTileOutputNodeBase node) {
		if (node.dataType() != DataType.ITEM) {
			return false;
		}
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(this.pos.offset(this.getFacing())), true);
		if (slave == null) {
			return false;
		}
		IItemTransporter nodeTile = TransporterManager.manager.getTransporter(world.getTileEntity(node.getPos().offset(node.getFacing())), true);
		if (nodeTile == null) {
			return false;
		}
		int limit = nodeTile.getSizeInventory(getFacing());//this might need to be opposite who knows, im not even sure what direction im facing right now
		boolean found = false;
		//mabye i have to iterate this more or 2d to just skip stacks that cant go anywhere
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = slave.removeItem(CommonFilters.Anything, this.getFacing().getOpposite(), 64, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = nodeTile.addItem(stack, node.getFacing().getOpposite(), true);
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
