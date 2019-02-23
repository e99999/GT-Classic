package gtclassic.block;

import java.util.Arrays;
import java.util.List;

import ic2.core.block.base.BlockMultiID;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public abstract class GTBlockMultiID extends BlockMultiID
{
	
	public GTBlockMultiID(Material materialIn)
	{
		super(materialIn);
	}
	
	@Override
	public List<Integer> getValidMetas()
	{
		return Arrays.asList(0);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainerIC2(this, allFacings, active);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState();
	}

	@Override
	public IBlockState getDefaultBlockState()
	{
		IBlockState state = getDefaultState().withProperty(active, false);
		if(hasFacing())
		{
			state = state.withProperty(allFacings, EnumFacing.NORTH);
		}
		return state;
	}
}
