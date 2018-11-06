package gtclassic;

import org.apache.logging.log4j.Level;

import gtclassic.proxy.GTProxyCommon;

import net.minecraftforge.common.config.Configuration;

public class GTConfig {

	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_GEN = "worldgen";
    private static final String CATEGORY_DIMENSIONS = "dimensions";
    

    // This values below you can access elsewhere in your mod:
    public static boolean isThisAGoodGregtech = true;
    public static String yourRealName = "Steve";
    
    public static boolean genNetherPyrite = true;
    public static boolean genNetherCinnabar = true;
    public static boolean genNetherSphalerite = true;
    
    public static int dimensionId = 100;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    
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
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        isThisAGoodGregtech = cfg.getBoolean("goodPort", CATEGORY_GENERAL, isThisAGoodGregtech, "Is this a good GregTech port?");
        yourRealName = cfg.getString("realName", CATEGORY_GENERAL, yourRealName, "Set your real name here");
    }
    
    private static void initGenerationConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GEN, "World generation configuration");
        genNetherPyrite = cfg.getBoolean("genNetherPyrite", CATEGORY_GEN, genNetherPyrite, "Generate Nether Pyrite Ore");
        genNetherCinnabar = cfg.getBoolean("genNetherCinnabar", CATEGORY_GEN, genNetherCinnabar, "Generate Nether Cinnabar Ore");
        genNetherSphalerite = cfg.getBoolean("genNetherSphalerite", CATEGORY_GEN, genNetherSphalerite, "Generate Nether Sphalerite Ore");
    }

    private static void initDimensionConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_DIMENSIONS, "Dimension configuration");
        dimensionId = cfg.getInt("dimensionId", CATEGORY_DIMENSIONS, dimensionId, -1000, 1000, "The Id to use for the toxic dimension");

    }
}
