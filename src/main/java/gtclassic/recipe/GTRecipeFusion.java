package gtclassic.recipe;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileMultiFusionComputer;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTRecipeFusion {

	static GTMaterialGen GT;
	static GTMaterial M;
	static GTTileMultiFusionComputer Fusion;

	public static void recipesFusion() {

		/*
		 * Just a few test fusion recipes
		 */
		Fusion.addRecipe(new IRecipeInput[] { input("dustTungsten", 1), input(GT.getFluid(M.Lithium, 1)) },
				euCost(16775168), GT.getIc2(Ic2Items.iridiumOre, 1));

		Fusion.addRecipe(new IRecipeInput[] { input("dustTungsten", 1), input(GT.getFluid(M.Beryllium, 1)) },
				euCost(16775168), GT.getDust(M.Platinum, 1));
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
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 8196) - 100) };
	}

}
