package gtclassic.util;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipes {
	
	public static void init() {
        GTRecipes.initSmeltingRecipes();
        GTRecipes.initShaplessRecipes();
        GTRecipes.initShapedRecipes();
     }
	
	public static void initSmeltingRecipes() {
		//ore smelting recipes
		GameRegistry.addSmelting(GTBlocks.pyriteOre, new ItemStack(Items.IRON_INGOT, 1), 7.0F);
		GameRegistry.addSmelting(GTBlocks.tungstateOre, new ItemStack(GTItems.dustTungsten, 1), 1.0F);
		GameRegistry.addSmelting(GTBlocks.sheldoniteOre, new ItemStack(GTItems.ingotPlatinum, 1), 1.0F);
		GameRegistry.addSmelting(GTBlocks.sandIron, new ItemStack(Items.IRON_NUGGET, 3), 7.0F);

	}
	
	public static void initShaplessRecipes () {
		ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
		
		recipes.addShapelessRecipe(new ItemStack(GTItems.dustZinc, 1),
				new Object[]{GTItems.mortarFlint, GTItems.ingotZinc});
	}
	
	public static void initShapedRecipes () {
		ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
		
		recipes.addRecipe(new ItemStack(GTItems.mortarFlint, 1),
				new Object[]{" F ", 
							 "BFB", 
							 "BBB", 
							 'F', Items.FLINT,
							 'B', Blocks.PLANKS,});
		
		recipes.addRecipe(new ItemStack(GTItems.mortarIron, 1),
				new Object[]{" I ", 
							 "BIB", 
							 "BBB", 
							 'I', Items.IRON_INGOT,
							 'B', Blocks.STONE,});
		
		recipes.addRecipe(new ItemStack(GTItems.hammerIron, 1),
				new Object[]{"II ", 
							 "IIS", 
							 "II ", 
							 'I', Items.IRON_INGOT,
							 'S', Items.STICK,});
		
		
		recipes.addRecipe(new ItemStack(GTBlocks.zincBlock, 1),
				new Object[]{"III", 
							 "III", 
							 "III", 
							 'I', GTItems.ingotZinc});
	}

}
