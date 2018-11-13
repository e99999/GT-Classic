package gtclassic.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;

import ic2.api.classic.recipe.INullableRecipeInput;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.ItemWithMeta;
import net.minecraft.item.ItemStack;

public class GTMultiInputRecipeList
{
	Map<ItemWithMeta, Map<ItemWithMeta, MultiRecipeEntry>> recipeMap = new LinkedHashMap<ItemWithMeta, Map<ItemWithMeta, MultiRecipeEntry>>();
	List<MultiRecipeEntry> allRecipes = new ArrayList<MultiRecipeEntry>();
	String ownID;
	
	public GTMultiInputRecipeList(String id)
	{
		ownID = id;
	}
	
	public boolean checksRecipes()
	{
		return true;
	}
	
	public void addRecipe(IRecipeInput input, IRecipeInput secondInput, MachineOutput output, String id)
	{
		assert !Strings.isNullOrEmpty(id);
		assert output != null;
		if(checksRecipes() && !RecipeManager.register(ownID, id))
		{
			return;
		}
		List<ItemStack> firstList = getItemList(input);
		if(firstList.isEmpty())
		{	
			return;
		}
		List<ItemStack> secondList = getItemList(input);
		if(secondList.isEmpty())
		{
			return;
		}
		MultiRecipeEntry recipeInstance = new MultiRecipeEntry(input, secondInput, output, id);
		for(ItemStack first : firstList)
		{
			for(ItemStack second : secondList)
			{
				ItemWithMeta firstMeta = new ItemWithMeta(first);
				ItemWithMeta secondMeta = new ItemWithMeta(second);
				Map<ItemWithMeta, MultiRecipeEntry> entries = recipeMap.get(firstMeta);
				if(entries == null)
				{
					entries = new LinkedHashMap<ItemWithMeta, MultiRecipeEntry>();
					recipeMap.put(secondMeta, entries);
				}
				entries.put(secondMeta, recipeInstance);
			}
		}
		allRecipes.add(recipeInstance);
	}
	
	public void removeRecipe(ItemStack input)
	{
		Map<ItemWithMeta, MultiRecipeEntry> entry = recipeMap.get(new ItemWithMeta(input));
		if(entry != null)
		{
			allRecipes.removeAll(entry.values());
		}
	}
	
	public void removeRecipe(ItemStack input, ItemStack secondInput)
	{
		Map<ItemWithMeta, MultiRecipeEntry> entry = recipeMap.get(new ItemWithMeta(input));
		if(entry != null)
		{
			MultiRecipeEntry recipe = entry.get(new ItemWithMeta(secondInput));
			if(recipe != null)
			{
				allRecipes.remove(recipe);
			}
		}
	}
	
	public void removeRecipe(MultiRecipeEntry entry)
	{
		for(ItemStack first : getItemList(entry.getFirstInput()))
		{
			for(ItemStack second : getItemList(entry.getSecondInput()))
			{
				removeRecipe(first, second);
			}
		}
	}
	
	public MultiRecipeEntry getRecipe(ItemStack firstInput, ItemStack secondInput, boolean ignoreCount)
	{
		ItemWithMeta firstMeta = new ItemWithMeta(firstInput);
		ItemWithMeta secondMeta = new ItemWithMeta(secondInput);
		MultiRecipeEntry entry = process(getEntry(firstMeta, secondMeta), ignoreCount, firstInput, secondInput);
		if(entry != null)
		{
			return entry;
		}
		entry = process(getEntry(secondMeta, firstMeta), ignoreCount, secondInput, firstInput);
		if(entry != null)
		{
			return entry;
		}
		entry = process(getEntry(firstMeta.toWildcard(), secondMeta), ignoreCount, firstInput, secondInput);
		if(entry != null)
		{
			return entry;
		}
		entry = process(getEntry(secondMeta, firstMeta.toWildcard()), ignoreCount, secondInput, firstInput);
		if(entry != null)
		{
			return entry;
		}
		entry = process(getEntry(firstMeta, secondMeta.toWildcard()), ignoreCount, firstInput, secondInput);
		if(entry != null)
		{
			return entry;
		}
		entry = process(getEntry(secondMeta.toWildcard(), firstMeta), ignoreCount, secondInput, firstInput);
		if(entry != null)
		{
			return entry;
		}
		entry = process(getEntry(firstMeta.toWildcard(), secondMeta.toWildcard()), ignoreCount, firstInput, secondInput);
		if(entry != null)
		{
			return entry;
		}
		entry = process(getEntry(secondMeta.toWildcard(), firstMeta.toWildcard()), ignoreCount, secondInput, firstInput);
		if(entry != null)
		{
			return entry;
		}
		return null;
	}
	
	private MultiRecipeEntry process(MultiRecipeEntry input, boolean ignore, ItemStack first, ItemStack second)
	{
		if(input == null)
		{
			return null;
		}
		IRecipeInput firstInput = input.getFirstInput();
		if(firstInput.matches(first) && (ignore || firstInput.getAmount() <= first.getCount()))
		{
			IRecipeInput secondInput = input.getSecondInput();
			if(secondInput.matches(second) && (ignore || secondInput.getAmount() <= second.getCount()))
			{
				return input;
			}
		}
		return null;
	}
	
	private MultiRecipeEntry getEntry(ItemWithMeta first, ItemWithMeta second)
	{
		Map<ItemWithMeta, MultiRecipeEntry> entry = recipeMap.get(first);
		if(entry != null)
		{
			return entry.get(second);
		}
		return null;
	}
	
	private List<ItemStack> getItemList(IRecipeInput input)
	{
		return input instanceof INullableRecipeInput ? Arrays.asList(ItemStack.EMPTY) : input.getInputs();
	}
	
	public static class MultiRecipeEntry
	{
		IRecipeInput firstInput;
		IRecipeInput secondInput;
		MachineOutput output;
		String id;
		
		public MultiRecipeEntry(IRecipeInput firstInput, IRecipeInput secondInput, MachineOutput output, String recipeID)
		{
			this.firstInput = firstInput;
			this.secondInput = secondInput;
			this.output = output;
			id = recipeID;
		}
		
		public String getRecipeID()
		{
			return id;
		}
		
		public IRecipeInput getFirstInput()
		{
			return firstInput;
		}
		
		public MachineOutput getOutput()
		{
			return output;
		}
		
		public IRecipeInput getSecondInput()
		{
			return secondInput;
		}
	}
}

