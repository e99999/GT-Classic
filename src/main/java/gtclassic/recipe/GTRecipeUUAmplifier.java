package gtclassic.recipe;

import java.util.ArrayList;
import java.util.List;

import gtclassic.material.GTMaterialGen;
import gtclassic.util.recipe.GTRecipeElementObject;
import gtclassic.util.recipe.GTRecipeMultiInputList;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTRecipeUUAmplifier {

	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.uuamplifier");

	public static void init() {
		/** Collecting ic2 entries **/
		for (RecipeEntry var : ClassicRecipes.massfabAmplifier.getRecipeMap()) {
			addAmplifierToJei(new IRecipeInput[] {
					new RecipeInputItemStack(var.getInput().getInputs().get(0)) }, value(var.getOutput().getMetadata().getInteger("amplification")), GTMaterialGen.getIc2(Ic2Items.uuMatter, 1));
		}
		/** Adding my elements **/
		for (GTRecipeElementObject element : GTRecipeElementObject.fusionObjects) {
			int value = element.getNumber() * 1000;
			if (value < 5000) {
				value = 5000;
			}
			if (element.getNumber() != 77) {
			addAmplifierToJei(new IRecipeInput[] {
					element.getInput() }, value(value), GTMaterialGen.getIc2(Ic2Items.uuMatter, 1));
			}
		}
	}

	/**
	 * Stuff below for actual amp recipes and JEI iterators
	 **/
	public static IRecipeModifier[] value(int amount) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((amount / 1) - 100) };
	}

	public static void addAmplifierToJei(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
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
		addAmplifierToJei(inlist, new MachineOutput(mods, outlist));
	}

	static void addAmplifierToJei(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getUnlocalizedName(), 1);
	}

	public static void removeRecipe(String id){
		RECIPE_LIST.removeRecipe(id);
	}
}
