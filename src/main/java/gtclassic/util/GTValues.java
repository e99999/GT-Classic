package gtclassic.util;

import java.awt.Color;

import com.google.common.collect.ImmutableMap;

import gtclassic.GTMod;
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

	/*
	 * This is a map - string to color. its a temporary
	 * placeholder until I refactor my materials completely.
	 * current its used in material classes to sync the input string
	 * in the constructor to the correct color until it can be pulled
	 * from a material entry itself.
	 */
	private static final ImmutableMap<String, Color> MAT_COLOR = ImmutableMap.<String, Color>builder()
			.put("Almandine", new Color(255, 0, 0))
			.put("Aluminium", new Color(128, 200, 240))
			.put("Andradite", new Color(150, 120, 0))
			.put("Ashes", new Color(150, 150, 150))
			.put("Basalt", new Color(30, 20, 20))
			.put("Bauxite", new Color(200, 100, 0))
			.put("Brass", new Color(255, 180, 0))
			.put("Bronze", new Color(255, 128, 0))
			.put("Calcite", new Color(250, 230, 220))
			.put("Charcoal", new Color(100, 70, 70))
			.put("Chrome", new Color(245, 206, 227))
			.put("Cinnabar", new Color(150, 0, 0))
			.put("Clay", new Color(200, 200, 220))
			.put("Coal", new Color(70, 70, 70))
			.put("Copper", new Color(255, 100, 0))
			.put("DarkAshes", new Color(50, 50, 50))
			.put("Diamond", new Color(100, 240, 245))
			.put("Electrum", new Color(255, 255, 100))
			.put("Emerald", new Color(80, 255, 80))
			.put("EnderEye", new Color(160, 250, 230))
			.put("Enderpearl", new Color(108, 220, 200))
			.put("Endstone", new Color(255, 255, 255))
			.put("Flint", new Color(0, 32, 64))
			.put("Galena", new Color(100, 60, 100))
			.put("GalvanizedSteel", new Color(250, 240, 240))
			.put("RedGarnet", new Color(200, 80, 80))
			.put("YellowGarnet", new Color(200, 200, 80))
			.put("Glowstone", new Color(255, 255, 0))
			.put("Gold", new Color(255, 255, 30))
			.put("Grossular", new Color(200, 100, 0))
			.put("Gunpowder", new Color(128, 128, 128))
			.put("Iridium", new Color(255, 255, 255))
			.put("Iron", new Color(184, 184, 184))
			.put("Invar", new Color(180, 180, 120))
			.put("Lazurite", new Color(100, 120, 255))
			.put("Lead", new Color(140, 100, 140))
			.put("Magnalium", new Color(200, 190, 255))
			.put("Magnesium", new Color(255, 200, 200))
			.put("Manganese", new Color(250, 250, 250))
			.put("Marble", new Color(200, 200, 200))
			.put("Netherrack", new Color(200, 0, 0))
			.put("Nickel", new Color(200, 200, 250))
			.put("Obsidian", new Color(80, 50, 100))
			.put("Olivine", new Color(150, 255, 150))
			.put("Osmium", new Color(50, 50, 255))
			.put("Phosphor", new Color(255, 255, 0))
			.put("Plastic", new Color(200, 200, 200))
			.put("Platinum", new Color(215, 212, 137))
			.put("Plutonium", new Color(240, 50, 50))
			.put("Pyrite", new Color(150, 120, 40))
			.put("Pyrope", new Color(120, 50, 100))
			.put("RedRock", new Color(255, 80, 50))
			.put("Redstone", new Color(200, 0, 0))
			.put("RefinedIron", new Color(185, 215, 217))
			.put("Ruby", new Color(255, 100, 100))
			.put("Saltpeter", new Color(230, 230, 230))
			.put("Sapphire", new Color(100, 100, 200))
			.put("GreenSapphire", new Color(100, 200, 130))
			.put("Silicon", new Color(60, 60, 80))
			.put("Silver", new Color(220, 220, 255))
			.put("Sodalite", new Color(20, 20, 255))
			.put("Spessartine", new Color(255, 100, 100))
			.put("Sphalerite", new Color(200, 140, 40))
			.put("Steel", new Color(128, 128, 128))
			.put("Sulfur", new Color(200, 200, 0))
			.put("Thorium", new Color(0, 30, 0))
			.put("Tin", new Color(220, 220, 220))
			.put("Titanium", new Color(170, 143, 222))
			.put("Tungsten", new Color(50, 50, 50))
			.put("TungstenSteel", new Color(100, 100, 160))
			.put("Uranium", new Color(50, 240, 50))
			.put("Uvarovite", new Color(180, 255, 180))
			.put("Wood", new Color(100, 50, 0))
			.put("Zinc", new Color(250, 240, 240))
			.build();

	public static Color getColor(String name) {
		return MAT_COLOR.get(name);
	}

	public static Color getToolColor(int tier) {
		if (tier == 1) {
			return getColor("Iron");
		}
		if (tier == 2) {
			return getColor("Titanium");
		}
		if (tier == 3) {
			return getColor("TungstenSteel");
		}
		if (tier == 4) {
			return getColor("Chrome");
		}
		if (tier == 5) {
			return getColor("Iridium");
		}
		if (tier == 6) {
			return getColor("Osmium");
		}
		if (tier == 7) {
			return getColor("Iridium");
		}
		if (tier == 8) {
			return getColor("Iridium");
		} else {
			return Color.white;
		}
	}

	public static Color getMachineColor(int tier) {
		if (tier == 1) {
			return getColor("Iron");
		}
		if (tier == 2) {
			return getColor("Aluminium");
		}
		if (tier == 3) {
			return getColor("Platinum");
		}
		if (tier == 4) {
			return getColor("Titanium");
		}
		if (tier == 5) {
			return getColor("TungstenSteel");
		}
		if (tier == 6) {
			return getColor("Chrome");
		}
		if (tier == 7) {
			return getColor("Iridium");
		}
		if (tier == 8) {
			return getColor("Osmium");
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
