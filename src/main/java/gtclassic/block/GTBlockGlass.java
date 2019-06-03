package gtclassic.block;

import java.util.List;

import gtclassic.GTMod;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
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

public class GTBlockGlass extends BlockGlass implements ITexturedBlock, ILocaleBlock {

	String name;
	int id;
	LocaleComp comp;
	Material mat = Material.GLASS;

	public GTBlockGlass(String name, int id) {
		super(Material.GLASS, false);
		this.name = name;
		this.id = id;
		setRegistryName(this.name.toLowerCase() + "_glass");
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase() + "_glass");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(1.0F);
		setHarvestLevel("pickaxe", 0);
		setSoundType(SoundType.GLASS);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		if (this.mat == Material.GLASS) {
			return BlockRenderLayer.TRANSLUCENT;
		}
		return BlockRenderLayer.SOLID;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return this.mat != Material.GLASS;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return this.mat != Material.GLASS;
	}

	@Override
	@Deprecated
	public boolean isNormalCube(IBlockState state) {
		return this.mat != Material.GLASS;
	}

	public LocaleComp getName() {
		return this.comp;
	}

	public Block setUnlocalizedName(LocaleComp name) {
		this.comp = name;
		return super.setUnlocalizedName(name.getUnlocalized());
	}

	@Override
	public Block setUnlocalizedName(String name) {
		this.comp = new LocaleBlockComp("tile." + name);
		return super.setUnlocalizedName(name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[this.id];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromMeta(stack.getMetadata());
	}

	@Override
	public List<IBlockState> getValidStates() {
		return this.blockState.getValidStates();
	}
}
