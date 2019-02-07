package gtclassic.tool;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import gtclassic.util.GTValues;
import ic2.api.classic.item.IMiningDrill;
import ic2.api.item.ElectricItem;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTToolRockCutter extends ItemElectricTool
		implements IMiningDrill, IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	GTMaterial material;

	public GTToolRockCutter(GTMaterial material, int charge, int transfer, int tier) {
		super(0.0F, -3.0F, ToolMaterial.DIAMOND);
		this.material = material;
		this.tier = tier;
		this.attackDamage = 1.0F;
		this.maxCharge = charge;
		this.transferLimit = transfer;
		this.setRegistryName(getRockCutterName());
		this.setUnlocalizedName(GTMod.MODID + "." + getRockCutterName());
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	public String getRockCutterName() {
		return ("rockcutter_" + this.material.getName() + "_" + GTValues.getTierString(this.tier)).toLowerCase();
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Tier: " + GTValues.getTierString(this.tier)));
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Material: " + this.material.getName()));
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Size: " + String.valueOf(this.maxCharge)));
		tooltip.add(
				GTValues.getTierTextColor(this.tier) + I18n.format("Transfer: " + String.valueOf(this.transferLimit)));
		tooltip.add(
				GTValues.getTierTextColor(this.tier) + I18n.format("Cost: " + String.valueOf(this.transferLimit * 10)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return Items.DIAMOND_PICKAXE.canHarvestBlock(state);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
		return 3;
	}

	@Override
	public int getEnergyCost(ItemStack stack) {
		return (this.transferLimit * 10);
	}

	@Override
	public float getMiningSpeed(ItemStack stack) {
		return 1.0F;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (canMine(stack)) {
			return 1.0F;
		} else {
			return 0.0F;
		}
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe");
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public EnumEnchantmentType getType(ItemStack itemStack) {
		return null;
	}

	@Override
	public boolean isBasicDrill(ItemStack d) {
		return false;
	}

	@Override
	public int getExtraSpeed(ItemStack d) {
		return 0;
	}

	@Override
	public int getExtraEnergyCost(ItemStack d) {
		return 0;
	}

	@Override
	public void useDrill(ItemStack d) {
		ElectricItem.manager.use(d, this.getEnergyCost(d), (EntityLivingBase) null);
	}

	@Override
	public boolean canMine(ItemStack d) {
		return ElectricItem.manager.canUse(d, this.getEnergyCost(d));
	}

	@Override
	public boolean canMineBlock(ItemStack stack, IBlockState state, IBlockAccess access, BlockPos pos) {
		return ForgeHooks.canToolHarvestBlock(access, pos, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[36];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return GTValues.getToolColor(this.tier);
		} else {
			return this.material.getColor();
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		return true;
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int var1, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[36 + var1];
	}
}
