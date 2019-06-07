package gtclassic.recipe;

import java.lang.reflect.Field;
import java.util.Set;

import forestry.api.recipes.ICentrifugeRecipe;
import forestry.factory.recipes.CentrifugeRecipeManager;
import gtclassic.GTMod;
import gtclassic.tile.GTTileCentrifuge;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import net.minecraft.item.ItemStack;

public class GTRecipeForestry {

	public static void notTheBees() {
		Field recipes = null;
		try {
			recipes = CentrifugeRecipeManager.class.getDeclaredField("recipes");
		} catch (NoSuchFieldException e) {
			GTMod.logger.info("Trying to access Forestry recipes has failed : (");
		} catch (SecurityException e) {
			GTMod.logger.info("Forestry security deployed");
		}
		if (recipes != null) {
			recipes.setAccessible(true);
		}
		Set<ICentrifugeRecipe> copy = null;
		try {
			if (recipes != null) {
				copy = (Set<ICentrifugeRecipe>) recipes.get(recipes);
			}
		} catch (IllegalArgumentException e) {
			GTMod.logger.info("Accessed Forestry class but field getter failed");
		} catch (IllegalAccessException e) {
			GTMod.logger.info("Accessed Forestry class but access denied");
		}
		for (ICentrifugeRecipe entry : copy) {
			ItemStack input = entry.getInput();
			int time = entry.getProcessingTime();
			int size = entry.getAllProducts().size();
			if (size > 0) {
				ItemStack[] outputs = entry.getAllProducts().keySet().toArray(new ItemStack[size]);
				GTTileCentrifuge.addRecipe(input, 0, totalTime(100), outputs);
			}
		}
	}

	public static IRecipeModifier[] totalTime(int amount) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create(amount - 100) };
	}
}
