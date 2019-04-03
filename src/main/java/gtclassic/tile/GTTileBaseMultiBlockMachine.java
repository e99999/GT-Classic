package gtclassic.tile;

public abstract class GTTileBaseMultiBlockMachine extends GTTileBaseMultiInputMachine {

	public boolean lastState;
	public boolean firstCheck = true;

	public GTTileBaseMultiBlockMachine(int slots, int upgrades, int maxinput) {
		super(slots, upgrades, 8, 100, maxinput);
		// this is where the default time and length are set, to be set via recipe
		// modifer
		// after refactoring this will be moved to base multi input machine
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

}
