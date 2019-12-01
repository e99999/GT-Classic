package gtclassic.common.jei;

import javax.annotation.Nonnull;

import gtclassic.api.jei.GTJeiEntry;
import gtclassic.api.jei.GTJeiHandler;
import gtclassic.api.jei.GTJeiMultiRecipeCategory;
import gtclassic.api.jei.GTJeiMultiRecipeWrapper;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTItems;
import gtclassic.common.container.GTContainerWorktable;
import gtclassic.common.gui.GTGuiMachine.GTMagicEnergyConverterGui;
import gtclassic.common.gui.GTGuiMachine.GTMatterFabricatorGui;
import gtclassic.common.tile.GTTileMagicEnergyConverter;
import gtclassic.common.tile.GTTileMatterFabricator;
import ic2.jeiIntigration.SubModul;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.block.Block;
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
			for (GTJeiEntry entry : GTJeiHandler.getRegistry()) {
				wrapperUtil(registry, entry.getRecipeList(), entry.getCatalyst(), entry.getGuiClass(), entry.getClickX(), entry.getClickY(), entry.getSizeX(), entry.getSizeY());
			}
			// More Vanilla Crafting
			IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
			recipeTransferRegistry.addRecipeTransferHandler(GTContainerWorktable.class, VanillaRecipeCategoryUid.CRAFTING, 17, 25, 10, 52);
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.tileWorktable), new String[] { "minecraft.crafting" });
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.tileAutocrafter), new String[] { "minecraft.crafting" });
			// Amplifier
			registry.handleRecipes(MultiRecipe.class, GTJeiUUAmplifierWrapper::new, "gt.uuamplifier");
			registry.addRecipes(GTTileMatterFabricator.RECIPE_LIST.getRecipeList(), "gt.uuamplifier");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.tileFabricator), "gt.uuamplifier");
			registry.addRecipeClickArea(GTMatterFabricatorGui.class, 105, 34, 62, 22, "gt.uuamplifier");
			// Magic Fuel
			registry.handleRecipes(MultiRecipe.class, GTJeiMagicFuelWrapper::new, "gt.magicfuels");
			registry.addRecipes(GTTileMagicEnergyConverter.RECIPE_LIST.getRecipeList(), "gt.magicfuels");
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.tileMagicEnergyConverter), "gt.magicfuels");
			registry.addRecipeClickArea(GTMagicEnergyConverterGui.class, 78, 35, 16, 17, "gt.magicfuels");
			// Blacklist
			IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
			blacklist.addIngredientToBlacklist(GTMaterialGen.get(GTBlocks.lightSource));
			blacklist.addIngredientToBlacklist(GTMaterialGen.get(GTItems.orbDataStorage));
		}
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		for (GTJeiEntry entry : GTJeiHandler.getRegistry()) {
			categoryUtil(registry, entry.getRecipeList(), entry.getCatalyst());
		}
		// amplifier
		registry.addRecipeCategories(new GTJeiUUAmplifierCategory(registry.getJeiHelpers().getGuiHelper(), "gt.uuamplifier", GTBlocks.tileFabricator));
		// magic fuels
		registry.addRecipeCategories(new GTJeiMagicFuelCategory(registry.getJeiHelpers().getGuiHelper(), "gt.magicfuels", GTBlocks.tileMagicEnergyConverter));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void wrapperUtil(@Nonnull IModRegistry registry, GTRecipeMultiInputList list, Block catalyst,
			Class gui, int clickX, int clickY, int sizeX, int sizeY) {
		String recipeList = list.getCategory();
		registry.handleRecipes(GTRecipeMultiInputList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, recipeList);
		registry.addRecipes(list.getRecipeList(), recipeList);
		registry.addRecipeCatalyst(new ItemStack(catalyst), recipeList);
		if (gui != null) {
			registry.addRecipeClickArea(gui, clickX, clickY, sizeX, sizeY, recipeList);
		}
	}

	public static void categoryUtil(IRecipeCategoryRegistration registry, GTRecipeMultiInputList list, Block catalyst) {
		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), list.getCategory(), catalyst));
	}
}
