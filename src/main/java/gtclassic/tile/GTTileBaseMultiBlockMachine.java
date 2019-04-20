package gtclassic.tile;

import gtclassic.util.int3;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class GTTileBaseMultiBlockMachine extends GTTileBaseMultiInputMachine {

	public boolean lastState;
	public boolean firstCheck = true;

	public GTTileBaseMultiBlockMachine(int slots, int upgrades, int defaultinput, int maxinput) {
		super(slots, upgrades, defaultinput, 100, maxinput);
	}

	@Override
	public boolean canWork() {
		boolean superCall = super.canWork();
		if (superCall) {
			if (world.getTotalWorldTime() % 256 == 0 || firstCheck) {
				lastState = checkStructure();
				firstCheck = false;
			}
			superCall = superCall && lastState;
		}
		return superCall;
	}

	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3)) {
			return false;
		}
		return true;
	}

	@Override
	public void update() {
		tryImportItems();
		super.update();
		tryExportItems();
	}

	/*
	 * Below I am experimenting with moving item into the multi block tile
	 */

	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.left(2).asBlockPos());
	}

	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.right(2).asBlockPos());
	}

	public void tryImportItems() {

		TileEntity te = getImportTile();

		if (world.getTotalWorldTime() % 20 == 0 && canWork() && te != null) {
			IItemTransporter slave = TransporterManager.manager.getTransporter(te, true);
			IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
			if (slave == null) {
				return;
			}

			IFilter filter = new MachineFilter(this);
			int limit = 9;

			for (int i = 0; i < limit; ++i) {
				ItemStack stack = slave.removeItem(filter, getFacing().getOpposite(), 1, false);
				if (stack.isEmpty()) {
					break;
				}

				ItemStack added = controller.addItem(stack, getFacing().UP, true);
				if (added.getCount() <= 0) {
					break;
				}

				slave.removeItem(filter, getFacing().getOpposite(), 1, true);
			}
		}
	}

	public void tryExportItems() {

		TileEntity te = getExportTile();

		if (world.getTotalWorldTime() % 20 == 0 && te != null) {
			IItemTransporter slave = TransporterManager.manager.getTransporter(te, true);
			IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
			if (slave == null) {
				return;
			}

			int limit = 9;

			for (int i = 0; i < limit; ++i) {
				ItemStack stack = controller.removeItem(CommonFilters.Anything, getFacing().EAST, 1, false);
				if (stack.isEmpty()) {
					break;
				}

				ItemStack added = slave.addItem(stack, getFacing().UP, true);
				if (added.getCount() <= 0) {
					break;
				}

				controller.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
			}
		}
	}

}
