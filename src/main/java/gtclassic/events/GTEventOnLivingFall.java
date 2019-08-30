package gtclassic.events;

import gtclassic.item.GTItemSpringBoots;
import gtclassic.util.GTValues;
import ic2.core.IC2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
						IC2.audioManager.playOnce(player, GTValues.spring);
					}
				}
			}
		}
	}
}
