package gtclassic.toxicdimension.block;

import java.util.List;
import java.util.Random;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockToxicGrass extends Block implements ITexturedBlock {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public GTBlockToxicGrass() {
		super(Material.GRASS);
		setRegistryName("toxic_grass");
		setUnlocalizedName(GTClassic.MODID + ".grassToxic");
		setCreativeTab(GTClassic.creativeTabGT);
		setHardness(1.0F);
		setSoundType(SoundType.GROUND);
		setHarvestLevel("shovel", 0);

	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		if (enumFacing == EnumFacing.UP) {
			return Ic2Icons.getTextures("gtclassic_blocks")[78];
		}
		if (enumFacing == EnumFacing.DOWN) {
			return Ic2Icons.getTextures("gtclassic_blocks")[76];
		} else {
			return Ic2Icons.getTextures("gtclassic_blocks")[77];
		}

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
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Blocks.DIRT.getItemDropped(
				Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
	}

}
