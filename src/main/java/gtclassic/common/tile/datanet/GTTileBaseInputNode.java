package gtclassic.common.tile.datanet;

import gtclassic.common.util.datanet.GTDataNet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileBaseInputNode extends GTTileBaseDataNode implements ITickable {

	/**
	 * This tile actually moves items or fluids, and stores the output node list
	 **/
	public GTTileBaseInputNode(int slots) {
		super(slots);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % GTDataNet.RESET_RATE == 0) {
			this.computer = null;
		}
		if (world.getTotalWorldTime() % GTDataNet.TICK_RATE == 0) {
			if (this.computer == null || this.computer.dataNet == null || this.computer.dataNet.isEmpty()) {
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
}
