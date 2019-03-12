package gtclassic;

import org.apache.logging.log4j.Logger;

import gtclassic.material.GTMaterialDict;
import gtclassic.material.GTMaterialGen;
import gtclassic.proxy.GTProxyCommon;
import gtclassic.recipe.GTRecipe;
import gtclassic.tile.GTTileBaseMultiBlockMachine;
import gtclassic.tile.GTTileBaseMultiInputMachine;
import gtclassic.tile.GTTileBlockCustom;
import gtclassic.tile.GTTileBloomery;
import gtclassic.tile.GTTileDigitalChest;
import gtclassic.tile.GTTileLightningRod;
import gtclassic.util.GTCommandTeleport;
import gtclassic.util.GTCreativeTab;
import gtclassic.util.GTLootHandler;
import gtclassic.util.GTValues;
import ic2.api.energy.EnergyNet;
import ic2.api.reactor.IReactor;
import ic2.api.tile.IEnergyStorage;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.block.base.tile.TileEntityTransformer;
import ic2.core.block.crop.TileEntityCrop;
import ic2.core.block.personal.base.misc.IPersonalBlock;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GTMod.MODID, name = GTMod.MODNAME, version = GTMod.MODVERSION, dependencies = GTMod.DEPENDS, useMetadata = true)

public class GTMod {

	public static final String MODID = "gtclassic";
	public static final String MODNAME = "GregTech Classic";
	public static final String MODVERSION = "@VERSION@";
	public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod";
	public static final CreativeTabs creativeTabGT = new GTCreativeTab(MODID);
	@SidedProxy(clientSide = MODID + ".proxy.GTProxyClient", serverSide = MODID + ".proxy.GTProxyServer")
	public static GTProxyCommon proxy;

	@Mod.Instance
	public static GTMod instance;
	public static Logger logger;

