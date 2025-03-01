package gtclassic.api.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTRecipeMachineHandler {

	private GTRecipeMachineHandler() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * This is a helper to make the base recipe adders for a GTC Recipe List
	 * 
	 * @param recipeList - the list to use
	 * @param inputs     - IRecipeInput array of inputs
	 * @param modifiers  - RecipeModifers can use totalEU method in this same class,
	 *                   nullable
	 * @param outputs    - ItemStack array of outputs
	 */
	public static void addRecipe(GTRecipeMultiInputList recipeList, @Nullable IRecipeInput[] inputs,
			IRecipeModifier[] modifiers, ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		Collections.addAll(inlist, inputs);
		Collections.addAll(outlist, outputs);
		NBTTagCompound mods = new NBTTagCompound();
		if (modifiers != null) {
			for (IRecipeModifier modifier : modifiers) {
				modifier.apply(mods);
			}
		}
		addRecipe(recipeList, inlist, new MachineOutput(modifiers != null ? mods : null, outlist));
	}

	/** Private boiler for the recipe adder above **/
	private static void addRecipe(GTRecipeMultiInputList recipeList, List<IRecipeInput> input, MachineOutput output) {
		recipeList.addRecipe(input, output, output.getAllOutputs().get(0).getTranslationKey(), recipeList.energy);
	}

	/**
	 * Creates recipe length based on a total EU Value
	 * 
	 * @param recipeList - the recipe list to get the base EU per tick value from
	 * @param total      - EU (must be above 100)
	 * @return
	 */
	public static IRecipeModifier[] totalEu(GTRecipeMultiInputList recipeList, int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / recipeList.energy) - 100) };
	}

	/**
	 * Creates literal recipe length
	 * 
	 * @param total - time (must be above 100)
	 * @return
	 */
	public static IRecipeModifier[] totalTime(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create(total - 100) };
	}

	/**
	 * Removing a recipe from a GTC Recipe List
	 * 
	 * @param recipeList - recipe list to modify
	 * @param id         - the String id reference to the recipe (can be seen in JEI
	 *                   with debug mode)
	 */
	public static void removeRecipe(GTRecipeMultiInputList recipeList, String id) {
		recipeList.removeRecipe(id);
	}

	/**
	 * Convert from a IC2 Recipe list to a GTC Recipe List
	 * 
	 * @param ic2RecipeList - the Ic2 Recipe List to use as a base
	 * @param gtcRecipeList - the new GTC recipe list to convert too
	 */
	public static void convertIC2toGTC(List<RecipeEntry> ic2RecipeList, GTRecipeMultiInputList gtcRecipeList) {
		for (RecipeEntry entry : ic2RecipeList) {
			IRecipeInput[] in = { entry.getInput() };
			ItemStack[] out = {};
			GTRecipeMachineHandler.addRecipe(gtcRecipeList, in, GTRecipeMachineHandler.totalEu(gtcRecipeList, 120), entry.getOutput().getAllOutputs().toArray(out));
		}
	}
}
