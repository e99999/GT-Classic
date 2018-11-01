package gtclassic;

import gtclassic.toxicdimension.world.ToxicWorldProvider;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class GTDimensions {

    public static DimensionType testDimensionType;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        testDimensionType = DimensionType.register(GTMod.MODID, "_test", GTConfig.dimensionId, ToxicWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(GTConfig.dimensionId, testDimensionType);
    }

}
