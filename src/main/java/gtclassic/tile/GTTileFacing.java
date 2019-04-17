package gtclassic.tile;

import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class GTTileFacing extends TileEntityBlock {
	public GTTileFacing() {
		super();
		setFacing(EnumFacing.NORTH);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing();
	}
}
