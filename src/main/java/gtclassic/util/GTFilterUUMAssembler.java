package gtclassic.util;

import gtclassic.helpers.GTHelperStack;
import gtclassic.tile.GTTileUUMAssembler;
import gtclassic.util.recipe.GTRecipeMultiInputList.MultiRecipe;
import net.minecraft.item.ItemStack;

public class GTFilterUUMAssembler {

	public static boolean matches(ItemStack stack) {
		for (MultiRecipe map : GTTileUUMAssembler.RECIPE_LIST.getRecipeList()) {
			if (GTHelperStack.isEqual(stack, map.getOutputs().getAllOutputs().get(0))) {
				return true;
			}
		}
		return false;
	}

	public static int getCost(ItemStack stack) {
		for (MultiRecipe map : GTTileUUMAssembler.RECIPE_LIST.getRecipeList()) {
			if (GTHelperStack.isEqual(stack, map.getOutputs().getAllOutputs().get(0))) {
				return map.getInputs().get(0).getAmount();
			}
		}
		return 0;
	}

	public static int getAmountPer(ItemStack stack) {
		for (MultiRecipe map : GTTileUUMAssembler.RECIPE_LIST.getRecipeList()) {
			if (GTHelperStack.isEqual(stack, map.getOutputs().getAllOutputs().get(0))) {
				return map.getOutputs().getAllOutputs().get(0).getCount();
			}
		}
		return 0;
	}
}
