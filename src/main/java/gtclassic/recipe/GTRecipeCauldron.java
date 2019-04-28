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

	static GTMaterial M;

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("washing");

	/*
	 * Yes I know you're thinking "an enum really!?" but until i decide how to
	 * handle these random list I need - some of them will be enums.
	 */

	public enum GTRecipeCauldronEnum {

		TANTALITE(M.Tantalite, M.Niobium, M.Tantalum), SPHALERITE(M.Sphalerite, M.Zinc, M.Germanium),
		CINNABAR(M.Cinnabar, M.Redstone), SHELDONITE(M.Sheldonite, M.Platinum, M.Platinum),
		GALENA(M.Galena, M.Lead, M.Silver), TETRAHEDRITE(M.Tetrahedrite, M.Copper, M.Antimony),
		MALACHITE(M.Malachite, M.Copper, M.Calcite), CASSITERITE(M.Cassiterite, M.Tin, M.Tantalum),
		PYROLUSITE(M.Pyrolusite, M.Manganese, M.Manganese, M.Manganese), GARNIERITE(M.Garnierite, M.Nickel, M.Nickel),
		BISMUTHTINE(M.Bismuthtine, M.Bismuth, M.Antimony), DIRTYRESIN(M.DirtyResin, M.Resin, M.Resin, M.Resin, M.Wood),
		DARKASHES(M.DarkAshes, M.Ashes, M.Ashes);

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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

}
