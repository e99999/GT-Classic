package gtclassic.recipe;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileIndustrialCentrifuge.OutputItem;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GTRecipeIndustrialCentrifuge {

	static GTMaterialGen GT;
	static GTMaterial M;
	static final Item glassTube = GTItems.glassTube;

	public static void recipesCentrifuge1() {
		/*
		 * Recipes from GregTech 1, these are more like Electrolyzer recipes, but inside
		 * the Centrifuge.
		 */
		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.waterCell, 6), 6, euCost(3000),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 6), 0), new OutputItem(GT.getChemical(M.Hydrogen, 4), 1),
				new OutputItem(GT.getChemical(M.Oxygen, 2), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustCoal", 4, 8, euCost(7500),
				new OutputItem(GT.getChemical(M.Carbon, 8), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.rubberWood, 16), 12, euCost(25000),
				new OutputItem(GT.getChemical(M.Carbon, 8), 0), new OutputItem(GT.getIc2(Ic2Items.stickyResin, 8), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 6), 2), new OutputItem(GT.getChemical(M.Methane, 4), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Hydrogen, 4), 0, euCost(6000),
				new OutputItem(GT.get(glassTube, 3), 0), new OutputItem(GT.getChemical(M.Dueterium, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Dueterium, 4), 0, euCost(6000),
				new OutputItem(GT.get(glassTube, 3), 0), new OutputItem(GT.getChemical(M.Tritium, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Helium, 16), 0, euCost(18000),
				new OutputItem(GT.get(glassTube, 15), 0), new OutputItem(GT.getChemical(M.Helium3, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustRuby", 9, 3, euCost(25000),
				new OutputItem(GT.getDust(M.Aluminium, 2), 0), new OutputItem(GT.getDust(M.Chrome, 1), 1),
				new OutputItem(GT.getChemical(M.Oxygen, 3), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustSapphire", 8, 3, euCost(20000),
				new OutputItem(GT.getDust(M.Aluminium, 2), 0), new OutputItem(GT.getChemical(M.Oxygen, 3), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustGreenSapphire", 4, 0, euCost(15000),
				new OutputItem(GT.getDust(M.Sapphire, 4), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustEmerald", 29, 18, euCost(30000),
				new OutputItem(GT.getChemical(M.Oxygen, 9), 0), new OutputItem(GT.getDust(M.Aluminium, 2), 1),
				new OutputItem(GT.getChemical(M.Berilium, 3), 2), new OutputItem(GT.getChemical(M.Silicon, 6), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustEnderpearl", 16, 16, euCost(65000),
				new OutputItem(GT.getChemical(M.Chlorine, 6), 0), new OutputItem(GT.getChemical(M.Nitrogen, 5), 1),
				new OutputItem(GT.getChemical(M.Berilium, 1), 2), new OutputItem(GT.getChemical(M.Potassium, 4), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustEndereye", 16, 0, euCost(35000),
				new OutputItem(GT.getDust(M.EnderPearl, 8), 0), new OutputItem(GT.get(Items.BLAZE_POWDER, 8), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustLazurite", 59, 22, euCost(295000),
				new OutputItem(GT.getChemical(M.Sodium, 8), 0), new OutputItem(GT.getDust(M.Aluminium, 6), 1),
				new OutputItem(GT.getChemical(M.Silicon, 6), 2), new OutputItem(GT.getChemical(M.Calcium, 8), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustPyrite", 3, 0, euCost(15000),
				new OutputItem(GT.getIc2(Ic2Items.ironDust, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustCalcite", 10, 7, euCost(50000),
				new OutputItem(GT.getChemical(M.Calcium, 2), 0), new OutputItem(GT.getChemical(M.Carbon, 2), 1),
				new OutputItem(GT.getChemical(M.Oxygen, 3), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustSodalite", 23, 8, euCost(115000),
				new OutputItem(GT.getChemical(M.Chlorine, 1), 0), new OutputItem(GT.getChemical(M.Sodium, 4), 1),
				new OutputItem(GT.getDust(M.Aluminium, 3), 2), new OutputItem(GT.getChemical(M.Silicon, 3), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustBauxite", 24, 16, euCost(250000),
				new OutputItem(GT.getChemical(M.Oxygen, 6), 0), new OutputItem(GT.getDust(M.Aluminium, 16), 1),
				new OutputItem(GT.getDust(M.Titanium, 1), 2), new OutputItem(GT.getChemical(M.Hydrogen, 10), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.MAGMA_CREAM, 1), 0, euCost(2500),
				new OutputItem(GT.get(Items.BLAZE_POWDER, 1), 0), new OutputItem(GT.get(Items.SLIME_BALL, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dirt", 16, 0, euCost(12500), new OutputItem(GT.get(Blocks.SAND, 8), 0),
				new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 1), 2), new OutputItem(GT.get(Items.CLAY_BALL, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("grass", 16, 0, euCost(12500), new OutputItem(GT.get(Blocks.SAND, 8), 0),
				new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 2), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 2), 2), new OutputItem(GT.get(Items.CLAY_BALL, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.MYCELIUM, 8), 0, euCost(8250),
				new OutputItem(GT.get(Blocks.SAND, 4), 0), new OutputItem(GT.get(Blocks.BROWN_MUSHROOM, 2), 1),
				new OutputItem(GT.get(Blocks.RED_MUSHROOM, 2), 2), new OutputItem(GT.get(Items.CLAY_BALL, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.BLAZE_POWDER, 8), 0, euCost(15000),
				new OutputItem(GT.getIc2(Ic2Items.coalDust, 2), 0), new OutputItem(GT.get(Items.GUNPOWDER, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("sand", 32, 2, euCost(50000),
				new OutputItem(GT.getChemical(M.Silicon, 1), 0), new OutputItem(GT.getChemical(M.Oxygen, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustFlint", 8, 2, euCost(5000),
				new OutputItem(GT.getChemical(M.Silicon, 1), 0), new OutputItem(GT.getChemical(M.Oxygen, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.clayDust, 4), 2, euCost(5000),
				new OutputItem(GT.getChemical(M.Lithium, 1), 0), new OutputItem(GT.getChemical(M.Silicon, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustGlowstone", 16, 1, euCost(25000),
				new OutputItem(GT.get(Items.REDSTONE, 8), 0), new OutputItem(GT.getIc2(Ic2Items.goldDust, 8), 1),
				new OutputItem(GT.getChemical(M.Helium, 1), 2));
	}

	public static void recipesCentrifuge2() {
		/*
		 * Recipes from GregTech 2, these are more focused on getting tiny amounts of
		 * stuff from common materials and items.
		 */
		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.GOLDEN_APPLE, 1), 1, euCost(50000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0), new OutputItem(GT.get(Items.GOLD_INGOT, 8), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.GOLDEN_CARROT, 1), 1, euCost(50000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0), new OutputItem(GT.get(Items.GOLD_NUGGET, 6), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.SPECKLED_MELON, 1), 1, euCost(50000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0), new OutputItem(GT.get(Items.GOLD_NUGGET, 6), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.netherrackDust, 16), 0, euCost(12000),
				new OutputItem(GT.get(Items.GOLD_NUGGET, 1), 0), new OutputItem(GT.get(Items.REDSTONE, 1), 1),
				new OutputItem(GT.getDust(M.Sulfur, 4), 2), new OutputItem(GT.getIc2(Ic2Items.coalDust, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.LAVA, 16), 0, euCost(75000),
				new OutputItem(GT.getIngot(M.Electrum, 1), 0), new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 1),
				new OutputItem(GT.getSmallDust(M.Tungsten, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.lavaCell, 16), 0, euCost(75000),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 16), 0), new OutputItem(GT.getIngot(M.Electrum, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 2),
				new OutputItem(GT.getSmallDust(M.Tungsten, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.MAGMA, 16), 0, euCost(75000),
				new OutputItem(GT.getIngot(M.Electrum, 1), 0), new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 1),
				new OutputItem(GT.getSmallDust(M.Tungsten, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 2), 0, euCost(2500),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 2), 0), new OutputItem(GT.getDust(M.Thorium, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustUranium", 4, 4, euCost(250000),
				new OutputItem(GT.getDust(M.Tungsten, 1), 0),
				new OutputItem(GT.getIc2(Ic2Items.reactorUraniumRodSingle, 4), 1),
				new OutputItem(GT.getSmallDust(M.Plutonium, 1), 2), new OutputItem(GT.getDust(M.Thorium, 2), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.reactorReEnrichedUraniumRod, 8), 22, euCost(100000),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 5), 0),
				new OutputItem(GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 3), 1),
				new OutputItem(GT.getDust(M.Plutonium, 1), 2), new OutputItem(GT.getDust(M.Thorium, 4), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.stickyResin, 4), 0, euCost(6500),
				new OutputItem(GT.getIc2(Ic2Items.rubber, 14), 0),
				new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustCopper", 3, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Gold, 1), 0), new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustGold", 3, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Copper, 1), 0), new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustIron", 2, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Tin, 1), 0), new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustSilver", 2, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Lead, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustLead", 2, 0, euCost(12000),
				new OutputItem(GT.getSmallDust(M.Silver, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustZinc", 1, 0, euCost(5000),
				new OutputItem(GT.getSmallDust(M.Tin, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustBrass", 1, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Copper, 3), 0), new OutputItem(GT.getSmallDust(M.Zinc, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustBronze", 2, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Copper, 6), 0), new OutputItem(GT.getSmallDust(M.Tin, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustTin", 2, 0, euCost(10500),
				new OutputItem(GT.getSmallDust(M.Zinc, 1), 0), new OutputItem(GT.getSmallDust(M.Iron, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustNickel", 3, 0, euCost(17500),
				new OutputItem(GT.getSmallDust(M.Iron, 1), 0), new OutputItem(GT.getSmallDust(M.Gold, 1), 1),
				new OutputItem(GT.getSmallDust(M.Copper, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustPlatinum", 2, 0, euCost(15000),
				new OutputItem(GT.getNugget(M.Iridium, 1), 0), new OutputItem(GT.getSmallDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustElectrum", 2, 0, euCost(5000),
				new OutputItem(GT.getSmallDust(M.Gold, 2), 0), new OutputItem(GT.getSmallDust(M.Silver, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustInvar", 2, 0, euCost(5000),
				new OutputItem(GT.getIc2(Ic2Items.ironDust, 2), 0), new OutputItem(GT.getDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("gemLapis", 4, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Sodalite, 2), 0), new OutputItem(GT.getDust(M.Lazurite, 3), 1),
				new OutputItem(GT.getSmallDust(M.Pyrite, 1), 2), new OutputItem(GT.getSmallDust(M.Calcite, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustRedstone", 4, 0, euCost(35000),
				new OutputItem(GT.getChemical(M.Mercury, 3), 0), new OutputItem(GT.getChemical(M.Silicon, 1), 1),
				new OutputItem(GT.getDust(M.Pyrite, 5), 2), new OutputItem(GT.getDust(M.Ruby, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustEndstone", 16, 2, euCost(100000),
				new OutputItem(GT.get(Blocks.SAND, 12), 0), new OutputItem(GT.getChemical(M.Helium3, 1), 1),
				new OutputItem(GT.getChemical(M.Helium, 1), 2), new OutputItem(GT.getSmallDust(M.Tungsten, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustRedGarnet", 16, 0, euCost(15000),
				new OutputItem(GT.getDust(M.Pyrope, 3), 0), new OutputItem(GT.getDust(M.Almandine, 5), 1),
				new OutputItem(GT.getDust(M.Spessartine, 8), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustYellowGarnet", 16, 0, euCost(15000),
				new OutputItem(GT.getDust(M.Andradite, 5), 0), new OutputItem(GT.getDust(M.Grossular, 8), 1),
				new OutputItem(GT.getDust(M.Uvarovite, 3), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustDarkAshes", 2, 1, euCost(1250),
				new OutputItem(GT.getDust(M.Ashes, 1), 0), new OutputItem(GT.getChemical(M.Carbon, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustGranite", 4, 0, euCost(1000),
				new OutputItem(GT.getDust(M.Calcite, 2), 0), new OutputItem(GT.getDust(M.Flint, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.clayDust, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustRedRock", 4, 0, euCost(1000),
				new OutputItem(GT.getDust(M.Calcite, 2), 0), new OutputItem(GT.getDust(M.Flint, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.clayDust, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustMarble", 8, 0, euCost(6000),
				new OutputItem(GT.getDust(M.Magnesium, 1), 0), new OutputItem(GT.getDust(M.Calcite, 7), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustBasalt", 16, 0, euCost(10000),
				new OutputItem(GT.getDust(M.DarkAshes, 4), 0), new OutputItem(GT.getDust(M.Olivine, 1), 1),
				new OutputItem(GT.getDust(M.Calcite, 3), 2), new OutputItem(GT.getDust(M.Flint, 8), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getChemical(M.Sulfur, 1), 0, euCost(200),
				new OutputItem(GT.get(glassTube, 1), 0), new OutputItem(GT.getDust(M.Sulfur, 1), 1));

		/*
		 * Recipes from the GT2 Electrolyzer
		 */

		GTTileIndustrialCentrifuge.addRecipe("dustSphalerite", 5, 0, euCost(5000),
				new OutputItem(GT.getDust(M.Zinc, 2), 0), new OutputItem(GT.getDust(M.Sulfur, 1), 1));
	}

	public static void recipesCentrifuge3() {
		/*
		 * New recipes added by GregTech Classic, these at the moment are just give
		 * purpose to new materials added by GregTech and vanilla Minecraft since GT1
		 * and GT2.
		 */
		GTTileIndustrialCentrifuge.addRecipe(GT.getDust(M.Magnetite, 2), 0, euCost(1000),
				new OutputItem(GT.getIc2(Ic2Items.ironDust, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.QUARTZ, 1), 3, euCost(8000),
				new OutputItem(GT.getChemical(M.Silicon, 1), 0), new OutputItem(GT.getChemical(M.Oxygen, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustDiamond", 1, 0, euCost(10000),
				new OutputItem(GT.getSmallDust(M.Graphite, 2), 0), new OutputItem(GT.getSmallDust(M.Diamond, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.obsidianDust, 64), 10, euCost(16000),
				new OutputItem(GT.getIc2(Ic2Items.ironDust, 2), 0), new OutputItem(GT.getChemical(M.Silicon, 6), 1),
				new OutputItem(GT.getChemical(M.Oxygen, 4), 2));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.ROTTEN_FLESH, 16), 4, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0), new OutputItem(GT.get(Items.LEATHER, 2), 1),
				new OutputItem(GT.get(Items.SLIME_BALL, 1), 2));
	}

	public static void recipesCentrifuge4() {
		/*
		 * Recipes solely focused on getting methane from various things, at some point
		 * i will probably create some sort of dataset to collect all food and similar
		 * items from other mods.
		 */
		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.APPLE, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.MUSHROOM_STEW, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.BREAD, 64), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.PORKCHOP, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_PORKCHOP, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.BEEF, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_BEEF, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.FISH, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_FISH, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.CHICKEN, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_CHICKEN, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.MUTTON, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_MUTTON, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.MELON, 64), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.PUMPKIN, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.SPIDER_EYE, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.CARROT, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.POTATO, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.POISONOUS_POTATO, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.BAKED_POTATO, 24), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKIE, 64), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.CAKE, 8), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.BROWN_MUSHROOM_BLOCK, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.RED_MUSHROOM_BLOCK, 12), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.BROWN_MUSHROOM, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.RED_MUSHROOM, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.NETHER_WART, 32), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.terraWart, 16), 1, euCost(25000),
				new OutputItem(GT.getChemical(M.Methane, 1), 0));

	}

	public static IRecipeModifier[] euCost(int amount) {
		return new IRecipeModifier[] {
				ModifierType.RECIPE_LENGTH.create((amount / 12) - GTTileIndustrialCentrifuge.defaultLength) };
	}

}
