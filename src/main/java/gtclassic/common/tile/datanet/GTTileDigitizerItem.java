package gtclassic.common.tile.datanet;

import java.util.ArrayList;
import java.util.List;

import gtclassic.api.helpers.GTUtility;
import gtclassic.common.util.GTIFilters;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.InvertedFilter;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.item.ItemStack;

public class GTTileDigitizerItem extends GTTileBaseInputNode {

	private ArrayList<ItemStack> blacklist = new ArrayList<>();

	/** Transmits Items from the facing pos to valid output nodes on the network **/
	public GTTileDigitizerItem() {
		super(0);
	}

	@Override
	public boolean onDataNetTick(GTTileBaseOutputNode node) {
		if (node.dataType() != GTUtility.DataType.ITEM) {
			return false;
		}
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(this.pos.offset(this.getFacing())), true);
		if (slave == null) {
			return false;
		}
		IItemTransporter nodeTile = TransporterManager.manager.getTransporter(world.getTileEntity(node.inventoryPos()), true);
		if (nodeTile == null) {
			return false;
		}
		int limit = slave.getSizeInventory(getFacing());
		for (int i = 0; i < limit; ++i) {
			if (i == limit - 1) {
				getBlacklist().clear();
			}
			IFilter filter = node.inventoryFilter() != null ? node.inventoryFilter()
					: new InvertedFilter(new GTIFilters.ItemDigitizerFilter(this));
			ItemStack stack = slave.removeItem(filter, this.getFacing().getOpposite(), 64, false);
			ItemStack added = nodeTile.addItem(stack, node.inventoryFacing(), true);
			if (added.getCount() <= 0) {
				if (!stack.isEmpty()) {
					getBlacklist().add(stack);
				}
			} else {
				slave.removeItem(new BasicItemFilter(added), this.getFacing().getOpposite(), added.getCount(), true);
				getBlacklist().clear();
			}
		}
		return false;
	}

	/**
	 * @return The current blacklist being used in the iterator
	 */
	public List<ItemStack> getBlacklist() {
		return blacklist;
	}
}
