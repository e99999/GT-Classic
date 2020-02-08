package gtclassic.common.recipe;

import gtclassic.api.helpers.GTValues;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeCraftingHandler;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTConfig;
import gtclassic.common.GTItems;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.upgrades.EnchantmentModifier;
import ic2.core.item.recipe.upgrades.FlagModifier;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipe {

	private GTRecipe() {
		throw new IllegalStateException("Utility class");
	}

	static final String IC2_STEEL_MODE = "SteelRecipes";
	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	public static void initShapeless() {
		/** Duct Tape **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.ductTape), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64), GTMaterialGen.getIc2(Ic2Items.rubber, 64));
		/** Small Buffer **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.tileBufferSmall, 8), GTMaterialGen.get(GTBlocks.tileBufferLarge, 1));
		/** Light Helmet **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.lightHelment, 1), Ic2Items.solarHelmet.copy(), Ic2Items.luminator.copy(), Ic2Items.battery.copy());
		/** IC2 Fertilizer **/
		recipes.addShapelessRecipe(GTMaterialGen.getIc2(Ic2Items.fertilizer, 3), GTValues.DUST_SULFUR, GTValues.DUST_PHOSPHORUS, "dustCalcite");
		/** Clearing Scanner of NBT data **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.portableScanner), GTMaterialGen.get(GTItems.portableScanner));
		/** Fireworks from Phosphorus **/
		recipes.addShapelessRecipe(GTMaterialGen.get(Items.FIREWORKS, 3), GTValues.PAPER, GTValues.DUST_PHOSPHORUS, true);
		recipes.addShapelessRecipe(GTMaterialGen.get(Items.FIREWORKS, 3), GTValues.PAPER, GTValues.DUST_PHOSPHORUS, GTValues.DUST_PHOSPHORUS, true);
		recipes.addShapelessRecipe(GTMaterialGen.get(Items.FIREWORKS, 3), GTValues.PAPER, GTValues.DUST_PHOSPHORUS, GTValues.DUST_PHOSPHORUS, GTValues.DUST_PHOSPHORUS, true);
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)) {
			/** Gunpowder **/
			recipes.addShapelessRecipe(GTMaterialGen.get(Items.GUNPOWDER, 5), GTMaterialGen.getTube(GTMaterial.Potassium, 1), GTMaterialGen.getTube(GTMaterial.Potassium, 1), GTMaterialGen.getTube(GTMaterial.Nitrogen, 1), GTMaterialGen.getTube(GTMaterial.Nitrogen, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
			/** Methane **/
			recipes.addShapelessRecipe(GTMaterialGen.getTube(GTMaterial.Methane, 4), GTMaterialGen.getDust(GTMaterial.Carbon, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1));
			/** Magical Dye **/
			recipes.addShapelessRecipe(GTMaterialGen.getTube(GTMaterial.MagicDye, 1), "dyeCyan", "dyeMagenta", "dyeYellow", "dyeBlack", GTMaterialGen.get(Items.BLAZE_POWDER), GTMaterialGen.getTube(GTMaterial.Chlorine, 1));
			/** Electrum Dust **/
			recipes.addShapelessRecipe(GTMaterialGen.getDust(GTMaterial.Electrum, 2), "dustSilver", "dustGold");
			/** Invar Dust **/
			recipes.addShapelessRecipe(GTMaterialGen.getDust(GTMaterial.Invar, 3), "dustIron", "dustIron", "dustNickel");
			if (GTConfig.general.enableSuperSolidFuels) {
				/** Super Fuel Binder **/
				recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.fuelBinder, 6), GTValues.DUST_WOOD, GTValues.DUST_WOOD, GTValues.DUST_WOOD, GTValues.DUST_WOOD, GTValues.DUST_SULFUR, "dustLithium");
				recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.fuelBinder, 6), GTValues.DUST_WOOD, GTValues.DUST_WOOD, GTValues.DUST_WOOD, GTValues.DUST_WOOD, GTValues.DUST_SULFUR, GTMaterialGen.getTube(GTMaterial.Sodium, 1));
				/** Magic Super Fuel Binder **/
				recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.fuelBinderMagic, 3), GTMaterialGen.get(GTItems.fuelBinder), "dustEnderEye", GTMaterialGen.getTube(GTMaterial.Mercury, 1));
				recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.fuelBinderMagic, 3), GTMaterialGen.get(GTItems.fuelBinder), Items.BLAZE_POWDER, GTMaterialGen.getTube(GTMaterial.Mercury, 1));
				/** Solid Super Fuel **/
				recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.superFuel, 1), GTValues.BLOCK_COAL, GTMaterialGen.get(GTItems.fuelBinder), GTMaterialGen.get(GTItems.fuelBinder), GTMaterialGen.getTube(GTMaterial.Fuel, 1));
				/** Magic Solid Super Fuel **/
				recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.superFuelMagic, 1), GTValues.BLOCK_COAL, GTMaterialGen.get(GTItems.fuelBinderMagic), GTMaterialGen.get(GTItems.fuelBinderMagic), GTMaterialGen.get(GTItems.fuelBinderMagic), "dustSapphire", GTMaterialGen.getTube(GTMaterial.Fuel, 1));
				recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.superFuelMagic, 1), GTValues.BLOCK_COAL, GTMaterialGen.get(GTItems.fuelBinderMagic), GTMaterialGen.get(GTItems.fuelBinderMagic), GTMaterialGen.get(GTItems.fuelBinderMagic), "dustEmerald", GTMaterialGen.getTube(GTMaterial.Fuel, 1));
				recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.superFuelMagic, 1), GTValues.BLOCK_COAL, GTMaterialGen.get(GTItems.fuelBinderMagic), GTMaterialGen.get(GTItems.fuelBinderMagic), GTMaterialGen.get(GTItems.fuelBinderMagic), "dustThorium", GTMaterialGen.getTube(GTMaterial.Fuel, 1));
			}
		}
	}

	public static void initItems() {
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)) {
			/** Rock Cutter **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.rockCutter, 1), "DI ", "DI ", "DCB", new EnchantmentModifier(GTMaterialGen.get(GTItems.rockCutter), Enchantments.SILK_TOUCH).setUsesInput(), 'D', GTValues.GEM_DIAMOND, 'I', GTValues.INGOT_REFINEDIRON, 'C', GTValues.CIRCUIT_BASIC, 'B', Ic2Items.battery.copy());
			/** Jack Hammer **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.jackHammer, 1), "IBI", " C ", " D ", 'I', GTValues.INGOT_TUNGSTEN, 'C', GTValues.CIRCUIT_BASIC, 'B', Ic2Items.battery.copy(), 'D', GTValues.GEM_DIAMOND);
			/** Lithium Battery **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.lithiumBattery, 1), " G ", "ALA", "ALA", 'G', Ic2Items.goldCable.copy(), 'A', GTValues.INGOT_ALUMINIUM, 'L', "dustLithium");
			/** Lithium BatPack **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.lithiumBatpack, 1), "LCL", "LAL", "L L", 'C', GTValues.CIRCUIT_ADVANCED, 'A', GTValues.INGOT_ALUMINIUM, 'L', GTItems.lithiumBattery);
			/** Data Chip **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.chipData, 4), "EEE", "ECE", "EEE", 'E', "gemEmerald", 'C', GTValues.CIRCUIT_ADVANCED);
			/** Data Orb **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.orbData, 4), "SSS", "SCS", "SSS", 'S', "circuitElite", 'C', GTValues.CIRCUIT_DATA);
			/** Portable Scanner **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.portableScanner, 1), "PEP", "CFC", "PBP", 'P', GTValues.INGOT_ALUMINIUM, 'E', GTMaterialGen.getIc2(Ic2Items.euReader, 1), 'F', GTMaterialGen.getIc2(Ic2Items.cropAnalyzer, 1), 'C', GTValues.CIRCUIT_ADVANCED, 'B', GTMaterialGen.get(GTItems.lithiumBattery));
			/** Empty Spray Can **/
			recipes.addRecipe(GTMaterialGen.get(GTItems.sprayCanEmpty), "R", "C", 'C', Ic2Items.emptyCell, 'R', GTValues.DUST_REDSTONE);
		}
		/** Test Tube **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.testTube, 32), "G G", "G G", " G ", 'G', GTValues.BLOCK_GLASS);
		/** Sulfur Torches **/
		recipes.addRecipe(GTMaterialGen.get(Blocks.TORCH, 3), "R", "I", 'I', GTValues.STICK_WOOD, 'R', GTValues.DUST_SULFUR);
		/** Flint Mortar **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.flintMortar), " F ", "BFB", "BBB", 'F', Items.FLINT, 'B', "plankWood");
		/** Iron Mortar **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.ironMortar, 1), " I ", "BIB", "BBB", 'I', GTValues.INPUT_INGOT_ANYIRON, 'B', "stone");
		/** Magnifying Glass **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.magnifyingGlass), " P", "S ", 'P', "paneGlass", 'S', GTValues.STICK_WOOD);
		/** Destructo Pack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.destructoPack, 1), "CIC", "ILI", "CIC", 'L', GTValues.FS_LAVA, 'C', GTValues.CIRCUIT_BASIC, 'I', GTValues.INGOT_REFINEDIRON);
		/** Electro Magnet **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.electroMagnet, 1), "M M", "WMW", "IBI", 'M', Ic2Items.magnet, 'B', Ic2Items.battery, 'I', GTValues.INGOT_REFINEDIRON, 'W', Ic2Items.copperCable);
		/** Helium Coolant **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageHelium1, 1), " I ", "IHI", " I ", 'I', GTValues.INGOT_TIN, 'H', GTMaterialGen.getTube(GTMaterial.Helium, 1));
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageHelium3, 1), "III", "HHH", "III", 'I', GTValues.INGOT_TIN, 'H', GTItems.heatStorageHelium1);
		recipes.addRecipe(GTMaterialGen.get(GTItems.heatStorageHelium6, 1), "IHI", "IPI", "IHI", 'I', GTValues.INGOT_TIN, 'H', GTItems.heatStorageHelium3, 'P', Ic2Items.denseCopperPlate);
		/** Energy Control Circuit **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.circuitEnergy, 4), "CLC", "LPL", "CLC", 'L', Ic2Items.lapotronCrystal.copy(), 'C', GTValues.CIRCUIT_ADVANCED, 'P', GTValues.PLATE_IRIDIUM_ALLOY);
		/** Data Control Circuit **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.circuitData, 4), "CDC", "DPD", "CDC", 'D', GTValues.CIRCUIT_DATA, 'C', GTValues.CIRCUIT_ADVANCED, 'P', GTValues.PLATE_IRIDIUM_ALLOY);
		/** Lapotronic Energy Orb **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.orbEnergy, 1), "LLL", "LPL", "LLL", 'L', Ic2Items.lapotronCrystal.copy(), 'P', GTValues.PLATE_IRIDIUM_ALLOY);
		/** Super Conductor **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), "CCC", "PWP", "EEE", 'C', Ic2Items.reactorCoolantCellSix.copy(), 'E', GTValues.CIRCUIT_MASTER, 'W', GTValues.INGOT_TUNGSTEN, 'P', GTValues.PLATE_IRIDIUM_ALLOY);
		recipes.addRecipe(GTMaterialGen.get(GTItems.superConductor, 4), "CCC", "PWP", "EEE", 'C', GTItems.heatStorageHelium3, 'E', GTValues.CIRCUIT_MASTER, 'W', GTValues.INGOT_TUNGSTEN, 'P', GTValues.PLATE_IRIDIUM_ALLOY);
		/** Lapotron Batpack **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.lapotronPack, 1), "ELE", "SBS", "EPE", 'E', GTValues.CIRCUIT_MASTER, 'S', GTValues.CRAFTING_SUPERCONDUCTOR, 'L', GTValues.BATTERY_ULTIMATE, 'B', GTItems.lithiumBatpack, 'P', GTValues.PLATE_IRIDIUM_ALLOY);
		/** Tesla Staff **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.teslaStaff, 1), " SL", " PS", "P  ", 'L', GTValues.BATTERY_ULTIMATE, 'S', GTValues.CRAFTING_SUPERCONDUCTOR, 'P', GTValues.PLATE_IRIDIUM_ALLOY);
		/** Cloaking Device **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.cloakingDevice, 1), "IPI", "POP", "IPI", 'I', GTValues.INGOT_CHROME, 'P', GTValues.PLATE_IRIDIUM_ALLOY, 'O', GTValues.BATTERY_ULTIMATE);
		/** Echotron **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.echotron, 1), " C ", "CEC", " C ", 'E', GTBlocks.tileEchotron, 'C', GTValues.CIRCUIT_DATA);
		/** Spring Boots **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.springBoots, 1), "IBI", "I I", 'B', Ic2Items.compositeBoots.copy(), 'I', GTValues.INGOT_REFINEDIRON);
		/** Thorium rods **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodThorium2), "UCU", 'U', GTItems.rodThorium1, 'C', Ic2Items.denseCopperPlate.copy());
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodThorium4), " U ", "CCC", " U ", 'U', GTItems.rodThorium2, 'C', Ic2Items.denseCopperPlate.copy());
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodThorium4), "UCU", "CCC", "UCU", 'U', GTItems.rodThorium1, 'C', Ic2Items.denseCopperPlate.copy());
		/** Plutonium Rod **/
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodPlutonium2), "UCU", 'U', GTItems.rodPlutonium1, 'C', Ic2Items.denseCopperPlate.copy());
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodPlutonium4), " U ", "CCC", " U ", 'U', GTItems.rodPlutonium2, 'C', Ic2Items.denseCopperPlate.copy());
		recipes.addRecipe(GTMaterialGen.get(GTItems.rodPlutonium4), "UCU", "CCC", "UCU", 'U', GTItems.rodPlutonium1, 'C', Ic2Items.denseCopperPlate.copy());
	}

	public static void initBlocks() {
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)) {
			/** Fusion Casing **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingFusion), "CSC", "TMT", "CRC", 'M', GTValues.MACHINE_ELITE, 'C', GTValues.CIRCUIT_MASTER, 'S', GTValues.CRAFTING_SUPERCONDUCTOR, 'T', Ic2Items.teslaCoil.copy(), 'R', Ic2Items.reactorReflectorIridium.copy());
			/** Highly Advanced Machine Casing **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), "CTC", "TBT", "CTC", 'T', GTValues.INGOT_TITANIUM, 'C', GTValues.INGOT_CHROME, 'B', GTValues.MACHINE_ADV);
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingHighlyAdvanced), "TCT", "CBC", "TCT", 'T', GTValues.INGOT_TITANIUM, 'C', GTValues.INGOT_CHROME, 'B', GTValues.MACHINE_ADV);
			/** Fusion Computer **/
			if (GTConfig.general.removeIC2Plasmafier) {
				recipes.overrideRecipe("shaped_tile.blockPlasmafier_679353211", GTMaterialGen.get(GTBlocks.tileFusionReactor, 1), "ESE", "LCL", "ESE", 'E', GTValues.CIRCUIT_MASTER, 'S', GTBlocks.tileSupercondensator, 'L', GTValues.BATTERY_ULTIMATE, 'C', GTBlocks.tileComputer);
			} else {
				recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileFusionReactor, 1), "ESE", "LCL", "ESE", 'E', GTValues.CIRCUIT_MASTER, 'S', GTValues.CRAFTING_SUPERCONDUCTOR, 'L', GTValues.BATTERY_ULTIMATE, 'C', GTBlocks.tileComputer);
			}
			/** Electric Craftingtable **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileAutocrafter), " W ", "CMC", " P ", 'W', Ic2Items.battery.copy(), 'C', GTValues.CIRCUIT_ADVANCED, 'M', "workbench", 'P', GTValues.MACHINE_ADV);
			/** Industrial Centrifuge **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCentrifuge, 1), "RCR", "AEA", "RCR", 'E', Ic2Items.extractor, 'R', GTValues.INGOT_REFINEDIRON, 'A', GTValues.MACHINE_ADV, 'C', GTValues.CIRCUIT_ADVANCED);
			/** Charcoal Pit **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCharcoalPit), new Object[] { "IFI", "IFI", "IFI", 'I',
					"ingotRefinedIron", 'F', Items.FLINT });
			/** Disassemembler stuff with other mods **/
			Item top = GTConfig.modcompat.compatTwilightForest && Loader.isModLoaded(GTValues.MOD_ID_TFOREST)
					? GTMaterialGen.getModItem(GTValues.MOD_ID_TFOREST, "uncrafting_table").getItem()
					: GTMaterialGen.getIc2(Ic2Items.extractor).getItem();
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileDisassembler, 1), "RAR", "ECE", "RWR", 'A', top, 'W', GTBlocks.tileAutocrafter, 'R', GTValues.INGOT_REFINEDIRON, 'E', Ic2Items.insulatedCopperCable.copy(), 'C', GTValues.CIRCUIT_ADVANCED);
			/** Computer Cube **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileComputer, 1), "RGD", "GMG", "DGR", 'D', GTValues.CIRCUIT_ULTIMATE, 'R', GTValues.CIRCUIT_MASTER, 'G', GTValues.BLOCK_GLASS, 'M', GTValues.MACHINE_ADV);
			/** Digital Chest **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileDigitalChest, 1), "ICI", "MDM", "ICI", 'D', GTBlocks.tileComputer, 'I', GTValues.INPUT_INGOT_DIGITAL, 'C', GTValues.CIRCUIT_DATA, 'M', GTItems.chipData);
			/** Quantum Chest **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumChest, 1), "ICI", "MDM", "ICI", 'D', GTValues.CIRCUIT_ULTIMATE, 'I', GTValues.INPUT_INGOT_DIGITAL, 'C', GTValues.CHEST_WOOD, 'M', GTValues.MACHINE_ADV);
			/** Quantum Tank **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileQuantumTank, 1), "ICI", "MDM", "ICI", 'D', GTValues.CIRCUIT_ULTIMATE, 'I', GTValues.INPUT_INGOT_DIGITAL, 'C', Items.BUCKET, 'M', GTValues.MACHINE_ADV);
			/** Translocator **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileTranslocator), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', GTValues.CIRCUIT_BASIC, 'M', GTValues.MACHINE_BASIC, 'P', Ic2Items.importBasicUpgrade);
			/** Large Chest Buffer **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferLarge), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', GTValues.CIRCUIT_BASIC, 'M', GTValues.MACHINE_BASIC, 'P', GTValues.CHEST_WOOD);
			/** Cabinet **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileCabinet), "III", "CIC", "III", 'I', GTValues.INPUT_INGOT_MACHINE, 'C', GTValues.CHEST_WOOD);
			/** Workbench **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileWorktable), "IWI", "III", "ICI", 'I', GTValues.INPUT_INGOT_MACHINE, 'C', GTValues.CHEST_WOOD, 'W', "workbench");
			/** Drum **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileDrum), "I I", "IBI", "IBI", 'I', GTValues.INGOT_REFINEDIRON, 'B', Items.BUCKET);
			/** Fluid Translocator **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileTranslocatorFluid), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', GTValues.CIRCUIT_BASIC, 'M', GTValues.MACHINE_BASIC, 'P', Ic2Items.basicFluidImportUpgrade);
			/** Fluid Buffer **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferFluid), " W ", "CMC", " P ", 'W', Ic2Items.insulatedCopperCable.copy(), 'C', GTValues.CIRCUIT_BASIC, 'M', GTValues.MACHINE_BASIC, 'P', Items.BUCKET);
			/** Data Net Stuff **/
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.dataCable, 6), "RRR", "CIC", "RRR", 'I', GTValues.INGOT_SILICON, 'C', GTValues.CIRCUIT_DATA, 'R', "itemRubber");
			recipes.addRecipe(GTMaterialGen.get(GTItems.nodeItemDigitizer, 1), "CPC", "PMP", "CPC", 'P', GTValues.INPUT_PISTON_ANY, 'C', GTValues.CIRCUIT_DATA, 'M', GTValues.INGOT_SILICON);
			recipes.addRecipe(GTMaterialGen.get(GTItems.nodeFluidDigitizer, 1), "CPC", "PMP", "CPC", 'P', Items.BUCKET, 'C', GTValues.CIRCUIT_DATA, 'M', GTValues.INGOT_SILICON);
			recipes.addRecipe(GTMaterialGen.get(GTItems.nodeItemReconstructor, 1), "PIP", "CMC", "PIP", 'P', GTValues.INPUT_PISTON_ANY, 'C', GTValues.CIRCUIT_DATA, 'M', GTValues.INGOT_SILICON, 'I', GTValues.INPUT_INGOT_DIGITAL);
			recipes.addRecipe(GTMaterialGen.get(GTItems.nodeFluidReconstructor, 1), "PIP", "CMC", "PIP", 'P', Items.BUCKET, 'C', GTValues.CIRCUIT_DATA, 'M', GTValues.INGOT_SILICON, 'I', GTValues.INPUT_INGOT_DIGITAL);
		}
		/** Bonus recipe for piston **/
		if (GTConfig.general.morePistonRecipes) {
			recipes.addRecipe(GTMaterialGen.get(Blocks.PISTON), "WWW", "CIC", "CRC", 'W', "plankWood", 'C', "cobblestone", 'I', GTValues.INPUT_INGOT_ANY, 'R', GTValues.DUST_REDSTONE);
		}
		/** Bonus recipe for hopper **/
		if (GTConfig.general.moreHopperRecipes) {
			recipes.addRecipe(GTMaterialGen.get(Blocks.HOPPER), "I I", "ICI", " I ", 'I', GTValues.INPUT_INGOT_ANY, 'C', GTValues.CHEST_WOOD);
		}
		/** Tungsten Mining Pipe **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.miningPipe, 8), "T T", "T T", "TIT", 'T', GTValues.INGOT_TUNGSTEN, 'I', GTValues.GEM_DIAMOND);
		/** LESU Casing **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.casingLapotron), "BBB", "BCB", "BBB", 'B', "blockLapis", 'C', GTValues.CIRCUIT_BASIC);
		/** Lightning Rod **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileLightningRod, 1), "EAE", "ASA", "EAE", 'E', GTValues.CIRCUIT_MASTER, 'S', GTBlocks.tileSupercondensator, 'A', GTValues.MACHINE_ELITE);
		/** Bedrock Miner **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBedrockMiner, 1), "PCP", "MDE", "PTP", 'P', GTBlocks.miningPipe, 'C', GTValues.CIRCUIT_ADVANCED, 'M', Ic2Items.macerator, 'D', Ic2Items.diamondDrill, 'E', Ic2Items.extractor, 'T', Ic2Items.miner);
		/** Dragon Egg Energy Siphon **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileDragonEggEnergySiphon, 1), "CTC", "PSP", "CBC", 'C', GTValues.CIRCUIT_MASTER, 'S', GTBlocks.tileSupercondensator, 'B', GTValues.BATTERY_ULTIMATE, 'P', GTValues.PLATE_IRIDIUM_ALLOY, 'T', Ic2Items.teslaCoil.copy());
		/** Magic Energy Converter **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileMagicEnergyConverter, 1), "CTC", "IBI", "CLC", 'C', GTValues.CIRCUIT_ADVANCED, 'B', Blocks.BEACON, 'L', Ic2Items.lapotronCrystal.copy(), 'I', GTValues.INGOT_PLATINUM, 'T', Ic2Items.teslaCoil.copy());
		/** Magic Energy Absorber **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileMagicEnergyAbsorber, 1), "CTC", "PSP", "CBC", 'C', GTValues.CIRCUIT_MASTER, 'S', Blocks.BEACON, 'B', GTBlocks.tileMagicEnergyConverter, 'P', GTValues.PLATE_IRIDIUM_ALLOY, 'T', GTValues.CRAFTING_SUPERCONDUCTOR);
		/** Player Detector **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tilePlayerDetector, 1), " D ", "CcC", " D ", 'D', Blocks.OBSERVER, 'C', GTValues.CIRCUIT_BASIC, 'c', GTValues.MACHINE_ADV);
		/** Energy Storage **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileAESU), "OOO", "OCO", "OOO", 'O', GTValues.BATTERY_ULTIMATE, 'C', GTBlocks.tileComputer);
		/** Large Chest Buffer **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBufferLarge), "SSS", "SCS", "SSS", 'S', GTBlocks.tileBufferSmall, 'C', Ic2Items.upgradeBase.copy());
		/** Matter Fabricator **/
		if (GTConfig.general.removeIC2MassFab) {
			recipes.overrideRecipe("shaped_tile.blockMatter_1416524227", GTMaterialGen.get(GTBlocks.tileFabricator, 1), "ETE", "HLH", "ETE", 'E', GTValues.CIRCUIT_MASTER, 'T', Ic2Items.teleporter, 'H', GTValues.MACHINE_ELITE, 'L', GTValues.BATTERY_ULTIMATE);
		} else {
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileFabricator, 1), "ETE", "HLH", "ETE", 'E', GTValues.CIRCUIT_MASTER, 'T', Ic2Items.teleporter, 'H', GTValues.MACHINE_ELITE, 'L', GTValues.BATTERY_ULTIMATE);
		}
		/** Charge-O-Mat **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileChargeOmat, 1), "RCR", "AEA", "RMR", 'E', GTValues.BATTERY_ULTIMATE, 'R', GTValues.CIRCUIT_MASTER, 'A', GTValues.CHEST_WOOD, 'C', GTBlocks.tileComputer, 'M', GTValues.MACHINE_ADV);
		/** IDSU **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileIDSU, 1), "PHP", "HEH", "PHP", 'P', GTValues.PLATE_IRIDIUM_ALLOY, 'H', GTBlocks.tileAESU, 'E', Blocks.ENDER_CHEST);
		/** LESU **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileLESU, 1), " L ", "CBC", " H ", 'L', Ic2Items.transformerLV, 'H', Ic2Items.transformerMV, 'C', GTValues.CIRCUIT_ADVANCED, 'B', GTBlocks.casingLapotron);
		/** Supercondensator **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileSupercondensator, 1), "EAE", "SMS", "EAE", 'E', GTValues.CIRCUIT_MASTER, 'S', GTValues.CRAFTING_SUPERCONDUCTOR, 'A', GTValues.BATTERY_ULTIMATE, 'M', GTValues.MACHINE_ADV);
		/** Superconductor Cable **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileSuperconductorCableMAX, 4), "MEM", "SSS", "MEM", 'E', GTValues.CIRCUIT_MASTER, 'S', GTValues.CRAFTING_SUPERCONDUCTOR, 'M', Ic2Items.magnet);
		/** Superconductor Cable x 2 **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.tileSuperconductorCableIV, 2), GTMaterialGen.get(GTBlocks.tileSuperconductorCableMAX, 1));
		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.tileSuperconductorCableMAX, 1), GTMaterialGen.get(GTBlocks.tileSuperconductorCableIV, 1), GTMaterialGen.get(GTBlocks.tileSuperconductorCableIV, 1));
		/** Superconductor Cable x 4 **/
		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.tileSuperconductorCableHV, 2), GTMaterialGen.get(GTBlocks.tileSuperconductorCableIV, 1));
		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.tileSuperconductorCableIV, 1), GTMaterialGen.get(GTBlocks.tileSuperconductorCableHV, 1), GTMaterialGen.get(GTBlocks.tileSuperconductorCableHV, 1));
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileSuperconductorCableMAX, 1), "CC", "CC", 'C', GTBlocks.tileSuperconductorCableHV);
		/** Echotron **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileEchotron, 1), "CRC", "JMN", "CBC", 'C', GTValues.CIRCUIT_BASIC, 'R', "record", 'J', Blocks.JUKEBOX, 'M', GTValues.MACHINE_ADV, 'N', Blocks.NOTEBLOCK, 'B', Ic2Items.battery);
		/** Monster Repellator **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileMobRepeller, 1), "SSS", " M ", "CCC", 'S', "gemSapphire", 'M', GTValues.MACHINE_ADV, 'C', GTValues.CIRCUIT_BASIC);
		/** Energy Transmitter **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileEnergyTransmitter, 1), "STS", "WEW", "SCS", 'S', "gemSapphire", 'T', Ic2Items.teleporter, 'C', GTBlocks.tileComputer, 'E', Ic2Items.transformerMV, 'W', GTValues.CRAFTING_SUPERCONDUCTOR);
		/** UU Assembler **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileUUMAssembler, 1), "dCd", "TQE", "DBD", 'd', "circuitElite", 'C', GTBlocks.tileComputer, 'T', Ic2Items.teleporter, 'Q', GTBlocks.tileCabinet, 'E', GTBlocks.tileAutocrafter, 'D', GTValues.CIRCUIT_ULTIMATE, 'B', GTValues.INPUT_BATTERY_ADVANCED);
		/** Low Voltage Battery Block **/
		recipes.addRecipe(GTMaterialGen.get(GTBlocks.tileBatteryLV), " W ", "IRI", "IMI", 'W', Ic2Items.copperCable, 'I', GTValues.INGOT_TIN, 'M', GTValues.MACHINE_BASIC, 'R', "blockRedstone");
		/** More recipes for vanilla rails **/
		if (GTConfig.general.vanillaRailRecipes) {
			recipes.addRecipe(GTMaterialGen.get(Blocks.GOLDEN_RAIL, 6), "I I", "ISI", "IRI", 'I', "ingotElectrum", 'S', GTValues.STICK_WOOD, 'R', GTValues.DUST_REDSTONE);
			recipes.addRecipe(GTMaterialGen.get(Blocks.DETECTOR_RAIL, 8), "I I", "ISI", "IRI", 'I', GTValues.INGOT_REFINEDIRON, 'S', Blocks.STONE_PRESSURE_PLATE, 'R', GTValues.DUST_REDSTONE);
			recipes.addRecipe(GTMaterialGen.get(Blocks.DETECTOR_RAIL, 12), "I I", "ISI", "IRI", 'I', GTValues.INPUT_INGOT_HIGH, 'S', Blocks.STONE_PRESSURE_PLATE, 'R', GTValues.DUST_REDSTONE);
			recipes.addRecipe(GTMaterialGen.get(Blocks.RAIL, 20), "I I", "ISI", "I I", 'I', GTValues.INGOT_REFINEDIRON, 'S', GTValues.STICK_WOOD);
			recipes.addRecipe(GTMaterialGen.get(Blocks.RAIL, 32), "I I", "ISI", "I I", 'I', GTValues.INPUT_INGOT_HIGH, 'S', GTValues.STICK_WOOD);
			recipes.addRecipe(GTMaterialGen.get(Blocks.ACTIVATOR_RAIL, 8), "IRI", "ISI", "IRI", 'I', GTValues.INGOT_REFINEDIRON, 'S', Blocks.REDSTONE_TORCH, 'R', GTValues.STICK_WOOD);
			recipes.addRecipe(GTMaterialGen.get(Blocks.ACTIVATOR_RAIL, 16), "IRI", "ISI", "IRI", 'I', GTValues.INPUT_INGOT_HIGH, 'S', Blocks.REDSTONE_TORCH, 'R', GTValues.STICK_WOOD);
		}
	}

	public static void initIC2() {
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)) {
			/** Alt Wind Mill **/
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.windMill, 1), "X X", " Y ", "X X", 'Y', Ic2Items.generator.copy(), 'X', GTValues.INGOT_ALUMINIUM);
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.waterMill.copy(), 3), " X ", "XYX", " X ", 'Y', Ic2Items.generator.copy(), 'X', GTValues.INGOT_ALUMINIUM);
			/** Mixed Metal Ingots **/
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.mixedMetalIngot, 3), "III", "BBB", "TTT", 'I', GTValues.INGOT_REFINEDIRON, 'B', GTValues.INGOT_BRONZE, 'T', GTValues.INPUT_INGOT_MIXED);
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.mixedMetalIngot, 6), "III", "BBB", "TTT", 'I', GTValues.INGOT_TITANIUM, 'B', GTValues.INGOT_BRONZE, 'T', GTValues.INPUT_INGOT_MIXED);
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.mixedMetalIngot, 6), "III", "BBB", "TTT", 'I', GTValues.INGOT_TUNGSTEN, 'B', GTValues.INGOT_BRONZE, 'T', GTValues.INPUT_INGOT_MIXED);
			/** Alt Reactor Vent **/
			recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.reactorVent, 1), "IBI", "B B", "IBI", 'I', GTValues.INGOT_ALUMINIUM, 'B', Blocks.IRON_BARS);
		}
		/** More Luminator Recipes **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), "III", "GHG", "GGG", 'G', GTValues.BLOCK_GLASS, 'I', "ingotSilver", 'H', GTMaterialGen.getTube(GTMaterial.Helium, 1), 'C', Ic2Items.insulatedCopperCable.copy());
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.luminator, 16), "III", "GHG", "GGG", 'G', GTValues.BLOCK_GLASS, 'I', "ingotSilver", 'H', GTMaterialGen.getTube(GTMaterial.Mercury, 1), 'C', Ic2Items.insulatedCopperCable.copy());
		/** Overclocker helium coolant recipes **/
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 2), "CCC", "WEW", 'C', GTMaterialGen.get(GTItems.heatStorageHelium1), 'W', Ic2Items.insulatedCopperCable.copy(), 'E', Ic2Items.electricCircuit.copy());
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 6), "CCC", "WEW", 'C', GTMaterialGen.get(GTItems.heatStorageHelium3), 'W', GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 3), 'E', GTMaterialGen.getIc2(Ic2Items.electricCircuit, 3));
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 12), "CCC", "WEW", 'C', GTMaterialGen.get(GTItems.heatStorageHelium6), 'W', GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 6), 'E', GTMaterialGen.getIc2(Ic2Items.electricCircuit, 6));
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 2), " X ", "XYX", " X ", 'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 2), 'X', GTMaterialGen.get(GTItems.heatStorageHelium1));
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 4), "XXX", "XYX", "XXX", 'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 4), 'X', GTMaterialGen.get(GTItems.heatStorageHelium1));
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 8), " X ", "XYX", " X ", 'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 8), 'X', GTMaterialGen.get(GTItems.heatStorageHelium3));
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 16), "XXX", "XYX", "XXX", 'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 16), 'X', GTMaterialGen.get(GTItems.heatStorageHelium3));
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 16), " X ", "XYX", " X ", 'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 16), 'X', GTMaterialGen.get(GTItems.heatStorageHelium6));
		recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.overClockerUpgrade, 32), "XXX", "XYX", "XXX", 'Y', GTMaterialGen.getIc2(Ic2Items.upgradeBase, 32), 'X', GTMaterialGen.get(GTItems.heatStorageHelium6));
		/** Tape for cables **/
		recipes.addShapelessRecipe(Ic2Items.insulatedCopperCable.copy(), Ic2Items.copperCable.copy(), GTValues.DUCT_TAPE);
		recipes.addShapelessRecipe(Ic2Items.insulatedBronzeCable.copy(), Ic2Items.bronzeCable.copy(), GTValues.DUCT_TAPE);
		recipes.addShapelessRecipe(Ic2Items.doubleInsulatedBronzeCable, Ic2Items.insulatedBronzeCable.copy(), GTValues.DUCT_TAPE);
		recipes.addShapelessRecipe(Ic2Items.insulatedGoldCable.copy(), Ic2Items.goldCable.copy(), GTValues.DUCT_TAPE);
		recipes.addShapelessRecipe(Ic2Items.doubleInsulatedGoldCable.copy(), Ic2Items.insulatedGoldCable.copy(), GTValues.DUCT_TAPE);
		recipes.addShapelessRecipe(Ic2Items.insulatedIronCable.copy(), Ic2Items.ironCable.copy(), GTValues.DUCT_TAPE);
		recipes.addShapelessRecipe(Ic2Items.doubleInsulatedIronCable, Ic2Items.insulatedIronCable.copy(), GTValues.DUCT_TAPE);
		recipes.addShapelessRecipe(Ic2Items.tribbleInsulatedIronCable, Ic2Items.doubleInsulatedIronCable.copy(), GTValues.DUCT_TAPE);
		/** UU-Matter Recipes **/
		if (GTConfig.general.gregtechUURecipes) {
			recipes.overrideRecipe("shaped_item.emerald_981588030", GTMaterialGen.get(Items.EMERALD), "UUU", "UUU", " U ", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
			recipes.addRecipe(GTMaterialGen.getGem(GTMaterial.Ruby, 1), " UU", "UUU", "UU ", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
			recipes.addRecipe(GTMaterialGen.getGem(GTMaterial.Sapphire, 1), "UU ", "UUU", " UU", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
			recipes.addRecipe(GTMaterialGen.get(GTBlocks.oreBauxite, 6), "U U", " UU", "U U", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
			recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Titanium, 2), "UUU", " U ", " U ", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
			recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Aluminium, 16), " U ", " U ", "UUU", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
			recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Platinum, 1), "  U", "UUU", "UUU", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
			recipes.addRecipe(GTMaterialGen.getDust(GTMaterial.Tungsten, 6), "U  ", "UUU", "UUU", 'U', GTMaterialGen.getIc2(Ic2Items.uuMatter, 1), true);
		}
	}

	public static void initIC2Circuits() {
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)) {
			if (GTConfig.general.addBasicCircuitRecipes) {
				int recipeId = IC2.config.getFlag(IC2_STEEL_MODE) ? 1921363733 : 1058514721;
				recipes.overrideRecipe("shaped_item.itemPartCircuit_"
						+ recipeId, GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CCC", "RIR", "CCC", 'C', Ic2Items.insulatedCopperCable.copy(), 'R', GTValues.DUST_REDSTONE, 'I', GTValues.INPUT_INGOT_ELECTRIC);
				recipeId = IC2.config.getFlag(IC2_STEEL_MODE) ? -1911001323 : 1521116961;
				recipes.overrideRecipe("shaped_item.itemPartCircuit_"
						+ recipeId, GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "CRC", "CIC", "CRC", 'C', Ic2Items.insulatedCopperCable.copy(), 'R', GTValues.DUST_REDSTONE, 'I', GTValues.INPUT_INGOT_ELECTRIC);
				recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.electricCircuit, 2), "CCC", "III", "CCC", 'C', Ic2Items.insulatedCopperCable.copy(), 'I', GTValues.INPUT_INGOT_ELECTRIC);
				recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.electricCircuit, 2), "CIC", "CIC", "CIC", 'C', Ic2Items.insulatedCopperCable.copy(), 'I', GTValues.INPUT_INGOT_ELECTRIC);
			}
			if (GTConfig.general.addAdvCircuitRecipes) {
				recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-1948043137", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RGR", "LCL", "RGR", 'R', GTValues.DUST_REDSTONE, 'G', GTValues.DUST_GLOWSTONE, 'C', GTValues.CIRCUIT_BASIC, 'L', GTValues.INPUT_LAPIS_ANY);
				recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-205948801", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "RLR", "GCG", "RLR", 'R', GTValues.DUST_REDSTONE, 'G', GTValues.DUST_GLOWSTONE, 'C', GTValues.CIRCUIT_BASIC, 'L', GTValues.INPUT_LAPIS_ANY);
				recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 2), "RGR", "LCL", "RGR", 'R', GTValues.INPUT_INGOT_SILVER, 'G', GTValues.DUST_GLOWSTONE, 'C', GTValues.INPUT_CIRCUIT_BASIC_X2, 'L', GTValues.INPUT_LAPIS_ANY);
				recipes.addRecipe(GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 2), "RLR", "GCG", "RLR", 'R', GTValues.INPUT_INGOT_SILVER, 'G', GTValues.DUST_GLOWSTONE, 'C', GTValues.INPUT_CIRCUIT_BASIC_X2, 'L', GTValues.INPUT_LAPIS_ANY);
			}
		}
	}

	public static void initIC2Jetpacks() {
		if (GTConfig.general.harderJetpacks) {
			String inputItem = Loader.isModLoaded(GTValues.MOD_ID_GTCX) ? "plateStainlessSteel"
					: GTValues.INGOT_TITANIUM;
			int id = IC2.config.getFlag(IC2_STEEL_MODE) ? -1657838234 : 176647782;
			recipes.overrideRecipe("shaped_item.itemArmorJetpack_"
					+ id, StackUtil.copyWithDamage(Ic2Items.jetpack, 18001), "ICI", "IFI", "R R", 'I', inputItem, 'C', GTValues.CIRCUIT_BASIC, 'F', Ic2Items.fuelCan.copy(), 'R', GTValues.DUST_REDSTONE);
			id = IC2.config.getFlag(IC2_STEEL_MODE) ? -1370803315 : 463682701;
			recipes.overrideRecipe("shaped_item.itemArmorJetpackElectric_"
					+ id, GTMaterialGen.getIc2(Ic2Items.electricJetpack), "ICI", "IBI", "G G", 'I', inputItem, 'C', GTValues.CIRCUIT_ADVANCED, 'B', Ic2Items.batBox.copy(), 'G', Items.DRAGON_BREATH);
		}
	}

	public static void initIC2Overrides() {
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)) {
			/** Machine casings can take aluminium **/
			String machineId = IC2.config.getFlag(IC2_STEEL_MODE) ? "480320652" : "527557260";
			recipes.overrideRecipe("shaped_tile.blockmachine_"
					+ machineId, Ic2Items.machine.copy(), "III", "I I", "III", 'I', GTValues.INPUT_INGOT_MACHINE);
			/** Iridium Plate **/
			recipes.overrideRecipe("shaped_item.itemPartIridium_1100834802", GTMaterialGen.getIc2(Ic2Items.iridiumPlate, 1), "IAI", "ADA", "IAI", 'I', "ingotIridium", 'A', Ic2Items.advancedAlloy.copy(), 'D', GTValues.GEM_DIAMOND);
			/** Making the macerator harder **/
			if (GTConfig.general.harderIC2Macerator) {
				recipes.overrideRecipe("shaped_tile.blockStoneMacerator_-130868445", Ic2Items.stoneMacerator.copy(), "FDF", "DPD", "FBF", 'D', GTValues.GEM_DIAMOND, 'F', Items.FLINT, 'P', Blocks.PISTON, 'B', Blocks.FURNACE);
				recipes.overrideRecipe("shaped_tile.blockMacerator_127744036", Ic2Items.macerator.copy(), "III", "IMI", "ICI", 'I', GTValues.INGOT_REFINEDIRON, 'M', Ic2Items.stoneMacerator.copy(), 'C', GTValues.CIRCUIT_BASIC);
				recipes.overrideRecipe("shaped_tile.blockMacerator_2072794668", Ic2Items.macerator.copy(), "FDF", "DMD", "FCF", 'D', GTValues.GEM_DIAMOND, 'F', Items.FLINT, 'M', GTValues.MACHINE_BASIC, 'C', GTValues.CIRCUIT_BASIC);
			}
			if (IC2.config.getFlag("CraftingNuke")) {
				recipes.overrideRecipe("shaped_tile.blockNuke_-814805840", GTMaterialGen.getIc2(Ic2Items.nuke, 1), "CUC", "RPR", "CUC", 'C', GTValues.CIRCUIT_ADVANCED, 'U', Ic2Items.reactorReEnrichedUraniumRod.copy(), 'P', GTItems.rodPlutonium1, 'R', Ic2Items.reactorReflectorThick.copy());
			}
			/** Mining Laser Recipe **/
			recipes.overrideRecipe("shaped_item.itemToolMiningLaser_1732214669", Ic2Items.miningLaser.copy(), "RcB", "AAC", " AA", 'A', Ic2Items.advancedAlloy.copy(), 'C', GTValues.CIRCUIT_ADVANCED, 'c', GTMaterialGen.get(GTItems.heatStorageHelium6, 1), 'R', "gemRuby", 'B', GTValues.INPUT_BATTERY_ADVANCED);
			/*
			 * Ive dreaded this part for so long, but it appears Speiger lost his mind when
			 * he made these recipes and needs my help to fix them!
			 */
			/** Redoing Plasma Core **/
			recipes.overrideRecipe("shaped_item.itemPlasmaCore_-1985082214", Ic2Items.plasmaCore.copy(), "XYX", "YCY", "XYX", 'X', GTValues.CRAFTING_SUPERCONDUCTOR, 'Y', GTMaterialGen.getIngot(GTMaterial.Tungsten, 1), 'C', Ic2Items.plasmaCell.copy());
			/** Removing the most ugly cable ever to bless modded mc */
			int recipeId = IC2.config.getFlag(IC2_STEEL_MODE) ? -1869742241 : 449044295;
			GTRecipeCraftingHandler.removeRecipe("ic2", "shaped_item.itemPlasmaCable_" + recipeId);
			/** Quantum Overclocker BS **/
			recipes.overrideRecipe("shaped_item.quantumOverclockerUpgrade_-1387578587", Ic2Items.quantumOverclockerUpgrade.copy(), "XHX", "HVH", "XSX", 'X', "ingotTechnetium", 'H', GTItems.heatStorageHelium6, 'V', Ic2Items.overClockerUpgrade.copy(), 'S', GTValues.CRAFTING_SUPERCONDUCTOR);
			/** PESD Thingy **/
			recipes.overrideRecipe("shaped_item.itemPESD_-912043277", Ic2Items.pesd.copy(), "XYX", "YVY", "XYX", 'Y', GTValues.INGOT_TUNGSTEN, 'X', GTValues.BATTERY_ULTIMATE, 'V', Ic2Items.plasmaCore.copy());
			/** PESU **/
			recipes.overrideRecipe("shaped_tile.blockPesu_281205134", Ic2Items.pesu.copy(), "XYX", "CCC", "XYX", 'X', GTValues.INGOT_TUNGSTEN, 'Y', GTValues.CIRCUIT_MASTER, 'C', Ic2Items.pesd.copy());
			/** IV Transformer **/
			recipes.overrideRecipe("shaped_tile.blockTransformerIV_1876908464", Ic2Items.transformerIV.copy(), "XYX", "CVB", "XYX", 'X', GTValues.INGOT_TUNGSTEN, 'Y', GTValues.CRAFTING_SUPERCONDUCTOR, 'C', GTValues.CIRCUIT_MASTER, 'V', Ic2Items.transformerEV.copy(), 'B', Ic2Items.pesd);
			/** Teleporter-Ma-Bob **/
			recipes.overrideRecipe("shaped_item.itemPortableTeleporter_-869928001", Ic2Items.portableTeleporter.copy(), "XYX", "XCX", "BNB", 'X', GTValues.INGOT_PLATINUM, 'Y', GTItems.chipData, 'C', GTValues.CIRCUIT_MASTER, 'B', GTValues.BATTERY_ULTIMATE, 'N', Ic2Items.teleporter.copy());
			/** Removing the different versions of final wrench **/
			GTRecipeCraftingHandler.removeRecipe("ic2", "shaped_item.precisionWrench_-1322002202");
			/**
			 * This will make the highest teir wrench craftable after the regular wrench
			 **/
			recipes.overrideRecipe("shaped_item.precisionWrench_-1943783685", Ic2Items.precisionWrench.copy(), "XRX", "CVC", "XYX", (new FlagModifier(Ic2Items.precisionWrench.copy(), "Lossless", true)).setUsesInput(), 'X', Ic2Items.advancedCircuit.copy(), 'C', GTValues.INGOT_TUNGSTEN, 'Y', Ic2Items.electricWrench.copy(), 'V', Ic2Items.iridiumPlate.copy(), 'R', GTItems.rockCutter);
		}
		/** Thick Reflector Recipe **/
		recipes.overrideRecipe("shaped_item.reactorReflectorThick_-1313142365", Ic2Items.reactorReflectorThick.copy(), " P ", "PBP", " P ", 'P', Ic2Items.reactorReflector, 'B', GTMaterialGen.getTube(GTMaterial.Beryllium, 1));
		/** MFE with Lithium Batteries **/
		recipes.overrideRecipe("shaped_tile.blockMFE_-1307270245", Ic2Items.mfe.copy(), "XYX", "YCY", "XYX", 'C', GTValues.MACHINE_BASIC, 'Y', GTValues.INPUT_BATTERY_ADVANCED, 'X', Ic2Items.doubleInsulatedGoldCable.copy());
		recipes.overrideRecipe("shaped_tile.blockMFE_2004107975", Ic2Items.mfe.copy(), "XYX", "YCY", "XYX", 'C', GTValues.MACHINE_BASIC, 'Y', GTValues.INPUT_BATTERY_ADVANCED, 'X', GTMaterialGen.getIc2(Ic2Items.doubleInsulatedBronzeCable, 4));
		/** Battery Station Override **/
		recipes.overrideRecipe("shaped_tile.blockBatteryBox_214394435", Ic2Items.batteryStation.copy(), "XCX", "VBV", "XYX", 'Y', Ic2Items.transformerEV.copy(), 'C', GTValues.CRAFTING_SUPERCONDUCTOR, 'X', Ic2Items.advMachine.copy(), 'B', GTBlocks.tileComputer, 'V', GTValues.CIRCUIT_MASTER);
		/** RE Battery **/
		recipes.overrideRecipe("shaped_item.itemBatRE_2077392104", GTMaterialGen.getIc2(Ic2Items.battery, 1), " C ", "TRT", "TRT", 'T', GTValues.INGOT_TIN, 'R', GTValues.DUST_REDSTONE, 'C', Ic2Items.copperCable.copy());
		/** Energium Crystal Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatCrystal_-1564046631", GTMaterialGen.getIc2(Ic2Items.energyCrystal, 1), "RRR", "RDR", "RRR", 'D', GTValues.INPUT_CRYSTAL_LOW, 'R', GTValues.DUST_REDSTONE);
		/** Lapotron Stuff **/
		recipes.overrideRecipe("shaped_item.itemBatLamaCrystal_1330077638", GTMaterialGen.getIc2(Ic2Items.lapotronCrystal, 1), "LCL", "LDL", "LCL", 'D', GTValues.INPUT_CRYSTAL_HIGH, 'C', GTValues.CIRCUIT_BASIC, 'L', GTValues.INPUT_LAPIS_ANY);
		/** Adding ruby to glass fiber cable **/
		recipes.overrideRecipe("shaped_item.itemGlassCable_-542195504", GTMaterialGen.getIc2(Ic2Items.glassFiberCable, 4), "XXX", "CVC", "XXX", 'X', GTValues.BLOCK_GLASS, 'C', GTValues.DUST_REDSTONE, 'V', GTValues.INPUT_CRYSTAL_LOW);
		recipes.overrideRecipe("shaped_item.itemGlassCable_-410929364", GTMaterialGen.getIc2(Ic2Items.glassFiberCable, 6), "XXX", "CVC", "XXX", 'X', GTValues.BLOCK_GLASS, 'C', GTValues.INPUT_INGOT_SILVER, 'V', GTValues.INPUT_CRYSTAL_LOW);
		/** Solar Panel **/
		if (GTConfig.general.betterIC2SolarRecipes) {
			recipes.overrideRecipe("shaped_tile.blockSolarGenerator_1093731471", GTMaterialGen.getIc2(Ic2Items.solarPanel, 1), "XYX", "YXY", "CVC", 'V', GTValues.MACHINE_BASIC, 'X', "itemSilicon", 'Y', GTValues.BLOCK_GLASS, 'C', Ic2Items.carbonPlate);
			recipes.overrideRecipe("shaped_tile.blockSolarGenerator_261816397", GTMaterialGen.getIc2(Ic2Items.solarPanel, 1), "YYY", "XXX", "CVC", 'V', GTValues.MACHINE_BASIC, 'X', "itemSilicon", 'Y', GTValues.BLOCK_GLASS, 'C', Ic2Items.carbonPlate);
		}
	}

	public static void initProcessing() {
		GameRegistry.addSmelting(GTMaterialGen.get(GTBlocks.oreSheldonite, 1), (GTMaterialGen.getIngot(GTMaterial.Platinum, 1)), 0.1F);
		maceratorUtil("oreBauxite", 1, GTMaterialGen.getDust(GTMaterial.Bauxite, 4));
		maceratorUtil("oreIridium", 1, GTMaterialGen.getDust(GTMaterial.Iridium, 2));
		TileEntityMacerator.addRecipe(GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1), GTMaterialGen.getDust(GTMaterial.Iridium, 1));
		TileEntityMacerator.addRecipe("stoneMarble", 1, GTMaterialGen.getDust(GTMaterial.Calcite, 1));
		TileEntityMacerator.addRecipe("stoneLimestone", 1, GTMaterialGen.getDust(GTMaterial.Calcite, 1));
		TileEntityMacerator.addRecipe("stoneBasalt", 1, GTMaterialGen.getDust(GTMaterial.Basalt, 1));
		TileEntityMacerator.addRecipe(GTMaterialGen.get(Items.FLINT, 1), GTMaterialGen.getDust(GTMaterial.Flint, 1));
		TileEntityMacerator.addRecipe("enderpearl", 1, GTMaterialGen.getDust(GTMaterial.EnderPearl, 1));
		TileEntityMacerator.addRecipe(GTMaterialGen.get(Items.ENDER_EYE, 1), GTMaterialGen.getDust(GTMaterial.EnderEye, 2));
		TileEntityMacerator.addRecipe("gemEmerald", 1, GTMaterialGen.getDust(GTMaterial.Emerald, 1));
		TileEntityMacerator.addRecipe("logWood", 1, GTMaterialGen.getDust(GTMaterial.Wood, 6));
		TileEntityMacerator.addRecipe(GTMaterialGen.get(GTBlocks.oreChid), (GTMaterialGen.getDust(GTMaterial.BrownDye, 1)));
		TileEntityMacerator.addRecipe(GTMaterialGen.get(GTBlocks.phosphorLily), (GTMaterialGen.getDust(GTMaterial.Phosphorus, 1)));
		TileEntityExtractor.addRecipe("oreRuby", 1, GTMaterialGen.getGem(GTMaterial.Ruby, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreSapphire", 1, GTMaterialGen.getGem(GTMaterial.Sapphire, 3), 0.1F);
		TileEntityExtractor.addRecipe(GTMaterialGen.get(GTBlocks.brittleCharcoal), new ItemStack(Items.COAL, 3, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustEmerald", 1, GTMaterialGen.get(Items.EMERALD, 1));
		TileEntityCompressor.addRecipe("dustCarbon", 8, GTMaterialGen.getIc2(Ic2Items.carbonFiber, 1));
		TileEntityCompressor.addRecipe("dustUranium", 1, GTMaterialGen.getIc2(Ic2Items.uraniumIngot, 1), 0.3F);
		TileEntityCompressor.addRecipe("dustThorium", 1, GTMaterialGen.getIngot(GTMaterial.Thorium, 1));
		ClassicRecipes.fluidGenerator.addEntry(GTMaterialGen.getFluid(GTMaterial.Sodium), 3800, 8);
		ClassicRecipes.fluidGenerator.addEntry(GTMaterialGen.getFluid(GTMaterial.Methane), 3000, 16);
		ClassicRecipes.fluidGenerator.addEntry(GTMaterialGen.getFluid(GTMaterial.Fuel), 4000, 30);
		if (GTConfig.general.addHydrogenAsLiquidFuel) {
			ClassicRecipes.fluidGenerator.addEntry(GTMaterialGen.getFluid(GTMaterial.Hydrogen), 950, 16);
		}
		ItemStack fullCan = GTMaterialGen.get(GTItems.sprayCan);
		NBTTagCompound nbt = StackUtil.getNbtData(fullCan);
		nbt.setInteger("color", 15);
		ClassicRecipes.canningMachine.registerCannerItem(GTMaterialGen.get(GTItems.sprayCanEmpty), new RecipeInputItemStack(GTMaterialGen.getTube(GTMaterial.MagicDye, 1)), fullCan);
	}

	/*
	 * Adds a macerator recipe while removing duplicates generated by ic2c
	 */
	public static void maceratorUtil(String input, int amount, ItemStack output) {
		TileEntityMacerator.oreBlacklist.add(input);
		TileEntityMacerator.addRecipe(input, amount, output);
	}
}
