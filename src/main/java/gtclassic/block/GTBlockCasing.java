package gtclassic.block;

import java.util.List;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockCasing extends Block implements ITexturedBlock {
	public enum GTBlockCasingVariants {
		IRON(50), ALUMINIUM(51), TITANIUM(52), CHROME(53), IRIDIUM(54), TUNGSTEN(55), PLATINUM(56),

		SUPERCONDUCTOR(0), FUSION(1), FISSION(2), CRYSTAL(3);

		private int id;

		GTBlockCasingVariants(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTBlockCasingVariants variant;

	public GTBlockCasing(GTBlockCasingVariants variant) {
		super(Material.IRON);
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_casing");
		setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_casing");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(5.0F);
		setResistance(30.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[variant.getID()];
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTMod.MODID + ".nomobs"));
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
}
