package gtclassic.api.recipe;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.base.Strings;

import gtclassic.GTMod;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.CompareableStack;
import net.minecraft.item.ItemStack;

public class GTRecipeBasicMachineList extends BasicMachineRecipeList {

	String registryID;

	public GTRecipeBasicMachineList(String id) {
		super(id);
		registryID = id;
	}

	@Override
	public void addRecipe(IRecipeInput input, MachineOutput output, String id) {
		assert output != null;
		assert input != null;
		assert !Strings.isNullOrEmpty(id);
		if (checksRecipes() && !RecipeManager.register(registryID, id)) {
			return;
		}
		RecipeEntry toAdd = new RecipeEntry(input, output, id);
		Map<CompareableStack, RecipeEntry> addMap = new LinkedHashMap<CompareableStack, RecipeEntry>();
		for (ItemStack stack : input.getInputs()) {
			if (stack.isEmpty()) {
				continue;
			}
			CompareableStack meta = new CompareableStack(stack);
			RecipeEntry entry = recipeMap.get(meta);
			if (entry != null) {
				GTMod.logger.info("Recipe Overlap: " + entry.getInput() + " Recipe ID: " + id);
				return;
			}
			addMap.put(meta, toAdd);
		}
		recipeMap.putAll(addMap);
	}
}
