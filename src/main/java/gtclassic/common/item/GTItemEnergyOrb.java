package gtclassic.common.item;

import java.awt.Color;

import gtclassic.GTMod;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemEnergyOrb extends ItemBatteryBase {

	public GTItemEnergyOrb() {
		super(0);
		this.setRightClick();
		this.setRegistryName("energy_orb");
		this.setTranslationKey(GTMod.MODID + ".energyOrb");
		this.maxCharge = 10000000;
		this.transferLimit = 1000;
		this.tier = 4;
		this.provider = true;
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return true;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
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
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[5];
	}
}