	@Mod.EventHandler
	public synchronized void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
		GTBlocks.registerTiles();
		GTMaterialGen.init();
		GTBlocks.registerBlocks();
		GTItems.registerItems();
		GTMaterialDict.init();
		GTOreDict.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		GTRecipe.init();
		GameRegistry.registerWorldGenerator(new GTOreGen(), 0);
		MinecraftForge.EVENT_BUS.register(new GTLootHandler());
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		logger.info("Hello from Gregtech Classic!");
		if (GTValues.debugMode) {
			logger.info(
					"WARNING [Gregtech Classic is still in debug mode, this is very very bad if you are not a dev!]");
		}
		proxy.postInit(e);
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new GTCommandTeleport());
	}

	/*
	 * The logic for both the creative and survival scanners, dont know if this is
	 * the best place but since its helped me construct this entire mod I felt it
	 * earned a place in the main class : )
	 */

	public static EnumActionResult scanBlock(EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, EnumHand hand) {

		TileEntity tileEntity = world.getTileEntity(pos);
		IBlockState state = world.getBlockState(pos);

		if (player.isSneaking() || !IC2.platform.isSimulating()) {
			return EnumActionResult.PASS;
		} else {
			IC2.platform.messagePlayer(player,
					"-----X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ() + " -----");
			IC2.platform.messagePlayer(player, "Name: " + state.getBlock().getLocalizedName());
			IC2.platform.messagePlayer(player, "Block: " + state.getBlock().getUnlocalizedName());
			IC2.platform.messagePlayer(player, "State: " + state);
			IC2.platform.messagePlayer(player, "Hardness: " + state.getBlock().getBlockHardness(state, world, pos));
			IC2.platform.messagePlayer(player,
					"Blast Resistance: " + state.getBlock().getExplosionResistance(null) * 5.0F);
			IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
			if (tileEntity instanceof TileEntityBlock) {
				TileEntityBlock te = (TileEntityBlock) tileEntity;
				IC2.platform.messagePlayer(player, "Active: " + te.getActive());
				IC2.platform.messagePlayer(player, "Facing: " + te.getFacing());
			}
			if (tileEntity instanceof TileEntityGeneratorBase) {
				TileEntityGeneratorBase te2 = (TileEntityGeneratorBase) tileEntity;
				IC2.platform.messagePlayer(player, "Fuel: " + te2.fuel);
				IC2.platform.messagePlayer(player, "Storage: " + te2.storage + " EU");
			}
			if (tileEntity instanceof TileEntityElecMachine) {
				TileEntityElecMachine te3 = (TileEntityElecMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Tier: " + te3.getTier());
				IC2.platform.messagePlayer(player, "Energy: " + te3.energy + " EU");
			}
			if (tileEntity instanceof IEnergyStorage) {
				IEnergyStorage te4 = (IEnergyStorage) tileEntity;
				IC2.platform.messagePlayer(player, "Stored: " + te4.getStored() + " EU");
			}
			if (tileEntity instanceof IReactor) {
				IReactor te5 = (IReactor) tileEntity;
				IC2.platform.messagePlayer(player, "Reactor Heat: " + te5.getHeat());
				IC2.platform.messagePlayer(player, "Max Heat: " + te5.getMaxHeat());
				IC2.platform.messagePlayer(player, "HEM: " + te5.getHeatEffectModifier());
				IC2.platform.messagePlayer(player, "Output: " + te5.getReactorEnergyOutput() + " EU");
			}
			if (tileEntity instanceof IPersonalBlock) {
				IPersonalBlock te6 = (IPersonalBlock) tileEntity;
				IC2.platform.messagePlayer(player, "Can Access: " + te6.canAccess(player.getUniqueID()));
			}
			if (tileEntity instanceof TileEntityCrop) {
				TileEntityCrop te7 = (TileEntityCrop) tileEntity;
				IC2.platform.messagePlayer(player,
						"Crop=" + te7.getCrop() + " Size=" + te7.getCurrentSize() + " Growth=" + te7.getStatGrowth()
								+ " Gain=" + te7.getStatGain() + " Resistance=" + te7.getStatResistance()
								+ " Nutrients=" + te7.getTerrainNutrients() + " Water=" + te7.getTerrainHumidity()
								+ " GrowthPoints=" + te7.getGrowthPoints());
			}

			if (tileEntity instanceof GTTileBaseMultiInputMachine) {
				GTTileBaseMultiInputMachine multi = (GTTileBaseMultiInputMachine) tileEntity;
				IC2.platform.messagePlayer(player,
						"Progress: " + (Math.round((multi.getProgress() / multi.getMaxProgress()) * 100)) + "%");
				IC2.platform.messagePlayer(player, "Default Input: " + multi.defaultEnergyConsume + " EU");
				IC2.platform.messagePlayer(player, "Max Input: " + multi.defaultMaxInput + " EU");
			}

			if (tileEntity instanceof GTTileBaseMultiBlockMachine) {
				GTTileBaseMultiBlockMachine multi = (GTTileBaseMultiBlockMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + multi.checkStructure());
			}

			if (tileEntity instanceof GTTileLightningRod) {
				GTTileLightningRod rod = (GTTileLightningRod) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + rod.checkStructure());
				IC2.platform.messagePlayer(player,
						"Casing Block Amount: " + (rod.casingheight - (rod.getPos().getY() + 1)));
				IC2.platform.messagePlayer(player, "Casing Block Level: " + rod.casingheight);
				IC2.platform.messagePlayer(player, "Weather Height: " + world.getPrecipitationHeight(pos).getY());
				IC2.platform.messagePlayer(player, "Block Up Level: " + (rod.getPos().getY() + 1));
				IC2.platform.messagePlayer(player, "Storm Strength: " + ((int) (world.thunderingStrength) * 100) + "%");
				IC2.platform.messagePlayer(player,
						"1 out of " + rod.chance + " chance to strike based on fence height");
			}

			if (tileEntity instanceof GTTileBloomery) {
				GTTileBloomery bloom = (GTTileBloomery) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + bloom.checkStructure());
				IC2.platform.messagePlayer(player,
						"Progress: " + (Math.round((bloom.getProgress() / bloom.getMaxProgress()) * 100)) + "%");
				IC2.platform.messagePlayer(player, "Recipe State: " + bloom.getActiveRecipe());
			}

			if (tileEntity instanceof GTTileDigitalChest) {
				GTTileDigitalChest chest = (GTTileDigitalChest) tileEntity;
				IC2.platform.messagePlayer(player, "Display Count: " + chest.getDisplayCount());
				IC2.platform.messagePlayer(player, "Internal Count: " + chest.getQuantumCount());
			}

			if (tileEntity instanceof TileEntityElectricBlock) {
				TileEntityElectricBlock eu = (TileEntityElectricBlock) tileEntity;
				IC2.platform.messagePlayer(player, "Tier: " + eu.getTier());
				IC2.platform.messagePlayer(player, "Capacity: " + eu.getMaxEU() + " EU");
				IC2.platform.messagePlayer(player, "Output: " + eu.getOutput() + " EU");
			}

			if (tileEntity instanceof TileEntityTransformer) {
				TileEntityTransformer transformer = (TileEntityTransformer) tileEntity;
				IC2.platform.messagePlayer(player, "Low: " + transformer.lowOutput + " EU");
				IC2.platform.messagePlayer(player,
						"Low Tier: " + EnergyNet.instance.getTierFromPower((double) transformer.lowOutput));
				IC2.platform.messagePlayer(player, "High: " + transformer.highOutput + " EU");
				IC2.platform.messagePlayer(player,
						"High Tier: " + EnergyNet.instance.getTierFromPower((double) transformer.highOutput));
			}

			if (tileEntity instanceof GTTileBlockCustom) {
				GTTileBlockCustom blockcustom = (GTTileBlockCustom) tileEntity;
				IC2.platform.messagePlayer(player, "Stack Stored: " + blockcustom.getItem().getUnlocalizedName());
			}

			return EnumActionResult.SUCCESS;

		}

	}
}
