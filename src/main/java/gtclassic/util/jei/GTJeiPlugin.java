package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.GTItems;
import gtclassic.container.GTContainerWorkbench;
import gtclassic.fluid.GTFluidBlockDryable;
import gtclassic.ore.GTOreFlag;
import gtclassic.ore.GTOreStone;
import gtclassic.recipe.GTRecipeCauldron;
import gtclassic.recipe.GTRecipeProcessing;
import gtclassic.util.jei.category.GTJeiByproductsCategory;
import gtclassic.util.jei.category.GTJeiDryingCategory;
import gtclassic.util.jei.category.GTJeiInteractionCategory;
import gtclassic.util.jei.category.GTJeiMultiRecipeCategory;
import gtclassic.util.jei.wrapper.GTJeiByproductsWrapper;
import gtclassic.util.jei.wrapper.GTJeiDryingWrapper;
import gtclassic.util.jei.wrapper.GTJeiInteractionWrapper;
import gtclassic.util.jei.wrapper.GTJeiMultiRecipeWrapper;
import gtclassic.util.recipe.GTMultiInputRecipeList;
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
			for (GTJeiRegistry entry : GTJeiRegistry.values()) {
				wrapperUtil(registry, entry.getRecipeList(), entry.getCatalyst(), entry.getGuiClass(), entry.getClickX(), entry.getClickY(), entry.getSizeX(), entry.getSizeY());
			}
			// Vanilla crafting
			registry.addRecipeCatalyst(new ItemStack(GTItems.craftingTablet), new String[] { "minecraft.crafting" });
			// Washing
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, "gt.washing");
			registry.addRecipes(GTRecipeCauldron.RECIPE_LIST.getRecipeList(), "gt.washing");
			registry.addRecipeCatalyst(new ItemStack(Items.CAULDRON), "gt.washing");
			// Byproducts
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiByproductsWrapper::new, "gt.byproducts");
			registry.addRecipes(GTRecipeProcessing.BYPRODUCT_LIST.getRecipeList(), "gt.byproducts");
			registry.addRecipeCatalyst(new ItemStack(Blocks.IRON_ORE), "gt.byproducts");
			// World Interaction
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiInteractionWrapper::new, "gt.interaction");
			registry.addRecipes(GTRecipeProcessing.INTERACTION_LIST.getRecipeList(), "gt.interaction");
			registry.addRecipeCatalyst(new ItemStack(Blocks.GRASS), "gt.interaction");
			// Drying
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiDryingWrapper::new, "gt.drying");
			registry.addRecipes(GTFluidBlockDryable.DRYING_RECIPE_LIST.getRecipeList(), "gt.drying");
			registry.addRecipeCatalyst(new ItemStack(Blocks.HARDENED_CLAY), "gt.drying");
			registry.addRecipeCatalyst(new ItemStack(Blocks.STAINED_HARDENED_CLAY), "gt.drying");
			registry.addRecipeCatalyst(new ItemStack(Blocks.CONCRETE), "gt.drying");
			IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
			recipeTransferRegistry.addRecipeTransferHandler(GTContainerWorkbench.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 52);// this
																																			// is
																																			// what
																																			// fucks
																																			// up
																																			// shift
																																			// clicking
																																			// fixing
			IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
			for (Block block : Block.REGISTRY) {
				if (block instanceof GTOreStone) {
					if (((GTOreStone) block).getOreFlag().equals(GTOreFlag.BEDROCK)) {
						blacklist.addIngredientToBlacklist(new ItemStack(block));
					}
				}
			}
		}
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		for (GTJeiRegistry entry : GTJeiRegistry.values()) {
			categoryUtil(registry, entry.getRecipeList(), entry.getCatalyst());
		}
		// washing
		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "gt.washing", Blocks.WATER));
		// byproducts
		registry.addRecipeCategories(new GTJeiByproductsCategory(registry.getJeiHelpers().getGuiHelper(), "gt.byproducts", Blocks.IRON_ORE));
		// world interaction
		registry.addRecipeCategories(new GTJeiInteractionCategory(registry.getJeiHelpers().getGuiHelper(), "gt.interaction", Blocks.GRASS));
		// drying
		registry.addRecipeCategories(new GTJeiDryingCategory(registry.getJeiHelpers().getGuiHelper(), "gt.drying", Blocks.HARDENED_CLAY));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void wrapperUtil(@Nonnull IModRegistry registry, GTMultiInputRecipeList list, Block catalyst,
			Class gui, int clickX, int clickY, int sizeX, int sizeY) {
		String recipeList = list.getCategory();
		registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, recipeList);
		registry.addRecipes(list.getRecipeList(), recipeList);
		registry.addRecipeCatalyst(new ItemStack(catalyst), recipeList);
		if (gui != null) {
			registry.addRecipeClickArea(gui, clickX, clickY, sizeX, sizeY, recipeList);
		}
	}

	public static void categoryUtil(IRecipeCategoryRegistration registry, GTMultiInputRecipeList list, Block catalyst) {
		registry.addRecipeCategories(new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), list.getCategory(), catalyst));
	}
}
