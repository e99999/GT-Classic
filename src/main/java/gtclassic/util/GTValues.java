package gtclassic.util;

import java.awt.Color;

import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GTValues {

	/*
	 * This is place to hold global values temporarily, it will all be refactored
	 * into better places in time
	 */

	// boolean that renders anything labeled as WIP uncraftable
	public static boolean debugMode = true;

	// colors
	public static int white = 16777215;
	public static int grey = 4210752;
	public static int red = 15599112;
	public static int green = 9567352;

	// lang
	public static LocaleComp besu = new LocaleBlockComp("tile." + GTMod.MODID + ".besu");
	public static LocaleComp qesu = new LocaleBlockComp("tile." + GTMod.MODID + ".qesu");
	public static LocaleComp mesu = new LocaleBlockComp("tile." + GTMod.MODID + ".mesu");
	public static LocaleComp centrifuge = new LocaleBlockComp("tile." + GTMod.MODID + ".industrialcentrifuge");
	public static LocaleComp fusion = new LocaleBlockComp("tile." + GTMod.MODID + ".fusioncomputer");

	public static LocaleComp smallchest = new LocaleBlockComp("tile." + GTMod.MODID + ".smallchest");
	public static LocaleComp largechest = new LocaleBlockComp("tile." + GTMod.MODID + ".largechest");
	public static LocaleComp digitalchest = new LocaleBlockComp("tile." + GTMod.MODID + ".digitalchest");
	public static LocaleComp bookshelf = new LocaleBlockComp("tile." + GTMod.MODID + ".bookshelf");

	public static LocaleComp centrifugeEU = new LocaleJEIInfoComp("jei.centrifugeu.name");

	// recipe stuff
	public static FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
	public static FluidStack lava = new FluidStack(FluidRegistry.LAVA, 1000);

	public static IRecipeInput ingotElectric = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("ingotAluminium"), new RecipeInputOreDict(getIC2Ingot()),
					new RecipeInputOreDict("ingotSilver") });

	public static IRecipeInput plateElectric = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("plateAluminium"), new RecipeInputOreDict(getIC2Plate()),
					new RecipeInputOreDict("plateSilver") });

	public static String getIC2Ingot() {
		return IC2.getRefinedIron();
	}

	public static String getIC2Plate() {
		if (IC2.getRefinedIron().equals("ingotSteel")) {
			return "plateSteel";
		} else {
			return "plateRefinedIron";
		}
	}

	public static Color getToolColor(int tier) {
		if (tier == 1) {
			return GTMaterial.Steel.getColor();
		}
		if (tier == 2) {
			return GTMaterial.Titanium.getColor();
		}
		if (tier == 3) {
			return GTMaterial.TungstenSteel.getColor();
		}
		if (tier == 4) {
			return GTMaterial.Chrome.getColor();
		}
		if (tier == 5) {
			return GTMaterial.Iridium.getColor();
		}
		if (tier == 6) {
			return GTMaterial.Osmium.getColor();
		}
		if (tier == 7) {
			return GTMaterial.Osmium.getColor();
		}
		if (tier == 8) {
			return GTMaterial.Osmium.getColor();
		} else {
			return Color.white;
		}
	}

	public static Color getMachineColor(int tier) {
		if (tier == 1) {
			return GTMaterial.Steel.getColor();
		}
		if (tier == 2) {
			return GTMaterial.Aluminium.getColor();
		}
		if (tier == 3) {
			return GTMaterial.Platinum.getColor();
		}
		if (tier == 4) {
			return GTMaterial.Titanium.getColor();
		}
		if (tier == 5) {
			return GTMaterial.Tungsten.getColor();
		}
		if (tier == 6) {
			return GTMaterial.Chrome.getColor();
		}
		if (tier == 7) {
			return GTMaterial.Iridium.getColor();
		}
		if (tier == 8) {
			return GTMaterial.Osmium.getColor();
		} else {
			return Color.white;
		}
	}

	public static String getTierString(int tier) {
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
			return "null";
		}
	}

}
