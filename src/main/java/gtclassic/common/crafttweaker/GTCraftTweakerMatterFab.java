package gtclassic.common.crafttweaker;

import java.util.Locale;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import gtclassic.api.crafttweaker.GTCraftTweakerActions;
import gtclassic.common.tile.GTTileMatterFabricator;
import ic2.api.recipe.IRecipeInput;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtclassic.MatterFab")
@ZenRegister
public class GTCraftTweakerMatterFab {

	@ZenMethod
	public static void addRecipe(IIngredient input, int amplifier) {
		GTCraftTweakerActions.apply(new MatterFabRecipeAction(GTCraftTweakerActions.of(input), amplifier));
	}

	private static final class MatterFabRecipeAction implements IAction {

		private final IRecipeInput input;
		private final int amplifier;

		MatterFabRecipeAction(IRecipeInput input, int amplifier) {
			this.input = input;
			this.amplifier = amplifier;
		}

		@Override
		public void apply() {
			GTTileMatterFabricator.addAmplifier(input, amplifier);
		}

		@Override
		public String describe() {
			return String.format(Locale.ENGLISH, "Add Recipe[%s, %s] to %s", this.input, this.amplifier, GTTileMatterFabricator.RECIPE_LIST);
		}
	}
}
