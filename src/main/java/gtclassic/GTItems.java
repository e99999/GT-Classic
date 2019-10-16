package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.fluid.GTFluidTube;
import gtclassic.item.GTItemCloakingDevice;
import gtclassic.item.GTItemComponent;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDataOrbStorage;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemEchotron;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemEnergyOrb;
import gtclassic.item.GTItemEnergyPack;
import gtclassic.item.GTItemJackHammer;
import gtclassic.item.GTItemLightHelmet;
import gtclassic.item.GTItemLithiumBattery;
import gtclassic.item.GTItemMortar;
import gtclassic.item.GTItemReactorHeat;
import gtclassic.item.GTItemReactorRod;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSpringBoots;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.item.baubles.GTItemBaublesEnergyOrb;
import gtclassic.item.baubles.GTItemBaublesEnergyPack;
import gtclassic.item.baubles.GTItemBaublesLithiumBattery;
import gtclassic.material.GTMaterialGen;
import ic2.core.IC2;
import ic2.core.util.obj.plugins.IBaublesPlugin;
import net.minecraft.item.Item;

public class GTItems {

	private GTItems() {
		throw new IllegalStateException("Utility class");
	}

	static List<Item> toRegister = new ArrayList<>();
	public static GTFluidTube testTube;
	public static GTItemReactorHeat heatStorageHelium1;
	public static GTItemReactorHeat heatStorageHelium3;
	public static GTItemReactorHeat heatStorageHelium6;
	public static GTItemReactorRod rodThorium1;
	public static GTItemReactorRod rodThorium2;
	public static GTItemReactorRod rodThorium4;
	public static GTItemReactorRod rodPlutonium1;
	public static GTItemReactorRod rodPlutonium2;
	public static GTItemReactorRod rodPlutonium4;
	public static GTItemComponent circuitEnergy;
	public static GTItemComponent circuitData;
	public static GTItemComponent chipData;
	public static GTItemComponent superConductor;
	public static GTItemComponent orbData;
	public static GTItemDataOrbStorage orbDataStorage;
	public static GTItemMortar flintMortar;
	public static GTItemMortar ironMortar;
	public static GTItemDuctTape ductTape;
	public static GTItemSpringBoots springBoots;
	public static GTItemEchotron echotron;
	public static GTItemDestructoPack destructoPack;
	public static GTItemElectromagnet electroMagnet;
	public static GTItemLithiumBattery lithiumBattery;
	public static GTItemEnergyOrb orbEnergy;
	public static GTItemCloakingDevice cloakingDevice;
	public static GTItemEnergyPack lithiumBatpack;
	public static GTItemEnergyPack lapotronPack;
	public static GTItemLightHelmet lightHelment;
	public static GTItemTeslaStaff teslaStaff;
	public static GTItemRockCutter rockCutter;
	public static GTItemJackHammer jackHammer;
	public static GTItemSurvivalScanner portableScanner;
	public static GTItemCreativeScanner debugScanner;

	public static void initItems() {
		IBaublesPlugin plugin = IC2.loader.getPlugin("baubles", IBaublesPlugin.class);
		boolean doBaubles = GTConfig.compatBaubles && plugin != null;
		testTube = createItem(new GTFluidTube());
		heatStorageHelium1 = createItem(new GTItemReactorHeat("helium_single", 2, 60000));
		heatStorageHelium3 = createItem(new GTItemReactorHeat("helium_triple", 3, 180000));
		heatStorageHelium6 = createItem(new GTItemReactorHeat("helium_six", 4, 360000));
		rodThorium1 = createItem(new GTItemReactorRod("thorium_single", 16, 1));
		rodThorium2 = createItem(new GTItemReactorRod("thorium_double", 17, 2));
		rodThorium4 = createItem(new GTItemReactorRod("thorium_quad", 18, 4));
		rodPlutonium1 = createItem(new GTItemReactorRod("plutonium_single", 19, 1));
		rodPlutonium2 = createItem(new GTItemReactorRod("plutonium_double", 20, 2));
		rodPlutonium4 = createItem(new GTItemReactorRod("plutonium_quad", 21, 4));
		circuitEnergy = createItem(new GTItemComponent("energy_circuit", 6, 0));
		circuitData = createItem(new GTItemComponent("data_circuit", 7, 0));
		chipData = createItem(new GTItemComponent("data_chip", 9, 0));
		superConductor = createItem(new GTItemComponent("superconductor", 10, 0));
		orbData = createItem(new GTItemComponent("data_orb", 11, 0));
		orbDataStorage = createItem(new GTItemDataOrbStorage());
		flintMortar = createItem(new GTItemMortar("flint_mortar", 1, 2, 3));
		ironMortar = createItem(new GTItemMortar("iron_mortar", 2, 2, 95));
		ductTape = createItem(new GTItemDuctTape());
		springBoots = createItem(new GTItemSpringBoots());
		echotron = createItem(new GTItemEchotron());
		destructoPack = createItem(new GTItemDestructoPack());
		electroMagnet = createItem(new GTItemElectromagnet());
		lithiumBattery = doBaubles ? createItem(new GTItemBaublesLithiumBattery())
				: createItem(new GTItemLithiumBattery());
		orbEnergy = doBaubles ? createItem(new GTItemBaublesEnergyOrb()) : createItem(new GTItemEnergyOrb());
		cloakingDevice = createItem(new GTItemCloakingDevice());
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
