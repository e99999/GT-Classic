package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.GTTileMatterFabricator;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
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
import net.minecraft.init.Items;

public class GTRecipe {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static String ingotRefinedIron = IC2.getRefinedIron();
	static IRecipeInput ingotMachine = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict(ingotRefinedIron), new RecipeInputOreDict("ingotAluminium") });
	static IRecipeInput ingotElectric = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict(ingotRefinedIron), new RecipeInputOreDict("itemSilicon"),
			new RecipeInputOreDict("ingotAluminium") });
	static IRecipeInput ingotAny = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict(ingotRefinedIron), new RecipeInputOreDict("ingotSilver"),
			new RecipeInputOreDict("ingotBronze"), new RecipeInputOreDict("ingotAluminium"),
			new RecipeInputOreDict("ingotElectrum") });
	static IRecipeInput ingotMixed = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("ingotSilver"), new RecipeInputOreDict("ingotAluminium"),
			new RecipeInputOreDict("ingotElectrum") });
	static IRecipeInput lowCrystal = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("gemDiamond"), new RecipeInputOreDict("gemRuby") });
	static IRecipeInput highCrystal = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("gemSapphire"), new RecipeInputItemStack(Ic2Items.energyCrystal.copy()) });
	static IRecipeInput anyLapis = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("gemLapis"),
			new RecipeInputOreDict("dustLazurite"), new RecipeInputOreDict("dustSodalite") });
	static IRecipeInput anyConductor = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("dustRedstone"), new RecipeInputOreDict("ingotSilver"),
			new RecipeInputOreDict("ingotElectrum") });
	static IRecipeInput ingotConductor = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("ingotSilver"), new RecipeInputOreDict("ingotElectrum") });
	static IRecipeInput anyPiston = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputItemStack(GTMaterialGen.get(Blocks.STICKY_PISTON)),
			new RecipeInputItemStack(GTMaterialGen.get(Blocks.PISTON)) });
	static IRecipeInput ingotHigh = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("ingotTungsten"), new RecipeInputOreDict("ingotTitanium") });

	/*
	 * For now this set of recipes is heavily broken apart which allows me to
	 * reconfigure them with clarity. After the progression is finalized, all
	 * recipes with be in this class
	 */
	public static void init() {
		GTRecipeIterators.recipeIterators1();
		GTRecipeProcessing.recipesProcessing();
		GTRecipeUUAmplifier.init();
		// below is more how things will go
		GTTileCentrifuge.init();
		GTTileMatterFabricator.init();
		GTTileMultiBlastFurnace.init();
		GTTileMultiFusion.init();
		shapeless();
		items();
		blocks();
		ic2();
	}

	public static void postInit() {
		GTTileMultiBlastFurnace.removals();
		GTTileMultiBlastFurnace.postInit();
		GTRecipeMods.postInit();
	}

	public static void shapeless() {
		/** Duct Tape **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.ductTape), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64));
		/** Small Buffer **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.tileBufferSmall, 8), GTMaterialGen.get(GTBlocks.tileBufferLarge, 1));
		/** Broken NBT Tubes to Regular **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.testTube, 1), GTMaterialGen.get(GTItems.testTube, 1));
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
		/** Jack Hammer **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.jackHammer, 1), new Object[] { "IBI", " C ", " D ", 'I',
				"ingotTungsten", 'C', "circuitBasic", 'B', Ic2Items.battery.copy(), 'D', "gemDiamond" });
		/** Helium Reactor Coolant **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageSingle, 1), new Object[] { " I ", "IHI", " I ", 'I',
				"ingotTin", 'H', GTMaterialGen.getFluid(GTMaterial.Helium, 1) });
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageTriple, 1), new Object[] { "III", "HHH", "III", 'I',
				"ingotTin", 'H', GTItems.heatStorageSingle });
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageSix, 1), new Object[] { "IHI", "IPI", "IHI", 'I',
				"ingotTin", 'H', GTItems.heatStorageTriple, 'P', Ic2Items.denseCopperPlate });
		/** Lithium Battery **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.getLithiumBattery(), 1), new Object[] { " G ", "ALA", "ALA", 'G',
				Ic2Items.goldCable.copy(), 'A', "ingotAluminium", 'L', "dustLithium" });
		/** Lithium BatPack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.getLithiumBatpack(), 1), new Object[] { "LCL", "LAL", "L L", 'C',
				"circuitAdvanced", 'A', "ingotAluminium", 'L', GTItems.getLithiumBattery() });
		/** Energy Control Circuit **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.circuitEnergy, 4), new Object[] { "CLC", "LPL", "CLC", 'L',
				Ic2Items.lapotronCrystal.copy(), 'C', "circuitAdvanced", 'P', "plateIridiumAlloy" });
		/** Data Control Circuit **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.circuitData, 4), new Object[] { "CDC", "DPD", "CDC", 'D',
				"circuitData", 'C', "circuitAdvanced", 'P', "plateIridiumAlloy" });
		/** Data Chip **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.chipData, 4), new Object[] { "EEE", "ECE", "EEE", 'E', "gemEmerald",
				'C', "circuitAdvanced" });
		/** Lapotronic Energy Orb **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.getOrbEnergy(), 1), new Object[] { "LLL", "LPL", "LLL", 'L',
				Ic2Items.lapotronCrystal.copy(), 'P', "plateIridiumAlloy" });
		/** Data Orb **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.orbData, 4), new Object[] { "SSS", "SCS", "SSS", 'S',
				"circuitElite", 'C', "circuitData" });
		/** Super Conductor **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), new Object[] { "CCC", "PWP", "EEE", 'C',
				Ic2Items.reactorCoolantCellSix.copy(), 'E', "circuitMaster", 'W', "ingotTungsten", 'P',
				"plateIridiumAlloy" });
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), new Object[] { "CCC", "PWP", "EEE", 'C',
				GTItems.heatStorageTriple, 'E', "circuitMaster", 'W', "ingotTungsten", 'P', "plateIridiumAlloy" });
		/** Lapotron Batpack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.getLapotronPack(), 1), new Object[] { "ELE", "SBS", "EPE", 'E',
				"circuitMaster", 'S', GTItems.superConductor, 'L', "batteryUltimate", 'B', GTItems.getLithiumBatpack(),
				'P', "plateIridiumAlloy" });
		/** Tesla Staff **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.teslaStaff, 1), new Object[] { " SL", " PS", "P  ", 'L',
				"batteryUltimate", 'S', GTItems.superConductor, 'P', "plateIridiumAlloy" });
		/** Survival Scanner **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.portableScanner, 1), new Object[] { "PEP", "CFC", "PBP", 'P',
				"ingotAluminium", 'E', GTMaterialGen.getIc2(Ic2Items.euReader, 1), 'F',
				GTMaterialGen.getIc2(Ic2Items.cropAnalyzer, 1), 'C', "circuitAdvanced", 'B',
				GTMaterialGen.get(GTItems.getLithiumBattery()) });
	}

	public static void blocks() {
		/** Bonus recipe for piston **/
		recipes.addRecipe(GTMaterialGen.get(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood", 'C',
				"cobblestone", 'I', ingotAny, 'R', "dustRedstone" });
		/** Bonus recipe for hopper **/
		recipes.addRecipe(GTMaterialGen.get(Blocks.HOPPER), new Object[] { "I I", "ICI", " I ", 'I', ingotAny, 'C',
				"chestWood" });
		/** Reinforced Machine Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingReinforced, 5), new Object[] { "III", "CMC", "III", 'I',
				ingotRefinedIron, 'C', "circuitAdvanced", 'M', Ic2Items.advMachine.copy() });
		/** Highly Advanced Machine Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), new Object[] { "CTC", "TBT", "CTC", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', Ic2Items.advMachine.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), new Object[] { "TCT", "CBC", "TCT", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', Ic2Items.advMachine.copy() });
		/** Fusion Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingFusion), new Object[] { "CBY", "BRB", "YBS", 'B',
				GTBlocks.casingHighlyAdvanced, 'C', "circuitMaster", 'S', GTItems.superConductor, 'Y',
				Ic2Items.teslaCoil.copy(), 'B', Ic2Items.advMachine.copy(), 'R',
				Ic2Items.reactorReflectorIridium.copy() });
		/** LESU Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingLapotron), new Object[] { "BBB", "BCB", "BBB", 'B',
				"blockLapis", 'C', "circuitBasic" });
		/** Industrial Centrifuge **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', ingotRefinedIron, 'A', Ic2Items.advMachine, 'C', "circuitAdvanced" });
		/** Blast Furnace **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBlastFurnace, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.inductionFurnace, 'R', ingotRefinedIron, 'A', Ic2Items.advMachine, 'C', "circuitAdvanced" });
		/** Lightning Rod **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileLightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				"circuitMaster", 'S', GTItems.superConductor, 'A', GTBlocks.casingHighlyAdvanced });
		/** Fusion Computer **/
		recipes.overrideRecipe("shaped_tile.blockPlasmafier_679353211", GTMaterialGen.get(GTBlocks.tileFusionComputer, 1), new Object[] {
				"ESE", "LCL", "ESE", 'E', "circuitMaster", 'S', GTItems.superConductor, 'L', "batteryUltimate", 'C',
				GTBlocks.tileComputer });
		/** Player Detector **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tilePlayerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
				Blocks.OBSERVER, 'C', "circuitBasic", 'c', Ic2Items.advMachine });
		/** Computer Cube **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileComputer, 1), new Object[] { "RGD", "GMG", "DGR", 'D',
				"circuitUltimate", 'R', "circuitMaster", 'G', "blockGlass", 'M', Ic2Items.advMachine.copy() });
		/** Energy Storage **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileAESU), new Object[] { "OOO", "OCO", "OOO", 'O',
				"batteryUltimate", 'C', GTBlocks.tileComputer });
		/** Quantum Chest **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumChest, 1), new Object[] { "IDI", "MCM", "IDI", 'D',
				"circuitUltimate", 'I', "ingotChrome", 'C', "chestWood", 'M', Ic2Items.advMachine.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumChest, 1), new Object[] { "IDI", "MCM", "IDI", 'D',
				"circuitUltimate", 'I', "ingotTitanium", 'C', "chestWood", 'M', Ic2Items.advMachine.copy() });
		/** Cabinet **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCabinet), new Object[] { "III", "CIC", "III", 'I',
				ingotMachine, 'C', "chestWood" });
		/** Large Chest Buffer **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferLarge), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', "circuitBasic", 'M', Ic2Items.machine.copy(), 'P', "chestWood");
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferLarge), "SSS", "SCS", "SSS", 'S', GTBlocks.tileBufferSmall, 'C', Ic2Items.upgradeBase.copy());
		/** Matter Fabricator **/
		recipes.overrideRecipe("shaped_tile.blockMatter_1416524227", GTMaterialGen.get(GTBlocks.tileFabricator, 1), new Object[] {
				"ETE", "HLH", "ETE", 'E', "circuitMaster", 'T', Ic2Items.teleporter, 'H', GTBlocks.casingHighlyAdvanced,
				'L', "batteryUltimate" });
		/** Charge-O-Mat **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileChargeOmat, 1), new Object[] { "RCR", "AEA", "RMR", 'E',
				"batteryUltimate", 'R', "circuitMaster", 'A', "chestWood", 'C', GTBlocks.tileComputer, 'M',
				Ic2Items.advMachine.copy() });
		/** IDSU **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileIDSU, 1), new Object[] { "PHP", "HEH", "PHP", 'P',
				"plateIridiumAlloy", 'H', GTBlocks.tileAESU, 'E', Blocks.ENDER_CHEST });
		/** Stuff that is not ready yet **/
		if (GTMod.debugMode) {
			/** Translocator **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileTranslocator), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', "circuitBasic", 'M', Ic2Items.machine.copy(), 'P', anyPiston);
			/** UU Assembler **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileUUAssembler, 1), new Object[] { "dCd", "TQE", "DBD", 'd',
					"circuitElite", 'C', GTBlocks.tileComputer, 'T', Ic2Items.teleporter, 'Q',
					GTBlocks.tileQuantumChest, 'E', Ic2Items.industrialWorktable, 'D', "circuitUltimate", 'B',
					"batteryAdvanced" });
		}
		/** More recipes for vanilla rails **/
		// TODO check for railcraft for these
		// golden
		recipes.addRecipe(GTMaterialGen.get(Blocks.GOLDEN_RAIL, 6), new Object[] { "I I", "ISI", "IRI", 'I',
				"ingotElectrum", 'S', "stickWood", 'R', "dustRedstone" });
		// detector
		recipes.addRecipe(GTMaterialGen.get(Blocks.DETECTOR_RAIL, 8), new Object[] { "I I", "ISI", "IRI", 'I',
				ingotRefinedIron, 'S', Blocks.STONE_PRESSURE_PLATE, 'R', "dustRedstone" });
		recipes.addRecipe(GTMaterialGen.get(Blocks.DETECTOR_RAIL, 12), new Object[] { "I I", "ISI", "IRI", 'I',
				ingotHigh, 'S', Blocks.STONE_PRESSURE_PLATE, 'R', "dustRedstone" });
		// regular
		recipes.addRecipe(GTMaterialGen.get(Blocks.RAIL, 20), new Object[] { "I I", "ISI", "I I", 'I', ingotRefinedIron,
				'S', "stickWood" });
		recipes.addRecipe(GTMaterialGen.get(Blocks.RAIL, 32), new Object[] { "I I", "ISI", "I I", 'I', ingotHigh, 'S',
				"stickWood" });
		// activator
		recipes.addRecipe(GTMaterialGen.get(Blocks.ACTIVATOR_RAIL, 8), new Object[] { "IRI", "ISI", "IRI", 'I',
				ingotRefinedIron, 'S', Blocks.REDSTONE_TORCH, 'R', "stickWood" });
		recipes.addRecipe(GTMaterialGen.get(Blocks.ACTIVATOR_RAIL, 16), new Object[] { "IRI", "ISI", "IRI", 'I',
				ingotHigh, 'S', Blocks.REDSTONE_TORCH, 'R', "stickWood" });
	}

	public static void ic2() {
		/** Machine casings can take aluminium **/
		recipes.overrideRecipe("shaped_tile.blockMachine_527557260", Ic2Items.machine.copy(), "III", "I I", "III", 'I', ingotMachine);
		/** Alt Mining Laser Recipe **/
		recipes.addRecipe(Ic2Items.miningLaser.copy(), new Object[] { "Rcc", "AAC", " AA", 'A',
				Ic2Items.advancedAlloy.copy(), 'C', "circuitAdvanced", 'c',
				GTMaterialGen.getFluid(GTMaterial.Helium, 1), 'R', "dustRedstone" });
		/** Alt Reflector Recipe **/
		recipes.addRecipe(Ic2Items.reactorReflectorThick.copy(), new Object[] { " P ", "PBP", " P ", 'P',
				Ic2Items.reactorReflector, 'B', GTMaterialGen.getFluid(GTMaterial.Beryllium, 1) });
		/** More Luminator Recipes **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), new Object[] { "III", "GHG", "GGG", 'G',
				"blockGlass", 'I', "ingotSilver", 'H', GTMaterialGen.getFluid(GTMaterial.Helium, 1), 'C',
				Ic2Items.insulatedCopperCable.copy() });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), new Object[] { "III", "GHG", "GGG", 'G',
				"blockGlass", 'I', "ingotSilver", 'H', GTMaterialGen.getFluid(GTMaterial.Mercury, 1), 'C',
				Ic2Items.insulatedCopperCable.copy() });
		/** MFE with Lithium Batteries **/
		recipes.addRecipe(Ic2Items.mfe.copy(), new Object[] { "XYX", "YCY", "XYX", 'C', Ic2Items.machine.copy(), 'Y',
				GTItems.getLithiumBattery(), 'X', Ic2Items.doubleInsulatedGoldCable.copy() });
		recipes.addRecipe(Ic2Items.mfe.copy(), new Object[] { "XYX", "YCY", "XYX", 'C', Ic2Items.machine.copy(), 'Y',
				GTItems.getLithiumBattery(), 'X', GTMaterialGen.getIc2(Ic2Items.doubleInsulatedBronzeCable, 4) });
		/** Alt Reactor Vent **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.reactorVent, 1), new Object[] { "IBI", "B B", "IBI", 'I',
				"ingotAluminium", 'B', Blocks.IRON_BARS });
		/** Alt Wind Mill **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.windMill, 1), new Object[] { "X X", " Y ", "X X", 'Y',
				Ic2Items.generator.copy(), 'X', "ingotAluminium" });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.waterMill.copy(), 3), new Object[] { " X ", "XYX", " X ", 'Y',
				Ic2Items.generator.copy(), 'X', "ingotAluminium" });
		/** Mixed Metal Ingots **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.mixedMetalIngot, 3), new Object[] { "III", "BBB", "TTT", 'I',
				ingotRefinedIron, 'B', "ingotBronze", 'T', ingotMixed });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.mixedMetalIngot, 6), new Object[] { "III", "BBB", "TTT", 'I',
				"ingotTitanium", 'B', "ingotBronze", 'T', ingotMixed });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.mixedMetalIngot, 6), new Object[] { "III", "BBB", "TTT", 'I',
				"ingotTungsten", 'B', "ingotBronze", 'T', ingotMixed });
		/** Iridium Plate **/
		recipes.overrideRecipe("shaped_item.itemPartIridium_1100834802", GTMaterialGen.getIc2(Ic2Items.iridiumPlate, 1), "IAI", "ADA", "IAI", 'I', "ingotIridium", 'A', Ic2Items.advancedAlloy.copy(), 'D', "gemDiamond");
		/** Circutry Stuff **/
		recipes.overrideRecipe("shaped_item.itemPartCircuit_1058514721", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CCC", "RIR", "CCC", 'C', Ic2Items.insulatedCopperCable.copy(), 'R', anyConductor, 'I', ingotElectric);
		recipes.overrideRecipe("shaped_item.itemPartCircuit_1521116961", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CRC", "CIC", "CRC", 'C', Ic2Items.insulatedCopperCable.copy(), 'R', anyConductor, 'I', ingotElectric);
		recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-1948043137", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RGR", "LCL", "RGR", 'R', anyConductor, 'G', "dustGlowstone", 'C', "circuitBasic", 'L', anyLapis);
		recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-205948801", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RLR", "GCG", "RLR", 'R', anyConductor, 'G', "dustGlowstone", 'C', "circuitBasic", 'L', anyLapis);
		/** RE Battery **/
		recipes.overrideRecipe("shaped_item.itemBatRE_2077392104", GTMaterialGen.getIc2(Ic2Items.battery, 1), " C ", "TRT", "TRT", 'T', "ingotTin", 'R', "dustRedstone", 'C', Ic2Items.copperCable.copy());
		/** Energium Crystal Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatCrystal_-1564046631", GTMaterialGen.getIc2(Ic2Items.energyCrystal, 1), new Object[] {
				"RRR", "RDR", "RRR", 'D', lowCrystal, 'R', "dustRedstone" });
		/** Lapotron Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatLamaCrystal_1330077638", GTMaterialGen.getIc2(Ic2Items.lapotronCrystal, 1), new Object[] {
				"LCL", "LDL", "LCL", 'D', highCrystal, 'C', "circuitBasic", 'L', anyLapis });
		/** Solar Panel **/
		recipes.overrideRecipe("shaped_tile.blockSolarGenerator_1093731471", GTMaterialGen.getIc2(Ic2Items.solarPanel, 1), new Object[] {
				"XYX", "YXY", "CVC", 'V', Ic2Items.machine.copy(), 'X', "itemSilicon", 'Y', "blockGlass", 'C',
				Ic2Items.carbonPlate });
		recipes.overrideRecipe("shaped_tile.blockSolarGenerator_261816397", GTMaterialGen.getIc2(Ic2Items.solarPanel, 1), new Object[] {
				"YYY", "XXX", "CVC", 'V', Ic2Items.machine.copy(), 'X', "itemSilicon", 'Y', "blockGlass", 'C',
				Ic2Items.carbonPlate });
		/** Adding ruby to glass fiber cable **/
		recipes.overrideRecipe("shaped_item.itemGlassCable_-542195504", GTMaterialGen.getIc2(Ic2Items.glassFiberCable, 4), "XXX", "CVC", "XXX", 'X', "blockGlass", 'C', "dustRedstone", 'V', lowCrystal);
		recipes.overrideRecipe("shaped_item.itemGlassCable_-410929364", GTMaterialGen.getIc2(Ic2Items.glassFiberCable, 6), "XXX", "CVC", "XXX", 'X', "blockGlass", 'C', ingotConductor, 'V', lowCrystal);
		/** Overclocker helium coolant recipes **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 2), new Object[] { "CCC", "WEW", 'C',
				GTMaterialGen.get(GTItems.heatStorageSingle), 'W', Ic2Items.insulatedCopperCable.copy(), 'E',
				Ic2Items.electricCircuit.copy() });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 6), new Object[] { "CCC", "WEW", 'C',
				GTMaterialGen.get(GTItems.heatStorageTriple), 'W',
				GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 3), 'E',
				GTMaterialGen.getIc2(Ic2Items.electricCircuit, 3) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 12), new Object[] { "CCC", "WEW", 'C',
				GTMaterialGen.get(GTItems.heatStorageSix), 'W', GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 6),
				'E', GTMaterialGen.getIc2(Ic2Items.electricCircuit, 6) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 2), new Object[] { " X ", "XYX", " X ", 'Y',
				Ic2Items.upgradeBase.copy(), 'X', GTMaterialGen.get(GTItems.heatStorageSingle) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 2), new Object[] { "XXX", "XYX", "XXX", 'Y',
				GTMaterialGen.getIc2(Ic2Items.upgradeBase, 2), 'X', GTMaterialGen.get(GTItems.heatStorageSingle) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 8), new Object[] { " X ", "XYX", " X ", 'Y',
				GTMaterialGen.getIc2(Ic2Items.upgradeBase, 4), 'X', GTMaterialGen.get(GTItems.heatStorageTriple) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 16), new Object[] { "XXX", "XYX", "XXX",
				'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 8), 'X',
				GTMaterialGen.get(GTItems.heatStorageTriple) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 16), new Object[] { " X ", "XYX", " X ",
				'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 8), 'X', GTMaterialGen.get(GTItems.heatStorageSix) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 32), new Object[] { "XXX", "XYX", "XXX",
				'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 16), 'X', GTMaterialGen.get(GTItems.heatStorageSix) });
		/** UU-Matter Recipes **/
		recipes.overrideRecipe("shaped_item.emerald_981588030", GTMaterialGen.get(Items.EMERALD), new Object[] { "UUU",
				"UUU", " U ", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
		recipes.addRecipe(GTMaterialGen.getGem(GTMaterial.Ruby, 1), new Object[] { " UU", "UUU", "UU ", 'U',
				GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
		recipes.addRecipe(GTMaterialGen.getGem(GTMaterial.Sapphire, 1), new Object[] { "UU ", "UUU", " UU", 'U',
				GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.oreBauxite, 6), new Object[] { "U U", " UU", "U U", 'U',
				GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
		recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Titanium, 2), new Object[] { "UUU", " U ", " U ", 'U',
				GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
		recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Aluminium, 16), new Object[] { " U ", " U ", "UUU", 'U',
				GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
	}
}
