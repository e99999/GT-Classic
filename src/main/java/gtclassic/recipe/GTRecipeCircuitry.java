package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntitySawMill;

public class GTRecipeCircuitry {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipesCircutry() {

		/*
		 * Many of the recipes are subject to change as the list of machines populates.
		 * For now they allow testing and progression to happen.
		 */

		// Compressing silicon in boule
		TileEntityCompressor.addRecipe(GT.getChemical(M.Silicon, 9), (GT.get(GTItems.bouleSilicon, 1)), 0.2F);

		// Cutting the boule into silicon plates
		TileEntitySawMill.addRecipe(GT.get(GTItems.bouleSilicon, 1), (GT.getPlate(M.Silicon, 9)), 0.2F);

		/*
		 * Temporary recipes for getting the gregtech energy crystals required to
		 * progress
		 */
		recipes.addRecipe(GT.get(GTBlocks.tinyEnergium, 1),
				new Object[] { "RRR", "RDR", "RRR", 'D', "gemRuby", 'R', "dustRedstone" });

		recipes.addRecipe(GT.get(GTBlocks.tinyLapotron, 1),
				new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic", 'L', "gemLapis" });

		recipes.addRecipe(GT.get(GTBlocks.tinyLapotron, 1),
				new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic", 'L', "dustLazurite" });

	}

}
