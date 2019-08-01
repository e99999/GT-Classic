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
	public static boolean genEndIridium = true;
	public static boolean genOverworldIridium = true;
	public static boolean genOverworldRuby = true;
	public static boolean genOverworldSapphire = true;
	public static boolean genOverworldBauxite = true;
	// mod compat options
	public static boolean compatBaubles = true;
	public static boolean compatBuildcraft = true;
	public static boolean compatDraconic = true;
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
		genEndIridium = cfg.getBoolean("genEndIridium", CATEGORY_GENERATION, genEndIridium, "Generate Iridium ore in the end");
		genOverworldIridium = cfg.getBoolean("genOverworldIridium", CATEGORY_GENERATION, genOverworldIridium, "Generate Iridium ore in the overworld");
		genOverworldRuby = cfg.getBoolean("genOverworldRuby", CATEGORY_GENERATION, genOverworldRuby, "Generate Ruby ore in the overworld");
		genOverworldSapphire = cfg.getBoolean("genOverworldSapphire", CATEGORY_GENERATION, genOverworldSapphire, "Generate Sapphire ore in the overworld");
		genOverworldBauxite = cfg.getBoolean("genOverworldBauxite", CATEGORY_GENERATION, genOverworldBauxite, "Generate Bauxite ore in the overworld");
	}

	private static void initCompatConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_MODCOMPAT, "Mod compatability options");
		compatBaubles = cfg.getBoolean("compatBaubles", CATEGORY_MODCOMPAT, compatBaubles, "Enables mod compat for Baubles");
		compatBuildcraft = cfg.getBoolean("compatBuildcraft", CATEGORY_MODCOMPAT, compatBuildcraft, "Enables mod compat for Buildcraft");
		compatDraconic = cfg.getBoolean("compatDraconic", CATEGORY_MODCOMPAT, compatDraconic, "Enables mod compat for Draconic Evolution");
		compatForestry = cfg.getBoolean("compatForestry", CATEGORY_MODCOMPAT, compatForestry, "Enables mod compat for Forestry");
		compatIc2Extras = cfg.getBoolean("compatIc2Extras", CATEGORY_MODCOMPAT, compatIc2Extras, "Enables mod compat for Ic2 Extras");
		compatIE = cfg.getBoolean("compatIE", CATEGORY_MODCOMPAT, compatIE, "Enables mod compat for Immersive Engineering");
		compatThermal = cfg.getBoolean("compatThermal", CATEGORY_MODCOMPAT, compatThermal, "Enables mod compat for Thermal Expansion");
	}
}