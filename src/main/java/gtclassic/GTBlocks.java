package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.block.GTBlock;
import gtclassic.block.GTBlockBattery;
import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockDuctTape;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockSand;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileCustom;
import gtclassic.block.GTBlockTileStorage;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItemBlock;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.itemblock.GTItemBlockRare;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileArcFurnace;
import gtclassic.tile.GTTileAssemblyLine;
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
import gtclassic.tile.GTTileResinBoard;
import gtclassic.tile.GTTileResinChunk;
import gtclassic.tile.GTTileSmallChest;
import gtclassic.tile.GTTileSuperConductorHigh;
import gtclassic.tile.GTTileSuperConductorLow;
import gtclassic.tile.GTTileWorkbench;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTBlocks {

	static final List<Block> toRegister = new ArrayList<Block>();
	public static final GTBlockOre galenaOre = registerBlock(new GTBlockOre("Galena", 0, 1, 3.0F));
	public static final GTBlockOre iridiumOre = registerBlock(new GTBlockOre("Iridium", 1, 3, 20.0F));
	public static final GTBlockOre rubyOre = registerBlock(new GTBlockOre("Ruby", 2, 2, 4.0F));
	public static final GTBlockOre sapphireOre = registerBlock(new GTBlockOre("Sapphire", 3, 2, 4.0F));
	public static final GTBlockOre germaniteOre = registerBlock(new GTBlockOre("Germanite", 4, 1, 3.0F));
	public static final GTBlockOre calciteOre = registerBlock(new GTBlockOre("Calcite", 5, 1, 3.0F));
	public static final GTBlockOre bauxiteOre = registerBlock(new GTBlockOre("Bauxite", 6, 1, 3.0F));
	public static final GTBlockSand magnetiteSand = registerBlock(new GTBlockSand("Magnetite", 8));
	public static final GTBlockOre pyriteOre = registerBlock(new GTBlockOre("Pyrite", 9, 1, 2.0F));
	public static final GTBlockOre cinnabarOre = registerBlock(new GTBlockOre("Cinnabar", 10, 2, 3.0F));
	public static final GTBlockOre sphaleriteOre = registerBlock(new GTBlockOre("Sphalerite", 11, 1, 2.0F));
	public static final GTBlockOre tungstateOre = registerBlock(new GTBlockOre("Tungstate", 12, 2, 4.0F));
	public static final GTBlockOre sheldoniteOre = registerBlock(new GTBlockOre("Sheldonite", 13, 3, 3.5F));
	public static final GTBlockOre olivineOre = registerBlock(new GTBlockOre("Olivine", 14, 3, 3.0F));
	public static final GTBlockOre sodaliteOre = registerBlock(new GTBlockOre("Sodalite", 15, 2, 3.0F));

	public static final GTBlock bloomBlock = registerBlock(
			new GTBlock(Material.ROCK, "Bloom", 16, 1.0F, "pickaxe", 0, SoundType.STONE));
	public static final GTBlockSand slagSand = registerBlock(new GTBlockSand("Slag", 17));
	public static final GTBlockSand slagcreteSand = registerBlock(new GTBlockSand("Slagcrete", 18));
	public static final GTBlock slagGlass = registerBlock(
			new GTBlock(Material.GLASS, "SlagGlass", 19, 2.0F, "pickaxe", 0, SoundType.GLASS));

	public static final GTBlockCasing superCasingBlock = registerBlock(new GTBlockCasing("Superconductor", 0));
	public static final GTBlockCasing fusionCasingBlock = registerBlock(new GTBlockCasing("Fusion", 1));
	public static final GTBlockCasing fissionCasingBlock = registerBlock(new GTBlockCasing("Fission", 2));
	public static final GTBlockCasing crystalCasingBlock = registerBlock(new GTBlockCasing("Crystal", 3));
	public static final GTBlockCasing grateCasingBlock = registerBlock(new GTBlockCasing("Grate", 4));
	public static final GTBlockCasing heatCasingBlock = registerBlock(new GTBlockCasing("Heat", 5));
	public static final GTBlockCasing plasticCasingBlock = registerBlock(new GTBlockCasing("Plastic", 6));
	public static final GTBlockCasing structureCasingBlock = registerBlock(new GTBlockCasing("Structure", 7));

	public static final GTBlockCasing constantanCoilBlock = registerBlock(new GTBlockCasing("Constantan", 13));
	public static final GTBlockCasing graphiteCoilBlock = registerBlock(new GTBlockCasing("Graphite", 14));
	public static final GTBlockCasing nichromeCoilBlock = registerBlock(new GTBlockCasing("Nichrome", 15));

	public static final GTBlockTileBasic bloomery = registerBlock(new GTBlockTileBasic("machine_bloomery"));
	public static final GTBlockTileBasic chargeOMat = registerBlock(new GTBlockTileBasic("machine_chargeomat_ev"));
	public static final GTBlockTileBasic computerCube = registerBlock(new GTBlockTileBasic("machine_computercube_ev"));
	public static final GTBlockTileBasic industrialCentrifuge = registerBlock(
			new GTBlockTileBasic("machine_industrialcentrifuge_lv"));
	public static final GTBlockTileBasic alloySmelter = registerBlock(new GTBlockTileBasic("machine_alloysmelter_lv"));
	public static final GTBlockTileBasic assLine = registerBlock(new GTBlockTileBasic("machine_assemblyline_lv"));
	public static final GTBlockTileBasic arcFurnace = registerBlock(new GTBlockTileBasic("machine_arcfurnace_hv"));
	public static final GTBlockTileBasic matterFabricator = registerBlock(
			new GTBlockTileBasic("machine_matterfabricator_ev"));
	public static final GTBlockTileBasic matterReplicator = registerBlock(
			new GTBlockTileBasic("machine_matterreplicator_ev"));
	public static final GTBlockTileBasic digitalChest = registerBlock(new GTBlockTileBasic("tile_digitalchest"));
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

	public static final GTBlockTileCustom resinBottle = registerBlock(
			new GTBlockTileCustom("tile_resinbottle", 4, 8, false));
	public static final GTBlockTileCustom resinChunk = registerBlock(
			new GTBlockTileCustom("tile_resinchunk", 4, 3, false));
	public static final GTBlockTileCustom resinBoard = registerBlock(
			new GTBlockTileCustom("tile_resinboard", 12, 1, false));
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

	public static final String[] textureTileBasic = { "machine_bloomery", "machine_assemblyline_lv",
			"machine_arcfurnace_hv", "machine_chargeomat_ev", "machine_computercube_ev",
			"machine_industrialcentrifuge_lv", "machine_alloysmelter_lv", "machine_matterfabricator_ev",
			"machine_matterreplicator_ev", "machine_playerdetector_lv", "machine_fusioncomputer_iv",
			"machine_lightningrod_iv", "machine_quantumenergystorage_ev", "machine_basicenergystorage_ev",
			"machine_multienergystorage_mv", "machine_digitaltransformer_iv", "cable_energium_luv",
			"cable_lapotron_zpm", "tile_digitalchest", "tile_smallchest", "tile_largechest", "tile_bookshelf",
			"tile_workbench" };

	public static final String[] textureTileCustom = { "tile_resinbottle","tile_resinchunk", "tile_resinboard", "block_ducttape",
			"block_echophone", "coolant_helium_small", "coolant_helium_med", "coolant_helium_large",
			"rod_thorium_small", "rod_thorium_med", "rod_thorium_large", "rod_plutonium_small", "rod_plutonium_med",
			"rod_plutonium_large", "battery_lithium_small", "battery_lithium_med", "battery_lithium_large",
			"battery_lapotron_tiny", "battery_lapotron_small", "battery_lapotron_med", "battery_lapotron_large",
			"battery_lapotron_huge", "battery_energium_tiny", "battery_energium_small", "battery_energium_med",
			"battery_energium_large", "battery_energium_huge", };

	public static void registerBlocks() {
		for (Block block : GTMaterialGen.blockMap.values()) {
			createBlock(block);
		}

		registerStorageBlocks();

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

	public static void registerStorageBlocks() {
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.CASING) && !mat.equals(mat.Copper) && !mat.equals(mat.Tin)) {
				createBlock(new GTBlockTileStorage(mat, 0));
				createBlock(new GTBlockTileStorage(mat, 1));
				createBlock(new GTBlockTileStorage(mat, 2));
				createBlock(new GTBlockTileStorage(mat, 3));
			}
		}
	}

	public static void registerTiles() {

		GameRegistry.registerTileEntity(GTTileBlockCustom.class,
				new ResourceLocation(GTMod.MODID, "tileEntityCustomBlock"));
		GameRegistry.registerTileEntity(GTTileResinChunk.class,
				new ResourceLocation(GTMod.MODID, "tileEntityResinChunk"));
		GameRegistry.registerTileEntity(GTTileResinBoard.class,
				new ResourceLocation(GTMod.MODID, "tileEntityResinBoard"));

		GameRegistry.registerTileEntity(GTTileBloomery.class, new ResourceLocation(GTMod.MODID, "tileEntityBloomery"));
		GameRegistry.registerTileEntity(GTTileIndustrialCentrifuge.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIndustrialCentrifuge"));
		GameRegistry.registerTileEntity(GTTileAlloySmelter.class,
				new ResourceLocation(GTMod.MODID, "tileEntityAlloySmelter"));
		GameRegistry.registerTileEntity(GTTileAssemblyLine.class,
				new ResourceLocation(GTMod.MODID, "tileEntityAssemblyArray"));
		GameRegistry.registerTileEntity(GTTileArcFurnace.class,
				new ResourceLocation(GTMod.MODID, "tileEntityArcFurnace"));
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