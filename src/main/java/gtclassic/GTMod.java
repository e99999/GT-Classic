package gtclassic;

import org.apache.logging.log4j.Logger;

import gtclassic.material.GTMaterialDict;
import gtclassic.material.GTMaterialGen;
import gtclassic.proxy.GTProxyCommon;
import gtclassic.recipe.GTRecipe;
import gtclassic.util.GTCommandTeleport;
import gtclassic.util.GTCreativeTab;
import gtclassic.util.GTLootHandler;
import gtclassic.util.energy.MultiBlockHelper;
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
		logger.info("Hello from Gregtech Classic!");
		GTFluids.registerFluids();
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
		GameRegistry.registerWorldGenerator(new GTWorldGen(), 0);
		MinecraftForge.EVENT_BUS.register(new GTLootHandler());
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		MultiBlockHelper.INSTANCE.init();
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new GTCommandTeleport());
	}
}
