package gtclassic;

import gtclassic.item.GTItemChainsaw;
import gtclassic.item.GTItemDrill;
import gtclassic.item.GTItemComponents;
import gtclassic.item.GTItemComponents.GTItemComponentTypes;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemDust;
import gtclassic.item.GTItemDust.GTItemDustTypes;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemElement;
import gtclassic.item.GTItemElement.GTItemElementTypes;
import gtclassic.item.GTItemEnergyPack;
import gtclassic.item.GTItemGem;
import gtclassic.item.GTItemGem.GTItemGemTypes;
import gtclassic.item.GTItemHammerIron;
import gtclassic.item.GTItemIngot;
import gtclassic.item.GTItemIngot.GTItemIngotTypes;
import gtclassic.item.GTItemPlasma;
import gtclassic.item.GTItemPlasma.GTItemPlasmaTypes;
import gtclassic.item.GTItemReactorHeat;
import gtclassic.item.GTItemReactorHeat.GTItemHeatStorageTypes;
import gtclassic.item.GTItemReactorRod;
import gtclassic.item.GTItemReactorRod.GTItemRodTypes;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSonictron;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.util.GTBatteryBuilder;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class GTItems {

	public static final GTItemElement hydrogen = new GTItemElement(GTItemElementTypes.HYDROGEN),
			dueterium = new GTItemElement(GTItemElementTypes.DEUTERIUM),
			tritium = new GTItemElement(GTItemElementTypes.TRITIUM),
			helium = new GTItemElement(GTItemElementTypes.HELIUM),
			tungsten = new GTItemElement(GTItemElementTypes.TUNGSTEN),
			lithium = new GTItemElement(GTItemElementTypes.LITHIUM),
			helium3 = new GTItemElement(GTItemElementTypes.HELIUM3),
			silicon = new GTItemElement(GTItemElementTypes.SILICON),
			carbon = new GTItemElement(GTItemElementTypes.CARBON),
			methane = new GTItemElement(GTItemElementTypes.METHANE),
			berilium = new GTItemElement(GTItemElementTypes.BERILIUM),
			calcium = new GTItemElement(GTItemElementTypes.CALCIUM),
			sodium = new GTItemElement(GTItemElementTypes.SODIUM),
			chlorine = new GTItemElement(GTItemElementTypes.CHLORINE),
			potassium = new GTItemElement(GTItemElementTypes.POTASSIUM),
			nitrogen = new GTItemElement(GTItemElementTypes.NITROGEN),
			oxygen = new GTItemElement(GTItemElementTypes.OXYGEN);

	public static final GTItemPlasma plasmaHydrogen = new GTItemPlasma(GTItemPlasmaTypes.HYDROGEN),
			plasmaNitrogen = new GTItemPlasma(GTItemPlasmaTypes.NITROGEN),
			plasmaOxygen = new GTItemPlasma(GTItemPlasmaTypes.OXYGEN),
			plasmaHelium = new GTItemPlasma(GTItemPlasmaTypes.HELIUM),
			plasmaIron = new GTItemPlasma(GTItemPlasmaTypes.IRON);

	public static final GTItemDust dustEnderpearl = new GTItemDust(GTItemDustTypes.ENDERPEARL),
			dustEnderEye = new GTItemDust(GTItemDustTypes.ENDER_EYE),
			dustLazurite = new GTItemDust(GTItemDustTypes.LAZURITE),
			dustPyrite = new GTItemDust(GTItemDustTypes.PYRITE), dustCalcite = new GTItemDust(GTItemDustTypes.CALCITE),
			dustFlint = new GTItemDust(GTItemDustTypes.FLINT), dustUranium = new GTItemDust(GTItemDustTypes.URANIUM),
			dustBauxite = new GTItemDust(GTItemDustTypes.BAUXITE),
			dustAluminium = new GTItemDust(GTItemDustTypes.ALUMINIUM),
			dustTitanium = new GTItemDust(GTItemDustTypes.TITANIUM),
			dustChrome = new GTItemDust(GTItemDustTypes.CHROME), dustRuby = new GTItemDust(GTItemDustTypes.RUBY),
			dustSapphire = new GTItemDust(GTItemDustTypes.SAPPHIRE),
			dustGreenSapphire = new GTItemDust(GTItemDustTypes.GREEN_SAPPHIRE),
			dustEmerald = new GTItemDust(GTItemDustTypes.EMERALD),
			dustSodalite = new GTItemDust(GTItemDustTypes.SODALITE);

	public static final GTItemGem ruby = new GTItemGem(GTItemGemTypes.RUBY),
			sapphire = new GTItemGem(GTItemGemTypes.SAPPHIRE);

	public static final GTItemIngot ingotAluminium = new GTItemIngot(GTItemIngotTypes.ALUMINIUM),
			ingotTitanium = new GTItemIngot(GTItemIngotTypes.TITANIUM),
			ingotChrome = new GTItemIngot(GTItemIngotTypes.CHROME),
			ingotIridium = new GTItemIngot(GTItemIngotTypes.IRIDIUM);

	public static final GTItemReactorHeat heatStorageSingle = new GTItemReactorHeat(GTItemHeatStorageTypes.SINGLE),
			heatStorageTriple = new GTItemReactorHeat(GTItemHeatStorageTypes.TRIPLE),
			heatStorageSix = new GTItemReactorHeat(GTItemHeatStorageTypes.SIX);

	public static final GTItemReactorRod rodThoriumSingle = new GTItemReactorRod(GTItemRodTypes.SINGLETHORIUM),
			rodThoriumDouble = new GTItemReactorRod(GTItemRodTypes.DOUBLETHORIUM),
			rodThoriumQuad = new GTItemReactorRod(GTItemRodTypes.QUADTHORIUM),
			rodPlutoniumSingle = new GTItemReactorRod(GTItemRodTypes.SINGLEPLUTONIUM),
			rodPlutoniumDouble = new GTItemReactorRod(GTItemRodTypes.DOUBLEPLUTONIUM),
			rodPlutoniumQuad = new GTItemReactorRod(GTItemRodTypes.QUADPLUTONIUM);

	public static final GTItemComponents glassTube = new GTItemComponents(GTItemComponentTypes.GLASS_TUBE),
			energyFlowCircuit = new GTItemComponents(GTItemComponentTypes.ENERGY_FLOW_CIRCUIT),
			dataControlCircuit = new GTItemComponents(GTItemComponentTypes.DATA_CONTROL_CIRCUIT),
			dataStorageCircuit = new GTItemComponents(GTItemComponentTypes.DATA_STORAGE_CIRCUIT),
			dataOrb = new GTItemComponents(GTItemComponentTypes.DATA_ORB),
			plateSilicon = new GTItemComponents(GTItemComponentTypes.SILICON_PLATE);

	public static final GTItemDuctTape braintechAerospaceARDT = new GTItemDuctTape();

	public static final GTItemSonictron sonictronItem = new GTItemSonictron();
	public static final GTItemDestructoPack destructoPack = new GTItemDestructoPack();
	public static final GTItemCraftingTablet craftingTablet = new GTItemCraftingTablet();
	public static final GTItemHammerIron hammerIron = new GTItemHammerIron();
	public static final GTItemElectromagnet electroMagnet = new GTItemElectromagnet();
	public static final GTItemRockCutter rockCutter = new GTItemRockCutter();
	public static final GTItemDrill advancedDrill = new GTItemDrill();
	public static final GTItemChainsaw advancedChainsaw = new GTItemChainsaw();
	public static final GTItemTeslaStaff teslaStaff = new GTItemTeslaStaff();

	public static final GTItemEnergyPack lithiumBatpack = new GTItemEnergyPack(58,
			"gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 128);
	public static final GTItemEnergyPack lapotronPack = new GTItemEnergyPack(45,
			"gtclassic:textures/models/armor/lapotronpack", 10000000, "lapotron_pack", ".lapotronPack", 4, 8192);

	public static final GTItemCreativeScanner debugScanner = new GTItemCreativeScanner();
	public static final GTItemSurvivalScanner portableScanner = new GTItemSurvivalScanner();

	public static final GTBatteryBuilder smallLithium = new GTBatteryBuilder(GTBlocks.smallLithium, 100000, 128, 1),
			medLithium = new GTBatteryBuilder(GTBlocks.medLithium, 200000, 256, 2),
			largeLithium = new GTBatteryBuilder(GTBlocks.largeLithium, 400000, 512, 3),

			smallEnergium = new GTBatteryBuilder(GTBlocks.smallEnergium, 100000, 256, 2),
			medEnergium = new GTBatteryBuilder(GTBlocks.medEnergium, 1000000, 512, 3),
			largeEnergium = new GTBatteryBuilder(GTBlocks.largeEnergium, 10000000, 1024, 4),

			smallLapotron = new GTBatteryBuilder(GTBlocks.smallLapotron, 1000000, 1024, 3),
			medLapotron = new GTBatteryBuilder(GTBlocks.medLapotron, 10000000, 4096, 4),
			largeLapotron = new GTBatteryBuilder(GTBlocks.largeLapotron, 100000000, 8192, 5);

	public static final Item[] items = {

			hydrogen, dueterium, tritium, helium, tungsten, lithium, helium3, silicon, carbon, methane, berilium,
			calcium, sodium, chlorine, potassium, nitrogen, oxygen,

			plasmaHydrogen, plasmaNitrogen, plasmaOxygen, plasmaHelium, plasmaIron, glassTube,

			dustEnderpearl, dustEnderEye, dustLazurite, dustPyrite, dustCalcite, dustFlint, dustUranium, dustBauxite,
			dustAluminium, dustTitanium, dustChrome, dustRuby, dustSapphire, dustGreenSapphire, dustEmerald,
			dustSodalite,

			ruby, sapphire, ingotAluminium, ingotTitanium, ingotChrome, ingotIridium, plateSilicon,

			heatStorageSingle, heatStorageTriple, heatStorageSix,

			rodThoriumSingle, rodThoriumDouble, rodThoriumQuad, rodPlutoniumSingle, rodPlutoniumDouble,
			rodPlutoniumQuad,

			energyFlowCircuit, dataControlCircuit, dataStorageCircuit, dataOrb, braintechAerospaceARDT,

			sonictronItem, destructoPack, craftingTablet, hammerIron, electroMagnet, rockCutter, advancedDrill,
			advancedChainsaw, teslaStaff, lithiumBatpack, lapotronPack, portableScanner, debugScanner,

			smallLithium, medLithium, largeLithium, smallLapotron, medLapotron, largeLapotron, smallEnergium,
			medEnergium, largeEnergium,

	};

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		GTClassic.logger.info("Registering Items");
		for (Item item : items) {
			registry.register(item);
		}
	}
}
