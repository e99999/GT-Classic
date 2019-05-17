package gtclassic.tile;

import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.util.EnumFacing;

public abstract class GTTileBaseMachinePassive extends GTTileBaseMachine {

	public GTTileBaseMachinePassive(int slots) {
		super(slots, 0, 1, 100, 8);
		this.addedToEnergyNet = false;
	}

	@Override
	public void update() {
		tryImportItems();
		handleRedstone();
		updateNeighbors();
		boolean noRoom = addToInventory();
		if (shouldCheckRecipe) {
			lastRecipe = getRecipe();
			shouldCheckRecipe = false;
		}
		boolean canWork = canWork() && !noRoom;
		boolean operate = (canWork && lastRecipe != null);
		if (operate) {
			if (!getActive()) {
				getNetwork().initiateTileEntityEvent(this, 0, false);
			}
			setActive(true);
			progress += progressPerTick;
			if (progress >= recipeOperation) {
				process(lastRecipe);
				progress = 0;
				notifyNeighbors();
			}
			getNetwork().updateTileGuiField(this, "progress");
		} else {
			if (getActive()) {
				if (progress != 0) {
					getNetwork().initiateTileEntityEvent(this, 1, false);
				} else {
					getNetwork().initiateTileEntityEvent(this, 2, false);
				}
			}
			if (lastRecipe == null && progress != 0) {
				progress = 0;
				getNetwork().updateTileGuiField(this, "progress");
			}
			setActive(false);
		}
		updateComparators();
		tryExportItems();
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return false;
	}

	@Override
	public int getStoredEU() {
		return 0;
	}

	@Override
	public boolean provideEnergy() {
		return false;
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return true;
	}

}
