package gtclassic;

import gtclassic.item.GTItemComponents;
import gtclassic.item.GTItemComponents.GTItemComponentTypes;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.armor.GTItemEnergyPack;
import gtclassic.item.energy.GTItemLapotronicEnergyOrb;
import gtclassic.item.energy.GTItemLithiumBattery;
import gtclassic.item.energy.GTItemZeroPointModule;
import gtclassic.item.material.GTItemDust;
import gtclassic.item.material.GTItemDust.GTItemDustTypes;
import gtclassic.item.material.GTItemElement;
import gtclassic.item.material.GTItemElement.GTItemElementTypes;
import gtclassic.item.material.GTItemGem;
import gtclassic.item.material.GTItemGem.GTItemGemTypes;
import gtclassic.item.material.GTItemIngot;
import gtclassic.item.material.GTItemIngot.GTItemIngotTypes;
import gtclassic.item.material.GTItemPlasma;
import gtclassic.item.material.GTItemPlasma.GTItemPlasmaTypes;
import gtclassic.item.reactor.GTItemHeatStorage;
import gtclassic.item.reactor.GTItemHeatStorage.GTItemHeatStorageTypes;
import gtclassic.item.reactor.GTItemRod;
import gtclassic.item.reactor.GTItemRod.GTItemRodTypes;
import gtclassic.item.tool.GTItemAdvancedChainsaw;
import gtclassic.item.tool.GTItemAdvancedDrill;
import gtclassic.item.tool.GTItemCraftingTablet;
import gtclassic.item.tool.GTItemDebugScanner;
import gtclassic.item.tool.GTItemDestructoPack;
import gtclassic.item.tool.GTItemElectromagnet;
import gtclassic.item.tool.GTItemHammerIron;
import gtclassic.item.tool.GTItemRockCutter;
import gtclassic.item.tool.GTItemSonictron;
import gtclassic.item.tool.GTItemTeslaStaff;
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
			oxygen = new GTItemElement(GTItemElementTypes.OXYGEN),

			water = new GTItemElement(GTItemElementTypes.WATER), lava = new GTItemElement(GTItemElementTypes.LAVA);

	public static final GTItemPlasma plasmaHydrogen = new GTItemPlasma(GTItemPlasmaTypes.HYDROGEN),
			plasmaNitrogen = new GTItemPlasma(GTItemPlasmaTypes.NITROGEN),
			plasmaOxygen = new GTItemPlasma(GTItemPlasmaTypes.OXYGEN),
			plasmaHelium = new GTItemPlasma(GTItemPlasmaTypes.HELIUM),
			plasmaIron = new GTItemPlasma(GTItemPlasmaTypes.IRON), plasmaUU = new GTItemPlasma(GTItemPlasmaTypes.UU);

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

	public static final GTItemIngot ingotIridium = new GTItemIngot(GTItemIngotTypes.IRIDIUM),
			ingotAluminium = new GTItemIngot(GTItemIngotTypes.ALUMINIUM),
			ingotTitanium = new GTItemIngot(GTItemIngotTypes.TITANIUM),
			ingotChrome = new GTItemIngot(GTItemIngotTypes.CHROME);

	public static final GTItemHeatStorage heatStorageSingle = new GTItemHeatStorage(GTItemHeatStorageTypes.SINGLE),
			heatStorageTriple = new GTItemHeatStorage(GTItemHeatStorageTypes.TRIPLE),
			heatStorageSix = new GTItemHeatStorage(GTItemHeatStorageTypes.SIX);

	public static final GTItemRod rodThoriumSingle = new GTItemRod(GTItemRodTypes.SINGLETHORIUM),
			rodThoriumDouble = new GTItemRod(GTItemRodTypes.DOUBLETHORIUM),
			rodThoriumQuad = new GTItemRod(GTItemRodTypes.QUADTHORIUM),
			rodPlutoniumSingle = new GTItemRod(GTItemRodTypes.SINGLEPLUTONIUM),
			rodPlutoniumDouble = new GTItemRod(GTItemRodTypes.DOUBLEPLUTONIUM),
			rodPlutoniumQuad = new GTItemRod(GTItemRodTypes.QUADPLUTONIUM);

	public static final GTItemComponents glassTube = new GTItemComponents(GTItemComponentTypes.GLASS_TUBE),
			energyFlowCircuit = new GTItemComponents(GTItemComponentTypes.ENERGY_FLOW_CIRCUIT),
			dataControlCircuit = new GTItemComponents(GTItemComponentTypes.DATA_CONTROL_CIRCUIT),
			superConductor = new GTItemComponents(GTItemComponentTypes.SUPERCONDUCTOR),
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
	public static final GTItemAdvancedDrill advancedDrill = new GTItemAdvancedDrill();
	public static final GTItemAdvancedChainsaw advancedChainsaw = new GTItemAdvancedChainsaw();
	public static final GTItemTeslaStaff teslaStaff = new GTItemTeslaStaff();
	public static final GTItemLithiumBattery lithiumBattery = new GTItemLithiumBattery();
	public static final GTItemLapotronicEnergyOrb lapotronicEnergyOrb = new GTItemLapotronicEnergyOrb();
	public static final GTItemEnergyPack lithiumBatpack = new GTItemEnergyPack(58,
			"gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 128);
	public static final GTItemEnergyPack lapotronPack = new GTItemEnergyPack(45,
			"gtclassic:textures/models/armor/lapotronpack", 10000000, "lapotron_pack", ".lapotronPack", 4, 8192);
	public static final GTItemZeroPointModule zeroPointModule = new GTItemZeroPointModule();
	public static final GTItemDebugScanner debugScanner = new GTItemDebugScanner();

	public static final Item[] items = {

			hydrogen, dueterium, tritium, helium, tungsten, lithium, helium3, silicon, carbon, methane, berilium,
			calcium, sodium, chlorine, potassium, nitrogen, oxygen,

			water, lava,

			plasmaHydrogen, plasmaNitrogen, plasmaOxygen, plasmaHelium, plasmaIron, plasmaUU, glassTube,

			dustEnderpearl, dustEnderEye, dustLazurite, dustPyrite, dustCalcite, dustFlint, dustUranium, dustBauxite,
			dustAluminium, dustTitanium, dustChrome, dustRuby, dustSapphire, dustGreenSapphire, dustEmerald,
			dustSodalite,

			ruby, sapphire, ingotIridium, ingotAluminium, ingotTitanium, ingotChrome, plateSilicon,

			heatStorageSingle, heatStorageTriple, heatStorageSix,

			rodThoriumSingle, rodThoriumDouble, rodThoriumQuad, rodPlutoniumSingle, rodPlutoniumDouble,
			rodPlutoniumQuad,

			energyFlowCircuit, dataControlCircuit, superConductor, dataStorageCircuit, braintechAerospaceARDT, dataOrb,

			sonictronItem, destructoPack, craftingTablet, hammerIron, electroMagnet, rockCutter, advancedDrill,
			advancedChainsaw, teslaStaff, lithiumBattery, lapotronicEnergyOrb, lithiumBatpack, lapotronPack,
			debugScanner, zeroPointModule

	};

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry registry = event.getRegistry();
		// items
		GTClassic.logger.info("Registering Items");
		GTClassic.logger
				.info("WARNING [The ZPM is still in debug mode if you are reading this e99999 did not do his job]");
		for (Item item : items) {
			registry.register(item);
		}
	}
}
