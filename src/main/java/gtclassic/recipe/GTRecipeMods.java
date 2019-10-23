package gtclassic.recipe;

import static ic2.api.classic.recipe.ClassicRecipes.compressor;
import static ic2.api.classic.recipe.ClassicRecipes.macerator;

import gtclassic.GTConfig;
import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.helpers.GTHelperAdvRecipe;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;

public class GTRecipeMods {

	public static void postInit() {
		/** Stuff for people too slow to handle ore dict at the right time **/
		GTTileCentrifuge.addRecipe("dustDiamond", 1, 0, GTTileCentrifuge.totalEu(100000), GTMaterialGen.getDust(GTMaterial.Carbon, 64));
		if (GTConfig.compatBuildcraft && Loader.isModLoaded(GTValues.BUILDCRAFT)) {
			// Classic GT Quarry stuff
			GTHelperAdvRecipe.removeRecipe(GTValues.BUILDCRAFT, "quarry");
			GTRecipe.recipes.addRecipe(GTMaterialGen.getModItem(GTValues.BUILDCRAFT, "quarry"), new Object[] { "ICI",
					"GIG", "DRD", 'I', "gearIron", 'C', "circuitAdvanced", 'G', "gearGold", 'D', "gearDiamond", 'R',
					Ic2Items.diamondDrill.copy() });
		}
		/** EnderIO **/
		if (GTConfig.compatEnderIO && Loader.isModLoaded(GTValues.ENDERIO)) {
			GTMod.logger.info("Doing EnderIO Things");
			// Creating a relationship between Enderio Iron Alloy and Mixed Metal
			compressor.removeRecipe(input(Ic2Items.mixedMetalIngot.copy()));
			TileEntityCompressor.addRecipe(Ic2Items.mixedMetalIngot, 1, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 9, 1));
			TileEntityCompressor.addRecipe("ingotConstructionAlloy", 1, GTMaterialGen.getIc2(Ic2Items.advancedAlloy, 1));
			// Macerator
			TileEntityMacerator.addRecipe("itemPulsatingCrystal", 1, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 36, 1));
			TileEntityMacerator.addRecipe("itemVibrantCrystal", 1, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 35, 1));
			TileEntityMacerator.addRecipe("itemEnderCrystal", 1, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 37, 1));
			TileEntityMacerator.addRecipe("itemPrescientCrystal", 1, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 34, 1));
			TileEntityMacerator.addRecipe("gemQuartz", 1, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 33, 1));
			TileEntityMacerator.addRecipe("gemLapis", 1, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 32, 1));
			TileEntityMacerator.addRecipe("dustBedrock", 1, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_infinity_fog"));
			TileEntityMacerator.addRecipe("itemClayedGlowstone", 1, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_holy_fog"));
		}
		/** Ic2 Extras **/
		if (GTConfig.compatIc2Extras && Loader.isModLoaded(GTValues.IC2_EXTRAS)) {
			GTMod.logger.info("Doing IC2 Extras Things");
			TileEntityMacerator.addRecipe("crushedUranium", 1, GTMaterialGen.getDust(GTMaterial.Uranium, 1));
			TileEntityMacerator.addRecipe("crushedPurifiedUranium", 1, GTMaterialGen.getDust(GTMaterial.Uranium, 1));
			GTTileCentrifuge.addRecipe("dustUranium", 22, 0, GTTileCentrifuge.totalEu(250000), GTMaterialGen.getDust(GTMaterial.Tungsten, 1), GTMaterialGen.getModItem(GTValues.IC2_EXTRAS, "uranium238", 16), GTMaterialGen.getDust(GTMaterial.Plutonium, 1), GTMaterialGen.getDust(GTMaterial.Thorium, 4));
			GTTileCentrifuge.addRecipe("dustThorium", 4, 0, GTTileCentrifuge.totalEu(5000), GTMaterialGen.getModItem(GTValues.IC2_EXTRAS, "thorium232dust", 2));
			GTTileCentrifuge.addRecipe("dustThorium232", 2, 0, GTTileCentrifuge.totalEu(5000), GTMaterialGen.getModItem(GTValues.IC2_EXTRAS, "thorium230dust", 1));
			GTRecipe.recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.rodThorium1, 1), GTMaterialGen.getModItem(GTValues.IC2_EXTRAS, "emptyfuelrod"), GTMaterialGen.getIngot(GTMaterial.Thorium, 1));
			GTRecipe.recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.rodPlutonium1, 1), GTMaterialGen.getModItem(GTValues.IC2_EXTRAS, "emptyfuelrod"), GTMaterialGen.getIngot(GTMaterial.Plutonium, 1));
		} else {
			// If Ic2 Extras is not loaded, run regular recipes
			macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
			TileEntityMacerator.addRecipe("oreUranium", 1, GTMaterialGen.getDust(GTMaterial.Uranium, 2));
			TileEntityMacerator.addRecipe(GTMaterialGen.getIc2(Ic2Items.uraniumDrop, 1), GTMaterialGen.getDust(GTMaterial.Uranium, 1));
			TileEntityCompressor.addRecipe("dustPlutonium", 1, GTMaterialGen.getIngot(GTMaterial.Plutonium, 1));
			GTTileCentrifuge.addCustomRecipe("dustUranium", 22, GTMaterialGen.getIc2(Ic2Items.emptyCell, 16), GTTileCentrifuge.totalEu(250000), GTMaterialGen.getDust(GTMaterial.Tungsten, 1), GTMaterialGen.getIc2(Ic2Items.reactorUraniumRodSingle, 16), GTMaterialGen.getDust(GTMaterial.Plutonium, 1), GTMaterialGen.getDust(GTMaterial.Thorium, 4));
			GTTileCentrifuge.addRecipe(GTMaterialGen.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 1), 0, GTTileCentrifuge.totalEu(2500), GTMaterialGen.getIc2(Ic2Items.emptyCell, 1), GTMaterialGen.getDust(GTMaterial.Thorium, 1));
			GTRecipe.recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.rodThorium1, 1), GTMaterialGen.getIc2(Ic2Items.emptyCell, 1), GTMaterialGen.getIngot(GTMaterial.Thorium, 1));
			GTRecipe.recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.rodPlutonium1, 1), GTMaterialGen.getIc2(Ic2Items.emptyCell, 1), GTMaterialGen.getIngot(GTMaterial.Plutonium, 1));
		}
		/** Immersive Engineering **/
		if (GTConfig.compatIE && Loader.isModLoaded(GTValues.IMMERSIVE_ENGINEERING)) {
			GTMod.logger.info("Doing Immersive Engineering Things");
			GTTileCentrifuge.addRecipe("dustCoke", 8, 0, GTTileCentrifuge.totalEu(7500), GTMaterialGen.getModMetaItem(GTValues.IMMERSIVE_ENGINEERING, "material", 18, 1));
		}
		/** Thermal Mods **/
		if (GTConfig.compatThermal && Loader.isModLoaded(GTValues.THERMAL)) {
			GTMod.logger.info("Doing Thermal Expansion Things");
			// Adding thermal stuff to fluid gen
			addFluidGeneratorRecipe("crude_oil", 50000, 10);
			addFluidGeneratorRecipe("petrotheum", 50000, 12);
			addFluidGeneratorRecipe("creosote", 5000, 8);
			addFluidGeneratorRecipe("coal", 50000, 10);
			addFluidGeneratorRecipe("refined_oil", 150000, 24);
			addFluidGeneratorRecipe("refined_fuel", 200000, 32);
			addFluidGeneratorRecipe("seed_oil", 6000, 6);
			addFluidGeneratorRecipe("tree_oil", 50000, 8);
			addFluidGeneratorRecipe("refined_biofuel", 100000, 10);
			// Oil sand stuff
			GTTileCentrifuge.addRecipe("oreClathrateOilSand", 2, 0, GTTileCentrifuge.totalEu(8000), GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 892, 6), GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 833, 1));
			GTTileCentrifuge.addRecipe("oreClathrateOilShale", 2, 0, GTTileCentrifuge.totalEu(8000), GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 892, 6), GTMaterialGen.get(Items.FLINT));
			// Macerator Stuff
			TileEntityMacerator.addRecipe("oreClathrateRedstone", 1, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 893, 3));
			TileEntityMacerator.addRecipe("oreClathrateGlowstone", 1, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 894, 3));
			TileEntityMacerator.addRecipe("oreClathrateEnder", 1, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 895, 3));
			// Just two fun Electrolyzer recipes
			ClassicRecipes.electrolyzer.addChargeRecipe(GTMaterialGen.getModMetaItem(GTValues.THERMAL, "bait", 1, 1), GTMaterialGen.getModMetaItem(GTValues.THERMAL, "bait", 2, 1), 16000, "ThermalBait");
			ClassicRecipes.electrolyzer.addChargeRecipe(GTMaterialGen.getModMetaItem(GTValues.THERMAL, "fertilizer", 1, 1), GTMaterialGen.getModMetaItem(GTValues.THERMAL, "fertilizer", 2, 1), 16000, "ThermalFertilizer");
		}
		/** Forestry Sub Module, last so bees are always last **/
		if (GTConfig.compatForestry && Loader.isModLoaded(GTValues.FORESTRY)) {
			GTMod.logger.info("Doing Forestry Things");
			GTHelperAdvRecipe.removeRecipe(GTValues.FORESTRY, "bronze_ingot");
			GTTileCentrifuge.addRecipe("gemApatite", 5, 0, GTTileCentrifuge.totalEu(4000), GTMaterialGen.getDust(GTMaterial.Calcite, 4), GTMaterialGen.getIc2(Ic2Items.fertilizer, 1));
			GTRecipeForestry.notTheBees();
		}
	}

	public static void addFluidGeneratorRecipe(String fluid, int total, int output) {
		int translation = total / output;
		ClassicRecipes.fluidGenerator.addEntry(FluidRegistry.getFluid(fluid), translation, output);
	}

	public static IRecipeInput input(ItemStack stack) {
		return new RecipeInputItemStack(stack);
	}

	public static IRecipeInput input(String name, int amount) {
		return new RecipeInputOreDict(name, amount);
	}

	public static IRecipeInput tubes(int amount) {
		return new RecipeInputItemStack(GTMaterialGen.get(GTItems.testTube, amount));
	}

	public static IRecipeInput metal(String type, int amount) {
		return new RecipeInputCombined(amount, new IRecipeInput[] { new RecipeInputOreDict("ingot" + type, amount),
				new RecipeInputOreDict("dust" + type, amount), });
	}
}
