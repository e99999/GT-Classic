package gtclassic.api.block;

import java.util.List;

import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GTBlockBase extends Block implements ITexturedBlock, ILocaleBlock {

	private LocaleComp comp;

	public GTBlockBase(Material materialIn) {
		super(materialIn);
		this.comp = Ic2Lang.nullKey;
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
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
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

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromMeta(stack.getMetadata());
	}

	public LocaleComp getLocaleComp() {
		return this.comp;
	}
}
