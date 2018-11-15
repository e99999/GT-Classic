package gtclassic.util;


import gtclassic.blocks.resources.GTBlockMetals;
import gtclassic.items.resources.GTItemMaterials;
import gtclassic.items.resources.GTItemNuggets;
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
        GTRecipes.initMaterialRecipes();
     }
	
	public static void initSmeltingRecipes() {
		//ore smelting recipes
		GameRegistry.addSmelting(GTBlocks.pyriteOre, new ItemStack(Items.IRON_INGOT, 1), 0.1F);
		GameRegistry.addSmelting(GTBlocks.tungstateOre, new ItemStack(GTItems.dustTungsten, 1), 0.3F);
		GameRegistry.addSmelting(GTBlocks.sheldoniteOre, new ItemStack(GTItems.ingotPlatinum, 1), 0.6F);
		GameRegistry.addSmelting(GTBlocks.sandIron, new ItemStack(Items.IRON_NUGGET, 3), 0.1F);
		
		//dust to ingots
		GameRegistry.addSmelting(GTItems.dustBrass, new ItemStack(GTItems.ingotBrass, 1), 0.2F);

	}
	
	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	
	
	//TODO Use ore dictionary instead of internal classes
	public static void metalutil(GTItemNuggets nugget, GTItemMaterials ingot, GTBlockMetals block) {
		recipes.addRecipe(new ItemStack(block, 1),
				new Object[]{"III", "III", "III", 'I', ingot});
		recipes.addShapelessRecipe(new ItemStack(ingot, 9),
				new Object[]{block});
		recipes.addRecipe(new ItemStack(ingot, 1),
				new Object[]{"III", "III", "III", 'I', nugget});
		recipes.addShapelessRecipe(new ItemStack(nugget, 9),
				new Object[]{ingot});
		
	}
	
	public static void initMaterialRecipes() {
		//testing metail util method
		GTRecipes.metalutil(GTItems.nuggetBrass, GTItems.ingotBrass, GTBlocks.brassBlock);
		GTRecipes.metalutil(GTItems.nuggetPlatinum, GTItems.ingotPlatinum, GTBlocks.platinumBlock);
	}

	public static void initShapelessRecipes () {
		
		recipes.addShapelessRecipe(new ItemStack(GTBlocks.blockCabinet, 1),
				new Object[]{Ic2Items.machine, Blocks.CHEST});
		
		recipes.addShapelessRecipe(new ItemStack(GTItems.dustBrass, 4),
				new Object[]{Ic2Items.copperDust, Ic2Items.copperDust, Ic2Items.copperDust, GTItems.dustZinc});
		
		//flint mortar
		recipes.addShapelessRecipe(Ic2Items.coalDust,
				new Object[]{GTItems.mortarFlint, Items.COAL});

		recipes.addShapelessRecipe(Ic2Items.goldDust,
				new Object[]{GTItems.mortarFlint, Items.GOLD_INGOT});
		
		recipes.addShapelessRecipe(Ic2Items.clayDust,
				new Object[]{GTItems.mortarFlint, Blocks.CLAY});
		
		recipes.addShapelessRecipe(Ic2Items.copperDust,
				new Object[]{GTItems.mortarFlint, Ic2Items.copperIngot});
		
		recipes.addShapelessRecipe(Ic2Items.tinDust,
				new Object[]{GTItems.mortarFlint, Ic2Items.tinIngot});
		
		recipes.addShapelessRecipe(Ic2Items.silverDust,
				new Object[]{GTItems.mortarFlint, Ic2Items.silverIngot});
		
		recipes.addShapelessRecipe(new ItemStack(GTItems.dustElectrum, 1),
				new Object[]{GTItems.mortarFlint, GTItems.ingotElectrum});
		
		recipes.addShapelessRecipe(Ic2Items.flour,
				new Object[]{GTItems.mortarFlint, Items.WHEAT});
		
		//iron mortar
		recipes.addShapelessRecipe(Ic2Items.coalDust,
				new Object[]{GTItems.mortarIron, Items.COAL});
		
		recipes.addShapelessRecipe(Ic2Items.goldDust,
				new Object[]{GTItems.mortarIron, Items.GOLD_INGOT});
		
		recipes.addShapelessRecipe(Ic2Items.clayDust,
				new Object[]{GTItems.mortarIron, Blocks.CLAY});
		
		recipes.addShapelessRecipe(Ic2Items.copperDust,
				new Object[]{GTItems.mortarIron, Ic2Items.copperIngot});
		
		recipes.addShapelessRecipe(Ic2Items.tinDust,
				new Object[]{GTItems.mortarIron, Ic2Items.tinIngot});
		
		recipes.addShapelessRecipe(Ic2Items.silverDust,
				new Object[]{GTItems.mortarIron, Ic2Items.silverIngot});
		
		recipes.addShapelessRecipe(new ItemStack(GTItems.dustElectrum, 1),
				new Object[]{GTItems.mortarIron, GTItems.ingotElectrum});
		
		recipes.addShapelessRecipe(Ic2Items.flour,
				new Object[]{GTItems.mortarIron, Items.WHEAT});
		
		recipes.addShapelessRecipe(new ItemStack(Items.FLINT, 1),
				new Object[]{GTItems.mortarIron, Blocks.GRAVEL});
		
		recipes.addShapelessRecipe(Ic2Items.ironDust,
				new Object[]{GTItems.mortarIron, Items.IRON_INGOT});
		
		recipes.addShapelessRecipe(Ic2Items.ironDust,
				new Object[]{GTItems.mortarIron, Ic2Items.refinedIronIngot});
		
		recipes.addShapelessRecipe(Ic2Items.bronzeDust,
				new Object[]{GTItems.mortarIron, Ic2Items.bronzeIngot});
		
		recipes.addShapelessRecipe(new ItemStack(GTItems.dustBrass, 1),
				new Object[]{GTItems.mortarIron, GTItems.ingotBrass});
		

	}
	
	public static void initShapedRecipes () {
		
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
		
	}

}
