package gtclassic.util;


import gtclassic.blocks.resources.GTBlockMetal;
import gtclassic.items.resources.GTItemIngot;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
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
