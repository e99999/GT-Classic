package gtclassic.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileMultiBlastFurnace;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Locale;

@ZenClass("mods.gtclassic.BlastFurnace")
public class GTBlastFurnaceSupport {
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input) {
        CraftTweakerActions.apply(new BlastFurnaceRecipeAction(GTCraftTweakerPlugin.of(input), CraftTweakerMC.getItemStacks(output)));
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
            GTTileMultiBlastFurnace.addRecipe(input, output);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", Arrays.deepToString(this.input), Arrays.deepToString(this.output), GTTileIndustrialCentrifuge.RECIPE_LIST);
        }
    }
}
