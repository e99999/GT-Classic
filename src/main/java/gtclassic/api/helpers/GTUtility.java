package gtclassic.api.helpers;

import ic2.core.item.armor.electric.ItemArmorQuantumSuit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!! Just a few Utility Functions I use.
 */
public class GTUtility {

	/**
	 * ### Player Stuff ###
	 **/
	/**
	 * Checks to see if a player is fully equipped in quantum gear
	 * 
	 * @param entity - usually the player in this case
	 * @return
	 */
	public static boolean hasFullQuantumSuit(EntityLivingBase entity) {
		if (!(entity instanceof EntityPlayer)) {
			return false;
		}
		EntityPlayer player = (EntityPlayer) entity;
		for (int i = 0; i < 4; i++) {
			if (!(player.inventory.armorInventory.get(i).getItem() instanceof ItemArmorQuantumSuit)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ### Data Network Stuff ###
	 **/
	/** Tick rate for INPUT NODES to attempt to move items to the OUTPUT NODES **/
	public static final int DATA_NET_TICK_RATE = 10;
	/**
	 * Tick rate for that a network tries to validate itself doing recurrsive checks
	 **/
	public static final int DATA_NET_SEARCH_RATE = 128;
	/** Tick rate for networks to reset and require revalidation **/
	public static final int DATA_NET_RESET_RATE = 126;
	public static final String DATA_NET_NBT_CHANNEL = "channel";

	/** Enum for data types **/
	public enum DataType {
		ITEM(),
		FLUID(),
		REDSTONE();
	}
}
