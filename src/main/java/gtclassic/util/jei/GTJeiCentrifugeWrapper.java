package gtclassic.util.jei;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTItems;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.core.platform.registry.Ic2Formatters;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

@SuppressWarnings("deprecation")
public class GTJeiCentrifugeWrapper extends BlankRecipeWrapper {
	RecipeEntry entry;

	public GTJeiCentrifugeWrapper(RecipeEntry recipe) {
		this.entry = recipe;
	}

	@Override
	public void getIngredients(IIngredients components) {
		components.setInputLists(ItemStack.class, Arrays.asList(entry.getInput().getInputs(), Arrays.asList(
				new ItemStack(GTItems.glassTube, GTTileIndustrialCentrifuge.getRequiredCells(entry.getOutput())))));
		List<List<ItemStack>> outputs = new ArrayList<List<ItemStack>>();
		int count = 0;
		for (ItemStack stack : entry.getOutput().copy().getAllOutputs()) {
			outputs.add(Arrays.asList(stack));
			count++;
			if (count >= 4) {
				break;
			}
		}
		components.setOutputLists(ItemStack.class, outputs);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer font = minecraft.fontRenderer;

		font.drawString(
				GTValues.centrifugeEU.getLocalizedFormatted(new Object[] { Ic2Formatters.bigFormat
						.format((long) GTTileIndustrialCentrifuge.getRequiredEU(entry.getOutput())) }),
				36, 24, Color.gray.getRGB());
	}
}
