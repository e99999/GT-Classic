package gtclassic.util.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;

public class GTCraftTweakerActions {
    private static final boolean DEBUG = Boolean.getBoolean("gtclassic.debug");

    static void apply(IAction action) {
        if (DEBUG) {
            CraftTweakerAPI.apply(action);
        } else {
            action.apply();
        }
    }

    static IRecipeInput of(IItemStack item) {
        return new RecipeInputItemStack(CraftTweakerMC.getItemStack(item));
    }

    static IRecipeInput of(IIngredient ingredient) {
        if (ingredient instanceof IItemStack) {
            return of((IItemStack)ingredient);
        } else if (ingredient instanceof IOreDictEntry) {
            return new RecipeInputOreDict(((IOreDictEntry)ingredient).getName(), ingredient.getAmount());
        } else {
            // Fallback to the universal solution if we can't take any shortcut
            return new GTCraftTweakerIngredientInput(ingredient);
        }
    }

    static IRecipeInput[] of(IIngredient... ingredient) {
        IRecipeInput[] out = new IRecipeInput[ingredient.length];
        for(int index = 0; index < out.length; index++) {
            out[index] = of(ingredient[index]);
        }
        return out;
    }
}
