package gtclassic.api.pipe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.material.GTMaterial;
import gtclassic.common.GTLang;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockPipe extends BlockMultiID implements ICustomModeledBlock, IGTColorBlock {

	GTMaterial mat;
	Color color;
	boolean fluid;

	public GTBlockPipe(GTMaterial mat, boolean fluid) {
		super(Material.IRON);
		this.mat = mat;
		this.color = mat.getColor();
		this.fluid = fluid;
		setRegistryName("pipe_" + mat.getName());
		setUnlocalizedName(GTLang.PIPE);
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(5.0F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainerIC2(this, allFacings, active);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public IBlockState getDefaultBlockState() {
		IBlockState state = getDefaultState().withProperty(active, false);
		if (hasFacing()) {
			state = state.withProperty(allFacings, EnumFacing.NORTH);
		}
		return state;
	}

	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}

	@Override
	public List<IBlockState> getValidStateList() {
		IBlockState def = getDefaultState();
		List<IBlockState> states = new ArrayList<>();
		for (EnumFacing side : EnumFacing.VALUES) {
			states.add(def.withProperty(allFacings, side).withProperty(active, false));
			states.add(def.withProperty(allFacings, side).withProperty(active, true));
		}
		return states;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this.fluid) {
			return new GTTilePipeFluid();
		}
		return new GTTilePipeItem();
	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int var1) {
		return Ic2Icons.getTextures("pipe");
	}

	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public BaseModel getModelFromState(IBlockState var1) {
		return new GTModelPipe(var1, Ic2Icons.getTextures("pipe")[0]);
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		try {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof GTTilePipeBase) {
				GTTilePipeBase pipe = (GTTilePipeBase) tile;
				return new BlockStateContainerIC2.IC2BlockState(state, pipe.getConnections());
			}
		} catch (Exception exception) {
		}
		return super.getExtendedState(state, world, pos);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public Color getColor(Block block, int index) {
		return this.color;
	}
}
