package gtclassic;

import org.apache.logging.log4j.Level;

import gtclassic.proxy.GTProxyCommon;
import net.minecraftforge.common.config.Configuration;

public class GTConfig {

	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_GENERATION = "generation";
	private static final String CATEGORY_TOXICDIM = "toxic dimension";

	// general
	public static boolean compatSecretProject = false;
	public static boolean compatSuperTech = false;

	// generation
	public static boolean genIridium = true;
	public static boolean genRuby = true;
	public static boolean genSapphire = true;
	public static boolean genBauxite = true;
	public static boolean genBlackSand = true;

	// toxic dim
	public static int dimensionId = 100;

	public static void readConfig() {
		Configuration cfg = GTProxyCommon.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initGenerationConfig(cfg);
			initDimensionConfig(cfg);
		} catch (Exception e1) {
			GTClassic.logger.log(Level.ERROR, "Problem loading config file!", e1);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
		compatSecretProject = cfg.getBoolean("compatSecretProject", CATEGORY_GENERAL, compatSecretProject,
				"Enables Secret Project Compat");
		compatSuperTech = cfg.getBoolean("compatSuperTech", CATEGORY_GENERAL, compatSecretProject,
				"Enables Super Tech Compat");
	}

	private static void initGenerationConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERATION, "Generation configuration");
		genIridium = cfg.getBoolean("genIridium", CATEGORY_GENERATION, genIridium, "Generate Overworld Iridium Ore");
		genRuby = cfg.getBoolean("genRuby", CATEGORY_GENERATION, genRuby, "Generate Overworld Ruby");
		genSapphire = cfg.getBoolean("genSapphire", CATEGORY_GENERATION, genSapphire, "Generate Overworld Sapphire");
		genBauxite = cfg.getBoolean("genBauxite", CATEGORY_GENERATION, genBauxite, "Generate Overworld Bauxite");
		genBlackSand = cfg.getBoolean("genBlackSand", CATEGORY_GENERATION, genBlackSand,
				"Generate Overworld Black Sand");

	}

	private static void initDimensionConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_TOXICDIM, "Toxic Dimension configuration");
		dimensionId = cfg.getInt("dimensionId", CATEGORY_TOXICDIM, dimensionId, -1000, 1000,
				"The Id to use for the toxic dimension");

	}
}
