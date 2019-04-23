package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockCustom;
import gtclassic.block.GTBlockFalling;
import gtclassic.block.GTBlockGlass;
import gtclassic.block.GTBlockMortar;
import gtclassic.block.GTBlockSluice;
import gtclassic.block.GTBlockSluiceBoxExt;
import gtclassic.block.GTBlockStone;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileCustom;
import gtclassic.block.GTBlockTileStorage;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItemBlock;
import gtclassic.fluid.GTFluidBlockSlurry;
import gtclassic.itemblock.GTBlockBattery;
import gtclassic.itemblock.GTBlockDuctTape;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.itemblock.GTItemBlockRare;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.ore.GTOreFalling;
import gtclassic.ore.GTOreFlag;
import gtclassic.ore.GTOreRegistry;
import gtclassic.ore.GTOreStone;
import gtclassic.tile.GTTileBasicEnergyStorage;
import gtclassic.tile.GTTileBlockCustom;
import gtclassic.tile.GTTileBookshelf;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileDigitalChest;
import gtclassic.tile.GTTileDigitalTransformer;
import gtclassic.tile.GTTileElectricSmelter;
import gtclassic.tile.GTTileFacing;
import gtclassic.tile.GTTileHeatingElement;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileIndustrialElectrolyzer;
import gtclassic.tile.GTTileLargeChest;
import gtclassic.tile.GTTileMultiArcFurnace;
import gtclassic.tile.GTTileMultiBlastFurnace;
import gtclassic.tile.GTTileMultiBloomery;
import gtclassic.tile.GTTileMultiCharcoalPit;
import gtclassic.tile.GTTileMultiFusionComputer;
import gtclassic.tile.GTTileMultiIndustrialProcessor;
import gtclassic.tile.GTTileMultiLightningRod;
import gtclassic.tile.GTTilePlayerDetector;
import gtclassic.tile.GTTileQuantumEnergyStorage;
import gtclassic.tile.GTTileSluice;
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

	public static final GTBlockStone bloomBlock = registerBlock(new GTBlockStone("Bloom", 16, 1.0F, 0));
	public static final GTBlockFalling slagSand = registerBlock(new GTBlockFalling("Slag", 17));
	public static final GTBlockFalling slagcreteSand = registerBlock(new GTBlockFalling("Slagcrete", 18));
	public static final GTBlockGlass slagGlass = registerBlock(new GTBlockGlass("Slag", 19));
	public static final GTBlockFalling charcoalPile = registerBlock(new GTBlockFalling("Charcoal", 20));

	public static final GTBlockCasing superCasingBlock = registerBlock(new GTBlockCasing("Superconductor", 0));
	public static final GTBlockCasing fusionCasingBlock = registerBlock(new GTBlockCasing("Fusion", 1));
	public static final GTBlockCasing fissionCasingBlock = registerBlock(new GTBlockCasing("Fission", 2));

	public static final GTBlockCasing plastic1CasingBlock = registerBlock(new GTBlockCasing("Plastic1", 3));
	public static final GTBlockCasing plastic4CasingBlock = registerBlock(new GTBlockCasing("Plastic4", 4));
	public static final GTBlockCasing plastic16CasingBlock = registerBlock(new GTBlockCasing("Plastic16", 5));

	public static final GTBlockCasing heatCasingBlock = registerBlock(new GTBlockCasing("Heat", 6));

	public static final GTBlockCasing copperCoilBlock = registerBlock(new GTBlockCasing("CopperCoil", 12));
	public static final GTBlockCasing constantanCoilBlock = registerBlock(new GTBlockCasing("ConstantanCoil", 13));
	public static final GTBlockCasing graphiteCoilBlock = registerBlock(new GTBlockCasing("GraphiteCoil", 14));
	public static final GTBlockCasing nichromeCoilBlock = registerBlock(new GTBlockCasing("NichromeCoil", 15));

	// public static final GTBlockTileBuffer buffer = registerBlock(new
	// GTBlockTileBuffer());
	public static final GTBlockSluice sluiceBox = registerBlock(new GTBlockSluice());
	public static final GTBlockSluiceBoxExt sluiceBoxExtension = registerBlock(new GTBlockSluiceBoxExt());
	public static final GTBlockTileBasic heatingElement = registerBlock(new GTBlockTileBasic("machine_heatingelement"));
	public static final GTBlockTileBasic bloomery = registerBlock(new GTBlockTileBasic("machine_bloomery", 5));
	public static final GTBlockTileBasic charcoalPit = registerBlock(new GTBlockTileBasic("machine_charcoalpit", 4));
	public static final GTBlockTileBasic blastFurnace = registerBlock(
			new GTBlockTileBasic("machine_blastfurnace_lv", 3));
	public static final GTBlockTileBasic chargeOMat = registerBlock(new GTBlockTileBasic("machine_chargeomat_ev"));
	public static final GTBlockTileBasic computerCube = registerBlock(new GTBlockTileBasic("machine_computercube_ev"));
	public static final GTBlockTileBasic industrialCentrifuge = registerBlock(
			new GTBlockTileBasic("machine_industrialcentrifuge_lv"));
	public static final GTBlockTileBasic electricSmelter = registerBlock(
			new GTBlockTileBasic("machine_electricsmelter_lv"));
	public static final GTBlockTileBasic industrialProcessor = registerBlock(
			new GTBlockTileBasic("machine_industrialprocessor_mv", 3));
	public static final GTBlockTileBasic industrialElectrolyzer = registerBlock(
			new GTBlockTileBasic("machine_industrialelectrolyzer_mv", 1));
	public static final GTBlockTileBasic arcFurnace = registerBlock(new GTBlockTileBasic("machine_arcfurnace_hv"));
	public static final GTBlockTileBasic matterFabricator = registerBlock(
			new GTBlockTileBasic("machine_matterfabricator_ev"));
	public static final GTBlockTileBasic matterReplicator = registerBlock(
			new GTBlockTileBasic("machine_matterreplicator_ev"));
	public static final GTBlockTileBasic digitalChest = registerBlock(new GTBlockTileBasic("tile_digitalchest"));
	public static final GTBlockTileBasic playerDetector = registerBlock(
			new GTBlockTileBasic("machine_playerdetector_lv", 1));
	public static final GTBlockTileBasic fusionComputer = registerBlock(
			new GTBlockTileBasic("machine_fusioncomputer_luv"));
	public static final GTBlockTileBasic lightningRod = registerBlock(new GTBlockTileBasic("machine_lightningrod_iv"));
	public static final GTBlockTileBasic quantumEnergyStorage = registerBlock(
			new GTBlockTileBasic("machine_quantumenergystorage_ev"));
	public static final GTBlockTileBasic basicEnergyStorage = registerBlock(
			new GTBlockTileBasic("machine_basicenergystorage_ev"));
	public static final GTBlockTileBasic digitalTransformerIV = registerBlock(
			new GTBlockTileBasic("machine_digitaltransformer_luv"));
	public static final GTBlockTileBasic energiumCable = registerBlock(new GTBlockTileBasic("cable_energium_luv", 1));
	public static final GTBlockTileBasic lapotronCable = registerBlock(new GTBlockTileBasic("cable_lapotron_zpm"));

	public static final GTBlockCustom resinBoard = registerBlock(new GTBlockCustom("Resin", 0, 12, 1));
	public static final GTBlockCustom mudBlock = registerBlock(new GTBlockCustom("Mud", 21, 16, 2));

	public static final GTBlockMortar mortar = registerBlock(new GTBlockMortar());

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

	public static final GTFluidBlockSlurry slurryBlock = registerBlock(new GTFluidBlockSlurry());

	// public static final GTBlockTestLayer testBlock = registerBlock(new
	// GTBlockTestLayer());

	public static final String[] textureTileBasic = { "machine_heatingelement", "machine_bloomery",
			"machine_charcoalpit", "machine_blastfurnace_lv", "machine_arcfurnace_hv", "machine_chargeomat_ev",
			"machine_computercube_ev", "machine_industrialcentrifuge_lv", "machine_industrialelectrolyzer_mv",
			"machine_industrialprocessor_mv", "machine_electricsmelter_lv", "machine_matterfabricator_ev",
			"machine_matterreplicator_ev", "machine_playerdetector_lv", "machine_fusioncomputer_luv",
			"machine_lightningrod_iv", "machine_quantumenergystorage_ev", "machine_basicenergystorage_ev",
			"machine_digitaltransformer_luv", "cable_energium_luv", "cable_lapotron_zpm", "tile_digitalchest",
			"tile_smallchest", "tile_largechest", "tile_bookshelf", "tile_workbench" };

	public static final String[] textureTileCustom = { "block_mortar", "block_ducttape", "coolant_helium_small",
			"coolant_helium_med", "coolant_helium_large", "rod_thorium_small", "rod_thorium_med", "rod_thorium_large",
			"rod_plutonium_small", "rod_plutonium_med", "rod_plutonium_large", "battery_lithium_small",
			"battery_lithium_med", "battery_lithium_large", "battery_lapotron_tiny", "battery_lapotron_small",
			"battery_lapotron_med", "battery_lapotron_large", "battery_lapotron_huge", "battery_energium_tiny",
			"battery_energium_small", "battery_energium_med", "battery_energium_large", "battery_energium_huge", };

	public static void registerBlocks() {
		for (Block block : GTMaterialGen.blockMap.values()) {
			createBlock(block);
		}

		registerStorageBlocks();
		registerOreBlocks();

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

	public static void registerOreBlocks() {
		for (GTOreRegistry ore : GTOreRegistry.values()) {
			createBlock(new GTOreStone(ore, GTOreFlag.STONE));
		}
		for (GTOreRegistry ore : GTOreRegistry.values()) {
			createBlock(new GTOreStone(ore, GTOreFlag.NETHER));
		}
		for (GTOreRegistry ore : GTOreRegistry.values()) {
			createBlock(new GTOreStone(ore, GTOreFlag.END));
		}
		for (GTOreRegistry ore : GTOreRegistry.values()) {
			createBlock(new GTOreFalling(ore, GTOreFlag.SAND));
		}
		for (GTOreRegistry ore : GTOreRegistry.values()) {
			createBlock(new GTOreFalling(ore, GTOreFlag.GRAVEL));
		}
		for (GTOreRegistry ore : GTOreRegistry.values()) {
			createBlock(new GTOreStone(ore, GTOreFlag.BEDROCK));
		}
	}

	public static void registerStorageBlocks() {
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.CASING) && !mat.equals(mat.Copper) && !mat.equals(mat.Tin)
					&& !mat.equals(mat.Zinc)) {
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


		GameRegistry.registerTileEntity(GTTileSluice.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySluice"));
		GameRegistry.registerTileEntity(GTTileHeatingElement.class,
				new ResourceLocation(GTMod.MODID, "tileEntityHeatingElement"));
		GameRegistry.registerTileEntity(GTTileMultiBloomery.class,
				new ResourceLocation(GTMod.MODID, "tileEntityBloomery"));
		GameRegistry.registerTileEntity(GTTileMultiCharcoalPit.class,
				new ResourceLocation(GTMod.MODID, "tileEntityCharcoalPit"));
		GameRegistry.registerTileEntity(GTTileIndustrialCentrifuge.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIndustrialCentrifuge"));
		GameRegistry.registerTileEntity(GTTileMultiIndustrialProcessor.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIndustrialProcessor"));
		GameRegistry.registerTileEntity(GTTileIndustrialElectrolyzer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIndustrialElectrolyzer"));
		GameRegistry.registerTileEntity(GTTileElectricSmelter.class,
				new ResourceLocation(GTMod.MODID, "tileEntityElectricSmelter"));
		GameRegistry.registerTileEntity(GTTilePlayerDetector.class,
				new ResourceLocation(GTMod.MODID, "tileEntityPlayerDetector"));
		GameRegistry.registerTileEntity(GTTileMultiArcFurnace.class,
				new ResourceLocation(GTMod.MODID, "tileEntityArcFurnace"));
		GameRegistry.registerTileEntity(GTTileMultiBlastFurnace.class,
				new ResourceLocation(GTMod.MODID, "tileEntityBlastFurnace"));
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
				new ResourceLocation(GTMod.MODID, "tileEntityBasicEnergy"));
		GameRegistry.registerTileEntity(GTTileQuantumEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityQuantumEnergy"));

		GameRegistry.registerTileEntity(GTTileMultiLightningRod.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLightningRod"));
		GameRegistry.registerTileEntity(GTTileMultiFusionComputer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityFusionComputer"));
		GameRegistry.registerTileEntity(GTTileSuperConductorLow.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySuperConductorLow"));
		GameRegistry.registerTileEntity(GTTileSuperConductorHigh.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySuperConductorHigh"));

		GameRegistry.registerTileEntity(GTTileFacing.class, new ResourceLocation(GTMod.MODID, "tileEntityFacing"));
	}
}