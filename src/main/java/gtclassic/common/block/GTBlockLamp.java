package gtclassic.common.block;

import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileLamp;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTBlockLamp extends GTBlockStorage {

	public GTBlockLamp() {
		super("lamp", GTLang.TEST);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileLamp();
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean on = state.getValue(active).booleanValue();
		return on ? 15 : 0;
	}
}
