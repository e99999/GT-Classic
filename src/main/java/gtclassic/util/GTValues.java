package gtclassic.util;

import java.awt.Color;
import java.util.HashMap; 
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import gtclassic.GTMod;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;

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
	 * This is a temporary home for material color references
	 */
	public static Color Almandine = new Color(255, 0, 0);
	public static Color Aluminium = new Color(128, 200, 240);
	public static Color Andradite = new Color(150, 120, 0);
	public static Color Ashes = new Color(150, 150, 150);
	public static Color Basalt = new Color(30, 20, 20);
	public static Color Bauxite = new Color(200, 100, 0);
	public static Color Brass = new Color(255, 180, 0);
	public static Color Bronze = new Color(255, 128, 0);
	public static Color Calcite = new Color(250, 230, 220);
	public static Color Chrome = new Color(255, 230, 230);
	public static Color Cinnabar = new Color(150, 0, 0);
	public static Color Copper = new Color(255, 100, 0);
	public static Color DarkAshes = new Color(50, 50, 50);
	public static Color Diamond = new Color(200, 255, 255);
	public static Color Electrum = new Color(255, 255, 100);
	public static Color Emerald = new Color(80, 255, 80);
	public static Color EnderEye = new Color(160, 250, 230);
	public static Color Enderpearl = new Color(108, 220, 200);
	public static Color Endstone = new Color(255, 255, 255);
	public static Color Flint = new Color(0, 32, 64);
	public static Color Galena = new Color(100, 60, 100);
	public static Color GarnetRed = new Color(200, 80, 80);
	public static Color GarnetYellow = new Color(200, 80, 127);
	public static Color Grossular = new Color(200, 100, 0);
	public static Color Iridium = new Color(200, 200, 200);
	public static Color Iron = new Color(240, 240, 245);
	public static Color Invar = new Color(180, 180, 120);
	public static Color Lazurite = new Color(100, 120, 255);
	public static Color Lead = new Color(140, 100, 140);
	public static Color Magnesium = new Color(255, 200, 200);
	public static Color Manganese = new Color(250, 250, 250);
	public static Color Marble = new Color(200, 200, 200);
	public static Color Netherrack = new Color(200, 0, 0);
	public static Color Nickel = new Color(200, 200, 250);
	public static Color Olivine = new Color(150, 255, 150);
	public static Color Osmium = new Color(50, 50, 255);//
	public static Color Phosphor = new Color(255, 255, 0);
	public static Color Plastic = new Color(200, 200, 200);
	public static Color Platinum = new Color(215, 212, 137);
	public static Color Plutonium = new Color(240, 50, 50);
	public static Color Pyrite = new Color(150, 120, 40);
	public static Color Pyrope = new Color(120, 50, 100);
	public static Color RedRock = new Color(255, 80, 50);
	public static Color Ruby = new Color(255, 100, 100);
	public static Color Saltpeter = new Color(230, 230, 230);
	public static Color Sapphire = new Color(100, 100, 200);
	public static Color SapphireGreen = new Color(100, 200, 130);
	public static Color Silver = new Color(220, 220, 255);
	public static Color Sodalite = new Color(20, 20, 255);
	public static Color Spessartine = new Color(255, 100, 100);
	public static Color Sphalerite = new Color(255, 255, 255);
	public static Color Steel = new Color(128, 128, 128);
	public static Color Sulfur = new Color(200, 200, 0);
	public static Color Thorium = new Color(0, 30, 0);
	public static Color Tin = new Color(220, 220, 220);
	public static Color Titanium = new Color(170, 143, 222);
	public static Color Tungsten = new Color(50, 50, 50);
	public static Color Uranium = new Color(50, 240, 50);
	public static Color Uvarovite = new Color(180, 255, 180);
	public static Color Wood = new Color(100, 50, 0);
	public static Color Zinc = new Color(250, 240, 240);

	/*
	 * This is a really awful string to color method its a very
	 * crappy placeholder until I refactor my materials completely.
	 * current its used in material classes to sync the input string
	 * in the constructor to the correct color until it can be pulled 
	 * from a material entry itself.
	 */
	public static Color getColorByString(String name) {
		if (name.equalsIgnoreCase("Almandine")) {return new Color(255, 0, 0);}
		if (name.equalsIgnoreCase("Aluminium")) {return new Color(128, 200, 240);}
		if (name.equalsIgnoreCase("Andradite")) {return new Color(150, 120, 0);}
		if (name.equalsIgnoreCase("Ashes")) {return new Color(150, 150, 150);}
		if (name.equalsIgnoreCase("Basalt")) {return new Color(30, 20, 20);}
		if (name.equalsIgnoreCase("Bauxite")) {return new Color(200, 100, 0);}
		if (name.equalsIgnoreCase("Brass")) {return new Color(255, 180, 0);}
		if (name.equalsIgnoreCase("Bronze")) {return new Color(255, 128, 0);}
		if (name.equalsIgnoreCase("Calcite")) {return new Color(250, 230, 220);}
		if (name.equalsIgnoreCase("Chrome")) {return new Color(255, 230, 230);}
		if (name.equalsIgnoreCase("Cinnabar")) {return new Color(150, 0, 0);}
		if (name.equalsIgnoreCase("Copper")) {return new Color(255, 100, 0);}
		if (name.equalsIgnoreCase("DarkAshes")) {return new Color(50, 50, 50);}
		if (name.equalsIgnoreCase("Diamond")) {return new Color(200, 255, 255);}
		if (name.equalsIgnoreCase("Electrum")) {return new Color(255, 255, 100);}
		if (name.equalsIgnoreCase("Emerald")) {return new Color(80, 255, 80);}
		if (name.equalsIgnoreCase("EnderEye")) {return new Color(160, 250, 230);}
		if (name.equalsIgnoreCase("Enderpearl")) {return new Color(108, 220, 200);}
		if (name.equalsIgnoreCase("Endstone")) {return new Color(255, 255, 255);}
		if (name.equalsIgnoreCase("Flint")) {return new Color(0, 32, 64);}
		if (name.equalsIgnoreCase("Galena")) {return new Color(100, 60, 100);}
		if (name.equalsIgnoreCase("GarnetRed")) {return new Color(200, 80, 80);}
		if (name.equalsIgnoreCase("GarnetYellow")) {return new Color(200, 80, 127);}
		if (name.equalsIgnoreCase("Grossular")) {return new Color(200, 100, 0);}
		if (name.equalsIgnoreCase("Iridium")) {return new Color(200, 200, 200);}
		if (name.equalsIgnoreCase("Iron")) {return new Color(240, 240, 245);}
		if (name.equalsIgnoreCase("Invar")) {return new Color(180, 180, 120);}
		if (name.equalsIgnoreCase("Lazurite")) {return new Color(100, 120, 255);}
		if (name.equalsIgnoreCase("Lead")) {return new Color(140, 100, 140);}
		if (name.equalsIgnoreCase("Magnesium")) {return new Color(255, 200, 200);}
		if (name.equalsIgnoreCase("Manganese")) {return new Color(250, 250, 250);}
		if (name.equalsIgnoreCase("Marble")) {return new Color(200, 200, 200);}
		if (name.equalsIgnoreCase("Netherrack")) {return new Color(200, 0, 0);}
		if (name.equalsIgnoreCase("Nickel")) {return new Color(200, 200, 250);}
		if (name.equalsIgnoreCase("Olivine")) {return new Color(150, 255, 150);}
		if (name.equalsIgnoreCase("Osmium")) {return new Color(50, 50, 255);}//
		if (name.equalsIgnoreCase("Phosphor")) {return new Color(255, 255, 0);}
		if (name.equalsIgnoreCase("Plastic")) {return new Color(200, 200, 200);}
		if (name.equalsIgnoreCase("Platinum")) {return new Color(215, 212, 137);}
		if (name.equalsIgnoreCase("Plutonium")) {return new Color(240, 50, 50);}
		if (name.equalsIgnoreCase("Pyrite")) {return new Color(150, 120, 40);}
		if (name.equalsIgnoreCase("Pyrope")) {return new Color(120, 50, 100);}
		if (name.equalsIgnoreCase("RedRock")) {return new Color(255, 80, 50);}
		if (name.equalsIgnoreCase("Ruby")) {return new Color(255, 100, 100);}
		if (name.equalsIgnoreCase("Saltpeter")) {return new Color(230, 230, 230);}
		if (name.equalsIgnoreCase("Sapphire")) {return new Color(100, 100, 200);}
		if (name.equalsIgnoreCase("SapphireGreen")) {return new Color(100, 200, 130);}
		if (name.equalsIgnoreCase("Silver")) {return new Color(220, 220, 255);}
		if (name.equalsIgnoreCase("Sodalite")) {return new Color(20, 20, 255);}
		if (name.equalsIgnoreCase("Spessartine")) {return new Color(255, 100, 100);}
		if (name.equalsIgnoreCase("Sphalerite")) {return new Color(255, 255, 255);}
		if (name.equalsIgnoreCase("Steel")) {return new Color(128, 128, 128);}
		if (name.equalsIgnoreCase("Sulfur")) {return new Color(200, 200, 0);}
		if (name.equalsIgnoreCase("Thorium")) {return new Color(0, 30, 0);}
		if (name.equalsIgnoreCase("Tin")) {return new Color(220, 220, 220);}
		if (name.equalsIgnoreCase("Titanium")) {return new Color(170, 143, 222);}
		if (name.equalsIgnoreCase("Tungsten")) {return new Color(50, 50, 50);}
		if (name.equalsIgnoreCase("Uranium")) {return new Color(50, 240, 50);}
		if (name.equalsIgnoreCase("Uvarovite")) {return new Color(180, 255, 180);}
		if (name.equalsIgnoreCase("Wood")) {return new Color(100, 50, 0);}
		if (name.equalsIgnoreCase("Zinc")) {return new Color(250, 240, 240);}
		else {return Color.WHITE;}
	}

	private static final ImmutableMap<String, Color> MAT_COLOR = ImmutableMap.<String, Color>builder()
			.put("Almandine",new Color(255, 0, 0))
			.put("Aluminium",new Color(128, 200, 240))
			.put("Andradite",new Color(150, 120, 0))
			.put("Ashes",new Color(150, 150, 150))
			.put("Basalt",new Color(30, 20, 20))
			.put("Bauxite",new Color(200, 100, 0))
			.put("Brass",new Color(255, 180, 0))
			.put("Bronze",new Color(255, 128, 0))
			.put("Calcite",new Color(250, 230, 220))
			.put("Chrome",new Color(245, 206, 227))
			.put("Cinnabar",new Color(150, 0, 0))
			.put("Copper",new Color(255, 100, 0))
			.put("DarkAshes",new Color(50, 50, 50))
			.put("Diamond",new Color(200, 255, 255))
			.put("Electrum",new Color(255, 255, 100))
			.put("Emerald",new Color(80, 255, 80))
			.put("EnderEye",new Color(160, 250, 230))
			.put("Enderpearl",new Color(108, 220, 200))
			.put("Endstone",new Color(255, 255, 255))
			.put("Flint",new Color(0, 32, 64))
			.put("Galena",new Color(100, 60, 100))
			.put("GarnetRed",new Color(200, 80, 80))
			.put("GarnetYellow",new Color(200, 80, 127))
			.put("Gold", new Color(255, 255, 30))
			.put("Grossular",new Color(200, 100, 0))
			.put("Iridium",new Color(200, 200, 200))
			.put("Iron",new Color(240, 240, 245))
			.put("Invar",new Color(180, 180, 120))
			.put("Lazurite",new Color(100, 120, 255))
			.put("Lead",new Color(140, 100, 140))
			.put("Magnalium", new Color(200, 190, 255))
			.put("Magnesium",new Color(255, 200, 200))
			.put("Manganese",new Color(250, 250, 250))
			.put("Marble",new Color(200, 200, 200))
			.put("Netherrack",new Color(200, 0, 0))
			.put("Nickel",new Color(200, 200, 250))
			.put("Olivine",new Color(150, 255, 150))
			.put("Osmium",new Color(50, 50, 255))//
			.put("Phosphor",new Color(255, 255, 0))
			.put("Plastic",new Color(200, 200, 200))
			.put("Platinum",new Color(215, 212, 137))
			.put("Plutonium",new Color(240, 50, 50))
			.put("Pyrite",new Color(150, 120, 40))
			.put("Pyrope",new Color(120, 50, 100))
			.put("RedRock",new Color(255, 80, 50))
			.put("RefinedIron", new Color(180, 230, 220))
			.put("Ruby",new Color(255, 100, 100))
			.put("Saltpeter",new Color(230, 230, 230))
			.put("Sapphire",new Color(100, 100, 200))
			.put("SapphireGreen",new Color(100, 200, 130))
			.put("Silicon", new Color(60, 60, 80))
			.put("Silver",new Color(220, 220, 255))
			.put("Sodalite",new Color(20, 20, 255))
			.put("Spessartine",new Color(255, 100, 100))
			.put("Sphalerite",new Color(255, 255, 255))
			.put("Steel",new Color(128, 128, 128))
			.put("Sulfur",new Color(200, 200, 0))
			.put("Thorium",new Color(0, 30, 0))
			.put("Tin",new Color(220, 220, 220))
			.put("Titanium",new Color(170, 143, 222))
			.put("Tungsten",new Color(50, 50, 50))
			.put("TungstenSteel", new Color(100, 100, 160))
			.put("Uranium",new Color(50, 240, 50))
			.put("Uvarovite",new Color(180, 255, 180))
			.put("Wood",new Color(100, 50, 0))
			.put("Zinc",new Color(250, 240, 240))
            .build();
            
	public static Color getColor(String name) {
		try {
			return MAT_COLOR.get(name);
		} catch (Exception e) {
			GTMod.logger.info("You tried to call the material color:" + name + " it does not exist");
		}
		return Color.WHITE;
	}
	
	
}

	
	
	
	
	

