package gtclassic.recipe;

public class GTRecipe {

	/*
	 * For now this set of recipes is heavily broken apart which allows me to
	 * reconfigure them with clarity. After the progression is finalized, all
	 * recipes with be in this class
	 */

	public static void init() {
		GTRecipeAlloySmelter.recipesAlloySmelter1();
		GTRecipeAlloySmelter.recipesAlloySmelter2();
		GTRecipeCircuitry.recipesCircutry();
		GTRecipeIndustrialCentrifuge.recipesCentrifuge1();
		GTRecipeIndustrialCentrifuge.recipesCentrifuge2();
		GTRecipeIndustrialCentrifuge.recipesCentrifuge3();
		GTRecipeIndustrialCentrifuge.recipesCentrifuge4();
		GTRecipeIterators.recipeIterators1();
		GTRecipeIterators.recipeIterators2();
		GTRecipeIterators.recipeIterators3();
		GTRecipeMod.recipesIC2();
		GTRecipeProcessing.recipesProcessing();
		GTRecipeShaped.recipeShaped1();
		GTRecipeShaped.recipeShaped2();
		GTRecipeShaped.recipeShaped3();
		GTRecipeShapeless.recipeShapeless1();
	}

}
