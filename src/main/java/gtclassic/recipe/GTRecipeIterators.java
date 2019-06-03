package gtclassic.recipe;

import gtclassic.GTConfig;
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

public class GTRecipeIterators {

	public static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	public static void recipeIterators1() {
		/*
		 * The statements below iterate through the material registry to create recipes
		 * for the correct corresponding items and blocks.
		 */
		for (GTMaterial mat : GTMaterial.values()) {
			createDustRecipe(mat);
			createIngotRecipe(mat);
			createGemRecipe(mat);
			createNuggetRecipe(mat);
			createBlockRecipe(mat);
		}
	}

	public static void createDustRecipe(GTMaterial mat) {
		String smalldust = "dustSmall" + mat.getDisplayName();
		String dust = "dust" + mat.getDisplayName();
		if (mat.hasFlag(GTMaterialFlag.SMALLDUST)) {
			// Regular dust to small dust,
			recipes.addShapelessRecipe(GTMaterialGen.getSmallDust(mat, 4), new Object[] { dust });
			if (mat.hasFlag(GTMaterialFlag.DUST)) {
				// Small dust to regular dust
				recipes.addShapelessRecipe(GTMaterialGen.getDust(mat, 1), new Object[] { smalldust, smalldust,
						smalldust, smalldust });
				TileEntityCompressor.addRecipe(smalldust, 4, GTMaterialGen.getDust(mat, 1), 0.0F);
			}
		}
	}

	public static void createIngotRecipe(GTMaterial mat) {
		String nugget = "nugget" + mat.getDisplayName();
		if (!mat.equals(GTMaterial.Plastic)) {
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				// Ingot crafting recipe
				recipes.addRecipe(GTMaterialGen.getIngot(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', nugget });
				if (mat.hasFlag(GTMaterialFlag.DUST) || mat.equals(GTMaterial.AnnealedCopper)) {
						GameRegistry.addSmelting(GTMaterialGen.getDust(mat, 1), (GTMaterialGen.getIngot(mat, 1)), 0.1F);
				}
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

	public static void recipeIterators2() {
		/*
		 * This is where I will store recipes that are part of the material registry but
		 * are tied to other mods/vanilla so they cannot be created through iteration.
		 */
		final ItemStack dustGlowstone = new ItemStack(Items.GLOWSTONE_DUST);
		final ItemStack dustGunpowder = new ItemStack(Items.GUNPOWDER);
		final ItemStack dustRedstone = new ItemStack(Items.REDSTONE);
		dustUtil(dustGlowstone, GTMaterial.Glowstone);
		dustUtil(dustGunpowder, GTMaterial.Gunpowder);
		dustUtil(Ic2Items.tinDust, GTMaterial.Tin);
		dustUtil(Ic2Items.obsidianDust, GTMaterial.Obsidian);
		dustUtil(Ic2Items.bronzeDust, GTMaterial.Bronze);
		dustUtil(Ic2Items.coalDust, GTMaterial.Coal);
		dustUtil(Ic2Items.silverDust, GTMaterial.Silver);
		dustUtil(dustRedstone, GTMaterial.Redstone);
		dustUtil(Ic2Items.clayDust, GTMaterial.Clay);
		dustUtil(Ic2Items.goldDust, GTMaterial.Gold);
		dustUtil(Ic2Items.copperDust, GTMaterial.Copper);
		dustUtil(Ic2Items.netherrackDust, GTMaterial.Netherrack);
		dustUtil(Ic2Items.ironDust, GTMaterial.Iron);
		dustUtil(Ic2Items.charcoalDust, GTMaterial.Charcoal);
		ingotUtil(Ic2Items.copperIngot, GTMaterial.Copper);
		ingotUtil(Ic2Items.tinIngot, GTMaterial.Tin);
		ingotUtil(Ic2Items.bronzeIngot, GTMaterial.Bronze);
		ingotUtil(Ic2Items.silverIngot, GTMaterial.Silver);
	}

	public static void dustUtil(ItemStack stack, GTMaterial material) {
		String smalldust = "dustSmall" + material.getDisplayName();
		recipes.addShapelessRecipe(stack, new Object[] { smalldust, smalldust, smalldust, smalldust });
		TileEntityCompressor.addRecipe(smalldust, 4, GTMaterialGen.getIc2(stack, 1), 0.0F);
	}

	public static void ingotUtil(ItemStack stack, GTMaterial material) {
		String nugget = "nugget" + material.getDisplayName();
		recipes.addRecipe(stack, new Object[] { "XXX", "XXX", "XXX", 'X', nugget });
	}
}
