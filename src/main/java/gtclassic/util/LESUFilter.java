package gtclassic.util;

import gtclassic.GTBlocks;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LESUFilter implements AabbUtil.IBlockFilter {

	@Override
	public boolean isValidBlock(IBlockState state) {
		return state.equals(GTBlocks.casingLapotron.getDefaultState());
	}

	@Override
	public boolean isValidBlock(World world, BlockPos blockPos) {
		return world.getBlockState(blockPos).equals(GTBlocks.casingLapotron.getDefaultState());
	}

	@Override
	public boolean forceChunkLoad() {
		return false;
	}
}
