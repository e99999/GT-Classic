package gtclassic.recipe;

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

		int LOW_TIME = 4000;
		int MED_TIME = 8000;

		BlastFurnace.addRecipe(new IRecipeInput[] { input("ingotIron", 1) }, euCost(LOW_TIME),
				GT.getIc2(Ic2Items.refinedIronIngot, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustIron", 1) }, euCost(LOW_TIME),
				GT.getIc2(Ic2Items.refinedIronIngot, 1));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("oreIron", 1), input("dustCalcite", 1) }, euCost(LOW_TIME),
				GT.getIc2(Ic2Items.refinedIronIngot, 3), GT.getSmallDust(M.Slag, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustPyrite", 1), input("dustCalcite", 1) }, euCost(LOW_TIME),
				GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("orePyrite", 1), input("dustCalcite", 1) }, euCost(LOW_TIME),
				GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustMagnetite", 1), input("dustCalcite", 1) },
				euCost(LOW_TIME), GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("oreMagnetite", 1), input("dustCalcite", 1) },
				euCost(LOW_TIME), GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustLimonite", 1), input("dustCalcite", 1) },
				euCost(LOW_TIME), GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("oreLimonite", 1), input("dustCalcite", 1) },
				euCost(LOW_TIME), GT.getIc2(Ic2Items.refinedIronIngot, 2), GT.getSmallDust(M.Slag, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("ingotRefinedIron", 1), input("dustCoal", 2) },
				euCost(MED_TIME), GT.getIngot(M.Steel, 1), GT.getDust(M.DarkAshes, 2));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("ingotRefinedIron", 1), input("dustCarbon", 1) },
				euCost(MED_TIME), GT.getIngot(M.Steel, 1));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustChromite", 1) }, euCost(80000),
				GT.getNugget(M.Chrome, 6), GT.getSmallDust(M.Slag, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustSmallChromite", 4) }, euCost(80000),
				GT.getNugget(M.Chrome, 6), GT.getSmallDust(M.Slag, 1));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("oreChromite", 1) }, euCost(80000), GT.getNugget(M.Chrome, 6),
				GT.getSmallDust(M.Slag, 2));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustMolybdenite", 3) }, euCost(80000),
				GT.getHotIngot(M.Molybdenum, 1), GT.getSmallDust(M.Slag, 3));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("dustSmallMolybdenite", 12) }, euCost(80000),
				GT.getHotIngot(M.Molybdenum, 1), GT.getSmallDust(M.Slag, 3));
		BlastFurnace.addRecipe(new IRecipeInput[] { input("oreMolybdenite", 3) }, euCost(80000),
				GT.getHotIngot(M.Molybdenum, 1), GT.getSmallDust(M.Slag, 6));

		BlastFurnace.addRecipe(new IRecipeInput[] { input(GT.getIc2(Ic2Items.iridiumOre, 1)), }, euCost(90000),
				GT.getHotIngot(M.Iridium, 1));

		BlastFurnace.addRecipe(new IRecipeInput[] { input("oreIridium", 1), }, euCost(90000),
				GT.getHotIngot(M.Iridium, 1));

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

	public static IRecipeModifier[] euCost(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 20) - 100) };
	}

}
