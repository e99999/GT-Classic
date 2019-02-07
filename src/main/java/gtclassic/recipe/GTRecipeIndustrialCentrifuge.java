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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTRecipeIndustrialCentrifuge {

	static GTMaterialGen GT;
	static GTMaterial M;
	static final Item glassTube = GTItems.glassTube;

	public static void init() {

		// Recipes from GT1
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

		GTTileIndustrialCentrifuge.addRecipe("dirt", 16, 0, euCost(12500),
				new OutputItem(new ItemStack(Blocks.SAND, 8), 0),
				new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 1), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("grass", 16, 0, euCost(12500),
				new OutputItem(new ItemStack(Blocks.SAND, 8), 0),
				new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 2), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 2), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.MYCELIUM, 8), 0, euCost(8250),
				new OutputItem(new ItemStack(Blocks.SAND, 4), 0),
				new OutputItem(new ItemStack(Blocks.BROWN_MUSHROOM, 2), 1),
				new OutputItem(new ItemStack(Blocks.RED_MUSHROOM, 2), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 1), 3));

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

		GTTileIndustrialCentrifuge.addRecipe("dustGlowstone", 16, 1, euCost(25000),
				new OutputItem(new ItemStack(Items.REDSTONE, 8), 0),
				new OutputItem(GT.getIc2(Ic2Items.goldDust, 8), 1),
				new OutputItem(GT.getChemical(M.Helium, 1), 2));

		// Recipes from GT2
		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.GOLDEN_APPLE, 1), 1, euCost(50000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0),
				new OutputItem(new ItemStack(Items.GOLD_INGOT, 8), 1));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.GOLDEN_CARROT, 1), 1, euCost(50000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0),
				new OutputItem(new ItemStack(Items.GOLD_NUGGET, 6), 1));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.SPECKLED_MELON, 1), 1, euCost(50000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0),
				new OutputItem(new ItemStack(Items.GOLD_NUGGET, 6), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.netherrackDust, 16), 0, euCost(12000),
				new OutputItem(new ItemStack(Items.GOLD_NUGGET, 1), 0),
				new OutputItem(new ItemStack(Items.REDSTONE, 1), 1),
				new OutputItem(GT.getDust(M.Sulfur, 4), 2),
				new OutputItem(GT.getIc2(Ic2Items.coalDust, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.LAVA, 64), 0, euCost(250000),
				new OutputItem(GT.getIngot(M.Electrum, 1), 0),
				new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 1),
				new OutputItem(GT.getSmallDust(M.Tungsten, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.lavaCell, 16), 0, euCost(250000),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 16), 0),
				new OutputItem(GT.getIngot(M.Electrum, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 2),
				new OutputItem(GT.getSmallDust(M.Tungsten, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.MAGMA, 64), 0, euCost(250000),
				new OutputItem(GT.getIngot(M.Electrum, 1), 0),
				new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 1),
				new OutputItem(GT.getSmallDust(M.Tungsten, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 2), 0, euCost(2500),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 2), 0),
				new OutputItem(GT.getDust(M.Thorium, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustUranium", 4, 4, euCost(250000),
				new OutputItem(GT.getDust(M.Tungsten, 1), 0),
				new OutputItem(GT.getIc2(Ic2Items.reactorUraniumRodSingle, 4), 1),
				new OutputItem(GT.getSmallDust(M.Plutonium, 1), 2),
				new OutputItem(GT.getDust(M.Thorium, 2), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.reactorReEnrichedUraniumRod, 8), 22, euCost(100000),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 5), 0),
				new OutputItem(GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 3), 1),
				new OutputItem(GT.getDust(M.Plutonium, 1), 2),
				new OutputItem(GT.getDust(M.Thorium, 4), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.stickyResin, 4), 0, euCost(6500),
				new OutputItem(GT.getIc2(Ic2Items.rubber, 14), 0),
				new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 1), 2),
				new OutputItem(GT.getDust(M.Plastic, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustCopper", 3, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Gold, 1), 0),
				new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustGold", 3, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Copper, 1), 0),
				new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustIron", 2, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Tin, 1), 0),
				new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustSilver", 2, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Lead, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustLead", 2, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Silver, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustZinc", 1, 0, euCost(5000),
				new OutputItem(GT.getSmallDust(M.Tin, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustBrass", 1, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Copper, 3), 0),
				new OutputItem(GT.getSmallDust(M.Zinc, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustBronze", 2, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Copper, 6), 0),
				new OutputItem(GT.getSmallDust(M.Tin, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustTin", 2, 0, euCost(10500),
				new OutputItem(GT.getSmallDust(M.Zinc, 1), 0),
				new OutputItem(GT.getSmallDust(M.Iron, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustNickel", 3, 0, euCost(17500),
				new OutputItem(GT.getSmallDust(M.Iron, 1), 0),
				new OutputItem(GT.getSmallDust(M.Gold, 1), 1),
				new OutputItem(GT.getSmallDust(M.Copper, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustPlatinum", 2, 0, euCost(15000),
				new OutputItem(GT.getNugget(M.Iridium, 1), 0),
				new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustElectrum", 2, 0, euCost(5000),
				new OutputItem(GT.getSmallDust(M.Gold, 2), 0),
				new OutputItem(GT.getSmallDust(M.Silver, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustInvar", 2, 0, euCost(5000),
				new OutputItem(GT.getIc2(Ic2Items.ironDust, 2), 0),
				new OutputItem(GT.getDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("gemLapis", 4, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Sodalite, 2), 0),
				new OutputItem(GT.getDust(M.Lazurite, 3), 1),
				new OutputItem(GT.getSmallDust(M.Pyrite, 1), 2),
				new OutputItem(GT.getSmallDust(M.Calcite, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustRedstone", 4, 0, euCost(35000),
				new OutputItem(GT.getChemical(M.Mercury, 3), 0),
				new OutputItem(GT.getChemical(M.Silicon, 1), 1),
				new OutputItem(GT.getDust(M.Pyrite, 5), 2),
				new OutputItem(GT.getDust(M.Ruby, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustEndstone", 16, 2, euCost(100000),
				new OutputItem(new ItemStack(Blocks.SAND, 12), 0),
				new OutputItem(GT.getChemical(M.Helium3, 1), 1),
				new OutputItem(GT.getChemical(M.Helium, 1), 2),
				new OutputItem(GT.getSmallDust(M.Tungsten, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustRedGarnet", 16, 0, euCost(15000),
				new OutputItem(GT.getDust(M.Pyrope, 3), 0),
				new OutputItem(GT.getDust(M.Almandine, 5), 1),
				new OutputItem(GT.getDust(M.Spessartine, 8), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustYellowGarnet", 16, 0, euCost(15000),
				new OutputItem(GT.getDust(M.Andradite, 5), 0),
				new OutputItem(GT.getDust(M.Grossular, 8), 1),
				new OutputItem(GT.getDust(M.Uvarovite, 3), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustDarkAshes", 2, 1, euCost(1250),
				new OutputItem(GT.getDust(M.Ashes, 1), 0),
				new OutputItem(GT.getChemical(M.Carbon, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustGranite", 4, 0, euCost(1000),
				new OutputItem(GT.getDust(M.Calcite, 2), 0),
				new OutputItem(GT.getDust(M.Flint, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.clayDust, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustRedRock", 4, 0, euCost(1000),
				new OutputItem(GT.getDust(M.Calcite, 2), 0),
				new OutputItem(GT.getDust(M.Flint, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.clayDust, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustDiorite", 8, 0, euCost(6000),
				new OutputItem(GT.getDust(M.Magnesium, 1), 0),
				new OutputItem(GT.getDust(M.Calcite, 7), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustMarble", 8, 0, euCost(6000),
				new OutputItem(GT.getDust(M.Magnesium, 1), 0),
				new OutputItem(GT.getDust(M.Calcite, 7), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustAndestite", 16, 0, euCost(10000),
				new OutputItem(GT.getDust(M.DarkAshes, 4), 0),
				new OutputItem(GT.getDust(M.Olivine, 1), 1),
				new OutputItem(GT.getDust(M.Calcite, 3), 2),
				new OutputItem(GT.getDust(M.Flint, 8), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustBasalt", 16, 0, euCost(10000),
				new OutputItem(GT.getDust(M.DarkAshes, 4), 0),
				new OutputItem(GT.getDust(M.Olivine, 1), 1),
				new OutputItem(GT.getDust(M.Calcite, 3), 2),
				new OutputItem(GT.getDust(M.Flint, 8), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.CalciumCarbonate, 1), 0, euCost(200),
				new OutputItem(new ItemStack(glassTube, 1), 0),
				new OutputItem(GT.getDust(M.Calcite, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Sulfur, 1), 0, euCost(200),
				new OutputItem(new ItemStack(glassTube, 1), 0),
				new OutputItem(GT.getDust(M.Sulfur, 1), 1));

		// New recipes from GT Classic
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

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 16), 4, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0),
				new OutputItem(new ItemStack(Items.LEATHER, 2), 1),
				new OutputItem(new ItemStack(Items.SLIME_BALL, 1), 2));

		// Methane recipes

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.APPLE, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.MUSHROOM_STEW, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.BREAD, 64), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.PORKCHOP, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.COOKED_PORKCHOP, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.BEEF, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.COOKED_BEEF, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.FISH, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.COOKED_FISH, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.CHICKEN, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.COOKED_CHICKEN, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.MUTTON, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.COOKED_MUTTON, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.MELON, 64), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.PUMPKIN, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.SPIDER_EYE, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.CARROT, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.POTATO, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.POISONOUS_POTATO, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.BAKED_POTATO, 24), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.COOKIE, 64), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.CAKE, 8), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(new ItemStack(Items.NETHER_WART, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.terraWart, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

	}

	public static IRecipeModifier[] euCost(Integer amount) {
		return new IRecipeModifier[] {
				ModifierType.RECIPE_LENGTH.create((amount / 12) - GTTileIndustrialCentrifuge.defaultLength) };
	}

}
