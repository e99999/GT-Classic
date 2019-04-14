package gtclassic.util.recipe;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.base.Strings;

import gtclassic.GTMod;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.ItemWithMeta;
import net.minecraft.item.ItemStack;

public class GTBasicMachineRecipeList extends BasicMachineRecipeList {
	String registryID;

	public GTBasicMachineRecipeList(String id) {
		super(id);
		registryID = id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addRecipe(IRecipeInput input, MachineOutput output, String id) {
		assert output != null;
		assert input != null;
		assert !Strings.isNullOrEmpty(id);
		if (checksRecipes() && !RecipeManager.register(registryID, id)) {
			return;
		}
		RecipeEntry toAdd = new RecipeEntry(input, output, id);
		Map<ItemWithMeta, RecipeEntry> addMap = new LinkedHashMap<ItemWithMeta, RecipeEntry>();
		for (ItemStack stack : input.getInputs()) {
			if (stack.isEmpty()) {
				continue;
			}
			ItemWithMeta meta = new ItemWithMeta(stack);
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
