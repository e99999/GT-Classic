package gtclassic.proxy;

import gtclassic.Config;
import gtclassic.ModCore;
import gtclassic.ModBiomes;
import gtclassic.ModBlocks;
import gtclassic.ModDimensions;
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
public class CommonProxy {

    //config instance
    public static Configuration config;

    //create config on pre load
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "GTMod.cfg"));
		Config.readConfig();
        ModDimensions.init();  
    }


    public void init(FMLInitializationEvent e) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(ModCore.instance, new GuiProxy());
    	ModBiomes.init();
    	ModBiomes.initBiomeDict();
    }

    //check if the config is changed and save current on post init
    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }
    
    @SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	
    	//register blocks
    	ModCore.logger.info("Registering Blocks");
    	event.getRegistry().register(new BlockHazard());
        event.getRegistry().register(new BlockCabinet());

        registerTileEntity(TileEntityCabinet.class, "_cabinetblock");
        
        //register ores
        event.getRegistry().register(new BlockSandIron());
        
        //register toxic world stuff
        event.getRegistry().register(new BlockToxicPortalFrame());
        event.getRegistry().register(new BlockToxicGrass());
        event.getRegistry().register(new BlockToxicPortal());
        
    }

    //register items first then register items for blocks
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	
    	//items
    	ModCore.logger.info("Registering Items");
    	event.getRegistry().register(new ItemCreditDoge());
    	event.getRegistry().register(new ItemCreditAlk());
    	event.getRegistry().register(new ItemHammerIron());
    	
    	//blocks as items
    	ModCore.logger.info("Registering Blocks as Items");
    	event.getRegistry().register(new ItemBlock(ModBlocks.blockHazard).setRegistryName(ModBlocks.blockHazard.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.blockCabinet).setRegistryName(ModBlocks.blockCabinet.getRegistryName()));
    	
    	//ore blocks as items
    	event.getRegistry().register(new ItemBlock(ModBlocks.sandIron).setRegistryName(ModBlocks.sandIron.getRegistryName()));
    	
    	//toxic world stuff
    	event.getRegistry().register(new ItemBlock(ModBlocks.toxicPortalFrame).setRegistryName(ModBlocks.toxicPortalFrame.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.grassToxic).setRegistryName(ModBlocks.grassToxic.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.portal).setRegistryName(ModBlocks.portal.getRegistryName()));  	
    }
    
   
    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		GameRegistry.registerTileEntity(tileEntityClass, ModCore.MODID + ":" + name);
	}

    
}
