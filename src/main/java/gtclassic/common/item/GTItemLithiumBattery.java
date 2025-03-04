package gtclassic.common.item;

import java.awt.Color;

import gtclassic.GTMod;
import ic2.api.item.ElectricItem;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemLithiumBattery extends ItemBatteryBase {

	public GTItemLithiumBattery() {
		super(0);
		this.setRightClick();
		this.setRegistryName("lithium_battery");
		this.setTranslationKey(GTMod.MODID + ".lithiumBattery");
		this.maxCharge = 100000;
		this.transferLimit = 128;
		this.tier = 1;
		this.provider = true;
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return this.shouldBeStackable(stack) ? 16 : 1;
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return !this.shouldBeStackable(stack);
	}

	private boolean shouldBeStackable(ItemStack stack) {
		return !stack.hasTagCompound() || ElectricItem.manager.getCharge(stack) == 0.0D;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return this.shouldBeStackable(stack) ? false : super.showDurabilityBar(stack);
	}

	@Override
	public boolean wantsToPlay(ItemStack stack) {
		return true;
	}

	@Override
	public ResourceLocation createSound(ItemStack stack) {
		return Ic2Sounds.batteryUse;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(ItemStack item) {
		int meta = item.getItemDamage();
		if (meta == 0) {
			return Ic2Icons.getTextures(GTMod.MODID + "_items")[25];
		} else {
			return Ic2Icons.getTextures(GTMod.MODID + "_items")[24];
		}
	}
}