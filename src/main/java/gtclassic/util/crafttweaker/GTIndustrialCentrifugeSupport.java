package gtclassic.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
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
    public static void addRecipe(IItemStack[] output, IIngredient input, int cells, int energyPerTick, int totalEnergy) {
        CraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(GTCraftTweakerPlugin.of(input), cells, energyPerTick, totalEnergy, CraftTweakerMC.getItemStacks(output)));
    }

    private static final class IndustrialCentrifugeRecipeAction implements IAction {

        private final IRecipeInput input;
        private final int cells;
        private final int energyPerTick;
        private final int totalEnergy;
        private final ItemStack[] output;

        IndustrialCentrifugeRecipeAction(IRecipeInput input, int cells, int energyPerTick, int  totalEnergy, ItemStack... output) {
            this.input = input;
            this.cells = cells;
            this.energyPerTick = energyPerTick;
            this.totalEnergy = totalEnergy;
            this.output = output;
        }

        @Override
        public void apply() {
            //Empty Method till industrial centrifuge changes.
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s, %s, %s -> %s] to %s", this.input, this.cells, this.energyPerTick, this.totalEnergy, Arrays.deepToString(this.output), GTTileIndustrialCentrifuge.RECIPE_LIST);
        }
    }
}
