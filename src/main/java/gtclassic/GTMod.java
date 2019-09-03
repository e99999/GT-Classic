package gtclassic;

import org.apache.logging.log4j.Logger;

import gtclassic.events.GTEventCheckSpawn;
import gtclassic.events.GTEventEntityViewRenderEvent;
import gtclassic.events.GTEventLootTableLoad;
import gtclassic.events.GTEventOnLivingFall;
import gtclassic.events.GTEventPopulateChunk;
import gtclassic.material.GTMaterialGen;
import gtclassic.proxy.GTProxyCommon;
import gtclassic.recipe.GTRecipe;
import gtclassic.recipe.GTRecipeIterators;
import gtclassic.recipe.GTRecipeMods;
import gtclassic.recipe.GTRecipeProcessing;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.GTTileMatterFabricator;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.tile.multi.GTTileMultiFusionReactor;
import gtclassic.util.GTCommandTeleport;
import gtclassic.util.GTCreativeTab;
import gtclassic.util.energy.IDSUStorage;
import gtclassic.util.energy.MultiBlockHelper;
import gtclassic.worldgen.GTWorldGenOreOcean;
import ic2.core.IC2;
import net.minecraft.creativetab.CreativeTabs;
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
	public static final String MODVERSION = "1.0.3";
	public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod";
	public static final CreativeTabs creativeTabGT = new GTCreativeTab(MODID);
	@SidedProxy(clientSide = MODID + ".proxy.GTProxyClient", serverSide = MODID + ".proxy.GTProxyServer")
	public static GTProxyCommon proxy;
	@Mod.Instance
	public static GTMod instance;
	public static Logger logger;
	private static boolean quickDebug = false;
	public static boolean debugMode = GTConfig.debugMode || quickDebug;

	@Mod.EventHandler
	public synchronized void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
		logger.info("Hello from GregTech Classic!");
		GTFluids.registerFluids();
		GTBlocks.registerTiles();
		GTMaterialGen.init();
		GTBlocks.registerBlocks();
		GTItems.initBaubleItems();
		GTItems.registerItems();
		GTOreDict.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		GTRecipeIterators.init();
		GTRecipeProcessing.init();
		GTTileCentrifuge.init();
		GTTileMultiBlastFurnace.init();
		GTTileMultiBlastFurnace.removals();
		GTRecipe.initShapeless();
		GTRecipe.initItems();
		GTRecipe.initBlocks();
		GTRecipe.initIC2();
		GTWorldGenOreOcean.initDepositOres();
		GameRegistry.registerWorldGenerator(new GTWorldGen(), 0);
		MinecraftForge.EVENT_BUS.register(new GTEventOnLivingFall());
		MinecraftForge.EVENT_BUS.register(new GTEventLootTableLoad());
		MinecraftForge.EVENT_BUS.register(new GTEventCheckSpawn());
		MinecraftForge.EVENT_BUS.register(new GTEventEntityViewRenderEvent());
		MinecraftForge.EVENT_BUS.register(new GTEventPopulateChunk());
		// MinecraftForge.EVENT_BUS.register(new GTEventPlayerTick());
		IC2.saveManager.registerGlobal("IDSU_Storage", IDSUStorage.class, false);
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		MultiBlockHelper.INSTANCE.init();
		GTRecipeIterators.postInit();
		GTTileMatterFabricator.postInit();
		GTTileMultiFusionReactor.postInit();
		GTTileMultiBlastFurnace.removals();
		GTRecipeMods.postInit();
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new GTCommandTeleport());
	}

	/**
	 * A logger that will only work if debug mode is enabled
	 * 
	 * @param message
	 */
	public static void debugLogger(String message) {
		if (debugMode) {
			logger.info(message);
		}
	}
}
