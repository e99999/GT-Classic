package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDataNetObject;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileInputNodeBase extends TileEntityMachine
		implements ITickable, IGTDebuggableTile, IGTDataNetObject {

	public GTTileComputerCube computer;

	/**
	 * This tile actually moves items or fluids, and stores the output node list
	 **/
	public GTTileInputNodeBase(int slots) {
		super(slots);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return this.getFacing() != facing;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
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
				if (wTile instanceof GTTileOutputNodeBase) {
					GTTileOutputNodeBase node = (GTTileOutputNodeBase) wTile;
					if (onDataNetTick(node)) {
						break;
					}
				}
			}
		}
	}

	public abstract boolean onDataNetTick(GTTileOutputNodeBase node);

	@Override
	public void getData(Map<String, Boolean> data) {
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found or network is not powered", false);
		}
	}
}
