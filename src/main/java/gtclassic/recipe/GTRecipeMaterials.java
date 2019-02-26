package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipeMaterials {

	public static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeMaterials1() {
		/*
		 * The statements below iterate through the material registry to create recipes
		 * for the correct corresponding items and blocks.
		 */

		for (GTMaterial mat : GTMaterial.values()) {

			String smalldust = "dustSmall" + mat.getDisplayName();
			String dust = "dust" + mat.getDisplayName();
			String gem = "gem" + mat.getDisplayName();
			String ingot = "ingot" + mat.getDisplayName();
			String nugget = "nugget" + mat.getDisplayName();
			String stick = "stick" + mat.getDisplayName();
			String plate = "plate" + mat.getDisplayName();
			String block = "block" + mat.getDisplayName();

			if (mat.hasFlag(GTMaterialFlag.SMALLDUST)) {
				// Regular dust to small dust,
				recipes.addShapelessRecipe(GT.getSmallDust(mat, 4), new Object[] { dust });

				if (mat.hasFlag(GTMaterialFlag.DUST)) {
					// Small dust to regular dust
					recipes.addShapelessRecipe(GT.getDust(mat, 1),
							new Object[] { smalldust, smalldust, smalldust, smalldust });

					// Small dust to regular dust compressor
					TileEntityCompressor.addRecipe(smalldust, 4, GT.getDust(mat, 1), 0.0F);
				}

			}

			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				// Ingot crafting recipe
				recipes.addRecipe(GT.getIngot(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', nugget });

				if (mat.hasFlag(GTMaterialFlag.DUST) && mat.getSmeltable()) {
					GameRegistry.addSmelting(GT.getDust(mat, 1), (GT.getIngot(mat, 1)), 0.1F);
				}

			}

			if (mat.hasFlag(GTMaterialFlag.GEM)) {
				// Dust to gem
				TileEntityCompressor.addRecipe(dust, 1, GT.getGem(mat, 1), 0.0F);

				// Inverse
				TileEntityMacerator.addRecipe(gem, 1, GT.getDust(mat, 1), 0.0F);

				if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
					// Block and gem related logic
					recipes.addShapelessRecipe(GT.getGem(mat, 9), new Object[] { block });
					TileEntityCompressor.addRecipe(gem, 9, GT.getMaterialBlock(mat, 1), 0.0F);
					TileEntityMacerator.addRecipe(block, 1, GT.getDust(mat, 9), 0.0F);
					recipes.addRecipe(GT.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', gem });
				}
			}

			if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
				// Nugget crafting recipe
				recipes.addShapelessRecipe(GT.getNugget(mat, 9), new Object[] { ingot });
			}

			if (mat.hasFlag(GTMaterialFlag.PLATE) && (mat != M.Silicon)) {
				// Plate crafting recipe
				recipes.addRecipe(GT.getPlate(mat, 1),
						new Object[] { "H  ", "X  ", "X  ", 'H', "craftingToolForgeHammer", 'X', ingot });

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
					recipes.addRecipe(GT.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', ingot });
					TileEntityCompressor.addRecipe(ingot, 9, GT.getMaterialBlock(mat, 1), 0.0F);

					// Inverse
					recipes.addShapelessRecipe(GT.getIngot(mat, 9), new Object[] { block });
					TileEntityMacerator.addRecipe(block, 1, GT.getDust(mat, 9), 0.0F);
				}

			}

			if (mat.hasFlag(GTMaterialFlag.CASING)) {
				// Casing crafting recipe
				recipes.addRecipe(GT.getCasing(mat, 1), new Object[] { "SXX", "X X", "XXS", 'X', plate, 'S', stick });
			}
		}
	}

	public static void recipeMaterials2() {
		/*
		 * This is where I will store recipes that are part of the material registry but
		 * are tied to other mods/vanilla so they cannot be created through iteration.
		 */

		final ItemStack dustGlowstone = new ItemStack(Items.GLOWSTONE_DUST);
		final ItemStack dustGunpowder = new ItemStack(Items.GUNPOWDER);
		final ItemStack dustRedstone = new ItemStack(Items.REDSTONE);

		dustUtil(dustGlowstone, M.Glowstone);
		dustUtil(dustGunpowder, M.Gunpowder);
		dustUtil(Ic2Items.tinDust, M.Tin);
		dustUtil(Ic2Items.obsidianDust, M.Obsidian);
		dustUtil(Ic2Items.bronzeDust, M.Bronze);
		dustUtil(Ic2Items.coalDust, M.Coal);
		dustUtil(Ic2Items.silverDust, M.Silver);
		dustUtil(dustRedstone, M.Redstone);
		dustUtil(Ic2Items.clayDust, M.Clay);
		dustUtil(Ic2Items.goldDust, M.Gold);
		dustUtil(Ic2Items.copperDust, M.Copper);
		dustUtil(Ic2Items.netherrackDust, M.Netherrack);
		dustUtil(Ic2Items.ironDust, M.Iron);
		dustUtil(Ic2Items.charcoalDust, M.Charcoal);

		ingotUtil(Ic2Items.copperIngot, M.Copper);
		ingotUtil(Ic2Items.tinIngot, M.Tin);
		ingotUtil(Ic2Items.bronzeIngot, M.Bronze);
		ingotUtil(Ic2Items.silverIngot, M.Silver);

	}

	public static void dustUtil(ItemStack stack, GTMaterial material) {
		String smalldust = "dustSmall" + material.getDisplayName();
		recipes.addShapelessRecipe(stack, new Object[] { smalldust, smalldust, smalldust, smalldust });
		TileEntityCompressor.addRecipe(smalldust, 4, GT.getDust(material, 1), 0.0F);
	}

	public static void ingotUtil(ItemStack stack, GTMaterial material) {
		String nugget = "nugget" + material.getDisplayName();
		recipes.addRecipe(stack, new Object[] { "XXX", "XXX", "XXX", 'X', nugget });
	}

}
