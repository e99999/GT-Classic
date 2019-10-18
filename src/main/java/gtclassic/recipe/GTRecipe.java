package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import gtclassic.GTItems;
import gtclassic.helpers.GTHelperAdvRecipe;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
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
			new RecipeInputOreDict("ingotAluminium"), new RecipeInputOreDict("ingotSilver"),
			new RecipeInputOreDict("ingotElectrum"), new RecipeInputOreDict("ingotPlatinum") });
	static IRecipeInput ingotAny = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict(ingotRefinedIron), new RecipeInputOreDict("ingotSilver"),
			new RecipeInputOreDict("ingotBronze"), new RecipeInputOreDict("ingotAluminium"),
			new RecipeInputOreDict("ingotElectrum"), new RecipeInputOreDict("ingotPlatinum") });
	static IRecipeInput ingotAnyIron = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict(ingotRefinedIron), new RecipeInputOreDict("ingotIron") });
	static IRecipeInput ingotMixed = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("ingotSilver"), new RecipeInputOreDict("ingotAluminium"),
			new RecipeInputOreDict("ingotElectrum") });
	static IRecipeInput lowCrystal = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("gemDiamond"), new RecipeInputOreDict("gemRuby") });
	static IRecipeInput highCrystal = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("gemSapphire"), new RecipeInputItemStack(Ic2Items.energyCrystal.copy()) });
	static IRecipeInput anyLapis = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("gemLapis"),
			new RecipeInputOreDict("dustLazurite"), new RecipeInputOreDict("dustSodalite") });
	static IRecipeInput ingotSilver = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("ingotSilver"), new RecipeInputOreDict("ingotElectrum") });
	static IRecipeInput anyPiston = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputItemStack(GTMaterialGen.get(Blocks.STICKY_PISTON)),
			new RecipeInputItemStack(GTMaterialGen.get(Blocks.PISTON)) });
	static IRecipeInput ingotHigh = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("ingotTungsten"), new RecipeInputOreDict("ingotTitanium") });
	static IRecipeInput ingotDigital = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputOreDict("ingotChrome"), new RecipeInputOreDict("ingotTitanium"),
			new RecipeInputOreDict("ingotPlatinum") });
	static IRecipeInput batteryAdvanced = new RecipeInputCombined(1, new IRecipeInput[] {
			new RecipeInputItemStack(Ic2Items.energyCrystal.copy()),
			new RecipeInputItemStack(GTMaterialGen.get(GTItems.lithiumBattery)) });
	static IRecipeInput circuitBasicX2 = new RecipeInputCombined(2, new IRecipeInput[] {
			new RecipeInputOreDict("circuitBasic") });

	public static void initShapeless() {
		/** Duct Tape **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.ductTape), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64));
		/** Small Buffer **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.tileBufferSmall, 8), GTMaterialGen.get(GTBlocks.tileBufferLarge, 1));
		/** Light Helmet **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.lightHelment, 1), Ic2Items.solarHelmet.copy(), Ic2Items.luminator.copy(), Ic2Items.battery.copy());
		/** Electrum Dust **/
		recipes.addShapelessRecipe(GTMaterialGen.getDust(GTMaterial.Electrum, 2), "dustSilver", "dustGold");
		/** Methane **/
		recipes.addShapelessRecipe(GTMaterialGen.getTube(GTMaterial.Methane, 4), GTMaterialGen.getDust(GTMaterial.Carbon, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1));
		/** Gunpowder **/
		recipes.addShapelessRecipe(GTMaterialGen.get(Items.GUNPOWDER, 5), GTMaterialGen.getTube(GTMaterial.Potassium, 1), GTMaterialGen.getTube(GTMaterial.Potassium, 1), GTMaterialGen.getTube(GTMaterial.Nitrogen, 1), GTMaterialGen.getTube(GTMaterial.Nitrogen, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
	}

	public static void initItems() {
		/** Test Tube **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.testTube, 32), new Object[] { "G G", "G G", " G ", 'G',
				"blockGlass" });
		/** Flint Mortar **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.flintMortar), new Object[] { " F ", "BFB", "BBB", 'F', Items.FLINT,
				'B', "plankWood", });
		/** Iron Mortar **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.ironMortar, 1), new Object[] { " I ", "BIB", "BBB", 'I',
				ingotAnyIron, 'B', "stone", });
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
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageHelium1, 1), new Object[] { " I ", "IHI", " I ", 'I',
				"ingotTin", 'H', GTMaterialGen.getTube(GTMaterial.Helium, 1) });
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageHelium3, 1), new Object[] { "III", "HHH", "III", 'I',
				"ingotTin", 'H', GTItems.heatStorageHelium1 });
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageHelium6, 1), new Object[] { "IHI", "IPI", "IHI", 'I',
				"ingotTin", 'H', GTItems.heatStorageHelium3, 'P', Ic2Items.denseCopperPlate });
		/** Lithium Battery **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.lithiumBattery, 1), new Object[] { " G ", "ALA", "ALA", 'G',
				Ic2Items.goldCable.copy(), 'A', "ingotAluminium", 'L', "dustLithium" });
		/** Lithium BatPack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.lithiumBatpack, 1), new Object[] { "LCL", "LAL", "L L", 'C',
				"circuitAdvanced", 'A', "ingotAluminium", 'L', GTItems.lithiumBattery });
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
		recipes.addRecipe(GTMaterialGen.get(GTItems.orbEnergy, 1), new Object[] { "LLL", "LPL", "LLL", 'L',
				Ic2Items.lapotronCrystal.copy(), 'P', "plateIridiumAlloy" });
		/** Data Orb **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.orbData, 4), new Object[] { "SSS", "SCS", "SSS", 'S',
				"circuitElite", 'C', "circuitData" });
		/** Super Conductor **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), new Object[] { "CCC", "PWP", "EEE", 'C',
				Ic2Items.reactorCoolantCellSix.copy(), 'E', "circuitMaster", 'W', "ingotTungsten", 'P',
				"plateIridiumAlloy" });
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), new Object[] { "CCC", "PWP", "EEE", 'C',
				GTItems.heatStorageHelium3, 'E', "circuitMaster", 'W', "ingotTungsten", 'P', "plateIridiumAlloy" });
		/** Lapotron Batpack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.lapotronPack, 1), new Object[] { "ELE", "SBS", "EPE", 'E',
				"circuitMaster", 'S', "craftingSuperconductor", 'L', "batteryUltimate", 'B', GTItems.lithiumBatpack,
				'P', "plateIridiumAlloy" });
		/** Tesla Staff **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.teslaStaff, 1), new Object[] { " SL", " PS", "P  ", 'L',
				"batteryUltimate", 'S', "craftingSuperconductor", 'P', "plateIridiumAlloy" });
		/** Survival Scanner **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.portableScanner, 1), new Object[] { "PEP", "CFC", "PBP", 'P',
				"ingotAluminium", 'E', GTMaterialGen.getIc2(Ic2Items.euReader, 1), 'F',
				GTMaterialGen.getIc2(Ic2Items.cropAnalyzer, 1), 'C', "circuitAdvanced", 'B',
				GTMaterialGen.get(GTItems.lithiumBattery) });
		/** Cloaking Device **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.cloakingDevice, 1), new Object[] { "IPI", "POP", "IPI", 'I',
				"ingotChrome", 'P', "plateIridiumAlloy", 'O', "batteryUltimate" });
		/** Echotron **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.echotron, 1), new Object[] { " C ", "CEC", " C ", 'E',
				GTBlocks.tileEchotron, 'C', "circuitData" });
		/** Spring Boots **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.springBoots, 1), new Object[] { "IBI", "I I", 'B',
				Ic2Items.compositeBoots.copy(), 'I', ingotRefinedIron });
		/** Thorium rods **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodThorium2), new Object[] { "UCU", 'U', GTItems.rodThorium1, 'C',
				Ic2Items.denseCopperPlate.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodThorium4), new Object[] { " U ", "CCC", " U ", 'U',
				GTItems.rodThorium2, 'C', Ic2Items.denseCopperPlate.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodThorium4), new Object[] { "UCU", "CCC", "UCU", 'U',
				GTItems.rodThorium1, 'C', Ic2Items.denseCopperPlate.copy() });
		/** Plutonium Rod **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodPlutonium2), new Object[] { "UCU", 'U', GTItems.rodPlutonium1,
				'C', Ic2Items.denseCopperPlate.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodPlutonium4), new Object[] { " U ", "CCC", " U ", 'U',
				GTItems.rodPlutonium2, 'C', Ic2Items.denseCopperPlate.copy() });
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodPlutonium4), new Object[] { "UCU", "CCC", "UCU", 'U',
				GTItems.rodPlutonium1, 'C', Ic2Items.denseCopperPlate.copy() });
	}

	public static void initBlocks() {
		/** Bonus recipe for piston **/
		if (GTConfig.morePistonRecipes) {
			recipes.addRecipe(GTMaterialGen.get(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood",
					'C', "cobblestone", 'I', ingotAny, 'R', "dustRedstone" });
		}
		/** Bonus recipe for hopper **/
		if (GTConfig.moreHopperRecipes) {
			recipes.addRecipe(GTMaterialGen.get(Blocks.HOPPER), new Object[] { "I I", "ICI", " I ", 'I', ingotAny, 'C',
					"chestWood" });
		}
		/** Highly Advanced Machine Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), new Object[] { "CTC", "TBT", "CTC", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', "machineBlockAdvanced" });
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), new Object[] { "TCT", "CBC", "TCT", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', "machineBlockAdvanced" });
		/** Fusion Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingFusion), new Object[] { "CSC", "TMT", "CRC", 'M',
				"machineBlockElite", 'C', "circuitMaster", 'S', "craftingSuperconductor", 'T',
				Ic2Items.teslaCoil.copy(), 'R', Ic2Items.reactorReflectorIridium.copy() });
		/** LESU Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingLapotron), new Object[] { "BBB", "BCB", "BBB", 'B',
				"blockLapis", 'C', "circuitBasic" });
		/** Industrial Centrifuge **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', ingotRefinedIron, 'A', "machineBlockAdvanced", 'C', "circuitAdvanced" });
		/** Lightning Rod **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileLightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				"circuitMaster", 'S', GTBlocks.tileSupercondensator, 'A', "machineBlockElite" });
		/** Dragon Egg Energy Siphon **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileDragonEggEnergySiphon, 1), new Object[] { "CTC", "PSP", "CBC",
				'C', "circuitMaster", 'S', GTBlocks.tileSupercondensator, 'B', "batteryUltimate", 'P',
				"plateIridiumAlloy", 'T', Ic2Items.teslaCoil.copy() });
		/** Magic Energy Converter **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileMagicEnergyConverter, 1), new Object[] { "CTC", "IBI", "CLC",
				'C', "circuitAdvanced", 'B', Blocks.BEACON, 'L', Ic2Items.lapotronCrystal.copy(), 'I', "ingotPlatinum",
				'T', Ic2Items.teslaCoil.copy() });
		/** Magic Energy Absorber **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileMagicEnergyAbsorber, 1), new Object[] { "CTC", "PSP", "CBC",
				'C', "circuitMaster", 'S', Blocks.BEACON, 'B', GTBlocks.tileMagicEnergyConverter, 'P',
				"plateIridiumAlloy", 'T', "craftingSuperconductor" });
		/** Fusion Computer **/
		if (GTConfig.removeIC2Plasmafier) {
			recipes.overrideRecipe("shaped_tile.blockPlasmafier_679353211", GTMaterialGen.get(GTBlocks.tileFusionReactor, 1), new Object[] {
					"ESE", "LCL", "ESE", 'E', "circuitMaster", 'S', GTBlocks.tileSupercondensator, 'L',
					"batteryUltimate", 'C', GTBlocks.tileComputer });
		} else {
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileFusionReactor, 1), new Object[] { "ESE", "LCL", "ESE", 'E',
					"circuitMaster", 'S', "craftingSuperconductor", 'L', "batteryUltimate", 'C',
					GTBlocks.tileComputer });
		}
		/** Player Detector **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tilePlayerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
				Blocks.OBSERVER, 'C', "circuitBasic", 'c', "machineBlockAdvanced" });
		/** Computer Cube **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileComputer, 1), new Object[] { "RGD", "GMG", "DGR", 'D',
				"circuitUltimate", 'R', "circuitMaster", 'G', "blockGlass", 'M', "machineBlockAdvanced" });
		/** Energy Storage **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileAESU), new Object[] { "OOO", "OCO", "OOO", 'O',
				"batteryUltimate", 'C', GTBlocks.tileComputer });
		/** Digital Chest **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileDigitalChest, 1), new Object[] { "ICI", "MDM", "ICI", 'D',
				GTBlocks.tileComputer, 'I', ingotDigital, 'C', Items.SHULKER_SHELL, 'M', GTItems.chipData });
		/** Quantum Chest **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumChest, 1), new Object[] { "ICI", "MDM", "ICI", 'D',
				"circuitUltimate", 'I', ingotDigital, 'C', "chestWood", 'M', "machineBlockAdvanced" });
		/** Quantum Tank **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumTank, 1), new Object[] { "ICI", "MDM", "ICI", 'D',
				"circuitUltimate", 'I', ingotDigital, 'C', Items.BUCKET, 'M', "machineBlockAdvanced" });
		/** Cabinet **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCabinet), new Object[] { "III", "CIC", "III", 'I',
				ingotMachine, 'C', "chestWood" });
		/** Workbench **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileWorktable), new Object[] { "IWI", "III", "ICI", 'I',
				ingotMachine, 'C', "chestWood", 'W', "workbench" });
		/** Drum **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileDrum), new Object[] { "I I", "IBI", "I I", 'I',
				ingotRefinedIron, 'B', Items.BUCKET });
		/** Large Chest Buffer **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferLarge), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', "circuitBasic", 'M', "machineBlockBasic", 'P', "chestWood");
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferLarge), "SSS", "SCS", "SSS", 'S', GTBlocks.tileBufferSmall, 'C', Ic2Items.upgradeBase.copy());
		/** Fluid Buffer **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferFluid), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', "circuitBasic", 'M', "machineBlockBasic", 'P', Items.BUCKET);
		/** Translocator **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileTranslocator), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', "circuitBasic", 'M', "machineBlockBasic", 'P', anyPiston);
		/** Matter Fabricator **/
		if (GTConfig.removeIC2MassFab) {
			recipes.overrideRecipe("shaped_tile.blockMatter_1416524227", GTMaterialGen.get(GTBlocks.tileFabricator, 1), new Object[] {
					"ETE", "HLH", "ETE", 'E', "circuitMaster", 'T', Ic2Items.teleporter, 'H', "machineBlockElite", 'L',
					"batteryUltimate" });
		} else {
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileFabricator, 1), new Object[] { "ETE", "HLH", "ETE", 'E',
					"circuitMaster", 'T', Ic2Items.teleporter, 'H', "machineBlockElite", 'L', "batteryUltimate" });
		}
		/** Charge-O-Mat **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileChargeOmat, 1), new Object[] { "RCR", "AEA", "RMR", 'E',
				"batteryUltimate", 'R', "circuitMaster", 'A', "chestWood", 'C', GTBlocks.tileComputer, 'M',
				"machineBlockAdvanced" });
		/** IDSU **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileIDSU, 1), new Object[] { "PHP", "HEH", "PHP", 'P',
				"plateIridiumAlloy", 'H', GTBlocks.tileAESU, 'E', Blocks.ENDER_CHEST });
		/** LESU **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileLESU, 1), new Object[] { " L ", "CBC", " H ", 'L',
				Ic2Items.transformerLV, 'H', Ic2Items.transformerMV, 'C', "circuitAdvanced", 'B',
				GTBlocks.casingLapotron });
		/** Supercondensator **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileSupercondensator, 1), new Object[] { "EAE", "SMS", "EAE", 'E',
				"circuitMaster", 'S', "craftingSuperconductor", 'A', "batteryUltimate", 'M', "machineBlockAdvanced" });
		/** Superconductor Cable **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileSuperconductorCable, 4), new Object[] { "MEM", "SSS", "MEM",
				'E', "circuitMaster", 'S', "craftingSuperconductor", 'M', "machineBlockAdvanced" });
		/** Echotron **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileEchotron, 1), new Object[] { "CRC", "JMN", "CBC", 'C',
				"circuitBasic", 'R', "record", 'J', Blocks.JUKEBOX, 'M', "machineBlockAdvanced", 'N', Blocks.NOTEBLOCK,
				'B', Ic2Items.battery });
		/** Monster Repellator **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileMobRepeller, 1), new Object[] { "SSS", " M ", "CCC", 'S',
				"gemSapphire", 'M', "machineBlockAdvanced", 'C', "circuitBasic" });
		/** Stuff that is not ready yet **/
		/** UU Assembler **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileUUMAssembler, 1), new Object[] { "dCd", "TQE", "DBD", 'd',
				"circuitElite", 'C', GTBlocks.tileComputer, 'T', Ic2Items.teleporter, 'Q', GTBlocks.tileCabinet, 'E',
				"workbench", 'D', "circuitUltimate", 'B', batteryAdvanced });
		/** More recipes for vanilla rails **/
		if (GTConfig.vanillaRailRecipes) {
			// golden
			recipes.addRecipe(GTMaterialGen.get(Blocks.GOLDEN_RAIL, 6), new Object[] { "I I", "ISI", "IRI", 'I',
					"ingotElectrum", 'S', "stickWood", 'R', "dustRedstone" });
			// detector
			recipes.addRecipe(GTMaterialGen.get(Blocks.DETECTOR_RAIL, 8), new Object[] { "I I", "ISI", "IRI", 'I',
					ingotRefinedIron, 'S', Blocks.STONE_PRESSURE_PLATE, 'R', "dustRedstone" });
			recipes.addRecipe(GTMaterialGen.get(Blocks.DETECTOR_RAIL, 12), new Object[] { "I I", "ISI", "IRI", 'I',
					ingotHigh, 'S', Blocks.STONE_PRESSURE_PLATE, 'R', "dustRedstone" });
			// regular
			recipes.addRecipe(GTMaterialGen.get(Blocks.RAIL, 20), new Object[] { "I I", "ISI", "I I", 'I',
					ingotRefinedIron, 'S', "stickWood" });
			recipes.addRecipe(GTMaterialGen.get(Blocks.RAIL, 32), new Object[] { "I I", "ISI", "I I", 'I', ingotHigh,
					'S', "stickWood" });
			// activator
			recipes.addRecipe(GTMaterialGen.get(Blocks.ACTIVATOR_RAIL, 8), new Object[] { "IRI", "ISI", "IRI", 'I',
					ingotRefinedIron, 'S', Blocks.REDSTONE_TORCH, 'R', "stickWood" });
			recipes.addRecipe(GTMaterialGen.get(Blocks.ACTIVATOR_RAIL, 16), new Object[] { "IRI", "ISI", "IRI", 'I',
					ingotHigh, 'S', Blocks.REDSTONE_TORCH, 'R', "stickWood" });
		}
	}

	public static void initIC2() {
		/** Testing some removals **/
		GTHelperAdvRecipe.removeRecipe("ic2", "shaped_item.itemPartCarbonFibre_794316583");
		/** Machine casings can take aluminium **/
		String machineId = IC2.config.getFlag("SteelRecipes") ? "480320652" : "527557260";
		recipes.overrideRecipe("shaped_tile.blockmachine_"
				+ machineId, Ic2Items.machine.copy(), "III", "I I", "III", 'I', ingotMachine);
		/** Alt Mining Laser Recipe **/
		recipes.addRecipe(Ic2Items.miningLaser.copy(), new Object[] { "Rcc", "AAC", " AA", 'A',
				Ic2Items.advancedAlloy.copy(), 'C', "circuitAdvanced", 'c', GTMaterialGen.getTube(GTMaterial.Helium, 1),
				'R', "dustRedstone" });
		/** Thick Reflector Recipe **/
		recipes.overrideRecipe("shaped_item.reactorReflectorThick_-1313142365", Ic2Items.reactorReflectorThick.copy(), new Object[] {
				" P ", "PBP", " P ", 'P', Ic2Items.reactorReflector, 'B',
				GTMaterialGen.getTube(GTMaterial.Beryllium, 1) });
		/** More Luminator Recipes **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), new Object[] { "III", "GHG", "GGG", 'G',
				"blockGlass", 'I', "ingotSilver", 'H', GTMaterialGen.getTube(GTMaterial.Helium, 1), 'C',
				Ic2Items.insulatedCopperCable.copy() });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), new Object[] { "III", "GHG", "GGG", 'G',
				"blockGlass", 'I', "ingotSilver", 'H', GTMaterialGen.getTube(GTMaterial.Mercury, 1), 'C',
				Ic2Items.insulatedCopperCable.copy() });
		/** MFE with Lithium Batteries **/
		recipes.addRecipe(Ic2Items.mfe.copy(), new Object[] { "XYX", "YCY", "XYX", 'C', "machineBlockBasic", 'Y',
				GTItems.lithiumBattery, 'X', Ic2Items.doubleInsulatedGoldCable.copy() });
		recipes.addRecipe(Ic2Items.mfe.copy(), new Object[] { "XYX", "YCY", "XYX", 'C', "machineBlockBasic", 'Y',
				GTItems.lithiumBattery, 'X', GTMaterialGen.getIc2(Ic2Items.doubleInsulatedBronzeCable, 4) });
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
		if (GTConfig.addBasicCircuitRecipes) {
			recipes.overrideRecipe("shaped_item.itemPartCircuit_1058514721", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CCC", "RIR", "CCC", 'C', Ic2Items.insulatedCopperCable.copy(), 'R', "dustRedstone", 'I', ingotElectric);
			recipes.overrideRecipe("shaped_item.itemPartCircuit_1521116961", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CRC", "CIC", "CRC", 'C', Ic2Items.insulatedCopperCable.copy(), 'R', "dustRedstone", 'I', ingotElectric);
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.electricCircuit, 2), "CCC", "III", "CCC", 'C', Ic2Items.insulatedCopperCable.copy(), 'I', ingotElectric);
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.electricCircuit, 2), "CIC", "CIC", "CIC", 'C', Ic2Items.insulatedCopperCable.copy(), 'I', ingotElectric);
		}
		if (GTConfig.addAdvCircuitRecipes) {
			recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-1948043137", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RGR", "LCL", "RGR", 'R', "dustRedstone", 'G', "dustGlowstone", 'C', "circuitBasic", 'L', anyLapis);
			recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-205948801", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RLR", "GCG", "RLR", 'R', "dustRedstone", 'G', "dustGlowstone", 'C', "circuitBasic", 'L', anyLapis);
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 2), "RGR", "LCL", "RGR", 'R', ingotSilver, 'G', "dustGlowstone", 'C', circuitBasicX2, 'L', anyLapis);
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 2), "RLR", "GCG", "RLR", 'R', ingotSilver, 'G', "dustGlowstone", 'C', circuitBasicX2, 'L', anyLapis);
		}
		/** Harder Plasma Core Recipe **/
		// recipes.overrideRecipe("shaped_item.itemPlasmaCore_-1985082214",Ic2Items.plasmaCore.copy(),
		// new Object[]{"XYX", "YCY", "XYX", 'X',
		// GTMaterialGen.get(GTItems.circuitEnergy, 2), 'Y',
		// GTMaterialGen.getIngot(GTMaterial.Tungsten, 4), 'C',
		// Ic2Items.plasmaCell.copy()});
		/** Making the macerator harder **/
		if (GTConfig.harderIC2Macerator) {
			recipes.overrideRecipe("shaped_tile.blockStoneMacerator_-130868445", Ic2Items.stoneMacerator.copy(), new Object[] {
					"FDF", "DPD", "FBF", 'D', "gemDiamond", 'F', Items.FLINT, 'P', Blocks.PISTON, 'B',
					Blocks.FURNACE });
			recipes.overrideRecipe("shaped_tile.blockMacerator_127744036", Ic2Items.macerator.copy(), new Object[] {
					"III", "IMI", "ICI", 'I', ingotRefinedIron, 'M', Ic2Items.stoneMacerator.copy(), 'C',
					"circuitBasic" });
			recipes.overrideRecipe("shaped_tile.blockMacerator_2072794668", Ic2Items.macerator.copy(), new Object[] {
					"FDF", "DMD", "FCF", 'D', "gemDiamond", 'F', Items.FLINT, 'M', "machineBlockBasic", 'C',
					"circuitBasic" });
		}
		/** RE Battery **/
		recipes.overrideRecipe("shaped_item.itemBatRE_2077392104", GTMaterialGen.getIc2(Ic2Items.battery, 1), " C ", "TRT", "TRT", 'T', "ingotTin", 'R', "dustRedstone", 'C', Ic2Items.copperCable.copy());
		/** Energium Crystal Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatCrystal_-1564046631", GTMaterialGen.getIc2(Ic2Items.energyCrystal, 1), new Object[] {
				"RRR", "RDR", "RRR", 'D', lowCrystal, 'R', "dustRedstone" });
		/** Lapotron Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatLamaCrystal_1330077638", GTMaterialGen.getIc2(Ic2Items.lapotronCrystal, 1), new Object[] {
				"LCL", "LDL", "LCL", 'D', highCrystal, 'C', "circuitBasic", 'L', anyLapis });
		/** Solar Panel **/
		if (GTConfig.betterIC2SolarRecipes) {
			recipes.overrideRecipe("shaped_tile.blockSolarGenerator_1093731471", GTMaterialGen.getIc2(Ic2Items.solarPanel, 1), new Object[] {
					"XYX", "YXY", "CVC", 'V', "machineBlockBasic", 'X', "itemSilicon", 'Y', "blockGlass", 'C',
					Ic2Items.carbonPlate });
			recipes.overrideRecipe("shaped_tile.blockSolarGenerator_261816397", GTMaterialGen.getIc2(Ic2Items.solarPanel, 1), new Object[] {
					"YYY", "XXX", "CVC", 'V', "machineBlockBasic", 'X', "itemSilicon", 'Y', "blockGlass", 'C',
					Ic2Items.carbonPlate });
		}
		/** Adding ruby to glass fiber cable **/
		recipes.overrideRecipe("shaped_item.itemGlassCable_-542195504", GTMaterialGen.getIc2(Ic2Items.glassFiberCable, 4), "XXX", "CVC", "XXX", 'X', "blockGlass", 'C', "dustRedstone", 'V', lowCrystal);
		recipes.overrideRecipe("shaped_item.itemGlassCable_-410929364", GTMaterialGen.getIc2(Ic2Items.glassFiberCable, 6), "XXX", "CVC", "XXX", 'X', "blockGlass", 'C', ingotSilver, 'V', lowCrystal);
		/** Overclocker helium coolant recipes **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 2), new Object[] { "CCC", "WEW", 'C',
				GTMaterialGen.get(GTItems.heatStorageHelium1), 'W', Ic2Items.insulatedCopperCable.copy(), 'E',
				Ic2Items.electricCircuit.copy() });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 6), new Object[] { "CCC", "WEW", 'C',
				GTMaterialGen.get(GTItems.heatStorageHelium3), 'W',
				GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 3), 'E',
				GTMaterialGen.getIc2(Ic2Items.electricCircuit, 3) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 12), new Object[] { "CCC", "WEW", 'C',
				GTMaterialGen.get(GTItems.heatStorageHelium6), 'W',
				GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 6), 'E',
				GTMaterialGen.getIc2(Ic2Items.electricCircuit, 6) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 2), new Object[] { " X ", "XYX", " X ", 'Y',
				GTMaterialGen.getIc2(Ic2Items.upgradeBase, 2), 'X', GTMaterialGen.get(GTItems.heatStorageHelium1) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 4), new Object[] { "XXX", "XYX", "XXX", 'Y',
				GTMaterialGen.getIc2(Ic2Items.upgradeBase, 4), 'X', GTMaterialGen.get(GTItems.heatStorageHelium1) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 8), new Object[] { " X ", "XYX", " X ", 'Y',
				GTMaterialGen.getIc2(Ic2Items.upgradeBase, 8), 'X', GTMaterialGen.get(GTItems.heatStorageHelium3) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 16), new Object[] { "XXX", "XYX", "XXX",
				'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 16), 'X',
				GTMaterialGen.get(GTItems.heatStorageHelium3) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 16), new Object[] { " X ", "XYX", " X ",
				'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 16), 'X',
				GTMaterialGen.get(GTItems.heatStorageHelium6) });
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 32), new Object[] { "XXX", "XYX", "XXX",
				'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 32), 'X',
				GTMaterialGen.get(GTItems.heatStorageHelium6) });
		/** Tape for cables **/
		recipes.addShapelessRecipe(Ic2Items.insulatedCopperCable.copy(), new Object[] { Ic2Items.copperCable.copy(),
				"craftingToolDuctTape" });
		recipes.addShapelessRecipe(Ic2Items.insulatedBronzeCable.copy(), new Object[] { Ic2Items.bronzeCable.copy(),
				"craftingToolDuctTape" });
		recipes.addShapelessRecipe(Ic2Items.doubleInsulatedBronzeCable, new Object[] {
				Ic2Items.insulatedBronzeCable.copy(), "craftingToolDuctTape" });
		recipes.addShapelessRecipe(Ic2Items.insulatedGoldCable.copy(), new Object[] { Ic2Items.goldCable.copy(),
				"craftingToolDuctTape" });
		recipes.addShapelessRecipe(Ic2Items.doubleInsulatedGoldCable.copy(), new Object[] {
				Ic2Items.insulatedGoldCable.copy(), "craftingToolDuctTape" });
		recipes.addShapelessRecipe(Ic2Items.insulatedIronCable.copy(), new Object[] { Ic2Items.ironCable.copy(),
				"craftingToolDuctTape" });
		recipes.addShapelessRecipe(Ic2Items.doubleInsulatedIronCable, new Object[] { Ic2Items.insulatedIronCable.copy(),
				"craftingToolDuctTape" });
		recipes.addShapelessRecipe(Ic2Items.tribbleInsulatedIronCable, new Object[] {
				Ic2Items.doubleInsulatedIronCable.copy(), "craftingToolDuctTape" });
		if (IC2.config.getFlag("CraftingNuke")) {
			recipes.overrideRecipe("shaped_tile.blockNuke_-814805840", GTMaterialGen.getIc2(Ic2Items.nuke, 1), "CUC", "RPR", "CUC", 'C', "circuitAdvanced", 'U', Ic2Items.reactorReEnrichedUraniumRod.copy(), 'P', GTItems.rodPlutonium1, 'R', Ic2Items.reactorReflectorThick.copy());
		}
		/** UU-Matter Recipes **/
		if (GTConfig.gregtechUURecipes) {
			recipes.overrideRecipe("shaped_item.emerald_981588030", GTMaterialGen.get(Items.EMERALD), new Object[] {
					"UUU", "UUU", " U ", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
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
			recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Platinum, 1), new Object[] { "  U", "UUU", "UUU", 'U',
					GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
			recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Tungsten, 6), new Object[] { "U  ", "UUU", "UUU", 'U',
					GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true });
		}
	}
}
