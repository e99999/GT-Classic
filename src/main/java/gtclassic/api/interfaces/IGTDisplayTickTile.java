package gtclassic.api.interfaces;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IGTDisplayTickTile {

	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand);
}
