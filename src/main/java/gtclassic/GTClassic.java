package gtclassic;

import org.apache.logging.log4j.Logger;

import gtclassic.commands.GTCommandTeleport;
import gtclassic.proxy.GTProxyCommon;
import gtclassic.util.GTCreativeTab;
import gtclassic.util.GTLootHandler;
import gtclassic.util.GTOreDict;
import gtclassic.util.GTRecipes;
import gtclassic.world.GTOreGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GTClassic.MODID, name = GTClassic.MODNAME, version = GTClassic.MODVERSION, dependencies = GTClassic.DEPENDS, useMetadata = true)

public class GTClassic {

	public static final String MODID = "gtclassic";
	public static final String MODNAME = "GregTech Classic";
	public static final String MODVERSION = "@VERSION@";
	public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod;required-after:forge;";
	public static final CreativeTabs creativeTabGT = new GTCreativeTab(MODID);
	@SidedProxy(clientSide = "gtclassic.proxy.GTProxyClient", serverSide = "gtclassic.proxy.GTProxyServer")
	public static GTProxyCommon proxy;

	@Mod.Instance
	public static GTClassic instance;
	public static Logger logger;

	@Mod.EventHandler
	public synchronized void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		GameRegistry.registerWorldGenerator(new GTOreGen(), 0);
		GTOreDict.init();
		GTRecipes.init();
		MinecraftForge.EVENT_BUS.register(new GTLootHandler());
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		logger.info("Hello from Gregtech Classic!");
		proxy.postInit(e);
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new GTCommandTeleport());
	}
}
