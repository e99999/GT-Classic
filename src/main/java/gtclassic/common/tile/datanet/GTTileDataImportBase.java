package gtclassic.common.tile.datanet;

import java.util.HashSet;

import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileDataImportBase extends TileEntityMachine implements ITickable {

	public HashSet<BlockPos> outputNodes = new HashSet<>();
	public static final int TICK_RATE = 10;
	public boolean hasComputer;

	public GTTileDataImportBase(int slots) {
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
		if (world.getTotalWorldTime() % 126 == 0) {
			this.outputNodes.clear();
			this.hasComputer = false;
		}
		if (world.getTotalWorldTime() % TICK_RATE == 0) {
			this.setActive(!this.redstoneEnabled());
			if (!this.getActive()) {
				return;
			}
			if (this.outputNodes.isEmpty() && !this.hasComputer) {
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
