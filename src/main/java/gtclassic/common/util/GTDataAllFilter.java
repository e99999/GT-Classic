package gtclassic.common.util;

import gtclassic.api.interfaces.IGTDataNetBlock;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTDataAllFilter implements AabbUtil.IBlockFilter {

	public GTDataAllFilter() {
	}

	@Override
	public boolean isValidBlock(IBlockState state) {
		return state.getBlock() instanceof IGTDataNetBlock;
	}

	@Override
	public boolean isValidBlock(World world, BlockPos blockPos) {
		Block worldBlock = world.getBlockState(blockPos).getBlock();
		return worldBlock instanceof IGTDataNetBlock || world.getTileEntity(blockPos) instanceof IGTDataNetBlock;
	}

	@Override
	public boolean forceChunkLoad() {
		return false;
	}
}
