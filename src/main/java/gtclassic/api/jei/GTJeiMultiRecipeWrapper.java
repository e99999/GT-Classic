package gtclassic.api.jei;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import gtclassic.api.helpers.GTValues;
import gtclassic.api.recipe.GTFluidMachineOutput;
import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.common.GTConfig;
import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.energy.EnergyNet;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;


public class GTJeiMultiRecipeWrapper implements IRecipeWrapper {

	protected MultiRecipe multiRecipe;

	public GTJeiMultiRecipeWrapper(MultiRecipe multiRecipe) {
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
		MachineOutput output = multiRecipe.getOutputs();
		if (output instanceof GTFluidMachineOutput) {
			ingredients.setOutputs(FluidStack.class, ((GTFluidMachineOutput) output).getFluids());
		}
		ingredients.setOutputs(ItemStack.class, multiRecipe.getOutputs().getAllOutputs());
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		int extraHeight = 0;
		FontRenderer font = minecraft.fontRenderer;
		font.drawString("Ticks: " + getEntryTicks(multiRecipe.getOutputs()), 0, 40, Color.black.getRGB());
		font.drawString("Seconds: " + getEntryTicks(multiRecipe.getOutputs()) / 20.0F, 0, 50, Color.black.getRGB());
		font.drawString("Tier: "
				+ GTValues.getTierString(EnergyNet.instance.getTierFromPower(multiRecipe.getMachineEu())), 0, 60, Color.black.getRGB());
		font.drawString("Usage: " + multiRecipe.getMachineEu() + " EU/t", 0, 70, Color.black.getRGB());
		font.drawString("Cost: "
				+ NumberFormat.getNumberInstance(Locale.US).format(getEntryTicks(multiRecipe.getOutputs())
						* multiRecipe.getMachineEu())
				+ " EU", 0, 80, Color.black.getRGB());
		if (multiRecipe.getMachineEu() == 8192 && getEntryTicks(multiRecipe.getOutputs()) > 3000) {
			extraHeight = 10;
			font.drawString("Output: "
					+ NumberFormat.getNumberInstance(Locale.US).format(getEntryTicks(multiRecipe.getOutputs()) * 32000)
					+ " EU Out", 0, 90, Color.black.getRGB());
		}
		if (GTConfig.general.debugMode) {
			font.drawString("Recipe Id: " + multiRecipe.getRecipeID(), 0, 90 + extraHeight, Color.black.getRGB());
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
