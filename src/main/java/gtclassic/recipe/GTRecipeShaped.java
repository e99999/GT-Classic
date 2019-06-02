package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.item.recipe.upgrades.EnchantmentModifier;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTRecipeShaped {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	static String hammer = "craftingToolForgeHammer";
	static String file = "craftingToolFile";
	static String wrench = "craftingToolWrench";

	public static void recipeShaped1() {
		/*
		 * Recipes for tools and items
		 */

		IRecipeInput plateAnyIron = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("plateIron"),
				new RecipeInputOreDict("plateRefinedIron"), new RecipeInputOreDict("plateSteel") });

		IRecipeInput stickAnyIron = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("stickIron"),
				new RecipeInputOreDict("stickRefinedIron"), new RecipeInputOreDict("stickSteel") });

		recipes.addRecipe(GTMaterialGen.get(GTItems.electroMagnet, 1), new Object[] { "M M", "WMW", "IBI", 'M',
				Ic2Items.magnet, 'B', Ic2Items.battery, 'I', "stickMagneticIron", 'W', Ic2Items.copperCable });

		recipes.addRecipe(GTMaterialGen.get(GTItems.rockCutter, 1), new Object[] { "D C", "DIB", "DII",
				new EnchantmentModifier(GTMaterialGen.get(GTItems.rockCutter), Enchantments.SILK_TOUCH).setUsesInput(),
				'D', "gemDiamond", 'I', GTMaterialGen.getIc2(Ic2Items.carbonPlate, 1), 'C', "circuitBasic", 'B',
				Ic2Items.battery.copy() });

		recipes.addRecipe(GTMaterialGen.get(GTItems.testTube, 32), new Object[] { "G G", "G G", " G ", 'G',
				"blockGlass" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.batteryLithiumSmall, 1), new Object[] { " G ", "ALA", "ALA", 'G',
				Ic2Items.goldCable, 'A', "plateAluminium", 'L', "dustLithium" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.destructoPack, 1), new Object[] { "BIB", "ICI", "BIB", 'B',
				GTValues.lava, 'C', "circuitBasic", 'I', plateAnyIron });

		recipes.addRecipe(GTMaterialGen.get(GTItems.machineSwitch, 1), new Object[] { " H ", "PMP", " I ", 'I',
				stickAnyIron, 'M', Items.PAPER, 'P', plateAnyIron, 'H', hammer });

		recipes.addRecipe(GTMaterialGen.get(GTItems.craftingTablet, 1), new Object[] { "BIB", "ICI", "BIB", 'B',
				"workbench", 'C', "circuitBasic", 'I', plateAnyIron });

		recipes.addRecipe(GTMaterialGen.get(GTItems.teslaStaff, 1), new Object[] { " SL", " PS", "P  ", 'L',
				GTBlocks.batteryLapotronSmall, 'S', GTBlocks.casingSuperConductor, 'P', "stickIridium" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.portableScanner, 1), new Object[] { "PEP", "CFC", "PBP", 'P',
				"plateAluminium", 'E', GTMaterialGen.getIc2(Ic2Items.euReader, 1), 'F',
				GTMaterialGen.getIc2(Ic2Items.cropAnalyzer, 1), 'C', "circuitAdvanced", 'B',
				GTMaterialGen.get(GTBlocks.batteryLithiumSmall) });

		recipes.addRecipe(GTMaterialGen.get(GTItems.motorLV, 1), new Object[] { "CWS", "WRW", "PWC", 'S',
				"stickRefinedIron", 'P', "plateRefinedIron", 'W', "wireFineRedAlloy", 'R', "stickMagneticIron", 'C',
				Ic2Items.insulatedCopperCable });

		recipes.addRecipe(GTMaterialGen.get(GTItems.motorMV, 1), new Object[] { "CWS", "WRW", "PWC", 'S', "stickSteel",
				'P', "plateSteel", 'W', "wireFineCopper", 'R', "stickMagneticSteel", 'C',
				Ic2Items.insulatedGoldCable });

		recipes.addRecipe(GTMaterialGen.get(GTItems.motorHV, 1), new Object[] { "CWS", "WRW", "PWC", 'S',
				"stickStainlessSteel", 'P', "plateStainlessSteel", 'W', "wireFineAnnealedCopper", 'R',
				"stickMagneticNeodymium", 'C', Ic2Items.insulatedIronCable });

		/*
		 * recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageSingle, 1), new
		 * Object[] { " I ", "IHI", " I ", 'I', "ingotTin", 'H', GTItems.helium });
		 * 
		 * recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageTriple, 1), new
		 * Object[] { "III", "HHH", "III", 'I', "ingotTin", 'H',
		 * GTItems.heatStorageSingle });
		 * 
		 * recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageSix, 1), new Object[]
		 * { "IHI", "IPI", "IHI", 'I', "ingotTin", 'H', GTItems.heatStorageTriple, 'P',
		 * Ic2Items.denseCopperPlate });
		 * 
		 * 
		 * recipes.addRecipe(GTMaterialGen.get(GTBlocks.superCasingBlock, 4), new
		 * Object[] { "CCC", "PWP", "EEE", 'C', GTItems.heatStorageTriple, 'E',
		 * GTItems.circuitSapphire, 'W', "dustTungsten", 'P', "plateIridium" });
		 */

		// mold recipes
		ItemStack mold = GTMaterialGen.get(GTItems.moldBlank, 1);
		recipes.addRecipe(mold, new Object[] { "H", "P", "P", 'H', hammer, 'P', "plateSteel" });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldBlock, 1), new Object[] { "F", "P", " ", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldCable, 1), new Object[] { " F", "P ", "  ", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldIngot, 1), new Object[] { "  ", "PF", "  ", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldNugget, 1), new Object[] { "  ", "P ", " F", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldPlate, 1), new Object[] { " ", "P", "F", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldStick, 1), new Object[] { "  ", " P", "F ", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldTube, 1), new Object[] { "  ", "FP", "  ", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldGear, 1), new Object[] { "F ", " P", "  ", 'F', file, 'P',
				mold });
		recipes.addRecipe(GTMaterialGen.get(GTItems.moldBottle, 1), new Object[] { "  ", "FP", "  ", 'F', file, 'P',
				GTItems.moldTube });

	}

	public static void recipeShaped2() {
		/*
		 * Recipes for blocks
		 */

		// recipes.addRecipe(GTMaterialGen.get(GTBlocks.superCasingBlock, 4),
		// new Object[] { "CCC", "PWP", "EEE", 'C',
		// Ic2Items.reactorCoolantCellSix.copy(), 'E',
		// GTItems.circuitSapphire, 'W', "casingMachineTungsten", 'P', "plateIridium"
		// });

		// recipes.addRecipe(GTMaterialGen.get(GTBlocks.fusionCasingBlock),
		// new Object[] { "CRC", "BSB", "CRC", 'C', GTItems.circuitSapphire, 'S',
		// GTBlocks.superCasingBlock, 'B',
		// "casingMachineChrome", 'R', Ic2Items.reactorReflectorIridium.copy() });

		recipes.addRecipe(GTMaterialGen.get(Blocks.TORCH, 3), new Object[] { "R", "I", 'I', "stickWood", 'R',
				"dustSulfur" });
		recipes.addRecipe(GTMaterialGen.get(Blocks.TORCH, 4), new Object[] { "R", "I", 'I', "stickWood", 'R',
				"dustCoal" });
		recipes.addRecipe(GTMaterialGen.get(Blocks.TORCH, 4), new Object[] { "R", "I", 'I', "stickWood", 'R',
				"dustCharcoal" });

		IRecipeInput ingotMetal = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("ingotIron"),
				new RecipeInputOreDict("ingotRefinedIron"), new RecipeInputOreDict("ingotAluminium"),
				new RecipeInputOreDict("ingotAluminum"), new RecipeInputOreDict("ingotBronze"),
				new RecipeInputOreDict("ingotBrass"), new RecipeInputOreDict("ingotSilver"),
				new RecipeInputOreDict("ingotSteel") });

		recipes.addRecipe(GTMaterialGen.get(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood", 'C',
				"cobblestone", 'I', ingotMetal, 'R', "dustRedstone" });

		recipes.addRecipe(GTMaterialGen.get(Blocks.HOPPER), new Object[] { "I I", "ICI", " I ", 'W', "plankWood", 'C',
				"cobblestone", 'I', ingotMetal, 'C', "chestWood" });

		recipes.addRecipe(GTMaterialGen.get(Items.ARROW, 4), new Object[] { "T", "S", "F", 'T', Items.FLINT, 'S',
				"stickWood", 'F', GTItems.plasticFletching });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.sandSlagrete), new Object[] { "XXX", "XSX", "XXX", 'X', "gravel",
				'S', GTBlocks.sandSlag });
	}

	public static void recipeShaped3() {
		/*
		 * Recipes for tiles and machines
		 */

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.mortar), new Object[] { " X ", "CXC", "CCC", 'X',
				"ingotRefinedIron", 'C', Blocks.HARDENED_CLAY });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.drum), new Object[] { " H ", "XIX", "XIX", 'X',
				"plateStainlessSteel", 'I', "stickStainlessSteel", 'H', hammer, });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileHeating), new Object[] { "XHX", "XBX", "XCX", 'X',
				"plateCopper", 'B', Blocks.IRON_BARS, 'H', hammer, 'C', "coilCopper" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBloomery), new Object[] { "HXX", "XFX", "XXW", 'X',
				"plateBronze", 'F', Blocks.FURNACE, 'W', wrench, 'H', hammer, });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCharcoalPit), new Object[] { "XPX", "XWX", "XFX", 'X',
				"plateBronze", 'P', "plateRefinedIron", 'W', wrench, 'F', Items.FLINT });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBlastFurnace, 1), new Object[] { "PCP", "PFP", "PCP", 'P',
				"plateRefinedIron", 'F', Ic2Items.ironFurnace.copy(), 'C', "coilCopper" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBlastFurnace, 1), new Object[] { "PCP", "PFP", "PCP", 'P',
				"plateRefinedIron", 'F', GTBlocks.tileBloomery, 'C', "coilCopper" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileElectrolyzer, 1), new Object[] { "T H", "WEW", "RCR", 'T',
				wrench, 'H', hammer, 'W', Ic2Items.goldCable, 'E', "blockGlass", 'R', Ic2Items.doubleInsulatedGoldCable,
				'C', GTBlocks.casingPlastic1x });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileShredder, 1), new Object[] { "W H", "ASM", "DDD", 'W', wrench,
				'H', hammer, 'A', "circuitAdvanced", 'S', "casingMachineSteel", 'M', GTItems.motorMV, 'D',
				"gemDiamond" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileRoaster, 1), new Object[] { "HPW", "PMP", "PCP", 'C',
				"coilConstantan", 'M', Ic2Items.electroFurnace.copy(), 'P', "plateInvar", 'H', hammer, 'W', wrench });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBath), new Object[] { "XWX", "XFX", "XXX", 'X',
				"plateStainlessSteel", 'F', "casingMachineStainlessSteel", 'W', wrench });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileChemicalReactor, 1), new Object[] { "W H", "KMC", "PSP", 'W',
				wrench, 'H', hammer, 'C', GTMaterialGen.getCasing(GTMaterial.StainlessSteel, 1), 'M', GTItems.motorHV,
				'S', "stickStainlessSteel", 'P', "plateStainlessSteel", 'K', "circuitAdvanced" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileRefractory, 1), new Object[] { "W H", "MCM", "PSP", 'W',
				wrench, 'H', hammer, 'C', GTMaterialGen.getCasing(GTMaterial.Steel, 1), 'M', "circuitAdvanced", 'S',
				"coilGraphite", 'P', Ic2Items.tribbleInsulatedIronCable });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCryogenicSeparator, 1), new Object[] { "WMH", "SCS", "SPS",
				'W', wrench, 'H', hammer, 'C', GTMaterialGen.getCasing(GTMaterial.Aluminium, 1), 'M', "circuitAdvanced",
				'S', "plateUltimet", 'P', "plateSilicon" });

		// recipes.addRecipe(GTMaterialGen.get(GTBlocks.fusionComputer, 1),
		// new Object[] { "EPE", "LCL", "ESE", 'E', GTItems.circuitSapphire, 'S',
		// "plateTungstensteel", 'L',
		// "plateIridium", 'C', GTBlocks.computerCube, 'P', "platePlutonium" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileLightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				"plateNiobium", 'S', GTBlocks.batteryLapotronSmall, 'A', "stickTungstensteel" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingLightning, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				"plateNiobium", 'S', "stickIridium", 'A', "wireFineNiobiumTitanium" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCentrifuge, 1), new Object[] { "WCH", "MEM", "GCG", 'E',
				"casingMachineRefinedIron", 'M', GTItems.motorLV, 'G', "gearSteel", 'C', "circuitBasic", 'W', wrench,
				'H', hammer, });

		// recipes.addRecipe(GTMaterialGen.get(GTBlocks.computerCube, 1),
		// new Object[] { "RGD", "GMG", "DGR", 'D', GTItems.circuitDiamond, 'R',
		// GTItems.circuitSapphire, 'G',
		// "blockGlass", 'M', "casingMachineTitanium" });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileSmelter), new Object[] { "PBP", "FCF", "PBP", 'P',
				"plateRefinedIron", 'B', "coilConstantan", 'C', "plateInvar", 'F', Ic2Items.electroFurnace.copy() });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tilePlayerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
				Blocks.OBSERVER, 'C', "circuitBasic", 'c', "casingMachineRefinedIron" });

		/*
		 * Below many recipes are disabled because they arent finished!
		 */

		if (GTValues.debugMode) {

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.matterReplicator, 1),
			// new Object[] { "dCd", "TQE", "DBD", 'd', GTItems.circuitEmerald, 'C',
			// GTBlocks.computerCube, 'T',
			// Ic2Items.teleporter, 'Q', GTBlocks.digitalChest, 'E', GTItems.craftingTablet,
			// 'D',
			// GTItems.circuitDiamond, 'B', GTBlocks.tinyEnergium });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.matterFabricator, 1),
			// new Object[] { "ETE", "HLH", "ETE", 'E', GTItems.circuitSapphire, 'T',
			// Ic2Items.teleporter, 'H',
			// "casingMachineTitanium", 'L', GTBlocks.smallLapotron });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.digitalTransformerIV, 1),
			// new Object[] { "ELE", "SHS", "ELE", 'E', GTItems.circuitSapphire, 'S',
			// GTBlocks.superCasingBlock,
			// 'H', "casingMachineIridium", 'L', GTBlocks.smallLapotron });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.chargeOMat, 1),
			// new Object[] { "RCR", "AEA", "RMR", 'E', GTBlocks.smallLapotron, 'R',
			// GTItems.circuitSapphire, 'A',
			// "chestWood", 'C', GTBlocks.computerCube, 'M', "casingMachineTitanium" });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.multiEnergyStorage), new
			// Object[] { " G ",
			// "CMC", " G ", 'C',
			// "circuitAdvanced", 'M', GTBlocks.crystalCasingBlock, 'G',
			// Ic2Items.glassFiberCable.copy() });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.lapotronCable, 4), new Object[]
			// { "HEH",
			// "SSS", "HEH", 'E',
			// GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock, 'H',
			// GTBlocks.smallLapotron });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.energiumCable, 4), new Object[]
			// { "HEH",
			// "SSS", "HEH", 'E',
			// GTItems.circuitRuby, 'S', GTBlocks.superCasingBlock, 'H',
			// GTBlocks.smallEnergium });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBasicEnergy), new Object[] {
			// "OOO",
			// "OCO", "OOO", 'O',
			// GTBlocks.batteryLapotronSmall, 'C', GTBlocks.tileComputer });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumEnergy, 1), new
			// Object[] {
			// "PHP", "HEH", "PHP", 'P',
			// "plateIridium", 'H', GTBlocks.tileBasicEnergy, 'E', Blocks.ENDER_CHEST });

			// recipes.addRecipe(GTMaterialGen.get(GTBlocks.digitalChest, 1),
			// new Object[] { "IDI", "CMC", "IDI", 'D', GTItems.circuitDiamond, 'I',
			// GTItems.circuitEmerald, 'C',
			// "chestWood", 'M', "casingMachineTitanium" });

		}
	}

}
