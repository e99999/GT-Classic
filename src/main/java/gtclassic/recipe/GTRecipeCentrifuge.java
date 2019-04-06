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

public class GTRecipeCentrifuge {

	static GTMaterialGen GT;
	static GTMaterial M;
	static final Item glassTube = GTItems.testTube;

	public static void recipesCentrifuge1() {

		/*
		 * Back to normal recipes
		 */
		GTTileIndustrialCentrifuge.addRecipe("logRubber", 16, 4, euCost(25000),
				new OutputItem(GT.getDust(M.Carbon, 8), 0), new OutputItem(GT.getIc2(Ic2Items.stickyResin, 8), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 6), 2), new OutputItem(GT.getFluid(M.Methane, 4), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getFluid(M.Hydrogen, 4), 0, euCost(6000),
				new OutputItem(GT.get(glassTube, 3), 0), new OutputItem(GT.getFluid(M.Deuterium, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getFluid(M.Deuterium, 4), 0, euCost(6000),
				new OutputItem(GT.get(glassTube, 3), 0), new OutputItem(GT.getFluid(M.Tritium, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.getFluid(M.Helium, 16), 0, euCost(18000),
				new OutputItem(GT.get(glassTube, 15), 0), new OutputItem(GT.getFluid(M.Helium3, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustGreenSapphire", 4, 0, euCost(15000),
				new OutputItem(GT.getDust(M.Sapphire, 4), 0));

		GTTileIndustrialCentrifuge.addRecipe("dustEnderEye", 16, 0, euCost(35000),
				new OutputItem(GT.getDust(M.EnderPearl, 8), 0), new OutputItem(GT.get(Items.BLAZE_POWDER, 8), 1));

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

		GTTileIndustrialCentrifuge.addRecipe("dustGlowstone", 16, 0, euCost(25000),
				new OutputItem(GT.get(Items.REDSTONE, 8), 0), new OutputItem(GT.getIc2(Ic2Items.goldDust, 8), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.GOLDEN_APPLE, 1), 1, euCost(50000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0), new OutputItem(GT.get(Items.GOLD_INGOT, 8), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.GOLDEN_CARROT, 1), 1, euCost(50000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0), new OutputItem(GT.get(Items.GOLD_NUGGET, 6), 1));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.SPECKLED_MELON, 1), 1, euCost(50000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0), new OutputItem(GT.get(Items.GOLD_NUGGET, 6), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustNetherrack", 16, 0, euCost(12000),
				new OutputItem(GT.get(Items.GOLD_NUGGET, 1), 0), new OutputItem(GT.get(Items.REDSTONE, 1), 1),
				new OutputItem(GT.getDust(M.Sulfur, 4), 2), new OutputItem(GT.getIc2(Ic2Items.coalDust, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.LAVA, 16), 0, euCost(75000),
				new OutputItem(GT.getIngot(M.Electrum, 1), 0), new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 1),
				new OutputItem(GT.getSmallDust(M.Tungstate, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.lavaCell, 16), 0, euCost(75000),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 16), 0), new OutputItem(GT.getIngot(M.Electrum, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 2),
				new OutputItem(GT.getSmallDust(M.Tungstate, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.MAGMA, 16), 0, euCost(75000),
				new OutputItem(GT.getIngot(M.Electrum, 1), 0), new OutputItem(GT.getIc2(Ic2Items.copperIngot, 4), 1),
				new OutputItem(GT.getSmallDust(M.Tungstate, 1), 2));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 2), 0, euCost(2500),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 2), 0), new OutputItem(GT.getDust(M.Thorium, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustUranium", 4, 0, euCost(250000),
				new OutputItem(GT.getDust(M.Tungstate, 1), 0),
				new OutputItem(GT.getIc2(Ic2Items.reactorUraniumRodSingle, 4), 1),
				new OutputItem(GT.getSmallDust(M.Plutonium, 1), 2), new OutputItem(GT.getDust(M.Thorium, 2), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.reactorReEnrichedUraniumRod, 22), 0, euCost(100000),
				new OutputItem(GT.getIc2(Ic2Items.emptyCell, 5), 0),
				new OutputItem(GT.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 3), 1),
				new OutputItem(GT.getDust(M.Plutonium, 1), 2), new OutputItem(GT.getDust(M.Thorium, 4), 3));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.stickyResin, 4), 0, euCost(6500),
				new OutputItem(GT.getIc2(Ic2Items.rubber, 14), 0),
				new OutputItem(GT.getIc2(Ic2Items.compressedPlantBall, 1), 1),
				new OutputItem(GT.getIc2(Ic2Items.plantBall, 1), 2), new OutputItem(GT.getDust(M.Plastic, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustBrass", 1, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Copper, 3), 0), new OutputItem(GT.getSmallDust(M.Zinc, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustBronze", 2, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Copper, 6), 0), new OutputItem(GT.getSmallDust(M.Tin, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustSheldonite", 2, 0, euCost(15000),
				new OutputItem(GT.getNugget(M.Iridium, 1), 0), new OutputItem(GT.getSmallDust(M.Platinum, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustElectrum", 2, 0, euCost(5000),
				new OutputItem(GT.getSmallDust(M.Gold, 2), 0), new OutputItem(GT.getSmallDust(M.Silver, 2), 1));

		GTTileIndustrialCentrifuge.addRecipe("dustInvar", 2, 0, euCost(5000),
				new OutputItem(GT.getIc2(Ic2Items.ironDust, 2), 0), new OutputItem(GT.getDust(M.Nickel, 1), 1));

		GTTileIndustrialCentrifuge.addRecipe("gemLapis", 4, 0, euCost(7500),
				new OutputItem(GT.getSmallDust(M.Sodalite, 2), 0), new OutputItem(GT.getDust(M.Lazurite, 3), 1),
				new OutputItem(GT.getSmallDust(M.Pyrite, 1), 2), new OutputItem(GT.getSmallDust(M.Calcite, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustRedstone", 16, 3, euCost(35000),
				new OutputItem(GT.getFluid(M.Mercury, 3), 0), new OutputItem(GT.getDust(M.Silicon, 1), 1),
				new OutputItem(GT.getDust(M.Pyrite, 5), 2), new OutputItem(GT.getDust(M.Ruby, 1), 3));

		GTTileIndustrialCentrifuge.addRecipe("dustEndstone", 16, 2, euCost(100000),
				new OutputItem(GT.get(Blocks.SAND, 12), 0), new OutputItem(GT.getFluid(M.Helium3, 1), 1),
				new OutputItem(GT.getFluid(M.Helium, 1), 2), new OutputItem(GT.getSmallDust(M.Tungstate, 1), 3));

		// GTTileIndustrialCentrifuge.addRecipe("dustRedGarnet", 16, 0, euCost(15000),
		// new OutputItem(GT.getDust(M.Pyrope, 3), 0), new
		// OutputItem(GT.getDust(M.Almandine, 5), 1),
		// new OutputItem(GT.getDust(M.Spessartine, 8), 2));

		// GTTileIndustrialCentrifuge.addRecipe("dustYellowGarnet", 16, 0,
		// euCost(15000),
		// new OutputItem(GT.getDust(M.Andradite, 5), 0), new
		// OutputItem(GT.getDust(M.Grossular, 8), 1),
		// new OutputItem(GT.getDust(M.Uvarovite, 3), 2));

		GTTileIndustrialCentrifuge.addRecipe("dustDarkAshes", 2, 0, euCost(1250),
				new OutputItem(GT.getDust(M.Ashes, 1), 0), new OutputItem(GT.getDust(M.Carbon, 1), 1));

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
	}

	public static void recipesCentrifuge2() {

		/*
		 * Recipes solely focused on getting methane from various things, at some point
		 * i will probably create some sort of dataset to collect all food and similar
		 * items from other mods.
		 */

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.APPLE, 32), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.MUSHROOM_STEW, 16), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.BREAD, 64), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.PORKCHOP, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_PORKCHOP, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.BEEF, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_BEEF, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.FISH, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_FISH, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.CHICKEN, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_CHICKEN, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.MUTTON, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKED_MUTTON, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.MELON, 64), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.PUMPKIN, 16), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.SPIDER_EYE, 32), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.CARROT, 16), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.POTATO, 16), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.POISONOUS_POTATO, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.BAKED_POTATO, 24), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.COOKIE, 64), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.CAKE, 8), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.BROWN_MUSHROOM_BLOCK, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.RED_MUSHROOM_BLOCK, 12), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.BROWN_MUSHROOM, 32), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Blocks.RED_MUSHROOM, 32), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.NETHER_WART, 32), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.getIc2(Ic2Items.terraWart, 16), 1, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0));

		GTTileIndustrialCentrifuge.addRecipe(GT.get(Items.ROTTEN_FLESH, 16), 4, euCost(25000),
				new OutputItem(GT.getFluid(M.Methane, 1), 0), new OutputItem(GT.get(Items.LEATHER, 2), 1),
				new OutputItem(GT.get(Items.SLIME_BALL, 1), 2));

	}

	public static IRecipeModifier[] euCost(int amount) {
		return new IRecipeModifier[] {
				ModifierType.RECIPE_LENGTH.create((amount / 12) - GTTileIndustrialCentrifuge.defaultLength) };
	}

}
