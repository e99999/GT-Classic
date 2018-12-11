package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.tileentity.GTTileEntityFusionComputer;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.util.GTBlocks;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
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

		registry.addRecipeCatalyst(new ItemStack(GTBlocks.industrialCentrifuge), new String[] { "centrifuge" });
		registry.addRecipeCatalyst(new ItemStack(GTBlocks.fusionComputer), new String[] { "fusion" });

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
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new GTJeiCentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new GTJeiFusionCategory(registry.getJeiHelpers().getGuiHelper()));
	}

}
