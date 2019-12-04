package gtclassic.api.pipe;

import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.util.math.Vec3i;

public class GTTilePipe extends TileEntityBlock{
	
	public GTTilePipe() {
		
	}
	
	public Vec3i getConnections() {
	      return new Vec3i(1, 2, 3);
	   }
}
