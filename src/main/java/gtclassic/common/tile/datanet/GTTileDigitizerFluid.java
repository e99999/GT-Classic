package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class GTTileDigitizerFluid extends GTTileInputNodeBase implements IGTDebuggableTile {

	public GTTileDigitizerFluid() {
		super(0);
	}

	@Override
	public boolean onDataNetTick(GTTileOutputNodeBase node) {
		IFluidHandler start = FluidUtil.getFluidHandler(world, this.pos.offset(this.getFacing()), getFacing());
		IFluidHandler end = FluidUtil.getFluidHandler(world, node.getPos().offset(node.getFacing()), node.getFacing().getOpposite());
		boolean canExport = start != null && end != null;
		if (canExport && FluidUtil.tryFluidTransfer(end, start, 4000, true) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found", false);
		}
	}
}
