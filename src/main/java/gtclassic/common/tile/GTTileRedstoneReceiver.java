package gtclassic.common.tile;

import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class GTTileRedstoneReceiver extends TileEntityMachine {

	private int redstoneLevel = 0;

	public GTTileRedstoneReceiver() {
		super(0);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	public void setRedstoneLevel(int newLevel) {
		if (this.redstoneLevel != newLevel && newLevel != -1) {
			this.redstoneLevel = newLevel;
			this.setActive(this.redstoneLevel > 0);
		}
		this.updateNeighbors();
	}

	private void updateNeighbors() {
		EnumFacing[] sides = EnumFacing.HORIZONTALS;
		int sidesLength = sides.length;
		for (int i = 0; i < sidesLength; ++i) {
			EnumFacing side = sides[i];
			BlockPos newPos = this.getPos().offset(side);
			if (this.world.isBlockLoaded(newPos)) {
				this.world.neighborChanged(newPos, this.getBlockType(), this.getPos());
			}
		}
	}

	public int getRedstoneLevel() {
		return this.redstoneLevel;
	}
}
