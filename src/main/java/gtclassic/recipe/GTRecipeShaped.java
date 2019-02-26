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
import net.minecraft.item.ItemStack;

public class GTRecipeShaped {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeShaped1() {
		/*
		 * Recipes for tools and items
		 */

		recipes.addRecipe(new ItemStack(GTItems.electroMagnet, 1), new Object[] { "M M", "WMW", "IBI", 'M',
				Ic2Items.magnet, 'B', Ic2Items.battery, 'I', GTValues.plateElectric, 'W', Ic2Items.copperCable });

		recipes.addRecipe(new ItemStack(GTItems.rockCutter, 1),
				new Object[] { "DI ", "DI ", "DCB",
						new EnchantmentModifier(new ItemStack(GTItems.rockCutter), Enchantments.SILK_TOUCH)
								.setUsesInput(),
						'D', "dustDiamond", 'I', GTValues.plateElectric, 'C', "circuitBasic", 'B',
						Ic2Items.battery.copy() });

		recipes.addRecipe(new ItemStack(GTItems.advancedDrill, 1), new Object[] { "III", "ICI", "IBI", 'I',
				"plateSteel", 'B', GTBlocks.smallLithium, 'C', "circuitBasic" });

		recipes.addRecipe(new ItemStack(GTItems.advancedDrill2, 1), new Object[] { "DDD", "ICI", "IBI", 'I',
				"plateTitanium", 'B', GTBlocks.medLithium, 'C', "circuitAdvanced", 'D', "gemDiamond" });

		recipes.addRecipe(new ItemStack(GTItems.advancedDrill3, 1), new Object[] { "DDD", "ICI", "IBI", 'I',
				"plateTungstenSteel", 'B', GTBlocks.largeLithium, 'C', GTItems.circuitEmerald, 'D', "gemDiamond" });

		recipes.addRecipe(new ItemStack(GTItems.advancedChainsaw2, 1), new Object[] { " II", "ICI", "BI ", 'I',
				"plateTitanium", 'B', GTBlocks.medLithium, 'C', "circuitAdvanced", });

		recipes.addRecipe(new ItemStack(GTItems.glassTube, 32),
				new Object[] { "G G", "G G", " G ", 'G', "blockGlass" });

		recipes.addRecipe(new ItemStack(GTBlocks.smallLapotron, 1),
				new Object[] { "LLL", "LPL", "LLL", 'L', GTBlocks.tinyLapotron, 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTBlocks.smallLithium, 1), new Object[] { " G ", "ALA", "ALA", 'G',
				Ic2Items.doubleInsulatedGoldCable.copy(), 'A', "plateAluminium", 'L', GT.getChemical(M.Lithium, 1) });

		recipes.addRecipe(new ItemStack(GTItems.destructoPack, 1), new Object[] { "BIB", "ICI", "BIB", 'B',
				GTValues.lava, 'C', "circuitBasic", 'I', GTValues.plateElectric });

		recipes.addRecipe(new ItemStack(GTItems.craftingTablet, 1), new Object[] { "BIB", "ICI", "BIB", 'B',
				"workbench", 'C', "circuitBasic", 'I', GTValues.plateElectric });

		recipes.addRecipe(new ItemStack(GTItems.teslaStaff, 1), new Object[] { " SL", " PS", "P  ", 'L',
				GTBlocks.smallLapotron, 'S', GTBlocks.superCasingBlock, 'P', "stickIridium" });

		/*
		 * recipes.addRecipe(new ItemStack(GTItems.heatStorageSingle, 1), new Object[] {
		 * " I ", "IHI", " I ", 'I', "ingotTin", 'H', GTItems.helium });
		 * 
		 * recipes.addRecipe(new ItemStack(GTItems.heatStorageTriple, 1), new Object[] {
		 * "III", "HHH", "III", 'I', "ingotTin", 'H', GTItems.heatStorageSingle });
		 * 
		 * recipes.addRecipe(new ItemStack(GTItems.heatStorageSix, 1), new Object[] {
		 * "IHI", "IPI", "IHI", 'I', "ingotTin", 'H', GTItems.heatStorageTriple, 'P',
		 * Ic2Items.denseCopperPlate });
		 * 
		 * 
		 * recipes.addRecipe(new ItemStack(GTBlocks.superCasingBlock, 4), new Object[] {
		 * "CCC", "PWP", "EEE", 'C', GTItems.heatStorageTriple, 'E',
		 * GTItems.circuitSapphire, 'W', "dustTungsten", 'P', "plateIridium" });
		 */

	}

