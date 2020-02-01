package gtclassic.api.helpers;

public class GTValues {

	/** Static references to mod ids **/
	public static final String MOD_ID_BUILDCRAFT = "buildcraftbuilders", MOD_ID_FORESTRY = "forestry",
			MOD_ID_HARVESTCRAFT = "harvestcraft", MOD_ID_THERMAL = "thermalfoundation",
			MOD_ID_IE = "immersiveengineering", MOD_ID_IC2_EXTRAS = "ic2c_extras", MOD_ID_BAUBLES = "baubles",
			MOD_ID_ENDERIO = "enderio", MOD_ID_TFOREST = "twilightforest", MOD_ID_GTCX = "gtc_expansion";
	/** Below is some tooltip stuff **/
	public static final String TOOLTIP_NOMOBS = "tooltip.gtclassic.nomobs",
			TOOLTIP_BEACON = "tooltip.gtclassic.beaconbase";
	private static final String[] TIERS = { "N/A", "LV", "MV", "HV", "EV", "IV", "LuV", "ZPM", "UV", "MAX" };

	/** Get the name of a tier via its int value **/
	public static String getTierString(int tier) {
		return (tier < 0 || tier > 9) ? TIERS[0] : TIERS[tier];
	}
}
