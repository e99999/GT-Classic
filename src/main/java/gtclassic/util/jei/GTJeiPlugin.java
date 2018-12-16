package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.util.GTBlocks;
import gtclassic.util.GTItems;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.core.platform.registry.Ic2Items;
import ic2.jeiIntigration.SubModul;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
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
			
			IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
			
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.industrialCentrifuge), new String[] { "centrifuge" });
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.fusionComputer), new String[] { "fusion" });
			// TODO remove the ic2 worktable once Speiger patches it on his end
			registry.addRecipeCatalyst(Ic2Items.industrialWorktable, new String[] { "minecraft.crafting" });
			registry.addRecipeCatalyst(new ItemStack(GTBlocks.autoCrafter), new String[] { "minecraft.crafting" });
			registry.addRecipeCatalyst(new ItemStack(GTItems.craftingTablet), new String[] { "minecraft.crafting" });

			registry.handleRecipes(RecipeEntry.class, new IRecipeWrapperFactory<RecipeEntry>() {
				@Override
				public IRecipeWrapper getRecipeWrapper(RecipeEntry var1) {
					return new GTJeiCentrifugeWrapper(var1);
				}
			}, "centrifuge");
			registry.addRecipes(GTTileEntityIndustrialCentrifuge.RECIPE_LIST.getRecipeMap(), "centrifuge");

			registry.handleRecipes(RecipeEntry.class, new IRecipeWrapperFactory<RecipeEntry>() {
				@Override
				public IRecipeWrapper getRecipeWrapper(RecipeEntry var1) {
					return new GTJeiFusionWrapper(var1);
				}
			}, "fusion");
			registry.addRecipes(GTTileEntityFusionComputer.RECIPE_LIST.getRecipeMap(), "fusion");
			
			blacklist.addIngredientToBlacklist(new ItemStack(GTBlocks.toxicPortal));
		}
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new GTJeiCentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new GTJeiFusionCategory(registry.getJeiHelpers().getGuiHelper()));
	}

}
