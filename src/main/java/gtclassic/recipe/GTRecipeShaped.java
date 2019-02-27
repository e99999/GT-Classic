package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.item.recipe.upgrades.EnchantmentModifier;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;

public class GTRecipeShaped {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeShaped1() {
		/*
		 * Recipes for tools and items
		 */

		recipes.addRecipe(GT.get(GTItems.electroMagnet, 1), new Object[] { "M M", "WMW", "IBI", 'M', Ic2Items.magnet,
				'B', Ic2Items.battery, 'I', GTValues.plateElectric, 'W', Ic2Items.copperCable });

		recipes.addRecipe(GT.get(GTItems.rockCutter, 1), new Object[] { "DI ", "DI ", "DCB",
				new EnchantmentModifier(GT.get(GTItems.rockCutter), Enchantments.SILK_TOUCH).setUsesInput(), 'D',
				"dustDiamond", 'I', GTValues.plateElectric, 'C', "circuitBasic", 'B', Ic2Items.battery.copy() });

		recipes.addRecipe(GT.get(GTItems.advancedDrill, 1), new Object[] { "III", "ICI", "IBI", 'I', "plateSteel", 'B',
				GTBlocks.smallLithium, 'C', "circuitBasic" });

		recipes.addRecipe(GT.get(GTItems.advancedDrill2, 1), new Object[] { "DDD", "ICI", "IBI", 'I', "plateTitanium",
				'B', GTBlocks.medLithium, 'C', "circuitAdvanced", 'D', "gemDiamond" });

		recipes.addRecipe(GT.get(GTItems.advancedDrill3, 1), new Object[] { "DDD", "ICI", "IBI", 'I',
				"plateTungstenSteel", 'B', GTBlocks.largeLithium, 'C', GTItems.circuitEmerald, 'D', "gemDiamond" });

		recipes.addRecipe(GT.get(GTItems.advancedChainsaw2, 1), new Object[] { " II", "ICI", "BI ", 'I',
				"plateTitanium", 'B', GTBlocks.medLithium, 'C', "circuitAdvanced", });

		recipes.addRecipe(GT.get(GTItems.glassTube, 32), new Object[] { "G G", "G G", " G ", 'G', "blockGlass" });

		recipes.addRecipe(GT.get(GTBlocks.smallLapotron, 1),
				new Object[] { "LLL", "LPL", "LLL", 'L', GTBlocks.tinyLapotron, 'P', "plateIridium" });

		recipes.addRecipe(GT.get(GTBlocks.smallLithium, 1), new Object[] { " G ", "ALA", "ALA", 'G',
				Ic2Items.doubleInsulatedGoldCable.copy(), 'A', "plateAluminium", 'L', GT.getChemical(M.Lithium, 1) });

		recipes.addRecipe(GT.get(GTItems.destructoPack, 1), new Object[] { "BIB", "ICI", "BIB", 'B', GTValues.lava, 'C',
				"circuitBasic", 'I', GTValues.plateElectric });

		recipes.addRecipe(GT.get(GTItems.craftingTablet, 1), new Object[] { "BIB", "ICI", "BIB", 'B', "workbench", 'C',
				"circuitBasic", 'I', GTValues.plateElectric });

		recipes.addRecipe(GT.get(GTItems.teslaStaff, 1), new Object[] { " SL", " PS", "P  ", 'L',
				GTBlocks.smallLapotron, 'S', GTBlocks.superCasingBlock, 'P', "stickIridium" });

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

	}

