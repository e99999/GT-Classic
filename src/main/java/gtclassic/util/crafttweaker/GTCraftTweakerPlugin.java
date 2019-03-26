package gtclassic.util.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputArray;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GTCraftTweakerPlugin {
    static IRecipeInput of(IItemStack item) {
        return new RecipeInputItemStack(CraftTweakerMC.getItemStack(item));
    }

    static IRecipeInput of(IIngredient ingredient) {
        // TODO Dealing with null
        if (ingredient instanceof IItemStack) {
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

    public static void preInit(){
        if (Loader.isModLoaded("crafttweaker")){
            CraftTweakerAPI.registerClass(GTAlloySmelterSupport.class);
            CraftTweakerAPI.registerClass(GTBlastFurnaceSupport.class);
            CraftTweakerAPI.registerClass(GTFusionComputerSupport.class);
        }
    }
}
