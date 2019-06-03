package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockDrum;
import gtclassic.block.GTBlockSluice;
import gtclassic.block.GTBlockSluiceBoxExt;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItemBlock;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.itemblock.GTItemBlockRare;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileBasicEnergyStorage;
import gtclassic.tile.GTTileBlockCustom;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileDigitalChest;
import gtclassic.tile.GTTileDrum;
import gtclassic.tile.GTTileFacing;
import gtclassic.tile.GTTilePlayerDetector;
import gtclassic.tile.GTTileQuantumEnergyStorage;
import gtclassic.tile.GTTileSluice;
import gtclassic.tile.GTTileWorkbench;
import gtclassic.tile.multi.GTTileMultiFusion;
import gtclassic.tile.multi.GTTileMultiLightningRod;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTBlocks {

	private GTBlocks() {
		throw new IllegalStateException("Utility class");
	}

	static final List<Block> toRegister = new ArrayList<>();
	
	public static final GTBlockCasing casingSuperConductor = registerBlock(new GTBlockCasing("Superconductor", 0)),
			casingFusion = registerBlock(new GTBlockCasing("Fusion", 1)),
			casingFission = registerBlock(new GTBlockCasing("Fission", 2)),
			casingPlastic1x = registerBlock(new GTBlockCasing("Plastic1", 3)),
			casingPlastic4x = registerBlock(new GTBlockCasing("Plastic4", 4)),
			casingPlastic16x = registerBlock(new GTBlockCasing("Plastic16", 5)),
			casingLightning = registerBlock(new GTBlockCasing("Lightning", 8));
	public static final GTBlockSluice tileSluice = registerBlock(new GTBlockSluice());
	public static final GTBlockSluiceBoxExt tileSluiceExt = registerBlock(new GTBlockSluiceBoxExt());
	public static final GTBlockTileBasic 
			tileChargeOmat = registerBlock(new GTBlockTileBasic("machine_chargeomat_ev")),
			tileComputer = registerBlock(new GTBlockTileBasic("machine_computercube_ev")),
			tileCentrifuge = registerBlock(new GTBlockTileBasic("machine_industrialcentrifuge_lv")),
			tileFabricator = registerBlock(new GTBlockTileBasic("machine_matterfabricator_ev")),
			tileReplicator = registerBlock(new GTBlockTileBasic("machine_matterreplicator_ev")),
			tileDigitalChest = registerBlock(new GTBlockTileBasic("tile_digitalchest")),
			tilePlayerDetector = registerBlock(new GTBlockTileBasic("machine_playerdetector_lv", 1)),
			tileFusion = registerBlock(new GTBlockTileBasic("machine_fusioncomputer_ev")),
			tileLightningRod = registerBlock(new GTBlockTileBasic("machine_lightningrod_iv")),
			tileQuantumEnergy = registerBlock(new GTBlockTileBasic("machine_quantumenergystorage_ev")),
			tileBasicEnergy = registerBlock(new GTBlockTileBasic("machine_basicenergystorage_ev"));
	public static final GTBlockDrum drum = registerBlock(new GTBlockDrum(GTMaterial.StainlessSteel));
	
	protected static final String[] textureTileBasic = { "machine_heatingelement", "machine_bloomery",
			"machine_charcoalpit", "machine_blastfurnace_lv", "machine_leadchamber_lv", "machine_bath",
			"machine_chemicalreactor_hv", "machine_refractory_hv", "machine_roaster_lv", "machine_constructor_mv",
			"machine_compiler_hv", "machine_cryogenicseparator_mv", "machine_chargeomat_ev", "machine_computercube_ev",
			"machine_industrialcentrifuge_lv", "machine_industrialelectrolyzer_hv", "machine_shredder_mv",
			"machine_electricsmelter_lv", "machine_fluidsmelter_lv", "machine_matterfabricator_ev",
			"machine_matterreplicator_ev", "machine_playerdetector_lv", "machine_fusioncomputer_ev",
			"machine_lightningrod_iv", "machine_quantumenergystorage_ev", "machine_basicenergystorage_ev",
			"machine_digitaltransformer_luv", "cable_energium_luv", "cable_lapotron_zpm", "tile_digitalchest",
			"tile_smallchest", "tile_largechest", "tile_bookshelf", "tile_workbench", "tile_drum" };
	protected static final String[] textureTileCustom = { "block_mortar", "block_ducttape", "solar_panel",
			"coolant_helium_small", "coolant_helium_med", "coolant_helium_large", "rod_thorium_small",
			"rod_thorium_med", "rod_thorium_large", "rod_plutonium_small", "rod_plutonium_med", "rod_plutonium_large",
			"battery_lithium_small", "battery_lithium_med", "battery_lithium_large", "battery_lapotron_tiny",
			"battery_lapotron_small", "battery_lapotron_med", "battery_lapotron_large", "battery_lapotron_huge",
			"battery_energium_tiny", "battery_energium_small", "battery_energium_med", "battery_energium_large",
			"battery_energium_huge", };

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
		registerUtil(GTTileBlockCustom.class, "CustomBlock");
		registerUtil(GTTileSluice.class, "Sluice");
		registerUtil(GTTileCentrifuge.class, "IndustrialCentrifuge");
		registerUtil(GTTilePlayerDetector.class, "PlayerDetector");
		registerUtil(GTTileComputerCube.class, "ComputerCube");
		registerUtil(GTTileBasicEnergyStorage.class, "BasicEnergy");
		registerUtil(GTTileQuantumEnergyStorage.class, "QuantumEnergy");
		registerUtil(GTTileMultiLightningRod.class, "LightningRod");
		registerUtil(GTTileMultiFusion.class, "FusionComputer");
		registerUtil(GTTileDigitalChest.class, "QuantumChest");
		registerUtil(GTTileWorkbench.class, "Workbench");
		registerUtil(GTTileFacing.class, "Facing");
		registerUtil(GTTileDrum.class, "Tank");
	}

	// TODO fix the generic type
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerUtil(Class tile, String name) {
		GameRegistry.registerTileEntity(tile, new ResourceLocation(GTMod.MODID, "tileEntity" + name));
	}
}