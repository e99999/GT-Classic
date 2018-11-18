package gtclassic.util;

import gtclassic.blocks.resources.GTBlockMetal;
import gtclassic.items.resources.GTItemGem;
import gtclassic.items.resources.GTItemIngot;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.init.Blocks;
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
		
		recipes.addRecipe(new ItemStack(GTBlocks.rubyBlock, 1),
				new Object[]{"III", "III", "III", 'I', "gemRuby"});
		
		recipes.addShapelessRecipe(new ItemStack(GTItems.ruby, 9),
				new Object[]{GTBlocks.rubyBlock});
		
		recipes.addRecipe(new ItemStack(GTBlocks.sapphireBlock, 1),
				new Object[]{"III", "III", "III", 'I', "gemSapphire"});
		
		recipes.addShapelessRecipe(new ItemStack(GTItems.sapphire, 9),
				new Object[]{GTBlocks.sapphireBlock});
		
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
		TileEntityCompressor.addRecipe("dustSapphire", 1, new ItemStack(GTItems.sapphire), 0.1F);
		TileEntityMacerator.addRecipe("oreRuby", 0, StackUtil.copyWithSize(new ItemStack(GTItems.ruby), 2), 0.3F);
		TileEntityMacerator.addRecipe("oreSapphire", 0, StackUtil.copyWithSize(new ItemStack(GTItems.sapphire), 2), 0.3F);
		
		
	}

}
