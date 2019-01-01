package gtclassic;

import gtclassic.block.GTBlockCasing;
import gtclassic.block.GTBlockCasing.GTBlockCasingVariants;
import gtclassic.block.GTBlockEnergy;
import gtclassic.block.GTBlockEnergy.GTBlockEnergyVariants;
import gtclassic.block.GTBlockMachine;
import gtclassic.block.GTBlockMachine.GTBlockMachineVariants;
import gtclassic.block.GTBlockStorage;
import gtclassic.block.GTBlockStorage.GTBlockStorageVariants;
import gtclassic.block.material.GTBlockMetal;
import gtclassic.block.material.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.block.material.GTBlockOre;
import gtclassic.block.material.GTBlockOre.GTBlockOreVariants;
import gtclassic.block.material.GTBlockSandIron;
import gtclassic.block.tileentity.GTTileEntityBookshelf;
import gtclassic.block.tileentity.GTTileEntityComputerCube;
import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import gtclassic.block.tileentity.GTTileEntityHESU;
import gtclassic.block.tileentity.GTTileEntityIDSU;
import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.block.tileentity.GTTileEntityLESU;
import gtclassic.block.tileentity.GTTileEntityLargeChest;
import gtclassic.block.tileentity.GTTileEntityLightningRod;
import gtclassic.block.tileentity.GTTileEntityQuantumChest;
import gtclassic.block.tileentity.GTTileEntitySmallChest;
import gtclassic.block.tileentity.GTTileEntitySuperCondensator;
import gtclassic.block.tileentity.GTTileEntityWorkbench;
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

	public static final GTBlockCasing fusionMachineBlock = new GTBlockCasing(GTBlockCasingVariants.FUSION),
			highlyadvancedMachineBlock = new GTBlockCasing(GTBlockCasingVariants.HIGHLYADVANCED),
			lesuMachineBlock = new GTBlockCasing(GTBlockCasingVariants.LESU);

	public static final GTBlockMetal iridiumReinforcedStoneBlock = new GTBlockMetal(
			GTBlockMetalVariants.IRIDIUM_REINFORCED_STONE), rubyBlock = new GTBlockMetal(GTBlockMetalVariants.RUBY),
			sapphireBlock = new GTBlockMetal(GTBlockMetalVariants.SAPPHIRE),
			aluminiumBlock = new GTBlockMetal(GTBlockMetalVariants.ALUMINIUM),
			chromeBlock = new GTBlockMetal(GTBlockMetalVariants.CHROME),
			titaniumBlock = new GTBlockMetal(GTBlockMetalVariants.TITANIUM);

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

	public static final Block[] blocks = {

			fusionMachineBlock, highlyadvancedMachineBlock, lesuMachineBlock,

			iridiumReinforcedStoneBlock, rubyBlock, sapphireBlock, aluminiumBlock, titaniumBlock, chromeBlock,

			iridiumOre, rubyOre, sapphireOre, bauxiteOre, sandIron, iridiumEnd,

			autoCrafter, chargeOMat, computerCube, industrialCentrifuge, matterFabricator, uuMatterAssembler,
			playerDetector, sonictronBlock, fusionComputer, lightningRod,

			IDSU, HESU, LESU, superCondensator, superConductorWire,

			quantumChest, bookShelf, workBench, smallChest, largeChest

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

		GameRegistry.registerTileEntity(GTTileEntityHESU.class, new ResourceLocation(GTClassic.MODID, "tileHESU"));
		GameRegistry.registerTileEntity(GTTileEntityIDSU.class, new ResourceLocation(GTClassic.MODID, "tileIDSU"));
		GameRegistry.registerTileEntity(GTTileEntityLESU.class, new ResourceLocation(GTClassic.MODID, "tileLESU"));

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