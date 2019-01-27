package gtclassic;

import gtclassic.item.GTItemChainsaw;
import gtclassic.item.GTItemComponents;
import gtclassic.item.GTItemComponents.GTItemComponentTypes;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDrill;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemDust;
import gtclassic.item.GTItemDust.GTItemDustTypes;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemElement;
import gtclassic.item.GTItemElement.GTItemElementTypes;
import gtclassic.item.GTItemFile;
import gtclassic.item.GTItemGem;
import gtclassic.item.GTItemGem.GTItemGemTypes;
import gtclassic.item.GTItemHammer;
import gtclassic.item.GTItemIngot;
import gtclassic.item.GTItemIngot.GTItemIngotTypes;
import gtclassic.item.GTItemPlasma;
import gtclassic.item.GTItemPlasma.GTItemPlasmaTypes;
import gtclassic.item.GTItemReactorRod.GTItemRodTypes;
import gtclassic.item.GTItemStick.GTItemStickTypes;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemStick;
import gtclassic.item.GTItemEchophone;
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
			glassTube = new GTItemElement(GTItemElementTypes.EMPTY);

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
			dustSodalite = new GTItemDust(GTItemDustTypes.SODALITE),
			dustPlatinum = new GTItemDust(GTItemDustTypes.PLATINUM),
			dustTungsten = new GTItemDust(GTItemDustTypes.TUNGSTEN),
			dustPlastic = new GTItemDust(GTItemDustTypes.PLASTIC);

	public static final GTItemGem ruby = new GTItemGem(GTItemGemTypes.RUBY),
			sapphire = new GTItemGem(GTItemGemTypes.SAPPHIRE);

	public static final GTItemIngot ingotAluminium = new GTItemIngot(GTItemIngotTypes.ALUMINIUM),
			ingotTitanium = new GTItemIngot(GTItemIngotTypes.TITANIUM),
			ingotChrome = new GTItemIngot(GTItemIngotTypes.CHROME),
			ingotIridium = new GTItemIngot(GTItemIngotTypes.IRIDIUM),
			ingotTungsten = new GTItemIngot(GTItemIngotTypes.TUNGSTEN),
			ingotPlatinum = new GTItemIngot(GTItemIngotTypes.PLATINUM);
	
	public static final GTItemStick stickAluminium = new GTItemStick(GTItemStickTypes.ALUMINIUM),
			stickTitanium = new GTItemStick(GTItemStickTypes.TITANIUM),
			stickChrome = new GTItemStick(GTItemStickTypes.CHROME),
			stickIron = new GTItemStick(GTItemStickTypes.IRON),
			stickTungsten = new GTItemStick(GTItemStickTypes.TUNGSTEN),
			stickPlatinum = new GTItemStick(GTItemStickTypes.PLATINUM);
			

	public static final GTItemComponents bouleSilicon = new GTItemComponents(GTItemComponentTypes.BOULE_SILICON),
			plateSilicon = new GTItemComponents(GTItemComponentTypes.PLATE_SILICON),
			platePlastic = new GTItemComponents(GTItemComponentTypes.PLATE_PLASTIC),
			lensDiamond = new GTItemComponents(GTItemComponentTypes.LENS_DIAMOND),
			lensRuby = new GTItemComponents(GTItemComponentTypes.LENS_RUBY),
			lensEmerald = new GTItemComponents(GTItemComponentTypes.LENS_EMERALD),
			lensSapphire = new GTItemComponents(GTItemComponentTypes.LENS_SAPPHIRE),
			chipDiamond = new GTItemComponents(GTItemComponentTypes.CHIP_DIAMOND),
			chipRuby = new GTItemComponents(GTItemComponentTypes.CHIP_RUBY),
			chipEmerald = new GTItemComponents(GTItemComponentTypes.CHIP_EMERALD),
			chipSapphire = new GTItemComponents(GTItemComponentTypes.CHIP_SAPPHIRE),
			circuitEmpty = new GTItemComponents(GTItemComponentTypes.CIRCUIT_EMPTY),
			circuitDiamond = new GTItemComponents(GTItemComponentTypes.CIRCUIT_DIAMOND),
			circuitRuby = new GTItemComponents(GTItemComponentTypes.CIRCUIT_RUBY),
			circuitEmerald = new GTItemComponents(GTItemComponentTypes.CIRCUIT_EMERALD),
			circuitSapphire = new GTItemComponents(GTItemComponentTypes.CIRCUIT_SAPPHIRE);

	public static final GTItemDuctTape braintechAerospaceARDT = new GTItemDuctTape();

	public static final GTItemEchophone sonictronItem = new GTItemEchophone();
	public static final GTItemDestructoPack destructoPack = new GTItemDestructoPack();
	public static final GTItemCraftingTablet craftingTablet = new GTItemCraftingTablet();
	public static final GTItemFile fileIron = new GTItemFile();
	public static final GTItemHammer hammerIron = new GTItemHammer();
	public static final GTItemElectromagnet electroMagnet = new GTItemElectromagnet();
	public static final GTItemRockCutter rockCutter = new GTItemRockCutter();
	public static final GTItemDrill advancedDrill = new GTItemDrill();
	public static final GTItemChainsaw advancedChainsaw = new GTItemChainsaw();
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

			hydrogen, dueterium, tritium, helium, lithium, helium3, silicon, carbon, methane, berilium,
			calcium, sodium, chlorine, potassium, nitrogen, oxygen,

			plasmaHydrogen, plasmaNitrogen, plasmaOxygen, plasmaHelium, plasmaIron, glassTube,

			dustEnderpearl, dustEnderEye, dustLazurite, dustPyrite, dustCalcite, dustFlint, dustUranium, dustBauxite,
			dustAluminium, dustTitanium, dustChrome, dustTungsten, dustPlatinum, dustRuby, dustSapphire, dustGreenSapphire, dustEmerald,
			dustSodalite, dustPlastic,

			ruby, sapphire, ingotAluminium, ingotTitanium, ingotChrome,ingotTungsten, ingotPlatinum, ingotIridium,
			stickIron, stickAluminium, stickTitanium, stickChrome, stickTungsten, stickPlatinum,

			bouleSilicon, plateSilicon, platePlastic, lensDiamond, lensRuby, lensEmerald, lensSapphire, chipDiamond,
			chipRuby, chipEmerald, chipSapphire, circuitEmpty, circuitDiamond, circuitRuby, circuitEmerald,
			circuitSapphire,

			fileIron, hammerIron, braintechAerospaceARDT, sonictronItem, destructoPack, craftingTablet, electroMagnet,
			rockCutter, advancedDrill, advancedChainsaw, teslaStaff, portableScanner, debugScanner,

			smallLithium, medLithium, largeLithium,

			tinyLapotron, smallLapotron, medLapotron, largeLapotron, hugeLapotron,

			tinyEnergium, smallEnergium, medEnergium, largeEnergium, hugeEnergium,

	};

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		GTMod.logger.info("Registering Items");
		for (Item item : items) {
			registry.register(item);
		}
	}

}
