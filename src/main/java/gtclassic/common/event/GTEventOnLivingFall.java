package gtclassic.common.event;

import gtclassic.common.GTSounds;
import gtclassic.common.item.GTItemSpringBoots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventOnLivingFall {

	@SubscribeEvent
	public void onEntityFall(LivingFallEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			for (ItemStack armor : player.getEquipmentAndArmor()) {
				if (armor.getItem() instanceof GTItemSpringBoots) {
					float amount = event.getDistance();
					if (amount < 20) {
						event.setResult(Event.Result.DENY);
						event.setCanceled(true);
					} else {
						armor.damageItem(Math.round(amount), player);
						event.setDamageMultiplier(event.getDamageMultiplier() * 0.5F);
						player.jump();
						player.getEntityWorld().playSound(null, player.getPosition(), GTSounds.SPRING, SoundCategory.NEUTRAL, 1.0F, 1.0F
								+ player.getEntityWorld().rand.nextFloat());
					}
				}
			}
		}
	}
}
