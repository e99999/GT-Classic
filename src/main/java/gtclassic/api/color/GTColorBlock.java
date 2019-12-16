package gtclassic.api.color;

import gtclassic.api.interfaces.IGTColorBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GTColorBlock implements IBlockColor {

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		return ((IGTColorBlock) state.getBlock()).getColor(state, worldIn, pos, state.getBlock(), tintIndex).getRGB();
	}
}
