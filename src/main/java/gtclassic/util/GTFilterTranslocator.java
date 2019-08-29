package gtclassic.util;

import gtclassic.tile.GTTileTranslocator;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;

public class GTFilterTranslocator implements IFilter {

	GTTileTranslocator tile;

	public GTFilterTranslocator(GTTileTranslocator tile) {
		this.tile = tile;
	}

	public boolean matches(ItemStack stack) {
		if (stack.isEmpty()) {
			return false; // InputStack is null so it does not match.
		}
		int noneEmptyStacks = 0; // Stacks that are not empty
		for (int i = 0; i < tile.inventory.size(); i++) {
			ItemStack inventoryStack = tile.inventory.get(i);
			if (inventoryStack.isEmpty()) {
				continue;// Skip because the inventory is empty we dont need to compare it.
			}
			noneEmptyStacks++;
			if (GTStackUtil.isEqual(stack, inventoryStack)) {
				return true; // Found
			}
		}
		return noneEmptyStacks == 0; // If all stacks are empty means no filter. If it is more then 1 stack in the
										// filter then return false because it didnt match the filter.
	}
}
