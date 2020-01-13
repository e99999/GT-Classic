package gtclassic.common.tile.datanet;

import gtclassic.common.util.datanet.GTDataNet.DataType;
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
		if (node.dataType() != DataType.FLUID) {
			return false;
		}
		IFluidHandler start = FluidUtil.getFluidHandler(world, this.pos.offset(this.getFacing()), getFacing());
		IFluidHandler end = FluidUtil.getFluidHandler(world, node.inventoryPos(), node.inventoryFacing());
		boolean canExport = start != null && end != null;
		if (canExport && FluidUtil.tryFluidTransfer(end, start, 4000, true) != null) {
			return true;
		}
		return false;
	}
}
