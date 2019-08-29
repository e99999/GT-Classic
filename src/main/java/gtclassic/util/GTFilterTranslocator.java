package gtclassic.util;

import gtclassic.tile.GTTileTranslocator;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;

public class GTFilterTranslocator implements IFilter {

	GTTileTranslocator tile;

	public GTFilterTranslocator(GTTileTranslocator tile) {
		this.tile = tile;
	}

	@Override
	public boolean matches(ItemStack paramItemStack) {
		if (this.tile.inventory.stream().allMatch(e -> e.isEmpty())) {
			return true;
		}
		for (ItemStack invStack : this.tile.inventory) {
			if (GTStackUtil.isEqual(invStack, paramItemStack)) {
				return true;
			}
		}
		return false;
	}
}
