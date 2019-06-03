package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipeIterators {

	public static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	public static void recipeIterators1() {
		/*
		 * The statements below iterate through the material registry to create recipes
		 * for the correct corresponding items and blocks.
		 */
		for (GTMaterial mat : GTMaterial.values()) {
			createIngotRecipe(mat);
			createGemRecipe(mat);
			createNuggetRecipe(mat);
			createBlockRecipe(mat);
		}
	}


	public static void createIngotRecipe(GTMaterial mat) {
		String nugget = "nugget" + mat.getDisplayName();
		if (mat.hasFlag(GTMaterialFlag.INGOT)) {
			// Ingot crafting recipe
			recipes.addRecipe(GTMaterialGen.getIngot(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', nugget });
			if (mat.hasFlag(GTMaterialFlag.DUST)) {
				GameRegistry.addSmelting(GTMaterialGen.getDust(mat, 1), (GTMaterialGen.getIngot(mat, 1)), 0.1F);
			}
		}
	}

	public static void createGemRecipe(GTMaterial mat) {
		String dust = "dust" + mat.getDisplayName();
		String gem = "gem" + mat.getDisplayName();
		String block = "block" + mat.getDisplayName();
		if (mat.hasFlag(GTMaterialFlag.GEM)) {
			// Dust to gem
			TileEntityCompressor.addRecipe(dust, 1, GTMaterialGen.getGem(mat, 1), 0.0F);
			// Inverse
			TileEntityMacerator.addRecipe(gem, 1, GTMaterialGen.getDust(mat, 1), 0.0F);
			if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
				// Block and gem related logic
				recipes.addShapelessRecipe(GTMaterialGen.getGem(mat, 9), new Object[] { block });
				TileEntityCompressor.addRecipe(gem, 9, GTMaterialGen.getMaterialBlock(mat, 1), 0.0F);
				TileEntityMacerator.addRecipe(block, 1, GTMaterialGen.getDust(mat, 9), 0.0F);
				recipes.addRecipe(GTMaterialGen.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X',
						gem });
			}
		}
	}

	public static void createNuggetRecipe(GTMaterial mat) {
		String ingot = "ingot" + mat.getDisplayName();
		if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
			recipes.addShapelessRecipe(GTMaterialGen.getNugget(mat, 9), new Object[] { ingot });
		}
	}

	public static void createBlockRecipe(GTMaterial mat) {
		String ingot = "ingot" + mat.getDisplayName();
		String block = "block" + mat.getDisplayName();
		if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				// Block crafting recipe
				recipes.addRecipe(GTMaterialGen.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X',
						ingot });
				TileEntityCompressor.addRecipe(ingot, 9, GTMaterialGen.getMaterialBlock(mat, 1), 0.0F);
				// Inverse
				recipes.addShapelessRecipe(GTMaterialGen.getIngot(mat, 9), new Object[] { block });
				TileEntityMacerator.addRecipe(block, 1, GTMaterialGen.getDust(mat, 9), 0.0F);
			}
		}
	}
}
