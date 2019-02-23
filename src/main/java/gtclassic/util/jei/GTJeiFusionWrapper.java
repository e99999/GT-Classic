package gtclassic.util.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileFusionComputer;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

@SuppressWarnings("deprecation")
public class GTJeiFusionWrapper extends BlankRecipeWrapper {
	RecipeEntry entry;

	public GTJeiFusionWrapper(RecipeEntry recipe) {
		this.entry = recipe;
	}

	@Override
	public void getIngredients(IIngredients components) {
		// TODO SEE IF THIS FUCKED ANYTHING! i changed setinputlist to work with new
		// materials
		components.setInputs(ItemStack.class,
				Arrays.asList(entry.getInput().getInputs(),
						Arrays.asList((GTMaterialGen.getChemical(GTMaterial.Dueterium, 1)),
								GTTileFusionComputer.getRequiredCells(entry.getOutput()))));
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
