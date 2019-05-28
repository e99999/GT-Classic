package gtclassic;

import org.apache.logging.log4j.Level;

import gtclassic.proxy.GTProxyCommon;
import net.minecraftforge.common.config.Configuration;

public class GTConfig {

	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_GENERATION = "generation";

	// general
	public static boolean harderPlates = false;
	public static boolean harderRods = false;
	public static boolean harderGears = false;
	public static boolean harderRefractory = true;

	// generation
	public static boolean genOverworldOre = true;
	public static boolean genNetherOre = true;
	public static boolean genEndOre = true;
	public static boolean genBedrockOre = true;

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

	// @formatter:off
	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
		harderPlates = cfg.getBoolean("harderPlates", CATEGORY_GENERAL, harderPlates,"Hand crafting plates takes two ingots");
		harderRods = cfg.getBoolean("harderRods", CATEGORY_GENERAL, harderRods, "Hand crafting of rods output reduced to 1 from 2");
		harderGears = cfg.getBoolean("harderGears", CATEGORY_GENERAL, harderGears, "Hand crafting of gears takes 4 extra rods");
		harderRefractory = cfg.getBoolean("harderRefractory", CATEGORY_GENERAL, harderRefractory, "Enables larger refratory structure coming in the next update");
	}

	private static void initGenerationConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERATION, "Generation configuration");
		genOverworldOre = cfg.getBoolean("genOverworldOre", CATEGORY_GENERATION, genOverworldOre, "Generate stone, sand, and gravel ores in the Overworld");
		genNetherOre = cfg.getBoolean("genNetherOre", CATEGORY_GENERATION, genNetherOre, "Generate ores in the Nether");
		genEndOre = cfg.getBoolean("genEndOre", CATEGORY_GENERATION, genEndOre, "Generate ores in the End");
		genBedrockOre = cfg.getBoolean("genBedrockOre", CATEGORY_GENERATION, genBedrockOre, "Generate ores in bedrock");
	}
	// @formatter:on

}