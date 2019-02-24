package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileAlloySmelter;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTRecipeProcessing {

	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipesProcessing() {

		/*
		 * Recipes specific to GT Classic ores
		 */
		TileEntityMacerator.addRecipe("oreBauxite", 1, GT.getDust(M.Bauxite, 4), 0.1F);
		TileEntityMacerator.addRecipe("oreIridium", 1, GT.getIc2(Ic2Items.iridiumOre, 2), 0.5F);

		TileEntityExtractor.addRecipe("oreRuby", 1, GT.getGem(M.Ruby, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreSapphire", 1, GT.getGem(M.Sapphire, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreOlivine", 1, GT.getGem(M.Olivine, 3), 0.1F);

		/*
		 * Maceration recipes not covered by Ic2c automatically or that need to be
		 * different
		 */
		TileEntityMacerator.addRecipe(new ItemStack(Items.FLINT, 1), GT.getDust(M.Flint, 1), 0.1F);
		TileEntityMacerator.addRecipe(Ic2Items.uraniumDrop, 1, GT.getDust(M.Uranium, 1), 0.1F);
		TileEntityMacerator.addRecipe("enderpearl", 1, GT.getDust(M.EnderPearl, 1), 0.2F);
		TileEntityMacerator.addRecipe(new ItemStack(Items.ENDER_EYE, 1), GT.getDust(M.EnderEye, 1), 0.2F);
		TileEntityMacerator.addRecipe("gemDiamond", 1, GT.getDust(M.Diamond, 1), 0.1F);
		TileEntityMacerator.addRecipe("gemEmerald", 1, GT.getDust(M.Emerald, 1), 0.1F);

		/*
		 * Compressor recipes
		 */
		TileEntityCompressor.addRecipe(GT.getChemical(M.Carbon, 8), GT.getIc2(Ic2Items.carbonFiber, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustUranium", 1, GT.getIc2(Ic2Items.uraniumIngot, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustEmerald", 1, new ItemStack(Items.EMERALD), 0.1F);
		TileEntityCompressor.addRecipe("dustDiamond", 1, new ItemStack(Items.DIAMOND), 0.1F);

		// ignore this

		GTTileAlloySmelter.addRecipe("ingotTin", 1, "ingotCopper", 3, GT.getIc2(Ic2Items.bronzeIngot, 4));
		GTTileAlloySmelter.addRecipe("ingotCopper", 3, "ingotTin", 1,
				GT.getIc2(Ic2Items.bronzeIngot, 4));

	}

}
