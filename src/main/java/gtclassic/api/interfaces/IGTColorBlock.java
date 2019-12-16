package gtclassic.api.interfaces;

import java.awt.Color;

import ic2.core.platform.textures.obj.IColorEffectedTexture;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IGTColorBlock extends IColorEffectedTexture {

	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index);
}
