package gtclassic.util.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class GTCraftTweakerPlugin {
    static IRecipeInput of(IItemStack item) {
        return new RecipeInputItemStack(CraftTweakerMC.getItemStack(item));
    }

    static IRecipeInput of(IIngredient ingredient) {
        if (ingredient == null) {
            return new RecipeInputItemStack(ItemStack.EMPTY);
        } else if (ingredient instanceof IItemStack) {
            return GTCraftTweakerPlugin.of((IItemStack)ingredient);
        } else if (ingredient instanceof IOreDictEntry) {
            return new RecipeInputOreDict(((IOreDictEntry)ingredient).getName(), ingredient.getAmount());
        } else {
            // Fallback to the universal solution if we can't take any shortcut
            return new CraftTweakerIngredientInput(ingredient);
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
