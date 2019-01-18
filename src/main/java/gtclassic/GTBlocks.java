package gtclassic;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockCasing.GTBlockCasingVariants;
import gtclassic.block.GTBlockItem;
import gtclassic.block.GTBlockItem.GTBlockItemVariants;
import gtclassic.block.GTBlockMetal;
import gtclassic.block.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockOre.GTBlockOreVariants;
import gtclassic.block.GTBlockSandIron;
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

	public static final GTBlockTile autoCrafter = new GTBlockTile(GTBlockTileVariants.AUTOCRAFTER),
			chargeOMat = new GTBlockTile(GTBlockTileVariants.CHARGEOMAT),
			computerCube = new GTBlockTile(GTBlockTileVariants.COMPUTERCUBE),
			industrialCentrifuge = new GTBlockTile(GTBlockTileVariants.INDUSTRIALCENTRIFUGE),
			matterFabricator = new GTBlockTile(GTBlockTileVariants.MATTERFABRICATOR),
			uuMatterAssembler = new GTBlockTile(GTBlockTileVariants.UUMASSEMBLER),
			playerDetector = new GTBlockTile(GTBlockTileVariants.PLAYERDETECTOR),
			sonictronBlock = new GTBlockTile(GTBlockTileVariants.SONICTRON),
			fusionComputer = new GTBlockTile(GTBlockTileVariants.FUSIONCOMPUTER),
			lightningRod = new GTBlockTile(GTBlockTileVariants.LIGHTNINGROD),
			IDSU = new GTBlockTile(GTBlockTileVariants.IDSU), HESU = new GTBlockTile(GTBlockTileVariants.HESU),
			LESU = new GTBlockTile(GTBlockTileVariants.LESU),
			superCondensator = new GTBlockTile(GTBlockTileVariants.SUPERCONDENSATOR),
			superConductorWire = new GTBlockTile(GTBlockTileVariants.SUPERCONDUCTORWIRE),
			smallChest = new GTBlockTile(GTBlockTileVariants.SMALLCHEST),
			largeChest = new GTBlockTile(GTBlockTileVariants.LARGECHEST),
			quantumChest = new GTBlockTile(GTBlockTileVariants.QUANTUMCHEST),
			bookShelf = new GTBlockTile(GTBlockTileVariants.BOOKSHELF),
			workBench = new GTBlockTile(GTBlockTileVariants.WORKBENCH);

	public static final GTBlockItem smallLithium = new GTBlockItem(GTBlockItemVariants.SMALL_LITHIUM),
			smallCoolant = new GTBlockItem(GTBlockItemVariants.SMALL_COOLANT),
			medCoolant = new GTBlockItem(GTBlockItemVariants.MED_COOLANT),
			largeCoolant = new GTBlockItem(GTBlockItemVariants.LARGE_COOLANT),

			smallThorium = new GTBlockItem(GTBlockItemVariants.SMALL_THORIUM),
			medThorium = new GTBlockItem(GTBlockItemVariants.MED_THORIUM),
			largeThorium = new GTBlockItem(GTBlockItemVariants.LARGE_THORIUM),

			smallPlutonium = new GTBlockItem(GTBlockItemVariants.SMALL_PLUTONIUM),
			medPlutonium = new GTBlockItem(GTBlockItemVariants.MED_PLUTONIUM),
			largePlutonium = new GTBlockItem(GTBlockItemVariants.LARGE_PLUTONIUM),

			medLithium = new GTBlockItem(GTBlockItemVariants.MED_LITHIUM),
			largeLithium = new GTBlockItem(GTBlockItemVariants.LARGE_LITHIUM),
			smallLapotron = new GTBlockItem(GTBlockItemVariants.SMALL_LAPOTRON),
			medLapotron = new GTBlockItem(GTBlockItemVariants.MED_LAPOTRON),
			largeLapotron = new GTBlockItem(GTBlockItemVariants.LARGE_LAPOTRON),
			smallEnergium = new GTBlockItem(GTBlockItemVariants.SMALL_ENERGIUM),
			medEnergium = new GTBlockItem(GTBlockItemVariants.MED_ENERGIUM),
			largeEnergium = new GTBlockItem(GTBlockItemVariants.LARGE_ENERGIUM),

			aluminiumDataStick = new GTBlockItem(GTBlockItemVariants.ALUMINIUM_DATASTICK),
			titaniumDataStick = new GTBlockItem(GTBlockItemVariants.TITANIUM_DATASTICK),
			chromeDataStick = new GTBlockItem(GTBlockItemVariants.CHROME_DATASTICK),

			aluminiumDataDrive = new GTBlockItem(GTBlockItemVariants.ALUMINIUM_DATADRIVE),
			titaniumDataDrive = new GTBlockItem(GTBlockItemVariants.TITANIUM_DATADRIVE),
			chromeDataDrive = new GTBlockItem(GTBlockItemVariants.CHROME_DATADRIVE),

			energyCircuitBlock = new GTBlockItem(GTBlockItemVariants.ENERGY_CIRCUIT),
			dataCircuitBlock = new GTBlockItem(GTBlockItemVariants.DATA_CIRCUIT);

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

			smallThorium, medThorium, largeThorium, smallPlutonium, medPlutonium, largePlutonium,

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
				new ResourceLocation(GTClassic.MODID, "tileEntitySuperCondensator"));

		GameRegistry.registerTileEntity(GTTileEntitySmallChest.class,
				new ResourceLocation(GTClassic.MODID, "tileEntitySmallChest"));
		GameRegistry.registerTileEntity(GTTileEntityLargeChest.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityLargeChest"));
		GameRegistry.registerTileEntity(GTTileEntityQuantumChest.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityQuantumChest"));
		GameRegistry.registerTileEntity(GTTileEntityBookshelf.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityBookshelf"));
		GameRegistry.registerTileEntity(GTTileEntityWorkbench.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityWorkbench"));

		GameRegistry.registerTileEntity(GTTileEntityEnergyStorage.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityHESU"));
		GameRegistry.registerTileEntity(GTTileEntityDimensionalEnergyStorage.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityIDSU"));
		GameRegistry.registerTileEntity(GTTileEntityLapotronicEnergyStorage.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityLESU"));

		GameRegistry.registerTileEntity(GTTileEntityLightningRod.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityLightningRod"));
		GameRegistry.registerTileEntity(GTTileEntityFusionComputer.class,
				new ResourceLocation(GTClassic.MODID, "tileEntityFusionComputer"));
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		// overloads deprecated method to properly register tiles
		GameRegistry.registerTileEntity(tileEntityClass, GTClassic.MODID + ":" + name);
	}
}