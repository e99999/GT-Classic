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

		TANTALITE(M.Tantalite, M.Niobium, M.Tantalum, M.Manganese), SPHALERITE(M.Sphalerite, M.Zinc, M.Germanium),
		CINNABAR(M.Cinnabar, M.Redstone, M.Redstone), SHELDONITE(M.Sheldonite, M.Platinum, M.Platinum),
		TUNGSTATE(M.Tungstate, M.Tungsten, M.Manganese), GALENA(M.Galena, M.Lead, M.Silver),
		DARKASHES(M.DarkAshes, M.Ashes, M.Ashes);

		GTMaterial input;
		GTMaterial[] outputs;

		/**
		 * Creates recipes for cauldron washing and JEI
		 * 
		 * @param input   material from GT to be dust input
		 * @param outputs an array of materials for small dust output
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
			addFakeCauldronRecipe(recipes.getInput(), recipes.getOutputs());
		}
	}

	/**
	 * Overloaded logic for converting the raw material data into a recipe for JEI
	 * to view
	 * 
	 * @param input
	 * @param outputs
	 */
	public static void addFakeCauldronRecipe(GTMaterial input, GTMaterial... outputs) {
		List<IRecipeInput> inputlist = new ArrayList<>();
		List<ItemStack> outputlist = new ArrayList<>();
		inputlist.add((IRecipeInput) (new RecipeInputItemStack(GTMaterialGen.getDust(input, 1))));
		for (GTMaterial mat : outputs) {
			outputlist.add(GTMaterialGen.getSmallDust(mat, 1));
		}
		addFakeCauldronRecipe(inputlist, new MachineOutput(null, outputlist));
	}

	/**
	 * Base logic for JEI recipe stuff
	 * 
	 * @param input
	 * @param output
	 */
	static void addFakeCauldronRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

}
