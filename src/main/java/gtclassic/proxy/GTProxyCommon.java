package gtclassic.proxy;

import gtclassic.GTConfig;
import gtclassic.GTClassic;
import gtclassic.GTBiomes;
import gtclassic.GTDimensions;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@Mod.EventBusSubscriber
public class GTProxyCommon {

    //config instance
    public static Configuration config;

    //create config on pre load
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "GTClassic.cfg"));
		GTConfig.readConfig();
		config.save();
        GTDimensions.init();  
    }


    public void init(FMLInitializationEvent e) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(GTClassic.instance, new GTProxyGui());
    	GTBiomes.init();
    	GTBiomes.initBiomeDict();
    }

    
    public void postInit(FMLPostInitializationEvent e) {
    //temporarily empty post init method
    }   
}
