package gtclassic.api.slot;

import ic2.api.item.ICustomDamageItem;
import ic2.api.item.IElectricItem;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;

public class GTToolSlotFilter implements IFilter {

	@Override
	public boolean matches(ItemStack stack) {
		return !stack.isEmpty() && (stack.getItem().isDamageable() || stack.getItem() instanceof IElectricItem
				|| stack.getItem() instanceof ICustomDamageItem);
	}
}
