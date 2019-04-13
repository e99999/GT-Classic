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
	static GTMaterialGen GT;
	static GTMaterial M;

	static String hammer = "craftingToolForgeHammer";
	static String file = "craftingToolFile";
	static String wrench = "craftingToolWrench";

	public static void recipeShaped1() {
		/*
		 * Recipes for tools and items
		 */

		IRecipeInput plateAnyIron = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("plateIron"),
				new RecipeInputOreDict("plateRefinedIron"), new RecipeInputOreDict("plateSteel") });

		recipes.addRecipe(GT.get(GTItems.ironKnife, 1), new Object[] { "PPS", 'P', "plateIron", 'S', "stickWood" });

		recipes.addRecipe(GT.get(GTItems.electroMagnet, 1), new Object[] { "M M", "WMW", "IBI", 'M', Ic2Items.magnet,
				'B', Ic2Items.battery, 'I', plateAnyIron, 'W', Ic2Items.copperCable });

		recipes.addRecipe(GT.get(GTItems.rockCutter, 1),
				new Object[] { "DI ", "DI ", "DCB",
						new EnchantmentModifier(GT.get(GTItems.rockCutter), Enchantments.SILK_TOUCH).setUsesInput(),
						'D', "gemDiamond", 'I', "plateSteel", 'C', "circuitBasic", 'B', Ic2Items.battery.copy() });

		recipes.addRecipe(GT.get(GTItems.testTube, 32), new Object[] { "G G", "G G", " G ", 'G', "blockGlass" });

		recipes.addRecipe(GT.get(GTBlocks.smallLithium, 1), new Object[] { " G ", "ALA", "ALA", 'G', Ic2Items.goldCable,
				'A', "plateAluminium", 'L', GT.getFluid(M.Lithium, 1) });

		recipes.addRecipe(GT.get(GTItems.destructoPack, 1),
				new Object[] { "BIB", "ICI", "BIB", 'B', GTValues.lava, 'C', "circuitBasic", 'I', plateAnyIron });

		recipes.addRecipe(GT.get(GTItems.craftingTablet, 1),
				new Object[] { "BIB", "ICI", "BIB", 'B', "workbench", 'C', "circuitBasic", 'I', plateAnyIron });

		recipes.addRecipe(GT.get(GTItems.teslaStaff, 1), new Object[] { " SL", " PS", "P  ", 'L',
				GTBlocks.smallLapotron, 'S', GTBlocks.superCasingBlock, 'P', "stickIridium" });

		recipes.addRecipe(GT.get(GTItems.portableScanner, 1),
				new Object[] { "PEP", "CFC", "PBP", 'P', "plateAluminium", 'E', GT.getIc2(Ic2Items.euReader, 1), 'F',
						GT.getIc2(Ic2Items.cropAnalyzer, 1), 'C', "circuitAdvanced", 'B',
						GT.get(GTBlocks.smallLithium) });

		/*
		 * recipes.addRecipe(GT.get(GTItems.heatStorageSingle, 1), new Object[] { " I ",
		 * "IHI", " I ", 'I', "ingotTin", 'H', GTItems.helium });
		 * 
		 * recipes.addRecipe(GT.get(GTItems.heatStorageTriple, 1), new Object[] { "III",
		 * "HHH", "III", 'I', "ingotTin", 'H', GTItems.heatStorageSingle });
		 * 
		 * recipes.addRecipe(GT.get(GTItems.heatStorageSix, 1), new Object[] { "IHI",
		 * "IPI", "IHI", 'I', "ingotTin", 'H', GTItems.heatStorageTriple, 'P',
		 * Ic2Items.denseCopperPlate });
		 * 
		 * 
		 * recipes.addRecipe(GT.get(GTBlocks.superCasingBlock, 4), new Object[] { "CCC",
		 * "PWP", "EEE", 'C', GTItems.heatStorageTriple, 'E', GTItems.circuitSapphire,
		 * 'W', "dustTungsten", 'P', "plateIridium" });
		 */

		// mold recipes
		ItemStack mold = GT.get(GTItems.moldBlank, 1);
		recipes.addRecipe(mold, new Object[] { "H", "P", "P", 'H', hammer, 'P', "plateSteel" });
		recipes.addRecipe(GT.get(GTItems.moldBlock, 1), new Object[] { "F", "P", " ", 'F', file, 'P', mold });
		recipes.addRecipe(GT.get(GTItems.moldCable, 1), new Object[] { " F", "P ", "  ", 'F', file, 'P', mold });
		recipes.addRecipe(GT.get(GTItems.moldIngot, 1), new Object[] { "  ", "PF", "  ", 'F', file, 'P', mold });
		recipes.addRecipe(GT.get(GTItems.moldNugget, 1), new Object[] { "  ", "P ", " F", 'F', file, 'P', mold });
		recipes.addRecipe(GT.get(GTItems.moldPlate, 1), new Object[] { " ", "P", "F", 'F', file, 'P', mold });
		recipes.addRecipe(GT.get(GTItems.moldStick, 1), new Object[] { "  ", " P", "F ", 'F', file, 'P', mold });
		recipes.addRecipe(GT.get(GTItems.moldTube, 1), new Object[] { "  ", "FP", "  ", 'F', file, 'P', mold });
		recipes.addRecipe(GT.get(GTItems.moldGear, 1), new Object[] { "F ", " P", "  ", 'F', file, 'P', mold });

		// grinders
		recipes.addRecipe(GT.get(GTItems.grinderSteel, 1),
				new Object[] { "PPP", "PGP", "PPP", 'P', "plateSteel", 'G', "gearSteel" });
		recipes.addRecipe(GT.get(GTItems.grinderTitanium, 1),
				new Object[] { "MPM", "PGP", "MPM", 'P', "plateSteel", 'M', "plateTitanium", 'G', "gearSteel" });
		recipes.addRecipe(GT.get(GTItems.grinderTungstensteel, 1),
				new Object[] { "MPM", "PGP", "MPM", 'P', "plateSteel", 'M', "plateTungstensteel", 'G', "gearSteel" });

	}

	public static void recipeShaped2() {
		/*
		 * Recipes for blocks
		 */

		// recipes.addRecipe(GT.get(GTBlocks.superCasingBlock, 4),
		// new Object[] { "CCC", "PWP", "EEE", 'C',
		// Ic2Items.reactorCoolantCellSix.copy(), 'E',
		// GTItems.circuitSapphire, 'W', "casingMachineTungsten", 'P', "plateIridium"
		// });

		// recipes.addRecipe(GT.get(GTBlocks.fusionCasingBlock),
		// new Object[] { "CRC", "BSB", "CRC", 'C', GTItems.circuitSapphire, 'S',
		// GTBlocks.superCasingBlock, 'B',
		// "casingMachineChrome", 'R', Ic2Items.reactorReflectorIridium.copy() });

		recipes.addRecipe(GT.get(GTBlocks.copperCoilBlock), new Object[] { "XXX", "XXX", "XXX", 'X', "stickCopper" });

		recipes.addRecipe(GT.get(GTBlocks.constantanCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickConstantan" });

		recipes.addRecipe(GT.get(GTBlocks.graphiteCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickGraphite" });

		recipes.addRecipe(GT.get(GTBlocks.nichromeCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickNichrome" });

		recipes.addRecipe(GT.get(Blocks.TORCH, 3), new Object[] { "R", "I", 'I', "stickWood", 'R', "dustSulfur" });
		recipes.addRecipe(GT.get(Blocks.TORCH, 4), new Object[] { "R", "I", 'I', "stickWood", 'R', "dustCoal" });
		recipes.addRecipe(GT.get(Blocks.TORCH, 4), new Object[] { "R", "I", 'I', "stickWood", 'R', "dustCharcoal" });

		IRecipeInput ingotMetal = new RecipeInputCombined(1,
				new IRecipeInput[] { new RecipeInputOreDict("ingotIron"), new RecipeInputOreDict("ingotRefinedIron"),
						new RecipeInputOreDict("ingotAluminium"), new RecipeInputOreDict("ingotAluminum"),
						new RecipeInputOreDict("ingotBronze"), new RecipeInputOreDict("ingotBrass"),
						new RecipeInputOreDict("ingotSilver"), new RecipeInputOreDict("ingotSteel") });

		recipes.addRecipe(GT.get(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood", 'C',
				"cobblestone", 'I', ingotMetal, 'R', "dustRedstone" });

		recipes.addRecipe(GT.get(Blocks.HOPPER), new Object[] { "I I", "ICI", " I ", 'W', "plankWood", 'C',
				"cobblestone", 'I', ingotMetal, 'C', "chestWood" });

		recipes.addRecipe(GT.get(GTBlocks.slagcreteSand),
				new Object[] { "XXX", "XSX", "XXX", 'X', "gravel", 'S', GTBlocks.slagSand });
	}

	public static void recipeShaped3() {
		/*
		 * Recipes for tiles and machines
		 */

		recipes.addRecipe(GT.get(GTBlocks.heatingElement), new Object[] { "XHX", "XPX", "XXX", 'X', "plateCopper", 'P',
				"plateRefinedIron", 'H', hammer, 'F', Items.FLINT });

		recipes.addRecipe(GT.get(GTBlocks.bloomery),
				new Object[] { "XXX", "XFX", "XXX", 'X', "plateBronze", 'F', Blocks.FURNACE });

		recipes.addRecipe(GT.get(GTBlocks.charcoalPit), new Object[] { "XPX", "XWX", "XFX", 'X', "plateBronze", 'P',
				"plateRefinedIron", 'W', wrench, 'F', Items.FLINT });

		recipes.addRecipe(GT.get(GTBlocks.mortar),
				new Object[] { " X ", "CXC", "CCC", 'X', "ingotRefinedIron", 'C', Blocks.HARDENED_CLAY });

		recipes.addRecipe(GT.get(GTBlocks.blastFurnace, 1), new Object[] { "PCP", "PFP", "PCP", 'P', "plateRefinedIron",
				'B', Ic2Items.battery.copy(), 'F', Ic2Items.ironFurnace.copy(), 'C', GTBlocks.copperCoilBlock });

		recipes.addRecipe(GT.get(GTBlocks.blastFurnace, 1), new Object[] { "PCP", "PFP", "PCP", 'P', "plateRefinedIron",
				'B', Ic2Items.battery.copy(), 'F', GTBlocks.bloomery, 'C', GTBlocks.copperCoilBlock });

		recipes.addRecipe(GT.get(GTBlocks.industrialElectrolyzer, 1),
				new Object[] { "WEW", "RCR", 'W', Ic2Items.goldCable.copy(), 'E', Ic2Items.electrolyzer.copy(), 'R',
						Ic2Items.doubleInsulatedGoldCable, 'C', "casingMachineStainlessSteel" });

		recipes.addRecipe(GT.get(GTBlocks.industrialProcessor, 1), new Object[] { "PCP", "GMG", "PCP", 'P',
				"plateSteel", 'C', "circuitAdvanced", 'G', "gearSteel", 'M', Ic2Items.advMachine });

		// recipes.addRecipe(GT.get(GTBlocks.fusionComputer, 1),
		// new Object[] { "EPE", "LCL", "ESE", 'E', GTItems.circuitSapphire, 'S',
		// "plateTungstensteel", 'L',
		// "plateIridium", 'C', GTBlocks.computerCube, 'P', "platePlutonium" });

		// recipes.addRecipe(GT.get(GTBlocks.lightningRod, 1), new Object[] { "EAE",
		// "ASA", "EAE", 'E',
		// GTItems.circuitSapphire, 'S', "casingMachineIridium", 'A',
		// "stickTungstensteel" });

		recipes.addRecipe(GT.get(GTBlocks.industrialCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', "gearSteel", 'A', "stickSteel", 'C', "circuitBasic" });

		// recipes.addRecipe(GT.get(GTBlocks.computerCube, 1),
		// new Object[] { "RGD", "GMG", "DGR", 'D', GTItems.circuitDiamond, 'R',
		// GTItems.circuitSapphire, 'G',
		// "blockGlass", 'M', "casingMachineTitanium" });

		recipes.addRecipe(GT.get(GTBlocks.electricSmelter), new Object[] { "PBP", "CFC", "PBP", 'P', "plateInvar", 'B',
				GTBlocks.constantanCoilBlock, 'C', "circuitBasic", 'F', Ic2Items.electroFurnace.copy() });

		recipes.addRecipe(GT.get(GTBlocks.playerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D', Blocks.OBSERVER,
				'C', "circuitBasic", 'c', "casingMachineRefinedIron" });

		/*
		 * Below many recipes are disabled because they arent finished!
		 */

		if (GTValues.debugMode) {

			// recipes.addRecipe(GT.get(GTBlocks.matterReplicator, 1),
			// new Object[] { "dCd", "TQE", "DBD", 'd', GTItems.circuitEmerald, 'C',
			// GTBlocks.computerCube, 'T',
			// Ic2Items.teleporter, 'Q', GTBlocks.digitalChest, 'E', GTItems.craftingTablet,
			// 'D',
			// GTItems.circuitDiamond, 'B', GTBlocks.tinyEnergium });

			// recipes.addRecipe(GT.get(GTBlocks.matterFabricator, 1),
			// new Object[] { "ETE", "HLH", "ETE", 'E', GTItems.circuitSapphire, 'T',
			// Ic2Items.teleporter, 'H',
			// "casingMachineTitanium", 'L', GTBlocks.smallLapotron });

			// recipes.addRecipe(GT.get(GTBlocks.digitalTransformerIV, 1),
			// new Object[] { "ELE", "SHS", "ELE", 'E', GTItems.circuitSapphire, 'S',
			// GTBlocks.superCasingBlock,
			// 'H', "casingMachineIridium", 'L', GTBlocks.smallLapotron });

			// recipes.addRecipe(GT.get(GTBlocks.chargeOMat, 1),
			// new Object[] { "RCR", "AEA", "RMR", 'E', GTBlocks.smallLapotron, 'R',
			// GTItems.circuitSapphire, 'A',
			// "chestWood", 'C', GTBlocks.computerCube, 'M', "casingMachineTitanium" });

			// recipes.addRecipe(GT.get(GTBlocks.multiEnergyStorage), new Object[] { " G ",
			// "CMC", " G ", 'C',
			// "circuitAdvanced", 'M', GTBlocks.crystalCasingBlock, 'G',
			// Ic2Items.glassFiberCable.copy() });

			// recipes.addRecipe(GT.get(GTBlocks.lapotronCable, 4), new Object[] { "HEH",
			// "SSS", "HEH", 'E',
			// GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock, 'H',
			// GTBlocks.smallLapotron });

			// recipes.addRecipe(GT.get(GTBlocks.energiumCable, 4), new Object[] { "HEH",
			// "SSS", "HEH", 'E',
			// GTItems.circuitRuby, 'S', GTBlocks.superCasingBlock, 'H',
			// GTBlocks.smallEnergium });

			recipes.addRecipe(GT.get(GTBlocks.basicEnergyStorage),
					new Object[] { "OOO", "OCO", "OOO", 'O', GTBlocks.smallLapotron, 'C', GTBlocks.computerCube });

			recipes.addRecipe(GT.get(GTBlocks.quantumEnergyStorage, 1), new Object[] { "PHP", "HEH", "PHP", 'P',
					"plateIridium", 'H', GTBlocks.basicEnergyStorage, 'E', Blocks.ENDER_CHEST });

			// recipes.addRecipe(GT.get(GTBlocks.digitalChest, 1),
			// new Object[] { "IDI", "CMC", "IDI", 'D', GTItems.circuitDiamond, 'I',
			// GTItems.circuitEmerald, 'C',
			// "chestWood", 'M', "casingMachineTitanium" });

		}
	}

}
