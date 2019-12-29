package gtclassic.common.block;

import java.awt.Color;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.model.GTModelOre;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockOreBedrock extends GTBlockBase implements ILayeredBlockModel, ICustomModeledBlock, IGTColorBlock {

	GTMaterial mat;

	public GTBlockOreBedrock(GTMaterial mat) {
		super(Material.ROCK);
		this.mat = mat;
		setRegistryName(this.mat.getName() + "_bedrockore");
		setUnlocalizedName(GTMod.MODID + ".oreBedRock" + this.mat.getDisplayName());
		setCreativeTab(GTMod.creativeTabGT);
		setBlockUnbreakable();
		setResistance(100.0F);
		setSoundType(SoundType.STONE);
	}

	@Override
	public boolean isLayered(IBlockState var1) {
		return true;
	}

	@Override
	public int getLayers(IBlockState var1) {
		return 2;
	}

	@Override
	public AxisAlignedBB getRenderBox(IBlockState var1, int layer) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState state, EnumFacing par1) {
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/bedrock");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/bedrock");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getLayerTexture(IBlockState state, EnumFacing facing, int layer) {
		if (layer == 0) {
			// base bedrock layer
			return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/bedrock");
		}
		// this should return and ore texture over it and it does not
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[18];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelOre(this, state);
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
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		return index == 0 ? Color.white : this.mat.getColor();
	}
}
