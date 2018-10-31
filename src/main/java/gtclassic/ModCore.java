package gtclassic;

import gtclassic.commands.TeleportCommand;
import gtclassic.proxy.CommonProxy;
import gtclassic.world.OreGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModCore.MODID, name = ModCore.MODNAME, version = ModCore.MODVERSION, dependencies = "required-after:forge@[14.23.5.2772,)", useMetadata = true)
public class ModCore {

    public static final String MODID = "gtclassic";
    public static final String MODNAME = "Gregtech Classic";
    public static final String MODVERSION= "0.0.1";

    @SidedProxy(clientSide = "gtclassic.proxy.ClientProxy", serverSide = "gtclassic.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static ModCore instance;
    public static Logger logger;

    //below sets up your mod in 3 stages then triggers each even from common proxy
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        //registers the oregen file
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
        event.registerServerCommand(new TeleportCommand());
    }
}
