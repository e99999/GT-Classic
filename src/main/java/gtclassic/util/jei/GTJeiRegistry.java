package gtclassic.util.jei;

import gtclassic.GTBlocks;
import gtclassic.gui.GTGuiMachine;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.tile.GTTileBath;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.GTTileElectrolyzer;
import gtclassic.tile.GTTileRoaster;
import gtclassic.tile.GTTileShredder;
import gtclassic.tile.GTTileSmelter;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.tile.multi.GTTileMultiBloomery;
import gtclassic.tile.multi.GTTileMultiChemicalReactor;
import gtclassic.tile.multi.GTTileMultiCryogenicSeparator;
import gtclassic.tile.multi.GTTileMultiFusion;
import gtclassic.tile.multi.GTTileMultiRefractory;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import net.minecraft.block.Block;

public enum GTJeiRegistry {

	// @formatter:off
	CENTRIFUGE(GTTileCentrifuge.RECIPE_LIST, GTBlocks.tileCentrifuge, GTGuiMachine.GTIndustrialCentrifugeGui.class, 78, 24, 20, 18),
	ELECTROLYZER(GTTileElectrolyzer.RECIPE_LIST, GTBlocks.tileElectrolyzer, GTGuiMachine.GTIndustrialElectrolyzerGui.class, 78, 24, 20, 18),
	SHREDDER(GTTileShredder.RECIPE_LIST, GTBlocks.tileShredder, GTGuiMachine.GTShredderGui.class, 78, 24, 20, 11),
	FUSION(GTTileMultiFusion.RECIPE_LIST, GTBlocks.tileFusion, GTFusionComputerGui.class, 69, 34, 25, 17),
	SMELTER(GTTileSmelter.RECIPE_LIST, GTBlocks.tileSmelter, GTGuiMachine.GTElectricSmelterGui.class, 78, 25, 20, 16),
	BLASTFURNACE(GTTileMultiBlastFurnace.RECIPE_LIST, GTBlocks.tileBlastFurnace, GTGuiMachine.GTBlastFurnaceGui.class, 78, 24, 20, 18),
	REFRACTORY(GTTileMultiRefractory.RECIPE_LIST, GTBlocks.tileRefractory, GTGuiMachine.GTRefractoryGui.class, 78, 24, 20, 18),
	CHEMICALREACTOR(GTTileMultiChemicalReactor.RECIPE_LIST, GTBlocks.tileChemicalReactor, GTGuiMachine.GTChemicalReactorGui.class, 78, 25, 20, 18),
	CRYOGENICSEPARATOR(GTTileMultiCryogenicSeparator.RECIPE_LIST, GTBlocks.tileCryogenicSeparator, GTGuiMachine.GTCryogenicSeparatorGui.class, 78, 25, 20, 18),
	BLOOMERY(GTTileMultiBloomery.JEI_RECIPE_LIST, GTBlocks.tileBloomery, GTGuiMachine.GTBloomeryGui.class, 79, 18, 20, 11),
	ROASTER(GTTileRoaster.RECIPE_LIST, GTBlocks.tileRoaster, GTGuiMachine.GTRoasterGui.class, 78, 28, 20, 11),
	BATH(GTTileBath.RECIPE_LIST, GTBlocks.tileBath, GTGuiMachine.GTBathGui.class, 78, 28, 20, 11);
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
