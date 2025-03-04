package gtclassic.common.block;

import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockLightSource extends BlockAir implements ITexturedBlock, ILocaleBlock {

	LocaleComp comp;

	public GTBlockLightSource() {
		this.lightValue = 15;
		this.comp = Ic2Lang.nullKey;
		setRegistryName("lightsource");
		setTranslationKey(GTMod.MODID + "." + "lightsource");
		this.setTickRandomly(true);
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
		return Ic2Icons.getTextures("gtclassic_terrain")[111];
	}

	@Override
	public List<IBlockState> getValidStates() {
		return this.blockState.getValidStates();
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		worldIn.setBlockToAir(pos);
	}

	@Override
	public int tickRate(World worldIn) {
		return 1;
	}
}
