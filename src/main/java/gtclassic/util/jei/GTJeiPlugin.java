package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.container.GTContainerWorktable;
import gtclassic.gui.GTGuiMachine.GTMagicEnergyConverterGui;
import gtclassic.gui.GTGuiMachine.GTMatterFabricatorGui;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileMagicEnergyConverter;
import gtclassic.tile.GTTileMatterFabricator;
import gtclassic.util.jei.category.GTJeiMagicFuelCategory;
import gtclassic.util.jei.category.GTJeiMultiRecipeCategory;
import gtclassic.util.jei.category.GTJeiUUAmplifierCategory;
import gtclassic.util.jei.wrapper.GTJeiMagicFuelWrapper;
import gtclassic.util.jei.wrapper.GTJeiMultiRecipeWrapper;
import gtclassic.util.jei.wrapper.GTJeiUUAmplifierWrapper;
import gtclassic.util.recipe.GTRecipeMultiInputList;
import gtclassic.util.recipe.GTRecipeMultiInputList.MultiRecipe;
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
			for (GTJeiEntry entry : GTJeiHandler.registry) {
				wrapperUtil(registry, entry.getRecipeList(), entry.getCatalyst(), entry.getGuiClass(), entry.getClickX(), entry.getClickY(), entry.getSizeX(), entry.getSizeY());
			}
			// More Vanilla Crafting
			IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
			recipeTransferRegistry.addRecipeTransferHandler(GTContainerWorktable.class, VanillaRecipeCategoryUid.CRAFTING, 17, 25, 10, 52);
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.tileWorktable), new String[] { "minecraft.crafting" });
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
		for (GTJeiEntry entry : GTJeiHandler.registry) {
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
