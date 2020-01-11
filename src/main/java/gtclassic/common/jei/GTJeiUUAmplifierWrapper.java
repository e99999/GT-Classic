package gtclassic.common.jei;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.common.GTConfig;
import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class GTJeiUUAmplifierWrapper implements IRecipeWrapper {

	private MultiRecipe multiRecipe;

	public GTJeiUUAmplifierWrapper(MultiRecipe multiRecipe) {
		this.multiRecipe = multiRecipe;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<FluidStack> inputFluids = new ArrayList<>();
		ArrayList<ItemStack> inputItems = new ArrayList<>();
		for (IRecipeInput input : multiRecipe.getInputs()) {
			if (input instanceof RecipeInputFluid) {
				inputFluids.add(((RecipeInputFluid) input).fluid);
			} else {
				inputItems.addAll(input.getInputs());
			}
		}
		if (!inputFluids.isEmpty()) {
			ingredients.setInputs(FluidStack.class, inputFluids);
		}
		ingredients.setInputs(ItemStack.class, inputItems);
		ingredients.setOutputs(ItemStack.class, multiRecipe.getOutputs().getAllOutputs());
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer font = minecraft.fontRenderer;
		int value = getEntryTicks(multiRecipe.getOutputs());
		DecimalFormat df = new DecimalFormat("###.##");
		font.drawString("Progress: " + df.format(100.0 / (1000000.0 / value)) + "%", 0, 40, Color.black.getRGB());
		font.drawString("Multiplier: " + value / 5000.0 + "X", 0, 50, Color.black.getRGB());
		font.drawString("Cost: " + NumberFormat.getNumberInstance(Locale.US).format(value)
				+ " EU", 0, 60, Color.black.getRGB());
		if (GTConfig.general.debugMode) {
			font.drawString("Recipe Id: " + multiRecipe.getRecipeID(), 0, 70, Color.black.getRGB());
		}
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