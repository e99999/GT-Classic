package gtclassic;

import org.apache.logging.log4j.Level;

import gtclassic.proxy.GTProxyCommon;
import net.minecraftforge.common.config.Configuration;

public class GTConfig {

	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_GENERATION = "generation";

	// general
	public static boolean compatSecretProject = false;
	public static boolean compatSuperTech = false;

	// generation
	public static boolean genNetherPyrite = true;
	public static boolean genNetherCinnabar = true;
	public static boolean genNetherSphalerite = true;
	public static boolean genEndAsteroids = true;
	public static boolean genEndTungstate = true;
	public static boolean genEndSheldonite = true;
	public static boolean genEndSodalite = true;
	public static boolean genEndOlivine = true;
	public static boolean genOverworldGalena = true;
	public static boolean genOverworldIridium = true;
	public static boolean genOverworldRuby = true;
	public static boolean genOverworldSapphire = true;
	public static boolean genOverworldBauxite = true;
	public static boolean genOverworldBlackSand = true;

	public static void readConfig() {
		Configuration cfg = GTProxyCommon.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initGenerationConfig(cfg);
		} catch (Exception e1) {
			GTMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
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
		genNetherPyrite = cfg.getBoolean("genNetherPyrite", CATEGORY_GENERATION, genNetherPyrite,
				"Generate Nether Pyrite Ore");
		genNetherCinnabar = cfg.getBoolean("genNetherCinnabar", CATEGORY_GENERATION, genNetherCinnabar,
				"Generate Nether Cinnabar Ore");
		genNetherSphalerite = cfg.getBoolean("genNetherSphalerite", CATEGORY_GENERATION, genNetherSphalerite,
				"Generate Nether Sphalerite Ore");

		genEndAsteroids = cfg.getBoolean("genEndAsteroids", CATEGORY_GENERATION, genEndAsteroids,
				"Generate End Asteroids");
		genEndTungstate = cfg.getBoolean("genEndTungstate", CATEGORY_GENERATION, genEndTungstate,
				"Generate End Tungstate Ore");
		genEndSheldonite = cfg.getBoolean("genEndSheldonite", CATEGORY_GENERATION, genEndSheldonite,
				"Generate End Sheldonite");
		genEndSodalite = cfg.getBoolean("genEndSodalite", CATEGORY_GENERATION, genEndSodalite, "Generate End Sodalite");
		genEndOlivine = cfg.getBoolean("genEndOlivine", CATEGORY_GENERATION, genEndOlivine, "Generate End Olivine");

		genOverworldGalena = cfg.getBoolean("genGalena", CATEGORY_GENERATION, genOverworldGalena,
				"Generate Overworld Galena Ore");
		genOverworldIridium = cfg.getBoolean("genIridium", CATEGORY_GENERATION, genOverworldIridium,
				"Generate Overworld Iridium Ore");
		genOverworldRuby = cfg.getBoolean("genRuby", CATEGORY_GENERATION, genOverworldRuby, "Generate Overworld Ruby");
		genOverworldSapphire = cfg.getBoolean("genSapphire", CATEGORY_GENERATION, genOverworldSapphire,
				"Generate Overworld Sapphire");
		genOverworldBauxite = cfg.getBoolean("genBauxite", CATEGORY_GENERATION, genOverworldBauxite,
				"Generate Overworld Bauxite");
		genOverworldBlackSand = cfg.getBoolean("genBlackSand", CATEGORY_GENERATION, genOverworldBlackSand,
				"Generate Overworld Black Sand");

	}

}