package gtclassic;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import gtclassic.proxy.GTProxyCommon;
import gtclassic.util.GTCommandTeleport;
import gtclassic.util.GTCreativeTab;
import gtclassic.util.GTLootHandler;
import gtclassic.util.GTOreDict;
import gtclassic.util.GTValues;
import gtclassic.util.MyColorInterface;
import gtclassic.util.MyColors;
import ic2.api.classic.addon.misc.IOverrideObject;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.util.obj.IBootable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
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
	public static Map<String, IOverrideObject> overrides = new HashMap();

	@Mod.Instance
	public static GTMod instance;
	public static Logger logger;

	@Mod.EventHandler
	public synchronized void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
		GTBlocks.registerTiles();
		MinecraftForge.EVENT_BUS.register(GTBlocks.class);
		MinecraftForge.EVENT_BUS.register(GTItems.class);
		collectItems();
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
		if (GTValues.debugMode) {
			logger.info(
					"WARNING [Gregtech Classic is still in debug mode, this is very very bad if you are not a dev!]");
		}
		proxy.postInit(e);
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new GTCommandTeleport());
	}
	
	public static void collectItems()
	{
	    MyColors colors = new MyColors();
	    ItemColors registry = Minecraft.getMinecraft().getItemColors();
	    for(Item item : Item.REGISTRY)
	    {
	        if(item instanceof MyColorInterface)
	        {
	            registry.registerItemColorHandler(colors, item);
	        }
	    }
	}

	// TODO unused in prep for changing item/block registration
	public Block createBlock(Block par1, Class<? extends ItemBlockRare> par2) {
		return this.createBlock(par1, par2, false);
	}

	// TODO unused in prep for changing item/block registration
	public Block createBlock(Block par1, Class<? extends ItemBlockRare> par2, boolean subType) {
		String replace = par1.getUnlocalizedName().replace("tile.", "");
		if (overrides.containsKey(replace)) {

			IOverrideObject obj = (IOverrideObject) overrides.remove(replace);
			return this.createBlock(obj.getBlock(), (Class<? extends ItemBlockRare>) obj.getItemBlock());

		} else {
			ForgeRegistries.BLOCKS.register(par1.setRegistryName(MODID, replace));

			try {
				ItemBlockRare item = (ItemBlockRare) par2.getConstructor(Block.class).newInstance(par1);
				if (subType) {

					item.setHasSubtypes(true);
				}
				if (par1 instanceof ILocaleBlock) {

					item.setUnlocalizedName(((ILocaleBlock) par1).getName());
				}
				ForgeRegistries.ITEMS.register(item.setRegistryName(par1.getRegistryName()));

			} catch (Exception var6) {
				;
			}

			if (par1 instanceof IBootable) {

				((IBootable) par1).onLoad();
			}
			return par1;
		}
	}

	// TODO unused in prep for changing item/block registration
	public Item createItem(Item par1) {
		String replace = par1.getUnlocalizedName().replace("item.", "");
		if (overrides.containsKey(replace)) {

			IOverrideObject obj = (IOverrideObject) overrides.remove(replace);
			return this.createItem(obj.getItem());
		} else {
			ForgeRegistries.ITEMS.register(par1.setRegistryName(MODID, replace));
			if (par1 instanceof IBootable) {

				((IBootable) par1).onLoad();
			}
			return par1;
		}
	}
}
