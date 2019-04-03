package gtclassic.util.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.commands.CraftTweakerCommand;
import crafttweaker.mc1120.util.CraftTweakerPlatformUtils;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileMultiBlastFurnace;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ZenClass("mods.gtclassic.BlastFurnace")
@ZenRegister
public class GTBlastFurnaceSupport {
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input1) {
        CraftTweakerActions.apply(new BlastFurnaceRecipeAction(GTCraftTweakerPlugin.of(input1), CraftTweakerMC.getItemStacks(output)));
    }

    private static final class BlastFurnaceRecipeAction implements IAction {
        private final IRecipeInput[] input;
        private final ItemStack[] output;

        BlastFurnaceRecipeAction(IRecipeInput[] input, ItemStack... output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public void apply() {
            if (input.length > 3){
                CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > " + "Recipe can only have a max of three inputs!");
            }else {
                GTTileMultiBlastFurnace.addRecipe(input, output);
            }
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", Arrays.deepToString(this.input), Arrays.deepToString(this.output), GTTileMultiBlastFurnace.RECIPE_LIST);
        }
    }
}
