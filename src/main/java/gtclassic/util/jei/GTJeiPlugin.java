package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.GTItems;
import gtclassic.container.GTContainerWorkbench;
import gtclassic.ore.GTOreFlag;
import gtclassic.ore.GTOreStone;
import gtclassic.recipe.GTRecipeCauldron;
import gtclassic.util.jei.category.GTJeiMultiRecipeCategory;
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

			registry.addRecipeCatalyst(new ItemStack(GTItems.craftingTablet), new String[] { "minecraft.crafting" });

			for (GTJeiRegistry entry : GTJeiRegistry.values()) {
				wrapperUtil(registry, entry.getRecipeList(), entry.getCatalyst(), entry.getGuiClass(),
						entry.getClickX(), entry.getClickY(), entry.getSizeX(), entry.getSizeY());
			}

			// Washing
			registry.handleRecipes(GTMultiInputRecipeList.MultiRecipe.class, GTJeiMultiRecipeWrapper::new, "washing");
			registry.addRecipes(GTRecipeCauldron.RECIPE_LIST.getRecipeList(), "washing");
			registry.addRecipeCatalyst(new ItemStack(Items.CAULDRON), "washing");

			IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
			recipeTransferRegistry.addRecipeTransferHandler(GTContainerWorkbench.class,
					VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 52);

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
		registry.addRecipeCategories(
				new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "washing", Blocks.WATER));
	}

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
		registry.addRecipeCategories(
				new GTJeiMultiRecipeCategory(registry.getJeiHelpers().getGuiHelper(), list.getCategory(), catalyst));
	}
}
