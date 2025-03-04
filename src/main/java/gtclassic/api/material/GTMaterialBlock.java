package gtclassic.api.material;

import java.awt.Color;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTValues;
import gtclassic.api.interfaces.IGTColorBlock;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTMaterialBlock extends Block implements ITexturedBlock, IGTColorBlock, ILocaleBlock {

	private GTMaterial material;
	private GTMaterialFlag flag;
	LocaleComp comp;

	public GTMaterialBlock(GTMaterial material, GTMaterialFlag flag) {
		super(Material.IRON);
		this.material = material;
		this.flag = flag;
		this.comp = Ic2Lang.nullKey;
		setRegistryName(this.material.getName() + this.flag.getSuffix());
		setTranslationKey(GTMod.MODID + "." + this.flag.getPrefix() + this.material.getDisplayName());
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(5.0F);
		setResistance(15.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(GTValues.TOOLTIP_NOMOBS));
		tooltip.add(I18n.format(GTValues.TOOLTIP_BEACON));
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
		return true;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		int id = flag.isCrafttweaker() ? 0 : flag.getTextureID();
		return Ic2Icons.getTextures(flag.getTexture())[id];
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
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

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		return this.material.getColor();
	}

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
}
