package gtclassic.common.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import gtclassic.GTMod;
import ic2.api.item.ElectricItem;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemTeslaStaff extends ItemElectricTool implements IStaticTexturedItem {

	public GTItemTeslaStaff() {
		super(0.0F, 2.0F, ToolMaterial.DIAMOND);
		this.attackDamage = 1.0F;
		this.maxCharge = 10000000;
		this.transferLimit = 2048;
		this.operationEnergyCost = 2000000;
		this.tier = 4;
		this.setRegistryName("tesla_staff");
		this.setTranslationKey(GTMod.MODID + ".tesla_staff");
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.RARE;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[15];
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.DARK_AQUA + I18n.format(this.getTranslationKey().replace("item", "tooltip")));
	}

	@Override
	public EnumEnchantmentType getType(ItemStack item) {
		return EnumEnchantmentType.WEAPON;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!(attacker instanceof EntityPlayer)) {
			return true;
		} else {
			if (ElectricItem.manager.canUse(stack, this.operationEnergyCost)) {
				attacker.world.addWeatherEffect(new EntityLightningBolt(attacker.world, target.lastTickPosX, target.lastTickPosY, target.lastTickPosZ, false));
				ElectricItem.manager.use(stack, this.operationEnergyCost, attacker);
			} else {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 1.0F);
			}
			return false;
		}
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> map = HashMultimap.create();
		if (slot == EntityEquipmentSlot.MAINHAND) {
			if (ElectricItem.manager.canUse(stack, this.operationEnergyCost)) {
				map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 512.0D, 0));
			} else {
				map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 1.0D, 0));
			}
		}
		return map;
	}

	@Override
	public boolean isSpecialSupported(ItemStack paramItemStack, Enchantment paramEnchantment) {
		return false;
	}

	@Override
	public boolean isExcluded(ItemStack paramItemStack, Enchantment paramEnchantment) {
		return false;
	}
}
