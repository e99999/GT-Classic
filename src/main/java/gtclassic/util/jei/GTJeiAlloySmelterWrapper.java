package gtclassic.util.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

@SuppressWarnings("deprecation")
public class GTJeiAlloySmelterWrapper extends BlankRecipeWrapper {
	RecipeEntry entry;

	public GTJeiAlloySmelterWrapper(RecipeEntry recipe) {
		this.entry = recipe;
	}

	@Override
	public void getIngredients(IIngredients components) {

		List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
		int countin = 0;
		for (ItemStack stack : entry.getInput().getInputs()) {
			inputs.add(Arrays.asList(stack));
			countin++;
			if (countin >= 2) {
				break;
			}
		}
		components.setInputLists(ItemStack.class, inputs);

		List<List<ItemStack>> outputs = new ArrayList<List<ItemStack>>();
		int count = 0;
		for (ItemStack stack : entry.getOutput().copy().getAllOutputs()) {
			outputs.add(Arrays.asList(stack));
			count++;
			if (count >= 1) {
				break;
			}
		}
		components.setOutputLists(ItemStack.class, outputs);
	}
}
