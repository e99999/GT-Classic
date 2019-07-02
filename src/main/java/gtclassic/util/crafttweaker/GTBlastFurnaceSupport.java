package gtclassic.util.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Locale;

@ZenClass("mods.gtclassic.BlastFurnace")
@ZenRegister
public class GTBlastFurnaceSupport {
    @ZenMethod
    public static void addRecipe(IItemStack[] output, @Optional(valueLong = 1000L)int totalEu, IIngredient[] input1) {
        GTCraftTweakerActions.apply(new BlastFurnaceRecipeAction(GTCraftTweakerActions.of(input1), totalEu, CraftTweakerMC.getItemStacks(output)));
    }

    private static final class BlastFurnaceRecipeAction implements IAction {
        private final IRecipeInput[] input;
        private final int totalEu;
        private final ItemStack[] output;

        BlastFurnaceRecipeAction(IRecipeInput[] input, int totalEu, ItemStack... output) {
            this.input = input;
            this.totalEu = totalEu;
            this.output = output;
        }

        @Override
        public void apply() {
            if (input.length > 4){
                CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > " + "Recipe can only have a max of four inputs!");
            }else {
                GTTileMultiBlastFurnace.addRecipe(input, totalEu, output);
            }
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", Arrays.deepToString(this.input), Arrays.deepToString(this.output), GTTileMultiBlastFurnace.RECIPE_LIST);
        }
    }
}
