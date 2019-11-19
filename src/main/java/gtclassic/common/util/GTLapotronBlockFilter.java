package gtclassic.common.util;

import gtclassic.common.GTBlocks;
import gtclassic.common.tile.GTTileLESU;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTLapotronBlockFilter implements AabbUtil.IBlockFilter {

	TileEntity tile;

	public GTLapotronBlockFilter(TileEntity tile) {
		this.tile = tile;
	}

	@Override
	public boolean isValidBlock(IBlockState state) {
		return state.equals(GTBlocks.casingLapotron.getDefaultState());
	}

	@Override
	public boolean isValidBlock(World world, BlockPos blockPos) {
		Block block = world.getBlockState(blockPos).getBlock();
		if (block == GTBlocks.tileLESU) {
			if (blockPos.equals(this.tile.getPos())) {
				return false;
			}
			TileEntity worldTile = world.getTileEntity(blockPos);
			if (worldTile instanceof GTTileLESU) {
				((GTTileLESU) worldTile).enabled = false;
			}
			return false;
		}
		return block == GTBlocks.casingLapotron;
	}

	@Override
	public boolean forceChunkLoad() {
		return false;
	}
}
