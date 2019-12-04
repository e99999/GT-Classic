package gtclassic.api.pipe;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;

public class GTTilePipeItem extends GTTilePipeBase{
	
	public GTTilePipeItem() {
		
	}
	
	@Override
	public boolean canConnect(TileEntity tile, EnumFacing dir) {
		if (tile == null) {
			return false;
		}
		if (tile instanceof GTTilePipeItem) {
			return true;
		}
		return tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir);
	}
}
