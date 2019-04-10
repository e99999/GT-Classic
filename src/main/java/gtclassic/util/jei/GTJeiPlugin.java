package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.container.GTContainerWorkbench;
import gtclassic.gui.GTGuiMachine;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.gui.GTGuiMachine.GTIndustrialCentrifugeGui;
import gtclassic.recipe.GTRecipeCauldron;
import gtclassic.recipe.GTRecipeProcessing;
import gtclassic.tile.GTTileElectricSmelter;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileIndustrialElectrolyzer;
import gtclassic.tile.GTTileMultiArcFurnace;
import gtclassic.tile.GTTileMultiBlastFurnace;
import gtclassic.tile.GTTileMultiFusionComputer;
import gtclassic.tile.GTTileMultiIndustrialProcessor;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

			// Electrolyzer
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new,
					"gt.electrolyzer");
			registry.addRecipes(GTTileIndustrialElectrolyzer.RECIPE_LIST.getRecipeList(), "gt.electrolyzer");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.industrialElectrolyzer), "gt.electrolyzer");
			registry.addRecipeClickArea(GTGuiMachine.GTIndustrialElectrolyzerGui.class, 72, 34, 30, 16,
					"gt.electrolyzer");

			// Processor
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new,
					"gt.processor");
			registry.addRecipes(GTTileMultiIndustrialProcessor.RECIPE_LIST.getRecipeList(), "gt.processor");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.industrialProcessor), "gt.processor");
			registry.addRecipeClickArea(GTGuiMachine.GTIndustrialProcessorGui.class, 50, 29, 20, 10, "gt.processor");

			// Fusion
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, "fusion");
			registry.addRecipes(GTTileMultiFusionComputer.RECIPE_LIST.getRecipeList(), "fusion");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.fusionComputer), "fusion");
			registry.addRecipeClickArea(GTFusionComputerGui.class, 69, 34, 25, 17, "fusion");

			// Electric Smelter
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new,
					"electricsmelter");
			registry.addRecipes(GTTileElectricSmelter.RECIPE_LIST.getRecipeList(), "electricsmelter");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.electricSmelter), "electricsmelter");
			registry.addRecipeClickArea(GTGuiMachine.GTElectricSmelterGui.class, 78, 25, 20, 16, "electricsmelter");

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

			// Washing
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, "washing");
			registry.addRecipes(GTRecipeCauldron.RECIPE_LIST.getRecipeList(), "washing");
			registry.addRecipeCatalyst(new ItemStack(Items.CAULDRON), "washing");

			IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
			recipeTransferRegistry.addRecipeTransferHandler(GTContainerWorkbench.class,
					VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 52);
		}
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new GTJeiCentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(),
				"gt.electrolyzer", GTBlocks.industrialElectrolyzer));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(),
				"gt.processor", GTBlocks.industrialProcessor));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "fusion",
				GTBlocks.fusionComputer));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(),
				"electricsmelter", GTBlocks.electricSmelter));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "arcfurnace",
				GTBlocks.arcFurnace));

		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(),
				"blastfurnace", GTBlocks.blastFurnace));

		registry.addRecipeCategories(
				new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "bloomery", GTBlocks.bloomery));

		registry.addRecipeCategories(
				new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "washing", Blocks.WATER));
	}
}
