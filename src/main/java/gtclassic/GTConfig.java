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
	public static boolean animatedTextures = true;
	public static boolean addLootItems = true;
	public static boolean ingotsRequireBlastFurnace = true;
	public static boolean preventMobSpawnsCloseToSpawn = true;
	public static boolean removeIC2MassFab = true;
	public static boolean removeIC2Plasmafier = true;
	public static boolean addBasicCircuitRecipes = true;
	public static boolean addAdvCircuitRecipes = true;
	public static boolean vanillaRailRecipes = true;
	public static boolean harderIC2Macerator = true;
	public static boolean betterIC2SolarRecipes = true;
	public static boolean gregtechUURecipes = true;
	public static boolean oreDictWroughtIron = true;
	public static boolean moreHopperRecipes = true;
	public static boolean morePistonRecipes = true;
	public static boolean clearerWater = true;
	public static boolean replaceOceanGravelWithSand = true;
	public static boolean caveZombiesSpawnWithPickaxe = true;
	// generation
	public static boolean iridiumGenerate = true;
	public static int iridiumSize = 3;
	public static int iridiumWeight = 2;
	public static boolean platinumGenerate = true;
	public static int platinumSize = 4;
	public static int platinumWeight = 3;
	public static boolean rubyGenerate = true;
	public static int rubySize = 5;
	public static int rubyWeight = 2;
	public static boolean sapphireGenerate = true;
	public static int sapphireSize = 5;
	public static int sapphireWeight = 2;
	public static boolean bauxiteGenerate = true;
	public static int bauxiteSize = 16;
	public static int bauxiteWeight = 4;
	// mod compat options
	public static boolean compatBaubles = true;
	public static boolean compatBuildcraft = true;
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
		animatedTextures = cfg.getBoolean("animatedTextures", CATEGORY_CONFIG, animatedTextures, "Enables animated textures for GT blocks and items");
		addLootItems = cfg.getBoolean("addLootItems", CATEGORY_CONFIG, addLootItems, "Adds GregTech items to loot pool");
		ingotsRequireBlastFurnace = cfg.getBoolean("ingotsRequireBlastFurnace", CATEGORY_CONFIG, ingotsRequireBlastFurnace, "Tries to remove vanilla smelting of end game metals from all loaded mods");
		preventMobSpawnsCloseToSpawn = cfg.getBoolean("preventMobSpawnsCloseToSpawn", CATEGORY_CONFIG, preventMobSpawnsCloseToSpawn, "Prevents mob spawning in a 128 block radius around world spawn");
		removeIC2MassFab = cfg.getBoolean("removeIc2MassFab", CATEGORY_CONFIG, removeIC2MassFab, "Removes the IC2 Mass Fab in favor of the GT Matter Fabricator");
		removeIC2Plasmafier = cfg.getBoolean("removeIc2Plasmafier", CATEGORY_CONFIG, removeIC2Plasmafier, "Removes the IC2 Plasmafier in favor of GT Fusion");
		addBasicCircuitRecipes = cfg.getBoolean("addBasicCircuitRecipes", CATEGORY_CONFIG, addBasicCircuitRecipes, "Adds more metals to basic circuits, and a 2 x basic circuit recipe");
		addAdvCircuitRecipes = cfg.getBoolean("addAdvCircuitRecipes", CATEGORY_CONFIG, addAdvCircuitRecipes, "Adds more materials to adv circuit recipe");
		vanillaRailRecipes = cfg.getBoolean("vanillaRailRecipes", CATEGORY_CONFIG, vanillaRailRecipes, "Adds better recipes for vanilla rails");
		harderIC2Macerator = cfg.getBoolean("harderIC2Macerator", CATEGORY_CONFIG, harderIC2Macerator, "Makes the IC2 Macerator more expensive");
		betterIC2SolarRecipes = cfg.getBoolean("betterIC2SolarRecipes", CATEGORY_CONFIG, betterIC2SolarRecipes, "Makes the basic IC2 solar recipes simplier but more expensive");
		gregtechUURecipes = cfg.getBoolean("gregtechUURecipes", CATEGORY_CONFIG, gregtechUURecipes, "Adds UU recipes for GregTech materials");
		oreDictWroughtIron = cfg.getBoolean("oreDictWroughtIron", CATEGORY_CONFIG, oreDictWroughtIron, "Ore dictionaries all cases of ingotWroughtIron and ingotRefinedIron together");
		moreHopperRecipes = cfg.getBoolean("moreHopperRecipes", CATEGORY_CONFIG, moreHopperRecipes, "Adds more metals to vanilla hopper recipe");
		morePistonRecipes = cfg.getBoolean("morePistonRecipes", CATEGORY_CONFIG, morePistonRecipes, "Adds more metals to vanilla piston recipe");
		clearerWater = cfg.getBoolean("clearerWater", CATEGORY_CONFIG, clearerWater, "Reduces fog/haze when underwater");
		replaceOceanGravelWithSand = cfg.getBoolean("replaceOceanGravelWithSand", CATEGORY_CONFIG, replaceOceanGravelWithSand, "Replaces most gravel in oceans with sand");
		caveZombiesSpawnWithPickaxe = cfg.getBoolean("caveZombiesSpawnWithPickaxe", CATEGORY_CONFIG, caveZombiesSpawnWithPickaxe, "Zombies spawned in deep caves have a chance of wielding a pickaxe");
	}

	private static void initGenerationConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERATION, "Generation configuration");
		// iridium overworld ore
		iridiumGenerate = cfg.getBoolean("iridiumGenerate", CATEGORY_GENERATION, iridiumGenerate, "Generate Iridium ore in the overworld");
		iridiumSize = cfg.getInt("iridiumSize", CATEGORY_GENERATION, iridiumSize, 1, 32, "Max size of Iridium veins");
		iridiumWeight = cfg.getInt("iridiumWeight", CATEGORY_GENERATION, iridiumWeight, 1, 32, "Chance of Iridium veins to spawn");
		// platinum overworld ore
		platinumGenerate = cfg.getBoolean("platinumOverworldGenerate", CATEGORY_GENERATION, platinumGenerate, "Generate Platinum ore in jungle overworld biomes");
		platinumSize = cfg.getInt("platinumOverworldSize", CATEGORY_GENERATION, platinumSize, 1, 32, "Max size of Platinum veins");
		platinumWeight = cfg.getInt("platinumOverworldWeight", CATEGORY_GENERATION, platinumWeight, 1, 32, "Chance of Platinum veins to spawn");
		// ruby overworld ore
		rubyGenerate = cfg.getBoolean("rubyGenerate", CATEGORY_GENERATION, rubyGenerate, "Generate Ruby ore in hot overworld biomes");
		rubySize = cfg.getInt("rubySize", CATEGORY_GENERATION, rubySize, 1, 32, "Max size of Ruby veins");
		rubyWeight = cfg.getInt("rubyWeight", CATEGORY_GENERATION, rubyWeight, 1, 32, "Chance of Ruby veins to spawn");
		// sapphire overworld ore
		sapphireGenerate = cfg.getBoolean("sapphireGenerate", CATEGORY_GENERATION, sapphireGenerate, "Generate Sapphire ore in ocean overworld biomes");
		sapphireSize = cfg.getInt("sapphireSize", CATEGORY_GENERATION, sapphireSize, 1, 32, "Max size of Sapphire veins");
		sapphireWeight = cfg.getInt("sapphireWeight", CATEGORY_GENERATION, sapphireWeight, 1, 32, "Chance of Sapphire veins to spawn");
		// bauxite overworld ore
		bauxiteGenerate = cfg.getBoolean("bauxiteGenerate", CATEGORY_GENERATION, bauxiteGenerate, "Generate Bauxite ore in plains/forest biomes in the overworld");
		bauxiteSize = cfg.getInt("bauxiteSize", CATEGORY_GENERATION, bauxiteSize, 1, 32, "Max size of Bauxite veins");
		bauxiteWeight = cfg.getInt("bauxiteWeight", CATEGORY_GENERATION, bauxiteWeight, 1, 32, "Chance of Bauxite veins to spawn");
	}

	private static void initCompatConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_MODCOMPAT, "Mod compatability options");
		compatBaubles = cfg.getBoolean("compatBaubles", CATEGORY_MODCOMPAT, compatBaubles, "Enables mod compat for Baubles");
		compatBuildcraft = cfg.getBoolean("compatBuildcraft", CATEGORY_MODCOMPAT, compatBuildcraft, "Enables mod compat for Buildcraft");
		compatEnderIO = cfg.getBoolean("compatEnderIO", CATEGORY_MODCOMPAT, compatEnderIO, "Enables mod compat for EnderIO");
		compatForestry = cfg.getBoolean("compatForestry", CATEGORY_MODCOMPAT, compatForestry, "Enables mod compat for Forestry");
		compatIc2Extras = cfg.getBoolean("compatIc2Extras", CATEGORY_MODCOMPAT, compatIc2Extras, "Enables mod compat for Ic2 Extras");
		compatIE = cfg.getBoolean("compatIE", CATEGORY_MODCOMPAT, compatIE, "Enables mod compat for Immersive Engineering");
		compatThermal = cfg.getBoolean("compatThermal", CATEGORY_MODCOMPAT, compatThermal, "Enables mod compat for Thermal Expansion");
	}
}