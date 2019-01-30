package gtclassic;

import gtclassic.item.GTItemChainsaw;
import gtclassic.item.GTItemComponents;
import gtclassic.item.GTItemComponents.GTItemComponentTypes;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDrill;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemEchophone;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemElement;
import gtclassic.item.GTItemElement.GTItemElementTypes;
import gtclassic.item.GTItemFile;
import gtclassic.item.GTItemHammer;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.item.materials.GTItemDust;
import gtclassic.item.materials.GTItemDust.GTItemDustTypes;
import gtclassic.item.materials.GTItemGem;
import gtclassic.item.materials.GTItemIngot;
import gtclassic.item.materials.GTItemNugget;
import gtclassic.item.materials.GTItemPlasma;
import gtclassic.item.materials.GTItemPlasma.GTItemPlasmaTypes;
import gtclassic.item.materials.GTItemPlate;
import gtclassic.item.materials.GTItemStick;
import gtclassic.item.materials.GTItemTinyDust;
import gtclassic.item.materials.GTItemTinyDust.GTItemTinyDustTypes;
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

	public static final GTItemDust
	dustEnderpearl = new GTItemDust(GTItemDustTypes.ENDERPEARL),
    dustEnderEye = new GTItemDust(GTItemDustTypes.ENDEREYE),
	dustLazurite = new GTItemDust(GTItemDustTypes.LAZURITE),
	dustPyrite = new GTItemDust(GTItemDustTypes.PYRITE),
	dustCalcite = new GTItemDust(GTItemDustTypes.CALCITE),
	dustSodalite = new GTItemDust(GTItemDustTypes.SODALITE),
	dustNetherrack = new GTItemDust(GTItemDustTypes.NETHERRACK),
	dustFlint = new GTItemDust(GTItemDustTypes.FLINT),
	dustSulfur = new GTItemDust(GTItemDustTypes.SULFUR),
	dustSaltpeter = new GTItemDust(GTItemDustTypes.SALTPETER),
	dustEndstone = new GTItemDust(GTItemDustTypes.ENDSTONE),
	dustCinnabar = new GTItemDust(GTItemDustTypes.CINNABAR),
	dustMaganese = new GTItemDust(GTItemDustTypes.MAGANESE), //TODO MAGANESE!?
	dustMagnesium = new GTItemDust(GTItemDustTypes.MAGNESIUM),
	dustSphalerite = new GTItemDust(GTItemDustTypes.SPHALERITE),
	dustWoodPulp = new GTItemDust(GTItemDustTypes.WOODPULP),
	dustUranium = new GTItemDust(GTItemDustTypes.URANIUM),
	dustBauxite = new GTItemDust(GTItemDustTypes.BAUXITE),
	dustAluminium = new GTItemDust(GTItemDustTypes.ALUMINIUM),
	dustTitanium = new GTItemDust(GTItemDustTypes.TITANIUM),
	dustChrome = new GTItemDust(GTItemDustTypes.CHROME),
	dustElectrum = new GTItemDust(GTItemDustTypes.ELECTRUM),
	dustTungsten = new GTItemDust(GTItemDustTypes.TUNGSTEN),
	dustLead = new GTItemDust(GTItemDustTypes.LEAD),
	dustZinc = new GTItemDust(GTItemDustTypes.ZINC),
	dustBrass = new GTItemDust(GTItemDustTypes.BRASS),
	dustSteel = new GTItemDust(GTItemDustTypes.STEEL),
	dustPlatinum = new GTItemDust(GTItemDustTypes.PLATINUM),
	dustNickel = new GTItemDust(GTItemDustTypes.NICKEL),
	dustInvar = new GTItemDust(GTItemDustTypes.INVAR),
	dustOsmium = new GTItemDust(GTItemDustTypes.OSMIUM),
	dustRuby = new GTItemDust(GTItemDustTypes.RUBY),
	dustSapphire = new GTItemDust(GTItemDustTypes.SAPPHIRE),
	dustGreenSapphire = new GTItemDust(GTItemDustTypes.GREENSAPPHIRE),
	dustEmerald = new GTItemDust(GTItemDustTypes.EMERALD),
	dustDiamond = new GTItemDust(GTItemDustTypes.DIAMOND),
	dustOlivine = new GTItemDust(GTItemDustTypes.OLIVINE),
	dustGalena = new GTItemDust(GTItemDustTypes.GALENA),
	dustPhosphor = new GTItemDust(GTItemDustTypes.PHOSPHOR),
	dustRedGarnet = new GTItemDust(GTItemDustTypes.REDGARNET),
	dustYellowGarnet = new GTItemDust(GTItemDustTypes.YELLOWGARNET),
	dustPyrope = new GTItemDust(GTItemDustTypes.PYROPE),
	dustAlmandine = new GTItemDust(GTItemDustTypes.ALMANDINE),
	dustSpessartine = new GTItemDust(GTItemDustTypes.SPESSARTINE),
	dustAndradite = new GTItemDust(GTItemDustTypes.ANDRADITE),
	dustGrossular = new GTItemDust(GTItemDustTypes.GROSSULAR),
	dustUvarovite = new GTItemDust(GTItemDustTypes.UVAROVITE),
	dustAshes = new GTItemDust(GTItemDustTypes.ASHES),
	dustDarkAshes = new GTItemDust(GTItemDustTypes.DARKASHES),
	dustRedRock = new GTItemDust(GTItemDustTypes.REDROCK),
	dustMarble = new GTItemDust(GTItemDustTypes.MARBLE),
	dustBasalt = new GTItemDust(GTItemDustTypes.BASALT),
	dustThorium = new GTItemDust(GTItemDustTypes.THORIUM),
	dustPlutonium = new GTItemDust(GTItemDustTypes.PLUTONIUM),
	dustPlastic = new GTItemDust(GTItemDustTypes.PLASTIC);
	
	public static final GTItemTinyDust
	tinyDustEnderpearl = new GTItemTinyDust(GTItemTinyDustTypes.ENDERPEARL),
    tinyDustEnderEye = new GTItemTinyDust(GTItemTinyDustTypes.ENDEREYE),
	tinyDustLazurite = new GTItemTinyDust(GTItemTinyDustTypes.LAZURITE),
	tinyDustPyrite = new GTItemTinyDust(GTItemTinyDustTypes.PYRITE),
	tinyDustCalcite = new GTItemTinyDust(GTItemTinyDustTypes.CALCITE),
	tinyDustSodalite = new GTItemTinyDust(GTItemTinyDustTypes.SODALITE),
	tinyDustNetherrack = new GTItemTinyDust(GTItemTinyDustTypes.NETHERRACK),
	tinyDustFlint = new GTItemTinyDust(GTItemTinyDustTypes.FLINT),
	tinyDustSulfur = new GTItemTinyDust(GTItemTinyDustTypes.SULFUR),
	tinyDustSaltpeter = new GTItemTinyDust(GTItemTinyDustTypes.SALTPETER),
	tinyDustEndstone = new GTItemTinyDust(GTItemTinyDustTypes.ENDSTONE),
	tinyDustCinnabar = new GTItemTinyDust(GTItemTinyDustTypes.CINNABAR),
	tinyDustMaganese = new GTItemTinyDust(GTItemTinyDustTypes.MAGANESE),
	tinyDustMagnesium = new GTItemTinyDust(GTItemTinyDustTypes.MAGNESIUM),
	tinyDustSphalerite = new GTItemTinyDust(GTItemTinyDustTypes.SPHALERITE),
	tinyDustWoodPulp = new GTItemTinyDust(GTItemTinyDustTypes.WOOD_PULP),
	tinyDustUranium = new GTItemTinyDust(GTItemTinyDustTypes.URANIUM),
	tinyDustBauxite = new GTItemTinyDust(GTItemTinyDustTypes.BAUXITE),
	tinyDustAluminum = new GTItemTinyDust(GTItemTinyDustTypes.ALUMINUM),
	tinyDustTitanium = new GTItemTinyDust(GTItemTinyDustTypes.TITANIUM),
	tinyDustChrome = new GTItemTinyDust(GTItemTinyDustTypes.CHROME),
	tinyDustElectrum = new GTItemTinyDust(GTItemTinyDustTypes.ELECTRUM),
	tinyDustTungsten = new GTItemTinyDust(GTItemTinyDustTypes.TUNGSTEN),
	tinyDustLead = new GTItemTinyDust(GTItemTinyDustTypes.LEAD),
	tinyDustZinc = new GTItemTinyDust(GTItemTinyDustTypes.ZINC),
	tinyDustBrass = new GTItemTinyDust(GTItemTinyDustTypes.BRASS),
	tinyDustSteel = new GTItemTinyDust(GTItemTinyDustTypes.STEEL),
	tinyDustPlatinum = new GTItemTinyDust(GTItemTinyDustTypes.PLATINUM),
	tinyDustNickel = new GTItemTinyDust(GTItemTinyDustTypes.NICKEL),
	tinyDustInvar = new GTItemTinyDust(GTItemTinyDustTypes.INVAR),
	tinyDustOsmium = new GTItemTinyDust(GTItemTinyDustTypes.OSMIUM),
	tinyDustRuby = new GTItemTinyDust(GTItemTinyDustTypes.RUBY),
	tinyDustSapphire = new GTItemTinyDust(GTItemTinyDustTypes.SAPPHIRE),
	tinyDustGreenSapphire = new GTItemTinyDust(GTItemTinyDustTypes.GREENSAPPHIRE),
	tinyDustEmerald = new GTItemTinyDust(GTItemTinyDustTypes.EMERALD),
	tinyDustDiamond = new GTItemTinyDust(GTItemTinyDustTypes.DIAMOND),
	tinyDustOlivine = new GTItemTinyDust(GTItemTinyDustTypes.OLIVINE),
	tinyDustGalena = new GTItemTinyDust(GTItemTinyDustTypes.GALENA),
	tinyDustPhosphor = new GTItemTinyDust(GTItemTinyDustTypes.PHOSPHOR),
	tinyDustObsidian = new GTItemTinyDust(GTItemTinyDustTypes.OBSIDIAN),
	tinyDustCharcoal = new GTItemTinyDust(GTItemTinyDustTypes.CHARCOAL),
	tinyDustRedGarnet = new GTItemTinyDust(GTItemTinyDustTypes.REDGARNET),
	tinyDustYellowGarnet = new GTItemTinyDust(GTItemTinyDustTypes.YELLOWGARNET),
	tinyDustPyrope = new GTItemTinyDust(GTItemTinyDustTypes.PYROPE),
	tinyDustAlmandine = new GTItemTinyDust(GTItemTinyDustTypes.ALMANDINE),
	tinyDustSpessartine = new GTItemTinyDust(GTItemTinyDustTypes.SPESSARTINE),
	tinyDustAndradite = new GTItemTinyDust(GTItemTinyDustTypes.ANDRADITE),
	tinyDustGrossular = new GTItemTinyDust(GTItemTinyDustTypes.GROSSULAR),
	tinyDustUvarovite = new GTItemTinyDust(GTItemTinyDustTypes.UVAROVITE),
	tinyDustAshes = new GTItemTinyDust(GTItemTinyDustTypes.ASHES),
	tinyDustDarkAshes = new GTItemTinyDust(GTItemTinyDustTypes.DARKASHES),
	tinyDustRedRock = new GTItemTinyDust(GTItemTinyDustTypes.REDROCK),
	tinyDustMarble = new GTItemTinyDust(GTItemTinyDustTypes.MARBLE),
	tinyDustBasalt = new GTItemTinyDust(GTItemTinyDustTypes.BASALT),
	tinyDustThorium = new GTItemTinyDust(GTItemTinyDustTypes.THORIUM),
	tinyDustPlutonium = new GTItemTinyDust(GTItemTinyDustTypes.PLUTONIUM),
	tinyDustCoal = new GTItemTinyDust(GTItemTinyDustTypes.COAL),
	tinyDustIron = new GTItemTinyDust(GTItemTinyDustTypes.IRON),
	tinyDustGold = new GTItemTinyDust(GTItemTinyDustTypes.GOLD),
	tinyDustCopper = new GTItemTinyDust(GTItemTinyDustTypes.COPPER),
	tinyDustTin = new GTItemTinyDust(GTItemTinyDustTypes.TIN),
	tinyDustBronze = new GTItemTinyDust(GTItemTinyDustTypes.BRONZE),
	tinyDustSilver = new GTItemTinyDust(GTItemTinyDustTypes.SILVER),
	tinyDustClay = new GTItemTinyDust(GTItemTinyDustTypes.CLAY),
	tinyDustGunpowder = new GTItemTinyDust(GTItemTinyDustTypes.GUNPOWDER),
	tinyDustRedstone = new GTItemTinyDust(GTItemTinyDustTypes.REDSTONE),
	tinyDustGlowstone = new GTItemTinyDust(GTItemTinyDustTypes.GLOWSTONE);

	public static final GTItemGem
	gemGarnetRed = new GTItemGem("GarnetRed"),
	gemGarnetYellow = new GTItemGem("GarnetYellow"),
	gemOlivine = new GTItemGem("Olivine"),
	gemRuby = new GTItemGem("Ruby"),
	gemSapphire = new GTItemGem("Sapphire"),
	gemSapphireGreen = new GTItemGem("SapphireGreen");

	public static final GTItemIngot
	ingotAluminium = new GTItemIngot("Aluminium"),
	ingotBrass = new GTItemIngot("Brass"),
	ingotChrome = new GTItemIngot("Chrome"),
	ingotElectrum = new GTItemIngot("Electrum"),
	ingotInvar = new GTItemIngot("Invar"),
	ingotIridium = new GTItemIngot("Iridium"),
	ingotLead = new GTItemIngot("Lead"),
	ingotNickel = new GTItemIngot("Nickel"),
	ingotOsmium = new GTItemIngot("Osmium"),
	ingotPlatinum = new GTItemIngot("Platinum"),
	ingotPlutonium = new GTItemIngot("Plutonium"),
	ingotSteel = new GTItemIngot("Steel"),
	ingotThorium = new GTItemIngot("Thorium"),
	ingotTitanium = new GTItemIngot("Titanium"),
	ingotTungsten = new GTItemIngot("Tungsten"),
	ingotTungstensteel = new GTItemIngot("TungstenSteel"),
	ingotZinc = new GTItemIngot("Zinc");
	
	public static final GTItemPlate
	plateAluminum = new GTItemPlate("Aluminium"),
	plateBrass = new GTItemPlate("Brass"),
	plateBronze = new GTItemPlate("Bronze"),
	plateChrome = new GTItemPlate("Chrome"),
	plateCopper = new GTItemPlate("Copper"),
	plateElectrum = new GTItemPlate("Electrum"),
	plateGold = new GTItemPlate("Gold"),
	plateInvar = new GTItemPlate("Invar"),
	plateIron = new GTItemPlate("Iron"),
	plateLead = new GTItemPlate("Lead"),
	plateMagnalium = new GTItemPlate("Magnalium"),
	plateNickel = new GTItemPlate("Nickel"),
	plateOsmium = new GTItemPlate("Osmium"),
	platePlatinum = new GTItemPlate("Platinum"),
	plateRefinedIron = new GTItemPlate("RefinedIron"),
	plateSilicon = new GTItemPlate("Silicon"),
	plateSilver = new GTItemPlate("Silver"),
	plateSteel = new GTItemPlate("Steel"),
	plateTin = new GTItemPlate("Tin"),
	plateTitanium = new GTItemPlate("Titanium"),
	plateTungsten = new GTItemPlate("Tungsten"),
	plateTungstensteel = new GTItemPlate("TungstenSteel"),
	plateZinc = new GTItemPlate("Zinc");
	
	public static final GTItemStick 
	stickAluminium = new GTItemStick("Aluminium"),
	stickBrass = new GTItemStick("Brass"),
	stickBronze =  new GTItemStick("Bronze"),
	stickChrome = new GTItemStick("Chrome"),
	stickCopper = new GTItemStick("Copper"),
	stickElectrum = new GTItemStick("Electrum"),
	stickInvar = new GTItemStick("Invar"),
	stickIridium = new GTItemStick("Iridium"),
	stickIron = new GTItemStick("Iron"),
	stickLead = new GTItemStick("Lead"),
	stickNickel = new GTItemStick("Nickel"),
	stickOsmium = new GTItemStick("Osmium"),
	stickPlatinum = new GTItemStick("Platinum"),
	stickSilver = new GTItemStick("Silver"),
	stickSteel = new GTItemStick("Steel"),
	stickTin = new GTItemStick("Tin"),
	stickTitanium = new GTItemStick("Titanium"),
	stickTungsten = new GTItemStick("Tungsten"),
	stickZinc = new GTItemStick("Zinc");
	
	public static final GTItemNugget
	nuggetAluminium = new GTItemNugget("Aluminium"),
	nuggetBrass = new GTItemNugget("Brass"),
	nuggetBronze = new GTItemNugget("Bronze"),
	nuggetChrome = new GTItemNugget("Chrome"),
	nuggetCopper = new GTItemNugget("Copper"),
	nuggetElectrum = new GTItemNugget("Electrum"),
	nuggetInvar = new GTItemNugget("Invar"),
	nuggetIridium = new GTItemNugget("Iridium"),
	nuggetLead = new GTItemNugget("Lead"),
	nuggetNickel = new GTItemNugget("Nickel"),
	nuggetOsmium = new GTItemNugget("Osmium"),
	nuggetPlatinum = new GTItemNugget("Platinum"),
	nuggetSilver = new GTItemNugget("Silver"),
	nuggetSteel = new GTItemNugget("Steel"),
	nuggetTin = new GTItemNugget("Tin"),
	nuggetTitanium = new GTItemNugget("Titanium"),
	nuggetTungsten = new GTItemNugget("Tungsten"),
	nuggetZinc = new GTItemNugget("Zinc");

	public static final GTItemComponents bouleSilicon = new GTItemComponents(GTItemComponentTypes.BOULE_SILICON),
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
			
			dustEnderpearl,
			dustEnderEye,
			dustLazurite,
			dustPyrite,
			dustCalcite,
			dustSodalite,
			dustNetherrack,
			dustFlint,
			dustSulfur,
			dustSaltpeter,
			dustEndstone,
			dustCinnabar,
			dustMaganese,
			dustMagnesium,
			dustSphalerite,
			dustWoodPulp,
			dustUranium,
			dustBauxite,
			dustAluminium,
			dustTitanium,
			dustChrome,
			dustElectrum,
			dustTungsten,
			dustLead,
			dustZinc,
			dustBrass,
			dustSteel,
			dustPlatinum,
			dustNickel,
			dustInvar,
			dustOsmium,
			dustRuby,
			dustSapphire,
			dustGreenSapphire,
			dustEmerald,
			dustDiamond,
			dustOlivine,
			dustGalena,
			dustPhosphor,
			dustRedGarnet,
			dustYellowGarnet,
			dustPyrope,
			dustAlmandine,
			dustSpessartine,
			dustAndradite,
			dustGrossular,
			dustUvarovite,
			dustAshes,
			dustDarkAshes,
			dustRedRock,
			dustMarble,
			dustBasalt,
			dustThorium,
			dustPlutonium,
			dustPlastic,
			
			tinyDustEnderpearl,
			tinyDustEnderEye,
			tinyDustLazurite,
			tinyDustPyrite,
			tinyDustCalcite,
			tinyDustSodalite,
			tinyDustNetherrack,
			tinyDustFlint,
			tinyDustSulfur,
			tinyDustSaltpeter,
			tinyDustEndstone,
			tinyDustCinnabar,
			tinyDustMaganese,
			tinyDustMagnesium,
			tinyDustSphalerite,
			tinyDustWoodPulp,
			tinyDustUranium,
			tinyDustBauxite,
			tinyDustAluminum,
			tinyDustTitanium,
			tinyDustChrome,
			tinyDustElectrum,
			tinyDustTungsten,
			tinyDustLead,
			tinyDustZinc,
			tinyDustBrass,
			tinyDustSteel,
			tinyDustPlatinum,
			tinyDustNickel,
			tinyDustInvar,
			tinyDustOsmium,
			tinyDustRuby,
			tinyDustSapphire,
			tinyDustGreenSapphire,
			tinyDustEmerald,
			tinyDustDiamond,
			tinyDustOlivine,
			tinyDustGalena,
			tinyDustPhosphor,
			tinyDustObsidian,
			tinyDustCharcoal,
			tinyDustRedGarnet,
			tinyDustYellowGarnet,
			tinyDustPyrope,
			tinyDustAlmandine,
			tinyDustSpessartine,
			tinyDustAndradite,
			tinyDustGrossular,
			tinyDustUvarovite,
			tinyDustAshes,
			tinyDustDarkAshes,
			tinyDustRedRock,
			tinyDustMarble,
			tinyDustBasalt,
			tinyDustThorium,
			tinyDustPlutonium,
			tinyDustCoal,
			tinyDustIron,
			tinyDustGold,
			tinyDustCopper,
			tinyDustTin,
			tinyDustBronze,
			tinyDustSilver,
			tinyDustClay,
			tinyDustGunpowder,
			tinyDustRedstone,
			tinyDustGlowstone,

			gemGarnetRed,
			gemGarnetYellow,
			gemOlivine,
			gemRuby,
			gemSapphire,
			gemSapphireGreen,
			
			ingotTungstensteel,
			ingotIridium,
			ingotAluminium,
			ingotTitanium,
			ingotChrome,
			ingotElectrum,
			ingotTungsten,
			ingotLead,
			ingotZinc,
			ingotBrass,
			ingotSteel,
			ingotPlatinum,
			ingotNickel,
			ingotInvar,
			ingotOsmium,
			ingotThorium,
			ingotPlutonium,
			
			plateSilicon,
			plateIron,
			plateGold,
			plateRefinedIron,
			plateTin,
			plateCopper,
			plateSilver,
			plateBronze,
			plateElectrum,
			plateNickel,
			plateInvar,
			plateLead,
			plateAluminum,
			plateChrome,
			plateTitanium,
			plateSteel,
			platePlatinum,
			plateTungsten,
			plateBrass,
			plateZinc,
			plateTungstensteel,
			plateOsmium,
			plateMagnalium,
			
			stickAluminium,
			stickBrass,
			stickBronze,
			stickChrome,
			stickCopper,
			stickElectrum,
			stickInvar,
			stickIridium,
			stickIron,
			stickLead,
			stickNickel,
			stickOsmium,
			stickPlatinum,
			stickSilver,
			stickSteel,
			stickTin ,
			stickTitanium,
			stickTungsten,
			stickZinc,
			
			nuggetIridium,
			nuggetSilver,
			nuggetAluminium,
			nuggetTitanium,
			nuggetChrome,
			nuggetElectrum,
			nuggetTungsten,
			nuggetLead,
			nuggetZinc,
			nuggetBrass,
			nuggetSteel,
			nuggetPlatinum,
			nuggetNickel,
			nuggetInvar,
			nuggetOsmium,
			nuggetCopper,
			nuggetTin,
			nuggetBronze,

			bouleSilicon, platePlastic, lensDiamond, lensRuby, lensEmerald, lensSapphire, chipDiamond,
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
