package gtclassic;

import org.apache.logging.log4j.Level;

import gtclassic.proxy.GTProxyCommon;
import net.minecraftforge.common.config.Configuration;

public class GTConfig {

	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_WORLDGEN = "world generation";

	// general
	public static boolean compatSecretProject = false;
	public static boolean compatSuperTech = false;

	// generation
	public static boolean genIridium = true;
	public static boolean genRuby = true;
	public static boolean genSapphire = true;
	public static boolean genBauxite = true;
	public static boolean genBlackSand = true;

	public static void readConfig() {
		Configuration cfg = GTProxyCommon.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initGenerationConfig(cfg);
		} catch (Exception e1) {
			GTClassic.logger.log(Level.ERROR, "Problem loading config file!", e1);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General");
		compatSecretProject = cfg.getBoolean("compatSecretProject", CATEGORY_GENERAL, compatSecretProject,
				"Enables Secret Project Compat");
		compatSuperTech = cfg.getBoolean("compatSuperTech", CATEGORY_GENERAL, compatSecretProject,
				"Enables Super Tech Compat");
	}

	private static void initGenerationConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_WORLDGEN, "World Generation");
		genIridium = cfg.getBoolean("genIridium", CATEGORY_WORLDGEN, genIridium, "Generate Overworld Iridium Ore");
		genRuby = cfg.getBoolean("genRuby", CATEGORY_WORLDGEN, genRuby, "Generate Overworld Ruby");
		genSapphire = cfg.getBoolean("genSapphire", CATEGORY_WORLDGEN, genSapphire, "Generate Overworld Sapphire");
		genBauxite = cfg.getBoolean("genBauxite", CATEGORY_WORLDGEN, genBauxite, "Generate Overworld Bauxite");
		genBlackSand = cfg.getBoolean("genBlackSand", CATEGORY_WORLDGEN, genBlackSand, "Generate Overworld Black Sand");

	}

}
