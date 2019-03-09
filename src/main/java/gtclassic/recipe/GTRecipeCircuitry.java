package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;

public class GTRecipeCircuitry {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static IRecipeInput basicCircuitPlate = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("plateSilicon"), new RecipeInputOreDict("plateGermanium") });

	public static void recipesCircutry() {

		/*
		 * Many of the recipes are subject to change as the list of machines populates.
		 * For now they allow testing and progression to happen.
		 */

		// recipes.addRecipe(GT.get(GTBlocks.tinyEnergium, 1),
		// new Object[] { "RRR", "RDR", "RRR", 'D', "gemRuby", 'R', "dustRedstone" });

		// recipes.addRecipe(GT.get(GTBlocks.tinyLapotron, 1),
		// new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic",
		// 'L', "gemLapis" });

		// recipes.addRecipe(GT.get(GTBlocks.tinyLapotron, 1),
		// new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic",
		// 'L', "dustLazurite" });

		recipes.overrideRecipe("shaped_Electronic Circuit", GT.getIc2(Ic2Items.electricCircuit, 1), "CCC", "RSR", "CCC",
				'R', "dustRedstone", 'C', Ic2Items.insulatedCopperCable.copy(), 'S', basicCircuitPlate);

		recipes.overrideRecipe("shaped_Electronic Circuit_1", GT.getIc2(Ic2Items.electricCircuit, 1), "CRC", "CSC",
				"CRC", 'R', "dustRedstone", 'C', Ic2Items.insulatedCopperCable.copy(), 'S', basicCircuitPlate);

	}

}
