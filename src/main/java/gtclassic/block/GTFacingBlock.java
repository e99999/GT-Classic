package gtclassic.block;

import java.util.ArrayList;
import java.util.List;

import gtclassic.util.GTValues;
import ic2.api.tile.IWrenchable;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GTFacingBlock extends Block implements ICustomModeledBlock, ILocaleBlock, IWrenchable {
	BaseModel model;

	public GTFacingBlock(Material materialIn) {
		super(materialIn);

		this.setDefaultState(this.blockState.getBaseState().withProperty(GTValues.FACING, EnumFacing.NORTH));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		List<IBlockState> states = new ArrayList<>();
		for (EnumFacing facing : EnumFacing.VALUES) {
			states.add(this.blockState.getBaseState().withProperty(GTValues.FACING, facing));
		}
		return states;
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return getDefaultState();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, GTValues.FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(GTValues.FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(GTValues.FACING, EnumFacing.getFront(meta & 7));
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		EnumFacing facing = EnumFacing.fromAngle(placer.rotationYaw).getOpposite();
		worldIn.setBlockState(pos, state.withProperty(GTValues.FACING, facing), 3);
	}

	@Override
	public EnumFacing getFacing(World world, BlockPos pos) {
		return world.getBlockState(pos).getValue(GTValues.FACING);
	}

	@Override
	public boolean setFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
		return world.setBlockState(pos, world.getBlockState(pos).withProperty(GTValues.FACING, newDirection), 3);
	}

	@Override
	public boolean wrenchCanRemove(World world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public BaseModel getModelFromState(IBlockState state) {
		if (model == null) {
			model = getNewModelInstance();
		}
		return model;
	}

	public abstract BaseModel getNewModelInstance();

	public void onTextureReload() {
		model = null;
	}
}
