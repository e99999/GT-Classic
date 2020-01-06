package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class GTTileDigitizerFluid extends GTTileDigitizerBase implements IGTDebuggableTile {

	public GTTileDigitizerFluid() {
		super(0);
	}

	@Override
	public boolean onDataNetTick(BlockPos nodePos) {
		IFluidHandler start = FluidUtil.getFluidHandler(world, this.pos.offset(this.getFacing()), getFacing());
		IFluidHandler end = FluidUtil.getFluidHandler(world, nodePos, EnumFacing.UP);
		boolean canExport = start != null && end != null;
		if (canExport && FluidUtil.tryFluidTransfer(end, start, 4000, true) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Active: " + this.getActive(), false);
		data.put("Has Computer: " + this.hasComputer, false);
		data.put("Fluid Output Nodes: " + this.outputNodes.size(), false);
	}
}
