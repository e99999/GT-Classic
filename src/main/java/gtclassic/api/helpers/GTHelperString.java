package gtclassic.api.helpers;

import java.util.List;

import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTHelperString {

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
}