	public static void recipeShaped2() {
		/*
		 * Recipes for blocks
		 */

		recipes.addRecipe(new ItemStack(GTBlocks.superCasingBlock, 4),
				new Object[] { "CCC", "PWP", "EEE", 'C', Ic2Items.reactorCoolantCellSix.copy(), 'E',
						GTItems.circuitSapphire, 'W', "casingMachineTungsten", 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTBlocks.fusionCasingBlock),
				new Object[] { "CRC", "BSB", "CRC", 'C', GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock, 'B',
						GTBlocks.nichromeCoilBlock, 'R', Ic2Items.reactorReflectorIridium.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.crystalCasingBlock),
				new Object[] { "BBB", "BCB", "BBB", 'B', "blockRuby", 'C', "casingMachineAluminium" });

		recipes.addRecipe(new ItemStack(GTBlocks.kanthalCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickKanthal" });

		recipes.addRecipe(new ItemStack(GTBlocks.nichromeCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickNichrome" });

		recipes.addRecipe(new ItemStack(GTBlocks.constantanCoilBlock),
				new Object[] { "XXX", "XXX", "XXX", 'X', "stickConstantan" });

		recipes.addRecipe(new ItemStack(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood", 'C',
				"cobblestone", 'I', GTValues.ingotElectric, 'R', "dustRedstone" });

	}

	public static void recipeShaped3() {
		/*
		 * Recipes for tiles and machines
		 */

		recipes.addRecipe(new ItemStack(GTBlocks.fusionComputer, 1),
				new Object[] { "EPE", "LCL", "ESE", 'E', GTItems.circuitSapphire, 'S', "plateTungstenSteel", 'L',
						GTBlocks.medLapotron, 'C', GTBlocks.fusionCasingBlock, 'P', "platePlutonium" });

		recipes.addRecipe(new ItemStack(GTBlocks.lightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				GTItems.circuitSapphire, 'S', "casingMachineIridium", 'A', "stickTungstenSteel" });

		recipes.addRecipe(new ItemStack(GTBlocks.industrialCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', GTValues.plateElectric, 'A', "casingMachineSteel", 'C', "circuitAdvanced" });

		recipes.addRecipe(new ItemStack(GTBlocks.computerCube, 1),
				new Object[] { "RGD", "GMG", "DGR", 'D', GTItems.circuitDiamond, 'R', GTItems.circuitSapphire, 'G',
						"blockGlass", 'M', "casingMachineTitanium" });

		recipes.addRecipe(new ItemStack(GTBlocks.basicEnergyStorage),
				new Object[] { "OOO", "OCO", "OOO", 'O', GTBlocks.smallLapotron, 'C', GTBlocks.computerCube });

		/*
		 * Below many recipes are disabled because they arent finished!
		 */

		if (GTValues.debugMode) {

			recipes.addRecipe(new ItemStack(GTBlocks.uuMatterAssembler, 1),
					new Object[] { "dCd", "TQE", "DBD", 'd', GTItems.circuitEmerald, 'C', GTBlocks.computerCube, 'T',
							Ic2Items.teleporter, 'Q', GTBlocks.digitalChestLV, 'E', GTBlocks.autoCrafter, 'D',
							GTItems.circuitDiamond, 'B', GTBlocks.tinyEnergium });

			recipes.addRecipe(new ItemStack(GTBlocks.playerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
					Blocks.REDSTONE_TORCH, 'C', "circuitAdvanced", 'c', "casingMachineSteel" });

			recipes.addRecipe(new ItemStack(GTBlocks.matterFabricator, 1),
					new Object[] { "ETE", "HLH", "ETE", 'E', GTItems.circuitSapphire, 'T', Ic2Items.teleporter, 'H',
							"casingMachineTitanium", 'L', GTBlocks.smallLapotron });

			recipes.addRecipe(new ItemStack(GTBlocks.digitalTransformerIV, 1),
					new Object[] { "ELE", "SHS", "ELE", 'E', GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock,
							'H', "casingMachineIridium", 'L', GTBlocks.smallLapotron });

			recipes.addRecipe(new ItemStack(GTBlocks.autoCrafter, 1), new Object[] { " B ", "CcC", " A ", 'B',
					Ic2Items.battery, 'C', "circuitAdvanced", 'c', "workbench", 'A', "casingMachineSteel" });

			recipes.addRecipe(new ItemStack(GTBlocks.chargeOMat, 1),
					new Object[] { "RCR", "AEA", "RMR", 'E', GTBlocks.smallLapotron, 'R', GTItems.circuitSapphire, 'A',
							"chestWood", 'C', GTBlocks.computerCube, 'M', "casingMachineTitanium" });

			recipes.addRecipe(new ItemStack(GTBlocks.multiEnergyStorage), new Object[] { " G ", "CMC", " G ", 'C',
					"circuitAdvanced", 'M', GTBlocks.crystalCasingBlock, 'G', Ic2Items.glassFiberCable.copy() });

			recipes.addRecipe(new ItemStack(GTBlocks.lapotronWire, 4), new Object[] { "HEH", "SSS", "HEH", 'E',
					GTItems.circuitSapphire, 'S', GTBlocks.superCasingBlock, 'H', GTBlocks.smallLapotron });

			recipes.addRecipe(new ItemStack(GTBlocks.energiumWire, 4), new Object[] { "HEH", "SSS", "HEH", 'E',
					GTItems.circuitRuby, 'S', GTBlocks.superCasingBlock, 'H', GTBlocks.smallEnergium });

			recipes.addRecipe(new ItemStack(GTBlocks.quantumEnergyStorage, 1), new Object[] { "PHP", "HEH", "PHP", 'P',
					"plateIridium", 'H', GTBlocks.basicEnergyStorage, 'E', Blocks.ENDER_CHEST });

			recipes.addRecipe(new ItemStack(GTBlocks.digitalChestLV, 1), new Object[] { "IDI", "CMC", "IDI", 'D',
					GTItems.circuitDiamond, 'I', GTItems.circuitEmerald, 'C', "chestWood", 'M', "casingMachineSteel" });

			recipes.addRecipe(new ItemStack(GTBlocks.digitalChestMV, 1),
					new Object[] { "IDI", "CMC", "IDI", 'D', GTItems.circuitDiamond, 'I', GTItems.circuitEmerald, 'C',
							"chestWood", 'M', "casingMachineAluminium" });
		}
	}

}
