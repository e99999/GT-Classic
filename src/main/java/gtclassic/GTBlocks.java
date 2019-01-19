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

	public static final GTBlockTileBasic autoCrafter = new GTBlockTileBasic(GTBlockTileBasicVariants.AUTOCRAFTER),
			chargeOMat = new GTBlockTileBasic(GTBlockTileBasicVariants.CHARGEOMAT),
			computerCube = new GTBlockTileBasic(GTBlockTileBasicVariants.COMPUTERCUBE),
			industrialCentrifuge = new GTBlockTileBasic(GTBlockTileBasicVariants.INDUSTRIALCENTRIFUGE),
			matterFabricator = new GTBlockTileBasic(GTBlockTileBasicVariants.MATTERFABRICATOR),
			uuMatterAssembler = new GTBlockTileBasic(GTBlockTileBasicVariants.UUMASSEMBLER),
			playerDetector = new GTBlockTileBasic(GTBlockTileBasicVariants.PLAYERDETECTOR),
			sonictronBlock = new GTBlockTileBasic(GTBlockTileBasicVariants.SONICTRON),
			fusionComputer = new GTBlockTileBasic(GTBlockTileBasicVariants.FUSIONCOMPUTER),
			lightningRod = new GTBlockTileBasic(GTBlockTileBasicVariants.LIGHTNINGROD),
			IDSU = new GTBlockTileBasic(GTBlockTileBasicVariants.IDSU), HESU = new GTBlockTileBasic(GTBlockTileBasicVariants.HESU),
			LESU = new GTBlockTileBasic(GTBlockTileBasicVariants.LESU),
			superCondensator = new GTBlockTileBasic(GTBlockTileBasicVariants.SUPERCONDENSATOR),
			superConductorWire = new GTBlockTileBasic(GTBlockTileBasicVariants.SUPERCONDUCTORWIRE),
			smallChest = new GTBlockTileBasic(GTBlockTileBasicVariants.SMALLCHEST),
			largeChest = new GTBlockTileBasic(GTBlockTileBasicVariants.LARGECHEST),
			quantumChest = new GTBlockTileBasic(GTBlockTileBasicVariants.QUANTUMCHEST),
			bookShelf = new GTBlockTileBasic(GTBlockTileBasicVariants.BOOKSHELF),
			workBench = new GTBlockTileBasic(GTBlockTileBasicVariants.WORKBENCH);

	public static final GTBlockTileCustom smallLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.SMALL_LITHIUM),
			smallCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.SMALL_COOLANT),
			medCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.MED_COOLANT),
			largeCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.LARGE_COOLANT),

			smallThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.SMALL_THORIUM),
			medThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.MED_THORIUM),
			largeThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.LARGE_THORIUM),

			smallPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.SMALL_PLUTONIUM),
			medPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.MED_PLUTONIUM),
			largePlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.LARGE_PLUTONIUM),

			medLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.MED_LITHIUM),
			largeLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.LARGE_LITHIUM),
			smallLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.SMALL_LAPOTRON),
			medLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.MED_LAPOTRON),
			largeLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.LARGE_LAPOTRON),
			smallEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.SMALL_ENERGIUM),
			medEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.MED_ENERGIUM),
			largeEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.LARGE_ENERGIUM),

			aluminiumDataStick = new GTBlockTileCustom(GTBlockTileCustomVariants.ALUMINIUM_DATASTICK),
			titaniumDataStick = new GTBlockTileCustom(GTBlockTileCustomVariants.TITANIUM_DATASTICK),
			chromeDataStick = new GTBlockTileCustom(GTBlockTileCustomVariants.CHROME_DATASTICK),

			aluminiumDataDrive = new GTBlockTileCustom(GTBlockTileCustomVariants.ALUMINIUM_DATADRIVE),
			titaniumDataDrive = new GTBlockTileCustom(GTBlockTileCustomVariants.TITANIUM_DATADRIVE),
			chromeDataDrive = new GTBlockTileCustom(GTBlockTileCustomVariants.CHROME_DATADRIVE),

			energyCircuitBlock = new GTBlockTileCustom(GTBlockTileCustomVariants.ENERGY_CIRCUIT),
			dataCircuitBlock = new GTBlockTileCustom(GTBlockTileCustomVariants.DATA_CIRCUIT);

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