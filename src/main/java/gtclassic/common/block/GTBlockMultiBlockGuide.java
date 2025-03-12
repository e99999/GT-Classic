package gtclassic.common.block;

import java.util.List;

import gtclassic.GTMod;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockMultiBlockGuide extends BlockAir implements ITexturedBlock, ILocaleBlock {

	LocaleComp comp;

	public GTBlockMultiBlockGuide() {
		this.comp = Ic2Lang.nullKey;
		setRegistryName("multiblockguide");
		setTranslationKey(GTMod.MODID + "." + "multiblockguide");
		setLightOpacity(0);
	}

	@Override
	public LocaleComp getName() {
		return this.comp;
	}

	public Block setTranslationKey(LocaleComp name) {
		this.comp = name;
		return super.setTranslationKey(name.getUnlocalized());
	}

	@Override
	public Block setTranslationKey(String name) {
		this.comp = new LocaleBlockComp("tile." + name);
		return super.setTranslationKey(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		IBlockState neighborState = blockAccess.getBlockState(pos.offset(side));
		return neighborState.getBlock() == this;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
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

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures("gtclassic_transparent")[0];
	}

	@Override
	public List<IBlockState> getValidStates() {
		return this.blockState.getValidStates();
	}
	
}
