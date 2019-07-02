package gtclassic.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.tile.multi.GTTileMultiFusion;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Locale;

@ZenClass("mods.gtclassic.FusionComputer")
@ZenRegister
public class GTFusionSupport {
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int totalEu) {
        GTCraftTweakerActions.apply(new FusionComputerRecipeAction(GTCraftTweakerActions.of(input1), GTCraftTweakerActions.of(input2), totalEu, CraftTweakerMC.getItemStack(output)));
    }

    private static final class FusionComputerRecipeAction implements IAction {

        private final IRecipeInput input1;
        private final IRecipeInput input2;
        private final int totalEu;
        private final ItemStack output;

        FusionComputerRecipeAction(IRecipeInput input1, IRecipeInput input2, int totalEu, ItemStack output) {
            this.input1 = input1;
            this.input2 = input2;
            this.totalEu = totalEu;
            this.output = output;
        }

        @Override
        public void apply() {
            GTTileMultiFusion.addRecipe(new IRecipeInput[]{input1, input2}, GTTileMultiFusion.totalEu(totalEu), output);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s] to %s", this.input1, this.input2, this.output, GTTileMultiFusion.RECIPE_LIST);
        }
    }
}
