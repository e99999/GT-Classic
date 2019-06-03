package gtclassic.util.jei;

import gtclassic.GTBlocks;
import gtclassic.gui.GTGuiMachine;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.multi.GTTileMultiFusion;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import net.minecraft.block.Block;

public enum GTJeiRegistry {
	// @formatter:off
	CENTRIFUGE(GTTileCentrifuge.RECIPE_LIST, GTBlocks.tileCentrifuge, GTGuiMachine.GTIndustrialCentrifugeGui.class, 78, 24, 20, 18),
	FUSION(GTTileMultiFusion.RECIPE_LIST, GTBlocks.tileFusion, GTFusionComputerGui.class, 69, 34, 25, 17);

	// @formatter:on
	private GTMultiInputRecipeList list;
	private Block catalyst;
	@SuppressWarnings("rawtypes")
	private Class gui;
	private int clickX;
	private int clickY;
	private int sizeX;
	private int sizeY;

	@SuppressWarnings("rawtypes")
	GTJeiRegistry(GTMultiInputRecipeList list, Block catalyst, Class gui, int clickX, int clickY, int sizeX,
			int sizeY) {
		this.list = list;
		this.catalyst = catalyst;
		this.gui = gui;
		this.clickX = clickX;
		this.clickY = clickY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public GTMultiInputRecipeList getRecipeList() {
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
