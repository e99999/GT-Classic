package gtclassic.common.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.runtime.ScriptLoader;
import gtclassic.GTMod;

public class GTCraftTweakerLoader {
    public static void preInit(){
        ScriptLoader loader = CraftTweakerAPI.tweaker.getOrCreateLoader(GTMod.MODID + "_data");
        CraftTweakerAPI.tweaker.loadScript(false, loader);
    }
}
