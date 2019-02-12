package gtclassic.recipe;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTRecipeIterators {

	public static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;
	static final Item glassTube = GTItems.glassTube;

	public static void init() {
		for (GTMaterial mat : GTMaterial.values()) {

			String smalldust = "dustSmall" + mat.getDisplayName();
			String dust = "dust" + mat.getDisplayName();
			String gem = "gem" + mat.getDisplayName();
			String ingot = "ingot" + mat.getDisplayName();
			String nugget = "nugget" + mat.getDisplayName();
			String stick = "stick" + mat.getDisplayName();
			String plate = "plate" + mat.getDisplayName();
			String block = "block" + mat.getDisplayName();

			if (mat.hasFlag(GTMaterialFlag.SMALLDUST) && mat.hasFlag(GTMaterialFlag.DUST)) {
				// Small dust to regular dust
				recipes.addShapelessRecipe(GT.getDust(mat, 1), new Object[] { smalldust, smalldust, smalldust, smalldust });

				// Small dust to regular dust compressor
				TileEntityCompressor.addRecipe(smalldust, 4, GT.getDust(mat, 1), 0.0F);

				// Regular dust to small dust
				recipes.addShapelessRecipe(GT.getSmallDust(mat, 4), new Object[] { dust });
			}

			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				// Ingot crafting recipe
				recipes.addRecipe(GT.getIngot(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', nugget });
			}

			if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
				// Nugget crafting recipe
				recipes.addShapelessRecipe(GT.getNugget(mat, 9), new Object[] { ingot });
			}

			if (mat.hasFlag(GTMaterialFlag.PLATE)) {
				// Plate crafting recipe
				recipes.addRecipe(GT.getPlate(mat, 1), new Object[] { "H  ", "X  ", "X  ",
						'H', "craftingToolForgeHammer",
						'X', ingot });

				// If a dust is present create a maceration recipe
				if (mat.hasFlag(GTMaterialFlag.DUST)) {
					TileEntityMacerator.addRecipe(plate, 1, GT.getDust(mat, 1), 0.0F);
				}
			}

			if (mat.hasFlag(GTMaterialFlag.STICK)) {
				// Stick crafting recipe
				recipes.addShapelessRecipe(GT.getStick(mat, 1), new Object[] { "craftingToolFile", ingot });

				// If a dust is present create a maceration recipe
				if (mat.hasFlag(GTMaterialFlag.DUST)) {
					TileEntityMacerator.addRecipe(stick, 1, GT.getSmallDust(mat, 2), 0.0F);
				}
			}

			if (mat.hasFlag(GTMaterialFlag.BLOCK)) {

				if (mat.hasFlag(GTMaterialFlag.INGOT)) {
					// Block crafting recipe
					recipes.addRecipe(GT.getMaterialBlock(mat, 1),
							new Object[] { "XXX", "XXX", "XXX", 'X', ingot });
					TileEntityCompressor.addRecipe(ingot, 9, GT.getMaterialBlock(mat, 1), 0.0F);

					// Inverse
					recipes.addShapelessRecipe(GT.getIngot(mat, 9), new Object[] { block });
					TileEntityMacerator.addRecipe(block, 1, GT.getDust(mat, 9), 0.0F);
				}

				if (mat.hasFlag(GTMaterialFlag.GEM)) {
					// Block crafting recipe
					recipes.addRecipe(GT.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', gem });
					TileEntityCompressor.addRecipe(gem, 9, GT.getMaterialBlock(mat, 1), 0.0F);

					// Inverse
					recipes.addShapelessRecipe(GT.getGem(mat, 9),
							new Object[] { block });
					TileEntityMacerator.addRecipe(block, 1, GT.getDust(mat, 9), 0.0F);
				}
			}

			if (mat.hasFlag(GTMaterialFlag.CASING)) {
				// Casing crafting recipe
				recipes.addRecipe(GT.getCasing(mat, 1),
						new Object[] { "SXX", "X X", "XXS", 'X', plate, 'S', stick });
			}
		}
	}

}
