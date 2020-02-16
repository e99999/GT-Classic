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
}
