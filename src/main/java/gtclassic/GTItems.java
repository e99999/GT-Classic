package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.fluid.GTFluidTube;
import gtclassic.item.GTItemCloakingDevice;
import gtclassic.item.GTItemComponent;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemEchotron;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemEnergyOrb;
import gtclassic.item.GTItemEnergyPack;
import gtclassic.item.GTItemJackHammer;
import gtclassic.item.GTItemLightHelmet;
import gtclassic.item.GTItemLithiumBattery;
import gtclassic.item.GTItemSpringBoots;
import gtclassic.item.GTItemReactorHeat;
import gtclassic.item.GTItemReactorRod;
import gtclassic.item.GTItemRockCutter;
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
	public static final GTItemReactorHeat heatStorageHelium1 = createItem(new GTItemReactorHeat("helium_single", 2, 60000));
	public static final GTItemReactorHeat heatStorageHelium3 = createItem(new GTItemReactorHeat("helium_triple", 3, 180000));
	public static final GTItemReactorHeat heatStorageHelium6 = createItem(new GTItemReactorHeat("helium_six", 4, 360000));
	public static final GTItemReactorRod rodThorium1 = createItem(new GTItemReactorRod("thorium_single", 16, 1));
	public static final GTItemReactorRod rodThorium2 = createItem(new GTItemReactorRod("thorium_double", 17, 2));
	public static final GTItemReactorRod rodThorium4 = createItem(new GTItemReactorRod("thorium_quad", 18, 4));
	public static final GTItemReactorRod rodPlutonium1 = createItem(new GTItemReactorRod("plutonium_single", 19, 1));
	public static final GTItemReactorRod rodPlutonium2 = createItem(new GTItemReactorRod("plutonium_double", 20, 2));
	public static final GTItemReactorRod rodPlutonium4 = createItem(new GTItemReactorRod("plutonium_quad", 21, 4));
	public static final GTItemComponent circuitEnergy = createItem(new GTItemComponent("energy_circuit", 6, 0));
	public static final GTItemComponent circuitData = createItem(new GTItemComponent("data_circuit", 7, 0));
	public static final GTItemComponent chipData = createItem(new GTItemComponent("data_chip", 9, 0));
	public static final GTItemComponent superConductor = createItem(new GTItemComponent("super_conductor", 10, 0));
	public static final GTItemComponent orbData = createItem(new GTItemComponent("data_orb", 11, 0));
	public static final GTItemDuctTape ductTape = createItem(new GTItemDuctTape());
	public static final GTItemSpringBoots springBoots = createItem(new GTItemSpringBoots());
	public static final GTItemEchotron echotron = createItem(new GTItemEchotron());
	public static final GTItemDestructoPack destructoPack = createItem(new GTItemDestructoPack());
	public static final GTItemElectromagnet electroMagnet = createItem(new GTItemElectromagnet());
	private static GTItemLithiumBattery lithiumBattery;
	private static GTItemEnergyOrb orbEnergy;
	public static final GTItemCloakingDevice cloakingDevice = createItem(new GTItemCloakingDevice());
	private static GTItemEnergyPack lithiumBatpack;
	private static GTItemEnergyPack lapotronPack;
	public static final GTItemLightHelmet lightHelment = createItem(new GTItemLightHelmet());
	public static final GTItemTeslaStaff teslaStaff = createItem(new GTItemTeslaStaff());
	public static final GTItemRockCutter rockCutter = createItem(new GTItemRockCutter());
	public static final GTItemJackHammer jackHammer = createItem(new GTItemJackHammer());
	public static final GTItemSurvivalScanner portableScanner = createItem(new GTItemSurvivalScanner());
	public static final GTItemCreativeScanner debugScanner = createItem(new GTItemCreativeScanner());
	public static final GTFluidTube testTube = createItem(new GTFluidTube());

	public static GTItemLithiumBattery getLithiumBattery() {
		return lithiumBattery;
	}

	public static GTItemEnergyOrb getOrbEnergy() {
		return orbEnergy;
	}

	public static GTItemEnergyPack getLithiumBatpack() {
		return lithiumBatpack;
	}

	public static GTItemEnergyPack getLapotronPack() {
		return lapotronPack;
	}

	public static void initBaubleItems() {
		IBaublesPlugin plugin = IC2.loader.getPlugin("baubles", IBaublesPlugin.class);
		if (GTConfig.compatBaubles && plugin != null) {
			lithiumBattery = createItem(new GTItemBaublesLithiumBattery());
			lithiumBatpack = createItem(new GTItemBaublesEnergyPack(26, "gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 128));
			orbEnergy = createItem(new GTItemBaublesEnergyOrb());
			lapotronPack = createItem(new GTItemBaublesEnergyPack(13, "gtclassic:textures/models/armor/lapotronpack", 10000000, "lapotron_pack", ".lapotronPack", 4, 8192));
		} else {
			lithiumBattery = createItem(new GTItemLithiumBattery());
			lithiumBatpack = createItem(new GTItemEnergyPack(26, "gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 128));
			orbEnergy = createItem(new GTItemEnergyOrb());
			lapotronPack = createItem(new GTItemEnergyPack(13, "gtclassic:textures/models/armor/lapotronpack", 10000000, "lapotron_pack", ".lapotronPack", 4, 8192));
		}
	}

	public static <T extends Item> T createItem(T item) {
		toRegister.add(item);
		return item;
	}

	public static void registerItems() {
		for (Item item : GTMaterialGen.itemMap.values()) {
			IC2.getInstance().createItem(item);
		}
		for (Item item : toRegister) {
			IC2.getInstance().createItem(item);
		}
	}
}
