package gtclassic.item;

import gtclassic.GTMod;
import gtclassic.util.GTValues;
import ic2.core.IC2;
import ic2.core.item.armor.standart.ItemCompositeArmor;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemSpringBoots extends ItemCompositeArmor {

	public GTItemSpringBoots() {
		super(15, EntityEquipmentSlot.FEET);
		this.setRegistryName("spring_boots");
		this.setUnlocalizedName(GTMod.MODID + "." + "spring_boots");
		this.setCreativeTab(GTMod.creativeTabGT);
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
		if (player.onGround) {
			if (player.isSprinting()) {
				if (player instanceof EntityPlayer) {
					(player).jump();
				} else {
					player.setJumping(true);
				}
				IC2.audioManager.playOnce(player, GTValues.spring);
				// Play sounds here
				// world.playSound(null, player.getPosition().getX(),
				// player.getPosition().getY(), player.getPosition().getZ(),
				// SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F +
				// world.rand.nextFloat());
				stack.damageItem(1, player);
			}
		}
	}
}
