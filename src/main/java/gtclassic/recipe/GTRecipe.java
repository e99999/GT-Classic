package gtclassic.recipe;

import gtclassic.ore.GTOreRegistry;
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
import gtclassic.tile.multi.GTTileMultiLeadChamber;
import gtclassic.tile.multi.GTTileMultiRefractory;

public class GTRecipe {

	/*
	 * For now this set of recipes is heavily broken apart which allows me to
	 * reconfigure them with clarity. After the progression is finalized, all
	 * recipes with be in this class
	 */

	public static void init() {
		GTOreRegistry.oreDirectSmelting();
		GTRecipeCauldron.recipesCauldron();
		GTRecipeCircuitry.recipesCircutry();
		GTRecipeIterators.recipeIterators1();
		GTRecipeIterators.recipeIterators2();
		GTRecipeIterators.recipeIterators3();
		GTRecipeIteratorsTools.recipeIteratorsTools();
		GTRecipeMod.recipesIC2();
		GTRecipeProcessing.recipesProcessing();
		GTRecipeShaped.recipeShaped1();
		GTRecipeShaped.recipeShaped2();
		GTRecipeShaped.recipeShaped3();
		GTRecipeShapeless.recipeShapeless1();

		// below is more how things will go
		GTTileBath.init();
		GTTileCentrifuge.init();
		GTTileElectrolyzer.init();
		GTTileShredder.init();
		GTTileSmelter.init();
		GTTileRoaster.init();
		GTTileMultiBlastFurnace.init();
		GTTileMultiBloomery.init();
		GTTileMultiChemicalReactor.init();
		GTTileMultiFusion.init();
		GTTileMultiRefractory.init();
		GTTileMultiCryogenicSeparator.init();
		GTTileMultiLeadChamber.init();
	}

}
