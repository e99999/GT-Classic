package gtclassic.common.util;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.common.tile.datanet.GTTileDigitizerItem;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;

public class GTFilterItemDigitizer implements IFilter {

	GTTileDigitizerItem tile;

	public GTFilterItemDigitizer(GTTileDigitizerItem tile) {
		this.tile = tile;
	}

	public boolean matches(ItemStack stack) {
		if (stack.isEmpty()) {
			return true;
		}
		if (!this.tile.blacklist.isEmpty()) {
			for (ItemStack bStack : this.tile.blacklist) {
				if (GTHelperStack.isEqual(bStack, stack.copy())) {
					return true;
				}
			}
		}
		return false;
	}
}
