package gtclassic.common.tile.datanet;

import java.util.ArrayList;
import java.util.Map;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.GTFilterItemDigitizer;
import gtclassic.common.util.datanet.GTDataNet.DataType;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.InvertedFilter;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.item.ItemStack;

public class GTTileDigitizerItem extends GTTileInputNodeBase implements IGTDebuggableTile {

	public ArrayList<ItemStack> blacklist = new ArrayList<>();

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
		int limit = slave.getSizeInventory(getFacing());
		// boolean found = false;
		for (int i = 0; i < limit; ++i) {
			if (i == limit - 1) {
				blacklist.clear();
			}
			ItemStack stack = slave.removeItem(new InvertedFilter(new GTFilterItemDigitizer(this)), this.getFacing().getOpposite(), 64, false);
			// if (stack.isEmpty()) {
			// break;
			// }
			ItemStack added = nodeTile.addItem(stack, node.getFacing().getOpposite(), true);
			if (added.getCount() <= 0) {
				if (!stack.isEmpty()) {
					blacklist.add(stack);
				}
			} else {
				slave.removeItem(new BasicItemFilter(added), this.getFacing().getOpposite(), added.getCount(), true);
				// found = true;
				blacklist.clear();
			}
		}
		return false;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		for (ItemStack foo : blacklist) {
			data.put("Blacklisted: " + foo.getDisplayName(), false);
		}
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found", false);
		}
	}
}
