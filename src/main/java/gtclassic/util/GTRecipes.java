package gtclassic.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipes {
	
	public static void init() {
        GTRecipes.initSmeltingRecipes();
     }
	
	public static void initSmeltingRecipes() {
		//ore smelting recipes
		GameRegistry.addSmelting(GTBlocks.pyriteOre, new ItemStack(Items.IRON_INGOT, 1), 7.0F);
		GameRegistry.addSmelting(GTBlocks.tungstateOre, new ItemStack(GTItems.dustTungsten, 1), 1.0F);
		GameRegistry.addSmelting(GTBlocks.sheldoniteOre, new ItemStack(GTItems.ingotPlatinum, 1), 1.0F);
		GameRegistry.addSmelting(GTBlocks.sandIron, new ItemStack(Items.IRON_NUGGET, 3), 7.0F);

	}

}
