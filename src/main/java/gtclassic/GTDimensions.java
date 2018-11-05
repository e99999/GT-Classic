package gtclassic;

import gtclassic.toxicdimension.world.GTToxicWorldProvider;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class GTDimensions {

    public static DimensionType testDimensionType;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        testDimensionType = DimensionType.register(GTClassic.MODID, "_test", GTConfig.dimensionId, GTToxicWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(GTConfig.dimensionId, testDimensionType);
    }

}
