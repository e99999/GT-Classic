package gtclassic.recipe;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GTRecipeIndustrialCentrifuge {

	static GTMaterialGen GT;
	static GTMaterial M;
	static final Item glassTube = GTItems.testTube;
	static GTTileIndustrialCentrifuge Centrifuge;

	public static void recipesCentrifuge1() {

		/*
		 * Back to normal recipes
		 * 
		 */
		Centrifuge.addRecipe(GT.getIc2(Ic2Items.airCell, 2), 2, euCost(32000), GT.getFluid(M.Oxygen, 1),
				GT.getFluid(M.Nitrogen, 1), GT.getIc2(Ic2Items.emptyCell, 2));

		Centrifuge.addRecipe("logRubber", 16, 4, euCost(25000), GT.getDust(M.Wood, 8),
				GT.getIc2(Ic2Items.stickyResin, 8), GT.getIc2(Ic2Items.plantBall, 6), GT.getFluid(M.Methane, 4));

		Centrifuge.addRecipe(GT.getFluid(M.Hydrogen, 4), 0, euCost(6000), GT.get(glassTube, 3),
				GT.getFluid(M.Deuterium, 1));

		Centrifuge.addRecipe(GT.getFluid(M.Deuterium, 4), 0, euCost(6000), GT.get(glassTube, 3),
				GT.getFluid(M.Tritium, 1));

		Centrifuge.addRecipe(GT.getFluid(M.Helium, 16), 0, euCost(18000), GT.get(glassTube, 15),
				GT.getFluid(M.Helium3, 1));

		Centrifuge.addRecipe("dustGreenSapphire", 4, 0, euCost(15000), GT.getDust(M.Sapphire, 4));

		Centrifuge.addRecipe("dustEnderEye", 16, 0, euCost(35000), GT.getDust(M.EnderPearl, 8),
				GT.get(Items.BLAZE_POWDER, 8));

		Centrifuge.addRecipe(GT.get(Items.MAGMA_CREAM, 1), 0, euCost(2500), GT.get(Items.BLAZE_POWDER, 1),
				GT.get(Items.SLIME_BALL, 1));

		Centrifuge.addRecipe("dirt", 16, 0, euCost(12500), GT.get(Blocks.SAND, 8),
				GT.getIc2(Ic2Items.compressedPlantBall, 1), GT.getIc2(Ic2Items.plantBall, 1),
				GT.get(Items.CLAY_BALL, 1));

		Centrifuge.addRecipe("grass", 16, 0, euCost(12500), GT.get(Blocks.SAND, 8),
				GT.getIc2(Ic2Items.compressedPlantBall, 2), GT.getIc2(Ic2Items.plantBall, 2),
				GT.get(Items.CLAY_BALL, 1));

		Centrifuge.addRecipe(GT.get(Blocks.MYCELIUM, 8), 0, euCost(8250), GT.get(Blocks.SAND, 4),
				GT.get(Blocks.BROWN_MUSHROOM, 2), GT.get(Blocks.RED_MUSHROOM, 2), GT.get(Items.CLAY_BALL, 1));

		Centrifuge.addRecipe(GT.get(Items.BLAZE_POWDER, 8), 0, euCost(15000), GT.getIc2(Ic2Items.coalDust, 2),
				GT.get(Items.GUNPOWDER, 1));

		Centrifuge.addRecipe("dustGlowstone", 16, 1, euCost(25000), GT.get(Items.REDSTONE, 7),
				GT.getIc2(Ic2Items.goldDust, 7), GT.getFluid(M.Helium, 1), GT.getDust(M.Phosphorus, 1));

		Centrifuge.addRecipe(GT.get(Items.GOLDEN_APPLE, 1), 1, euCost(50000), GT.getFluid(M.Methane, 1),
				GT.get(Items.GOLD_INGOT, 8));

		Centrifuge.addRecipe(GT.get(Items.GOLDEN_CARROT, 1), 1, euCost(50000), GT.getFluid(M.Methane, 1),
				GT.get(Items.GOLD_NUGGET, 6));

		Centrifuge.addRecipe(GT.get(Items.SPECKLED_MELON, 1), 1, euCost(50000), GT.getFluid(M.Methane, 1),
				GT.get(Items.GOLD_NUGGET, 6));

		Centrifuge.addRecipe("dustNetherrack", 16, 0, euCost(12000), GT.get(Items.GOLD_NUGGET, 1),
				GT.get(Items.REDSTONE, 1), GT.getDust(M.Sulfur, 4), GT.getIc2(Ic2Items.coalDust, 1));

		Centrifuge.addRecipe(GT.get(Blocks.LAVA, 16), 0, euCost(75000), GT.getIngot(M.Electrum, 1),
				GT.getIc2(Ic2Items.copperIngot, 4), GT.getSmallDust(M.Tungstate, 1));

		Centrifuge.addRecipe(GT.getIc2(Ic2Items.lavaCell, 16), 0, euCost(75000), GT.getIc2(Ic2Items.emptyCell, 16),
				GT.getIngot(M.Electrum, 1), GT.getIc2(Ic2Items.copperIngot, 4), GT.getSmallDust(M.Tungstate, 1));

		Centrifuge.addRecipe(GT.get(Blocks.MAGMA, 16), 0, euCost(75000), GT.getIngot(M.Electrum, 1),
				GT.getIc2(Ic2Items.copperIngot, 4), GT.getSmallDust(M.Tungstate, 1));

		Centrifuge.addRecipe(GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 2), 0, euCost(2500),
				GT.getIc2(Ic2Items.emptyCell, 2), GT.getDust(M.Thorium, 1));

		Centrifuge.addRecipe("dustUranium", 4, 0, euCost(250000), GT.getDust(M.Tungstate, 1),
				GT.getIc2(Ic2Items.reactorUraniumRodSingle, 4), GT.getSmallDust(M.Plutonium, 1),
				GT.getDust(M.Thorium, 2));

		Centrifuge.addRecipe(GT.getIc2(Ic2Items.reactorReEnrichedUraniumRod, 22), 0, euCost(100000),
				GT.getIc2(Ic2Items.emptyCell, 5), GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 3),
				GT.getDust(M.Plutonium, 1), GT.getDust(M.Thorium, 4));

		Centrifuge.addRecipe(GT.getIc2(Ic2Items.stickyResin, 4), 0, euCost(6500), GT.getIc2(Ic2Items.rubber, 14),
				GT.getIc2(Ic2Items.compressedPlantBall, 1), GT.getIc2(Ic2Items.plantBall, 1));

		Centrifuge.addRecipe("dustBrass", 1, 0, euCost(7500), GT.getSmallDust(M.Copper, 3), GT.getSmallDust(M.Zinc, 1));

		Centrifuge.addRecipe("dustBronze", 2, 0, euCost(7500), GT.getSmallDust(M.Copper, 6), GT.getSmallDust(M.Tin, 2));

		Centrifuge.addRecipe("dustSheldonite", 2, 0, euCost(15000), GT.getNugget(M.Iridium, 1),
				GT.getSmallDust(M.Platinum, 2));

		Centrifuge.addRecipe("dustElectrum", 2, 0, euCost(5000), GT.getSmallDust(M.Gold, 2),
				GT.getSmallDust(M.Silver, 2));

		Centrifuge.addRecipe("dustInvar", 2, 0, euCost(5000), GT.getIc2(Ic2Items.ironDust, 2), GT.getDust(M.Nickel, 1));

		Centrifuge.addRecipe("gemLapis", 4, 0, euCost(7500), GT.getSmallDust(M.Sodalite, 2), GT.getDust(M.Lazurite, 3),
				GT.getSmallDust(M.Pyrite, 1), GT.getSmallDust(M.Calcite, 1));

		Centrifuge.addRecipe("dustRedstone", 16, 3, euCost(35000), GT.getFluid(M.Mercury, 3), GT.getDust(M.Silicon, 1),
				GT.getDust(M.Pyrite, 5), GT.getDust(M.Ruby, 1));

		Centrifuge.addRecipe("dustEndstone", 16, 2, euCost(100000), GT.get(Blocks.SAND, 12), GT.getFluid(M.Helium3, 1),
				GT.getFluid(M.Helium, 1), GT.getSmallDust(M.Tungstate, 1));

		Centrifuge.addRecipe("dustDarkAshes", 2, 0, euCost(1250), GT.getDust(M.Ashes, 1), GT.getDust(M.Carbon, 1));

		Centrifuge.addRecipe("dustGranite", 4, 0, euCost(1000), GT.getDust(M.Calcite, 2), GT.getDust(M.Flint, 1),
				GT.getIc2(Ic2Items.clayDust, 1));

		Centrifuge.addRecipe("dustRedRock", 4, 0, euCost(1000), GT.getDust(M.Calcite, 2), GT.getDust(M.Flint, 1),
				GT.getIc2(Ic2Items.clayDust, 1));

		Centrifuge.addRecipe("dustMarble", 8, 0, euCost(6000), GT.getDust(M.Magnesium, 1), GT.getDust(M.Calcite, 7));

		Centrifuge.addRecipe("dustBasalt", 16, 0, euCost(10000), GT.getDust(M.DarkAshes, 4), GT.getDust(M.Olivine, 1),
				GT.getDust(M.Calcite, 3), GT.getDust(M.Flint, 8));

		Centrifuge.addRecipe(GT.getIc2(Ic2Items.woodGasCell, 5), 4, euCost(15000), GT.getDust(M.Plastic, 1),
				GT.getFluid(M.Hydrogen, 3), GT.getFluid(M.Methane, 1), GT.getIc2(Ic2Items.emptyCell, 5));

		Centrifuge.addRecipe("oreBasalt", 12, 0, euCost(15000), GT.getDust(M.Basalt, 9), GT.getDust(M.GarnetRed, 1),
				GT.getDust(M.GarnetYellow, 1), GT.getDust(M.Zirconium, 1));

		Centrifuge.addRecipe("dustRedGarnet", 16, 0, euCost(15000), GT.getDust(M.Pyrope, 3), GT.getDust(M.Almandine, 5),
				GT.getDust(M.Spessartine, 8));

		Centrifuge.addRecipe("dustYellowGarnet", 16, 0, euCost(15000), GT.getDust(M.Andradite, 5),
				GT.getDust(M.Grossular, 8), GT.getDust(M.Uvarovite, 3));

		Centrifuge.addRecipe("dustDirtyResin", 4, 0, euCost(8000), GT.getDust(M.Resin, 3), GT.getDust(M.Wood, 1));

		// Centrifuge.addCustomRecipe(GT.get(Blocks.SOUL_SAND, 16),
		// GT.getIc2(Ic2Items.fuelCan, 1), euCost(16000),
		// GT.get(Blocks.SAND, 10), GT.getIc2(Ic2Items.fullFuelCan, 1),
		// GT.getDust(M.Saltpeter, 4), GT.getIc2(Ic2Items.coalDust, 1));

	}

	public static void recipesCentrifuge2() {

		/*
		 * Recipes solely focused on getting methane from various things, at some point
		 * i will probably create some sort of dataset to collect all food and similar
		 * items from other mods.
		 */

		Centrifuge.addRecipe(GT.get(Items.APPLE, 32), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.MUSHROOM_STEW, 16), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.BREAD, 64), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.PORKCHOP, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.COOKED_PORKCHOP, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.BEEF, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.COOKED_BEEF, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.FISH, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.COOKED_FISH, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.CHICKEN, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.COOKED_CHICKEN, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.MUTTON, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.COOKED_MUTTON, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.MELON, 64), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Blocks.PUMPKIN, 16), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.SPIDER_EYE, 32), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.CARROT, 16), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.POTATO, 16), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.POISONOUS_POTATO, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.BAKED_POTATO, 24), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.COOKIE, 64), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.CAKE, 8), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Blocks.BROWN_MUSHROOM_BLOCK, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Blocks.RED_MUSHROOM_BLOCK, 12), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Blocks.BROWN_MUSHROOM, 32), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Blocks.RED_MUSHROOM, 32), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.NETHER_WART, 32), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.getIc2(Ic2Items.terraWart, 16), 1, euCost(25000), GT.getFluid(M.Methane, 1));

		Centrifuge.addRecipe(GT.get(Items.ROTTEN_FLESH, 16), 1, euCost(25000), GT.getFluid(M.Methane, 1),
				GT.get(Items.LEATHER, 6), GT.get(Items.SLIME_BALL, 1));

	}

	public static IRecipeModifier[] euCost(int amount) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((amount / 12) - 100) };
	}

}
