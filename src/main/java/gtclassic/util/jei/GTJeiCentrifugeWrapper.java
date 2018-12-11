package gtclassic.util.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.util.GTItems;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class GTJeiCentrifugeWrapper extends BlankRecipeWrapper {
	RecipeEntry entry;

	public GTJeiCentrifugeWrapper(RecipeEntry recipe) {
		this.entry = recipe;
	}

	@Override
	public void getIngredients(IIngredients components) {
		components.setInputLists(ItemStack.class,
				Arrays.asList(entry.getInput().getInputs(), Arrays.asList(new ItemStack(GTItems.glassTube,
						GTTileEntityIndustrialCentrifuge.getRequiredCells(entry.getOutput())))));
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
}
