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
			fissionCasingBlock = new GTBlockCasing(GTBlockCasingVariants.FISSION),
			crystalCasingBlock = new GTBlockCasing(GTBlockCasingVariants.CRYSTAL);

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
			GTBlockTileBasicVariants.MACHINE_AUTOCRAFTER_LV),
			chargeOMat = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_CHARGEOMAT_EV),
			computerCube = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_COMPUTERCUBE_EV),
			industrialCentrifuge = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_INDUSTRIALCENTRIFUGE_LV),
			matterFabricator = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_MATTERFABRICATOR_EV),
			uuMatterAssembler = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_UUMASSEMBLER_EV),
			playerDetector = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_PLAYERDETECTOR_LV),
			sonictronBlock = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_SONICTRON_LV),
			fusionComputer = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_FUSIONCOMPUTER_IV),
			lightningRod = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_LIGHTNINGROD_IV),
			IDSU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_ADVANCEDENERGYSTORAGE_EV),
			HESU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_BASICENERGYSTORAGE_EV),
			LESU = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_MULTIENERGYSTORAGE_MV),
			superCondensator = new GTBlockTileBasic(GTBlockTileBasicVariants.MACHINE_SUPERCONDENSATOR_IV),
			energiumWire = new GTBlockTileBasic(GTBlockTileBasicVariants.WIRE_ENERGIUM_LUV),
			lapotronWire = new GTBlockTileBasic(GTBlockTileBasicVariants.WIRE_LAPOTRON_ZPM),
			smallChest = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_SMALLCHEST_LV),
			largeChest = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_LARGECHEST_LV),
			quantumChest = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_QUANTUMCHEST_LV),
			bookShelf = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_BOOKSHELF_LV),
			workBench = new GTBlockTileBasic(GTBlockTileBasicVariants.TILE_WORKBENCH_LV);

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

	public static final Block[] blocks = {

			ironCasingBlock, aluminiumCasingBlock, titaniumCasingBlock, chromeCasingBlock, iridiumCasingBlock,
			tungstenCasingBlock,

			superCasingBlock, fusionCasingBlock, fissionCasingBlock, crystalCasingBlock,

			rubyBlock, sapphireBlock, aluminiumBlock, titaniumBlock, chromeBlock, iridiumBlock,

			rubyOre, sapphireOre, bauxiteOre, sandIron, iridiumOre, iridiumEnd,

			autoCrafter, chargeOMat, computerCube, industrialCentrifuge, matterFabricator, uuMatterAssembler,
			playerDetector, sonictronBlock, fusionComputer, lightningRod,

			IDSU, HESU, LESU, superCondensator, energiumWire, lapotronWire,

			quantumChest, bookShelf, workBench, smallChest, largeChest,

			smallCoolant, medCoolant, largeCoolant,

			smallThorium, medThorium, largeThorium, smallPlutonium, medPlutonium, largePlutonium,

			smallLithium, medLithium, largeLithium, tinyLapotron, smallLapotron, medLapotron, largeLapotron,
			hugeLapotron, tinyEnergium, smallEnergium, medEnergium, largeEnergium, hugeEnergium,

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
		GameRegistry.registerTileEntity(GTTileEntitySuperCondensator.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySuperCondensator"));

		GameRegistry.registerTileEntity(GTTileEntitySmallChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySmallChest"));
		GameRegistry.registerTileEntity(GTTileEntityLargeChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLargeChest"));
		GameRegistry.registerTileEntity(GTTileEntityQuantumChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityQuantumChest"));
		GameRegistry.registerTileEntity(GTTileEntityBookshelf.class,
				new ResourceLocation(GTMod.MODID, "tileEntityBookshelf"));
		GameRegistry.registerTileEntity(GTTileEntityWorkbench.class,
				new ResourceLocation(GTMod.MODID, "tileEntityWorkbench"));

		GameRegistry.registerTileEntity(GTTileEntityEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityHESU"));
		GameRegistry.registerTileEntity(GTTileEntityDimensionalEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIDSU"));
		GameRegistry.registerTileEntity(GTTileEntityLapotronicEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLESU"));

		GameRegistry.registerTileEntity(GTTileEntityLightningRod.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLightningRod"));
		GameRegistry.registerTileEntity(GTTileEntityFusionComputer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityFusionComputer"));
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		// overloads deprecated method to properly register tiles
		GameRegistry.registerTileEntity(tileEntityClass, GTMod.MODID + ":" + name);
	}
}