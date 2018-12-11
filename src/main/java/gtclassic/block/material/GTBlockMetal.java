package gtclassic.block.material;

import java.util.List;

import gtclassic.GTClassic;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockMetal extends Block implements ITexturedBlock {
	public enum GTBlockMetalVariants {
		IRIDIUM_REINFORCED_STONE(64), RUBY(66), SAPPHIRE(67), ALUMINIUM(68), TITANIUM(69), CHROME(70);

		private int id;

		GTBlockMetalVariants(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTBlockMetalVariants variant;

	public GTBlockMetal(GTBlockMetalVariants variant) {
		super(Material.IRON);
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_block");
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_block");
		setCreativeTab(GTClassic.creativeTabGT);
		setHardness(5.0F);
		setResistance(15.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
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
		return Ic2Icons.getTextures("gtclassic_blocks")[variant.getID()];
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTClassic.MODID + ".nomobs"));
		tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTClassic.MODID + ".beacon"));
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
}
