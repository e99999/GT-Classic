package gtclassic.common.util.datanet;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.common.tile.datanet.GTTileQuantumChest;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;

public class GTFilterQuantumChest implements IFilter {

	GTTileQuantumChest tile;

	public GTFilterQuantumChest(GTTileQuantumChest tile) {
		this.tile = tile;
	}

	@Override
	public boolean matches(ItemStack stack) {
		if (tile.display() == null || tile.display().isEmpty()) {
			return true;
		}
		return GTHelperStack.isEqual(tile.display(), stack);
	}
}