	public static void recipeShaped2() {
		/*
		 * Recipes for blocks
		 */

		recipes.addRecipe(GT.get(GTBlocks.superCasingBlock, 4),
				new Object[] { "CCC", "PWP", "EEE", 'C', Ic2Items.reactorCoolantCellSix.copy(), 'E',
						GTItems.circuitSapphire, 'W', "casingMachineTungsten", 'P', "plateIridium" });

		recipes.addRecipe(GT.get(GTBlocks.fusionCasingBlock),
				new Object[] { "CRC", "BSB", "CRC", 'C', GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock, 'B',
						"casingMachineChrome", 'R', Ic2Items.reactorReflectorIridium.copy() });

		recipes.addRecipe(GT.get(GTBlocks.crystalCasingBlock),
				new Object[] { "BBB", "BCB", "BBB", 'B', "blockRuby", 'C', "casingMachineAluminium" });

		recipes.addRecipe(GT.get(GTBlocks.kanthalCoilBlock), new Object[] { "XXX", "XXX", "XXX", 'X', "stickKanthal" });

		recipes.addRecipe(GT.get(GTBlocks.nichromeCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickNichrome" });

		recipes.addRecipe(GT.get(GTBlocks.constantanCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickConstantan" });

		recipes.addRecipe(GT.get(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood", 'C',
				"cobblestone", 'I', GTValues.ingotElectric, 'R', "dustRedstone" });

	}

	public static void recipeShaped3() {
		/*
		 * Recipes for tiles and machines
		 */

		recipes.addRecipe(GT.get(GTBlocks.fusionComputer, 1),
				new Object[] { "EPE", "LCL", "ESE", 'E', GTItems.circuitSapphire, 'S', "plateTungstenSteel", 'L',
						GTBlocks.medLapotron, 'C', GTBlocks.fusionCasingBlock, 'P', "platePlutonium" });

		recipes.addRecipe(GT.get(GTBlocks.lightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				GTItems.circuitSapphire, 'S', "casingMachineIridium", 'A', "stickTungstenSteel" });

		recipes.addRecipe(GT.get(GTBlocks.industrialCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', GTValues.plateElectric, 'A', "casingMachineSteel", 'C', "circuitAdvanced" });

		recipes.addRecipe(GT.get(GTBlocks.computerCube, 1),
				new Object[] { "RGD", "GMG", "DGR", 'D', GTItems.circuitDiamond, 'R', GTItems.circuitSapphire, 'G',
						"blockGlass", 'M', "casingMachineTitanium" });

		recipes.addRecipe(GT.get(GTBlocks.basicEnergyStorage),
				new Object[] { "OOO", "OCO", "OOO", 'O', GTBlocks.smallLapotron, 'C', GTBlocks.computerCube });

		recipes.addRecipe(GT.get(GTBlocks.alloySmelter), new Object[] { "PBP", "CFC", "PBP", 'P', "plateInvar", 'B',
				GTBlocks.constantanCoilBlock, 'C', "circuitBasic", 'F', Ic2Items.electroFurnace.copy() });

		/*
		 * Below many recipes are disabled because they arent finished!
		 */

		if (GTValues.debugMode) {

			recipes.addRecipe(GT.get(GTBlocks.uuMatterAssembler, 1),
					new Object[] { "dCd", "TQE", "DBD", 'd', GTItems.circuitEmerald, 'C', GTBlocks.computerCube, 'T',
							Ic2Items.teleporter, 'Q', GTBlocks.digitalChestLV, 'E', GTBlocks.autoCrafter, 'D',
							GTItems.circuitDiamond, 'B', GTBlocks.tinyEnergium });

			recipes.addRecipe(GT.get(GTBlocks.playerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
					Blocks.REDSTONE_TORCH, 'C', "circuitAdvanced", 'c', "casingMachineSteel" });

			recipes.addRecipe(GT.get(GTBlocks.matterFabricator, 1),
					new Object[] { "ETE", "HLH", "ETE", 'E', GTItems.circuitSapphire, 'T', Ic2Items.teleporter, 'H',
							"casingMachineTitanium", 'L', GTBlocks.smallLapotron });

			recipes.addRecipe(GT.get(GTBlocks.digitalTransformerIV, 1),
					new Object[] { "ELE", "SHS", "ELE", 'E', GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock,
							'H', "casingMachineIridium", 'L', GTBlocks.smallLapotron });

			recipes.addRecipe(GT.get(GTBlocks.autoCrafter, 1), new Object[] { " B ", "CcC", " A ", 'B',
					Ic2Items.battery, 'C', "circuitAdvanced", 'c', "workbench", 'A', "casingMachineSteel" });

			recipes.addRecipe(GT.get(GTBlocks.chargeOMat, 1),
					new Object[] { "RCR", "AEA", "RMR", 'E', GTBlocks.smallLapotron, 'R', GTItems.circuitSapphire, 'A',
							"chestWood", 'C', GTBlocks.computerCube, 'M', "casingMachineTitanium" });

			recipes.addRecipe(GT.get(GTBlocks.multiEnergyStorage), new Object[] { " G ", "CMC", " G ", 'C',
					"circuitAdvanced", 'M', GTBlocks.crystalCasingBlock, 'G', Ic2Items.glassFiberCable.copy() });

			recipes.addRecipe(GT.get(GTBlocks.lapotronCable, 4), new Object[] { "HEH", "SSS", "HEH", 'E',
					GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock, 'H', GTBlocks.smallLapotron });

			recipes.addRecipe(GT.get(GTBlocks.energiumCable, 4), new Object[] { "HEH", "SSS", "HEH", 'E',
					GTItems.circuitRuby, 'S', GTBlocks.superCasingBlock, 'H', GTBlocks.smallEnergium });

			recipes.addRecipe(GT.get(GTBlocks.quantumEnergyStorage, 1), new Object[] { "PHP", "HEH", "PHP", 'P',
					"plateIridium", 'H', GTBlocks.basicEnergyStorage, 'E', Blocks.ENDER_CHEST });

			recipes.addRecipe(GT.get(GTBlocks.digitalChestLV, 1), new Object[] { "IDI", "CMC", "IDI", 'D',
					GTItems.circuitDiamond, 'I', GTItems.circuitEmerald, 'C', "chestWood", 'M', "casingMachineSteel" });

			recipes.addRecipe(GT.get(GTBlocks.digitalChestMV, 1),
					new Object[] { "IDI", "CMC", "IDI", 'D', GTItems.circuitDiamond, 'I', GTItems.circuitEmerald, 'C',
							"chestWood", 'M', "casingMachineAluminium" });
		}
	}

}
