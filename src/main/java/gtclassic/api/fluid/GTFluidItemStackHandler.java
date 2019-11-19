package gtclassic.api.fluid;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;

public class GTFluidItemStackHandler extends FluidHandlerItemStackSimple {

	protected final ItemStack emptyContainer;

	public GTFluidItemStackHandler(ItemStack container, ItemStack emptyContainer, int capacity) {
		super(container, capacity);
		this.emptyContainer = emptyContainer;
	}

	@Override
	protected void setContainerToEmpty() {
		this.emptyContainer.setTagCompound(null);
		container = emptyContainer;
	}
}
