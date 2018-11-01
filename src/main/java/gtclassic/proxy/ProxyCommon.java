package gtclassic.proxy;

import gtclassic.GTConfig;
import gtclassic.GTMod;
import gtclassic.GTBiomes;
import gtclassic.GTBlocks;
import gtclassic.GTDimensions;
import gtclassic.blocks.BlockHazard;
import gtclassic.blocks.cabinet.BlockCabinet;
import gtclassic.blocks.cabinet.TileEntityCabinet;
import gtclassic.blocks.ores.BlockSandIron;
import gtclassic.items.ItemCreditAlk;
import gtclassic.items.ItemCreditDoge;
import gtclassic.items.ItemHammerIron;
import gtclassic.toxicdimension.blocks.BlockToxicPortalFrame;
import gtclassic.toxicdimension.blocks.BlockToxicGrass;
import gtclassic.toxicdimension.blocks.BlockToxicPortal;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;


//in many cases you can use CommonProxy for the server side since most things you want to init on the server you have to init client side as well 

@Mod.EventBusSubscriber
public class ProxyCommon {

    //config instance
    public static Configuration config;

    //create config on pre load
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "GTMod.cfg"));
		GTConfig.readConfig();
        GTDimensions.init();  
    }


    public void init(FMLInitializationEvent e) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(GTMod.instance, new ProxyGui());
    	GTBiomes.init();
    	GTBiomes.initBiomeDict();
    }

    //check if the config is changed and save current on post init
    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }
    
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	
    	//register blocks
    	GTMod.logger.info("Registering Blocks");
    	event.getRegistry().register(new BlockHazard());
        event.getRegistry().register(new BlockCabinet());

        registerTileEntity(TileEntityCabinet.class, "_cabinetblock");
        
        //register ores
        event.getRegistry().register(new BlockSandIron());
        
        //register toxic world stuff
        event.getRegistry().register(new BlockToxicPortalFrame());
        event.getRegistry().register(new BlockToxicPortal());
        event.getRegistry().register(new BlockToxicGrass());
        
    }

    //register items first then register items for blocks
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	
    	//items
    	GTMod.logger.info("Registering Items");
    	event.getRegistry().register(new ItemCreditDoge());
    	event.getRegistry().register(new ItemCreditAlk());
    	event.getRegistry().register(new ItemHammerIron());
    	
    	//blocks as items
    	GTMod.logger.info("Registering Blocks as Items");
    	event.getRegistry().register(new ItemBlock(GTBlocks.blockHazard).setRegistryName(GTBlocks.blockHazard.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(GTBlocks.blockCabinet).setRegistryName(GTBlocks.blockCabinet.getRegistryName()));
    	
    	//ore blocks as items
    	event.getRegistry().register(new ItemBlock(GTBlocks.sandIron).setRegistryName(GTBlocks.sandIron.getRegistryName()));
    	
    	//toxic world stuff
    	event.getRegistry().register(new ItemBlock(GTBlocks.toxicPortalFrame).setRegistryName(GTBlocks.toxicPortalFrame.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(GTBlocks.toxicPortal).setRegistryName(GTBlocks.toxicPortal.getRegistryName()));  
    	event.getRegistry().register(new ItemBlock(GTBlocks.grassToxic).setRegistryName(GTBlocks.grassToxic.getRegistryName()));	
    }
    
   
    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		GameRegistry.registerTileEntity(tileEntityClass, GTMod.MODID + ":" + name);
	}

    
}
