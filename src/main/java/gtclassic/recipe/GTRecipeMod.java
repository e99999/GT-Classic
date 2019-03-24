package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.platform.registry.Ic2Items;

public class GTRecipeMod {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipesIC2() {
		/*
		 * Recipes for IC2-Classic
		 */

		// recipes.addRecipe(Ic2Items.miningLaser.copy(),
		// new Object[] { "Rcc", "AAC", " AA", 'A', Ic2Items.advancedAlloy.copy(), 'C',
		// Ic2Items.advancedCircuit.copy(), 'c', GT.getChemical(M.Helium, 1), 'R',
		// "dustRedstone" });

		// recipes.addRecipe(GT.getIc2(Ic2Items.solarPanel, 1),
		// new Object[] { "YYY", "XPX", "CVC", 'C', Ic2Items.electricCircuit.copy(),
		// 'V',
		// Ic2Items.generator.copy(), 'X', "plateSilicon", 'Y', "blockGlass", 'P',
		// Ic2Items.carbonPlate });

		// recipes.addRecipe(Ic2Items.reactorReflectorThick.copy(), new Object[] { " P
		// ", "PBP", " P ", 'P',
		// Ic2Items.reactorReflector, 'B', GT.getChemical(M.Berilium, 1) });

		// recipes.addRecipe(GT.getIc2(Ic2Items.luminator, 16), new Object[] { "III",
		// "GHG", "GGG", 'G', "blockGlass", 'I',
		// "ingotSilver", 'H', GT.getChemical(M.Helium, 1), 'C',
		// Ic2Items.insulatedCopperCable.copy() });

		// recipes.addRecipe(GT.getIc2(Ic2Items.luminator, 16), new Object[] { "III",
		// "GHG", "GGG", 'G', "blockGlass", 'I',
		// "ingotSilver", 'H', GT.getChemical(M.Mercury, 1), 'C',
		// Ic2Items.insulatedCopperCable.copy() });

		recipes.addRecipe(GT.getIc2(Ic2Items.mixedMetalIngot, 3),
				new Object[] { "III", "BBB", "TTT", 'I', "plateSteel", 'B', "plateBronze", 'T', "plateAluminium" });

		recipes.addRecipe(GT.getIc2(Ic2Items.mixedMetalIngot, 1),
				new Object[] { "I  ", "B  ", "T  ", 'I', "plateSteel", 'B', "plateBronze", 'T', "plateAluminium" });

		recipes.addRecipe(GT.getIc2(Ic2Items.mixedMetalIngot, 6),
				new Object[] { "III", "BBB", "TTT", 'I', "plateSteel", 'B', "plateBronze", 'T', "plateTitanium" });

		recipes.addRecipe(GT.getIc2(Ic2Items.mixedMetalIngot, 2),
				new Object[] { "I ", "B ", "T ", 'I', "plateSteel", 'B', "plateBronze", 'T', "plateTitanium" });
	}

}
