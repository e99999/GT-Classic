package gtclassic.util;

import java.awt.Color;

import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.util.text.TextFormatting;

public class GTValues {

	// index for storing global variables

	// boolean that renders anything labeled as WIP uncraftable
	public static boolean debugMode = true;

	// colors
	public static int white = 16777215;
	public static int grey = 4210752;
	public static int red = 15599112;
	public static int green = 9567352;

	// lang
	public static LocaleComp hesu = new LocaleBlockComp("tile." + GTMod.MODID + ".hesu");
	public static LocaleComp idsu = new LocaleBlockComp("tile." + GTMod.MODID + ".idsu");
	public static LocaleComp lesu = new LocaleBlockComp("tile." + GTMod.MODID + ".lesu");
	public static LocaleComp centrifuge = new LocaleBlockComp("tile." + GTMod.MODID + ".industrialcentrifuge");
	public static LocaleComp fusion = new LocaleBlockComp("tile." + GTMod.MODID + ".fusioncomputer");

	public static LocaleComp smallchest = new LocaleBlockComp("tile." + GTMod.MODID + ".smallchest");
	public static LocaleComp largechest = new LocaleBlockComp("tile." + GTMod.MODID + ".largechest");
	public static LocaleComp digitalchest = new LocaleBlockComp("tile." + GTMod.MODID + ".digitalchest");
	public static LocaleComp bookshelf = new LocaleBlockComp("tile." + GTMod.MODID + ".bookshelf");

	public static LocaleComp centrifugeEU = new LocaleJEIInfoComp("jei.centrifugeu.name");

	public static Color getToolColor(int tier) {
		if (tier == 1) {
			return GTMaterial.Iron.getColor();
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
			return GTMaterial.Iron.getColor();
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

	public static TextFormatting getTierTextColor(int tier) {
		if (tier == 1) {
			return TextFormatting.GRAY;
		}
		if (tier == 2) {
			return TextFormatting.DARK_AQUA;
		}
		if (tier == 3) {
			return TextFormatting.YELLOW;
		}
		if (tier == 4) {
			return TextFormatting.LIGHT_PURPLE;
		}
		if (tier == 5) {
			return TextFormatting.DARK_BLUE;
		}
		if (tier == 6) {
			return TextFormatting.GREEN;
		}
		if (tier == 7) {
			return TextFormatting.WHITE;
		}
		if (tier == 8) {
			return TextFormatting.BLUE;
		} else {
			return TextFormatting.WHITE;
		}
	}

}
