package gtclassic.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.materialsnew.GTMaterial;
import gtclassic.materialsnew.GTMaterialGen;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockOreStone extends Block implements ITexturedBlock {

	String name;
	int id;
	int harvest;
	float hardness;

	public GTBlockOreStone(String name, int id, int harvest, float hardness) {
		super(Material.ROCK);
		this.name = name;
		this.id = id;
		this.harvest = harvest;
		this.hardness = hardness;
		setRegistryName(this.name.toLowerCase() + "_ore");
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase() + "_ore");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(this.hardness);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", this.harvest);
		setSoundType(SoundType.STONE);
	}

	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();

		// TODO ADD FORTUNE DROPS

		// Nether Ores
		if (this == GTBlocks.cinnabarOre) {
			drops.add(GTMaterialGen.getDust(GTMaterial.Cinnabar, 2));
			if (RANDOM.nextFloat() < 0.25f) {
				drops.add(new ItemStack(Items.REDSTONE, 1));
			}

		}

		if (this == GTBlocks.pyriteOre) {
			drops.add(GTMaterialGen.getDust(GTMaterial.Pyrite, 2));
		}

		if (this == GTBlocks.sphaleriteOre) {
			drops.add(GTMaterialGen.getDust(GTMaterial.Sphalerite, 1));
			if (RANDOM.nextFloat() < 0.25f) {
				drops.add(GTMaterialGen.getDust(GTMaterial.Zinc, 1));
			}
			if (RANDOM.nextFloat() < 0.125f) {
				drops.add(GTMaterialGen.getGem(GTMaterial.GarnetYellow, 1));
			}
		}

		// End Ores
		if (this == GTBlocks.tungstateOre) {
			drops.add(new ItemStack(GTBlocks.tungstateOre, 1));
		}

		if (this == GTBlocks.sheldoniteOre) {
			drops.add(new ItemStack(GTBlocks.sheldoniteOre, 1));
		}

		if (this == GTBlocks.sodaliteOre) {
			drops.add(GTMaterialGen.getDust(GTMaterial.Sodalite, 6));
			if (RANDOM.nextFloat() < 0.25f) {
				drops.add(GTMaterialGen.getDust(GTMaterial.Aluminium, 1));
			}
		}

		if (this == GTBlocks.olivineOre) {
			drops.add(GTMaterialGen.getGem(GTMaterial.Olivine, 1));
		}

		// Default Ores
		if (this == GTBlocks.galenaOre) {
			drops.add(new ItemStack(GTBlocks.galenaOre, 1));
		}

		if (this == GTBlocks.iridiumOre) {
			drops.add(Ic2Items.iridiumOre);
		}

		if (this == GTBlocks.rubyOre) {
			if (RANDOM.nextFloat() > 0.10f) {
				drops.add(GTMaterialGen.getGem(GTMaterial.Ruby, 1));
			} else {
				drops.add(GTMaterialGen.getGem(GTMaterial.GarnetRed, 1));
			}
		}

		if (this == GTBlocks.sapphireOre) {// iron and add xp
			if (RANDOM.nextFloat() > 0.10f) {
				drops.add(GTMaterialGen.getGem(GTMaterial.Sapphire, 1));
			} else {
				drops.add(GTMaterialGen.getGem(GTMaterial.SapphireGreen, 1));
			}
		}

		if (this == GTBlocks.bauxiteOre) {// stone
			drops.add(new ItemStack(GTBlocks.bauxiteOre, 1));
		}

		return drops;
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		int xp = 0;

		if (this == GTBlocks.sphaleriteOre) {
			xp = MathHelper.getInt(rand, 0, 2);
		} else if (this == GTBlocks.iridiumOre) {
			xp = MathHelper.getInt(rand, 3, 7);
		} else if (this == GTBlocks.rubyOre) {
			xp = MathHelper.getInt(rand, 2, 5);
		} else if (this == GTBlocks.sapphireOre) {
			xp = MathHelper.getInt(rand, 2, 5);
		}
		return xp;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.iridiumOre) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTMod.MODID + ".iridium"));
		}

		else if (this == GTBlocks.rubyOre) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTMod.MODID + ".ruby"));
		}

		else if (this == GTBlocks.sapphireOre) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTMod.MODID + ".sapphire"));
		}

		else if (this == GTBlocks.bauxiteOre) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTMod.MODID + ".bauxite"));
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
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
	public List<IBlockState> getValidStates() {
		return this.blockState.getValidStates();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromMeta(stack.getMetadata());
	}
}