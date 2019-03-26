package gtclassic.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileMultiBlastFurnace;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Locale;

@ZenClass("mods.gtclassic.BlastFurnace")
public class GTBlastFurnaceSupport {
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input1) {
        CraftTweakerActions.apply(new BlastFurnaceRecipeAction(GTCraftTweakerPlugin.of(input1), new RecipeInputItemStack(ItemStack.EMPTY), new RecipeInputItemStack(ItemStack.EMPTY), CraftTweakerMC.getItemStacks(output)));
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input1, IIngredient input2) {
        CraftTweakerActions.apply(new BlastFurnaceRecipeAction(GTCraftTweakerPlugin.of(input1), GTCraftTweakerPlugin.of(input2), new RecipeInputItemStack(ItemStack.EMPTY), CraftTweakerMC.getItemStacks(output)));
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input1, IIngredient input2, IIngredient input3) {
        CraftTweakerActions.apply(new BlastFurnaceRecipeAction(GTCraftTweakerPlugin.of(input1), GTCraftTweakerPlugin.of(input2), GTCraftTweakerPlugin.of(input3), CraftTweakerMC.getItemStacks(output)));
    }

    private static final class BlastFurnaceRecipeAction implements IAction {

        private final IRecipeInput input1;
        private final IRecipeInput input2;
        private final IRecipeInput input3;
        private final ItemStack[] output;

        BlastFurnaceRecipeAction(IRecipeInput input1, IRecipeInput input2, IRecipeInput input3, ItemStack... output) {
            this.input1 = input1;
            this.input2 = input2;
            this.input3 = input3;
            this.output = output;
        }

        @Override
        public void apply() {
            GTTileMultiBlastFurnace.addRecipe(input1, input2, input3, output);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %S, %s -> %s] to %s", this.input1, this.input2, this.input3, Arrays.deepToString(this.output), GTTileIndustrialCentrifuge.RECIPE_LIST);
        }
    }
}
