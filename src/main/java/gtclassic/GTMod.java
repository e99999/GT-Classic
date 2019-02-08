package gtclassic;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import gtclassic.color.GTColorBlock;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItem;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterialDict;
import gtclassic.material.GTMaterialGen;
import gtclassic.proxy.GTProxyCommon;
import gtclassic.recipe.GTRecipe;
import gtclassic.util.GTCommandTeleport;
import gtclassic.util.GTCreativeTab;
import gtclassic.util.GTLootHandler;
import gtclassic.util.GTOreDict;
import gtclassic.util.GTValues;
import ic2.api.classic.addon.misc.IOverrideObject;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
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
		GTMaterialGen.init();
		MinecraftForge.EVENT_BUS.register(GTBlocks.class);
		MinecraftForge.EVENT_BUS.register(GTItems.class);
		GTMaterialDict.init();
		GTOreDict.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		registerTintedBlocks();
		registerTintedItems();
		GTRecipe.init();
		GameRegistry.registerWorldGenerator(new GTOreGen(), 0);
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

	public static void registerTintedItems() {
		GTColorItem colors = new GTColorItem();
		ItemColors registry = Minecraft.getMinecraft().getItemColors();
		for (Item item : Item.REGISTRY) {
			if (item instanceof GTColorItemInterface) {
				if (GTValues.debugMode) {
					logger.info("Registering item color modification:" + item.getUnlocalizedName());
				}
				registry.registerItemColorHandler(colors, item);
			}
		}
	}

	public static void registerTintedBlocks() {
		GTColorBlock colors = new GTColorBlock();
		BlockColors registry = Minecraft.getMinecraft().getBlockColors();
		for (Block block : Block.REGISTRY) {
			if (block instanceof GTColorBlockInterface) {
				if (GTValues.debugMode) {
					logger.info("Registering block color modification:" + block.getUnlocalizedName());
				}
				registry.registerBlockColorHandler(colors, block);
			}
		}
	}
}
