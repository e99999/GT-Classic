package gtclassic.util.jei.wrapper;

import java.awt.Color;
import java.util.ArrayList;

import gtclassic.util.GTValues;
import gtclassic.util.recipe.GTMultiInputRecipeList.MultiRecipe;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.energy.EnergyNet;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
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

		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutputs(ItemStack.class, multiRecipe.getOutputs().getAllOutputs());
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer font = minecraft.fontRenderer;
		font.drawString("Ticks: " + getEntryTicks(multiRecipe.getOutputs()), 0, 40, Color.black.getRGB());
		font.drawString("Seconds: " + getEntryTicks(multiRecipe.getOutputs()) / 20.0F, 0, 50, Color.black.getRGB());
		font.drawString(
				"Tier: " + GTValues.getTierString(EnergyNet.instance.getTierFromPower(multiRecipe.getMachineEu())), 0,
				60, Color.black.getRGB());
		font.drawString("Usage: " + multiRecipe.getMachineEu() + " EU/t", 0, 70, Color.black.getRGB());
		font.drawString("Cost: " + getEntryTicks(multiRecipe.getOutputs()) * multiRecipe.getMachineEu() + " EU", 0, 80,
				Color.black.getRGB());
	}

	public MultiRecipe getMultiRecipe() {
		return multiRecipe;
	}

	public static int getEntryTicks(MachineOutput output) {
		if (output == null || output.getMetadata() == null) {
			return 100;
		}
		return (100 + output.getMetadata().getInteger("RecipeTime"));
	}

}
