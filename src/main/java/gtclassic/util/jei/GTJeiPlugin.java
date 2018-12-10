package gtclassic.util.jei;

import javax.annotation.Nonnull;

import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class GTJeiPlugin implements IModPlugin {
	
	@Override
	public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
		// empty method for construction
	}
	
	@Override
	public void register(@Nonnull IModRegistry registry) {
		
	
		// register handlers first
		// register recipes seconds
		 
		//registry.handleRecipes(RecipeEntry.class,, "centrifuge");
	}
	
	@Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
    }

}
