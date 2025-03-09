package gtclassic.common.item;

import java.awt.Color;
import java.util.List;

import gtclassic.GTMod;
import ic2.api.classic.item.ICropAnalyzer;
import ic2.api.classic.item.IEUReader;
import ic2.api.classic.item.IThermometer;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.entity.IC2Potion;
import ic2.core.item.armor.base.ItemElectricArmorBase;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemUltimateArmor extends ItemElectricArmorBase implements IEUReader, IThermometer, ICropAnalyzer {
	
	public static final String ULTIMATE_ARMOR_STRING = "UltimateArmorFlight";

	public GTItemUltimateArmor() {
		super(40, EntityEquipmentSlot.CHEST, 100000000, 1000, 4);
		this.setRegistryName("ultimate_armor");
		this.setTranslationKey(GTMod.MODID + "." + "ultimate_armor");
		this.setCreativeTab(GTMod.creativeTabGT);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.YELLOW + I18n.format(this.getTranslationKey().replace("item", "tooltip")+"0"));
		tooltip.add(TextFormatting.YELLOW + I18n.format(this.getTranslationKey().replace("item", "tooltip")+"1"));
	}

	@Override
	public boolean isCropAnalyzer(ItemStack arg0) {
		return true;
	}

	@Override
	public boolean isThermometer(ItemStack arg0) {
		return true;
	}

	@Override
	public boolean isEUReader(ItemStack arg0) {
		return true;
	}

	@Override
	public double getDamageAbsorptionRatio() {
		return 1.0;
	}

	@Override
	public int getEnergyPerDamage() {
		return 100;
	}
	
	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}
	
	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public String getTexture() {
		return "gtclassic:textures/models/armor/ultimate";
	}
	
	@Override
	public boolean canProvideEnergy(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[40];
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (!ElectricItem.manager.canUse(stack, 1000)) {
			return;
		}
		if (IC2.platform.isSimulating()) {
			PotionEffect poison, radiation, wither, slowness;
			Potion nightvision = MobEffects.NIGHT_VISION;
			Potion speed = MobEffects.SPEED;
			player.setHealth(player.getMaxHealth());
			player.getFoodStats().setFoodLevel(20); // Max hunger
			player.getFoodStats().setFoodSaturationLevel(20.0f); // Max saturation
			poison = player.getActivePotionEffect(MobEffects.POISON);
			if (player.isInWater()) {
				player.setAir(300);
			}
			if (poison != null) {
				IC2.platform.removePotion((EntityLivingBase) player, MobEffects.POISON);
			}
			radiation = player.getActivePotionEffect((Potion) IC2Potion.radiation);
			if (radiation != null) {
				IC2.platform.removePotion((EntityLivingBase) player, (Potion) IC2Potion.radiation);
			}
			wither = player.getActivePotionEffect(MobEffects.WITHER);
			if (wither != null) {
				IC2.platform.removePotion((EntityLivingBase) player, MobEffects.WITHER);
			}
			slowness = player.getActivePotionEffect(MobEffects.SLOWNESS);
			if (slowness != null) {
				IC2.platform.removePotion((EntityLivingBase) player, MobEffects.SLOWNESS);
			}
			player.addPotionEffect(new PotionEffect(nightvision, 250, 3, false, false));
			if (!player.isPotionActive(speed)) {
				player.addPotionEffect(new PotionEffect(speed, 100, 4, false, false));
			}
			player.extinguish();
		}
		useEnergy(stack, 1000, player);
	}

}
