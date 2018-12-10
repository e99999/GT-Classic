package gtclassic.util;

import gtclassic.GTClassic;
import gtclassic.blocks.GTBlockCasing;
import gtclassic.blocks.GTBlockCasing.GTBlockCasingVariants;
import gtclassic.blocks.GTBlockEnergy;
import gtclassic.blocks.GTBlockEnergy.GTBlockEnergyVariants;
import gtclassic.blocks.GTBlockGenerator;
import gtclassic.blocks.GTBlockGenerator.GTBlockGeneratorVariants;
import gtclassic.blocks.GTBlockMachine;
import gtclassic.blocks.GTBlockMachine.GTBlockMachineVariants;
import gtclassic.blocks.GTBlockStorage;
import gtclassic.blocks.GTBlockStorage.GTBlockStorageVariants;
import gtclassic.blocks.resources.GTBlockMetal;
import gtclassic.blocks.resources.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.blocks.resources.GTBlockOre;
import gtclassic.blocks.resources.GTBlockOre.GTBlockOreVariants;
import gtclassic.blocks.resources.GTBlockSandIron;
import gtclassic.tileentity.GTTileEntityComputerCube;
import gtclassic.tileentity.GTTileEntityFusionComputer;
import gtclassic.tileentity.GTTileEntityHESU;
import gtclassic.tileentity.GTTileEntityIDSU;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.tileentity.GTTileEntityLargeChest;
import gtclassic.tileentity.GTTileEntityQuantumChest;
import gtclassic.tileentity.GTTileEntitySmallChest;
import gtclassic.tileentity.GTTileEntitySuperCondensator;
import gtclassic.toxicdimension.blocks.GTBlockToxicGrass;
import gtclassic.toxicdimension.blocks.GTBlockToxicPortal;
import gtclassic.toxicdimension.blocks.GTBlockToxicPortalFrame;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class GTBlocks {

	public static final GTBlockCasing fusionMachineBlock = new GTBlockCasing(GTBlockCasingVariants.FUSION),
			highlyadvancedMachineBlock = new GTBlockCasing(GTBlockCasingVariants.HIGHLYADVANCED);

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
			sonictronBlock = new GTBlockMachine(GTBlockMachineVariants.SONICTRON);

	public static final GTBlockStorage smallChest = new GTBlockStorage(GTBlockStorageVariants.SMALLCHEST),
			largeChest = new GTBlockStorage(GTBlockStorageVariants.LARGECHEST),
			quantumChest = new GTBlockStorage(GTBlockStorageVariants.QUANTUMCHEST);

	public static final GTBlockEnergy IDSU = new GTBlockEnergy(GTBlockEnergyVariants.IDSU),
			HESU = new GTBlockEnergy(GTBlockEnergyVariants.HESU),
			superCondensator = new GTBlockEnergy(GTBlockEnergyVariants.SUPERCONDENSATOR),
			superConductorWire = new GTBlockEnergy(GTBlockEnergyVariants.SUPERCONDUCTORWIRE);

	public static final GTBlockGenerator fusionComputer = new GTBlockGenerator(GTBlockGeneratorVariants.FUSIONCOMPUTER),
			lightningRod = new GTBlockGenerator(GTBlockGeneratorVariants.LIGHTNINGROD);

	public static final GTBlockToxicPortalFrame toxicPortalFrame = new GTBlockToxicPortalFrame();
	public static final GTBlockToxicPortal toxicPortal = new GTBlockToxicPortal();
	public static final GTBlockToxicGrass grassToxic = new GTBlockToxicGrass();

	public static final Block[] blocks = {

			fusionMachineBlock, highlyadvancedMachineBlock,

			iridiumReinforcedStoneBlock, rubyBlock, sapphireBlock, aluminiumBlock, titaniumBlock, chromeBlock,

			iridiumOre, rubyOre, sapphireOre, bauxiteOre, sandIron, iridiumEnd,

			autoCrafter, chargeOMat, computerCube, industrialCentrifuge, matterFabricator, uuMatterAssembler,
			playerDetector, sonictronBlock,

			smallChest, largeChest, quantumChest,

			fusionComputer, lightningRod, IDSU, HESU, superCondensator, superConductorWire,

			toxicPortalFrame, toxicPortal, grassToxic

	};

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry registry = event.getRegistry();
		for (Block block : blocks) {
			registry.register(block);
		}
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry registry = event.getRegistry();
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

		GameRegistry.registerTileEntity(GTTileEntityHESU.class, new ResourceLocation(GTClassic.MODID, "tileHESU"));
		GameRegistry.registerTileEntity(GTTileEntityIDSU.class, new ResourceLocation(GTClassic.MODID, "tileIDSU"));

		GameRegistry.registerTileEntity(GTTileEntityFusionComputer.class,
				new ResourceLocation(GTClassic.MODID, "tileFusionReactor"));
	}

	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		// overloads deprecated method to properly register tiles
		GameRegistry.registerTileEntity(tileEntityClass, GTClassic.MODID + ":" + name);
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		toxicPortal.initModel();
		grassToxic.initModel();
	}
}