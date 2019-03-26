package gtclassic.util.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;

public class CraftTweakerActions {
    private static final boolean DEBUG = Boolean.getBoolean("gtclassic.debug");

    static void apply(IAction action) {
        if (DEBUG) {
            CraftTweakerAPI.apply(action);
        } else {
            action.apply();
        }
    }
}

