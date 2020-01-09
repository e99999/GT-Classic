package gtclassic.common.util;

import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;

public class GTFilterFluidCapability implements IFilter {

	@Override
	public boolean matches(ItemStack stack) {
		return !stack.isEmpty() && FluidUtil.getFluidHandler(stack) != null;
	}
}
