package gtclassic.common.event;

import gtclassic.common.GTItems;
import gtclassic.common.item.GTItemUltimateArmor;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class GTEventOnPlayerTick {

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		if (IC2.platform.isSimulating() && event.phase == TickEvent.Phase.END) {
			EntityPlayer player = event.player;
			ItemStack chestplate = player.inventory.armorInventory.get(2);
			boolean hasUltimateArmor = chestplate.getItem() == GTItems.ultimateArmor;
			// Track whether my armor granted flight
			if (hasUltimateArmor && ElectricItem.manager.canUse(chestplate, 1000)) {
				if (!player.capabilities.allowFlying) {
					player.capabilities.allowFlying = true; // Grant flight
					player.sendPlayerAbilities();
				}
				player.getEntityData().setBoolean(GTItemUltimateArmor.ULTIMATE_ARMOR_STRING, true); // Store flag
			} else {
				boolean hadUltimateArmor = player.getEntityData().getBoolean(GTItemUltimateArmor.ULTIMATE_ARMOR_STRING);
				if (hadUltimateArmor) { // Only remove if my armor granted flight
					if (!player.isCreative() && !player.isSpectator()) { // Don't remove for creative players or spectators
						player.capabilities.allowFlying = false;
						player.capabilities.isFlying = false; // Stop flying
						player.sendPlayerAbilities();
					}
					player.getEntityData().setBoolean(GTItemUltimateArmor.ULTIMATE_ARMOR_STRING, false); // Reset flag
				}
			}
		}
	}
}
