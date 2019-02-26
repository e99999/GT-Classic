package gtclassic.util.jei.wrapper;

import java.util.ArrayList;

import gtclassic.util.recipe.GTMultiInputRecipeList.MultiRecipe;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class GTJeiMultiRecipeWrapper implements IRecipeWrapper {

	private MultiRecipe multiRecipe;

	public GTJeiMultiRecipeWrapper(MultiRecipe multiRecipe) {
		this.multiRecipe = multiRecipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<ItemStack> inputs = new ArrayList<>();
		for (IRecipeInput input : multiRecipe.getInputs()) {
			inputs.addAll(input.getInputs());
		}

		ingredients.setInputs(VanillaTypes.ITEM, inputs);
		ingredients.setOutputs(VanillaTypes.ITEM, multiRecipe.getOutputs().getAllOutputs());
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		// TODO draw EU etc
	}
}
