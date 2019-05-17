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

public class GTJeiBloomWrapper implements IRecipeWrapper {

	private MultiRecipe multiRecipe;

	public GTJeiBloomWrapper(MultiRecipe multiRecipe) {
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
		font.drawSplitString("1-5 XP", 92, 19, 144, Color.green.getRGB());
	}

	public MultiRecipe getMultiRecipe() {
		return multiRecipe;
	}
}
