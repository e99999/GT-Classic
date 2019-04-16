package gtclassic.block;

import java.util.List;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockTestLayer extends Block implements ILayeredBlockModel, ITexturedBlock {

	static final AxisAlignedBB LAYER_BLOCK_AABB = new AxisAlignedBB(0.005D, 0.005D, 0.005D, .99D, .99D, .99D);

	public GTBlockTestLayer() {
		super(Material.ROCK);
		setRegistryName("test_block");
		setUnlocalizedName(GTMod.MODID + ".testBlock");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(1.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return LAYER_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[32];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public List<IBlockState> getValidStates() {
		return this.blockState.getValidStates();
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromMeta(stack.getMetadata());
	}

	@Override
	public boolean isLayered(IBlockState state) {
		// returns if this block is layered using the layered interface
		return true;
	}

	@Override
	public int getLayers(IBlockState state) {
		// returns integer for amount of layers
		return 3;
	}

	@Override
	public AxisAlignedBB getRenderBox(IBlockState state, int layer) {
		// if the layer is the bottom (0) return a full bounding box
		// if not shrink it so a slightly smaller size?
		if (layer == 0) {
			return FULL_BLOCK_AABB;
		} else {
			return LAYER_BLOCK_AABB;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public net.minecraft.util.BlockRenderLayer getBlockLayer() {
		// allows transparent portions to pass
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public TextureAtlasSprite getLayerTexture(IBlockState state, EnumFacing facing, int layer) {
		// returns the second row of the sprite sheet and the first 3 sprites as layers
		// 0-2
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[16 + layer];
	}

}
