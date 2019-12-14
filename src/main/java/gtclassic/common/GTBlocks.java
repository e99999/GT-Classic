package gtclassic.common;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.color.GTColorItemBlock;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTItemBlock;
import gtclassic.api.itemblock.GTItemBlockRare;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.pipe.GTTilePipeFluid;
import gtclassic.api.pipe.GTTilePipeItem;
import gtclassic.common.block.GTBlockBattery;
import gtclassic.common.block.GTBlockCasing;
import gtclassic.common.block.GTBlockDrum;
import gtclassic.common.block.GTBlockLightSource;
import gtclassic.common.block.GTBlockMachine;
import gtclassic.common.block.GTBlockMachineDirectionable;
import gtclassic.common.block.GTBlockOre;
import gtclassic.common.block.GTBlockQuantumChest;
import gtclassic.common.block.GTBlockQuantumTank;
import gtclassic.common.block.GTBlockSuperconductorCable;
import gtclassic.common.block.GTBlockUUMAssembler;
import gtclassic.common.tile.GTTileAESU;
import gtclassic.common.tile.GTTileAutocrafter;
import gtclassic.common.tile.GTTileBattery;
import gtclassic.common.tile.GTTileBufferFluid;
import gtclassic.common.tile.GTTileBufferLarge;
import gtclassic.common.tile.GTTileBufferSmall;
import gtclassic.common.tile.GTTileCabinet;
import gtclassic.common.tile.GTTileCentrifuge;
import gtclassic.common.tile.GTTileChargeOMat;
import gtclassic.common.tile.GTTileComputerCube;
import gtclassic.common.tile.GTTileDigitalChest;
import gtclassic.common.tile.GTTileDisassembler;
import gtclassic.common.tile.GTTileDragonEggEnergySiphon;
import gtclassic.common.tile.GTTileDrum;
import gtclassic.common.tile.GTTileEchotron;
import gtclassic.common.tile.GTTileIDSU;
import gtclassic.common.tile.GTTileMagicEnergyAbsorber;
import gtclassic.common.tile.GTTileMagicEnergyConverter;
import gtclassic.common.tile.GTTileMatterFabricator;
import gtclassic.common.tile.GTTileMobRepeller;
import gtclassic.common.tile.GTTilePlayerDetector;
import gtclassic.common.tile.GTTileQuantumChest;
import gtclassic.common.tile.GTTileQuantumTank;
import gtclassic.common.tile.GTTileSupercondensator;
import gtclassic.common.tile.GTTileSuperconductorCable;
import gtclassic.common.tile.GTTileTranslocator;
import gtclassic.common.tile.GTTileTranslocatorFluid;
import gtclassic.common.tile.GTTileUUMAssembler;
import gtclassic.common.tile.GTTileWorktable;
import gtclassic.common.tile.multi.GTTileMultiFusionReactor;
import gtclassic.common.tile.multi.GTTileMultiLESU;
import gtclassic.common.tile.multi.GTTileMultiLightningRod;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTBlocks {

	private GTBlocks() {
		throw new IllegalStateException("Utility class");
	}

	static final List<Block> toRegister = new ArrayList<>();
	public static final GTBlockOre oreIridium = registerBlock(new GTBlockOre("Iridium", 81, 20.0F, 3));
	public static final GTBlockOre oreSheldonite = registerBlock(new GTBlockOre("Sheldonite", 80, 5.0F, 2));
	public static final GTBlockOre oreRuby = registerBlock(new GTBlockOre("Ruby", 82, 4.0F, 2));
	public static final GTBlockOre oreSapphire = registerBlock(new GTBlockOre("Sapphire", 83, 4.0F, 2));
	public static final GTBlockOre oreBauxite = registerBlock(new GTBlockOre("Bauxite", 84, 3.0F, 1));
	public static final GTBlockCasing casingFusion = registerBlock(new GTBlockCasing("fusion", 1, 500.0F));
	public static final GTBlockCasing casingLapotron = registerBlock(new GTBlockCasing("lapotron", 5, 100.0F));
	public static final GTBlockCasing casingHighlyAdvanced = registerBlock(new GTBlockCasing("highlyadvanced", 29, 250.0F));
	public static final GTBlockMachine tileAutocrafter = registerBlock(new GTBlockMachine("autocrafter", GTLang.AUTOCRAFTER));
	public static final GTBlockMachine tileChargeOmat = registerBlock(new GTBlockMachine("chargeomat", GTLang.CHARGE_O_MAT));
	public static final GTBlockMachine tileComputer = registerBlock(new GTBlockMachine("computercube", GTLang.COMPUTER_CUBE));
	public static final GTBlockMachine tileCentrifuge = registerBlock(new GTBlockMachine("industrialcentrifuge", GTLang.INDUSTRIAL_CENTRIFUGE));
	public static final GTBlockMachine tileDisassembler = registerBlock(new GTBlockMachine("disassembler", GTLang.DISASSEMBLER));
	public static final GTBlockMachine tileFabricator = registerBlock(new GTBlockMachine("matterfabricator", GTLang.MATTER_FAB));
	public static final GTBlockUUMAssembler tileUUMAssembler = registerBlock(new GTBlockUUMAssembler());
	public static final GTBlockMachine tileEchotron = registerBlock(new GTBlockMachine("echotronblock", GTLang.ECHOTRON));
	public static final GTBlockMachine tilePlayerDetector = registerBlock(new GTBlockMachine("playerdetector", GTLang.PLAYER_DETECTOR, 1));
	public static final GTBlockMachine tileMobRepeller = registerBlock(new GTBlockMachine("mobrepeller", GTLang.MOB_REPELLER, 1));
	public static final GTBlockMachine tileDragonEggEnergySiphon = registerBlock(new GTBlockMachine("dragoneggenergysiphon", GTLang.DRAGON_EGG_ENERGY_SIPHON));
	public static final GTBlockMachine tileMagicEnergyConverter = registerBlock(new GTBlockMachine("magicenergyconverter", GTLang.MAGIC_ENERGY_CONVERTER));
	public static final GTBlockMachine tileMagicEnergyAbsorber = registerBlock(new GTBlockMachine("magicenergyabsorber", GTLang.MAGIC_ENERGY_ABSORBER));
	public static final GTBlockMachine tileFusionReactor = registerBlock(new GTBlockMachine("fusionreactor", GTLang.FUSION_REACTOR, 5));
	public static final GTBlockMachine tileLightningRod = registerBlock(new GTBlockMachine("lightningrod", GTLang.LIGHTNING_ROD, 3));
	public static final GTBlockBattery tileBatteryLV = registerBlock(new GTBlockBattery("batteryblocklv", GTLang.BATTERYBLOCK_LV));
	public static final GTBlockMachineDirectionable tileLESU = registerBlock(new GTBlockMachineDirectionable("lesu", GTLang.LESU, 2));
	public static final GTBlockMachineDirectionable tileAESU = registerBlock(new GTBlockMachineDirectionable("aesu", GTLang.AESU));
	public static final GTBlockMachineDirectionable tileIDSU = registerBlock(new GTBlockMachineDirectionable("idsu", GTLang.IDSU));
	public static final GTBlockMachineDirectionable tileSupercondensator = registerBlock(new GTBlockMachineDirectionable("supercondensator", GTLang.SUPERCONDENSATOR));
	public static final GTBlockSuperconductorCable tileSuperconductorCable = registerBlock(new GTBlockSuperconductorCable());
	public static final GTBlockMachine tileWorktable = registerBlock(new GTBlockMachine("worktable", GTLang.WORKTABLE));
	public static final GTBlockMachine tileCabinet = registerBlock(new GTBlockMachine("cabinet", GTLang.CABINET));
	public static final GTBlockDrum tileDrum = registerBlock(new GTBlockDrum());
	public static final GTBlockMachine tileDigitalChest = registerBlock(new GTBlockMachine("digitalchest", GTLang.DIGITAL_CHEST));
	public static final GTBlockQuantumChest tileQuantumChest = registerBlock(new GTBlockQuantumChest());
	public static final GTBlockQuantumTank tileQuantumTank = registerBlock(new GTBlockQuantumTank());
	public static final GTBlockMachineDirectionable tileTranslocator = registerBlock(new GTBlockMachineDirectionable("translocator", GTLang.TRANSLOCATOR, 3));
	public static final GTBlockMachineDirectionable tileBufferLarge = registerBlock(new GTBlockMachineDirectionable("bufferlarge", GTLang.BUFFER_LARGE, 2));
	public static final GTBlockMachineDirectionable tileBufferSmall = registerBlock(new GTBlockMachineDirectionable("buffersmall", GTLang.BUFFER_SMALL, 2));
	public static final GTBlockMachineDirectionable tileTranslocatorFluid = registerBlock(new GTBlockMachineDirectionable("translocatorfluid", GTLang.TRANSLOCATOR_FLUID, 3));
	public static final GTBlockMachineDirectionable tileBufferFluid = registerBlock(new GTBlockMachineDirectionable("bufferfluid", GTLang.BUFFER_FLUID, 2));
	public static final GTBlockLightSource lightSource = registerBlock(new GTBlockLightSource());
	/** This is where GTBlockTile holds its textures **/
	protected static final String[] textureTileBasic = { "autocrafter", "chargeomat", "computercube",
			"industrialcentrifuge", "matterfabricator", "uumassembler", "disassembler", "echotronblock", "digitalchest",
			"quantumchest", "quantumtank", "playerdetector", "mobrepeller", "fusionreactor", "lightningrod",
			"dragoneggenergysiphon", "magicenergyconverter", "magicenergyabsorber", "idsu", "aesu", "lesu",
			"supercondensator", "superconductorcable", "cabinet", "drum", "worktable", "translocator",
			"translocatorfluid", "bufferlarge", "buffersmall", "bufferfluid", "pipe" };

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
		if (block instanceof IGTItemBlock) {
			return ((IGTItemBlock) block).getCustomItemBlock();
		}
		if (block instanceof IGTColorBlock) {
			return GTColorItemBlock.class;
		}
		return GTItemBlockRare.class;
	}

	public static void registerTiles() {
		registerUtil(GTTileCentrifuge.class, "IndustrialCentrifuge");
		registerUtil(GTTilePlayerDetector.class, "PlayerDetector");
		registerUtil(GTTileMobRepeller.class, "MobRepeller");
		registerUtil(GTTileEchotron.class, "Echotron");
		registerUtil(GTTileComputerCube.class, "ComputerCube");
		registerUtil(GTTileAutocrafter.class, "Autocrafter");
		registerUtil(GTTileDisassembler.class, "Disassembler");
		registerUtil(GTTileChargeOMat.class, "ChargeOMat");
		registerUtil(GTTileMultiLESU.class, "LESU");
		registerUtil(GTTileIDSU.class, "IDSU");
		registerUtil(GTTileAESU.class, "AESU");
		registerUtil(GTTileMultiLightningRod.class, "LightningRod");
		registerUtil(GTTileSupercondensator.class, "Supercondensator");
		registerUtil(GTTileSuperconductorCable.class, "SuperconductorCable");
		registerUtil(GTTileMultiFusionReactor.class, "FusionComputer");
		registerUtil(GTTileDigitalChest.class, "DigitalChest");
		registerUtil(GTTileQuantumChest.class, "QuantumChest");
		registerUtil(GTTileQuantumTank.class, "QuantumTank");
		registerUtil(GTTileMatterFabricator.class, "MatterFabricator");
		registerUtil(GTTileUUMAssembler.class, "UUMAssembler");
		registerUtil(GTTileDragonEggEnergySiphon.class, "DragonEggEnergySiphon");
		registerUtil(GTTileMagicEnergyConverter.class, "MagicEnergyConverter");
		registerUtil(GTTileMagicEnergyAbsorber.class, "MagicEnergyAbsorber");
		registerUtil(GTTileWorktable.class, "Worktable");
		registerUtil(GTTileCabinet.class, "Cabinet");
		registerUtil(GTTileDrum.class, "Drum");
		registerUtil(GTTileBattery.GTTileBatteryLV.class, "Battery");
		registerUtil(GTTileTranslocator.class, "Translocator");
		registerUtil(GTTileBufferSmall.class, "BufferSmall");
		registerUtil(GTTileBufferLarge.class, "BufferLarge");
		registerUtil(GTTileTranslocatorFluid.class, "TranslocatorFluid");
		registerUtil(GTTileBufferFluid.class, "BufferFluid");
		registerUtil(GTTilePipeItem.GTTilePipeItemSmall.class, "SmallItemPipe");
		registerUtil(GTTilePipeItem.GTTilePipeItemLarge.class, "LargeItemPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipe800.class, "TinyFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipe1600.class, "SmallFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipe3200.class, "MedFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipe6400.class, "LargeFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipe12800.class, "HugeFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipe25600.class, "MassiveFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipeMax1.class, "SmallSuperFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipeMax2.class, "MedSuperFluidPipe");
		registerUtil(GTTilePipeFluid.GTTileFluidPipeMax3.class, "LargeSuperFluidPipe");
	}

	public static void registerUtil(Class<? extends TileEntity> tile, String name) {
		GameRegistry.registerTileEntity(tile, new ResourceLocation(GTMod.MODID, "tileEntity" + name));
	}
}