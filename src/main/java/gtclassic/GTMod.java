package gtclassic;

import gtclassic.commands.CommandTeleport;
import gtclassic.proxy.ProxyCommon;
import gtclassic.world.OreGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = GTMod.MODID, name = GTMod.MODNAME, version = GTMod.MODVERSION, dependencies = "required-after:forge@[14.23.5.2772,)", useMetadata = true)
public class GTMod {

    public static final String MODID = "gtclassic";
    public static final String MODNAME = "GregTech Classic";
    public static final String MODVERSION= "0.0.1";

    @SidedProxy(clientSide = "gtclassic.proxy.ProxyClient", serverSide = "gtclassic.proxy.ProxyServer")
    public static ProxyCommon proxy;

    @Mod.Instance
    public static GTMod instance;
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        // some hello world action
        logger.info("HELLO WORLD!");
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandTeleport());
    }
}
