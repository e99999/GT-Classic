package gtclassic.common.jei;

import java.awt.Color;
import java.util.ArrayList;

import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class GTJeiTrophyWrapper implements IRecipeWrapper {

	private MultiRecipe multiRecipe;

	public GTJeiTrophyWrapper(MultiRecipe multiRecipe) {
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
		font.drawString("Production: " + getEntryValue(multiRecipe.getOutputs())
				+ " EU/t", 0, 40, Color.black.getRGB());
	}

	public MultiRecipe getMultiRecipe() {
		return multiRecipe;
	}

	public static int getEntryValue(MachineOutput output) {
		if (output == null || output.getMetadata() == null) {
			return 0;
		}
		return (output.getMetadata().getInteger("RecipeTime"));
	}
}