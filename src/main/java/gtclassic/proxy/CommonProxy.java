package gtclassic.proxy;

import gtclassic.Config;
import gtclassic.ModCore;
import gtclassic.ModBiomes;
import gtclassic.ModBlocks;
import gtclassic.ModDimensions;
import gtclassic.blocks.HazardBlock;
import gtclassic.blocks.cabinet.CabinetBlock;
import gtclassic.blocks.cabinet.CabinetTileEntity;
import gtclassic.blocks.ores.IronSand;
import gtclassic.items.DogeCoin;
import gtclassic.items.IronMultiTool;
import gtclassic.toxicdimension.blocks.ToxicPortalFrameBlock;
import gtclassic.toxicdimension.items.AlkCoin;
import gtclassic.toxicdimension.blocks.ToxicGrassBlock;
import gtclassic.toxicdimension.blocks.ToxicPortalBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
    	event.getRegistry().register(new HazardBlock());
        event.getRegistry().register(new CabinetBlock());
        //TODO use new method to register this te
        GameRegistry.registerTileEntity(CabinetTileEntity.class, ModCore.MODID + "_cabinetblock");
        
        //register ores
        event.getRegistry().register(new IronSand());
        
        //register toxic world stuff
        event.getRegistry().register(new ToxicPortalFrameBlock());
        event.getRegistry().register(new ToxicGrassBlock());
        event.getRegistry().register(new ToxicPortalBlock());
        
    }

    //register items first then register items for blocks
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	
    	//items
    	ModCore.logger.info("Registering Items");
    	event.getRegistry().register(new DogeCoin());
    	event.getRegistry().register(new AlkCoin());
    	event.getRegistry().register(new IronMultiTool());
    	
    	//blocks as items
    	ModCore.logger.info("Registering Blocks as Items");
    	event.getRegistry().register(new ItemBlock(ModBlocks.hazardBlock).setRegistryName(ModBlocks.hazardBlock.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.cabinetBlock).setRegistryName(ModBlocks.cabinetBlock.getRegistryName()));
    	
    	//ore blocks as items
    	event.getRegistry().register(new ItemBlock(ModBlocks.ironSand).setRegistryName(ModBlocks.ironSand.getRegistryName()));
    	
    	//toxic world stuff
    	event.getRegistry().register(new ItemBlock(ModBlocks.portalFrame).setRegistryName(ModBlocks.portalFrame.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.toxicgrassBlock).setRegistryName(ModBlocks.toxicgrassBlock.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.portal).setRegistryName(ModBlocks.portal.getRegistryName()));  	
    }

    
}
