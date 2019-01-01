package gtclassic;

import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge.OutputItem;
import gtclassic.util.GTValues;
import gtclassic.util.recipe.GTRecipeHelpers.IRecipeModifier;
import gtclassic.util.recipe.GTRecipeHelpers.ModifierType;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.item.recipe.upgrades.EnchantmentModifier;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipes {

	public static void init() {
		GTRecipes.initSmeltingRecipes();
		GTRecipes.initShapelessRecipes();
		GTRecipes.initShapedRecipes();
		GTRecipes.initMachineRecipes();
	}

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	private static String getRefinedIron() { // TODO check if this loads to early because its static
		return IC2.config.getFlag("SteelRecipes") ? "ingotSteel" : "ingotRefinedIron";
	}

	static IRecipeInput ingotElectric = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("ingotAluminium"), new RecipeInputOreDict(getRefinedIron()),
					new RecipeInputOreDict("ingotSilver") });

	static FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
	static FluidStack lava = new FluidStack(FluidRegistry.LAVA, 1000);

	public static void initSmeltingRecipes() {

		GameRegistry.addSmelting(GTItems.dustAluminium, new ItemStack(GTItems.ingotAluminium, 1), 0.3F);
		GameRegistry.addSmelting(GTItems.dustChrome, new ItemStack(GTItems.ingotChrome, 1), 0.3F);
		GameRegistry.addSmelting(GTBlocks.titaniumBlock, new ItemStack(GTItems.ingotTitanium, 9), 0.1F);
		GameRegistry.addSmelting(GTItems.dustTitanium, new ItemStack(GTItems.ingotTitanium, 1), 0.3F);

		GameRegistry.addSmelting(GTBlocks.sandIron, new ItemStack(Items.IRON_NUGGET, 3), 0.1F);
	}

	public static void initShapelessRecipes() {

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.smallChest, 1),
				new Object[] { Ic2Items.machine, "chestWood" });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChest, 1),
				new Object[] { GTBlocks.smallChest, GTBlocks.smallChest });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChest, 1),
				new Object[] { "chestWood", "chestWood", Ic2Items.machine, Ic2Items.machine });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.bookShelf, 1),
				new Object[] { Ic2Items.machine, Items.BOOK, Items.BOOK, Items.BOOK });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.workBench, 1),
				new Object[] { Ic2Items.machine, "workbench" });

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

		// MATERIALS

		ingotUtil(GTBlocks.iridiumBlock, GTItems.ingotIridium, "Iridium");
		gemUtil(GTBlocks.rubyBlock, GTItems.ruby, "Ruby");
		gemUtil(GTBlocks.sapphireBlock, GTItems.sapphire, "Sapphire");
		ingotUtil(GTBlocks.aluminiumBlock, GTItems.ingotAluminium, "Aluminium");
		ingotUtil(GTBlocks.titaniumBlock, GTItems.ingotTitanium, "Titanium");
		ingotUtil(GTBlocks.chromeBlock, GTItems.ingotChrome, "Chrome");

		// TOOLS

		recipes.addRecipe(new ItemStack(GTItems.hammerIron, 1),
				new Object[] { "II ", "IIS", "II ", 'I', "ingotIron", 'S', Items.STICK });

		recipes.addRecipe(new ItemStack(GTItems.electroMagnet, 1), new Object[] { "M M", "WMW", "IBI", 'M',
				Ic2Items.magnet, 'B', Ic2Items.battery, 'I', ingotElectric, 'W', Ic2Items.copperCable });

		recipes.addRecipe(new ItemStack(GTItems.rockCutter, 1),
				new Object[] { "DI ", "DI ", "DCB",
						new EnchantmentModifier(new ItemStack(GTItems.rockCutter), Enchantments.SILK_TOUCH)
								.setUsesInput(),
						'D', "gemDiamond", 'I', getRefinedIron(), 'C', "circuitBasic", 'B', Ic2Items.battery.copy() });

		recipes.addRecipe(new ItemStack(GTItems.advancedDrill, 1), new Object[] { "DDD", "ICI", "IBI", 'I',
				"ingotTitanium", 'B', GTItems.lithiumBattery, 'C', GTItems.dataControlCircuit, 'D', "gemDiamond" });

		recipes.addRecipe(new ItemStack(GTItems.advancedChainsaw, 1), new Object[] { " II", "ICI", "BI ", 'I',
				"ingotTitanium", 'B', GTItems.lithiumBattery, 'C', GTItems.dataControlCircuit });

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

		// TILES

		recipes.addRecipe(new ItemStack(GTBlocks.fusionComputer, 1),
				new Object[] { "ESE", "LCL", "ESE", 'E', GTItems.energyFlowCircuit, 'S', GTBlocks.superCondensator, 'L',
						GTItems.lapotronicEnergyOrb, 'C', GTBlocks.computerCube });

		recipes.addRecipe(new ItemStack(GTBlocks.lightningRod, 1), new Object[] { "EAE", "ASA", "EAE", 'E',
				GTItems.energyFlowCircuit, 'S', GTBlocks.superCondensator, 'A', GTBlocks.highlyadvancedMachineBlock });

		recipes.addRecipe(new ItemStack(GTBlocks.industrialCentrifuge, 1), new Object[] { "RCR", "AEA", "RCR", 'E',
				Ic2Items.extractor, 'R', ingotElectric, 'A', Ic2Items.advMachine, 'C', "circuitAdvanced" });

		if (GTValues.debugMode) {

			recipes.addRecipe(new ItemStack(GTBlocks.uuMatterAssembler, 1),
					new Object[] { "dCd", "TQE", "DBD", 'd', GTItems.dataControlCircuit, 'C', GTBlocks.computerCube,
							'T', Ic2Items.teleporter, 'Q', GTBlocks.quantumChest, 'E', GTBlocks.autoCrafter, 'D',
							GTItems.dataOrb, 'B', "batteryAdvanced" });

			recipes.addRecipe(new ItemStack(GTBlocks.playerDetector, 1), new Object[] { " D ", "CcC", " D ", 'D',
					GTItems.dataStorageCircuit, 'C', "circuitAdvanced", 'c', GTBlocks.computerCube });

			recipes.addRecipe(new ItemStack(GTBlocks.matterFabricator, 1),
					new Object[] { "ETE", "HLH", "ETE", 'E', GTItems.energyFlowCircuit, 'T', Ic2Items.teleporter, 'H',
							GTBlocks.highlyadvancedMachineBlock, 'L', GTItems.lapotronicEnergyOrb });

			recipes.addRecipe(new ItemStack(GTBlocks.superCondensator, 1),
					new Object[] { "ELE", "SHS", "ELE", 'E', GTItems.energyFlowCircuit, 'S', GTItems.superConductor,
							'H', GTBlocks.highlyadvancedMachineBlock, 'L', GTItems.lapotronicEnergyOrb });

			recipes.addRecipe(new ItemStack(GTBlocks.autoCrafter, 1), new Object[] { " B ", "CcC", " A ", 'B',
					Ic2Items.battery, 'C', "circuitAdvanced", 'c', "workbench", 'A', Ic2Items.advMachine });

			recipes.addRecipe(new ItemStack(GTBlocks.chargeOMat, 1),
					new Object[] { "RCR", "AEA", "RMR", 'E', GTItems.lapotronicEnergyOrb, 'R',
							GTItems.energyFlowCircuit, 'A', "chestWood", 'C', GTBlocks.computerCube, 'M',
							Ic2Items.advMachine.copy() });

			recipes.addRecipe(new ItemStack(GTBlocks.quantumChest, 1), new Object[] { "IDI", "MCM", "IDI", 'D',
					GTItems.dataOrb, 'I', "ingotChrome", 'C', "chestWood", 'M', Ic2Items.advMachine.copy() });

			recipes.addRecipe(new ItemStack(GTBlocks.quantumChest, 1), new Object[] { "IDI", "MCM", "IDI", 'D',
					GTItems.dataOrb, 'I', "ingotTitanium", 'C', "chestWood", 'M', Ic2Items.advMachine.copy() });

			recipes.addRecipe(new ItemStack(GTBlocks.LESU), new Object[] { " G ", "CMC", " G ", 'C', "circuitAdvanced",
					'M', GTBlocks.lesuMachineBlock, 'G', Ic2Items.glassFiberCable.copy() });

			recipes.addRecipe(new ItemStack(GTBlocks.superConductorWire, 4),
					new Object[] { "HEH", "SSS", "HEH", 'E', GTItems.energyFlowCircuit, 'S', GTItems.superConductor,
							'H', GTBlocks.highlyadvancedMachineBlock, });

			recipes.addRecipe(new ItemStack(GTBlocks.IDSU, 1), new Object[] { "PHP", "HEH", "PHP", 'P', "plateIridium",
					'H', GTBlocks.HESU, 'E', Blocks.ENDER_CHEST });

			recipes.addRecipe(new ItemStack(GTBlocks.sonictronBlock, 1),
					new Object[] { "CRC", "NMN", "CJC", 'C', "circuitBasic", 'N', Blocks.NOTEBLOCK, 'J', Blocks.JUKEBOX,
							'M', Ic2Items.advMachine.copy(), 'R', "record" });
		}

		recipes.addRecipe(new ItemStack(GTBlocks.computerCube, 1), new Object[] { "RGD", "GMG", "DGR", 'D',
				GTItems.dataOrb, 'R', GTItems.energyFlowCircuit, 'G', "blockGlass", 'M', Ic2Items.advMachine.copy() });

		recipes.addRecipe(new ItemStack(GTBlocks.HESU),
				new Object[] { "OOO", "OCO", "OOO", 'O', GTItems.lapotronicEnergyOrb, 'C', GTBlocks.computerCube });

		// IC2C RECIPES (ADDITIONS)

		recipes.addRecipe(new ItemStack(Blocks.PISTON), new Object[] { "WWW", "CIC", "CRC", 'W', "plankWood", 'C',
				"cobblestone", 'I', ingotElectric, 'R', "dustRedstone" });

		recipes.addRecipe(Ic2Items.machine.copy(), new Object[] { "XXX", "X X", "XXX", 'X', "ingotAluminium" });

		recipes.addRecipe(Ic2Items.reactorVent.copy(),
				new Object[] { "IBI", "B B", "IBI", 'I', ingotElectric, 'B', Blocks.IRON_BARS });

		recipes.addRecipe(Ic2Items.windMill.copy(),
				new Object[] { "X X", " Y ", "X X", 'Y', Ic2Items.generator.copy(), 'X', "ingotAluminium" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.waterMill.copy(), 3),
				new Object[] { " X ", "XYX", " X ", 'Y', Ic2Items.generator.copy(), 'X', "ingotAluminium" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.mixedMetalIngot, 3),
				new Object[] { "III", "BBB", "TTT", 'I', getRefinedIron(), 'B', "ingotBronze", 'T', "ingotAluminum" });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.mixedMetalIngot, 6),
				new Object[] { "III", "BBB", "TTT", 'I', "ingotTitanium", 'B', "ingotBronze", 'T', ingotElectric });

		recipes.addRecipe(Ic2Items.electricCircuit.copy(), new Object[] { "CCC", "RIR", "CCC", 'I', ingotElectric, 'R',
				"dustRedstone", 'C', Ic2Items.insulatedCopperCable.copy() });

		recipes.addRecipe(Ic2Items.electricCircuit.copy(), new Object[] { "CRC", "CIC", "CRC", 'I', ingotElectric, 'R',
				"dustRedstone", 'C', Ic2Items.insulatedCopperCable.copy() });

		recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.electricCircuit, 2), new Object[] { "CCC", "RSR", "CCC", 'R',
				"dustRedstone", 'C', Ic2Items.insulatedCopperCable.copy(), 'S', "plateSilicon" });

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

		// IC2C RECIPES (REPLACEMENTS)

		recipes.overrideRecipe("shaped_Iridium Plate", StackUtil.copyWithSize(Ic2Items.iridiumPlate, 1), "IAI", "ADA",
				"IAI", 'I', "ingotIridium", 'A', Ic2Items.advancedAlloy.copy(), 'D', "gemDiamond");

		recipes.overrideRecipe("shaped_Plasma Core", StackUtil.copyWithSize(Ic2Items.plasmaCore, 1), "XYX", "YCY",
				"XYX", 'X', StackUtil.copyWithSize(Ic2Items.magnet, 2), 'Y',
				StackUtil.copyWithSize(Ic2Items.advancedAlloy, 4), 'C', "itemPlasma");

	}

	public static void initMachineRecipes() {

		// IC2C COMPRESSOR

		TileEntityCompressor.addRecipe("dustSapphire", 1, new ItemStack(GTItems.sapphire), 0.1F);
		TileEntityCompressor.addRecipe("dustRuby", 1, new ItemStack(GTItems.ruby), 0.1F);
		TileEntityCompressor.addRecipe("dustEmerald", 1, new ItemStack(Items.EMERALD), 0.1F);
		TileEntityCompressor.addRecipe(Ic2Items.iridiumOre, 1, new ItemStack(GTItems.ingotIridium), 0.5F);

		TileEntityCompressor.addRecipe(new ItemStack(GTItems.silicon, 1),
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

		// TODO bug speiger, the below recipes should be autogenerating

		TileEntityMacerator.addRecipe("ingotAluminium", 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustAluminium), 1), 0.1F);
		TileEntityMacerator.addRecipe("ingotChrome", 1, StackUtil.copyWithSize(new ItemStack(GTItems.dustChrome), 1),
				0.1F);
		TileEntityMacerator.addRecipe("ingotTitanium", 1,
				StackUtil.copyWithSize(new ItemStack(GTItems.dustTitanium), 1), 0.1F);

		// IC2C EXTRACTOR

		TileEntityExtractor.addRecipe("oreRuby", 1, StackUtil.copyWithSize(new ItemStack(GTItems.ruby), 3), 0.3F);
		TileEntityExtractor.addRecipe("oreSapphire", 1, StackUtil.copyWithSize(new ItemStack(GTItems.sapphire), 3),
				0.3F);

		// INDUSTRIAL CENTRIFUGE RECIPES IN ORDER OF ORIGINAL GT1

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.water, 6), 0, euCost(3000),
				new OutputItem(new ItemStack(GTItems.hydrogen, 4), 0),
				new OutputItem(new ItemStack(GTItems.oxygen, 2), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.waterCell, 6), 6, euCost(3000),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.emptyCell, 6), 0),
				new OutputItem(new ItemStack(GTItems.hydrogen, 4), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 2), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustCoal", 4, 8, euCost(7500),
				new OutputItem(new ItemStack(GTItems.carbon, 8), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.rubberWood, 16), 12, euCost(25000),
				new OutputItem(new ItemStack(GTItems.carbon, 8), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.stickyResin, 8), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 6), 2),
				new OutputItem(new ItemStack(GTItems.methane, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.hydrogen, 4), 0, euCost(6000),
				new OutputItem(new ItemStack(GTItems.glassTube, 3), 0),
				new OutputItem(new ItemStack(GTItems.dueterium, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.dueterium, 4), 0, euCost(6000),
				new OutputItem(new ItemStack(GTItems.glassTube, 3), 0),
				new OutputItem(new ItemStack(GTItems.tritium, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.helium, 16), 0, euCost(18000),
				new OutputItem(new ItemStack(GTItems.glassTube, 15), 0),
				new OutputItem(new ItemStack(GTItems.helium3, 1), 1));

		// GTTileEntityIndustrialCentrifuge.addRecipe("dustUranium", 16, 22,
		// euCost(250000),
		// new OutputItem(new ItemStack(GTItems.tungsten, 1), 0),
		// new OutputItem(StackUtil.copyWithSize(Ic2Items.reactorUraniumRodSingle, 16),
		// 1));
		// TODO - missing 1 plutonium rods
		// TODO - missing 4 throiums rods

		GTTileEntityIndustrialCentrifuge.addRecipe("dustRuby", 9, 3, euCost(25000),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 2), 0),
				new OutputItem(new ItemStack(GTItems.dustChrome, 1), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 3), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustSapphire", 8, 3, euCost(20000),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 2), 0),
				new OutputItem(new ItemStack(GTItems.oxygen, 3), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustGreenSapphire", 4, 0, euCost(15000),
				new OutputItem(new ItemStack(GTItems.dustSapphire, 4), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustEmerald", 29, 18, euCost(25000),
				new OutputItem(new ItemStack(GTItems.oxygen, 9), 0),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 2), 1),
				new OutputItem(new ItemStack(GTItems.berilium, 3), 2),
				new OutputItem(new ItemStack(GTItems.silicon, 6), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustEnderPearl", 16, 16, euCost(65000),
				new OutputItem(new ItemStack(GTItems.chlorine, 6), 0),
				new OutputItem(new ItemStack(GTItems.nitrogen, 5), 1),
				new OutputItem(new ItemStack(GTItems.berilium, 1), 2),
				new OutputItem(new ItemStack(GTItems.potassium, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustEnderEye", 16, 0, euCost(35000),
				new OutputItem(new ItemStack(GTItems.dustEnderpearl, 8), 0),
				new OutputItem(new ItemStack(Items.BLAZE_POWDER, 8), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustLazurite", 59, 22, euCost(295000),
				new OutputItem(new ItemStack(GTItems.sodium, 8), 0),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 6), 1),
				new OutputItem(new ItemStack(GTItems.silicon, 6), 2),
				new OutputItem(new ItemStack(GTItems.calcium, 8), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustPyrite", 3, 0, euCost(15000),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.ironDust, 1), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustCalcite", 10, 7, euCost(50000),
				new OutputItem(new ItemStack(GTItems.calcium, 2), 0),
				new OutputItem(new ItemStack(GTItems.carbon, 2), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 3), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustSodalite", 23, 8, euCost(115000),
				new OutputItem(new ItemStack(GTItems.chlorine, 1), 0),
				new OutputItem(new ItemStack(GTItems.sodium, 4), 1),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 3), 2),
				new OutputItem(new ItemStack(GTItems.silicon, 3), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustBauxite", 24, 16, euCost(250000),
				new OutputItem(new ItemStack(GTItems.oxygen, 6), 0),
				new OutputItem(new ItemStack(GTItems.dustAluminium, 16), 1),
				new OutputItem(new ItemStack(GTItems.dustTitanium, 1), 2),
				new OutputItem(new ItemStack(GTItems.hydrogen, 10), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.MAGMA_CREAM, 1), 0, euCost(2500),
				new OutputItem(new ItemStack(Items.BLAZE_POWDER, 1), 0),
				new OutputItem(new ItemStack(Items.SLIME_BALL, 1), 1));

		// TODO MISSING RECIPE REQUIRE THORIUM CELL cost 2500
		// input 2 near depleted uranium rod/cells
		// output 0 -empty cell or something
		// output 1 -one thorium rod/cell

		GTTileEntityIndustrialCentrifuge.addRecipe("dirt", 64, 0, euCost(50000),
				new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.compressedPlantBall, 2), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 2), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 2), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("grass", 64, 0, euCost(50000),
				new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.compressedPlantBall, 2), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 4), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 2), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.MYCELIUM, 64), 0, euCost(62500),
				new OutputItem(new ItemStack(Blocks.SAND, 32), 0),
				new OutputItem(new ItemStack(Blocks.BROWN_MUSHROOM, 16), 1),
				new OutputItem(new ItemStack(Blocks.RED_MUSHROOM, 16), 2),
				new OutputItem(new ItemStack(Items.CLAY_BALL, 8), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK, 64), 6, euCost(150000),
				new OutputItem(new ItemStack(GTItems.methane, 6), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK, 64), 6, euCost(150000),
				new OutputItem(new ItemStack(GTItems.methane, 6), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.BROWN_MUSHROOM, 64), 4, euCost(50000),
				new OutputItem(new ItemStack(GTItems.methane, 4), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.RED_MUSHROOM, 64), 4, euCost(50000),
				new OutputItem(new ItemStack(GTItems.methane, 4), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.NETHER_WART, 64), 2, euCost(50000),
				new OutputItem(new ItemStack(GTItems.methane, 2), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.terraWart, 64), 4, euCost(50000),
				new OutputItem(new ItemStack(GTItems.methane, 4), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe("gemLapis", 64, 0, euCost(125000),
				new OutputItem(new ItemStack(GTItems.dustSodalite, 8), 0),
				new OutputItem(new ItemStack(GTItems.dustLazurite, 48), 1),
				new OutputItem(new ItemStack(GTItems.dustPyrite, 4), 2),
				new OutputItem(new ItemStack(GTItems.dustCalcite, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.BLAZE_POWDER, 8), 0, euCost(15000),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.coalDust, 2), 0),
				new OutputItem(new ItemStack(Items.GUNPOWDER, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe("sand", 32, 2, euCost(50000),
				new OutputItem(new ItemStack(GTItems.silicon, 1), 0),
				new OutputItem(new ItemStack(GTItems.oxygen, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustFlint", 8, 2, euCost(5000),
				new OutputItem(new ItemStack(GTItems.silicon, 1), 0),
				new OutputItem(new ItemStack(GTItems.oxygen, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.clayDust, 4), 2, euCost(5000),
				new OutputItem(new ItemStack(GTItems.lithium, 1), 0),
				new OutputItem(new ItemStack(GTItems.silicon, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.stickyResin, 8), 0, euCost(12500),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.rubber, 28), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.compressedPlantBall, 2), 1),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.plantBall, 2), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe("dustGlowstone", 16, 1, euCost(25000),
				new OutputItem(new ItemStack(Items.REDSTONE, 8), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.goldDust, 8), 1),
				new OutputItem(new ItemStack(GTItems.helium, 1), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.netherrackDust, 64), 0,
				euCost(50000), new OutputItem(new ItemStack(Items.GOLD_NUGGET, 4), 0),
				new OutputItem(new ItemStack(Items.REDSTONE, 4), 1),
				new OutputItem(new ItemStack(Items.GUNPOWDER, 8), 2),
				new OutputItem(new ItemStack(GTItems.dustPyrite, 4), 3)); // changed this from more coal dust to pyrite

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTItems.lava, 64), 0, euCost(250000),
				new OutputItem(new ItemStack(GTItems.glassTube, 60), 0),
				new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 1),
				new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 2),
				new OutputItem(new ItemStack(GTItems.tungsten, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.LAVA, 64), 4, euCost(250000),
				new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 0),
				new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 1),
				new OutputItem(new ItemStack(GTItems.tungsten, 4), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.lavaCell, 64), 4, euCost(250000),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.emptyCell, 64), 0),
				new OutputItem(new ItemStack(Items.GOLD_INGOT, 4), 1),
				new OutputItem(new ItemStack(Items.IRON_INGOT, 16), 2),
				new OutputItem(new ItemStack(GTItems.tungsten, 4), 3));

		GTTileEntityIndustrialCentrifuge.addRecipe("endstone", 64, 9, euCost(100000),
				new OutputItem(new ItemStack(Blocks.SAND, 48), 0), // out0
				new OutputItem(new ItemStack(GTItems.helium3, 4), 1), // out1
				new OutputItem(new ItemStack(GTItems.helium, 4), 2), // out2
				new OutputItem(new ItemStack(GTItems.tungsten, 1), 3));// out3

		// INDUSTRIAL CENTRIFUGE RECIPES NEW/OUT OF ORDER

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(GTBlocks.sandIron, 8), 0, euCost(15000),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.ironDust, 7), 0),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.goldDust, 1), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.QUARTZ, 1), 3, euCost(8000),
				new OutputItem(new ItemStack(GTItems.silicon, 1), 0),
				new OutputItem(new ItemStack(GTItems.oxygen, 2), 1));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Blocks.MAGMA, 64), 64, euCost(3000),
				new OutputItem(new ItemStack(GTItems.lava, 64), 0));

		GTTileEntityIndustrialCentrifuge.addRecipe(StackUtil.copyWithSize(Ic2Items.obsidianDust, 64), 10, euCost(16000),
				new OutputItem(StackUtil.copyWithSize(Ic2Items.ironDust, 2), 0),
				new OutputItem(new ItemStack(GTItems.silicon, 6), 1),
				new OutputItem(new ItemStack(GTItems.oxygen, 4), 2));

		GTTileEntityIndustrialCentrifuge.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 16), 4, euCost(6000),
				new OutputItem(new ItemStack(GTItems.methane, 4), 0),
				new OutputItem(new ItemStack(Items.LEATHER, 4), 1),
				new OutputItem(new ItemStack(Items.SLIME_BALL, 1), 2));

		// PLACEHOLDER FUSION - second input is set to dueterium in the tile for now

		GTTileEntityFusionComputer.addRecipe("dustTungsten", 1, 0, Ic2Items.iridiumOre);
		GTTileEntityFusionComputer.addRecipe(new ItemStack(GTItems.tritium), 1, (new ItemStack(GTItems.plasmaHelium)));
		GTTileEntityFusionComputer.addRecipe(new ItemStack(GTItems.helium3), 1, (new ItemStack(GTItems.plasmaHelium)));
		GTTileEntityFusionComputer.addRecipe(StackUtil.copyWithSize(Ic2Items.uuMatter, 10), 0,
				(new ItemStack(GTItems.plasmaUU)));

	}

	public static void ingotUtil(Block block, Item ingot, String name) {
		String ingotstring = "ingot" + name;
		String blockstring = "block" + name;
		recipes.addRecipe(new ItemStack(block, 1), new Object[] { "XXX", "XXX", "XXX", 'X', ingotstring });
		TileEntityCompressor.addRecipe(ingotstring, 9, new ItemStack(block), 0.1F);
		recipes.addShapelessRecipe(new ItemStack(ingot, 9), new Object[] { blockstring });
	}
	
	public static void gemUtil(Block block, Item gem, String name) {
		String gemstring = "gem" + name;
		String blockstring = "block" + name;
		recipes.addRecipe(new ItemStack(block, 1), new Object[] { "XXX", "XXX", "XXX", 'X', gemstring });
		TileEntityCompressor.addRecipe(gemstring, 9, new ItemStack(block), 0.1F);
		recipes.addShapelessRecipe(new ItemStack(gem, 9), new Object[] { blockstring });
	}

	public static IRecipeModifier[] euCost(Integer amount) {
		return new IRecipeModifier[] {
				ModifierType.RECIPE_LENGTH.create((amount / 12) - GTTileEntityIndustrialCentrifuge.defaultLength) };
	}

}
