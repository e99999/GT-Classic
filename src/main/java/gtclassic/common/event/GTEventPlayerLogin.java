package gtclassic.common.event;

import gtclassic.common.item.GTItemUltimateArmor;
import ic2.core.IC2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class GTEventPlayerLogin {

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		 EntityPlayer player = event.player;
		    if (IC2.platform.isSimulating()) { // Server-side only
		        boolean hadUltimateFlight = player.getEntityData().getBoolean(GTItemUltimateArmor.ULTIMATE_ARMOR_STRING);
		        if (hadUltimateFlight) {
		            // If they had flight granted by the armor, restore it
		            player.capabilities.allowFlying = true;
		            player.capabilities.isFlying = true; // Keep them flying if they were
		            player.sendPlayerAbilities(); // Sync the client with the server
		        }
		    }
		}
}
