package gtclassic.util;

import java.awt.Color;

import gtclassic.GTMod;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.util.math.AxisAlignedBB;

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
	
	//colors
	
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
	
}
