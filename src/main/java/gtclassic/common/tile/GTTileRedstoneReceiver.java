package gtclassic.common.tile;

import gtclassic.api.helpers.GTUtility;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.obj.IRedstoneProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class GTTileRedstoneReceiver extends TileEntityMachine implements IRedstoneProvider {

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
			world.notifyNeighborsOfStateChange(pos, this.getBlockType(), true);
			GTUtility.updateNeighborhood(this.world, this.pos, this.getBlockType(), RotationList.ALL);
		}
	}

	@Override
	public int getRedstoneStrenght(EnumFacing var1) {
		return this.redstoneLevel;
	}
}
