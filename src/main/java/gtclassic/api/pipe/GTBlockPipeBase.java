package gtclassic.api.pipe;

import java.awt.Color;

import gtclassic.api.block.GTBlockBaseConnect;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.material.GTMaterial;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GTBlockPipeBase extends GTBlockBaseConnect implements IGTColorBlock {

	GTMaterial mat;
	Color color;
	int[] sizes;

	public GTBlockPipeBase(GTMaterial mat, int[] sizes) {
		super();
		this.mat = mat;
		this.color = mat.getColor();
		this.sizes = sizes;
		setHardness(5.0F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite[] getIconSheet(int var1) {
		return Ic2Icons.getTextures("pipe");
	}

	@SideOnly(Side.CLIENT)
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
	public boolean recolorBlock(World world, BlockPos pos, EnumFacing side, net.minecraft.item.EnumDyeColor color) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTilePipeBase) {
			GTTilePipeBase pipe = (GTTilePipeBase) tile;
			pipe.color = color.getColorValue();
			IBlockState state = tile.getWorld().getBlockState(tile.getPos());
			pipe.updateConnections();
			world.notifyBlockUpdate(pos, state, state, 2);
			return true;
		}
		return false;
	}

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		if (worldIn != null) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof GTTilePipeBase) {
				GTTilePipeBase pipe = (GTTilePipeBase) tile;
				if (pipe.isColored()) {
					return new Color(pipe.color);
				}
			}
		}
		return this.mat.getColor();
	}

	public GTMaterial getMaterial() {
		return this.mat;
	}
}
