package gtclassic.common.tile;

import gtclassic.common.event.GTEventCheckSpawn;
import ic2.core.block.base.tile.TileEntityElecMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class GTTileMobRepeller extends TileEntityElecMachine implements ITickable {

	public int range = 16;

	public GTTileMobRepeller() {
		super(0, 32);
		maxEnergy = 1000;
	}

	@Override
	public boolean supportsNotify() {
		return false;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		int[] coords = new int[] { this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(),
				this.getWorld().provider.getDimension() };
		GTEventCheckSpawn.mobReps.add(coords);
	}

	@Override
	public void onUnloaded() {
		super.onUnloaded();
		int[] coords = new int[] { this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(),
				this.getWorld().provider.getDimension() };
		GTEventCheckSpawn.mobReps.remove(coords);
	}

	@Override
	public void update() {
		if (!this.redstoneEnabled()) {
			int[] coords = new int[] { this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(),
					this.getWorld().provider.getDimension() };
			if (world.getTotalWorldTime() % 600 == 0 && !GTEventCheckSpawn.mobReps.contains(coords)) {
				GTEventCheckSpawn.mobReps.add(coords);
			}
			if (this.energy >= 4) {
				this.range = 64;
				this.setActive(true);
				this.useEnergy(4);
			} else {
				this.range = 16;
				this.setActive(false);
			}
		}
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public boolean redstoneEnabled() {
		return this.world.isBlockPowered(this.getPos());
	}
	
	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}
}
