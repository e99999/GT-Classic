package gtclassic.common.util;

import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTIBlockFilter implements AabbUtil.IBlockFilter {

	Block block;

	public GTIBlockFilter(Block block) {
		this.block = block;
	}

	@Override
	public boolean isValidBlock(IBlockState state) {
		return state.equals(this.block.getDefaultState());
	}

	@Override
	public boolean isValidBlock(World world, BlockPos blockPos) {
		Block block = world.getBlockState(blockPos).getBlock();
		return block == this.block;
	}

	@Override
	public boolean forceChunkLoad() {
		return false;
	}
}
