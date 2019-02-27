package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.block.GTBlockBattery;
import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockDuctTape;
import gtclassic.block.GTBlockOreSand;
import gtclassic.block.GTBlockOreStone;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileCustom;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItemBlock;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.itemblock.GTItemBlockRare;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileBasicEnergyStorage;
import gtclassic.tile.GTTileBlockCustom;
import gtclassic.tile.GTTileBloomery;
import gtclassic.tile.GTTileBookshelf;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileDigitalChest;
import gtclassic.tile.GTTileDigitalTransformer;
import gtclassic.tile.GTTileFusionComputer;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileLargeChest;
import gtclassic.tile.GTTileLightningRod;
import gtclassic.tile.GTTileMultiEnergyStorage;
import gtclassic.tile.GTTileQuantumEnergyStorage;
import gtclassic.tile.GTTileSmallChest;
import gtclassic.tile.GTTileSuperConductorHigh;
import gtclassic.tile.GTTileSuperConductorLow;
import gtclassic.tile.GTTileWorkbench;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTBlocks {

	static final List<Block> toRegister = new ArrayList<Block>();
	public static final GTBlockCasing superCasingBlock = registerBlock(new GTBlockCasing("Superconductor", 0));
	public static final GTBlockCasing fusionCasingBlock = registerBlock(new GTBlockCasing("Fusion", 1));
	public static final GTBlockCasing fissionCasingBlock = registerBlock(new GTBlockCasing("Fission", 2));
	public static final GTBlockCasing crystalCasingBlock = registerBlock(new GTBlockCasing("Crystal", 3));

	public static final GTBlockCasing kanthalCoilBlock = registerBlock(new GTBlockCasing("Kanthal", 13));
	public static final GTBlockCasing nichromeCoilBlock = registerBlock(new GTBlockCasing("Nichrome", 14));
	public static final GTBlockCasing constantanCoilBlock = registerBlock(new GTBlockCasing("Constantan", 15));

	public static final GTBlockOreStone galenaOre = registerBlock(new GTBlockOreStone("Galena", 0, 1, 3.0F));
	public static final GTBlockOreStone iridiumOre = registerBlock(new GTBlockOreStone("Iridium", 1, 3, 20.0F));
	public static final GTBlockOreStone rubyOre = registerBlock(new GTBlockOreStone("Ruby", 2, 2, 4.0F));
	public static final GTBlockOreStone sapphireOre = registerBlock(new GTBlockOreStone("Sapphire", 3, 2, 4.0F));
	public static final GTBlockOreStone bauxiteOre = registerBlock(new GTBlockOreStone("Bauxite", 4, 1, 3.0F));
	public static final GTBlockOreSand magnetiteOre = registerBlock(new GTBlockOreSand("Magnetite", 5));
	public static final GTBlockOreStone pyriteOre = registerBlock(new GTBlockOreStone("Pyrite", 6, 1, 2.0F));
	public static final GTBlockOreStone cinnabarOre = registerBlock(new GTBlockOreStone("Cinnabar", 7, 2, 3.0F));
	public static final GTBlockOreStone sphaleriteOre = registerBlock(new GTBlockOreStone("Sphalerite", 8, 1, 2.0F));
	public static final GTBlockOreStone tungstateOre = registerBlock(new GTBlockOreStone("Tungstate", 9, 2, 4.0F));
	public static final GTBlockOreStone sheldoniteOre = registerBlock(new GTBlockOreStone("Sheldonite", 10, 3, 3.5F));
	public static final GTBlockOreStone olivineOre = registerBlock(new GTBlockOreStone("Olivine", 11, 3, 3.0F));
	public static final GTBlockOreStone sodaliteOre = registerBlock(new GTBlockOreStone("Sodalite", 12, 2, 3.0F));

	public static final GTBlockTileBasic bloomery = registerBlock(new GTBlockTileBasic("machine_bloomery"));
	public static final GTBlockTileBasic autoCrafter = registerBlock(new GTBlockTileBasic("machine_autocrafter_lv"));
	public static final GTBlockTileBasic chargeOMat = registerBlock(new GTBlockTileBasic("machine_chargeomat_ev"));
	public static final GTBlockTileBasic computerCube = registerBlock(new GTBlockTileBasic("machine_computercube_ev"));
	public static final GTBlockTileBasic industrialCentrifuge = registerBlock(
			new GTBlockTileBasic("machine_industrialcentrifuge_lv"));
	public static final GTBlockTileBasic alloySmelter = registerBlock(new GTBlockTileBasic("machine_alloysmelter_lv"));
	public static final GTBlockTileBasic matterFabricator = registerBlock(
			new GTBlockTileBasic("machine_matterfabricator_ev"));
	public static final GTBlockTileBasic uuMatterAssembler = registerBlock(
			new GTBlockTileBasic("machine_uumassembler_ev"));
	public static final GTBlockTileBasic playerDetector = registerBlock(
			new GTBlockTileBasic("machine_playerdetector_lv"));
	public static final GTBlockTileBasic fusionComputer = registerBlock(
			new GTBlockTileBasic("machine_fusioncomputer_iv"));
	public static final GTBlockTileBasic lightningRod = registerBlock(new GTBlockTileBasic("machine_lightningrod_iv"));
	public static final GTBlockTileBasic quantumEnergyStorage = registerBlock(
			new GTBlockTileBasic("machine_quantumenergystorage_ev"));
	public static final GTBlockTileBasic basicEnergyStorage = registerBlock(
			new GTBlockTileBasic("machine_basicenergystorage_ev"));
	public static final GTBlockTileBasic multiEnergyStorage = registerBlock(
			new GTBlockTileBasic("machine_multienergystorage_mv"));
	public static final GTBlockTileBasic digitalTransformerIV = registerBlock(
			new GTBlockTileBasic("machine_digitaltransformer_iv"));
	public static final GTBlockTileBasic energiumCable = registerBlock(new GTBlockTileBasic("cable_energium_luv"));
	public static final GTBlockTileBasic lapotronCable = registerBlock(new GTBlockTileBasic("cable_lapotron_zpm"));
	public static final GTBlockTileBasic smallChestLV = registerBlock(new GTBlockTileBasic("tile_smallchest_lv"));
	public static final GTBlockTileBasic largeChestLV = registerBlock(new GTBlockTileBasic("tile_largechest_lv"));
	public static final GTBlockTileBasic digitalChestLV = registerBlock(new GTBlockTileBasic("tile_digitalchest_lv"));
	public static final GTBlockTileBasic bookShelfLV = registerBlock(new GTBlockTileBasic("tile_bookshelf_lv"));
	public static final GTBlockTileBasic workBenchLV = registerBlock(new GTBlockTileBasic("tile_workbench_lv"));
	public static final GTBlockTileBasic smallChestMV = registerBlock(new GTBlockTileBasic("tile_smallchest_mv"));
	public static final GTBlockTileBasic largeChestMV = registerBlock(new GTBlockTileBasic("tile_largechest_mv"));
	public static final GTBlockTileBasic digitalChestMV = registerBlock(new GTBlockTileBasic("tile_digitalchest_mv"));
	public static final GTBlockTileBasic bookShelfMV = registerBlock(new GTBlockTileBasic("tile_bookshelf_mv"));
	public static final GTBlockTileBasic workBenchMV = registerBlock(new GTBlockTileBasic("tile_workbench_mv"));

	public static final GTBlockTileCustom Echophone = registerBlock(
			new GTBlockTileCustom("block_echophone", 8, 14, false));

	public static final GTBlockDuctTape DuctTape = registerBlock(new GTBlockDuctTape("block_ducttape", 10, 4, false));

	public static final GTBlockTileCustom smallCoolant = registerBlock(
			new GTBlockTileCustom("coolant_helium_small", 5, 13, false));
	public static final GTBlockTileCustom medCoolant = registerBlock(
			new GTBlockTileCustom("coolant_helium_med", 13, 5, false));
	public static final GTBlockTileCustom largeCoolant = registerBlock(
			new GTBlockTileCustom("coolant_helium_large", 13, 5, false));

	public static final GTBlockTileCustom smallThorium = registerBlock(
			new GTBlockTileCustom("rod_thorium_small", 3, 10, true));
	public static final GTBlockTileCustom medThorium = registerBlock(
			new GTBlockTileCustom("rod_thorium_med", 4, 10, true));
	public static final GTBlockTileCustom largeThorium = registerBlock(
			new GTBlockTileCustom("rod_thorium_large", 5, 10, true));

	public static final GTBlockTileCustom smallPlutonium = registerBlock(
			new GTBlockTileCustom("rod_plutonium_small", 3, 10, true));
	public static final GTBlockTileCustom medPlutonium = registerBlock(
			new GTBlockTileCustom("rod_plutonium_med", 4, 10, true));
	public static final GTBlockTileCustom largePlutonium = registerBlock(
			new GTBlockTileCustom("rod_plutonium_large", 5, 10, true));

	public static final GTBlockBattery smallLithium = registerBlock(
			new GTBlockBattery("battery_lithium_small", 6, 11, false, 100000, 128, 1));
	public static final GTBlockBattery medLithium = registerBlock(
			new GTBlockBattery("battery_lithium_med", 8, 11, false, 200000, 256, 2));
	public static final GTBlockBattery largeLithium = registerBlock(
			new GTBlockBattery("battery_lithium_large", 10, 11, false, 400000, 512, 3));

	public static final GTBlockBattery tinyEnergium = registerBlock(
			new GTBlockBattery("battery_energium_tiny", 6, 6, true, 100000, 256, 2));
	public static final GTBlockBattery smallEnergium = registerBlock(
			new GTBlockBattery("battery_energium_small", 8, 8, true, 1000000, 512, 3));
	public static final GTBlockBattery medEnergium = registerBlock(
			new GTBlockBattery("battery_energium_med", 10, 10, true, 10000000, 1024, 4));
	public static final GTBlockBattery largeEnergium = registerBlock(
			new GTBlockBattery("battery_energium_large", 12, 12, true, 100000000, 4096, 5));
	public static final GTBlockBattery hugeEnergium = registerBlock(
			new GTBlockBattery("battery_energium_huge", 14, 14, true, 1000000000, 8192, 6));

	public static final GTBlockBattery tinyLapotron = registerBlock(
			new GTBlockBattery("battery_lapotron_tiny", 6, 6, true, 1000000, 1024, 3));
	public static final GTBlockBattery smallLapotron = registerBlock(
			new GTBlockBattery("battery_lapotron_small", 8, 8, true, 10000000, 4096, 4));
	public static final GTBlockBattery medLapotron = registerBlock(
			new GTBlockBattery("battery_lapotron_med", 10, 10, true, 100000000, 8192, 5));
	public static final GTBlockBattery largeLapotron = registerBlock(
			new GTBlockBattery("battery_lapotron_large", 12, 12, true, 1000000000, 16384, 6));
	public static final GTBlockBattery hugeLapotron = registerBlock(
			new GTBlockBattery("battery_lapotron_huge", 14, 14, true, Integer.MAX_VALUE, 32768, 7));

	// public static final GTBlockTestLayer testBlock = registerBlock(new
	// GTBlockTestLayer());

	public static final String[] textureTileBasic = { "machine_bloomery", "machine_autocrafter_lv",
			"machine_chargeomat_ev", "machine_computercube_ev", "machine_industrialcentrifuge_lv",
			"machine_alloysmelter_lv", "machine_matterfabricator_ev", "machine_uumassembler_ev",
			"machine_playerdetector_lv", "machine_fusioncomputer_iv", "machine_lightningrod_iv",
			"machine_quantumenergystorage_ev", "machine_basicenergystorage_ev", "machine_multienergystorage_mv",
			"machine_digitaltransformer_iv", "cable_energium_luv", "cable_lapotron_zpm", "tile_smallchest_lv",
			"tile_largechest_lv", "tile_digitalchest_lv", "tile_bookshelf_lv", "tile_workbench_lv",
			"tile_smallchest_mv", "tile_largechest_mv", "tile_digitalchest_mv", "tile_bookshelf_mv",
			"tile_workbench_mv" };

	public static final String[] textureTileCustom = { "block_ducttape", "block_echophone", "coolant_helium_small",
			"coolant_helium_med", "coolant_helium_large", "rod_thorium_small", "rod_thorium_med", "rod_thorium_large",
			"rod_plutonium_small", "rod_plutonium_med", "rod_plutonium_large", "battery_lithium_small",
			"battery_lithium_med", "battery_lithium_large", "battery_lapotron_tiny", "battery_lapotron_small",
			"battery_lapotron_med", "battery_lapotron_large", "battery_lapotron_huge", "battery_energium_tiny",
			"battery_energium_small", "battery_energium_med", "battery_energium_large", "battery_energium_huge", };

	public static void registerBlocks() {
		for (Block block : GTMaterialGen.blockMap.values()) {
			createBlock(block);
		}

		for (Block block : toRegister) {
			createBlock(block);
		}
	}

	static <T extends Block> T registerBlock(T block) {
		toRegister.add(block);
		return block;
	}

	public static void createBlock(Block block) {
		IC2.getInstance().createBlock(block, getItemBlock(block));
	}

	static Class<? extends ItemBlockRare> getItemBlock(Block block) {
		if (block instanceof GTItemBlockInterface) {
			return ((GTItemBlockInterface) block).getCustomItemBlock();
		}
		if (block instanceof GTColorBlockInterface) {
			return GTColorItemBlock.class;
		}
		return GTItemBlockRare.class;
	}

	public static void registerTiles() {

		GameRegistry.registerTileEntity(GTTileBlockCustom.class,
				new ResourceLocation(GTMod.MODID, "tileEntityCustomBlock"));

		GameRegistry.registerTileEntity(GTTileBloomery.class,
				new ResourceLocation(GTMod.MODID, "tileEntityBloomery"));
		GameRegistry.registerTileEntity(GTTileIndustrialCentrifuge.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIndustrialCentrifuge"));
		GameRegistry.registerTileEntity(GTTileAlloySmelter.class,
				new ResourceLocation(GTMod.MODID, "tileEntityAlloySmelter"));
		GameRegistry.registerTileEntity(GTTileComputerCube.class,
				new ResourceLocation(GTMod.MODID, "tileEntityComputerCube"));
		GameRegistry.registerTileEntity(GTTileDigitalTransformer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityDigitalTransformer"));

		GameRegistry.registerTileEntity(GTTileSmallChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySmallChest"));
		GameRegistry.registerTileEntity(GTTileLargeChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLargeChest"));
		GameRegistry.registerTileEntity(GTTileDigitalChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityQuantumChest"));
		GameRegistry.registerTileEntity(GTTileBookshelf.class,
				new ResourceLocation(GTMod.MODID, "tileEntityBookshelf"));
		GameRegistry.registerTileEntity(GTTileWorkbench.class,
				new ResourceLocation(GTMod.MODID, "tileEntityWorkbench"));

		GameRegistry.registerTileEntity(GTTileBasicEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityHESU"));
		GameRegistry.registerTileEntity(GTTileQuantumEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIDSU"));
		GameRegistry.registerTileEntity(GTTileMultiEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLESU"));

		GameRegistry.registerTileEntity(GTTileLightningRod.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLightningRod"));
		GameRegistry.registerTileEntity(GTTileFusionComputer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityFusionComputer"));
		GameRegistry.registerTileEntity(GTTileSuperConductorLow.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySuperConductorLow"));
		GameRegistry.registerTileEntity(GTTileSuperConductorHigh.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySuperConductorHigh"));
	}
}