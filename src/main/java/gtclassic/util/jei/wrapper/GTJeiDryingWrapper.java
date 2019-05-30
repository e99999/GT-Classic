package gtclassic.util.jei.wrapper;

import java.awt.Color;
import java.util.ArrayList;

import gtclassic.util.recipe.GTMultiInputRecipeList.MultiRecipe;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class GTJeiDryingWrapper implements IRecipeWrapper {

	private MultiRecipe multiRecipe;

	public GTJeiDryingWrapper(MultiRecipe multiRecipe) {
		this.multiRecipe = multiRecipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<ItemStack> inputs = new ArrayList<>();
		for (IRecipeInput input : multiRecipe.getInputs()) {
			inputs.addAll(input.getInputs());
		}

		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutputs(ItemStack.class, multiRecipe.getOutputs().getAllOutputs());
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer font = minecraft.fontRenderer;
		font.drawSplitString(
				"The solar evaporation process requires placing a fluid source block on vanilla concrete or terracotta blocks in a biome that is not cold",
				0, 36, 144, Color.black.getRGB());
	}

	public MultiRecipe getMultiRecipe() {
		return multiRecipe;
	}
}
