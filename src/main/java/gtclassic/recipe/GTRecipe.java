package gtclassic.recipe;

import gtclassic.ore.GTOreRegistry;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.multi.GTTileMultiFusion;

public class GTRecipe {

	/*
	 * For now this set of recipes is heavily broken apart which allows me to
	 * reconfigure them with clarity. After the progression is finalized, all
	 * recipes with be in this class
	 */
	public static void init() {
		GTOreRegistry.oreDirectSmelting();
		GTRecipeIterators.recipeIterators1();
		GTRecipeIterators.recipeIterators2();
		GTRecipeMod.recipesIC2();
		GTRecipeProcessing.recipesProcessing();
		// below is more how things will go
		GTTileCentrifuge.init();
		GTTileMultiFusion.init();
	}
}
