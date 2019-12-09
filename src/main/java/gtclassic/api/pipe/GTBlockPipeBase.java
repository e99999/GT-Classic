package gtclassic.api.pipe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.material.GTMaterial;
import ic2.core.block.base.BlockMultiID;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GTBlockPipeBase extends BlockMultiID implements ICustomModeledBlock, IGTColorBlock {

	GTMaterial mat;
	Color color;
	boolean fluid;
	int[] sizes;

	public GTBlockPipeBase(GTMaterial mat, int[] sizes) {
		super(Material.IRON);
		this.mat = mat;
		this.color = mat.getColor();
		this.sizes = sizes;
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
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelPipe(state, Ic2Icons.getTextures("pipe")[0], this.sizes);
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (!(tile instanceof GTTilePipeBase)) {
			return new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
		} else {
			GTTilePipeBase pipe = (GTTilePipeBase) tile;
			double thickness = (sizes[1] - sizes[0]) / 32.0D;
			double minX = 0.5D - thickness;
			double minY = 0.5D - thickness;
			double minZ = 0.5D - thickness;
			double maxX = 0.5D + thickness;
			double maxY = 0.5D + thickness;
			double maxZ = 0.5D + thickness;
			if (pipe.connection.contains(EnumFacing.WEST)) {
				minX = 0.0D;
			}
			if (pipe.connection.contains(EnumFacing.DOWN)) {
				minY = 0.0D;
			}
			if (pipe.connection.contains(EnumFacing.NORTH)) {
				minZ = 0.0D;
			}
			if (pipe.connection.contains(EnumFacing.EAST)) {
				maxX = 1.0D;
			}
			if (pipe.connection.contains(EnumFacing.UP)) {
				maxY = 1.0D;
			}
			if (pipe.connection.contains(EnumFacing.SOUTH)) {
				maxZ = 1.0D;
			}
			return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
		}
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

	public GTMaterial getMaterial() {
		return this.mat;
	}
}
