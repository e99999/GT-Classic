package gtclassic;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockCasing.GTBlockCasingVariants;
import gtclassic.block.GTBlockTileCustom;
import gtclassic.block.GTBlockTileCustom.GTBlockTileCustomVariants;
import gtclassic.block.GTBlockMetal;
import gtclassic.block.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockOre.GTBlockOreVariants;
import gtclassic.block.GTBlockSandIron;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileBasic.GTBlockTileBasicVariants;
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

	public static final GTBlockTileBasic autoCrafter = new GTBlockTileBasic(
			GTBlockTileBasicVariants.MACHINE_LV_AUTOCRAFTER),
			chargeOMat = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_EV_CHARGEOMAT),
			computerCube = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_EV_COMPUTERCUBE),
			industrialCentrifuge = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_INDUSTRIALCENTRIFUGE),
			matterFabricator = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_EV_MATTERFABRICATOR),
			uuMatterAssembler = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_EV_UUMASSEMBLER),
			playerDetector = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_PLAYERDETECTOR),
			sonictronBlock = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_SONICTRON),
			fusionComputer = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_IV_FUSIONCOMPUTER),
			lightningRod = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_IV_LIGHTNINGROD),
			IDSU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_EV_ADVANCEDENERGYSTORAGE),
			HESU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_EV_BASICENERGYSTORAGE),
			LESU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_MV_MULTIENERGYSTORAGE),
			superCondensator = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_IV_SUPERCONDENSATOR),
			superConductorWire = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_IV_SUPERCONDUCTORWIRE),
			smallChest = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_SMALLCHEST),
			largeChest = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_LARGECHEST),
			quantumChest = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_QUANTUMCHEST),
			bookShelf = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_BOOKSHELF),
			workBench = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LV_WORKBENCH);

	public static final GTBlockTileCustom smallCoolant = new GTBlockTileCustom(
			GTBlockTileCustomVariants.COOLANT_SMALL_HELIUM),
			medCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.COOLANT_MED_HELIUM),
			largeCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.COOLAN_LARGE_HELIUM),

			smallThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_SMALL_THORIUM),
			medThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_MED_THORIUM),
			largeThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_LARGE_THORIUM),

			smallPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_SMALL_PLUTONIUM),
			medPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_MED_PLUTONOIUM),
			largePlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_LARGE_PLUTONIUM),

			smallLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_SMALL_LITHIUM),
			medLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_MED_LITHIUM),
			largeLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LARGE_LITHIUM),
			smallLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_SMALL_LAPOTRON),
			medLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_MED_LAPOTRON),
			largeLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LARGE_LAPOTRON),
			smallEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_SMALL_ENERGIUM),
			medEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_MED_ENERGIUM),
			largeEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LARGE_ENERGIUM),

			aluminiumDataStick = new GTBlockTileCustom(GTBlockTileCustomVariants.DATA_SMALL_ALUMINIUM),
			titaniumDataStick = new GTBlockTileCustom(GTBlockTileCustomVariants.DATA_SMALL_TITANIUM),
			chromeDataStick = new GTBlockTileCustom(GTBlockTileCustomVariants.DATA_SMALL_CHROME),

			aluminiumDataDrive = new GTBlockTileCustom(GTBlockTileCustomVariants.DATA_LARGE_ALUMINIUM),
			titaniumDataDrive = new GTBlockTileCustom(GTBlockTileCustomVariants.DATA_LARGE_TITANIUM),
			chromeDataDrive = new GTBlockTileCustom(GTBlockTileCustomVariants.DATA_LARGE_CHROME),

			energyCircuitBlock = new GTBlockTileCustom(GTBlockTileCustomVariants.CIRCUIT_ENERGY),
			dataCircuitBlock = new GTBlockTileCustom(GTBlockTileCustomVariants.CIRCUIT_DATA);

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