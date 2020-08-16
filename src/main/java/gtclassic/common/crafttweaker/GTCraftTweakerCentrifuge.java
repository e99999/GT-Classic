package gtclassic.common.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.api.crafttweaker.GTCraftTweakerActions;
import gtclassic.common.tile.GTTileCentrifuge;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Locale;

@ZenClass("mods.gtclassic.IndustrialCentrifuge")
@ZenRegister
public class GTCraftTweakerCentrifuge {

	@ZenMethod
	public static void addRecipe(IItemStack[] output, IIngredient input1, IIngredient input2,
			@Optional(valueLong = 3200L) int totalEu) {
		GTCraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(new IRecipeInput[]{GTCraftTweakerActions.of(input1), GTCraftTweakerActions.of(input2)}, totalEu, CraftTweakerMC.getItemStacks(output)));
	}

	@ZenMethod
	public static void addRecipe(IItemStack[] output, IIngredient input1, @Optional(valueLong = 3200L) int totalEu) {
		GTCraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(new IRecipeInput[]{GTCraftTweakerActions.of(input1)}, totalEu, CraftTweakerMC.getItemStacks(output)));
	}

	@ZenMethod
	public static void addRecipe(IItemStack[] output, ILiquidStack[] fluidOutput, IIngredient input1, IIngredient input2,
								 @Optional(valueLong = 3200L) int totalEu) {
		GTCraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(new IRecipeInput[]{GTCraftTweakerActions.of(input1), GTCraftTweakerActions.of(input2)}, totalEu, CraftTweakerMC.getItemStacks(output), CraftTweakerMC.getLiquidStacks(fluidOutput)));
	}

	@ZenMethod
	public static void addRecipe(IItemStack[] output, ILiquidStack[] fluidOutput, IIngredient input1, @Optional(valueLong = 3200L) int totalEu) {
		GTCraftTweakerActions.apply(new IndustrialCentrifugeRecipeAction(new IRecipeInput[]{GTCraftTweakerActions.of(input1)}, totalEu, CraftTweakerMC.getItemStacks(output), CraftTweakerMC.getLiquidStacks(fluidOutput)));
	}

	private static final class IndustrialCentrifugeRecipeAction implements IAction {

		private final IRecipeInput[] input;
		private final int totalEu;
		private final ItemStack[] output;
		private final FluidStack[] fluidOutput;

		IndustrialCentrifugeRecipeAction(IRecipeInput[] input, int totalEu, ItemStack[] output, FluidStack[] fluidOutput) {
			this.input = input;
			this.totalEu = totalEu;
			this.output = output;
			this.fluidOutput = fluidOutput;
		}

		IndustrialCentrifugeRecipeAction(IRecipeInput[] input, int totalEu, ItemStack[] output) {
			this.input = input;
			this.totalEu = totalEu;
			this.output = output;
			this.fluidOutput = new FluidStack[]{};
		}

		@Override
		public void apply() {
			if (totalEu > 0) {
				String recipeId = fluidOutput.length == 0 ? output[0].getUnlocalizedName() : fluidOutput[0].getUnlocalizedName();
				GTTileCentrifuge.addRecipe(input, GTTileCentrifuge.totalEu(totalEu), output, fluidOutput, recipeId + "_ct");
			} else {
				CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > "
						+ "Eu amount must be greater then 0!!");
			}
		}

		@Override
		public String describe() {
			return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s, %s] to %s", Arrays.deepToString(this.input), this.totalEu, Arrays.deepToString(this.output), Arrays.deepToString(fluidOutput), GTTileCentrifuge.RECIPE_LIST);
		}
	}
}
