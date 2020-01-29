package gtclassic.common.util;

import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTLogBlockFilter implements AabbUtil.IBlockFilter {

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValidBlock(IBlockState state) {
		return state.getBlock().getMaterial(state) == Material.WOOD;
	}

	@Override
	public boolean isValidBlock(World world, BlockPos blockPos) {
		return world.getBlockState(blockPos).getBlock().isWood(world, blockPos);
	}

	@Override
	public boolean forceChunkLoad() {
		return false;
	}
}
