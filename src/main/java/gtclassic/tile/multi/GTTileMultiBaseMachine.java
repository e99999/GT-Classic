package gtclassic.tile.multi;

import gtclassic.tile.GTTileBaseMachine;
import gtclassic.util.int3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public abstract class GTTileMultiBaseMachine extends GTTileBaseMachine {

	public boolean lastState;
	public boolean firstCheck = true;

	public GTTileMultiBaseMachine(int slots, int upgrades, int defaultinput, int maxinput) {
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
	public boolean hasGui(EntityPlayer player) {
		return checkStructure();
	}

	@Override
	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.left(2).asBlockPos());
	}

	@Override
	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.right(2).asBlockPos());
	}
}
