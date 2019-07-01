package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.fluid.GTFluidTube;
import gtclassic.item.GTItemComponent;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemEnergyOrb;
import gtclassic.item.GTItemEnergyPack;
import gtclassic.item.GTItemJackHammer;
import gtclassic.item.GTItemLithiumBattery;
import gtclassic.item.GTItemReactorHeat;
import gtclassic.item.GTItemReactorHeat.GTItemHeatStorageTypes;
import gtclassic.item.GTItemReactorRod;
import gtclassic.item.GTItemReactorRod.GTItemRodTypes;
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
	public static final GTItemReactorHeat heatStorageSingle = createItem(new GTItemReactorHeat(GTItemHeatStorageTypes.SINGLE));
	public static final GTItemReactorHeat heatStorageTriple = createItem(new GTItemReactorHeat(GTItemHeatStorageTypes.TRIPLE));
	public static final GTItemReactorHeat heatStorageSix = createItem(new GTItemReactorHeat(GTItemHeatStorageTypes.SIX));
	public static final GTItemReactorRod rodThorium1 = createItem(new GTItemReactorRod(GTItemRodTypes.SINGLETHORIUM));
	public static final GTItemReactorRod rodThorium2 = createItem(new GTItemReactorRod(GTItemRodTypes.DOUBLETHORIUM));
	public static final GTItemReactorRod rodThorium4 = createItem(new GTItemReactorRod(GTItemRodTypes.QUADTHORIUM));
	public static final GTItemReactorRod rodPlutonium1 = createItem(new GTItemReactorRod(GTItemRodTypes.SINGLEPLUTONIUM));
	public static final GTItemReactorRod rodPlutonium2 = createItem(new GTItemReactorRod(GTItemRodTypes.DOUBLEPLUTONIUM));
	public static final GTItemReactorRod rodPlutonium4 = createItem(new GTItemReactorRod(GTItemRodTypes.QUADPLUTONIUM));
	public static final GTItemComponent circuitEnergy = createItem(new GTItemComponent("energy_circuit", 6, 0));
	public static final GTItemComponent circuitData = createItem(new GTItemComponent("data_circuit", 7, 0));
	public static final GTItemComponent chipData = createItem(new GTItemComponent("data_chip", 9, 0));
	public static final GTItemComponent superConductor = createItem(new GTItemComponent("super_conductor", 10, 0));
	public static final GTItemComponent orbData = createItem(new GTItemComponent("data_orb", 11, 0));
	public static final GTItemDuctTape ductTape = createItem(new GTItemDuctTape());
	public static final GTItemDestructoPack destructoPack = createItem(new GTItemDestructoPack());
	public static final GTItemElectromagnet electroMagnet = createItem(new GTItemElectromagnet());
	private static GTItemLithiumBattery lithiumBattery;
	private static GTItemEnergyOrb orbEnergy;
	private static GTItemEnergyPack lithiumBatpack;
	private static GTItemEnergyPack lapotronPack;
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

	public static void initBaubleItems(){
		IBaublesPlugin plugin = IC2.loader.getPlugin("baubles", IBaublesPlugin.class);
		if (plugin != null){
			lithiumBattery = createItem(new GTItemBaublesLithiumBattery());
			lithiumBatpack = createItem(new GTItemBaublesEnergyPack(26, "gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 128));
			orbEnergy = createItem(new GTItemBaublesEnergyOrb());
			lapotronPack = createItem(new GTItemBaublesEnergyPack(13, "gtclassic:textures/models/armor/lapotronpack", 10000000, "lapotron_pack", ".lapotronPack", 4, 8192));
		}else {
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
