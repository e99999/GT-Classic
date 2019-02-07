package gtclassic;

import gtclassic.item.GTItemComponents;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemEchophone;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolChainsaw;
import gtclassic.tool.GTToolFile;
import gtclassic.tool.GTToolHammer;
import gtclassic.tool.GTToolMiningDrill;
import gtclassic.tool.GTToolRockCutter;
import gtclassic.util.GTBatteryBuilder;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class GTItems {

	public static final GTItemComponents glassTube = new GTItemComponents("glassTube", 0, false),
			bouleSilicon = new GTItemComponents("bouleSilicon", 32, false),
			platePlastic = new GTItemComponents("platePlastic", 34, false),
			lensDiamond = new GTItemComponents("lensDiamond", 35, true),
			lensRuby = new GTItemComponents("lensRuby", 36, true),
			lensEmerald = new GTItemComponents("lensEmerald", 37, true),
			lensSapphire = new GTItemComponents("lensSapphire", 38, true),
			chipDiamond = new GTItemComponents("chipDiamond", 39, false),
			chipRuby = new GTItemComponents("chipRuby", 40, false),
			chipEmerald = new GTItemComponents("chipEmerald", 41, false),
			chipSapphire = new GTItemComponents("chipSapphire", 42, false),
			circuitEmpty = new GTItemComponents("circuitEmpty", 43, false),
			circuitDiamond = new GTItemComponents("circuitDiamond", 44, false),
			circuitRuby = new GTItemComponents("circuitRuby", 45, false),
			circuitEmerald = new GTItemComponents("circuitEmerald", 46, false),
			circuitSapphire = new GTItemComponents("circutSapphire", 47, false);

	public static final GTItemDuctTape braintechAerospaceARDT = new GTItemDuctTape();
	public static final GTItemEchophone sonictronItem = new GTItemEchophone();
	public static final GTItemDestructoPack destructoPack = new GTItemDestructoPack();
	public static final GTItemCraftingTablet craftingTablet = new GTItemCraftingTablet();

	public static final GTToolFile fileIron = new GTToolFile(GTMaterial.Iron);
	public static final GTToolFile fileAluminium = new GTToolFile(GTMaterial.Aluminium);
	public static final GTToolFile fileTitanium = new GTToolFile(GTMaterial.Titanium);
	public static final GTToolFile fileTungstenSteel = new GTToolFile(GTMaterial.TungstenSteel);

	public static final GTToolHammer hammerIron = new GTToolHammer(GTMaterial.Iron);
	public static final GTToolHammer hammerAluminium = new GTToolHammer(GTMaterial.Aluminium);
	public static final GTToolHammer hammerTitanium = new GTToolHammer(GTMaterial.Titanium);
	public static final GTToolHammer hammerTungstenSteel = new GTToolHammer(GTMaterial.TungstenSteel);

	public static final GTToolMiningDrill advancedDrill = new GTToolMiningDrill(GTMaterial.Diamond, 100000, 128, 1);
	public static final GTToolMiningDrill advancedDrill2 = new GTToolMiningDrill(GTMaterial.Diamond, 200000, 256, 2);
	public static final GTToolMiningDrill advancedDrill3 = new GTToolMiningDrill(GTMaterial.Diamond, 400000, 512, 3);

	public static final GTToolChainsaw advancedChainsaw = new GTToolChainsaw(GTMaterial.Steel, 100000, 128, 1);
	public static final GTToolChainsaw advancedChainsaw2 = new GTToolChainsaw(GTMaterial.Titanium, 200000, 256, 2);
	public static final GTToolChainsaw advancedChainsaw3 = new GTToolChainsaw(GTMaterial.TungstenSteel, 400000, 512, 3);

	public static final GTToolRockCutter rockCutter = new GTToolRockCutter(GTMaterial.Diamond, 10000, 100, 1);
	public static final GTToolRockCutter rockCutter2 = new GTToolRockCutter(GTMaterial.Diamond, 100000, 256, 2);
	public static final GTToolRockCutter rockCutter3 = new GTToolRockCutter(GTMaterial.Diamond, 1000000, 1024, 3);

	public static final GTItemElectromagnet electroMagnet = new GTItemElectromagnet();
	public static final GTItemTeslaStaff teslaStaff = new GTItemTeslaStaff();

	public static final GTItemCreativeScanner debugScanner = new GTItemCreativeScanner();
	public static final GTItemSurvivalScanner portableScanner = new GTItemSurvivalScanner();

	public static final GTBatteryBuilder smallLithium = new GTBatteryBuilder(GTBlocks.smallLithium, 100000, 128, 1),
			medLithium = new GTBatteryBuilder(GTBlocks.medLithium, 200000, 256, 2),
			largeLithium = new GTBatteryBuilder(GTBlocks.largeLithium, 400000, 512, 3),

			tinyEnergium = new GTBatteryBuilder(GTBlocks.tinyEnergium, 100000, 256, 2),
			smallEnergium = new GTBatteryBuilder(GTBlocks.smallEnergium, 1000000, 512, 3),
			medEnergium = new GTBatteryBuilder(GTBlocks.medEnergium, 10000000, 1024, 4),
			largeEnergium = new GTBatteryBuilder(GTBlocks.largeEnergium, 100000000, 4096, 5),
			hugeEnergium = new GTBatteryBuilder(GTBlocks.hugeEnergium, 1000000000, 8192, 6),

			tinyLapotron = new GTBatteryBuilder(GTBlocks.tinyLapotron, 1000000, 1024, 3),
			smallLapotron = new GTBatteryBuilder(GTBlocks.smallLapotron, 10000000, 4096, 4),
			medLapotron = new GTBatteryBuilder(GTBlocks.medLapotron, 100000000, 8192, 5),
			largeLapotron = new GTBatteryBuilder(GTBlocks.largeLapotron, 1000000000, 16384, 6),
			hugeLapotron = new GTBatteryBuilder(GTBlocks.hugeLapotron, Integer.MAX_VALUE, 32768, 7);

	public static final Item[] items = {

			glassTube,
			bouleSilicon,
			platePlastic,
			lensDiamond,
			lensRuby,
			lensEmerald,
			lensSapphire,
			chipDiamond,
			chipRuby,
			chipEmerald,
			chipSapphire,
			circuitEmpty,
			circuitDiamond,
			circuitRuby,
			circuitEmerald,
			circuitSapphire,

			fileIron,
			fileAluminium,
			fileTitanium,
			fileTungstenSteel,

			hammerIron,
			hammerAluminium,
			hammerTitanium,
			hammerTungstenSteel,

			advancedDrill,
			advancedDrill2,
			advancedDrill3,

			advancedChainsaw,
			advancedChainsaw2,
			advancedChainsaw3,

			rockCutter,
			rockCutter2,
			rockCutter3,

			destructoPack,
			craftingTablet,
			braintechAerospaceARDT,
			sonictronItem,
			electroMagnet,
			teslaStaff,
			portableScanner,
			debugScanner,

			smallLithium,
			medLithium,
			largeLithium,

			tinyLapotron,
			smallLapotron,
			medLapotron,
			largeLapotron,
			hugeLapotron,

			tinyEnergium,
			smallEnergium,
			medEnergium,
			largeEnergium,
			hugeEnergium,

	};

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		GTMod.logger.info("Registering Items");

		// Registers all materials with item based flags as materials
		for (Item item : GTMaterialGen.itemMap.values()) {
			registry.register(item);
		}
		// Registers static item references in this class
		for (Item item : items) {
			registry.register(item);
		}
	}

}
