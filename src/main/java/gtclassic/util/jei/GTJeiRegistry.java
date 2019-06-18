package gtclassic.util.jei;

import gtclassic.GTBlocks;
import gtclassic.gui.GTGuiMachine;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.tile.multi.GTTileMultiFusion;
import gtclassic.util.recipe.GTRecipeMultiInputList;
import net.minecraft.block.Block;

public enum GTJeiRegistry {
	CENTRIFUGE(GTTileCentrifuge.RECIPE_LIST, GTBlocks.tileCentrifuge, GTGuiMachine.GTIndustrialCentrifugeGui.class, 78, 24, 20, 18),
	BLASTFURNACE(GTTileMultiBlastFurnace.RECIPE_LIST, GTBlocks.tileBlastFurnace, GTGuiMachine.GTBlastFurnaceGui.class, 78, 24, 20, 18),
	FUSION(GTTileMultiFusion.RECIPE_LIST, GTBlocks.tileFusionComputer, GTGuiMachine.GTFusionComputerGui.class, 110, 34, 25, 17);

	private GTRecipeMultiInputList list;
	private Block catalyst;
	@SuppressWarnings("rawtypes")
	private Class gui;
	private int clickX;
	private int clickY;
	private int sizeX;
	private int sizeY;

	@SuppressWarnings("rawtypes")
	GTJeiRegistry(GTRecipeMultiInputList list, Block catalyst, Class gui, int clickX, int clickY, int sizeX,
			int sizeY) {
		this.list = list;
		this.catalyst = catalyst;
		this.gui = gui;
		this.clickX = clickX;
		this.clickY = clickY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public GTRecipeMultiInputList getRecipeList() {
		return this.list;
	}

	public Block getCatalyst() {
		return this.catalyst;
	}

	@SuppressWarnings("rawtypes")
	public Class getGuiClass() {
		return this.gui;
	}

	public int getClickX() {
		return this.clickX;
	}

	public int getClickY() {
		return this.clickY;
	}

	public int getSizeX() {
		return this.sizeX;
	}

	public int getSizeY() {
		return this.sizeY;
	}
}
