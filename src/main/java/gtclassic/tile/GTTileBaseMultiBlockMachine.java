package gtclassic.tile;

public abstract class GTTileBaseMultiBlockMachine extends GTTileBaseMultiInputMachine {

	public boolean lastState;
	public boolean firstCheck = true;

	public GTTileBaseMultiBlockMachine(int slots, int upgrades, int defaultinput, int maxinput) {
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

}
