package gtclassic;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockCasing.GTBlockCasingVariants;
import gtclassic.block.GTBlockMetal;
import gtclassic.block.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockOre.GTBlockOreVariants;
import gtclassic.block.GTBlockSandIron;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileBasic.GTBlockTileBasicVariants;
import gtclassic.block.GTBlockTileCustom;
import gtclassic.block.GTBlockTileCustom.GTBlockTileCustomVariants;
import gtclassic.block.test.GTBlockTestLayer;
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
			platinumCasingBlock = new GTBlockCasing(GTBlockCasingVariants.PLATINUM),
			superCasingBlock = new GTBlockCasing(GTBlockCasingVariants.SUPERCONDUCTOR),
			fusionCasingBlock = new GTBlockCasing(GTBlockCasingVariants.FUSION),
			fissionCasingBlock = new GTBlockCasing(GTBlockCasingVariants.FISSION),
			crystalCasingBlock = new GTBlockCasing(GTBlockCasingVariants.CRYSTAL);

	public static final GTBlockMetal
	rubyBlock = new GTBlockMetal(GTBlockMetalVariants.RUBY),
	sapphireBlock = new GTBlockMetal(GTBlockMetalVariants.SAPPHIRE),
	aluminiumBlock = new GTBlockMetal(GTBlockMetalVariants.ALUMINIUM),
	titaniumBlock = new GTBlockMetal(GTBlockMetalVariants.TITANIUM),
	chromeBlock = new GTBlockMetal(GTBlockMetalVariants.CHROME),
	steelBlock = new GTBlockMetal(GTBlockMetalVariants.STEEL),
	brassBlock = new GTBlockMetal(GTBlockMetalVariants.BRASS),
	leadBlock = new GTBlockMetal(GTBlockMetalVariants.LEAD),
	electrumBlock = new GTBlockMetal(GTBlockMetalVariants.ELECTRUM),
	zincBlock = new GTBlockMetal(GTBlockMetalVariants.ZINC),
	olivineBlock = new GTBlockMetal(GTBlockMetalVariants.OLIVINE),
	greenSapphireBlock = new GTBlockMetal(GTBlockMetalVariants.GREEN_SAPPHIRE),
	platinumBlock = new GTBlockMetal(GTBlockMetalVariants.PLATINUM),
	tungstenBlock = new GTBlockMetal(GTBlockMetalVariants.TUNGSTEN),
	nickelBlock = new GTBlockMetal(GTBlockMetalVariants.NICKEL),
	tungstensteelBlock = new GTBlockMetal(GTBlockMetalVariants.TUNGSTENSTEEL),
	invarBlock = new GTBlockMetal(GTBlockMetalVariants.INVAR),
	osmiumBlock = new GTBlockMetal(GTBlockMetalVariants.OSMIUM),
	iridiumBlock = new GTBlockMetal(GTBlockMetalVariants.IRIDIUM);

	public static final GTBlockOre
    galenaOre = new GTBlockOre(GTBlockOreVariants.GALENA),
    iridiumOre = new GTBlockOre(GTBlockOreVariants.IRIDIUM),
    rubyOre = new GTBlockOre(GTBlockOreVariants.RUBY),
    sapphireOre = new GTBlockOre(GTBlockOreVariants.SAPPHIRE),
    bauxiteOre = new GTBlockOre(GTBlockOreVariants.BAUXITE),
    pyriteOre = new GTBlockOre(GTBlockOreVariants.PYRITE),
    cinnabarOre = new GTBlockOre(GTBlockOreVariants.CINNABAR),
    sphaleriteOre = new GTBlockOre(GTBlockOreVariants.SPHALERITE),
    tungstateOre = new GTBlockOre(GTBlockOreVariants.TUNGSTATE),
    sheldoniteOre = new GTBlockOre(GTBlockOreVariants.SHELDONITE),
    olivineOre = new GTBlockOre(GTBlockOreVariants.OLIVINE),
    sodaliteOre = new GTBlockOre(GTBlockOreVariants.SODALITE);

	public static final GTBlockSandIron sandIron = new GTBlockSandIron();

	public static final GTBlockTileBasic autoCrafter = new GTBlockTileBasic(
			GTBlockTileBasicVariants.MACHINE_AUTOCRAFTER_LV),
			chargeOMat = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_CHARGEOMAT_EV),
			computerCube = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_COMPUTERCUBE_EV),
			industrialCentrifuge = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_INDUSTRIALCENTRIFUGE_LV),
			matterFabricator = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_MATTERFABRICATOR_EV),
			uuMatterAssembler = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_UUMASSEMBLER_EV),
			playerDetector = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_PLAYERDETECTOR_LV),
			echophoneBlock = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_ECHOPHONE_LV),
			fusionComputer = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_FUSIONCOMPUTER_IV),
			lightningRod = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LIGHTNINGROD_IV),
			IDSU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_QUANTUMENERGYSTORAGE_EV),
			HESU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_BASICENERGYSTORAGE_EV),
			LESU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_MULTIENERGYSTORAGE_MV),
			digitalTransformerIV = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_DIGITALTRANSFORMER_IV),
			energiumWire = new GTBlockTileBasic(GTBlockTileBasicVariants.WIRE_ENERGIUM_LUV),
			lapotronWire = new GTBlockTileBasic(GTBlockTileBasicVariants.WIRE_LAPOTRON_ZPM),
			smallChestLV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_SMALLCHEST_LV),
			largeChestLV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_LARGECHEST_LV),
			digitalChestLV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_DIGITALCHEST_LV),
			bookShelfLV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_BOOKSHELF_LV),
			workBenchLV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_WORKBENCH_LV),
			smallChestMV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_SMALLCHEST_MV),
			largeChestMV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_LARGECHEST_MV),
			digitalChestMV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_DIGITALCHEST_MV),
			bookShelfMV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_BOOKSHELF_MV),
			workBenchMV = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_WORKBENCH_MV);

	public static final GTBlockTileCustom smallCoolant = new GTBlockTileCustom(
			GTBlockTileCustomVariants.COOLANT_HELIUM_SMALL),
			medCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.COOLANT_HELIUM_MED),
			largeCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.COOLANT_HELIUM_LARGE),

			smallThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_THORIUM_SMALL),
			medThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_THORIUM_MED),
			largeThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_THORIUM_LARGE),

			smallPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_PLUTONIUM_SMALL),
			medPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_PLUTONIUM_MED),
			largePlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_PLUTONIUM_LARGE),

			smallLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LITHIUM_SMALL),
			medLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LITHIUM_MED),
			largeLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LITHIUM_LARGE),

			tinyLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_TINY),
			smallLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_SMALL),
			medLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_MED),
			largeLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_LARGE),
			hugeLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_HUGE),

			tinyEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_TINY),
			smallEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_SMALL),
			medEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_MED),
			largeEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_LARGE),
			hugeEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_HUGE);
	
	public static final GTBlockTestLayer testBlock = new GTBlockTestLayer();

	public static final Block[] blocks = {

			ironCasingBlock, aluminiumCasingBlock, titaniumCasingBlock, chromeCasingBlock,
			tungstenCasingBlock, platinumCasingBlock, iridiumCasingBlock,

			superCasingBlock, fusionCasingBlock, fissionCasingBlock, crystalCasingBlock,

			rubyBlock,
            sapphireBlock,
            aluminiumBlock,
            titaniumBlock,
            chromeBlock,
            steelBlock,
            brassBlock,
            leadBlock,
            electrumBlock,
            zincBlock,
            olivineBlock,
            greenSapphireBlock,
            platinumBlock,
            tungstenBlock,
            nickelBlock,
            tungstensteelBlock,
            invarBlock,
            osmiumBlock,
            iridiumBlock,

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

			autoCrafter, chargeOMat, computerCube, industrialCentrifuge, matterFabricator, uuMatterAssembler,
			playerDetector, echophoneBlock, fusionComputer, lightningRod,

			IDSU, HESU, LESU, digitalTransformerIV, energiumWire, lapotronWire,

			digitalChestLV, bookShelfLV, workBenchLV, smallChestLV, largeChestLV,
			digitalChestMV, bookShelfMV, workBenchMV, smallChestMV, largeChestMV,

			smallCoolant, medCoolant, largeCoolant,

			smallThorium, medThorium, largeThorium, smallPlutonium, medPlutonium, largePlutonium,

			smallLithium, medLithium, largeLithium, 
			tinyLapotron, smallLapotron, medLapotron, largeLapotron,hugeLapotron, 
			
			tinyEnergium, smallEnergium, medEnergium, largeEnergium, hugeEnergium,
			
			testBlock,

	};

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		GTMod.logger.info("Registering Blocks");
		for (Block block : blocks) {
			registry.register(block);
		}
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		for (Block block : blocks) {
			registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName())
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