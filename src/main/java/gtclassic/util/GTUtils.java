package gtclassic.util;

import java.util.List;

import net.minecraft.item.ItemStack;

public class GTUtils {

	/** Created by Muramasa - Consume and return a ItemStack **/
	public static ItemStack consumeAndReturn(ItemStack toConsume, ItemStack recipeStack) {
		if (toConsume.getCount() >= recipeStack.getCount()) {
			toConsume.shrink(recipeStack.getCount());
			return ItemStack.EMPTY;
		} else {
			recipeStack.shrink(toConsume.getCount());
			toConsume.setCount(0);
			return recipeStack;
		}
	}

	/**
	 * Created by Muramasa - Returns the index of an item in a list, or -1 if not
	 * found
	 **/
	public static int contains(List<ItemStack> list, ItemStack item) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i).isItemEqual(item))
				return i;
		}
		return -1;
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
}
