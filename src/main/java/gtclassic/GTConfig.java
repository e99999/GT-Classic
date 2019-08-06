package gtclassic;

import org.apache.logging.log4j.Level;

import gtclassic.proxy.GTProxyCommon;
import net.minecraftforge.common.config.Configuration;

public class GTConfig {

	private static final String CATEGORY_CONFIG = "config";
	private static final String CATEGORY_GENERATION = "generation";
	private static final String CATEGORY_MODCOMPAT = "modcompatability";
	// config
	public static boolean debugMode = false;
	public static boolean addLootItems = true;
	public static boolean ingotsRequireBlastFurnace = true;
	// generation
	public static boolean bauxiteGenerate = true;
	public static int bauxiteSize = 16;
	public static int bauxiteWeight = 4;
	public static boolean iridiumEndGenerate = true;
	public static int iridiumEndSize = 3;
	public static int iridiumEndWeight = 4;
	public static boolean iridiumOverworldGenerate = true;
	public static int iridiumOverworldSize = 3;
	public static int iridiumOverworldWeight = 2;
	public static boolean rubyGenerate = true;
	public static int rubySize = 5;
	public static int rubyWeight = 2;
	public static boolean sapphireGenerate = true;
	public static int sapphireSize = 5;
	public static int sapphireWeight = 2;
	// mod compat options
	public static boolean compatBaubles = true;
	public static boolean compatBuildcraft = true;
	public static boolean compatDraconic = true;
	public static boolean compatEnderIO = true;
	public static boolean compatForestry = true;
	public static boolean compatIc2Extras = true;
	public static boolean compatIE = true;
	public static boolean compatThermal = true;

	public static void readConfig() {
		Configuration cfg = GTProxyCommon.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initGenerationConfig(cfg);
			initCompatConfig(cfg);
		} catch (Exception e1) {
			GTMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_CONFIG, "Configuration");
		debugMode = cfg.getBoolean("debugMode", CATEGORY_CONFIG, debugMode, "Enables debug logger for additional information");
		addLootItems = cfg.getBoolean("addLootItems", CATEGORY_CONFIG, addLootItems, "Adds GregTech items to loot pool");
		ingotsRequireBlastFurnace = cfg.getBoolean("ingotsRequireBlastFurnace", CATEGORY_CONFIG, ingotsRequireBlastFurnace, "Tries to remove vanilla smelting of end game metals from all loaded mods");
	}

	private static void initGenerationConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERATION, "Generation configuration");
		// bauxite overworld ore
		bauxiteGenerate = cfg.getBoolean("bauxiteGenerate", CATEGORY_GENERATION, bauxiteGenerate, "Generate Bauxite ore in plains/forest biomes in the overworld");
		bauxiteSize = cfg.getInt("bauxiteSize", CATEGORY_GENERATION, bauxiteSize, 1, 32, "Max size of Bauxite veins");
		bauxiteWeight = cfg.getInt("bauxiteWeight", CATEGORY_GENERATION, bauxiteWeight, 1, 32, "Chance of Bauxite veins to spawn");
		// iridum end ore
		iridiumEndGenerate = cfg.getBoolean("iridiumEndGenerate", CATEGORY_GENERATION, iridiumEndGenerate, "Generate Iridium ore in the end");
		iridiumEndSize = cfg.getInt("iridiumEndSize", CATEGORY_GENERATION, iridiumEndSize, 1, 32, "Max size of Iridium veins in the end");
		iridiumEndWeight = cfg.getInt("iridiumEndWeight", CATEGORY_GENERATION, iridiumEndWeight, 1, 32, "Chance of Iridium veins in the end");
		// iridium overworld ore
		iridiumOverworldGenerate = cfg.getBoolean("iridiumOverworldGenerate", CATEGORY_GENERATION, iridiumOverworldGenerate, "Generate Iridium ore in the overworld");
		iridiumOverworldSize = cfg.getInt("iridiumOverworldSize", CATEGORY_GENERATION, iridiumOverworldSize, 1, 32, "Max size of Iridium veins in the overworld");
		iridiumOverworldWeight = cfg.getInt("iridiumOverworldWeight", CATEGORY_GENERATION, iridiumOverworldWeight, 1, 32, "Chance of Iridium veins in the overworld");
		// ruby overworld ore
		rubyGenerate = cfg.getBoolean("rubyGenerate", CATEGORY_GENERATION, rubyGenerate, "Generate Ruby ore in hot overworld biomes");
		rubySize = cfg.getInt("rubySize", CATEGORY_GENERATION, rubySize, 1, 32, "Max size of Ruby veins");
		rubyWeight = cfg.getInt("rubyWeight", CATEGORY_GENERATION, rubyWeight, 1, 32, "Chance of Ruby veins to spawn");
		// sapphire overworld ore
		sapphireGenerate = cfg.getBoolean("sapphireGenerate", CATEGORY_GENERATION, sapphireGenerate, "Generate Sapphire ore in ocean overworld biomes");
		sapphireSize = cfg.getInt("sapphireSize", CATEGORY_GENERATION, sapphireSize, 1, 32, "Max size of Sapphire veins");
		sapphireWeight = cfg.getInt("sapphireWeight", CATEGORY_GENERATION, sapphireWeight, 1, 32, "Chance of Sapphire veins to spawn");
	}

	private static void initCompatConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_MODCOMPAT, "Mod compatability options");
		compatBaubles = cfg.getBoolean("compatBaubles", CATEGORY_MODCOMPAT, compatBaubles, "Enables mod compat for Baubles");
		compatBuildcraft = cfg.getBoolean("compatBuildcraft", CATEGORY_MODCOMPAT, compatBuildcraft, "Enables mod compat for Buildcraft");
		compatDraconic = cfg.getBoolean("compatDraconic", CATEGORY_MODCOMPAT, compatDraconic, "Enables mod compat for Draconic Evolution");
		compatEnderIO = cfg.getBoolean("compatEnderIO", CATEGORY_MODCOMPAT, compatEnderIO, "Enables mod compat for EnderIO");
		compatForestry = cfg.getBoolean("compatForestry", CATEGORY_MODCOMPAT, compatForestry, "Enables mod compat for Forestry");
		compatIc2Extras = cfg.getBoolean("compatIc2Extras", CATEGORY_MODCOMPAT, compatIc2Extras, "Enables mod compat for Ic2 Extras");
		compatIE = cfg.getBoolean("compatIE", CATEGORY_MODCOMPAT, compatIE, "Enables mod compat for Immersive Engineering");
		compatThermal = cfg.getBoolean("compatThermal", CATEGORY_MODCOMPAT, compatThermal, "Enables mod compat for Thermal Expansion");
	}
}