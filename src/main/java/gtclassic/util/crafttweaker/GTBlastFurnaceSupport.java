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
    public static void addSingleRecipe(IIngredient input1, IItemStack... output) {
        CraftTweakerActions.apply(new BlastFurnaceSingleRecipeAction(GTCraftTweakerPlugin.of(input1), CraftTweakerMC.getItemStacks(output)));
    }

    @ZenMethod
    public static void addDoubleRecipe(IIngredient input1, IIngredient input2, IItemStack... output) {
        CraftTweakerActions.apply(new BlastFurnaceDoubleRecipeAction(GTCraftTweakerPlugin.of(input1), GTCraftTweakerPlugin.of(input2), CraftTweakerMC.getItemStacks(output)));
    }

    @ZenMethod
    public static void addTripleRecipe(IIngredient input1, IIngredient input2, IIngredient input3, IItemStack... output) {
        CraftTweakerActions.apply(new BlastFurnaceTripleRecipeAction(GTCraftTweakerPlugin.of(input1), GTCraftTweakerPlugin.of(input2), GTCraftTweakerPlugin.of(input3), CraftTweakerMC.getItemStacks(output)));
    }

    private static final class BlastFurnaceSingleRecipeAction implements IAction {

        private final IRecipeInput input1;
        private final ItemStack[] output;

        BlastFurnaceSingleRecipeAction(IRecipeInput input1, ItemStack... output) {
            this.input1 = input1;
            this.output = output;
        }

        @Override
        public void apply() {
            GTTileMultiBlastFurnace.addRecipe(input1, output);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s",  Arrays.deepToString(this.output), GTTileIndustrialCentrifuge.RECIPE_LIST);
        }
    }

    private static final class BlastFurnaceDoubleRecipeAction implements IAction {

        private final IRecipeInput input1;
        private final IRecipeInput input2;
        private final ItemStack[] output;

        BlastFurnaceDoubleRecipeAction(IRecipeInput input1, IRecipeInput input2, ItemStack... output) {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
        }

        @Override
        public void apply() {
            GTTileMultiBlastFurnace.addRecipe(input1, input2, output);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s",  Arrays.deepToString(this.output), GTTileIndustrialCentrifuge.RECIPE_LIST);
        }
    }

    private static final class BlastFurnaceTripleRecipeAction implements IAction {

        private final IRecipeInput input1;
        private final IRecipeInput input2;
        private final IRecipeInput input3;
        private final ItemStack[] output;

        BlastFurnaceTripleRecipeAction(IRecipeInput input1, IRecipeInput input2, IRecipeInput input3, ItemStack... output) {
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
            return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s",  Arrays.deepToString(this.output), GTTileIndustrialCentrifuge.RECIPE_LIST);
        }
    }
}
