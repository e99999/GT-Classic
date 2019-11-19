package gtclassic.api.recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import gtclassic.GTMod;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.ItemWithMeta;
import net.minecraft.item.ItemStack;

public class GTRecipeMultiInputList {

	public static final MultiRecipe INVALID_RECIPE = new MultiRecipe(new ArrayList<IRecipeInput>(), new MachineOutput(null, new ArrayList<ItemStack>()), "Invalid", 0);
	protected List<MultiRecipe> recipes = new ArrayList<MultiRecipe>();
	protected Map<String, MultiRecipe> recipeMap = new LinkedHashMap<String, MultiRecipe>();
	protected Map<String, List<IRecipeInput>> massChangeRemoveMap = new LinkedHashMap<String, List<IRecipeInput>>();
	protected Map<ItemWithMeta, List<IRecipeInput>> validInputs = new LinkedHashMap<ItemWithMeta, List<IRecipeInput>>();
	String category;
	int energy;

	public GTRecipeMultiInputList(String category, int energy) {
		this.category = category;
		this.energy = energy;
	}

	public GTRecipeMultiInputList(String category) {
		this(category, 0);
	}

	public void addRecipe(List<IRecipeInput> inputs, MachineOutput output, String id, int eu) {
		id = getRecipeID(recipeMap.keySet(), id, 0);
		if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
			return;
		}
		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i) != null && isListInvalid(inputs.get(i).getInputs())) {
				GTMod.debugLogger("Recipe[" + id + "] has a invalid input for machine " + category);
				return;
			}
		}
		if (isListInvalid(output.getAllOutputs())) {
			GTMod.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
			for (int i = 0; i < inputs.size(); i++) {
				GTMod.debugLogger("Recipe[" + id + ": " + inputs.get(i) + "] as input " + category);
			}
			return;
		}
		MultiRecipe recipe = new MultiRecipe(inputs, output, id, eu);
		recipes.add(recipe);
		recipeMap.put(id, recipe);
		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i) != null) {
				for (ItemStack stack : inputs.get(i).getInputs()) {
					ItemWithMeta meta = new ItemWithMeta(stack);
					List<IRecipeInput> list = validInputs.get(meta);
					if (list == null) {
						list = new ArrayList<IRecipeInput>();
						validInputs.put(meta, list);
					}
					list.add(inputs.get(i));
				}
			}
		}
	}

	boolean massChanging = false;

	public void startMassChange() {
		massChanging = true;
	}

	public void removeRecipe(String id) {
		;
		if (!recipeMap.containsKey(id)) {
			GTMod.logger.info("Recipe[" + id + "] doesn't exist for machine " + category);
			return;
		}
		MultiRecipe recipe = getFromID(id);
		recipes.remove(recipe);
		recipeMap.remove(id);
		if (!massChanging) {
			rebuildValidInputs();
		}
	}

	public void rebuildValidInputs() {
		validInputs.clear();
		for (MultiRecipe recipe : recipes) {
			List<IRecipeInput> inputs = recipe.getInputs();
			for (int i = 0; i < inputs.size(); i++) {
				if (inputs.get(i) != null) {
					for (ItemStack stack : inputs.get(i).getInputs()) {
						ItemWithMeta meta = new ItemWithMeta(stack);
						List<IRecipeInput> list = validInputs.get(meta);
						if (list == null) {
							list = new ArrayList<IRecipeInput>();
							validInputs.put(meta, list);
						}
						list.add(inputs.get(i));
					}
				}
			}
		}
	}

	public void finishMassChange() {
		if (!massChanging) {
			return;
		}
		massChanging = false;
		rebuildValidInputs();
	}

	public static String getRecipeID(Set<String> ids, String base, int index) {
		String newString = base;
		if (index > 0) {
			newString = newString + "_" + index;
		}
		if (ids.contains(newString)) {
			return getRecipeID(ids, base, index + 1);
		}
		return newString;
	}

	public boolean isValidRecipeInput(ItemStack stack) {
		List<IRecipeInput> inputs = validInputs.get(new ItemWithMeta(stack));
		if (inputs == null) {
			return false;
		}
		for (IRecipeInput input : inputs) {
			if (input.matches(stack)) {
				return true;
			}
		}
		return false;
	}

	public MultiRecipe getPriorityRecipe(Predicate<MultiRecipe> checker) {
		MultiRecipe match = INVALID_RECIPE;
		for (MultiRecipe recipe : recipes) {
			if (recipe.getInputSize() > match.getInputSize() && checker.test(recipe)) {
				match = recipe;
			}
		}
		return match;
	}

	public MultiRecipe getRecipe(Predicate<MultiRecipe> checker) {
		for (MultiRecipe recipe : recipes) {
			if (checker.test(recipe)) {
				return recipe;
			}
		}
		return INVALID_RECIPE;
	}

	public List<MultiRecipe> getAllRecipes(Predicate<MultiRecipe> checker) {
		List<MultiRecipe> result = new ArrayList<>();
		for (MultiRecipe recipe : recipes) {
			if (checker.test(recipe))
				result.add(recipe);
		}
		return result;
	}

	public MultiRecipe getFromID(String id) {
		return recipeMap.get(id);
	}

	private boolean isListInvalid(List<ItemStack> list) {
		if (list.isEmpty()) {
			return true;
		}
		for (ItemStack stack : list) {
			if (stack.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static class MultiRecipe {

		List<IRecipeInput> inputs;
		MachineOutput outputs;
		String id;
		int eu;

		public MultiRecipe(List<IRecipeInput> inputs, MachineOutput outputs, String id, int eu) {
			this.inputs = inputs;
			this.outputs = outputs;
			this.id = id;
			this.eu = eu;
		}

		public String getRecipeID() {
			return id;
		}

		public int getInputSize() {
			return inputs.size();
		}

		public int getMachineEu() {
			return this.eu;
		}

		public IRecipeInput getInput(int slot) {
			if (inputs.size() <= slot) {
				return null;
			}
			return inputs.get(slot);
		}

		public boolean matches(int slot, ItemStack stack) {
			if (inputs.size() <= slot || stack.isEmpty())
				return false;
			IRecipeInput input = inputs.get(slot);
			return input.matches(stack) && input.getAmount() <= stack.getCount();
		}

		public boolean matchesIgnoringSize(int slot, ItemStack stack) {
			if (inputs.size() <= slot || stack.isEmpty())
				return false;
			IRecipeInput input = inputs.get(slot);
			return input.matches(stack);
		}

		public List<IRecipeInput> getInputs() {
			return inputs;
		}

		public MachineOutput getOutputs() {
			return outputs;
		}
	}

	public Collection<MultiRecipe> getRecipeList() {
		return new ArrayList<MultiRecipe>(recipes);
	}

	public String getCategory() {
		return this.category;
	}
}
