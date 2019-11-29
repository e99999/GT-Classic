package gtclassic.api.interfaces;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IGTDisplayTickTile {

	@SideOnly(Side.CLIENT)
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand);
}
