package gtclassic.api.pipe;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class GTTilePipeFluid extends GTTilePipeBase{
	
	public GTTilePipeFluid() {
		
	}
	
	@Override
	public boolean canConnect(TileEntity tile, EnumFacing dir) {
		if (tile == null) {
			return false;
		}
		if (tile instanceof GTTilePipeFluid) {
			return true;
		}
		return tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir);
	}
}
