package gtclassic.api.helpers;

import java.util.List;

import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!! Just a few Utility Functions I use.
 */
public class GTUtility {

	/** ### Some Simple String Stuff **/
	private static final String PAINTED_TRUE = "tooltip.gtclassic.paintedtrue";
	private static final String PAINTED_FALSE = "tooltip.gtclassic.paintedfalse";
	public static final String BEACONBASE = "tooltip.gtclassic.beaconbase";
	public static final String NOMOBSPAWN = "tooltip.gtclassic.nomobs";
	private static final String[] TIERS = { "N/A", "LV", "MV", "HV", "EV", "IV", "LuV", "ZPM", "UV", "MAX" };

	public static void tooltipPaintable(ItemStack stack, List<String> tooltip) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("color")) {
			tooltip.add(I18n.format(PAINTED_TRUE));
		} else {
			tooltip.add(I18n.format(PAINTED_FALSE));
		}
	}

	public static String getTierString(int tier) {
		if (tier < 0 || tier > 9) {
			return TIERS[0];
		}
		return TIERS[tier];
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
