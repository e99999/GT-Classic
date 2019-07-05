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
	// generation
	public static boolean genEndIridium = true;
	public static boolean genOverworldIridium = true;
	public static boolean genOverworldRuby = true;
	public static boolean genOverworldSapphire = true;
	public static boolean genOverworldBauxite = true;
	// mod compat options
	public static boolean ingotsRequireBlastFurnace = true;

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
		ingotsRequireBlastFurnace = cfg.getBoolean("ingotsRequireBlastFurnace", CATEGORY_MODCOMPAT, ingotsRequireBlastFurnace, "Tries to remove vanilla smelting of end game metals from all loaded mods");
	}
}