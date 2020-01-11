package gtclassic.common.util.datanet;

import gtclassic.common.tile.GTTileQuantumTank;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;

public class GTFilterQuantumTank implements IFilter {

	GTTileQuantumTank tile;

	public GTFilterQuantumTank(GTTileQuantumTank tile) {
		this.tile = tile;
	}

	@Override
	public boolean matches(ItemStack stack) {
		if (FluidUtil.getFluidHandler(stack) == null) {
			return false;
		}
		if (this.tile.getTankInstance().getFluid() == null || this.tile.getTankInstance().getFluidAmount() == 0) {
			return true;
		}
		return this.tile.getTankInstance().getFluid().getFluid() == FluidUtil.getFluidContained(stack).getFluid();
	}
}
