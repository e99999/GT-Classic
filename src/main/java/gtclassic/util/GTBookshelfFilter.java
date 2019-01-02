package gtclassic.util;

import gtclassic.block.tileentity.GTTileEntityBookshelf;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTBookshelfFilter implements IFilter {
	GTTileEntityBookshelf bookshelf;

	public GTBookshelfFilter(GTTileEntityBookshelf tile) {
		this.bookshelf = tile;
	}

	public boolean matches(ItemStack stack) {
		return stack.getItem() == Items.BOOK || stack.hasEffect();
	}

}
