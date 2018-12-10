package gtclassic.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;
import java.util.Arrays;

public class GTJeiCentrifugeWrapper extends BlankRecipeWrapper {
	RecipeEntry entry;

	public GTJeiCentrifugeWrapper(RecipeEntry recipe) {
		this.entry = recipe;
	}

	public void getIngredients(IIngredients components) {
		components.setInputLists(ItemStack.class, Arrays.asList(this.entry.getInput().getInputs()));
		components.setOutputs(ItemStack.class, this.entry.getOutput().copy().getAllOutputs());
	}

}
