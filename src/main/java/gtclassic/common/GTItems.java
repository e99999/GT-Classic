package gtclassic.common;

import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.item.GTItemCloakingDevice;
import gtclassic.common.item.GTItemComponent;
import gtclassic.common.item.GTItemCreativeScanner;
import gtclassic.common.item.GTItemDataOrbStorage;
import gtclassic.common.item.GTItemDepletedRod;
import gtclassic.common.item.GTItemDestructoPack;
import gtclassic.common.item.GTItemDuctTape;
import gtclassic.common.item.GTItemEchotron;
import gtclassic.common.item.GTItemElectromagnet;
import gtclassic.common.item.GTItemEnergyOrb;
import gtclassic.common.item.GTItemEnergyPack;
import gtclassic.common.item.GTItemFluidTube;
import gtclassic.common.item.GTItemForcefield;
import gtclassic.common.item.GTItemIsotopicRod;
import gtclassic.common.item.GTItemJackHammer;
import gtclassic.common.item.GTItemLightHelmet;
import gtclassic.common.item.GTItemLithiumBattery;
import gtclassic.common.item.GTItemMagnifyingGlass;
import gtclassic.common.item.GTItemReactorHeat;
import gtclassic.common.item.GTItemReactorRod;
import gtclassic.common.item.GTItemRockCutter;
import gtclassic.common.item.GTItemSensorStick;
import gtclassic.common.item.GTItemSprayCan;
import gtclassic.common.item.GTItemSpringBoots;
import gtclassic.common.item.GTItemSurvivalScanner;
import gtclassic.common.item.GTItemTeslaStaff;
import gtclassic.common.item.baubles.GTItemBaublesEnergyOrb;
import gtclassic.common.item.baubles.GTItemBaublesEnergyPack;
import gtclassic.common.item.baubles.GTItemBaublesLithiumBattery;
import ic2.core.IC2;
import ic2.core.util.obj.plugins.IBaublesPlugin;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class GTItems {

	private GTItems() {
		throw new IllegalStateException("Utility class");
	}

	static List<Item> toRegister = new ArrayList<>();
	public static GTItemFluidTube testTube;
	public static GTItemReactorHeat heatStorageHelium1;
	public static GTItemReactorHeat heatStorageHelium3;
	public static GTItemReactorHeat heatStorageHelium6;
	public static GTItemReactorRod rodThorium1;
	public static GTItemReactorRod rodThorium2;
	public static GTItemReactorRod rodThorium4;
	public static GTItemReactorRod rodPlutonium1;
	public static GTItemReactorRod rodPlutonium2;
	public static GTItemReactorRod rodPlutonium4;
	public static GTItemIsotopicRod isotopicRodThorium;
	public static GTItemIsotopicRod isotopicRodPlutonium;
	public static GTItemDepletedRod nearDepletedRodThorium;
	public static GTItemDepletedRod nearDepletedRodPlutonium;
	public static GTItemDepletedRod reEnrichedRodThorium;
	public static GTItemDepletedRod reEnrichedRodPlutonium;
	public static GTItemComponent circuitEnergy;
	public static GTItemComponent circuitData;
	public static GTItemComponent chipData;
	public static GTItemComponent superConductor;
	public static GTItemComponent orbData;
	public static GTItemDataOrbStorage orbDataStorage;
	public static GTItemMagnifyingGlass magnifyingGlass;
	public static GTItemDuctTape ductTape;
	public static GTItemComponent fuelBinder;
	public static GTItemComponent fuelBinderMagic;
	public static GTItemComponent sprayCanEmpty;
	public static GTItemSprayCan sprayCan;
	public static GTItemSpringBoots springBoots;
	public static GTItemEchotron echotron;
	public static GTItemDestructoPack destructoPack;
	public static GTItemElectromagnet electroMagnet;
	public static GTItemLithiumBattery lithiumBattery;
	public static GTItemEnergyOrb orbEnergy;
	public static GTItemCloakingDevice cloakingDevice;
	public static GTItemForcefield forceField;
	public static GTItemEnergyPack lithiumBatpack;
	public static GTItemEnergyPack lapotronPack;
	public static GTItemLightHelmet lightHelment;
	public static GTItemTeslaStaff teslaStaff;
	public static GTItemRockCutter rockCutter;
	public static GTItemJackHammer jackHammer;
	public static GTItemSensorStick sensorStick;
	public static GTItemSurvivalScanner portableScanner;
	public static GTItemCreativeScanner debugScanner;

	public static void initItems() {
		IBaublesPlugin plugin = IC2.loader.getPlugin("baubles", IBaublesPlugin.class);
		boolean doBaubles = GTConfig.modcompat.compatBaubles && plugin != null;
		testTube = createItem(new GTItemFluidTube());
		heatStorageHelium1 = createItem(new GTItemReactorHeat("helium_single", 2, 60000));
		heatStorageHelium3 = createItem(new GTItemReactorHeat("helium_triple", 3, 180000));
		heatStorageHelium6 = createItem(new GTItemReactorHeat("helium_six", 4, 360000));
		rodThorium1 = createItem(new GTItemReactorRod("thorium_single", 16, 1));
		rodThorium2 = createItem(new GTItemReactorRod("thorium_double", 17, 2));
		rodThorium4 = createItem(new GTItemReactorRod("thorium_quad", 18, 4));
		rodPlutonium1 = createItem(new GTItemReactorRod("plutonium_single", 19, 1));
		rodPlutonium2 = createItem(new GTItemReactorRod("plutonium_double", 20, 2));
		rodPlutonium4 = createItem(new GTItemReactorRod("plutonium_quad", 21, 4));
		isotopicRodThorium = createItem(new GTItemIsotopicRod("thorium", 41));
		isotopicRodPlutonium = createItem(new GTItemIsotopicRod("plutonium", 44));
		nearDepletedRodThorium = createItem(new GTItemDepletedRod("near_depleted", "thorium", 8, 2));
		nearDepletedRodPlutonium = createItem(new GTItemDepletedRod("near_depleted", "plutonium", 11, 2));
		reEnrichedRodThorium = createItem(new GTItemDepletedRod("re_enriched", "thorium", 10, 2));
		reEnrichedRodPlutonium = createItem(new GTItemDepletedRod("re_enriched", "plutonium", 13, 2));
		circuitEnergy = createItem(new GTItemComponent("energy_circuit", 6, 0));
		circuitData = createItem(new GTItemComponent("data_circuit", 7, 0));
		chipData = createItem(new GTItemComponent("data_chip", 9, 0));
		superConductor = createItem(new GTItemComponent("superconductor", 10, 0));
		orbData = createItem(new GTItemComponent("data_orb", 11, 0));
		orbDataStorage = createItem(new GTItemDataOrbStorage());
		magnifyingGlass = createItem(new GTItemMagnifyingGlass());
		ductTape = createItem(new GTItemDuctTape());
		fuelBinder = createItem(new GTItemComponent("super_fuel_binder", 2, 2));
		fuelBinderMagic = createItem(new GTItemComponent("magic_super_fuel_binder", 2, 2));
		sprayCanEmpty = createItem(new GTItemComponent("spray_can_empty", 4, 2));
		sprayCan = createItem(new GTItemSprayCan());
		springBoots = createItem(new GTItemSpringBoots());
		echotron = createItem(new GTItemEchotron());
		destructoPack = createItem(new GTItemDestructoPack());
		electroMagnet = createItem(new GTItemElectromagnet());
		lithiumBattery = doBaubles ? createItem(new GTItemBaublesLithiumBattery())
				: createItem(new GTItemLithiumBattery());
		orbEnergy = doBaubles ? createItem(new GTItemBaublesEnergyOrb()) : createItem(new GTItemEnergyOrb());
		cloakingDevice = createItem(new GTItemCloakingDevice());
		forceField = createItem(new GTItemForcefield());
		lithiumBatpack = doBaubles
				? createItem(new GTItemBaublesEnergyPack(26, "gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 128))
				: createItem(new GTItemEnergyPack(26, "gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 128));
		lapotronPack = doBaubles
				? createItem(new GTItemBaublesEnergyPack(13, "gtclassic:textures/models/armor/lapotronpack", 10000000, "lapotron_pack", ".lapotronPack", 4, 8192))
				: createItem(new GTItemEnergyPack(13, "gtclassic:textures/models/armor/lapotronpack", 10000000, "lapotron_pack", ".lapotronPack", 4, 8192));
		lightHelment = createItem(new GTItemLightHelmet());
		teslaStaff = createItem(new GTItemTeslaStaff());
		rockCutter = createItem(new GTItemRockCutter());
		jackHammer = createItem(new GTItemJackHammer());
		sensorStick = createItem(new GTItemSensorStick());
		portableScanner = createItem(new GTItemSurvivalScanner());
		debugScanner = createItem(new GTItemCreativeScanner());
	}

	public static <T extends Item> T createItem(T item) {
		toRegister.add(item);
		return item;
	}

	public static void registerItems() {
		IC2.getInstance().createItem(testTube);
		for (Item item : GTMaterialGen.itemMap.values()) {
			IC2.getInstance().createItem(item);
		}
		for (Item item : toRegister) {
			if (item != testTube) {
				IC2.getInstance().createItem(item);
			}
		}
	}
}
