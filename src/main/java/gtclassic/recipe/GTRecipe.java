package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.multi.GTTileMultiFusion;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.item.recipe.upgrades.EnchantmentModifier;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;

public class GTRecipe {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static String ingotRefinedIron = IC2.getRefinedIron();
	static IRecipeInput ingotElectric = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict(ingotRefinedIron), new RecipeInputOreDict("ingotSilver"),
			new RecipeInputOreDict("ingotAluminium") });
	static IRecipeInput lowCrystal = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("gemDiamond"), new RecipeInputOreDict("gemRuby") });
	static IRecipeInput highCrystal = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("gemSapphire"), new RecipeInputItemStack(Ic2Items.energyCrystal.copy()) });
	static IRecipeInput lapis = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("gemLapis"),
			new RecipeInputOreDict("dustLazurite"), new RecipeInputOreDict("dustSodalite") });
	static IRecipeInput advComponent = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("dustGlowstone"), new RecipeInputOreDict("ingotSilver") });

	/*
	 * For now this set of recipes is heavily broken apart which allows me to
	 * reconfigure them with clarity. After the progression is finalized, all
	 * recipes with be in this class
	 */
	public static void init() {
		GTRecipeIterators.recipeIterators1();
		GTRecipeProcessing.recipesProcessing();
		// below is more how things will go
		GTTileCentrifuge.init();
		GTTileMultiFusion.init();
		shapeless();
		items();
		blocks();
		ic2();
	}

	public static void shapeless() {
	}

	public static void items() {
		/** Test Tube **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.testTube, 32), new Object[] { "G G", "G G", " G ", 'G',
				"blockGlass" });
		/** Destructo Pack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.destructoPack, 1), new Object[] { "CIC", "ILI", "CIC", 'L',
				GTValues.lava, 'C', "circuitBasic", 'I', ingotRefinedIron });
		/** Electro Magnet **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.electroMagnet, 1), new Object[] { "M M", "WMW", "IBI", 'M',
				Ic2Items.magnet, 'B', Ic2Items.battery, 'I', ingotRefinedIron, 'W', Ic2Items.copperCable });
		/** Rock Cutter **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.rockCutter, 1), new Object[] { "DI ", "DI ", "DCB",
				new EnchantmentModifier(GTMaterialGen.get(GTItems.rockCutter), Enchantments.SILK_TOUCH).setUsesInput(),
				'D', "gemDiamond", 'I', ingotRefinedIron, 'C', "circuitBasic", 'B', Ic2Items.battery.copy() });
		/** Duct Tape **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.ductTape), new Object[] { "XX ", "XX ", "   ", 'X',
				GTMaterialGen.getIc2(Ic2Items.rubber, 64) });
		/** Helium Reactor Coolant **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageSingle, 1), new Object[] { " I ", "IHI", " I ", 'I',
				"ingotTin", 'H', GTMaterialGen.getFluid(GTMaterial.Helium, 1) });
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageTriple, 1), new Object[] { "III", "HHH", "III", 'I',
				"ingotTin", 'H', GTItems.heatStorageSingle });
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageSix, 1), new Object[] { "IHI", "IPI", "IHI", 'I',
				"ingotTin", 'H', GTItems.heatStorageTriple, 'P', Ic2Items.denseCopperPlate });
		/** Lithium Battery **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.lithiumBattery, 1), new Object[] { " G ", "ALA", "ALA", 'G',
				Ic2Items.goldCable.copy(), 'A', "ingotAluminium", 'L', "dustLithium" });
		/** Lithium BatPack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.lithiumBatpack, 1), new Object[] { "LCL", "LAL", "L L", 'C',
				"circuitAdvanced", 'A', "ingotAluminium", 'L', GTItems.lithiumBattery });
		/** Energy Control Circuit **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.circuitEnergy, 4), new Object[] { "CLC", "LPL", "CLC", 'L',
				Ic2Items.lapotronCrystal.copy(), 'C', "circuitAdvanced", 'P', "plateIridium" });
		/** Data Control Circuit **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.circuitData, 4), new Object[] { "CDC", "DPD", "CDC", 'D',
				GTItems.chipData, 'C', "circuitAdvanced", 'P', "plateIridium" });
		/** Data Chip **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.chipData, 4), new Object[] { "EEE", "ECE", "EEE", 'E', "gemEmerald",
				'C', "circuitAdvanced" });
		/** Lapotronic Energy Orb **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.orbEnergy, 1), new Object[] { "LLL", "LPL", "LLL", 'L',
				Ic2Items.lapotronCrystal.copy(), 'P', "plateIridium" });
		/** Data Orb **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.orbData, 4), new Object[] { "SSS", "SCS", "SSS", 'S',
				GTItems.circuitData, 'C', GTItems.chipData });
		/** Super Conductor **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), new Object[] { "CCC", "PWP", "EEE", 'C',
				Ic2Items.reactorCoolantCellSix.copy(), 'E', GTItems.circuitEnergy, 'W', "ingotTungsten", 'P',
				"plateIridium" });
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), new Object[] { "CCC", "PWP", "EEE", 'C',
				GTItems.heatStorageTriple, 'E', GTItems.circuitEnergy, 'W', "ingotTungsten", 'P', "plateIridium" });
		/** Lapotron Batpack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.lapotronPack, 1), new Object[] { "ELE", "SBS", "EPE", 'E',
				GTItems.circuitEnergy, 'S', GTItems.superConductor, 'L', GTItems.orbEnergy, 'B',
				Ic2Items.lapPack.copy(), 'P', "plateIridium" });
		/** Tesla Staff **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.teslaStaff, 1), new Object[] { "LS ", "SP ", "  P", 'L',
				GTItems.orbEnergy, 'S', GTItems.superConductor, 'P', "plateIridium" });
		/** Survival Scanner **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.portableScanner, 1), new Object[] { "PEP", "CFC", "PBP", 'P',
				"ingotAluminium", 'E', GTMaterialGen.getIc2(Ic2Items.euReader, 1), 'F',
				GTMaterialGen.getIc2(Ic2Items.cropAnalyzer, 1), 'C', "circuitAdvanced", 'B',
				GTMaterialGen.get(GTItems.lithiumBattery) });
	}

	public static void blocks() {
		/** Highly Advanced Machine Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), new Object[] { "CTC", "TBT", "CTC", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', Ic2Items.advMachine.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), new Object[] { "TCT", "CBC", "TCT", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', Ic2Items.advMachine.copy() });
		/** Fusion Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingFusion), new Object[] { "CBY", "BRB", "YBS", 'B',
				GTBlocks.casingHighlyAdvanced, 'C', GTItems.circuitEnergy, 'S', GTItems.superConductor, 'Y',
				Ic2Items.teslaCoil.copy(), 'B', Ic2Items.advMachine.copy(), 'R',
				Ic2Items.reactorReflectorIridium.copy() });
		/** LESU Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingLESU), new Object[] { "BBB", "BCB", "BBB", 'B', "blockLapis",
				'C', "circuitBasic" });
		/** Industrial Centrifuge **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', ingotRefinedIron, 'A', Ic2Items.advMachine, 'C', "circuitAdvanced" });
		/** Lightning Rod **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileLightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				GTItems.circuitEnergy, 'S', GTItems.superConductor, 'A', GTBlocks.casingHighlyAdvanced });
		/** Fusion Computer **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileFusionComputer, 1), new Object[] { "ESE", "LCL", "ESE", 'E',
				GTItems.circuitEnergy, 'S', GTItems.superConductor, 'L', GTItems.orbEnergy, 'C',
				GTBlocks.tileComputer });
		/** Player Detector **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tilePlayerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
				Blocks.OBSERVER, 'C', "circuitBasic", 'c', Ic2Items.advMachine });
		/** Computer Cube **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileComputer, 1), new Object[] { "RGD", "GMG", "DGR", 'D',
				GTItems.orbData, 'R', GTItems.circuitEnergy, 'G', "blockGlass", 'M', Ic2Items.advMachine.copy() });
		/** Energy Storage **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBasicEnergy), new Object[] { "OOO", "OCO", "OOO", 'O',
				GTItems.orbEnergy, 'C', GTBlocks.tileComputer });
		/** Quantum Chest **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumChest, 1), new Object[] { "IDI", "MCM", "IDI", 'D',
				GTItems.orbData, 'I', "ingotChrome", 'C', "chestWood", 'M', Ic2Items.advMachine.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumChest, 1), new Object[] { "IDI", "MCM", "IDI", 'D',
				GTItems.orbData, 'I', "ingotTitanium", 'C', "chestWood", 'M', Ic2Items.advMachine.copy() });
		/** Stuff that is not ready yet **/
		if (GTValues.debugMode) {
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileAssembler, 1), new Object[] { "dCd", "TQE", "DBD", 'd',
					GTItems.circuitData, 'C', GTBlocks.tileComputer, 'T', Ic2Items.teleporter, 'Q',
					GTBlocks.tileQuantumChest, 'E', Ic2Items.industrialWorktable, 'D', GTItems.orbData, 'B',
					"batteryAdvanced" });
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileFabricator, 1), new Object[] { "ETE", "HLH", "ETE", 'E',
					GTItems.circuitEnergy, 'T', Ic2Items.teleporter, 'H', GTBlocks.casingHighlyAdvanced, 'L',
					GTItems.orbEnergy });
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileChargeOmat, 1), new Object[] { "RCR", "AEA", "RMR", 'E',
					GTItems.orbEnergy, 'R', GTItems.circuitEnergy, 'A', "chestWood", 'C', GTBlocks.tileComputer, 'M',
					Ic2Items.advMachine.copy() });
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumEnergy, 1), new Object[] { "PHP", "HEH", "PHP", 'P',
					"plateIridium", 'H', GTBlocks.tileBasicEnergy, 'E', Blocks.ENDER_CHEST });
		}
	}

	public static void ic2() {
		recipes.addRecipe(Ic2Items.miningLaser.copy(), new Object[] { "Rcc", "AAC", " AA", 'A',
				Ic2Items.advancedAlloy.copy(), 'C', "circuitAdvanced", 'c',
				GTMaterialGen.getFluid(GTMaterial.Helium, 1), 'R', "dustRedstone" });
		recipes.addRecipe(Ic2Items.reactorReflectorThick.copy(), new Object[] { " P ", "PBP", " P ", 'P',
				Ic2Items.reactorReflector, 'B', GTMaterialGen.getFluid(GTMaterial.Beryllium, 1) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), new Object[] { "III", "GHG", "GGG", 'G',
				"blockGlass", 'I', "ingotSilver", 'H', GTMaterialGen.getFluid(GTMaterial.Helium, 1), 'C',
				Ic2Items.insulatedCopperCable.copy() });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), new Object[] { "III", "GHG", "GGG", 'G',
				"blockGlass", 'I', "ingotSilver", 'H', GTMaterialGen.getFluid(GTMaterial.Mercury, 1), 'C',
				Ic2Items.insulatedCopperCable.copy() });
		recipes.addRecipe(Ic2Items.mfe.copy(), new Object[] { "XYX", "YCY", "XYX", 'C', Ic2Items.machine.copy(), 'Y',
				GTItems.lithiumBattery, 'X', Ic2Items.doubleInsulatedGoldCable.copy() });
		recipes.addRecipe(Ic2Items.mfe.copy(), new Object[] { "XYX", "YCY", "XYX", 'C', Ic2Items.machine.copy(), 'Y',
				GTItems.lithiumBattery, 'X', GTMaterialGen.getIc2(Ic2Items.doubleInsulatedBronzeCable, 4) });
		recipes.overrideRecipe("shaped_item.itemPartIridium_1100834802", GTMaterialGen.getIc2(Ic2Items.iridiumPlate, 1), "IAI", "ADA", "IAI", 'I', "ingotIridium", 'A', Ic2Items.advancedAlloy.copy(), 'D', "gemDiamond");
		/** Circutry Stuff **/
		recipes.overrideRecipe("shaped_item.itemPartCircuit_1058514721", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CCC", "RIR", "CCC", 'C', Ic2Items.copperCable.copy(), 'R', "dustRedstone", 'I', ingotElectric);
		recipes.overrideRecipe("shaped_item.itemPartCircuit_1521116961", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CRC", "CIC", "CRC", 'C', Ic2Items.copperCable.copy(), 'R', "dustRedstone", 'I', ingotElectric);
		recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-1948043137", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RGR", "LCL", "RGR", 'R', "dustRedstone", 'G', advComponent, 'C', "circuitBasic", 'L', lapis);
		recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-205948801", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RLR", "GCG", "RLR", 'R', "dustRedstone", 'G', advComponent, 'C', "circuitBasic", 'L', lapis);
		/** RE Battery **/
		recipes.overrideRecipe("shaped_item.itemBatRE_2077392104", GTMaterialGen.getIc2(Ic2Items.battery, 1), " C ", "TRT", "TRT", 'T', "ingotTin", 'R', "dustRedstone", 'C', Ic2Items.copperCable.copy());
		/** Energium Crystal Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatCrystal_-1564046631", GTMaterialGen.getIc2(Ic2Items.energyCrystal, 1), new Object[] {
				"RRR", "RDR", "RRR", 'D', lowCrystal, 'R', "dustRedstone" });
		/** Lapotron Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatLamaCrystal_1330077638", GTMaterialGen.getIc2(Ic2Items.lapotronCrystal, 1), new Object[] {
				"LCL", "LDL", "LCL", 'D', highCrystal, 'C', "circuitBasic", 'L', lapis });
	}
}
