package gtclassic.api.helpers;

public class GTHelperString {

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
		} else {
			return "MAX";
		}
	}
}
