package gtclassic.recipe;

import static ic2.api.classic.recipe.ClassicRecipes.compressor;
import static ic2.api.classic.recipe.ClassicRecipes.macerator;

import gtclassic.GTConfig;
import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;

public class GTRecipeMods {

	public static void postInit() {
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
			// Dark steel upgrade
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] {
					input(GTMaterialGen.getModItem(GTValues.ENDERIO, "block_dark_iron_bars", 1)), input("itemClay", 1),
					input("string", 4) }, 120000, GTMaterialGen.getModItem(GTValues.ENDERIO, "item_dark_steel_upgrade"));
			// Electric Steel
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Iron", 1), input("dustCoal", 1),
					input("itemSilicon", 1) }, 40000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 0, 1));
			// Energenic Alloy
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustRedstone", 1), metal("Gold", 1),
					input("dustGlowstone", 1) }, 40000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 1, 1));
			// Vibrant Alloy
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("ingotEnergeticAlloy", 1),
					input("enderpearl", 1) }, 40000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 2, 1));
			// Redstone Alloy
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustRedstone", 1),
					input("itemSilicon", 1) }, 40000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 3, 1));
			// Conductive Iron
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustRedstone", 1),
					metal("Iron", 1) }, 40000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 4, 1));
			// Pulsating Alloy
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Iron", 1),
					input("enderpearl", 1) }, 40000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 5, 1));
			// Dark Steel
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Steel", 1),
					input("dustObsidian", 1) }, 80000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 6, 1));
			// End Steel
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("endstone", 1), input("ingotDarkSteel", 1),
					input("obsidian", 1) }, 80000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 8, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input(GTMaterialGen.get(Blocks.SOUL_SAND, 1)),
					metal("Gold", 1) }, 40000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_alloy_ingot", 7, 1));
			// Fused Quartz - Custom Recipe!
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustNetherQuartz", 2),
					input("blockGlass", 1) }, 10000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_fused_quartz", 1));
			// Enlightened Fused Quartz
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("gemQuartz", 4),
					input("dustGlowstone", 4) }, 20000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_enlightened_fused_quartz", 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("blockQuartz", 4),
					input("glowstone", 4) }, 20000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_enlightened_fused_quartz", 1));
			// Enlightned Clear Glass
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("blockGlass", 1),
					input("dustGlowstone", 4) }, 20000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_enlightened_fused_glass", 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("blockGlass", 1),
					input("glowstone", 1) }, 20000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_enlightened_fused_glass", 1));
			// Dark Fused Quartz
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dyeBlack", 1),
					input("gemQuartz", 4) }, 20000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_dark_fused_quartz", 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dyeBlack", 1),
					input("blockQuartz", 1) }, 20000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_dark_fused_quartz", 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dyeBlack", 1),
					input("blockGlassHardened", 1) }, 10000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_dark_fused_quartz", 1));
			// Dyes
			doEnderIOBlastFurnaceDyeThings("Green", 48);
			doEnderIOBlastFurnaceDyeThings("Brown", 49);
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustCoal", 6),
					input("slimeball", 1) }, 8000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 50, 2));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustCoal", 3),
					input("egg", 1) }, 6000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 50, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustCharcoal", 6),
					input("slimeball", 1) }, 8000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 50, 2));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustCharcoal", 3),
					input("egg", 1) }, 6000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 50, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input(GTMaterialGen.get(Items.BEETROOT, 1)),
					input("itemClay", 3), input("egg", 6) }, 60000, new ItemStack(Items.DYE, 12, 1));
			// Machine Chassis
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("itemSimpleMachineChassi", 1),
					input("dyeMachine", 1) }, 14400, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 1, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("itemEndSteelMachineChassi", 1),
					input("dyeEnhancedMachine", 1) }, 14400, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 54, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("itemSimpleMachineChassi", 1),
					input("dyeSoulMachine", 1) }, 14400, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 53, 1));
			// Other Stuff
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustLapis", 1), input("blockWool", 1),
					input("dustTin", 1) }, 20000, GTMaterialGen.getModItem(GTValues.ENDERIO, "block_industrial_insulation", 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("ingotBrickNether", 1),
					input("cropNetherWart", 4),
					input("itemClay", 6) }, 80000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 72, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustBedrock", 1),
					input("dustCoal", 1) }, 20000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 75, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustBedrock", 1),
					input("dustCharcoal", 1) }, 20000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 75, 1));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dustGlowstone", 1),
					input("itemClay", 1) }, 20000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", 76, 2));
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
			// Adds alloys if thermal is not present
			if (!Loader.isModLoaded(GTValues.THERMAL) || !GTConfig.compatThermal) {
				// Constantan
				GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Copper", 1),
						metal("Nickel", 1) }, GTTileMultiBlastFurnace.COST_TINY, GTMaterialGen.getModMetaItem(GTValues.IMMERSIVE_ENGINEERING, "metal", 6, 2));
			}
		}
		/** Thermal Mods **/
		if (GTConfig.compatThermal && Loader.isModLoaded(GTValues.THERMAL)) {
			GTMod.logger.info("Doing Thermal Expansion Things");
			// Hardended Glass
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Lead", 1),
					input("dustObsidian", 4) }, GTTileMultiBlastFurnace.COST_SMALL, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "glass", 3, 2));
			// Invar
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Iron", 2),
					metal("Nickel", 1) }, GTTileMultiBlastFurnace.COST_TINY, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 162, 3));
			// Constantan
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Copper", 1),
					metal("Nickel", 1) }, GTTileMultiBlastFurnace.COST_TINY, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 164, 2));
			// Signalum
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Copper", 3), metal("Silver", 1),
					input("dustRedstone", 9) }, GTTileMultiBlastFurnace.COST_SMALL, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 165, 4));
			// Lumium
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Tin", 3), metal("Silver", 1),
					input("dustGlowstone", 4) }, GTTileMultiBlastFurnace.COST_MED, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 166, 4));
			// Enderium
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Lead", 3), metal("Platinum", 1),
					input("dustEnderPearl", 4) }, GTTileMultiBlastFurnace.COST_MED, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 167, 4));
			// Adding thermal stuff to fluid gen
			addFluidGeneratorRecipe("crude_oil", 50000, 10);
			addFluidGeneratorRecipe("redstone", 10000, 10);
			addFluidGeneratorRecipe("glowstone", 25000, 12);
			addFluidGeneratorRecipe("pyrotheum", 200000, 36);
			addFluidGeneratorRecipe("cryotheum", 50000, 12);
			addFluidGeneratorRecipe("aerotheum", 50000, 12);
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
			// Just two fun Electrolyer recipes
			ClassicRecipes.electrolyzer.addChargeRecipe(GTMaterialGen.getModMetaItem(GTValues.THERMAL, "bait", 1, 1), GTMaterialGen.getModMetaItem(GTValues.THERMAL, "bait", 2, 1), 16000, "ThermalBait");
			ClassicRecipes.electrolyzer.addChargeRecipe(GTMaterialGen.getModMetaItem(GTValues.THERMAL, "fertilizer", 1, 1), GTMaterialGen.getModMetaItem(GTValues.THERMAL, "fertilizer", 2, 1), 16000, "ThermalFertilizer");
		}
		/** Forestry Sub Module, last so bees are always last **/
		if (GTConfig.compatForestry && Loader.isModLoaded(GTValues.FORESTRY)) {
			GTMod.logger.info("Doing Forestry Things");
			GTRecipeForestry.notTheBees();
		}
	}

	public static void doEnderIOBlastFurnaceDyeThings(String color, int meta) {
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("itemPlant" + color.toLowerCase(), 12),
				input("slimeball", 1) }, 8000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", meta, 2));
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("itemPlant" + color.toLowerCase(), 6),
				input("egg", 1) }, 6000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", meta, 1));
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dye" + color, 2), input("slimeball", 1),
				input("dustCoal", 2) }, 8000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", meta, 2));
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dye" + color, 2), input("slimeball", 1),
				input("dustCharcoal", 2) }, 8000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", meta, 2));
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dye" + color, 1), input("egg", 1),
				input("dustCoal", 1) }, 6000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", meta, 1));
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { input("dye" + color, 1), input("egg", 1),
				input("dustCharcoal", 1) }, 6000, GTMaterialGen.getModMetaItem(GTValues.ENDERIO, "item_material", meta, 1));
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
