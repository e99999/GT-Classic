package gtclassic.common.tile.datanet;

import gtclassic.api.helpers.GTUtility;
import ic2.core.util.obj.IRedstoneTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileBaseInputNode extends GTTileBaseDataNode implements ITickable, IRedstoneTile {

	/**
	 * This tile actually moves items or fluids
	 **/
	public GTTileBaseInputNode(int slots) {
		super(slots);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % GTUtility.DATA_NET_RESET_RATE == 0) {
			this.setComputer(null);
		}
		if (world.getTotalWorldTime() % GTUtility.DATA_NET_TICK_RATE == 0) {
			if (!canWork()) {
				return;
			}
			iterateDataNetwork();
		}
	}

	private void iterateDataNetwork() {
		for (BlockPos nodePos : this.getComputer().getDataNetwork()) {
			if (!world.isBlockLoaded(nodePos) || nodePos == this.pos) {
				continue;
			}
			TileEntity tile = world.getTileEntity(nodePos);
			if (tile instanceof GTTileBaseOutputNode) {
				GTTileBaseOutputNode node = (GTTileBaseOutputNode) tile;
				if (this.getChannel() != node.getChannel()) {
					continue;
				}
				if (onDataNetTick(node)) {
					break;
				}
			}
		}
	}

	/**
	 * The logic for what this node should do/look for while all nodes are passed
	 * over via iteration.
	 * 
	 * @param node - each node on the network gets passed through this arg
	 * @return - true if a transfer was made, false will keep iterating.
	 */
	public abstract boolean onDataNetTick(GTTileBaseOutputNode node);

	public boolean canWork() {
		if (this.getComputer() == null || !this.getComputer().hasDataNetwork()) {
			return false;
		}
		if (!world.isBlockLoaded(this.pos.offset(this.getFacing()))) {
			return false;
		}
		return !world.isBlockPowered(this.getPos());
	}

	public boolean canConnectToRedstone(EnumFacing side) {
		return side != this.getFacing();
	}
}
