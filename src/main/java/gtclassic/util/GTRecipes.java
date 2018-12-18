package gtclassic.util;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge.OutputItem;
import gtclassic.util.misc.RecipeHelpers.IRecipeModifier;
import gtclassic.util.misc.RecipeHelpers.ModifierType;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.item.recipe.upgrades.EnchantmentModifier;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;

public class GTRecipes {

	public static void init() {
		GTRecipes.initSmeltingRecipes();
		// GTRecipes.initReplaceRecipes(); Figure out how to remove advancement errors
		// when doing this
		GTRecipes.initShapelessRecipes();
		GTRecipes.initShapedRecipes();
		GTRecipes.initMachineRecipes();
	}

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	static IRecipeInput ingotElectric = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("ingotAluminium"), new RecipeInputOreDict("ingotRefinedIron"),
					new RecipeInputOreDict("ingotSilver"), new RecipeInputOreDict("ingotSteel") });

	static IRecipeInput ingotSteel = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("ingotRefinedIron"), new RecipeInputOreDict("ingotSteel") });

	static FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
	static FluidStack lava = new FluidStack(FluidRegistry.LAVA, 1000);

	public static void initSmeltingRecipes() {

		GameRegistry.addSmelting(GTBlocks.rubyBlock, new ItemStack(GTItems.ruby, 9), 0.1F);
		GameRegistry.addSmelting(GTBlocks.sapphireBlock, new ItemStack(GTItems.sapphire, 9), 0.1F);
		GameRegistry.addSmelting(GTBlocks.aluminiumBlock, new ItemStack(GTItems.ingotAluminium, 9), 0.1F);
		GameRegistry.addSmelting(GTItems.dustAluminium, new ItemStack(GTItems.ingotAluminium, 1), 0.3F);
		GameRegistry.addSmelting(GTBlocks.chromeBlock, new ItemStack(GTItems.ingotChrome, 9), 0.1F);
		GameRegistry.addSmelting(GTItems.dustChrome, new ItemStack(GTItems.ingotChrome, 1), 0.3F);
		GameRegistry.addSmelting(GTBlocks.titaniumBlock, new ItemStack(GTItems.ingotTitanium, 9), 0.1F);
		GameRegistry.addSmelting(GTItems.dustTitanium, new ItemStack(GTItems.ingotTitanium, 1), 0.3F);

		GameRegistry.addSmelting(GTBlocks.sandIron, new ItemStack(Items.IRON_NUGGET, 3), 0.1F);
	}

	public static void initReplaceRecipes() {
		ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;
		ArrayList<IRecipe> recipesList = Lists.newArrayList(recipeRegistry.getValuesCollection());

		for (IRecipe r : recipesList) {
			ItemStack output = r.getRecipeOutput();

			if (output.getItem() == Item.getItemFromBlock(Blocks.IRON_BLOCK)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe("ingotIron", 9, new ItemStack(Blocks.IRON_BLOCK), 0.1F);
			}
			if (output.getItem() == Item.getItemFromBlock(Blocks.GOLD_BLOCK)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe("ingotGold", 9, new ItemStack(Blocks.GOLD_BLOCK), 0.1F);
			}
			if (output.getItem() == Item.getItemFromBlock(Blocks.DIAMOND_BLOCK)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe("gemDiamond", 9, new ItemStack(Blocks.DIAMOND_BLOCK), 0.1F);
			}
			if (output.getItem() == Item.getItemFromBlock(Blocks.EMERALD_BLOCK)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe("gemEmerald", 9, new ItemStack(Blocks.EMERALD_BLOCK), 0.1F);
			}
			if (output.getItem() == Item.getItemFromBlock(Blocks.LAPIS_BLOCK)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe("gemLapis", 9, new ItemStack(Blocks.LAPIS_BLOCK), 0.1F);
			}
			if (output.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe("dustRedstone", 9, new ItemStack(Blocks.REDSTONE_BLOCK), 0.1F);
			}
			if (output.getItem() == Item.getItemFromBlock(Blocks.GLOWSTONE)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe("dustGlowstone", 4, new ItemStack(Blocks.GLOWSTONE), 0.1F);
			}
			if (output.getItem() == Item.getItemFromBlock(Blocks.QUARTZ_BLOCK)) {
				recipeRegistry.remove(r.getRegistryName());
				TileEntityCompressor.addRecipe(new ItemStack(Items.QUARTZ, 4), new ItemStack(Blocks.QUARTZ_BLOCK),
						0.1F);
			}
		}
	}

	public static void initShapelessRecipes() {

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.iridiumReinforcedStoneBlock, 1),
				new Object[] { Ic2Items.reinforcedStone, "ingotIridium" });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.smallChest, 1),
				new Object[] { Ic2Items.machine, "chestWood" });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChest, 1),
				new Object[] { GTBlocks.smallChest, GTBlocks.smallChest });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChest, 1),
				new Object[] { "chestWood", "chestWood", Ic2Items.machine, Ic2Items.machine });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.bookShelf, 1),
				new Object[] { Ic2Items.machine, Items.BOOK, Items.BOOK, Items.BOOK });

		recipes.addShapelessRecipe(new ItemStack(GTItems.methane, 4),
				new Object[] { GTItems.carbon, GTItems.hydrogen, GTItems.hydrogen, GTItems.hydrogen });

		recipes.addShapelessRecipe(new ItemStack(Items.GUNPOWDER, 5),
				new Object[] { GTItems.potassium, GTItems.potassium, GTItems.nitrogen, GTItems.nitrogen, GTItems.oxygen,
						GTItems.oxygen, GTItems.oxygen });

		recipes.addShapelessRecipe(new ItemStack(GTItems.glassTube, 1), new Object[] { GTItems.oxygen });

		recipes.addShapelessRecipe(new ItemStack(GTItems.water, 1), new Object[] { water, GTItems.glassTube });

		recipes.addShapelessRecipe(new ItemStack(GTItems.lava, 1), new Object[] { lava, GTItems.glassTube });
	}

	public static void initShapedRecipes() {

		// TOOLS

		recipes.addRecipe(new ItemStack(GTItems.hammerIron, 1),
				new Object[] { "II ", "IIS", "II ", 'I', "ingotIron", 'S', Items.STICK });

		recipes.addRecipe(new ItemStack(GTItems.electroMagnet, 1), new Object[] { "M M", "WMW", "IBI", 'M',
				Ic2Items.magnet, 'B', Ic2Items.battery, 'I', ingotElectric, 'W', Ic2Items.copperCable });

		recipes.addRecipe(new ItemStack(GTItems.rockCutter, 1),
				new Object[] { "DI ", "DI ", "DCB",
						new EnchantmentModifier(new ItemStack(GTItems.rockCutter), Enchantments.SILK_TOUCH)
								.setUsesInput(),
						'D', "gemDiamond", 'I', ingotSteel, 'C', "circuitBasic", 'B', Ic2Items.battery.copy() });

		recipes.addRecipe(new ItemStack(GTItems.advancedDrill, 1), new Object[] { " I ", "ICI", "IBI", 'I',
				"ingotTitanium", 'B', GTItems.lithiumBattery, 'C', GTItems.energyFlowCircuit });

		recipes.addRecipe(new ItemStack(GTItems.advancedChainsaw, 1), new Object[] { " II", "ICI", "BI ", 'I',
				"ingotTitanium", 'B', GTItems.lithiumBattery, 'C', GTItems.energyFlowCircuit });

		recipes.addRecipe(new ItemStack(GTItems.glassTube, 32),
				new Object[] { "G G", "G G", " G ", 'G', "blockGlass" });

		// TODO make this shapeless when Speiger or Meduris fixes it
		recipes.addRecipe(new ItemStack(GTItems.braintechAerospaceARDT),
				new Object[] { "XX ", "XX ", "   ", 'X', StackUtil.copyWithSize(Ic2Items.rubber, 64) });

		// ITEMS

		recipes.addRecipe(new ItemStack(GTItems.heatStorageSingle, 1),
				new Object[] { " I ", "IHI", " I ", 'I', "ingotTin", 'H', GTItems.helium });

		recipes.addRecipe(new ItemStack(GTItems.heatStorageTriple, 1),
				new Object[] { "III", "HHH", "III", 'I', "ingotTin", 'H', GTItems.heatStorageSingle });

		recipes.addRecipe(new ItemStack(GTItems.heatStorageSix, 1), new Object[] { "IHI", "IPI", "IHI", 'I', "ingotTin",
				'H', GTItems.heatStorageTriple, 'P', Ic2Items.denseCopperPlate });

		recipes.addRecipe(new ItemStack(GTItems.lapotronicEnergyOrb, 1),
				new Object[] { "LLL", "LPL", "LLL", 'L', Ic2Items.lapotronCrystal.copy(), 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTItems.dataControlCircuit, 4), new Object[] { "CDC", "DPD", "CDC", 'D',
				GTItems.dataStorageCircuit, 'C', "circuitAdvanced", 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTItems.dataStorageCircuit, 4),
				new Object[] { "EEE", "ECE", "EEE", 'E', "gemEmerald", 'C', "circuitAdvanced" });

		recipes.addRecipe(new ItemStack(GTItems.dataOrb, 4),
				new Object[] { "SSS", "SCS", "SSS", 'S', GTItems.dataStorageCircuit, 'C', GTItems.dataControlCircuit });

		recipes.addRecipe(new ItemStack(GTItems.energyFlowCircuit, 4), new Object[] { "CLC", "LPL", "CLC", 'L',
				Ic2Items.lapotronCrystal.copy(), 'C', "circuitAdvanced", 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTItems.superConductor, 4),
				new Object[] { "CCC", "PWP", "EEE", 'C', Ic2Items.reactorCoolantCellSix.copy(), 'E',
						GTItems.energyFlowCircuit, 'W', "dustTungsten", 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTItems.superConductor, 4), new Object[] { "CCC", "PWP", "EEE", 'C',
				GTItems.heatStorageTriple, 'E', GTItems.energyFlowCircuit, 'W', "dustTungsten", 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTItems.lapotronPack, 1),
				new Object[] { "ELE", "SBS", "EPE", 'E', GTItems.energyFlowCircuit, 'S', GTItems.superConductor, 'L',
						GTItems.lapotronicEnergyOrb, 'B', Ic2Items.lapPack.copy(), 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTItems.destructoPack, 1),
				new Object[] { "BIB", "ICI", "BIB", 'B', lava, 'C', "circuitBasic", 'I', ingotElectric });

		recipes.addRecipe(new ItemStack(GTItems.craftingTablet, 1),
				new Object[] { "BIB", "ICI", "BIB", 'B', "workbench", 'C', "circuitBasic", 'I', ingotElectric });

		recipes.addRecipe(new ItemStack(GTItems.teslaStaff, 1), new Object[] { "LS ", "SP ", "  P", 'L',
				GTItems.lapotronicEnergyOrb, 'S', GTItems.superConductor, 'P', "plateIridium" });

		recipes.addRecipe(new ItemStack(GTItems.lithiumBattery, 1), new Object[] { " G ", "ALA", "ALA", 'G',
				Ic2Items.doubleInsulatedGoldCable.copy(), 'A', "ingotAluminium", 'L', "dustLithium" });

		recipes.addRecipe(new ItemStack(GTItems.lithiumBatpack, 1), new Object[] { "LCL", "LAL", "L L", 'C',
				"circuitAdvanced", 'A', "ingotAluminium", 'L', GTItems.lithiumBattery });

		recipes.addRecipe(new ItemStack(GTItems.creditAlk, 1), new Object[] { "DSR", "EPE", "RSD", 'D', "gemDiamond",
				'S', "gemSapphire", 'R', "gemRuby", 'E', "gemEmerald", 'P', Ic2Items.advancedAlloy });

		// BLOCKS

		recipes.addRecipe(new ItemStack(GTBlocks.highlyadvancedMachineBlock), new Object[] { "CTC", "TBT", "CTC", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', Ic2Items.advMachine.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.highlyadvancedMachineBlock), new Object[] { "TCT", "CBC", "TCT", 'T',
				"ingotTitanium", 'C', "ingotChrome", 'B', Ic2Items.advMachine.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.fusionMachineBlock),
				new Object[] { "YBC", "BRB", "SBY", 'B', GTBlocks.highlyadvancedMachineBlock, 'C',
						GTItems.energyFlowCircuit, 'S', GTItems.superConductor, 'Y', Ic2Items.teslaCoil.copy(), 'B',
						Ic2Items.advMachine.copy(), 'R', Ic2Items.reactorReflectorIridium.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.lesuMachineBlock),
				new Object[] { "BBB", "BCB", "BBB", 'B', "blockLapis", 'C', "circuitBasic" });

		recipes.addRecipe(new ItemStack(GTBlocks.fusionMachineBlock),
				new Object[] { "CBY", "BRB", "YBS", 'B', GTBlocks.highlyadvancedMachineBlock, 'C',
						GTItems.energyFlowCircuit, 'S', GTItems.superConductor, 'Y', Ic2Items.teslaCoil.copy(), 'B',
						Ic2Items.advMachine.copy(), 'R', Ic2Items.reactorReflectorIridium.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.toxicPortalFrame, 2),
				new Object[] { "TIN", "XMY", "PEH", 'T', GTItems.braintechAerospaceARDT, 'I', GTItems.ingotTitanium,
						'N', Ic2Items.scrapMetal, 'X', GTItems.proton, 'M', GTBlocks.iridiumReinforcedStoneBlock, 'Y',
						GTItems.neutron, 'P', Ic2Items.reactorNearDepletedUraniumRod.copy(), 'E', "ingotAluminium", 'H',
						"toolHammer" });

		// TILES

		recipes.addRecipe(new ItemStack(GTBlocks.fusionComputer, 1),
				new Object[] { "ESE", "LCL", "ESE", 'E', GTItems.energyFlowCircuit, 'S', GTBlocks.superCondensator, 'L',
						GTItems.lapotronicEnergyOrb, 'C', GTBlocks.computerCube });

		recipes.addRecipe(new ItemStack(GTBlocks.lightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				GTItems.energyFlowCircuit, 'S', GTBlocks.superCondensator, 'A', GTBlocks.highlyadvancedMachineBlock });

		recipes.addRecipe(new ItemStack(GTBlocks.uuMatterAssembler, 1),
				new Object[] { "dCd", "TQE", "DBD", 'd', GTItems.dataControlCircuit, 'C', GTBlocks.computerCube, 'T',
						Ic2Items.teleporter, 'Q', GTBlocks.quantumChest, 'E', GTBlocks.autoCrafter, 'D',
						GTItems.dataOrb, 'B', "batteryAdvanced" });

		recipes.addRecipe(new ItemStack(GTBlocks.industrialCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', ingotElectric, 'A', Ic2Items.advMachine, 'C', "circuitAdvanced" });

		recipes.addRecipe(new ItemStack(GTBlocks.playerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
				GTItems.dataStorageCircuit, 'C', "circuitAdvanced", 'c', GTBlocks.computerCube });

		recipes.addRecipe(new ItemStack(GTBlocks.matterFabricator, 1),
				new Object[] { "ETE", "HLH", "ETE", 'E', GTItems.energyFlowCircuit, 'T', Ic2Items.teleporter, 'H',
						GTBlocks.highlyadvancedMachineBlock, 'L', GTItems.lapotronicEnergyOrb });

		recipes.addRecipe(new ItemStack(GTBlocks.superCondensator, 1),
				new Object[] { "ELE", "SHS", "ELE", 'E', GTItems.energyFlowCircuit, 'S', GTItems.superConductor, 'H',
						GTBlocks.highlyadvancedMachineBlock, 'L', GTItems.lapotronicEnergyOrb });

		recipes.addRecipe(new ItemStack(GTBlocks.autoCrafter, 1), new Object[] { " B ", "CcC", " A ", 'B',
				Ic2Items.battery, 'C', "circuitAdvanced", 'c', "workbench", 'A', Ic2Items.advMachine });

		recipes.addRecipe(new ItemStack(GTBlocks.chargeOMat, 1),
				new Object[] { "RCR", "AEA", "RMR", 'E', GTItems.lapotronicEnergyOrb, 'R', GTItems.energyFlowCircuit,
						'A', "chestWood", 'C', GTBlocks.computerCube, 'M', Ic2Items.advMachine.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.quantumChest, 1), new Object[] { "DRD", "MCM", "DRD", 'D',
				GTItems.dataOrb, 'R', GTItems.energyFlowCircuit, 'C', "chestWood", 'M', Ic2Items.advMachine.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.sonictronBlock, 1),
				new Object[] { "CRC", "NMN", "CJC", 'C', "circuitBasic", 'N', Blocks.NOTEBLOCK, 'J', Blocks.JUKEBOX,
						'M', Ic2Items.advMachine.copy(), 'R', "record" });

		recipes.addRecipe(new ItemStack(GTBlocks.computerCube, 1), new Object[] { "RGD", "GMG", "DGR", 'D',
				GTItems.dataOrb, 'R', GTItems.energyFlowCircuit, 'G', "blockGlass", 'M', Ic2Items.advMachine.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.superConductorWire, 4), new Object[] { "HEH", "SSS", "HEH", 'E',
				GTItems.energyFlowCircuit, 'S', GTItems.superConductor, 'H', GTBlocks.highlyadvancedMachineBlock, });

		recipes.addRecipe(new ItemStack(GTBlocks.IDSU, 1),
				new Object[] { "PHP", "HEH", "PHP", 'P', "plateIridium", 'H', GTBlocks.HESU, 'E', Blocks.ENDER_CHEST });

		recipes.addRecipe(new ItemStack(GTBlocks.HESU),
				new Object[] { "OOO", "OCO", "OOO", 'O', GTItems.lapotronicEnergyOrb, 'C', GTBlocks.computerCube });

		recipes.addRecipe(new ItemStack(GTBlocks.LESU), new Object[] { " G ", "CMC", " G ", 'C', "circuitAdvanced", 'M',
				GTBlocks.lesuMachineBlock, 'G', Ic2Items.glassFiberCable.copy() });

		// IC2C RECIPES

		recipes.addRecipe(new ItemStack(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood", 'C',
				"cobblestone", 'I', ingotElectric, 'R', "dustRedstone" });

		recipes.addRecipe(Ic2Items.reactorVent.copy(),
				new Object[] { "IBI", "B B", "IBI", 'I', ingotElectric, 'B', Blocks.IRON_BARS });

		recipes.addRecipe(Ic2Items.windMill.copy(),
				new Object[] { "X X", " Y ", "X X", 'Y', Ic2Items.generator.copy(), 'X', "ingotAluminium" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.waterMill.copy(), 3),
				new Object[] { " X ", "XYX", " X ", 'Y', Ic2Items.generator.copy(), 'X', "ingotAluminium" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.mixedMetalIngot, 3),
				new Object[] { "III", "BBB", "TTT", 'I', ingotSteel, 'B', "ingotBronze", 'T', "ingotAluminum" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.mixedMetalIngot, 6),
				new Object[] { "III", "BBB", "TTT", 'I', "ingotTitanium", 'B', "ingotBronze", 'T', ingotElectric });

		recipes.addRecipe(Ic2Items.electricCircuit.copy(), new Object[] { "CCC", "RIR", "CCC", 'I', ingotElectric, 'R',
				"dustRedstone", 'C', Ic2Items.insulatedCopperCable.copy() });

		recipes.addRecipe(Ic2Items.electricCircuit.copy(), new Object[] { "CRC", "CIC", "CRC", 'I', ingotElectric, 'R',
				"dustRedstone", 'C', Ic2Items.insulatedCopperCable.copy() });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.electricCircuit, 2), new Object[] { "CCC", "SIS", "CCC", 'I',
				ingotElectric, 'C', Ic2Items.insulatedCopperCable.copy(), 'S', "plateSilicon" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.advancedCircuit, 2), new Object[] { "IGI", "LCL", "IGI", 'L',
				"dyeBlue", 'G', Items.GLOWSTONE_DUST, 'I', "ingotSilver", 'C', Ic2Items.electricCircuit.copy() });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.advancedCircuit, 2), new Object[] { "ILI", "GCG", "ILI", 'L',
				"dyeBlue", 'G', Items.GLOWSTONE_DUST, 'I', "ingotSilver", 'C', Ic2Items.electricCircuit.copy() });

		recipes.addRecipe(Ic2Items.energyCrystal.copy(),
				new Object[] { "RRR", "RDR", "RRR", 'D', "gemRuby", 'R', "dustRedstone" });

		recipes.addRecipe(Ic2Items.lapotronCrystal.copy(), new Object[] { "LCL", "LDL", "LCL", 'D',
				Ic2Items.energyCrystal.copy(), 'C', "circuitBasic", 'L', "dustLazurite" });

		recipes.addRecipe(Ic2Items.lapotronCrystal.copy(),
				new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic", 'L', "gemLapis" });

		recipes.addRecipe(Ic2Items.lapotronCrystal.copy(),
				new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic", 'L', "dustLazurite" });

		recipes.addRecipe(Ic2Items.miningLaser.copy(),
				new Object[] { "Rcc", "AAC", " AA", 'A', Ic2Items.advancedAlloy.copy(), 'C',
						Ic2Items.advancedCircuit.copy(), 'c', GTItems.helium, 'R', "dustRedstone" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.solarPanel, 2),
				new Object[] { "YYY", "XPX", "CVC", 'C', Ic2Items.electricCircuit.copy(), 'V',
						Ic2Items.generator.copy(), 'X', "plateSilicon", 'Y', "blockGlass", 'P', Ic2Items.carbonPlate });

		recipes.addRecipe(Ic2Items.reactorReflectorThick.copy(),
				new Object[] { " P ", "PBP", " P ", 'P', Ic2Items.reactorReflector, 'B', GTItems.berilium });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.luminator, 16), new Object[] { "III", "GHG", "GGG", 'G',
				"blockGlass", 'I', ingotElectric, 'H', GTItems.helium, 'C', Ic2Items.insulatedCopperCable.copy() });

		recipes.addRecipe(Ic2Items.plasmaCore.copy(),
				new Object[] { "XYX", "YCY", "XYX", 'X', StackUtil.copyWithSize(Ic2Items.magnet, 2), 'Y',
						StackUtil.copyWithSize(Ic2Items.advancedAlloy, 4), 'C', GTItems.plasmaUU });

	}

	public static void initMachineRecipes() {

		// IC2C COMPRESSOR

		TileEntityCompressor.addRecipe("dustSapphire", 1, new ItemStack(GTItems.sapphire), 0.1F);
		TileEntityCompressor.addRecipe("dustRuby", 1, new ItemStack(GTItems.ruby), 0.1F);
		TileEntityCompressor.addRecipe("dustEmerald", 1, new ItemStack(Items.EMERALD), 0.1F);
		TileEntityCompressor.addRecipe(Ic2Items.iridiumOre, 1, new ItemStack(GTItems.ingotIridium), 0.5F);

		TileEntityCompressor.addRecipe("gemRuby", 9, new ItemStack(GTBlocks.rubyBlock), 0.1F);
		TileEntityCompressor.addRecipe("gemSapphire", 9, new ItemStack(GTBlocks.sapphireBlock), 0.1F);
		TileEntityCompressor.addRecipe("ingotAluminium", 9, new ItemStack(GTBlocks.aluminiumBlock), 0.1F);
		TileEntityCompressor.addRecipe("ingotChrome", 9, new ItemStack(GTBlocks.chromeBlock), 0.1F);
		TileEntityCompressor.addRecipe("ingotTitanium", 9, new ItemStack(GTBlocks.titaniumBlock), 0.1F);

		TileEntityCompressor.addRecipe(new ItemStack(GTItems.silicon, 9),
				StackUtil.copyWithSize(new ItemStack(GTItems.plateSilicon), 1), 0.2F);

		TileEntityCompressor.addRecipe(new ItemStack(GTItems.carbon, 8),
				StackUtil.copyWithSize(Ic2Items.carbonFiber, 1), 0.2F);

		TileEntityCompressor.addRecipe(new ItemStack(GTItems.dustUranium, 1),
				StackUtil.copyWithSize(Ic2Items.uraniumIngot, 1), 0.2F);

		// IC2C MACERATOR

		TileEntityMacerator.addRecipe(new ItemStack(Items.FLINT, 1),
				StackUtil.copyWithSize(new ItemStack(GTItems.dustFlint), 1), 0.1F);

		TileEntityMacerator.addRecipe(Ic2Items.uraniumDrop, 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustUranium), 1), 0.1F);

		TileEntityMacerator.addRecipe("enderpearl", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustEnderpearl), 1),
				0.3F);
		TileEntityMacerator.addRecipe(new ItemStack(Items.ENDER_EYE, 1),
				StackUtil.copyWithSize(new ItemStack(GTItems.dustEnderEye), 2), 0.5F);

		TileEntityMacerator.addRecipe("oreBauxite", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustBauxite), 4),
				0.1F);
		TileEntityMacerator.addRecipe("oreIridium", 1, StackUtil.copyWithSize(Ic2Items.iridiumOre, 2), 1.0F);

		TileEntityMacerator.addRecipe("gemRuby", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustRuby), 1), 0.1F);
		TileEntityMacerator.addRecipe("gemEmerald", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustEmerald), 1),
				0.1F);
		TileEntityMacerator.addRecipe("gemSapphire", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustSapphire), 1),
				0.1F);
		TileEntityMacerator.addRecipe("ingotAluminium", 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustAluminium), 1), 0.1F);
		TileEntityMacerator.addRecipe("ingotChrome", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustChrome), 1),
				0.1F);
		TileEntityMacerator.addRecipe("ingotTitanium", 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustTitanium), 1), 0.1F);

		TileEntityMacerator.addRecipe("blockRuby", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustRuby), 9), 0.1F);
		TileEntityMacerator.addRecipe("blockEmerald", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustEmerald), 9),
				0.1F);
		TileEntityMacerator.addRecipe("blockSapphire", 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustSapphire), 9), 0.1F);
		TileEntityMacerator.addRecipe("blockAluminium", 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustAluminium), 9), 0.1F);
		TileEntityMacerator.addRecipe("blockChrome", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustChrome), 9),
				0.1F);
		TileEntityMacerator.addRecipe("blockTitanium", 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustTitanium), 9), 0.1F);

		// IC2C EXTRACTOR

		TileEntityExtractor.addRecipe("oreRuby", 1, StackUtil.copyWithSize(new ItemStack(GTItems.ruby), 3), 0.3F);
		TileEntityExtractor.addRecipe("oreSapphire", 1, StackUtil.copyWithSize(new ItemStack(GTItems.sapphire), 3),
				0.3F);

		// INDUSTRIAL CENTRIFUGE RECIPES IN ORDER OF ORIGINAL GT1

		IRecipeModifier[] hardgate = new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create(15900) };
		IRecipeModifier[] medgate = new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create(7900) };
		IRecipeModifier[] easygate = new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create(3900) };

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.water, 6), 0,
				new OutputItem(new ItemStack(GTItems.hydrogen, 4), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 2), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.waterCell, 6), 6,
				new OutputItem(StackUtil.copyWithSize(Ic2Items.emptyCell, 6), 0),
				new OutputItem(new ItemStack(GTItems.hydrogen, 4), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 2), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustCoal", 4, 8,
				new OutputItem(new ItemStack(GTItems.carbon, 8), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.rubberWood, 16), 12,
				new OutputItem(new ItemStack(GTItems.carbon, 8), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.stickyResin, 8), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 6), 2),
				new OutputItem(new ItemStack(GTItems.methane, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.hydrogen, 4), 0, medgate,
				new OutputItem(new ItemStack(GTItems.glassTube, 3), 0),
				new OutputItem(new ItemStack(GTItems.dueterium, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.dueterium, 4), 0, medgate,
				new OutputItem(new ItemStack(GTItems.glassTube, 3), 0),
				new OutputItem(new ItemStack(GTItems.tritium, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.helium, 16), 0, medgate,
				new OutputItem(new ItemStack(GTItems.glassTube, 15), 0),
				new OutputItem(new ItemStack(GTItems.helium3, 1), 1));

		// GTTileEntityIndustrialCentrifuge.addRecipe("dustUranium", 16, 22,
		// new OutputItem(new ItemStack(GTItems.tungsten, 1), 0),
		// new OutputItem(StackUtil.copyWithSize(Ic2Items.reactorUraniumRodSingle, 16),
		// 1));
		// TODO - missing 1 plutonium rods
		// TODO - missing 4 throiums rods

		GTTileEntityIndustrialCentrifuge.addRecipe("dustRuby", 9, 3, easygate,
				new OutputItem(new ItemStack(GTItems.dustAluminium, 2), 1),
				new OutputItem(new ItemStack(GTItems.dustChrome, 1), 2),
				new OutputItem(new ItemStack(GTItems.oxygen, 3), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustSapphire", 8, 3, easygate,
				new OutputItem(new ItemStack(GTItems.dustAluminium, 2), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 3), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustGreenSapphire", 4, 0,
				new OutputItem(new ItemStack(GTItems.dustSapphire, 4), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustEmerald", 29, 18, easygate,
				new OutputItem(new ItemStack(GTItems.oxygen, 9), 0),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 2), 1),
				new OutputItem(new ItemStack(GTItems.berilium, 3), 2),
				new OutputItem(new ItemStack(GTItems.silicon, 6), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustEnderPearl", 16, 16,
				new OutputItem(new ItemStack(GTItems.chlorine, 6), 0),
				new OutputItem(new ItemStack(GTItems.nitrogen, 5), 1),
				new OutputItem(new ItemStack(GTItems.berilium, 1), 2),
				new OutputItem(new ItemStack(GTItems.potassium, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustEnderEye", 16, 0,
				new OutputItem(new ItemStack(GTItems.dustEnderpearl, 8), 1),
				new OutputItem(new ItemStack(Items.BLAZE_POWDER, 8), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustLazurite", 59, 22, medgate,
				new OutputItem(new ItemStack(GTItems.sodium, 8), 0),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 6), 1),
				new OutputItem(new ItemStack(GTItems.silicon, 6), 2),
				new OutputItem(new ItemStack(GTItems.calcium, 8), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustPyrite", 3, 0,
				new OutputItem(StackUtil.copyWithSize(Ic2Items.ironDust, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustCalcite", 10, 7,
				new OutputItem(new ItemStack(GTItems.calcium, 2), 1),
				new OutputItem(new ItemStack(GTItems.carbon, 2), 2),
				new OutputItem(new ItemStack(GTItems.oxygen, 3), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustSodalite", 23, 8, easygate,
				new OutputItem(new ItemStack(GTItems.chlorine, 1), 0),
				new OutputItem(new ItemStack(GTItems.sodium, 4), 1),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 3), 2),
				new OutputItem(new ItemStack(GTItems.silicon, 3), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustBauxite", 24, 16, hardgate,
				new OutputItem(new ItemStack(GTItems.oxygen, 6), 0),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 16), 1),
				new OutputItem(new ItemStack(GTItems.dustTitanium, 1), 2),
				new OutputItem(new ItemStack(GTItems.hydrogen, 10), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.MAGMA_CREAM, 1), 0,
				new OutputItem(new ItemStack(Items.BLAZE_POWDER, 1), 1),
				new OutputItem(new ItemStack(Items.SLIME_BALL, 1), 2));

		// TODO MISSING RECIPE REQUIRE THORIUM CELL
		// input 2 near depleted uranium rod/cells
		// output 0 -empty cell or something
		// output 1 -one thorium rod/cell

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.DIRT, 64), 0,
				new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.compressedPlantBall, 2), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 2), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 2), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.GRASS, 64), 0,
				new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.compressedPlantBall, 2), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 4), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 2), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.MYCELIUM, 64), 0,
				new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
				new OutputItem(new ItemStack(Blocks.BROWN_MUSHROOM, 16), 1),
				new OutputItem(new ItemStack(Blocks.RED_MUSHROOM, 16), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 8), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK, 64), 6,
				new OutputItem(new ItemStack(GTItems.methane, 6), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK, 64), 6,
				new OutputItem(new ItemStack(GTItems.methane, 6), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM, 64), 4,
				new OutputItem(new ItemStack(GTItems.methane, 4), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM, 64), 4,
				new OutputItem(new ItemStack(GTItems.methane, 4), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.NETHER_WART, 64), 2,
				new OutputItem(new ItemStack(GTItems.methane, 2), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.terraWart, 64), 4,
				new OutputItem(new ItemStack(GTItems.methane, 4), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe("gemLapis", 64, 0, medgate,
				new OutputItem(new ItemStack(GTItems.dustSodalite, 8), 0),
				new OutputItem(new ItemStack(GTItems.dustLazurite, 48), 1),
				new OutputItem(new ItemStack(GTItems.dustPyrite, 4), 2),
				new OutputItem(new ItemStack(GTItems.dustCalcite, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.BLAZE_POWDER, 8), 0,
				new OutputItem(StackUtil.copyWithSize(Ic2Items.coalDust, 2), 1),
				new OutputItem(new ItemStack(Items.GUNPOWDER, 1), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.SAND, 32), 2, medgate,
				new OutputItem(new ItemStack(GTItems.silicon, 1), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 1), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustFlint", 8, 2, easygate,
				new OutputItem(new ItemStack(GTItems.silicon, 1), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 1), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.clayDust, 4), 2, medgate,
				new OutputItem(new ItemStack(GTItems.lithium, 1), 1),
				new OutputItem(new ItemStack(GTItems.silicon, 1), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.stickyResin, 8), 0,
				new OutputItem(StackUtil.copyWithSize(Ic2Items.rubber, 28), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.compressedPlantBall, 2), 2),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 2), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustGlowstone", 16, 1, easygate,
				new OutputItem(new ItemStack(Items.REDSTONE, 8), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.goldDust, 8), 2),
				new OutputItem(new ItemStack(GTItems.helium, 1), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.netherrackDust, 64), 0, easygate,
				new OutputItem(new ItemStack(Items.GOLD_NUGGET, 4), 0),
				new OutputItem(new ItemStack(Items.REDSTONE, 4), 1),
				new OutputItem(new ItemStack(Items.GUNPOWDER, 8), 2),
				new OutputItem(new ItemStack(GTItems.dustPyrite, 4), 3)); // changed this from more coal dust to pyrite

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.lava, 64), 0, easygate,
				new OutputItem(new ItemStack(GTItems.glassTube, 60), 0),
				new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 1),
				new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 2),
				new OutputItem(new ItemStack(GTItems.tungsten, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.LAVA, 64), 4, easygate,
				new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 1),
				new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 2),
				new OutputItem(new ItemStack(GTItems.tungsten, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.lavaCell, 64), 4, easygate,
				new OutputItem(StackUtil.copyWithSize(Ic2Items.emptyCell, 64), 0),
				new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 1),
				new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 2),
				new OutputItem(new ItemStack(GTItems.tungsten, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("endstone", 64, 9, easygate,
				new OutputItem(new ItemStack(Blocks.SAND, 48), 0), // out0
				new OutputItem(new ItemStack(GTItems.helium3, 4), 1), // out1
				new OutputItem(new ItemStack(GTItems.helium, 4), 2), // out2
				new OutputItem(new ItemStack(GTItems.tungsten, 1), 3));// out3

		// INDUSTRIAL CENTRIFUGE RECIPES NEW/OUT OF ORDER

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTBlocks.sandIron, 3), 0,
				new OutputItem(StackUtil.copyWithSize(Ic2Items.ironDust, 2), 1),
				new OutputItem(new ItemStack(Items.GOLD_NUGGET, 1), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.QUARTZ, 8), 12,
				new OutputItem(new ItemStack(GTItems.silicon, 8), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 4), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.obsidianDust, 64), 10,
				new OutputItem(StackUtil.copyWithSize(Ic2Items.ironDust, 2), 0),
				new OutputItem(new ItemStack(GTItems.silicon, 6), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 4), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.uuMatter, 1), 8, hardgate,
				new OutputItem(new ItemStack(GTItems.proton, 4), 0),
				new OutputItem(new ItemStack(GTItems.neutron, 4), 2));

		// PLACEHOLDER FUSION - second input is set to dueterium in the tile for now

		GTTileEntityFusionComputer.addRecipe("dustTungsten", 1, 1, Ic2Items.iridiumOre);
		GTTileEntityFusionComputer.addRecipe(new ItemStack(GTItems.tritium), 1, (new ItemStack(GTItems.plasmaHelium)));
		GTTileEntityFusionComputer.addRecipe(new ItemStack(GTItems.helium3), 1, (new ItemStack(GTItems.plasmaHelium)));
		GTTileEntityFusionComputer.addRecipe(StackUtil.copyWithSize(Ic2Items.uuMatter, 10), 0,
				(new ItemStack(GTItems.plasmaUU)));

	}

}
