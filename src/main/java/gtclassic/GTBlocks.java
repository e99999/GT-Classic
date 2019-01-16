package gtclassic;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockCasing.GTBlockCasingVariants;
import gtclassic.block.GTBlockEnergy;
import gtclassic.block.GTBlockEnergy.GTBlockEnergyVariants;
import gtclassic.block.GTBlockMachine;
import gtclassic.block.GTBlockMachine.GTBlockMachineVariants;
import gtclassic.block.GTBlockMetal;
import gtclassic.block.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockOre.GTBlockOreVariants;
import gtclassic.block.GTBlockSandIron;
import gtclassic.block.GTBlockStorage;
import gtclassic.block.GTBlockStorage.GTBlockStorageVariants;
import gtclassic.block.GTBlockTile;
import gtclassic.block.GTBlockTile.GTBlockTileVariants;
import gtclassic.tileentity.GTTileEntityBookshelf;
import gtclassic.tileentity.GTTileEntityComputerCube;
import gtclassic.tileentity.GTTileEntityDimensionalEnergyStorage;
import gtclassic.tileentity.GTTileEntityEnergyStorage;
import gtclassic.tileentity.GTTileEntityFusionComputer;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.tileentity.GTTileEntityLapotronicEnergyStorage;
import gtclassic.tileentity.GTTileEntityLargeChest;
import gtclassic.tileentity.GTTileEntityLightningRod;
import gtclassic.tileentity.GTTileEntityQuantumChest;
import gtclassic.tileentity.GTTileEntitySmallChest;
import gtclassic.tileentity.GTTileEntitySuperCondensator;
import gtclassic.tileentity.GTTileEntityWorkbench;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class GTBlocks {

	public static final GTBlockCasing ironCasingBlock = new GTBlockCasing(GTBlockCasingVariants.IRON),
			aluminiumCasingBlock = new GTBlockCasing(GTBlockCasingVariants.ALUMINIUM),
			titaniumCasingBlock = new GTBlockCasing(GTBlockCasingVariants.TITANIUM),
			chromeCasingBlock = new GTBlockCasing(GTBlockCasingVariants.CHROME),
			iridiumCasingBlock = new GTBlockCasing(GTBlockCasingVariants.IRIDIUM),
			tungstenCasingBlock = new GTBlockCasing(GTBlockCasingVariants.TUNGSTEN),
			superCasingBlock = new GTBlockCasing(GTBlockCasingVariants.SUPERCONDUCTOR),
			fusionCasingBlock = new GTBlockCasing(GTBlockCasingVariants.FUSION),
			lesuCasingBlock = new GTBlockCasing(GTBlockCasingVariants.LESU);

	public static final GTBlockMetal iridiumBlock = new GTBlockMetal(GTBlockMetalVariants.IRIDIUM),
			rubyBlock = new GTBlockMetal(GTBlockMetalVariants.RUBY),
			sapphireBlock = new GTBlockMetal(GTBlockMetalVariants.SAPPHIRE),
			aluminiumBlock = new GTBlockMetal(GTBlockMetalVariants.ALUMINIUM),
			titaniumBlock = new GTBlockMetal(GTBlockMetalVariants.TITANIUM),
			chromeBlock = new GTBlockMetal(GTBlockMetalVariants.CHROME);

	public static final GTBlockOre iridiumOre = new GTBlockOre(GTBlockOreVariants.IRIDIUM),
			rubyOre = new GTBlockOre(GTBlockOreVariants.RUBY),
			sapphireOre = new GTBlockOre(GTBlockOreVariants.SAPPHIRE),
			bauxiteOre = new GTBlockOre(GTBlockOreVariants.BAUXITE),
			iridiumEnd = new GTBlockOre(GTBlockOreVariants.IRIDIUM_END);

	public static final GTBlockSandIron sandIron = new GTBlockSandIron();

	public static final GTBlockMachine autoCrafter = new GTBlockMachine(GTBlockMachineVariants.AUTOCRAFTER),
			chargeOMat = new GTBlockMachine(GTBlockMachineVariants.CHARGEOMAT),
			computerCube = new GTBlockMachine(GTBlockMachineVariants.COMPUTERCUBE),
			industrialCentrifuge = new GTBlockMachine(GTBlockMachineVariants.INDUSTRIALCENTRIFUGE),
			matterFabricator = new GTBlockMachine(GTBlockMachineVariants.MATTERFABRICATOR),
			uuMatterAssembler = new GTBlockMachine(GTBlockMachineVariants.UUMASSEMBLER),
			playerDetector = new GTBlockMachine(GTBlockMachineVariants.PLAYERDETECTOR),
			sonictronBlock = new GTBlockMachine(GTBlockMachineVariants.SONICTRON),
			fusionComputer = new GTBlockMachine(GTBlockMachineVariants.FUSIONCOMPUTER),
			lightningRod = new GTBlockMachine(GTBlockMachineVariants.LIGHTNINGROD);

	public static final GTBlockEnergy IDSU = new GTBlockEnergy(GTBlockEnergyVariants.IDSU),
			HESU = new GTBlockEnergy(GTBlockEnergyVariants.HESU), LESU = new GTBlockEnergy(GTBlockEnergyVariants.LESU),
			superCondensator = new GTBlockEnergy(GTBlockEnergyVariants.SUPERCONDENSATOR),
			superConductorWire = new GTBlockEnergy(GTBlockEnergyVariants.SUPERCONDUCTORWIRE);

	public static final GTBlockStorage smallChest = new GTBlockStorage(GTBlockStorageVariants.SMALLCHEST),
			largeChest = new GTBlockStorage(GTBlockStorageVariants.LARGECHEST),
			quantumChest = new GTBlockStorage(GTBlockStorageVariants.QUANTUMCHEST),
			bookShelf = new GTBlockStorage(GTBlockStorageVariants.BOOKSHELF),
			workBench = new GTBlockStorage(GTBlockStorageVariants.WORKBENCH);

	public static final GTBlockTile smallLithium = new GTBlockTile(GTBlockTileVariants.SMALL_LITHIUM),
			smallCoolant = new GTBlockTile(GTBlockTileVariants.SMALL_COOLANT),
			medCoolant = new GTBlockTile(GTBlockTileVariants.MED_COOLANT),
			largeCoolant = new GTBlockTile(GTBlockTileVariants.LARGE_COOLANT),
			
			smallThorium = new GTBlockTile(GTBlockTileVariants.SMALL_THORIUM),
			medThorium = new GTBlockTile(GTBlockTileVariants.MED_THORIUM),
			largeThorium = new GTBlockTile(GTBlockTileVariants.LARGE_THORIUM),
			
			smallPlutonium = new GTBlockTile(GTBlockTileVariants.SMALL_PLUTONIUM),
			medPlutonium = new GTBlockTile(GTBlockTileVariants.MED_PLUTONIUM),
			largePlutonium = new GTBlockTile(GTBlockTileVariants.LARGE_PLUTONIUM),

			medLithium = new GTBlockTile(GTBlockTileVariants.MED_LITHIUM),
			largeLithium = new GTBlockTile(GTBlockTileVariants.LARGE_LITHIUM),
			smallLapotron = new GTBlockTile(GTBlockTileVariants.SMALL_LAPOTRON),
			medLapotron = new GTBlockTile(GTBlockTileVariants.MED_LAPOTRON),
			largeLapotron = new GTBlockTile(GTBlockTileVariants.LARGE_LAPOTRON),
			smallEnergium = new GTBlockTile(GTBlockTileVariants.SMALL_ENERGIUM),
			medEnergium = new GTBlockTile(GTBlockTileVariants.MED_ENERGIUM),
			largeEnergium = new GTBlockTile(GTBlockTileVariants.LARGE_ENERGIUM),

			aluminiumDataStick = new GTBlockTile(GTBlockTileVariants.ALUMINIUM_DATASTICK),
			titaniumDataStick = new GTBlockTile(GTBlockTileVariants.TITANIUM_DATASTICK),
			chromeDataStick = new GTBlockTile(GTBlockTileVariants.CHROME_DATASTICK),

			aluminiumDataDrive = new GTBlockTile(GTBlockTileVariants.ALUMINIUM_DATADRIVE),
			titaniumDataDrive = new GTBlockTile(GTBlockTileVariants.TITANIUM_DATADRIVE),
			chromeDataDrive = new GTBlockTile(GTBlockTileVariants.CHROME_DATADRIVE),

			energyCircuitBlock = new GTBlockTile(GTBlockTileVariants.ENERGY_CIRCUIT),
			dataCircuitBlock = new GTBlockTile(GTBlockTileVariants.DATA_CIRCUIT);

	public static final Block[] blocks = {

			ironCasingBlock, aluminiumCasingBlock, titaniumCasingBlock, chromeCasingBlock, iridiumCasingBlock,
			tungstenCasingBlock,

			superCasingBlock, fusionCasingBlock, lesuCasingBlock,

			rubyBlock, sapphireBlock, aluminiumBlock, titaniumBlock, chromeBlock, iridiumBlock,

			rubyOre, sapphireOre, bauxiteOre, sandIron, iridiumOre, iridiumEnd,

			autoCrafter, chargeOMat, computerCube, industrialCentrifuge, matterFabricator, uuMatterAssembler,
			playerDetector, sonictronBlock, fusionComputer, lightningRod,

			IDSU, HESU, LESU, superCondensator, superConductorWire,

			quantumChest, bookShelf, workBench, smallChest, largeChest,

			smallCoolant, medCoolant, largeCoolant,
			
			smallThorium, medThorium, largeThorium,
			smallPlutonium, medPlutonium, largePlutonium,
			
			smallLithium, medLithium, largeLithium, smallLapotron, medLapotron, largeLapotron, smallEnergium,
			medEnergium, largeEnergium,

			aluminiumDataStick, titaniumDataStick, chromeDataStick, aluminiumDataDrive, titaniumDataDrive,
			chromeDataDrive,

			energyCircuitBlock, dataCircuitBlock,

	};

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		GTClassic.logger.info("Registering Blocks");
		for (Block block : blocks) {
			registry.register(block);
		}
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		for (Block block : blocks) {
			registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName())
					.setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(GTClassic.creativeTabGT));
		}
	}

	public static void registerTiles() {
		GameRegistry.registerTileEntity(GTTileEntityIndustrialCentrifuge.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityIndustrialCentrifuge"));
		GameRegistry.registerTileEntity(GTTileEntityComputerCube.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityComputerCube"));
		GameRegistry.registerTileEntity(GTTileEntitySuperCondensator.class,
				new ResourceLocation(GTClassic.MODID, "tileSuperCondensator"));

		GameRegistry.registerTileEntity(GTTileEntitySmallChest.class,
				new ResourceLocation(GTClassic.MODID, "tileEntitySmallChest"));
		GameRegistry.registerTileEntity(GTTileEntityLargeChest.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityLargeChest"));
		GameRegistry.registerTileEntity(GTTileEntityQuantumChest.class,
				new ResourceLocation(GTClassic.MODID, "tileQuantumChest"));
		GameRegistry.registerTileEntity(GTTileEntityBookshelf.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityBookshelf"));
		GameRegistry.registerTileEntity(GTTileEntityWorkbench.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityWorkbench"));

		GameRegistry.registerTileEntity(GTTileEntityEnergyStorage.class,
				new ResourceLocation(GTClassic.MODID, "tileHESU"));
		GameRegistry.registerTileEntity(GTTileEntityDimensionalEnergyStorage.class,
				new ResourceLocation(GTClassic.MODID, "tileIDSU"));
		GameRegistry.registerTileEntity(GTTileEntityLapotronicEnergyStorage.class,
				new ResourceLocation(GTClassic.MODID, "tileLESU"));

		GameRegistry.registerTileEntity(GTTileEntityLightningRod.class,
				new ResourceLocation(GTClassic.MODID, "tileLightningRod"));
		GameRegistry.registerTileEntity(GTTileEntityFusionComputer.class,
				new ResourceLocation(GTClassic.MODID, "tileFusionComputer"));
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		// overloads deprecated method to properly register tiles
		GameRegistry.registerTileEntity(tileEntityClass, GTClassic.MODID + ":" + name);
	}
}