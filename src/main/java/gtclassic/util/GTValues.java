package gtclassic.util;

import gtclassic.GTMod;
import ic2.core.IC2;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GTValues {
	/*
	 * This is place to hold global values temporarily, it will all be refactored
	 * into better places in time
	 */

	// recipe stuff
	public static FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
	public static FluidStack lava = new FluidStack(FluidRegistry.LAVA, 1000);
	// mod ids
	public static final String BUILDCRAFT = "buildcraftcore";
	public static final String FORESTRY = "forestry";
	public static final String HARVESTCRAFT = "harvestcraft";
	public static final String DRACONIC = "draconicevolution";
	public static final String THERMAL = "thermalfoundation";
	public static final String IMMERSIVE_ENGINEERING = "immersiveengineering";
	public static final String IC2_EXTRAS = "ic2c_extras";
	public static final String BAUBLES = "baubles";
	public static final String ENDERIO = "enderio";
	// sounds
	public static ResourceLocation sonar = new ResourceLocation(GTMod.MODID, "sounds/sonar.ogg");
	public static ResourceLocation cloak = new ResourceLocation(GTMod.MODID, "sounds/cloak.ogg");

	public static String getIC2Ingot() {
		return IC2.getRefinedIron();
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
		} else {
			return "MAX";
		}
	}
}
