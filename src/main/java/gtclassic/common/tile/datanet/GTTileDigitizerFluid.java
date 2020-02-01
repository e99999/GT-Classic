package gtclassic.common.tile.datanet;

import gtclassic.api.helpers.GTUtility;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class GTTileDigitizerFluid extends GTTileBaseInputNode {

	/**
	 * Transmits Fluids from the facing pos to valid output nodes on the network
	 **/
	public GTTileDigitizerFluid() {
		super(0);
	}

	@Override
	public boolean onDataNetTick(GTTileBaseOutputNode node) {
		if (node.dataType() != GTUtility.DataType.FLUID) {
			return false;
		}
		IFluidHandler start = FluidUtil.getFluidHandler(world, this.pos.offset(this.getFacing()), getFacing());
		IFluidHandler end = FluidUtil.getFluidHandler(world, node.inventoryPos(), node.inventoryFacing());
		boolean canExport = start != null && end != null;
		if (canExport && node.tankFilter() == null) {
			return FluidUtil.tryFluidTransfer(end, start, 2000, true) != null;
		}
		if (canExport && node.tankFilter() != null) {
			FluidStack fake = FluidUtil.tryFluidTransfer(end, start, 500, false);
			if (fake != null && (fake.getFluid().getName() == node.tankFilter().getFluid().getName())) {
				return FluidUtil.tryFluidTransfer(end, start, 2000, true) != null;
			}
		}
		return false;
	}
}
