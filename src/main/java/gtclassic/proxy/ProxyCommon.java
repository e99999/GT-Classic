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


//in many cases you can use CommonProxy for the server side since most things you want to init on the server you have to init client side as well 

@Mod.EventBusSubscriber
public class ProxyCommon {

    //config instance
    public static Configuration config;

    //create config on pre load
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "GTClassic.cfg"));
		GTConfig.readConfig();
        GTDimensions.init();  
    }


    public void init(FMLInitializationEvent e) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(GTClassic.instance, new ProxyGui());
    	GTBiomes.init();
    	GTBiomes.initBiomeDict();
    }

    //check if the config is changed and save current on post init
    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }
    
//	@SubscribeEvent
//    public static void registerBlocks(RegistryEvent.Register<Block> event) {
//
//    	//register blocks
//    	GTClassic.logger.info("Registering Blocks");
//    	event.getRegistry().register(new GTBlockHazard());
//        event.getRegistry().register(new GTBlockCabinet());
//
//        registerTileEntity(TileEntityCabinet.class, "_cabinetblock");
//
//        //register resources
//        event.getRegistry().register(new GTBlockSandIron());
//
//        //register toxic world stuff
//        event.getRegistry().register(new BlockToxicPortalFrame());
//        event.getRegistry().register(new BlockToxicPortal());
//        event.getRegistry().register(new BlockToxicGrass());
//
//    }
//
//    //register items first then register items for blocks
//    @SubscribeEvent
//    public static void registerItems(RegistryEvent.Register<Item> event) {
//
//    	//items
//    	GTClassic.logger.info("Registering Items");
//    	event.getRegistry().register(new GTItemCreditDoge());
//    	event.getRegistry().register(new GTItemCreditAlk());
//    	event.getRegistry().register(new GTItemHammerIron());
//
//    	//blocks as items
//    	GTClassic.logger.info("Registering Blocks as Items");
//    	event.getRegistry().register(new ItemBlock(GTBlocks.blockHazard).setRegistryName(GTBlocks.blockHazard.getRegistryName()));
//    	event.getRegistry().register(new ItemBlock(GTBlocks.blockCabinet).setRegistryName(GTBlocks.blockCabinet.getRegistryName()));
//
//    	//ore blocks as items
//    	event.getRegistry().register(new ItemBlock(GTBlocks.sandIron).setRegistryName(GTBlocks.sandIron.getRegistryName()));
//
//    	//toxic world stuff
//    	event.getRegistry().register(new ItemBlock(GTBlocks.toxicPortalFrame).setRegistryName(GTBlocks.toxicPortalFrame.getRegistryName()));
//    	event.getRegistry().register(new ItemBlock(GTBlocks.toxicPortal).setRegistryName(GTBlocks.toxicPortal.getRegistryName()));
//    	event.getRegistry().register(new ItemBlock(GTBlocks.grassToxic).setRegistryName(GTBlocks.grassToxic.getRegistryName()));
//    }
//
//
//    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
//		GameRegistry.registerTileEntity(tileEntityClass, GTClassic.MODID + ":" + name);
//	}

    
}
