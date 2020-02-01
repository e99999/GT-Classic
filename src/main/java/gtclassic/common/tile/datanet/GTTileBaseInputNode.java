package gtclassic.common.tile.datanet;

import gtclassic.api.helpers.GTUtility;
import ic2.core.util.obj.IRedstoneTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileBaseInputNode extends GTTileBaseDataNode implements ITickable, IRedstoneTile {

	/**
	 * This tile actually moves items or fluids, and stores the output node list
	 **/
	public GTTileBaseInputNode(int slots) {
		super(slots);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % GTUtility.DATA_NET_RESET_RATE == 0) {
			this.computer = null;
		}
		if (world.getTotalWorldTime() % GTUtility.DATA_NET_TICK_RATE == 0) {
			if (this.computer == null || this.computer.dataNet == null || this.computer.dataNet.isEmpty()) {
				return;
			}
			if (world.isBlockPowered(this.getPos())) {
				return;
			}
			if (!world.isBlockLoaded(this.pos.offset(this.getFacing()))) {
				return;
			}
			for (BlockPos nodePos : this.computer.dataNet) {
				if (!world.isBlockLoaded(nodePos) || nodePos == this.pos) {
					continue;
				}
				TileEntity wTile = world.getTileEntity(nodePos);
				if (wTile instanceof GTTileBaseOutputNode) {
					GTTileBaseOutputNode node = (GTTileBaseOutputNode) wTile;
					if (this.channel != node.channel) {
						continue;
					}
					if (onDataNetTick(node)) {
						break;
					}
				}
			}
		}
	}

	public abstract boolean onDataNetTick(GTTileBaseOutputNode node);

	public boolean canConnectToRedstone(EnumFacing side) {
		return side != this.getFacing();
	}
}
