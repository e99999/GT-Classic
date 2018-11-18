package gtclassic.util;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipes {
	
	public static void init() {
        GTRecipes.initSmeltingRecipes();
        GTRecipes.initShapelessRecipes();
        GTRecipes.initShapedRecipes();
        GTRecipes.initMachineRecipes();
     }
	
	public static void initSmeltingRecipes() {
		//ore smelting recipe
		GameRegistry.addSmelting(GTBlocks.sandIron, new ItemStack(Items.IRON_NUGGET, 3), 0.1F);
	}
	
	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	
	public static void initShapelessRecipes () {
	}
		
	public static void initShapedRecipes () {
		
		recipes.addRecipe(new ItemStack(GTItems.hammerIron, 1),
				new Object[]{"II ", "IIS", "II ", 'I', "ingotIron",'S', Items.STICK,});
		
		recipes.addRecipe(Ic2Items.energyCrystal.copy(),
				new Object[]{"RRR", "RDR", "RRR", 'D', "gemRuby", 'R', "dustRedstone"});
		
		recipes.addRecipe(Ic2Items.lapotronCrystal.copy(), 
				new Object[]{"LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', Ic2Items.electricCircuit.copy(), 'L', "gemLapis"});
		
		
	}
	
	public static void initMachineRecipes () {
		TileEntityCompressor.addRecipe("dustRuby", 1, new ItemStack(GTItems.ruby), 0.1F);
		TileEntityCompressor.addRecipe("gemRuby", 9, new ItemStack(GTBlocks.rubyBlock), 0.1F);
		TileEntityCompressor.addRecipe("dustSapphire", 1, new ItemStack(GTItems.sapphire), 0.1F);
		TileEntityCompressor.addRecipe("gemSapphire", 9, new ItemStack(GTBlocks.sapphireBlock), 0.1F);
		
		TileEntityMacerator.addRecipe("oreRuby", 1, StackUtil.copyWithSize(new ItemStack(GTItems.ruby), 2), 0.3F);
		TileEntityMacerator.addRecipe("blockRuby", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustRuby), 9), 0.1F);
		TileEntityMacerator.addRecipe("oreSapphire", 1, StackUtil.copyWithSize(new ItemStack(GTItems.sapphire), 2), 0.3F);
		TileEntityMacerator.addRecipe("blockSapphire", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustSapphire), 9), 0.1F);
		
		TileEntityMacerator.addRecipe("oreBauxite", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustBauxite), 4), 0.1F);
		TileEntityMacerator.addRecipe("oreIridium", 1, StackUtil.copyWithSize(Ic2Items.iridiumOre, 2), 1.0F);
		
		
	}

}
