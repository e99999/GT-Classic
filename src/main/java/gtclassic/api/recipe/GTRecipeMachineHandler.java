package gtclassic.api.recipe;

import java.util.ArrayList;
import java.util.List;

import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTRecipeMachineHandler {
	
	public static IRecipeModifier[] totalEu(GTRecipeMultiInputList recipeList, int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / recipeList.energy) - 100) };
	}

	public static void addRecipe(GTRecipeMultiInputList recipeList, IRecipeInput[] inputs, IRecipeModifier[] modifiers,
			ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		for (IRecipeInput input : inputs) {
			inlist.add(input);
		}
		NBTTagCompound mods = new NBTTagCompound();
		for (IRecipeModifier modifier : modifiers) {
			modifier.apply(mods);
		}
		for (ItemStack output : outputs) {
			outlist.add(output);
		}
		addRecipe(recipeList, inlist, new MachineOutput(mods, outlist));
	}

	static void addRecipe(GTRecipeMultiInputList recipeList, List<IRecipeInput> input, MachineOutput output) {
		recipeList.addRecipe(input, output, output.getAllOutputs().get(0).getUnlocalizedName(), recipeList.energy);
	}

	public static void removeRecipe(GTRecipeMultiInputList recipeList, String id) {
		recipeList.removeRecipe(id);
	}
}
