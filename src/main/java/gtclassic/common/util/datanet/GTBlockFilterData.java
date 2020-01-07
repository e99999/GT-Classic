package gtclassic.common.util.datanet;

import gtclassic.common.GTBlocks;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTBlockFilterData implements AabbUtil.IBlockFilter {

	Block block;
	Block dCable = GTBlocks.dataCable;

	/** This filter will loop over any cables or specific input block **/
	public GTBlockFilterData(Block block) {
		this.block = block;
	}

	@Override
	public boolean isValidBlock(IBlockState state) {
		return state.equals(this.block.getDefaultState()) || state.equals(this.dCable.getDefaultState());
	}

	@Override
	public boolean isValidBlock(World world, BlockPos blockPos) {
		Block worldBlock = world.getBlockState(blockPos).getBlock();
		return worldBlock == this.block || worldBlock == dCable;
	}

	@Override
	public boolean forceChunkLoad() {
		return false;
	}
}
