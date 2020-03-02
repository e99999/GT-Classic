package gtclassic.api.helpers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.misc.StackUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class GTHelperStack {

	/** Checks if a stack can merge with default stack size **/
	public static boolean canMerge(ItemStack stack, ItemStack toCompare) {
		return canMerge(stack, toCompare, stack.getMaxStackSize());
	}

	/** Checks if a stack can merge **/
	public static boolean canMerge(ItemStack stack, ItemStack toCompare, int maxCount) {
		return (isEqual(stack, toCompare) && (toCompare.getCount() + stack.getCount() <= maxCount))
				|| toCompare.isEmpty();
	}

	/** Just an easy place for me to call this over and over **/
	public static boolean isEqual(ItemStack stack, ItemStack toCompare) {
		return StackUtil.isStackEqual(stack, toCompare, false, false);
	}

	/** Just an easy place for me to call this over and over **/
	public static ItemStack copyWithSize(ItemStack itemStack, int newSize) {
		ItemStack ret = itemStack.copy();
		ret.setCount(newSize);
		return ret;
	}

	/** Checks if a stack is equal or if they are "equal" via ore dictionary **/
	public static boolean isEqualCompareOreDict(ItemStack stack, ItemStack toCompare) {
		return StackUtil.isStackEqual(stack, toCompare, false, false) || sharesOreDict(stack, toCompare);
	}

	/** Checks if two items share ore dict entries **/
	public static boolean sharesOreDict(ItemStack stack, ItemStack toCompare) {
		if (!stack.isEmpty() && (OreDictionary.getOreIDs(stack).length > 0)) {
			for (int i = 0; i < OreDictionary.getOreIDs(stack).length; i++) {
				if (matchOreDict(toCompare, OreDictionary.getOreName(OreDictionary.getOreIDs(stack)[i]))) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEqualOrSharesOreDict(ItemStack stack, ItemStack toCompare) {
		return isEqual(stack, toCompare) || sharesOreDict(stack, toCompare);
	}

	/** Checks if a machine slot can accept a stack **/
	public static boolean canOutputStack(TileEntityMachine tile, ItemStack stack, int slot) {
		if (tile.inventory.get(slot).isEmpty()) {
			return true;
		}
		int count = tile.inventory.get(slot).getCount();
		boolean hasRoom = count < tile.inventory.get(slot).getMaxStackSize();
		return hasRoom && isEqual(tile.getStackInSlot(slot), stack);
	}

	/** Checks if a machine slot is empty **/
	public static boolean isSlotEmpty(TileEntityMachine tile, int slot) {
		return tile.inventory.get(slot).isEmpty();
	}

	/** Checks if a machine slot is full **/
	public static boolean isSlotFull(TileEntityMachine tile, int slot) {
		if (tile.inventory.get(slot).isEmpty()) {
			return false;
		}
		return tile.inventory.get(slot).getCount() == tile.inventory.get(slot).getMaxStackSize();
	}

	/** Gets the stacksize of a slot **/
	public int getSlotStackCount(TileEntityMachine tile, int slot) {
		if (tile.inventory.get(slot).isEmpty()) {
			return 0;
		}
		return tile.getStackInSlot(slot).getCount();
	}

	/** Created by Muramasa - Merges B into A, ignoring maxStackSize **/
	public static List<ItemStack> mergeItems(List<ItemStack> a, List<ItemStack> b) {
		int position, size = b.size();
		for (int i = 0; i < size; i++) {
			if (b.get(i).isEmpty())
				continue;
			position = contains(a, b.get(i));
			if (position == -1)
				a.add(b.get(i));
			else
				a.get(position).grow(b.get(i).getCount());
		}
		return a;
	}

	/** Returns the index of an item in a list, or -1 if not found **/
	public static int contains(List<ItemStack> list, ItemStack item) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (StackUtil.isStackEqual(list.get(i), item)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the index of an item in a list with stack size check, or -1 if not
	 * found
	 **/
	public static int containsWithSize(List<ItemStack> list, ItemStack item) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (StackUtil.isStackEqual(list.get(i), item) && item.getCount() >= list.get(i).getCount()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the index of an item in a list with stack size check, or -1 if not
	 * found
	 **/
	public static int containsWithSizeFuzzy(List<ItemStack> list, ItemStack item) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (GTHelperStack.isEqualOrSharesOreDict(list.get(i), item) && item.getCount() >= list.get(i).getCount()) {
				return i;
			}
		}
		return -1;
	}

	/** Checks if an itemstack has an oredict entry **/
	public static boolean matchOreDict(ItemStack stack, String entry) {
		if (!stack.isEmpty() && (OreDictionary.getOreIDs(stack).length > 0)) {
			for (int i = 0; i < OreDictionary.getOreIDs(stack).length; i++) {
				if (OreDictionary.getOreName(OreDictionary.getOreIDs(stack)[i]).contains(entry)) {
					return true;
				}
			}
		}
		return false;
	}

	/** Checks if an itemstack has an oredict entry **/
	public static boolean oreDictStartsWith(ItemStack stack, String entry) {
		if (!stack.isEmpty() && (OreDictionary.getOreIDs(stack).length > 0)) {
			for (int i = 0; i < OreDictionary.getOreIDs(stack).length; i++) {
				if (OreDictionary.getOreName(OreDictionary.getOreIDs(stack)[i]).startsWith(entry)) {
					return true;
				}
			}
		}
		return false;
	}

	/** removing smelting recipes code by Muramasa **/
	public static void removeSmelting(ItemStack resultStack) {
		ItemStack recipeResult;
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		Iterator<ItemStack> iterator = recipes.keySet().iterator();
		while (iterator.hasNext()) {
			ItemStack tmpRecipe = iterator.next();
			recipeResult = recipes.get(tmpRecipe);
			if (ItemStack.areItemStacksEqual(resultStack, recipeResult)) {
				iterator.remove();
			}
		}
	}

	/** Attempts to sort/merge similar itemstacks in an inventory **/
	public static void tryCondenseInventory(TileEntityMachine tile, int startSlot, int endSlot) {
		for (int i = startSlot; i < endSlot; ++i) {
			for (int j = startSlot; j < endSlot; ++j) {
				if (i == j) {
					continue;
				}
				ItemStack stack1 = tile.inventory.get(i);
				ItemStack stack2 = tile.inventory.get(j);
				if (!stack1.isEmpty() && !stack2.isEmpty()
						&& (GTHelperStack.isEqual(stack1, stack2) && stack1.getCount() < stack1.getMaxStackSize())) {
					int max = stack1.getMaxStackSize() - stack1.getCount();
					int available = stack2.getCount();
					int size = GTHelperMath.clip(available, 1, max);
					stack1.grow(size);
					stack2.shrink(size);
				}
				if (stack2.isEmpty() && !stack1.isEmpty() && j < i) {
					tile.inventory.set(j, stack1.copy());
					tile.inventory.set(i, ItemStack.EMPTY);
				}
			}
		}
	}
}
