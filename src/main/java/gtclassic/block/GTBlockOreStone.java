package gtclassic.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.GTOreRegistry;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockOreStone extends Block implements ITexturedBlock, ILocaleBlock {

	String name;
	int id;
	int level;
	float hardness;
	LocaleComp comp;
	GTOreRegistry ore;

	public GTBlockOreStone(GTOreRegistry ore, int id, int level, float hardness) {
		super(Material.ROCK);
		this.ore = ore;
		this.id = id;
		this.name = ore.getMaterial().getDisplayName();
		this.level = level;
		this.hardness = hardness;
		this.comp = Ic2Lang.nullKey;
		setRegistryName(this.name.toLowerCase() + "_ore");
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase() + "_ore");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(this.hardness);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", this.level);
		if (this.ore.equals(GTOreRegistry.VIBRANIUM)) {
			setBlockUnbreakable();
		}
		setSoundType(SoundType.STONE);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Spawns Between Y Level " + ore.getMinY() + " - " + ore.getMaxY()));
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

	public GTOreRegistry getOreEntry() {
		return this.ore;
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		GTMaterial mat = this.ore.getMaterial();
		if (mat.equals(GTMaterial.Iridium)) {
			drops.add(GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1));
			return drops;
		}
		if (mat.equals(GTMaterial.Olivine)) {
			drops.add(GTMaterialGen.getGem(GTMaterial.Olivine, 1));
			return drops;
		}
		if (mat.equals(GTMaterial.Sapphire)) {
			drops.add(GTMaterialGen.getGem(GTMaterial.Sapphire, 1));
			return drops;
		}
		if (mat.equals(GTMaterial.Ruby)) {
			drops.add(GTMaterialGen.getGem(GTMaterial.Ruby, 1));
			return drops;
		}
		if (mat.equals(GTMaterial.Coal)) {
			drops.add(GTMaterialGen.get(Items.COAL, 2));
			return drops;
		} else {
			drops.add(GTMaterialGen.get(this));
			return drops;
		}
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		GTMaterial mat = this.ore.getMaterial();
		int xp = 0;
		if (mat.equals(GTMaterial.Iridium)) {
			xp = MathHelper.getInt(rand, 3, 7);
		}
		if (mat.equals(GTMaterial.Olivine) || mat.equals(GTMaterial.Sapphire) || mat.equals(GTMaterial.Ruby)
				|| mat.equals(GTMaterial.Coal)) {
			xp = MathHelper.getInt(rand, 2, 5);
		}
		return xp;
	}
}