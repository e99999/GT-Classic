package gtclassic;

import org.apache.logging.log4j.Logger;

import gtclassic.item.GTItemReactorRod;
import gtclassic.material.GTMaterialDict;
import gtclassic.material.GTMaterialGen;
import gtclassic.proxy.GTProxyCommon;
import gtclassic.recipe.GTRecipe;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.util.GTCommandTeleport;
import gtclassic.util.GTCreativeTab;
import gtclassic.util.GTLootHandler;
import gtclassic.util.energy.IDSUStorage;
import gtclassic.util.energy.MultiBlockHelper;
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
	public static final String MODVERSION = "0.0.9";
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
		logger.info("Hello from Gregtech Classic!");
		GTFluids.registerFluids();
		GTBlocks.registerTiles();
		GTMaterialGen.init();
		GTBlocks.registerBlocks();
		GTItemReactorRod.init();
		GTItems.initBaubleItems();
		GTItems.registerItems();
		GTMaterialDict.init();
		GTOreDict.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		GTRecipe.init();
		GameRegistry.registerWorldGenerator(new GTWorldGen(), 0);
		MinecraftForge.EVENT_BUS.register(new GTLootHandler());
		IC2.saveManager.registerGlobal("IDSU_Storage", IDSUStorage.class, false);
		GTTileMultiBlastFurnace.removals();
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		MultiBlockHelper.INSTANCE.init();
		GTTileMultiBlastFurnace.removals();
		GTRecipe.postInit();
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		if (debugMode) {
			event.registerServerCommand(new GTCommandTeleport());
		}
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
