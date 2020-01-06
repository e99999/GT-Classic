package gtclassic.common.util;

import gtclassic.common.GTBlocks;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTDataNetFitler implements AabbUtil.IBlockFilter {

	Block block;
	Block dCable = GTBlocks.dataCable;

	public GTDataNetFitler(Block block) {
		this.block = block;
	}

	@Override
	public boolean isValidBlock(IBlockState state) {
		return state.equals(this.block.getDefaultState()) || state.equals(this.dCable.getDefaultState());
	}

	@Override
	public boolean isValidBlock(World world, BlockPos blockPos) {
		Block block = world.getBlockState(blockPos).getBlock();
		return block == this.block || block == dCable;
	}

	@Override
	public boolean forceChunkLoad() {
		return false;
	}
}
