package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.container.GTContainerWorkbench;
import gtclassic.gui.GTGuiMachine;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.gui.GTGuiMachine.GTIndustrialCentrifugeGui;
import gtclassic.recipe.GTRecipeProcessing;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileMultiArcFurnace;
import gtclassic.tile.GTTileMultiAssemblyLine;
import gtclassic.tile.GTTileMultiBlastFurnace;
import gtclassic.tile.GTTileMultiFusionComputer;
import gtclassic.util.jei.category.GTJeiMultiRecipeCategory;
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
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class GTJeiPlugin implements IModPlugin {

	@Override
	public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
		// empty method for construction
	}

	@Override
	public void register(@Nonnull IModRegistry registry) {

		if (SubModul.load) {
			// registry.addRecipeCatalyst(new ItemStack(GTBlocks.workBenchLV), new String[]
			// { "minecraft.crafting" });
			registry.addRecipeCatalyst(new ItemStack(GTItems.craftingTablet), new String[] { "minecraft.crafting" });

			// Centrifuge - to be refactored
			registry.handleRecipes(RecipeEntry.class, new IRecipeWrapperFactory<RecipeEntry>() {
				@Override
				public IRecipeWrapper getRecipeWrapper(RecipeEntry var1) {
					return new GTJeiCentrifugeWrapper(var1);
				}
			}, "centrifuge");
			registry.addRecipes(GTTileIndustrialCentrifuge.RECIPE_LIST.getRecipeMap(), "centrifuge");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.industrialCentrifuge), new String[] { "centrifuge" });
			registry.addRecipeClickArea(GTIndustrialCentrifugeGui.class, 62, 29, 10, 10, "centrifuge");

			// Fusion
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, "fusion");
			registry.addRecipes(GTTileMultiFusionComputer.RECIPE_LIST.getRecipeList(), "fusion");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.fusionComputer), "fusion");
			registry.addRecipeClickArea(GTFusionComputerGui.class, 111, 35, 25, 17, "fusion");

			// Alloy Smelter
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new,
					"alloysmelter");
			registry.addRecipes(GTTileAlloySmelter.RECIPE_LIST.getRecipeList(), "alloysmelter");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.alloySmelter), "alloysmelter");
			registry.addRecipeClickArea(GTGuiMachine.GTAlloySmelterGui.class, 78, 25, 20, 16, "alloysmelter");

			// Assembly Line
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new,
					"assemblyline");
			registry.addRecipes(GTTileMultiAssemblyLine.RECIPE_LIST.getRecipeList(), "assemblyline");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.assLine), "assemblyline");
			registry.addRecipeClickArea(GTGuiMachine.GTAssemblyLineGui.class, 88, 25, 20, 16, "assemblyline");

			// Arc Furnace
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new,
					"arcfurnace");
			registry.addRecipes(GTTileMultiArcFurnace.RECIPE_LIST.getRecipeList(), "arcfurnace");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.arcFurnace), "arcfurnace");
			registry.addRecipeClickArea(GTGuiMachine.GTArcFurnaceGui.class, 58, 28, 20, 11, "arcfurnace");

			// Blast Furnace
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new,
					"blastfurnace");
			registry.addRecipes(GTTileMultiBlastFurnace.RECIPE_LIST.getRecipeList(), "blastfurnace");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.blastFurnace), "blastfurnace");
			registry.addRecipeClickArea(GTGuiMachine.GTBlastFurnaceGui.class, 58, 28, 20, 11, "blastfurnace");

			// Bloomery
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, "bloomery");
			registry.addRecipes(GTRecipeProcessing.BLOOM_RECIPE_LIST.getRecipeList(), "bloomery");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.bloomery), "bloomery");
			registry.addRecipeClickArea(GTGuiMachine.GTBloomeryGui.class, 79, 18, 20, 11, "bloomery");

			IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
			recipeTransferRegistry.addRecipeTransferHandler(GTContainerWorkbench.class,
					VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 52);
		}
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new GTJeiCentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "fusion",
				GTBlocks.fusionComputer));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(),
				"alloysmelter", GTBlocks.alloySmelter));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(),
				"assemblyline", GTBlocks.assLine));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "arcfurnace",
				GTBlocks.arcFurnace));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(),
				"blastfurnace", GTBlocks.blastFurnace));

		registry.addRecipeCategories(
				new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "bloomery", GTBlocks.bloomery));
	}
}
