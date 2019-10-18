package gtclassic.util.crafttweaker;

import java.util.Locale;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.tile.GTTileUUMAssembler;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtclassic.UUMAssembler")
@ZenRegister
public class GTUUMAssemblerSupport {

	@ZenMethod
	public static void addRecipe(int neededUU, IItemStack output) {
		GTCraftTweakerActions.apply(new UUMAssemblerRecipeAction(neededUU, CraftTweakerMC.getItemStack(output)));
	}

	private static final class UUMAssemblerRecipeAction implements IAction {

		private final ItemStack output;
		private final int neededUU;

		UUMAssemblerRecipeAction(int neededUU, ItemStack output) {
			this.neededUU = neededUU;
			this.output = output;
		}

		@Override
		public void apply() {
			if (neededUU > 0) {
				GTTileUUMAssembler.addUUMAssemblerValue(neededUU, output);
			} else {
				CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > "
						+ "Amount of uumatter required must be greater then 0!!");
			}
		}

		@Override
		public String describe() {
			return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s] to %s", this.neededUU, output, GTTileUUMAssembler.RECIPE_LIST);
		}
	}
}
