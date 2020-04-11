package gtclassic.common.block;

import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileLamp;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
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
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileLamp) {
			return ((GTTileLamp) tile).getActive() ? 15 : 0;
		} else {
			return 0;
		}
	}

	@Deprecated
	public int getLightValue(IBlockState state) {
		return 0;
	}
}
