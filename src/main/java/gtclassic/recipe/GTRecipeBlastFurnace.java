package gtclassic.recipe;

import gtclassic.GTConfig;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileMultiBlastFurnace;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTRecipeBlastFurnace {

	static GTMaterialGen GT;
	static GTMaterial M;
	static GTTileMultiBlastFurnace BlastFurnace;

	public static void recipesBlastFurnace() {
		/*
		 * GT Blast Furnace recipes
		 */

		BlastFurnace.addRecipe(new IRecipeInput[] { input("ingotIron", 1), input("dustCoal", 2) }, euCost(18, 30000),
				GT.getIngot(M.Steel, 1), GT.getDust(M.DarkAshes, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("ingotRefinedIron", 1), input("dustCoal", 2) },
				euCost(18, 30000), GT.getIngot(M.Steel, 1), GT.getDust(M.DarkAshes, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("oreIron", 1), input("dustCalcite", 1) }, euCost(12, 8000),
				GT.getIc2(Ic2Items.refinedIronIngot, 3), GT.getSmallDust(M.Slag, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustPyrite", 1), input("dustCalcite", 1) }, euCost(12, 8000),
				GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 1));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustMagnetite", 1), input("dustCalcite", 1) },
				euCost(12, 8000), GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 1));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustLimonite", 1), input("dustCalcite", 1) },
				euCost(12, 8000), GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 1));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustTantalum", 1) }, euCost(30, 80000),
				GT.getIngot(M.Tantalum, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustSmallTantalum", 1) }, euCost(30, 80000),
				GT.getIngot(M.Tantalum, 1));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustChromite", 1) }, euCost(30, 80000),
				GT.getNugget(M.Chrome, 6));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustSmallChromite", 4) }, euCost(30, 80000),
				GT.getNugget(M.Chrome, 6));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustStainlessSteel", 1) }, euCost(30, 80000),
				GT.getIngot(M.StainlessSteel, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustSmallStainlessSteel", 4) }, euCost(30, 80000),
				GT.getIngot(M.StainlessSteel, 1));

		if (GTConfig.harderAluminium) {
			BlastFurnace.addRecipe(new IRecipeInput[] { input("dustAluminium", 1) }, euCost(30, 80000),
					GT.getIngot(M.Aluminium, 1));
			BlastFurnace.addRecipe(new IRecipeInput[] { input("dustSmallAluminium", 4) }, euCost(30, 80000),
					GT.getIngot(M.Aluminium, 1));
		}
	}

	public static IRecipeInput input(ItemStack stack) {
		return new RecipeInputItemStack(stack);
	}

	public static IRecipeInput input(String name, int amount) {
		return new RecipeInputOreDict(name, amount);
	}

	public static IRecipeInput tubes(int amount) {
		return new RecipeInputItemStack(GT.get(GTItems.testTube, amount));
	}

	public static IRecipeModifier[] euCost(int eu, int amount) {
		int i = eu - 8;
		if (i == 0) {
			i = 1;
		} // i have machines default to 8, this ensures your input is what you think it is
		return new IRecipeModifier[] { ModifierType.RECIPE_ENERGY.create(i),
				ModifierType.RECIPE_LENGTH.create((amount / i) - 100)// same for this 100
		};
	}

}
