package gtclassic.api.helpers;

import java.util.List;

import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTHelperString {

	private static final String PAINTED_TRUE = "tooltip.gtclassic.paintedtrue";
	private static final String PAINTED_FALSE = "tooltip.gtclassic.paintedfalse";
	private static final String MONKEYWRENCH = "tooltip.gtclassic.monkeywrench";
	public static final String BEACONBASE = "tooltip.gtclassic.beaconbase";
	public static final String NOMOBSPAWN = "tooltip.gtclassic.nomobs";
	

	public static void tooltipPaintable(ItemStack stack, List<String> tooltip) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("color")) {
			tooltip.add(I18n.format(PAINTED_TRUE));
		} else {
			tooltip.add(I18n.format(PAINTED_FALSE));
		}
	}
	public static void tooltipMonkeyWrench(List<String> tooltip) {
			tooltip.add(I18n.format(MONKEYWRENCH));
	}

	public static String getTierString(int tier) {
		if (tier == 0) {
			return "N/A";
		}
		if (tier == 1) {
			return "LV";
		}
		if (tier == 2) {
			return "MV";
		}
		if (tier == 3) {
			return "HV";
		}
		if (tier == 4) {
			return "EV";
		}
		if (tier == 5) {
			return "IV";
		}
		if (tier == 6) {
			return "LuV";
		}
		if (tier == 7) {
			return "ZPM";
		}
		if (tier == 8) {
			return "UV";
		}
		if (tier == 9) {
			return "MAX";
		} else {
			return "N/A";
		}
	}
}
