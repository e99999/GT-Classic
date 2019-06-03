package gtclassic.recipe;

import java.util.ArrayList;
import java.util.List;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import net.minecraft.item.ItemStack;

public class GTRecipeCauldron {

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.washing");

	/*
	 * Yes I know you're thinking "an enum really!?" but until i decide how to
	 * handle these random list I need - some of them will be enums.
	 */
	public enum GTRecipeCauldronEnum {
		TANTALITE(GTMaterial.Tantalite, GTMaterial.Niobium, GTMaterial.Tantalum),
		SPHALERITE(GTMaterial.Sphalerite, GTMaterial.Zinc, GTMaterial.Germanium),
		CINNABAR(GTMaterial.Cinnabar, GTMaterial.Redstone),
		SHELDONITE(GTMaterial.Sheldonite, GTMaterial.Platinum, GTMaterial.Platinum),
		GALENA(GTMaterial.Galena, GTMaterial.Lead, GTMaterial.Silver),
		TETRAHEDRITE(GTMaterial.Tetrahedrite, GTMaterial.Copper, GTMaterial.Antimony),
		MALACHITE(GTMaterial.Malachite, GTMaterial.Copper, GTMaterial.Calcite),
		CASSITERITE(GTMaterial.Cassiterite, GTMaterial.Tin, GTMaterial.Tin, GTMaterial.Tantalum),
		PYROLUSITE(GTMaterial.Pyrolusite, GTMaterial.Manganese, GTMaterial.Manganese),
		GARNIERITE(GTMaterial.Garnierite, GTMaterial.Nickel, GTMaterial.Nickel),
		BISMUTHTINE(GTMaterial.Bismuthtine, GTMaterial.Bismuth, GTMaterial.Antimony),
		DIRTYRESIN(GTMaterial.DirtyResin, GTMaterial.Resin, GTMaterial.Resin, GTMaterial.Resin, GTMaterial.Wood),
		DARKASHES(GTMaterial.DarkAshes, GTMaterial.Ashes, GTMaterial.Ashes),
		SULFUR(GTMaterial.Sulfur, GTMaterial.Phosphorus, GTMaterial.Phosphorus);

		GTMaterial input;
		GTMaterial[] outputs;

		/**
		 * Creates recipes for cauldron washing and JEI
		 * 
		 * @param input   GTMaterial material from GT to be dust input
		 * @param outputs GTMaterial an array for small dust output
		 */
		GTRecipeCauldronEnum(GTMaterial input, GTMaterial... outputs) {
			this.input = input;
			this.outputs = outputs;
		}

		public GTMaterial getInput() {
			return this.input;
		}

		public GTMaterial[] getOutputs() {
			return this.outputs;
		}
	}

	/**
	 * This iterates the washing recipe enum thru a wrapper for JEI
	 */
	public static void recipesCauldron() {
		for (GTRecipeCauldron.GTRecipeCauldronEnum recipes : GTRecipeCauldron.GTRecipeCauldronEnum.values()) {
			addFakeWashingRecipe(recipes.getInput(), recipes.getOutputs());
		}
	}

	/**
	 * Overloaded logic for converting the raw material data into a recipe for JEI
	 * to view, this will give you a dust to small dust display
	 * 
	 * @param input
	 * @param outputs
	 */
	public static void addFakeWashingRecipe(GTMaterial input, GTMaterial... outputs) {
		List<IRecipeInput> inputlist = new ArrayList<>();
		List<ItemStack> outputlist = new ArrayList<>();
		inputlist.add((IRecipeInput) (new RecipeInputItemStack(GTMaterialGen.getDust(input, 1))));
		for (GTMaterial mat : outputs) {
			outputlist.add(GTMaterialGen.getSmallDust(mat, 1));
		}
		addFakeCauldronRecipe(inputlist, new MachineOutput(null, outputlist));
	}

	/**
	 * Overloaded logic for converting the raw material data into a recipe for JEI
	 * to view, this will give you a quenching display
	 * 
	 * @param input
	 * @param outputs
	 */
	public static void addFakeQuenchingRecipe(GTMaterial mat) {
		List<IRecipeInput> inputlist = new ArrayList<>();
		List<ItemStack> outputlist = new ArrayList<>();
		inputlist.add((IRecipeInput) (new RecipeInputItemStack(GTMaterialGen.getHotIngot(mat, 1))));
		outputlist.add(GTMaterialGen.getIngot(mat, 1));
		addFakeCauldronRecipe(inputlist, new MachineOutput(null, outputlist));
	}

	/**
	 * Base logic for JEI recipe stuff
	 * 
	 * @param input
	 * @param output
	 */
	public static void addFakeCauldronRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 0);
	}
}
