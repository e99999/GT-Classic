package gtclassic.common.item;

import java.awt.Color;

import gtclassic.GTMod;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import ic2.core.item.armor.base.ItemIC2AdvArmorBase;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemEnergyPack extends ItemIC2AdvArmorBase implements IDamagelessElectricItem {

	String texture;
	int maxEnergy;
	int tier;
	int transferlimit;
	EnumRarity rare;
	int indexitem;

	public GTItemEnergyPack(int index, String tex, int max, String reg, String unl, int lvl, int limit) {
		super(index, EntityEquipmentSlot.CHEST);
		this.indexitem = index;
		this.setMaxDamage(0);
		this.texture = tex;
		this.maxEnergy = max;
		this.setRegistryName(reg);
		this.setTranslationKey(GTMod.MODID + unl);
		this.setCreativeTab(GTMod.creativeTabGT);
		this.tier = lvl; // 1;
		this.transferlimit = limit;
		this.rare = EnumRarity.COMMON;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	public GTItemEnergyPack setRarity(EnumRarity newValue) {
		this.rare = newValue;
		return this;
	}

	@Override
	public String getTexture() {
		return this.texture;
	}

	@Override
	public ItemStack getRepairItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canProvideEnergy(ItemStack stack) {
		return true;
	}

	@Override
	public double getMaxCharge(ItemStack stack) {
		return this.maxEnergy;
	}

	@Override
	public int getTier(ItemStack stack) {
		return this.tier;
	}

	@Override
	public double getTransferLimit(ItemStack stack) {
		return this.transferlimit;
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.RARE;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			ItemStack empty = new ItemStack(this);
			ItemStack full = new ItemStack(this);
			ElectricItem.manager.discharge(empty, 2.147483647E9D, 2147483647, true, false, false);
			ElectricItem.manager.charge(full, 2.147483647E9D, 2147483647, true, false);
			subItems.add(empty);
			subItems.add(full);
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D - ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[this.indexitem];
	}
}