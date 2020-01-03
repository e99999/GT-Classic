package gtclassic.common.block;

import java.awt.Color;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.material.GTMaterial;
import gtclassic.common.GTBlocks;
import gtclassic.common.tile.pipeline.GTTilePipelineFluid;
import gtclassic.common.tile.pipeline.GTTilePipelineItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockPipeline extends GTBlockStorage {

	GTMaterial mat;

	public GTBlockPipeline(GTMaterial mat, LocaleComp comp) {
		super(mat.getDisplayName() + "_pipeline", comp, 2);
		this.mat = mat;
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
		return this.mat.getColor();
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

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[13];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}
}
