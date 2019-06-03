package gtclassic.tile.multi;

import gtclassic.tile.GTTileBaseMachinePassive;
import gtclassic.util.int3;
import net.minecraft.tileentity.TileEntity;

public abstract class GTTileMultiBasePassive extends GTTileBaseMachinePassive {

	public boolean lastState;
	public boolean firstCheck = true;

	public GTTileMultiBasePassive(int slots) {
		super(slots);
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
