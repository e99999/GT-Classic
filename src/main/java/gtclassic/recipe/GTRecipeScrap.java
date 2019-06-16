package gtclassic.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.recipe.GTRecipeElementObject;
import gtclassic.util.recipe.GTRecipeMultiInputList;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.high.TileEntityMassFabricator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTRecipeScrap {

	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.scrap");

	public static void init() {
		// instead of recipes i will iterate my element list as valid scrap
		for (GTRecipeElementObject element : GTRecipeElementObject.fusionObjects) {
			String id = "Element" + element.getNumber() + "Amplifier";
			int amplifier = element.getNumber() * 1000;
			// if the scrap value is below regular scrap, make it equal with regular scrap
			if (amplifier < 5000) {
				amplifier = 5000;
			}
			// Adds stuff to the mass fab list
			addAmplifier(element.getInput(), amplifier, id);
			GTMod.debugLogger("Added " + element.getOutput().getDisplayName() + " as UU amplifier");
		}
		// Again after the input mixing to create a JEI list of the merged values
		for (RecipeEntry var : ClassicRecipes.massfabAmplifier.getRecipeMap()) {
			addAmplifierToJei(new IRecipeInput[] {
					new RecipeInputItemStack(var.getInput().getInputs().get(0)) }, value(var.getOutput().getMetadata().getInteger("amplification")), GTMaterialGen.getIc2(Ic2Items.uuMatter, 1));
		}
		TileEntityMassFabricator.addAmplifier(GTMaterialGen.getFluid(GTMaterial.Oxygen, 1), 8000, "test");
	}

	/**
	 * Stuff below for actual amp recipes and JEI iterators
	 **/
	public static void addAmplifier(IRecipeInput input, int amplifier, String id) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("amplification", amplifier);
		ClassicRecipes.massfabAmplifier.addRecipe(input, new MachineOutput(nbt, Arrays.asList(Ic2Items.uuMatter)), id);
	}

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
}
