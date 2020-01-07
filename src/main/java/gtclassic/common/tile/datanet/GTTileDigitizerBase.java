package gtclassic.common.tile.datanet;

import java.util.HashSet;

import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileDigitizerBase extends TileEntityMachine implements ITickable {

	public HashSet<BlockPos> outputNodes = new HashSet<>();
	public boolean hasComputer;

	/**
	 * This tile actually moves items or fluids, and stores the output node list
	 **/
	public GTTileDigitizerBase(int slots) {
		super(slots);
		this.hasComputer = false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		return nbt;
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
			this.outputNodes.clear();
			this.hasComputer = false;
		}
		if (world.getTotalWorldTime() % GTDataNet.TICK_RATE == 0) {
			if (this.outputNodes.isEmpty() || !this.hasComputer) {
				return;
			}
			// if import pos not loaded return;
			for (BlockPos nodePos : outputNodes) {
				if (!world.isBlockLoaded(nodePos) || nodePos == this.pos) {
					continue;
				}
				if (onDataNetTick(nodePos)) {
					break;
				}
			}
		}
	}

	public boolean redstoneEnabled() {
		return this.world.isBlockPowered(this.getPos());
	}

	public abstract boolean onDataNetTick(BlockPos nodePos);
}
