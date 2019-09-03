package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockCasingAdvanced;
import gtclassic.block.GTBlockDrum;
import gtclassic.block.GTBlockLightSource;
import gtclassic.block.GTBlockMachine;
import gtclassic.block.GTBlockMachineDirectionable;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockQuantumChest;
import gtclassic.block.GTBlockQuantumTank;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItemBlock;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.itemblock.GTItemBlockRare;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileAESU;
import gtclassic.tile.GTTileBufferFluid;
import gtclassic.tile.GTTileBufferLarge;
import gtclassic.tile.GTTileBufferSmall;
import gtclassic.tile.GTTileCabinet;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.GTTileChargeOMat;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileDrum;
import gtclassic.tile.GTTileEchotron;
import gtclassic.tile.GTTileFacing;
import gtclassic.tile.GTTileIDSU;
import gtclassic.tile.GTTileLESU;
import gtclassic.tile.GTTileMatterFabricator;
import gtclassic.tile.GTTileMobRepeller;
import gtclassic.tile.GTTilePlayerDetector;
import gtclassic.tile.GTTileQuantumChest;
import gtclassic.tile.GTTileQuantumTank;
import gtclassic.tile.GTTileSluice;
import gtclassic.tile.GTTileSupercondensator;
import gtclassic.tile.GTTileTranslocator;
import gtclassic.tile.GTTileWorktable;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.tile.multi.GTTileMultiFusionReactor;
import gtclassic.tile.multi.GTTileMultiLightningRod;
import gtclassic.util.GTLang;
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
	public static final GTBlockOre orePlatinum = registerBlock(new GTBlockOre("Platinum", 80, 5.0F, 2));
	public static final GTBlockOre oreRuby = registerBlock(new GTBlockOre("Ruby", 82, 4.0F, 2));
	public static final GTBlockOre oreSapphire = registerBlock(new GTBlockOre("Sapphire", 83, 4.0F, 2));
	public static final GTBlockOre oreBauxite = registerBlock(new GTBlockOre("Bauxite", 84, 3.0F, 1));
	public static final GTBlockCasing casingFusion = registerBlock(new GTBlockCasing("fusion", 2, 500.0F));
	public static final GTBlockCasing casingLapotron = registerBlock(new GTBlockCasing("lapotron", 5, 100.0F));
	public static final GTBlockCasing casingReinforced = registerBlock(new GTBlockCasing("reinforced", 1, 150.0F));
	public static final GTBlockCasing casingHighlyAdvanced = registerBlock(new GTBlockCasing("highlyadvanced", 29, 250.0F));
	// public static final GTBlockSluice tileSluice = registerBlock(new
	// GTBlockSluice());
	// public static final GTBlockSluiceBoxExt tileSluiceExt = registerBlock(new
	// GTBlockSluiceBoxExt());
	public static final GTBlockMachine tileBlastFurnace = registerBlock(new GTBlockMachine("gtblastfurnace", GTLang.BLAST_FURNACE, 3));
	public static final GTBlockMachine tileChargeOmat = registerBlock(new GTBlockMachine("chargeomat", GTLang.CHARGE_O_MAT));
	public static final GTBlockMachine tileComputer = registerBlock(new GTBlockMachine("computercube", GTLang.COMPUTER_CUBE));
	public static final GTBlockMachine tileCentrifuge = registerBlock(new GTBlockMachine("industrialcentrifuge", GTLang.INDUSTRIAL_CENTRIFUGE));
	public static final GTBlockMachine tileFabricator = registerBlock(new GTBlockMachine("matterfabricator", GTLang.MATTER_FAB));
	public static final GTBlockMachine tileUUAssembler = registerBlock(new GTBlockMachine("uuassembler", GTLang.UU_ASSEMBLER));
	public static final GTBlockMachine tileEchotron = registerBlock(new GTBlockMachine("echotronblock", GTLang.ECHOTRON));
	public static final GTBlockMachine tilePlayerDetector = registerBlock(new GTBlockMachine("playerdetector", GTLang.PLAYER_DETECTOR, 1));
	public static final GTBlockMachine tileMobRepeller = registerBlock(new GTBlockMachine("mobrepeller", GTLang.MOB_REPELLER, 1));
	public static final GTBlockMachine tileFusionReactor = registerBlock(new GTBlockMachine("fusionreactor", GTLang.FUSION_REACTOR, 4));
	public static final GTBlockMachine tileLightningRod = registerBlock(new GTBlockMachine("lightningrod", GTLang.LIGHTNING_ROD, 2));
	public static final GTBlockMachineDirectionable tileLESU = registerBlock(new GTBlockMachineDirectionable("lesu", GTLang.LESU));
	public static final GTBlockMachineDirectionable tileAESU = registerBlock(new GTBlockMachineDirectionable("aesu", GTLang.AESU));
	public static final GTBlockMachineDirectionable tileIDSU = registerBlock(new GTBlockMachineDirectionable("idsu", GTLang.IDSU));
	public static final GTBlockCasingAdvanced casingSupercontainer = registerBlock(new GTBlockCasingAdvanced("supercontainer", 109, 800.0F));
	public static final GTBlockMachineDirectionable tileSupercondensator = registerBlock(new GTBlockMachineDirectionable("supercondensator", GTLang.SUPERCONDENSATOR));
	public static final GTBlockMachine tileWorktable = registerBlock(new GTBlockMachine("worktable", GTLang.WORKTABLE));
	public static final GTBlockMachine tileCabinet = registerBlock(new GTBlockMachine("cabinet", GTLang.CABINET));
	public static final GTBlockDrum tileDrum = registerBlock(new GTBlockDrum());
	public static final GTBlockQuantumChest tileQuantumChest = registerBlock(new GTBlockQuantumChest());
	public static final GTBlockQuantumTank tileQuantumTank = registerBlock(new GTBlockQuantumTank());
	public static final GTBlockMachineDirectionable tileTranslocator = registerBlock(new GTBlockMachineDirectionable("translocator", GTLang.TRANSLOCATOR, 3));
	public static final GTBlockMachineDirectionable tileBufferLarge = registerBlock(new GTBlockMachineDirectionable("bufferlarge", GTLang.BUFFER_LARGE, 2));
	public static final GTBlockMachineDirectionable tileBufferSmall = registerBlock(new GTBlockMachineDirectionable("buffersmall", GTLang.BUFFER_SMALL, 2));
	public static final GTBlockMachineDirectionable tileBufferFluid = registerBlock(new GTBlockMachineDirectionable("bufferfluid", GTLang.BUFFER_FLUID, 2));
	public static final GTBlockLightSource lightSource = registerBlock(new GTBlockLightSource());
	/** This is where GTBlockTile holds its textures **/
	protected static final String[] textureTileBasic = { "gtblastfurnace", "chargeomat", "computercube",
			"industrialcentrifuge", "matterfabricator", "uuassembler", "echotronblock", "quantumchest", "quantumtank",
			"playerdetector", "mobrepeller", "fusionreactor", "lightningrod", "idsu", "aesu", "lesu",
			"supercondensator", "cabinet", "drum", "worktable", "translocator", "bufferlarge", "buffersmall",
			"bufferfluid" };

	public static void registerBlocks() {
		for (Block block : GTMaterialGen.blockMap.values()) {
			createBlock(block);
		}
		GTFluids.registerFluidBlocks();
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
		registerUtil(GTTileSluice.class, "Sluice");
		registerUtil(GTTileCentrifuge.class, "IndustrialCentrifuge");
		registerUtil(GTTilePlayerDetector.class, "PlayerDetector");
		registerUtil(GTTileMobRepeller.class, "MobRepeller");
		registerUtil(GTTileEchotron.class, "Echotron");
		registerUtil(GTTileComputerCube.class, "ComputerCube");
		registerUtil(GTTileMultiBlastFurnace.class, "BlastFurnace");
		registerUtil(GTTileChargeOMat.class, "ChargeOMat");
		registerUtil(GTTileLESU.class, "LESU");
		registerUtil(GTTileIDSU.class, "IDSU");
		registerUtil(GTTileAESU.class, "AESU");
		registerUtil(GTTileMultiLightningRod.class, "LightningRod");
		registerUtil(GTTileSupercondensator.class, "Supercondensator");
		registerUtil(GTTileMultiFusionReactor.class, "FusionComputer");
		registerUtil(GTTileQuantumChest.class, "QuantumChest");
		registerUtil(GTTileQuantumTank.class, "QuantumTank");
		registerUtil(GTTileMatterFabricator.class, "MatterFabricator");
		registerUtil(GTTileWorktable.class, "Worktable");
		registerUtil(GTTileFacing.class, "Facing");
		registerUtil(GTTileCabinet.class, "Cabinet");
		registerUtil(GTTileDrum.class, "Drum");
		registerUtil(GTTileTranslocator.class, "Translocator");
		registerUtil(GTTileBufferSmall.class, "BufferSmall");
		registerUtil(GTTileBufferLarge.class, "BufferLarge");
		registerUtil(GTTileBufferFluid.class, "BufferFluid");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerUtil(Class tile, String name) {
		GameRegistry.registerTileEntity(tile, new ResourceLocation(GTMod.MODID, "tileEntity" + name));
	}
}