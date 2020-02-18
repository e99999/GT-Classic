package gtclassic;

import org.apache.logging.log4j.Logger;

import gtclassic.api.helpers.GTCommandTeleport;
import gtclassic.api.helpers.GTValues;
import gtclassic.api.material.GTMaterialElement;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.world.GTBedrockOreHandler;
import gtclassic.api.world.GTTwilightForestHandler;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTConfig;
import gtclassic.common.GTCreativeTab;
import gtclassic.common.GTCrops;
import gtclassic.common.GTItems;
import gtclassic.common.GTOreDict;
import gtclassic.common.GTSounds;
import gtclassic.common.GTWorldGen;
import gtclassic.common.event.GTEventCheckSpawn;
import gtclassic.common.event.GTEventEntityViewRenderEvent;
import gtclassic.common.event.GTEventLootTableLoad;
import gtclassic.common.event.GTEventOnLivingFall;
import gtclassic.common.event.GTEventPopulateChunk;
import gtclassic.common.proxy.GTProxyCommon;
import gtclassic.common.recipe.GTRecipe;
import gtclassic.common.recipe.GTRecipeIterators;
import gtclassic.common.recipe.GTRecipeMods;
import gtclassic.common.tile.GTTileCentrifuge;
import gtclassic.common.tile.GTTileDisassembler;
import gtclassic.common.tile.GTTileMagicEnergyConverter;
import gtclassic.common.tile.GTTileMatterFabricator;
import gtclassic.common.tile.GTTileUUMAssembler;
import gtclassic.common.tile.multi.GTTileMultiFusionReactor;
import gtclassic.common.util.GTIDSUStorageManager;
import ic2.core.IC2;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GTMod.MODID, name = GTMod.MODNAME, version = GTMod.MODVERSION, dependencies = GTMod.DEPENDS, useMetadata = true)
public class GTMod {

	public static final String MODID = "gtclassic";
	public static final String MODNAME = "GregTech Classic";
	public static final String MODVERSION = "1.1.1";
	public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod;before:gtc_expansion@[0.0.9,);after:twilightforest@[3.9.984,)";
	public static final CreativeTabs creativeTabGT = new GTCreativeTab(MODID);
	@SidedProxy(clientSide = MODID + ".common.proxy.GTProxyClient", serverSide = MODID + ".common.proxy.GTProxyServer")
	public static GTProxyCommon proxy;
	@Mod.Instance
	public static GTMod instance;
	public static Logger logger;

	public GTMod() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public synchronized void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
		logger.info("Hello from GregTech Classic!");
		GTBlocks.registerTiles();
		GTMaterialGen.initFlags();
		GTMaterialGen.init();
		GTBlocks.registerBlocks();
		GTItems.initItems();
		GTItems.registerItems();
		GTCrops.init();
		GTOreDict.init();
		GTEventLootTableLoad.init();
		MinecraftForge.EVENT_BUS.register(GTSounds.class);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		GTMaterialElement.init();
		GTRecipeIterators.init();
		GTTileCentrifuge.init();
		GTTileUUMAssembler.init();
		GTTileMagicEnergyConverter.init();
		GTRecipe.initShapeless();
		GTRecipe.initItems();
		GTRecipe.initBlocks();
		GTRecipe.initIC2();
		GTRecipe.initIC2Circuits();
		GTRecipe.initIC2Jetpacks();
		GTRecipe.initIC2Overrides();
		GTRecipe.initProcessing();
		GTBedrockOreHandler.bedrockOresInit();
		GameRegistry.registerWorldGenerator(new GTWorldGen(), 0);
		MinecraftForge.EVENT_BUS.register(new GTEventOnLivingFall());
		MinecraftForge.EVENT_BUS.register(new GTEventLootTableLoad());
		MinecraftForge.EVENT_BUS.register(new GTEventCheckSpawn());
		MinecraftForge.EVENT_BUS.register(new GTEventEntityViewRenderEvent());
		MinecraftForge.EVENT_BUS.register(new GTEventPopulateChunk());
		IC2.saveManager.registerGlobal("IDSU_Storage", GTIDSUStorageManager.class, false);
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		GTMaterialGen.postInitProperities();
		GTRecipeIterators.postInit();
		GTTileMatterFabricator.postInit();
		GTTileMultiFusionReactor.postInit();
		GTRecipeMods.postInit();
		GTTileDisassembler.init();
		if (GTConfig.modcompat.compatTwilightForest && Loader.isModLoaded(GTValues.MOD_ID_TFOREST)) {
			GTTwilightForestHandler.initStalactites();
		}
	}

	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(MODID)) {
			ConfigManager.sync(MODID, Config.Type.INSTANCE);
		}
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new GTCommandTeleport());
	}
}
