package gtclassic.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.recipe.GTRecipeIndustrialCentrifuge;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Locale;

@ZenClass("mods.gtclassic.IndustrialCentrifuge")
public class GTIndustrialCentrifugeSupport {

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, int cells, int energy) {
        CraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(GTCraftTweakerPlugin.of(input), cells, energy, CraftTweakerMC.getItemStacks(output)));
    }

    private static final class IndustrialCentrifugeRecipeAction implements IAction {

        private final IRecipeInput input;
        private final int cells;
        private final int energy;
        private final ItemStack[] output;

        IndustrialCentrifugeRecipeAction(IRecipeInput input, int cells, int energy, ItemStack... output) {
            this.input = input;
            this.cells = cells;
            this.energy = energy;
            this.output = output;
        }

        @Override
        public void apply() {
            //GTTileIndustrialCentrifuge.addRecipe(input, cells, GTRecipeIndustrialCentrifuge.euCost(energy), );
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s %s -> %s] to %s", this.input, this.cells, this.energy, Arrays.deepToString(this.output), GTTileIndustrialCentrifuge.RECIPE_LIST);
        }
    }
}
