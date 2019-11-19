package gtclassic.common.block;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockOre extends GTBlockBase{

	String name;
	int id;

	public GTBlockOre(String name, int id, float hardness, int level) {
		super(Material.ROCK);
		this.name = name;
		this.id = id;
		setRegistryName(this.name + "_ore");
		setUnlocalizedName(GTMod.MODID + ".ore" + this.name);
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(hardness);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", level);
		setSoundType(SoundType.STONE);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip")));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[this.id];
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		int bonus = fortune > 0 ? RANDOM.nextInt(fortune + 1) : 0;
		if (this.equals(GTBlocks.oreIridium)) {
			drops.add(GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1 + bonus));
		}
		if (this.equals(GTBlocks.oreRuby)) {
			drops.add(GTMaterialGen.getGem(GTMaterial.Ruby, 1 + bonus));
		}
		if (this.equals(GTBlocks.oreSapphire)) {
			drops.add(GTMaterialGen.getGem(GTMaterial.Sapphire, 1 + bonus));
		}
		if (this.equals(GTBlocks.oreBauxite)) {
			drops.add(GTMaterialGen.get(GTBlocks.oreBauxite));
		}
		if (this.equals(GTBlocks.oreSheldonite)) {
			drops.add(GTMaterialGen.get(GTBlocks.oreSheldonite));
		}
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		if (this.equals(GTBlocks.oreIridium)) {
			return MathHelper.getInt(RANDOM, 3, 7);
		}
		if (this.equals(GTBlocks.oreRuby) || this.equals(GTBlocks.oreSapphire)) {
			return MathHelper.getInt(RANDOM, 2, 5);
		}
		return 0;
	}
}
