package gtclassic.util;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipes {
	
	public static void init() {
        GTRecipes.initSmeltingRecipes();
        GTRecipes.initShapelessRecipes();
        GTRecipes.initShapedRecipes();
     }
	
	public static void initSmeltingRecipes() {
		//ore smelting recipe
		GameRegistry.addSmelting(GTBlocks.sandIron, new ItemStack(Items.IRON_NUGGET, 3), 0.1F);
	}
	
	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	public static void initShapelessRecipes () {
		
	}
		
	
	public static void initShapedRecipes () {
		
	}
	

}
