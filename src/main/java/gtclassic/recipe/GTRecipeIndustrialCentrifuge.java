package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileIndustrialCentrifuge.OutputItem;
import gtclassic.util.recipe.GTRecipeHelpers.IRecipeModifier;
import gtclassic.util.recipe.GTRecipeHelpers.ModifierType;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTRecipeIndustrialCentrifuge {
	
	public static GTMaterialGen GT;
	public static GTMaterial M;
	public static final Item glassTube = GTItems.glassTube;
	
	public static void init() {
		// INDUSTRIAL CENTRIFUGE RECIPES IN ORDER OF ORIGINAL GT1

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.waterCell, 6), 6, euCost(3000),
						new OutputItem(GT.getIc2(Ic2Items.emptyCell, 6), 0),
						new OutputItem(GT.getChemical(M.Hydrogen, 4), 1),
						new OutputItem(GT.getChemical(M.Oxygen, 2), 2));

				GTTileIndustrialCentrifuge.addRecipe("dustCoal", 4, 8, euCost(7500),
						new OutputItem(GT.getChemical(M.Carbon, 8), 0));

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.rubberWood, 16), 12, euCost(25000),
						new OutputItem(GT.getChemical(M.Carbon, 8), 0),
						new OutputItem(GT.getIc2(Ic2Items.stickyResin, 8), 1),
						new OutputItem(GT.getIc2(Ic2Items.plantBall, 6), 2),
						new OutputItem(GT.getChemical(M.Methane, 4), 3));

				GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Hydrogen, 4), 0, euCost(6000),
						new OutputItem(new ItemStack(glassTube, 3), 0),
						new OutputItem(GT.getChemical(M.Dueterium, 1), 1));

				GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Dueterium, 4), 0, euCost(6000),
						new OutputItem(new ItemStack(glassTube, 3), 0),
						new OutputItem(GT.getChemical(M.Tritium, 1), 1));

				GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Helium, 16), 0, euCost(18000),
						new OutputItem(new ItemStack(glassTube, 15), 0),
						new OutputItem(GT.getChemical(M.Helium3, 1), 1));

				/*
				 * GTTileIndustrialCentrifuge.addRecipe("dustUranium", 16, 22,
				 * euCost(250000), new OutputItem(new ItemStack(GTItems.tungsten, 1), 0), new
				 * OutputItem(GT.getIc2(Ic2Items.reactorUraniumRodSingle, 16), 1),
				 * new OutputItem(new ItemStack(GTItems.rodPlutoniumSingle, 1), 2), new
				 * OutputItem(new ItemStack(GTItems.rodThoriumSingle, 4), 3));
				 */

				GTTileIndustrialCentrifuge.addRecipe("dustRuby", 9, 3, euCost(25000),
						new OutputItem(GT.getDust(M.Aluminium, 2), 0),
						new OutputItem(GT.getDust(M.Chrome, 1), 1),
						new OutputItem(GT.getChemical(M.Oxygen, 3), 2));

				GTTileIndustrialCentrifuge.addRecipe("dustSapphire", 8, 3, euCost(20000),
						new OutputItem(GT.getDust(M.Aluminium, 2), 0),
						new OutputItem(GT.getChemical(M.Oxygen, 3), 1));

				GTTileIndustrialCentrifuge.addRecipe("dustGreenSapphire", 4, 0, euCost(15000),
						new OutputItem(GT.getDust(M.Sapphire, 4), 0));

				GTTileIndustrialCentrifuge.addRecipe("dustEmerald", 29, 18, euCost(30000),
						new OutputItem(GT.getChemical(M.Oxygen, 9), 0),
						new OutputItem(GT.getDust(M.Aluminium, 2), 1),
						new OutputItem(GT.getChemical(M.Berilium, 3), 2),
						new OutputItem(GT.getChemical(M.Silicon, 6), 3));

				GTTileIndustrialCentrifuge.addRecipe("dustEnderpearl", 16, 16, euCost(65000),
						new OutputItem(GT.getChemical(M.Chlorine, 6), 0),
						new OutputItem(GT.getChemical(M.Nitrogen, 5), 1),
						new OutputItem(GT.getChemical(M.Berilium, 1), 2),
						new OutputItem(GT.getChemical(M.Potassium, 4), 3));

				GTTileIndustrialCentrifuge.addRecipe("dustEndereye", 16, 0, euCost(35000),
						new OutputItem(GT.getDust(M.EnderPearl, 8), 0),
						new OutputItem(new ItemStack(Items.BLAZE_POWDER, 8), 1));

				GTTileIndustrialCentrifuge.addRecipe("dustLazurite", 59, 22, euCost(295000),
						new OutputItem(GT.getChemical(M.Sodium, 8), 0),
						new OutputItem(GT.getDust(M.Aluminium, 6), 1),
						new OutputItem(GT.getChemical(M.Silicon, 6), 2),
						new OutputItem(GT.getChemical(M.Calcium, 8), 3));

				GTTileIndustrialCentrifuge.addRecipe("dustPyrite", 3, 0, euCost(15000),
						new OutputItem(GT.getIc2(Ic2Items.ironDust, 1), 0));

				GTTileIndustrialCentrifuge.addRecipe("dustCalcite", 10, 7, euCost(50000),
						new OutputItem(GT.getChemical(M.Calcium, 2), 0),
						new OutputItem(GT.getChemical(M.Carbon, 2), 1),
						new OutputItem(GT.getChemical(M.Oxygen, 3), 2));

				GTTileIndustrialCentrifuge.addRecipe("dustSodalite", 23, 8, euCost(115000),
						new OutputItem(GT.getChemical(M.Chlorine, 1), 0),
						new OutputItem(GT.getChemical(M.Sodium, 4), 1),
						new OutputItem(GT.getDust(M.Aluminium, 3), 2),
						new OutputItem(GT.getChemical(M.Silicon, 3), 3));

				GTTileIndustrialCentrifuge.addRecipe("dustBauxite", 24, 16, euCost(250000),
						new OutputItem(GT.getChemical(M.Oxygen, 6), 0),
						new OutputItem(GT.getDust(M.Aluminium, 16), 1),
						new OutputItem(GT.getDust(M.Titanium, 1), 2),
						new OutputItem(GT.getChemical(M.Hydrogen, 10), 3));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.MAGMA_CREAM, 1), 0, euCost(2500),
						new OutputItem(new ItemStack(Items.BLAZE_POWDER, 1), 0),
						new OutputItem(new ItemStack(Items.SLIME_BALL, 1), 1));

				/*
				 * GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.
				 * reactorNearDepletedUraniumRod, 2), 22, euCost(2500), new
				 * OutputItem(GT.getIc2(Ic2Items.emptyCell, 1), 0), new
				 * OutputItem(new ItemStack(GTItems.rodThoriumSingle, 1), 1));
				 */

				GTTileIndustrialCentrifuge.addRecipe("dirt", 64, 0, euCost(50000),
						new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
						new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 2), 1),
						new OutputItem(GT.getIc2(Ic2Items.plantBall, 2), 2),
						new OutputItem(new ItemStack(Items.CLAY_BALL, 2), 3));

				GTTileIndustrialCentrifuge.addRecipe("grass", 64, 0, euCost(50000),
						new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
						new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 2), 1),
						new OutputItem(GT.getIc2(Ic2Items.plantBall, 4), 2),
						new OutputItem(new ItemStack(Items.CLAY_BALL, 2), 3));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.MYCELIUM, 64), 0, euCost(62500),
						new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
						new OutputItem(new ItemStack(Blocks.BROWN_MUSHROOM, 16), 1),
						new OutputItem(new ItemStack(Blocks.RED_MUSHROOM, 16), 2),
						new OutputItem(new ItemStack(Items.CLAY_BALL, 8), 3));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK, 64), 6, euCost(150000),
						new OutputItem(GT.getChemical(M.Methane, 6), 0));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK, 64), 6, euCost(150000),
						new OutputItem(GT.getChemical(M.Methane, 6), 0));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM, 64), 4, euCost(50000),
						new OutputItem(GT.getChemical(M.Methane, 4), 0));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM, 64), 4, euCost(50000),
						new OutputItem(GT.getChemical(M.Methane, 4), 0));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.NETHER_WART, 64), 2, euCost(50000),
						new OutputItem(GT.getChemical(M.Methane, 2), 0));

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.terraWart, 64), 4, euCost(50000),
						new OutputItem(GT.getChemical(M.Methane, 4), 0));

				GTTileIndustrialCentrifuge.addRecipe("gemLapis", 64, 0, euCost(125000),
						new OutputItem(GT.getDust(M.Sodalite, 8), 0),
						new OutputItem(GT.getDust(M.Lazurite, 48), 1),
						new OutputItem(GT.getDust(M.Pyrite, 4), 2),
						new OutputItem(GT.getDust(M.Calcite, 4), 3));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.BLAZE_POWDER, 8), 0, euCost(15000),
						new OutputItem(GT.getIc2(Ic2Items.coalDust, 2), 0),
						new OutputItem(new ItemStack(Items.GUNPOWDER, 1), 1));

				GTTileIndustrialCentrifuge.addRecipe("sand", 32, 2, euCost(50000),
						new OutputItem(GT.getChemical(M.Silicon, 1), 0),
						new OutputItem(GT.getChemical(M.Oxygen, 1), 1));

				GTTileIndustrialCentrifuge.addRecipe("dustFlint", 8, 2, euCost(5000),
						new OutputItem(GT.getChemical(M.Silicon, 1), 0),
						new OutputItem(GT.getChemical(M.Oxygen, 1), 1));

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.clayDust, 4), 2, euCost(5000),
						new OutputItem(GT.getChemical(M.Lithium, 1), 0),
						new OutputItem(GT.getChemical(M.Silicon, 1), 1));

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.stickyResin, 8), 0, euCost(12500),
						new OutputItem(GT.getIc2(Ic2Items.rubber, 28), 0),
						new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 2), 1),
						new OutputItem(GT.getIc2(Ic2Items.plantBall, 2), 2),
						new OutputItem(GT.getDust(M.Plastic, 2), 3));

				GTTileIndustrialCentrifuge.addRecipe("dustGlowstone", 16, 1, euCost(25000),
						new OutputItem(new ItemStack(Items.REDSTONE, 8), 0),
						new OutputItem(GT.getIc2(Ic2Items.goldDust, 8), 1),
						new OutputItem(GT.getChemical(M.Helium, 1), 2));

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.netherrackDust, 64), 0,
						euCost(50000), new OutputItem(new ItemStack(Items.GOLD_NUGGET, 4), 0),
						new OutputItem(new ItemStack(Items.REDSTONE, 4), 1),
						new OutputItem(new ItemStack(Items.GUNPOWDER, 8), 2),
						new OutputItem(GT.getDust(M.Pyrite, 4), 3)); // changed this from more coal dust to pyrite

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.LAVA, 64), 4, euCost(250000),
						new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 0),
						new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 1),
						new OutputItem(GT.getDust(M.Tungsten, 4), 2));

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.lavaCell, 64), 4, euCost(250000),
						new OutputItem(GT.getIc2(Ic2Items.emptyCell, 64), 0),
						new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 1),
						new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 2),
						new OutputItem(GT.getDust(M.Tungsten, 4), 3));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.MAGMA, 64), 4, euCost(250000),
						new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 0),
						new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 1),
						new OutputItem(GT.getDust(M.Tungsten, 4), 2));

				GTTileIndustrialCentrifuge.addRecipe("dustEndstone", 64, 9, euCost(100000),
						new OutputItem(new ItemStack(Blocks.SAND, 48), 0), // out0
						new OutputItem(GT.getChemical(M.Helium3, 4), 1), // out1
						new OutputItem(GT.getChemical(M.Helium, 4), 2), // out2
						new OutputItem(GT.getDust(M.Tungsten, 1), 3));// out3

				//RECIPES FROM GT2
				
				
				
				// INDUSTRIAL CENTRIFUGE RECIPES NEW/OUT OF ORDER

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(GTBlocks.sandIron, 8), 0, euCost(15000),
						new OutputItem(GT.getIc2(Ic2Items.ironDust, 7), 0),
						new OutputItem(GT.getIc2(Ic2Items.goldDust, 1), 1));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.QUARTZ, 1), 3, euCost(8000),
						new OutputItem(GT.getChemical(M.Silicon, 1), 0),
						new OutputItem(GT.getChemical(M.Oxygen, 2), 1));

				GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.obsidianDust, 64), 10, euCost(16000),
						new OutputItem(GT.getIc2(Ic2Items.ironDust, 2), 0),
						new OutputItem(GT.getChemical(M.Silicon, 6), 1),
						new OutputItem(GT.getChemical(M.Oxygen, 4), 2));

				GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 16), 4, euCost(6000),
						new OutputItem(GT.getChemical(M.Methane, 4), 0),
						new OutputItem(new ItemStack(Items.LEATHER, 4), 1),
						new OutputItem(new ItemStack(Items.SLIME_BALL, 1), 2));

	}
	
	public static IRecipeModifier[] euCost(Integer amount) {
		return new IRecipeModifier[] {
				ModifierType.RECIPE_LENGTH.create((amount / 12) - GTTileIndustrialCentrifuge.defaultLength) };
	}

}
