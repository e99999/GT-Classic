package gtclassic.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.tile.GTTileMagicEnergyConverter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Locale;

@ZenClass("mods.gtclassic.MagicEnergyConverter")
@ZenRegister
public class GTMagicEnergyConverterSupport {

	@ZenMethod
	public static void addRecipe(IItemStack input, @Optional(valueLong = 8000L) int totalGeneratedEu) {
		GTCraftTweakerActions.apply(new MagicEnergyConverterRecipeAction(CraftTweakerMC.getItemStack(input), totalGeneratedEu));
	}

	@ZenMethod
	public static void addRecipe(ILiquidStack input) {
		GTCraftTweakerActions.apply(new MagicEnergyConverterRecipeAction(CraftTweakerMC.getLiquidStack(input).getFluid()));
	}

	private static final class MagicEnergyConverterRecipeAction implements IAction {

		private final ItemStack itemInput;
		private final Fluid fluidInput;
		private final int totalGeneratedEu;
		private boolean fluid;

		MagicEnergyConverterRecipeAction(ItemStack input, int totalGeneratedEu) {
			this.itemInput = input;
			this.fluidInput = null;
			this.totalGeneratedEu = totalGeneratedEu;
			this.fluid = false;
		}

		MagicEnergyConverterRecipeAction(Fluid fluidInput) {
			this.itemInput = null;
			this.totalGeneratedEu = 0;
			this.fluidInput = fluidInput;
			this.fluid = true;
		}

		@Override
		public void apply() {
			if (fluid){
				GTTileMagicEnergyConverter.addRecipe(fluidInput);
			} else {
				GTTileMagicEnergyConverter.addRecipe(itemInput, totalGeneratedEu);
			}
		}

		@Override
		public String describe() {
			if (fluid) {
				return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", this.fluidInput, 8000, GTTileMagicEnergyConverter.RECIPE_LIST);
			} else {
				return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", this.itemInput, totalGeneratedEu, GTTileMagicEnergyConverter.RECIPE_LIST);
			}
		}
	}
}
