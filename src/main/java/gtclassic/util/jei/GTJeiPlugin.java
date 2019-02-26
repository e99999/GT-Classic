package gtclassic.util.jei;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.gui.GTGuiMachine;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.gui.GTGuiMachine.GTIndustrialCentrifugeGui;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileFusionComputer;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.util.jei.category.GTJeiAlloySmelterCategory;
import gtclassic.util.jei.wrapper.GTJeiMultiRecipeWrapper;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.jeiIntigration.SubModul;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class GTJeiPlugin implements IModPlugin {

	@Override
	public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
		// empty method for construction
	}

	@Override
	public void register(@Nonnull IModRegistry registry) {

		if (SubModul.load) {

			registry.addRecipeCatalyst(new ItemStack(GTBlocks.industrialCentrifuge), new String[] { "centrifuge" });
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.fusionComputer), new String[] { "fusion" });
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.workBenchLV), new String[] { "minecraft.crafting" });
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.autoCrafter), new String[] { "minecraft.crafting" });
			registry.addRecipeCatalyst(new ItemStack(GTItems.craftingTablet), new String[] { "minecraft.crafting" });

			registry.addRecipeClickArea(GTIndustrialCentrifugeGui.class, 62, 29, 10, 10, "centrifuge");
			registry.addRecipeClickArea(GTFusionComputerGui.class, 111, 35, 25, 17, "fusion");

			registry.handleRecipes(RecipeEntry.class, new IRecipeWrapperFactory<RecipeEntry>() {
				@Override
				public IRecipeWrapper getRecipeWrapper(RecipeEntry var1) {
					return new GTJeiCentrifugeWrapper(var1);
				}
			}, "centrifuge");
			registry.addRecipes(GTTileIndustrialCentrifuge.RECIPE_LIST.getRecipeMap(), "centrifuge");

			registry.handleRecipes(RecipeEntry.class, new IRecipeWrapperFactory<RecipeEntry>() {
				@Override
				public IRecipeWrapper getRecipeWrapper(RecipeEntry var1) {
					return new GTJeiFusionWrapper(var1);
				}
			}, "fusion");
			registry.addRecipes(GTTileFusionComputer.RECIPE_LIST.getRecipeMap(), "fusion");


			registry.addRecipeClickArea(GTGuiMachine.GTAlloySmelterGui.class, 62, 29, 10, 10, "alloysmelter");
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, "alloysmelter");
			registry.addRecipes(GTTileAlloySmelter.RECIPE_LIST.getRecipeList(), "alloysmelter");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.alloySmelter), "alloysmelter");
		}
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new GTJeiAlloySmelterCategory(registry.getJeiHelpers().getGuiHelper(), GTBlocks.alloySmelter, "alloysmelter"));


		registry.addRecipeCategories(new GTJeiCentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new GTJeiFusionCategory(registry.getJeiHelpers().getGuiHelper()));
	}
}
