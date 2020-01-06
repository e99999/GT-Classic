package gtclassic.common.block.datanet;

import java.awt.Color;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.model.GTModelBlock;
import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockStorage;
import gtclassic.common.tile.datanet.GTTilePipelineFluid;
import gtclassic.common.tile.datanet.GTTilePipelineItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockPipeline extends GTBlockStorage implements ILayeredBlockModel, ICustomModeledBlock {

	public static final Color REFINED_IRON = new Color(220, 235, 235);

	public GTBlockPipeline(String name, LocaleComp comp) {
		super(name + "_pipeline", comp, 3);
	}

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		if (worldIn != null) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof IGTRecolorableStorageTile) {
				IGTRecolorableStorageTile colorTile = (IGTRecolorableStorageTile) tile;
				if (colorTile.isColored()) {
					return colorTile.getTileColor();
				}
			}
		}
		return REFINED_IRON;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this == GTBlocks.pipelineItem) {
			return new GTTilePipelineItem();
		}
		return new GTTilePipelineFluid();
	}

	@Override
	public boolean hasFacing() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return null;
	}

	@Override
	public boolean isLayered(IBlockState state) {
		return state.getValue(active);
	}

	@Override
	public int getLayers(IBlockState state) {
		return state.getValue(active) ? 2 : 1;
	}

	@Override
	public AxisAlignedBB getRenderBox(IBlockState var1, int layer) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState state, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[11];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getLayerTexture(IBlockState state, EnumFacing facing, int layer) {
		if (state.getValue(active) && layer == 1) {
			return Ic2Icons.getTextures(GTMod.MODID + "_materials")[this == GTBlocks.pipelineFluid ? 13 : 12];
		}
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[11];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelBlock(this, state);
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
}
