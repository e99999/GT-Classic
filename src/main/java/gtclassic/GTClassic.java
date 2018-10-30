package gtclassic;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = GTClassic.MODID, name = GTClassic.NAME, version = GTClassic.VERSION, dependencies = "required-after:forge@[14.23.5.2772,)", useMetadata = true)
public class GTClassic
{
    public static final String MODID = "gtclassic";
    public static final String NAME = "Gregtech Classic";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	// some hello world action
        logger.info("HELLO WORLD!");
    }
}
