package gtclassic.block;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.GTOreGen;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockOreSand extends BlockFalling implements ITexturedBlock, ILocaleBlock {

	GTOreGen ore;
	String name;
	int id;
	int level;
	float hardness;
	LocaleComp comp;

	public GTBlockOreSand(GTOreGen ore, int id, int level, float hardness) {
		super(Material.SAND);
		this.ore = ore;
		this.id = id;
		this.name = ore.getMaterial().getDisplayName();
		this.comp = Ic2Lang.nullKey;
		this.level = level;
		this.hardness = hardness;
		setRegistryName(this.name.toLowerCase() + "_sand");
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase() + "_sand");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(this.hardness);
		setSoundType(SoundType.SAND);
		setHarvestLevel("shovel", this.level);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// TODO add tooltips
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_ores")[this.id];
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

	public GTOreGen getOreEntry() {
		return this.ore;
	}

}
