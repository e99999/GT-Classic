package gtclassic.common;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.color.GTColorItemBlock;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTItemBlock;
import gtclassic.api.item.GTItemBlock;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.block.GTBlockBattery;
import gtclassic.common.block.GTBlockBurnable;
import gtclassic.common.block.GTBlockCasing;
import gtclassic.common.block.GTBlockDrum;
import gtclassic.common.block.GTBlockLamp;
import gtclassic.common.block.GTBlockLightSource;
import gtclassic.common.block.GTBlockMachine;
import gtclassic.common.block.GTBlockMiningPipe;
import gtclassic.common.block.GTBlockMortar;
import gtclassic.common.block.GTBlockOre;
import gtclassic.common.block.GTBlockOreBedrock;
import gtclassic.common.block.GTBlockOreFlower;
import gtclassic.common.block.GTBlockQuantumChest;
import gtclassic.common.block.GTBlockQuantumTank;
import gtclassic.common.block.GTBlockStorage;
import gtclassic.common.block.GTBlockSuperconductorCable;
import gtclassic.common.block.GTBlockUUMAssembler;
import gtclassic.common.tile.GTTileAESU;
import gtclassic.common.tile.GTTileAutocrafter;
import gtclassic.common.tile.GTTileBattery;
import gtclassic.common.tile.GTTileBedrockMiner;
import gtclassic.common.tile.GTTileBlockExtender;
import gtclassic.common.tile.GTTileBufferFluid;
import gtclassic.common.tile.GTTileBufferLarge;
import gtclassic.common.tile.GTTileBufferSmall;
import gtclassic.common.tile.GTTileCabinet;
import gtclassic.common.tile.GTTileCentrifuge;
import gtclassic.common.tile.GTTileCharcoalPit;
import gtclassic.common.tile.GTTileChargeOMat;
import gtclassic.common.tile.GTTileComputer;
import gtclassic.common.tile.GTTileDigitalChest;
import gtclassic.common.tile.GTTileDisassembler;
import gtclassic.common.tile.GTTileDisplayScreen;
import gtclassic.common.tile.GTTileDragonEggEnergySiphon;
import gtclassic.common.tile.GTTileDrum;
import gtclassic.common.tile.GTTileEchotron;
import gtclassic.common.tile.GTTileEnergyTransmitter;
import gtclassic.common.tile.GTTileIDSU;
import gtclassic.common.tile.GTTileItemFilter;
import gtclassic.common.tile.GTTileLamp;
import gtclassic.common.tile.GTTileMagicEnergyAbsorber;
import gtclassic.common.tile.GTTileMagicEnergyConverter;
import gtclassic.common.tile.GTTileMatterFabricator;
import gtclassic.common.tile.GTTileMobRepeller;
import gtclassic.common.tile.GTTilePlayerDetector;
import gtclassic.common.tile.GTTileQuantumChest;
import gtclassic.common.tile.GTTileQuantumTank;
import gtclassic.common.tile.GTTileRedstoneReceiver;
import gtclassic.common.tile.GTTileRedstoneTransmitter;
import gtclassic.common.tile.GTTileRockBreaker;
import gtclassic.common.tile.GTTileSupercondensator;
import gtclassic.common.tile.GTTileSuperconductorCables;
import gtclassic.common.tile.GTTileTesseractMaster;
import gtclassic.common.tile.GTTileTesseractSlave;
import gtclassic.common.tile.GTTileTranslocator;
import gtclassic.common.tile.GTTileTranslocatorFluid;
import gtclassic.common.tile.GTTileTypeFilter;
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
	public static final GTBlockBurnable brittleCharcoal = registerBlock(new GTBlockBurnable("brittlecharcoal", 0, 3200));
	public static final GTBlockBurnable superFuel = registerBlock(new GTBlockBurnable("superfuel", 1, 100000));
	public static final GTBlockBurnable superFuelMagic = registerBlock(new GTBlockBurnable("superfuelmagic", 1, 150000));
	public static final GTBlockOreBedrock oreBedrockGold = registerBlock(new GTBlockOreBedrock("Gold", 0));
	public static final GTBlockOreBedrock oreBedrockIron = registerBlock(new GTBlockOreBedrock("Iron", 1));
	public static final GTBlockOreBedrock oreBedrockCoal = registerBlock(new GTBlockOreBedrock("Coal", 2));
	public static final GTBlockOreBedrock oreBedrockLapis = registerBlock(new GTBlockOreBedrock("Lapis", 3));
	public static final GTBlockOreBedrock oreBedrockDiamond = registerBlock(new GTBlockOreBedrock("Diamond", 4));
	public static final GTBlockOreBedrock oreBedrockRedstone = registerBlock(new GTBlockOreBedrock("Redstone", 5));
	public static final GTBlockOreBedrock oreBedrockEmerald = registerBlock(new GTBlockOreBedrock("Emerald", 6));
	public static final GTBlockOreBedrock oreBedrockCopper = registerBlock(new GTBlockOreBedrock("Copper", 7));
	public static final GTBlockOreBedrock oreBedrockTin = registerBlock(new GTBlockOreBedrock("Tin", 8));
	public static final GTBlockOreBedrock oreBedrockUranium = registerBlock(new GTBlockOreBedrock("Uranium", 9));
	public static final GTBlockOreBedrock oreBedrockSilver = registerBlock(new GTBlockOreBedrock("Silver", 10));
	public static final GTBlockOreBedrock oreBedrockSheldonite = registerBlock(new GTBlockOreBedrock("Sheldonite", 12));
	public static final GTBlockOreBedrock oreBedrockRuby = registerBlock(new GTBlockOreBedrock("Ruby", 13));
	public static final GTBlockOreBedrock oreBedrockSapphire = registerBlock(new GTBlockOreBedrock("Sapphire", 14));
	public static final GTBlockOreBedrock oreBedrockBauxite = registerBlock(new GTBlockOreBedrock("Bauxite", 15));
	public static final GTBlockOre oreIridium = registerBlock(new GTBlockOre("Iridium", 11, 20.0F, 3));
	public static final GTBlockOre oreSheldonite = registerBlock(new GTBlockOre("Sheldonite", 12, 5.0F, 2));
	public static final GTBlockOre oreRuby = registerBlock(new GTBlockOre("Ruby", 13, 4.0F, 2));
	public static final GTBlockOre oreSapphire = registerBlock(new GTBlockOre("Sapphire", 14, 4.0F, 2));
	public static final GTBlockOre oreBauxite = registerBlock(new GTBlockOre("Bauxite", 15, 3.0F, 1));
	public static final GTBlockCasing casingFusion = registerBlock(new GTBlockCasing("fusion", 4, 500.0F));
	public static final GTBlockCasing casingLapotron = registerBlock(new GTBlockCasing("lapotron", 5, 100.0F));
	public static final GTBlockCasing casingHighlyAdvanced = registerBlock(new GTBlockCasing("highlyadvanced", 6, 250.0F));
	public static final GTBlockMachine tileChargeOmat = registerBlock(new GTBlockMachine("chargeomat", GTLang.CHARGE_O_MAT));
	public static final GTBlockMachine tileComputer = registerBlock(new GTBlockMachine("computercube", GTLang.COMPUTER_CUBE));
	public static final GTBlockMachine tileCentrifuge = registerBlock(new GTBlockMachine("industrialcentrifuge", GTLang.INDUSTRIAL_CENTRIFUGE));
	public static final GTBlockMachine tileCharcoalPit = registerBlock(new GTBlockMachine("charcoalpit", GTLang.CHARCOAL_PIT, 5));
	public static final GTBlockMachine tileDisassembler = registerBlock(new GTBlockMachine("disassembler", GTLang.DISASSEMBLER));
	public static final GTBlockMachine tileBedrockMiner = registerBlock(new GTBlockMachine("bedrockminer", GTLang.BEDROCK_MINER, 5));
	public static final GTBlockMachine tileFabricator = registerBlock(new GTBlockMachine("matterfabricator", GTLang.MATTER_FAB, 2));
	public static final GTBlockUUMAssembler tileUUMAssembler = registerBlock(new GTBlockUUMAssembler());
	public static final GTBlockMachine tileEchotron = registerBlock(new GTBlockMachine("echotronblock", GTLang.ECHOTRON));
	public static final GTBlockMachine tilePlayerDetector = registerBlock(new GTBlockMachine("playerdetector", GTLang.PLAYER_DETECTOR, 3));
	public static final GTBlockMachine tileMobRepeller = registerBlock(new GTBlockMachine("mobrepeller", GTLang.MOB_REPELLER, 2));
	public static final GTBlockMachine tileEnergyTransmitter = registerBlock(new GTBlockMachine("energytransmitter", GTLang.ENERGY_TRANSMITTER, 3));
	public static final GTBlockMachine tileDragonEggEnergySiphon = registerBlock(new GTBlockMachine("dragoneggenergysiphon", GTLang.DRAGON_EGG_ENERGY_SIPHON, 2));
	public static final GTBlockMachine tileMagicEnergyConverter = registerBlock(new GTBlockMachine("magicenergyconverter", GTLang.MAGIC_ENERGY_CONVERTER));
	public static final GTBlockMachine tileMagicEnergyAbsorber = registerBlock(new GTBlockMachine("magicenergyabsorber", GTLang.MAGIC_ENERGY_ABSORBER, 3));
	public static final GTBlockMachine tileFusionReactor = registerBlock(new GTBlockMachine("fusionreactor", GTLang.FUSION_REACTOR, 7));
	public static final GTBlockMachine tileLightningRod = registerBlock(new GTBlockMachine("lightningrod", GTLang.LIGHTNING_ROD, 3));
	public static final GTBlockMachine tileTesseractMaster = registerBlock(new GTBlockMachine("tesseractmaster", GTLang.TESSERACT_MASTER, 2));
	public static final GTBlockMachine tileTesseractSlave = registerBlock(new GTBlockMachine("tesseractslave", GTLang.TESSERACT_SLAVE, 2));
	public static final GTBlockBattery tileBatteryLV = registerBlock(new GTBlockBattery("batteryblocklv", 1, 32, 80000));
	public static final GTBlockMachine tileLESU = registerBlock(new GTBlockMachine("lesu", GTLang.LESU, 3));
	public static final GTBlockMachine tileAESU = registerBlock(new GTBlockMachine("aesu", GTLang.AESU, 2));
	public static final GTBlockMachine tileIDSU = registerBlock(new GTBlockMachine("idsu", GTLang.IDSU, 2));
	public static final GTBlockMachine tileSupercondensator = registerBlock(new GTBlockMachine("supercondensator", GTLang.SUPERCONDENSATOR));
	public static final GTBlockStorage tileWorktable = registerBlock(new GTBlockStorage("worktable", GTLang.WORKTABLE));
	public static final GTBlockStorage tileCabinet = registerBlock(new GTBlockStorage("cabinet", GTLang.CABINET));
	public static final GTBlockDrum tileDrum = registerBlock(new GTBlockDrum());
	public static final GTBlockMachine tileDigitalChest = registerBlock(new GTBlockMachine("digitalchest", GTLang.DIGITAL_CHEST));
	public static final GTBlockQuantumChest tileQuantumChest = registerBlock(new GTBlockQuantumChest());
	public static final GTBlockQuantumTank tileQuantumTank = registerBlock(new GTBlockQuantumTank());
	public static final GTBlockMachine tileBlockExtender = registerBlock(new GTBlockMachine("blockextender", GTLang.BLOCK_EXTENDER, 2));
	public static final GTBlockMachine tileDisplayScreen = registerBlock(new GTBlockMachine("displayscreen", GTLang.DISPLAY_SCREEN, 2));
	public static final GTBlockMachine tileRedstoneTransmitter = registerBlock(new GTBlockMachine("redstonetransmitter", GTLang.REDSTONE_TRANSMITTER, 4));
	public static final GTBlockMachine tileRedstoneReceiver = registerBlock(new GTBlockMachine("redstonereceiver", GTLang.REDSTONE_RECEIVER));
	public static final GTBlockMachine tileAutocrafter = registerBlock(new GTBlockMachine("autocrafter", GTLang.AUTOCRAFTER));
	public static final GTBlockMachine tileRockBreaker = registerBlock(new GTBlockMachine("rockbreaker", GTLang.ROCK_BREAKER));
	public static final GTBlockMachine tileTranslocator = registerBlock(new GTBlockMachine("translocator", GTLang.TRANSLOCATOR, 1));
	public static final GTBlockMachine tileTypeFilter = registerBlock(new GTBlockMachine("typefilter", GTLang.TYPE_FILTER));
	public static final GTBlockMachine tileItemFilter = registerBlock(new GTBlockMachine("itemfilter", GTLang.ITEM_FILTER));
	public static final GTBlockMachine tileBufferSmall = registerBlock(new GTBlockMachine("buffersmall", GTLang.BUFFER_SMALL, 1));
	public static final GTBlockMachine tileBufferLarge = registerBlock(new GTBlockMachine("bufferlarge", GTLang.BUFFER_LARGE, 1));
	public static final GTBlockMachine tileTranslocatorFluid = registerBlock(new GTBlockMachine("translocatorfluid", GTLang.TRANSLOCATOR_FLUID, 1));
	public static final GTBlockMachine tileBufferFluid = registerBlock(new GTBlockMachine("bufferfluid", GTLang.BUFFER_FLUID, 1));
	public static final GTBlockLamp tileLamp = registerBlock(new GTBlockLamp());
	public static final GTBlockSuperconductorCable tileSuperconductorCableBEACON = registerBlock(new GTBlockSuperconductorCable(16, "0"));
	public static final GTBlockSuperconductorCable tileSuperconductorCableMAX = registerBlock(new GTBlockSuperconductorCable(12, ""));
	public static final GTBlockSuperconductorCable tileSuperconductorCableIV = registerBlock(new GTBlockSuperconductorCable(6, "2"));
	public static final GTBlockSuperconductorCable tileSuperconductorCableHV = registerBlock(new GTBlockSuperconductorCable(4, "4"));
	public static final GTBlockMiningPipe miningPipe = registerBlock(new GTBlockMiningPipe());
	public static final GTBlockMortar flintMortar = registerBlock(new GTBlockMortar("flintmortar", "axe"));
	public static final GTBlockMortar ironMortar = registerBlock(new GTBlockMortar("ironmortar", "pickaxe"));
	public static final GTBlockOreFlower oreChid = registerBlock(new GTBlockOreFlower("orechid", 38));
	public static final GTBlockLightSource lightSource = registerBlock(new GTBlockLightSource());

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
		return GTItemBlock.class;
	}

	public static void registerTiles() {
		registerUtil(GTTileCentrifuge.class, "IndustrialCentrifuge");
		registerUtil(GTTilePlayerDetector.class, "PlayerDetector");
		registerUtil(GTTileMobRepeller.class, "MobRepeller");
		registerUtil(GTTileEnergyTransmitter.class, "EnergyTransmitter");
		registerUtil(GTTileEchotron.class, "Echotron");
		registerUtil(GTTileAutocrafter.class, "Autocrafter");
		registerUtil(GTTileRockBreaker.class, "RockBreaker");
		registerUtil(GTTileDisassembler.class, "Disassembler");
		registerUtil(GTTileCharcoalPit.class, "CharcoalPit");
		registerUtil(GTTileBedrockMiner.class, "BedrockMiner");
		registerUtil(GTTileChargeOMat.class, "ChargeOMat");
		registerUtil(GTTileComputer.class, "Computer");
		registerUtil(GTTileDisplayScreen.class, "DisplayScreen");
		registerUtil(GTTileMultiLESU.class, "LESU");
		registerUtil(GTTileIDSU.class, "IDSU");
		registerUtil(GTTileAESU.class, "AESU");
		registerUtil(GTTileTesseractMaster.class, "TesseractGenerator");
		registerUtil(GTTileTesseractSlave.class, "TesseractTerminal");
		registerUtil(GTTileMultiLightningRod.class, "LightningRod");
		registerUtil(GTTileSupercondensator.class, "Supercondensator");
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
		registerUtil(GTTileBattery.class, "Battery");
		registerUtil(GTTileBlockExtender.class, "BlockExtender");
		registerUtil(GTTileRedstoneTransmitter.class, "RedstoneTransmitter");
		registerUtil(GTTileRedstoneReceiver.class, "RedstoneReceiver");
		registerUtil(GTTileTranslocator.class, "Translocator");
		registerUtil(GTTileTypeFilter.class, "TypeFilter");
		registerUtil(GTTileItemFilter.class, "ItemFilter");
		registerUtil(GTTileBufferSmall.class, "BufferSmall");
		registerUtil(GTTileBufferLarge.class, "BufferLarge");
		registerUtil(GTTileTranslocatorFluid.class, "TranslocatorFluid");
		registerUtil(GTTileBufferFluid.class, "BufferFluid");
		registerUtil(GTTileLamp.class, "Lamp");
		registerUtil(GTTileSuperconductorCables.SuperconductorMAX.class, "SuperconductorCableMAX");
		registerUtil(GTTileSuperconductorCables.SuperconductorIV.class, "SuperconductorCableIV");
		registerUtil(GTTileSuperconductorCables.SuperconductorHV.class, "SuperconductorCableHV");
	}

	private static void registerUtil(Class<? extends TileEntity> tile, String name) {
		GameRegistry.registerTileEntity(tile, new ResourceLocation(GTMod.MODID, "tileEntity" + name));
	}
}