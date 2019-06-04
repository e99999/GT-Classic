package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItemBlock;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.itemblock.GTItemBlockRare;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileBasicEnergyStorage;
import gtclassic.tile.GTTileBlockCustom;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileDigitalChest;
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
	public static final GTBlockOre oreIridium = registerBlock(new GTBlockOre("Iridium", 81, 20.0F, 3));
	public static final GTBlockOre oreRuby = registerBlock(new GTBlockOre("Ruby", 82, 4.0F, 2));
	public static final GTBlockOre oreSapphire = registerBlock(new GTBlockOre("Sapphire", 83, 4.0F, 2));
	public static final GTBlockOre oreBauxite = registerBlock(new GTBlockOre("Bauxite", 84, 3.0F, 1));
	public static final GTBlockCasing blockFusion = registerBlock(new GTBlockCasing("fusion", 1));
	public static final GTBlockCasing blockLESU = registerBlock(new GTBlockCasing("lesu", 5));
	public static final GTBlockCasing blockHighlyAdvanced = registerBlock(new GTBlockCasing("highlyadvanced", 29));
	// public static final GTBlockSluice tileSluice = registerBlock(new
	// GTBlockSluice());
	// public static final GTBlockSluiceBoxExt tileSluiceExt = registerBlock(new
	// GTBlockSluiceBoxExt());
	public static final GTBlockTileBasic tileChargeOmat = registerBlock(new GTBlockTileBasic("chargeomat")),
			tileComputer = registerBlock(new GTBlockTileBasic("computercube")),
			tileCentrifuge = registerBlock(new GTBlockTileBasic("industrialcentrifuge")),
			tileFabricator = registerBlock(new GTBlockTileBasic("matterfabricator")),
			tileReplicator = registerBlock(new GTBlockTileBasic("matterreplicator")),
			tileDigitalChest = registerBlock(new GTBlockTileBasic("digitalchest")),
			tilePlayerDetector = registerBlock(new GTBlockTileBasic("playerdetector")),
			tileFusion = registerBlock(new GTBlockTileBasic("fusioncomputer")),
			tileLightningRod = registerBlock(new GTBlockTileBasic("lightningrod")),
			tileQuantumEnergy = registerBlock(new GTBlockTileBasic("quantumenergystorage")),
			tileBasicEnergy = registerBlock(new GTBlockTileBasic("basicenergystorage"));
	protected static final String[] textureTileBasic = { "chargeomat", "computercube", "industrialcentrifuge",
			"matterfabricator", "matterreplicator", "digitalchest", "playerdetector", "fusioncomputer", "lightningrod",
			"quantumenergystorage", "basicenergystorage" };

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
	}

	// TODO fix the generic type
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerUtil(Class tile, String name) {
		GameRegistry.registerTileEntity(tile, new ResourceLocation(GTMod.MODID, "tileEntity" + name));
	}
}