package gtclassic;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockOreSand;
import gtclassic.block.GTBlockOreStone;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileCustom;
import gtclassic.block.test.GTBlockTestLayer;
import gtclassic.material.GTMaterialGen;
import gtclassic.tileentity.GTTileEntityBasicEnergyStorage;
import gtclassic.tileentity.GTTileEntityBookshelf;
import gtclassic.tileentity.GTTileEntityComputerCube;
import gtclassic.tileentity.GTTileEntityDigitalChest;
import gtclassic.tileentity.GTTileEntityDigitalTransformer;
import gtclassic.tileentity.GTTileEntityFusionComputer;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.tileentity.GTTileEntityLargeChest;
import gtclassic.tileentity.GTTileEntityLightningRod;
import gtclassic.tileentity.GTTileEntityMultiEnergyStorage;
import gtclassic.tileentity.GTTileEntityQuantumEnergyStorage;
import gtclassic.tileentity.GTTileEntitySmallChest;
import gtclassic.tileentity.GTTileEntitySuperConductor;
import gtclassic.tileentity.GTTileEntityWorkbench;
import gtclassic.util.color.GTColorItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class GTBlocks {

	public static final GTBlockCasing superCasingBlock = new GTBlockCasing("Superconductor", 0),
			fusionCasingBlock = new GTBlockCasing("Fusion", 1),
			fissionCasingBlock = new GTBlockCasing("Fission", 2),
			crystalCasingBlock = new GTBlockCasing("Crystal", 3);

	public static final GTBlockOreStone galenaOre = new GTBlockOreStone("Galena", 0, 1, 3.0F),
			iridiumOre = new GTBlockOreStone("Iridium", 1, 3, 20.0F),
			rubyOre = new GTBlockOreStone("Ruby", 2, 2, 4.0F),
			sapphireOre = new GTBlockOreStone("Sapphire", 3, 2, 4.0F),
			bauxiteOre = new GTBlockOreStone("Bauxite", 4, 1, 3.0F),
			pyriteOre = new GTBlockOreStone("Pyrite", 6, 1, 2.0F),
			cinnabarOre = new GTBlockOreStone("Cinnabar", 7, 2, 3.0F),
			sphaleriteOre = new GTBlockOreStone("Sphalerite", 8, 1, 2.0F),
			tungstateOre = new GTBlockOreStone("Tungstate", 9, 2, 4.0F),
			sheldoniteOre = new GTBlockOreStone("Sheldonite", 10, 3, 3.5F),
			olivineOre = new GTBlockOreStone("Olivine", 11, 3, 3.0F),
			sodaliteOre = new GTBlockOreStone("Sodalite", 12, 2, 3.0F);

	public static final GTBlockOreSand sandIron = new GTBlockOreSand("Iron", 5);

	public static final String[] textureTileBasic = {
			"machine_autocrafter_lv",
			"machine_chargeomat_ev",
			"machine_computercube_ev",
			"machine_industrialcentrifuge_lv",
			"machine_matterfabricator_ev",
			"machine_uumassembler_ev",
			"machine_playerdetector_lv",
			"machine_echophone_lv",
			"machine_fusioncomputer_iv",
			"machine_lightningrod_iv",
			"machine_quantumenergystorage_ev",
			"machine_basicenergystorage_ev",
			"machine_multienergystorage_mv",
			"machine_digitaltransformer_iv",
			"wire_energium_luv",
			"wire_lapotron_zpm",
			"tile_smallchest_lv",
			"tile_largechest_lv",
			"tile_digitalchest_lv",
			"tile_bookshelf_lv",
			"tile_workbench_lv",
			"tile_smallchest_mv",
			"tile_largechest_mv",
			"tile_digitalchest_mv",
			"tile_bookshelf_mv",
			"tile_workbench_mv"
	};

	public static final GTBlockTileBasic autoCrafter = new GTBlockTileBasic("machine_autocrafter_lv"),
			chargeOMat = new GTBlockTileBasic("machine_chargeomat_ev"),
			computerCube = new GTBlockTileBasic("machine_computercube_ev"),
			industrialCentrifuge = new GTBlockTileBasic("machine_industrialcentrifuge_lv"),
			matterFabricator = new GTBlockTileBasic("machine_matterfabricator_ev"),
			uuMatterAssembler = new GTBlockTileBasic("machine_uumassembler_ev"),
			playerDetector = new GTBlockTileBasic("machine_playerdetector_lv"),
			echophoneBlock = new GTBlockTileBasic("machine_echophone_lv"),
			fusionComputer = new GTBlockTileBasic("machine_fusioncomputer_iv"),
			lightningRod = new GTBlockTileBasic("machine_lightningrod_iv"),
			IDSU = new GTBlockTileBasic("machine_quantumenergystorage_ev"),
			HESU = new GTBlockTileBasic("machine_basicenergystorage_ev"),
			LESU = new GTBlockTileBasic("machine_multienergystorage_mv"),
			digitalTransformerIV = new GTBlockTileBasic("machine_digitaltransformer_iv"),
			energiumWire = new GTBlockTileBasic("wire_energium_luv"),
			lapotronWire = new GTBlockTileBasic("wire_lapotron_zpm"),
			smallChestLV = new GTBlockTileBasic("tile_smallchest_lv"),
			largeChestLV = new GTBlockTileBasic("tile_largechest_lv"),
			digitalChestLV = new GTBlockTileBasic("tile_digitalchest_lv"),
			bookShelfLV = new GTBlockTileBasic("tile_bookshelf_lv"),
			workBenchLV = new GTBlockTileBasic("tile_workbench_lv"),
			smallChestMV = new GTBlockTileBasic("tile_smallchest_mv"),
			largeChestMV = new GTBlockTileBasic("tile_largechest_mv"),
			digitalChestMV = new GTBlockTileBasic("tile_digitalchest_mv"),
			bookShelfMV = new GTBlockTileBasic("tile_bookshelf_mv"),
			workBenchMV = new GTBlockTileBasic("tile_workbench_mv");

	public static final String[] textureTileCustom = {
			"coolant_helium_small",
			"coolant_helium_med",
			"coolant_helium_large",
			"rod_thorium_small",
			"rod_thorium_med",
			"rod_thorium_large",
			"rod_plutonium_small",
			"rod_plutonium_med",
			"rod_plutonium_large",
			"battery_lithium_small",
			"battery_lithium_med",
			"battery_lithium_large",
			"battery_lapotron_tiny",
			"battery_lapotron_small",
			"battery_lapotron_med",
			"battery_lapotron_large",
			"battery_lapotron_huge",
			"battery_energium_tiny",
			"battery_energium_small",
			"battery_energium_med",
			"battery_energium_large",
			"battery_energium_huge",
	};

	public static final GTBlockTileCustom smallCoolant = new GTBlockTileCustom("coolant_helium_small", 5, 13, false),
			medCoolant = new GTBlockTileCustom("coolant_helium_med", 13, 2, false),
			largeCoolant = new GTBlockTileCustom("coolant_helium_large", 13, 2, false),

			smallThorium = new GTBlockTileCustom("rod_thorium_small", 3, 10, true),
			medThorium = new GTBlockTileCustom("rod_thorium_med", 4, 10, true),
			largeThorium = new GTBlockTileCustom("rod_thorium_large", 5, 10, true),

			smallPlutonium = new GTBlockTileCustom("rod_plutonium_small", 3, 10, true),
			medPlutonium = new GTBlockTileCustom("rod_plutonium_med", 4, 10, true),
			largePlutonium = new GTBlockTileCustom("rod_plutonium_large", 5, 10, true),

			smallLithium = new GTBlockTileCustom("battery_lithium_small", 6, 11, false),
			medLithium = new GTBlockTileCustom("battery_lithium_med", 8, 11, false),
			largeLithium = new GTBlockTileCustom("battery_lithium_large", 10, 11, false),

			tinyLapotron = new GTBlockTileCustom("battery_lapotron_tiny", 6, 6, true),
			smallLapotron = new GTBlockTileCustom("battery_lapotron_small", 8, 8, true),
			medLapotron = new GTBlockTileCustom("battery_lapotron_med", 10, 10, true),
			largeLapotron = new GTBlockTileCustom("battery_lapotron_large", 12, 12, true),
			hugeLapotron = new GTBlockTileCustom("battery_lapotron_huge", 14, 14, true),

			tinyEnergium = new GTBlockTileCustom("battery_energium_tiny", 6, 6, true),
			smallEnergium = new GTBlockTileCustom("battery_energium_small", 8, 8, true),
			medEnergium = new GTBlockTileCustom("battery_energium_med", 10, 10, true),
			largeEnergium = new GTBlockTileCustom("battery_energium_large", 12, 12, true),
			hugeEnergium = new GTBlockTileCustom("battery_energium_huge", 14, 14, true);

	public static final GTBlockTestLayer testBlock = new GTBlockTestLayer();

	public static final Block[] blocks = {

			superCasingBlock,
			fusionCasingBlock,
			fissionCasingBlock,
			crystalCasingBlock,

			galenaOre,
			iridiumOre,
			rubyOre,
			sapphireOre,
			bauxiteOre,
			sandIron,
			pyriteOre,
			cinnabarOre,
			sphaleriteOre,
			tungstateOre,
			sheldoniteOre,
			olivineOre,
			sodaliteOre,

			autoCrafter,
			chargeOMat,
			computerCube,
			industrialCentrifuge,
			matterFabricator,
			uuMatterAssembler,
			playerDetector,
			echophoneBlock,
			fusionComputer,
			lightningRod,

			IDSU,
			HESU,
			LESU,
			digitalTransformerIV,
			energiumWire,
			lapotronWire,

			digitalChestLV, bookShelfLV, workBenchLV, smallChestLV, largeChestLV,
			digitalChestMV, bookShelfMV, workBenchMV, smallChestMV, largeChestMV,

			smallCoolant, medCoolant, largeCoolant,

			smallThorium, medThorium, largeThorium, smallPlutonium, medPlutonium, largePlutonium,

			smallLithium, medLithium, largeLithium,
			tinyLapotron, smallLapotron, medLapotron, largeLapotron, hugeLapotron,

			tinyEnergium, smallEnergium, medEnergium, largeEnergium, hugeEnergium,

			testBlock,

	};

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		GTMod.logger.info("Registering Blocks");

		// Registers materials with block based material flags from material block map
		for (Block block : GTMaterialGen.blockMap.values()) {
			registry.register(block);
		}

		// Registers the static block references in this class
		for (Block block : blocks) {
			registry.register(block);
		}
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		// Registers materials with block based material flags from material block map
		// as items
		for (Block block : GTMaterialGen.blockMap.values()) {
			registry.register(new GTColorItemBlock(block).setRegistryName(block.getRegistryName())
					.setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(GTMod.creativeTabGT));
		}

		// Registers the static block references in this class as items
		for (Block block : blocks) {
			registry.register(new GTColorItemBlock(block).setRegistryName(block.getRegistryName())
					.setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(GTMod.creativeTabGT));
		}
	}

	public static void registerTiles() {
		GameRegistry.registerTileEntity(GTTileEntityIndustrialCentrifuge.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIndustrialCentrifuge"));
		GameRegistry.registerTileEntity(GTTileEntityComputerCube.class,
				new ResourceLocation(GTMod.MODID, "tileEntityComputerCube"));
		GameRegistry.registerTileEntity(GTTileEntityDigitalTransformer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityDigitalTransformer"));

		GameRegistry.registerTileEntity(GTTileEntitySmallChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySmallChest"));
		GameRegistry.registerTileEntity(GTTileEntityLargeChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLargeChest"));
		GameRegistry.registerTileEntity(GTTileEntityDigitalChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityQuantumChest"));
		GameRegistry.registerTileEntity(GTTileEntityBookshelf.class,
				new ResourceLocation(GTMod.MODID, "tileEntityBookshelf"));
		GameRegistry.registerTileEntity(GTTileEntityWorkbench.class,
				new ResourceLocation(GTMod.MODID, "tileEntityWorkbench"));

		GameRegistry.registerTileEntity(GTTileEntityBasicEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityHESU"));
		GameRegistry.registerTileEntity(GTTileEntityQuantumEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIDSU"));
		GameRegistry.registerTileEntity(GTTileEntityMultiEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLESU"));

		GameRegistry.registerTileEntity(GTTileEntityLightningRod.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLightningRod"));
		GameRegistry.registerTileEntity(GTTileEntityFusionComputer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityFusionComputer"));
		GameRegistry.registerTileEntity(GTTileEntitySuperConductor.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySuperConductor"));
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		// overloads deprecated method to properly register tiles
		GameRegistry.registerTileEntity(tileEntityClass, GTMod.MODID + ":" + name);
	}
}