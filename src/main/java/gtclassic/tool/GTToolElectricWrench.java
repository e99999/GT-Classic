package gtclassic.tool;

import java.awt.Color;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTValues;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.classic.item.IElectricTool;
import ic2.api.item.ElectricItem;
import ic2.core.item.tool.ItemToolWrench;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTToolElectricWrench extends ItemToolWrench implements IDamagelessElectricItem, IElectricTool,
		IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	GTMaterial material;
	int tier;

	public GTToolElectricWrench(ToolMaterial tmat) {
		this.material = GTToolMaterial.getGTMaterial(tmat);
		this.tier = material.getLevel() - 1;
		if (this.tier <= 0) {
			this.tier = 1;
		}
		this.setMaxDamage(this.material.getDurability() * (this.tier * 100));
		this.setRegistryName(this.material.getName() + "_electricwrench");
		this.setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + "_electricwrench");
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		if (material.equals(material.Plutonium) || material.equals(material.Thorium)
				|| material.equals(material.Uranium)) {
			return true;
		}
		return super.hasEffect(stack);
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public boolean canTakeDamage(ItemStack stack, int damage) {
		return ElectricItem.manager.canUse(stack, (double) (damage * 50)) && super.canTakeDamage(stack, damage);
	}

	@Override
	public void damageItem(ItemStack stack, int damage, EntityPlayer player) {
		ElectricItem.manager.use(stack, (double) (damage * 50), player);
		super.damageItem(stack, damage, player);
	}

	@Override
	public boolean canProvideEnergy(ItemStack var1) {
		return false;
	}

	@Override
	public double getMaxCharge(ItemStack var1) {
		return (int) (Math.pow(2, this.tier) * 50000);
	}

	@Override
	public int getTier(ItemStack var1) {
		return this.tier;
	}

	@Override
	public double getTransferLimit(ItemStack var1) {
		return (int) (Math.pow(2, this.tier) * 64);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public EnumEnchantmentType getType(ItemStack item) {
		return EnumEnchantmentType.BREAKABLE;
	}

	@Override
	public boolean isExcluded(ItemStack stack, Enchantment ench) {
		return ench == Enchantments.MENDING;
	}

	@Override
	public boolean isSpecialSupported(ItemStack var1, Enchantment var2) {
		return false;
	}

	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (this.getDamage(stack) == this.getMaxDamage()) {
			if (this.tier == 1) {
				ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.get(GTBlocks.batteryLithiumSmall));
				ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1));
				ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getPlate(GTMaterial.Steel, 5));
			}
			if (this.tier == 2) {
				ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.get(GTBlocks.batteryLithiumMed));
				ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1));
				ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getPlate(GTMaterial.Titanium, 5));
			}
			// TODO add tier 3 when parts are available
		}
		stack.damageItem(1, player);
		return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			ItemStack empty = new ItemStack(this, 1, 0);
			ItemStack full = new ItemStack(this, 1, 0);
			ElectricItem.manager.discharge(empty, 2.147483647E9D, Integer.MAX_VALUE, true, false, false);
			ElectricItem.manager.charge(full, 2.147483647E9D, Integer.MAX_VALUE, true, false);
			items.add(empty);
			items.add(full);
		}
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D - ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canOverrideLossChance(ItemStack stack) {
		return true;
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

	public GTMaterial getMaterial() {
		return this.material;
	}

}
