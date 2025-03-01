package gtclassic.common.item;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.common.GTSounds;
import ic2.core.item.armor.standart.ItemCompositeArmor;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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

public class GTItemSpringBoots extends ItemCompositeArmor {

	public GTItemSpringBoots() {
		super(15, EntityEquipmentSlot.FEET);
		this.setRegistryName("spring_boots");
		this.setTranslationKey(GTMod.MODID + "." + "spring_boots");
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.ITALIC + I18n.format(this.getTranslationKey().replace("item", "tooltip")));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[32];
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		Potion jump = MobEffects.JUMP_BOOST;
		if (!player.isPotionActive(jump)) {
			player.addPotionEffect(new PotionEffect(jump, 10, 2, false, false));
		}
		if (player.onGround && player.isSprinting()) {
			player.jump();
			player.playSound(GTSounds.SPRING, 1.0F, 1.0F + world.rand.nextFloat());
			if (world.rand.nextInt(2) == 0) {
				stack.damageItem(1, player);
			}
		}
	}
}
