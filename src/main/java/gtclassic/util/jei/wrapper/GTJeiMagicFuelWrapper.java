package gtclassic.util.jei.wrapper;

import java.awt.Color;
import java.util.ArrayList;

import gtclassic.util.recipe.GTRecipeMultiInputList.MultiRecipe;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class GTJeiMagicFuelWrapper implements IRecipeWrapper {

	private MultiRecipe multiRecipe;

	public GTJeiMagicFuelWrapper(MultiRecipe multiRecipe) {
		this.multiRecipe = multiRecipe;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<ItemStack> inputs = new ArrayList<>();
		for (IRecipeInput input : multiRecipe.getInputs()) {
			inputs.addAll(input.getInputs());
		}
		ingredients.setInputs(ItemStack.class, inputs);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer font = minecraft.fontRenderer;
		font.drawString("Total: 8000 EU", 0, 40, Color.black.getRGB());
		font.drawString("Per: 1000ml", 0, 50, Color.black.getRGB());
		font.drawString("Production: 24 EU/t", 0, 60, Color.black.getRGB());
	}

	public MultiRecipe getMultiRecipe() {
		return multiRecipe;
	}
}