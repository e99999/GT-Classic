package gtclassic.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileMultiBlastFurnace;
import gtclassic.tile.GTTileMultiFusionComputer;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Locale;

@ZenClass("mods.gtclassic.FusionComputer")
@ZenRegister
public class GTFusionComputerSupport {
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2) {
        CraftTweakerActions.apply(new FusionComputerRecipeAction(GTCraftTweakerPlugin.of(input1), GTCraftTweakerPlugin.of(input2), CraftTweakerMC.getItemStack(output)));
    }

    private static final class FusionComputerRecipeAction implements IAction {

        private final IRecipeInput input1;
        private final IRecipeInput input2;
        private final ItemStack output;

        FusionComputerRecipeAction(IRecipeInput input1, IRecipeInput input2, ItemStack output) {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
        }

        @Override
        public void apply() {
            GTTileMultiFusionComputer.addRecipe(input1, input2, output);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s] to %s", this.input1, this.input2, this.output, GTTileMultiFusionComputer.RECIPE_LIST);
        }
    }
}
