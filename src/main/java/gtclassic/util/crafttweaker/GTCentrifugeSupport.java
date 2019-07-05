package gtclassic.util.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.GTItems;
import gtclassic.tile.GTTileCentrifuge;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Locale;

@ZenClass("mods.gtclassic.IndustrialCentrifuge")
@ZenRegister
public class GTCentrifugeSupport {
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input1, IIngredient input2, @Optional(valueLong = 3200L)int totalEu) {
        GTCraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(GTCraftTweakerActions.of(input1), GTCraftTweakerActions.of(input2), totalEu, CraftTweakerMC.getItemStacks(output)));
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input1, @Optional(valueLong = 3200L)int totalEu) {
        GTCraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(GTCraftTweakerActions.of(input1), totalEu, CraftTweakerMC.getItemStacks(output)));
    }

    @ZenMethod
    public static void addCellRecipe(IItemStack[] output, IIngredient input1, int cells, @Optional(valueLong = 3200L)int totalEu) {
        if (cells > 0){
            GTCraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(GTCraftTweakerActions.of(input1), new RecipeInputItemStack(new ItemStack(GTItems.testTube, cells)), totalEu, CraftTweakerMC.getItemStacks(output)));
        }else {
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > " + "Cell count must be greater then 0!!");
        }
    }

    private static final class IndustrialCentrifugeRecipeAction implements IAction {
        private final IRecipeInput input1;
        private final IRecipeInput input2;
        private final int totalEu;
        private final ItemStack[] output;

        IndustrialCentrifugeRecipeAction(IRecipeInput input1, IRecipeInput input2, int totalEu, ItemStack... output) {
            this.input1 = input1;
            this.input2 = input2;
            this.totalEu = totalEu;
            this.output = output;
        }

        IndustrialCentrifugeRecipeAction(IRecipeInput input1, int totalEu, ItemStack... output) {
            this.input1 = input1;
            this.input2 = null;
            this.totalEu = totalEu;
            this.output = output;
        }

        @Override
        public void apply() {
            if (totalEu > 0){
                if (input2 != null){
                    GTTileCentrifuge.addRecipe(new IRecipeInput[]{input1, input2}, GTTileCentrifuge.totalEu(totalEu), output);
                }else {
                    GTTileCentrifuge.addRecipe(new IRecipeInput[]{input1}, GTTileCentrifuge.totalEu(totalEu), output);
                }
            }else {
                CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > " + "Eu amount must be greater then 0!!");
            }

        }

        @Override
        public String describe() {
            if (input2 != null){
                return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", this.input1, this.input2, Arrays.deepToString(this.output), GTTileCentrifuge.RECIPE_LIST);
            }else {
                return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", this.input1, Arrays.deepToString(this.output), GTTileCentrifuge.RECIPE_LIST);
            }
        }
    }

}
