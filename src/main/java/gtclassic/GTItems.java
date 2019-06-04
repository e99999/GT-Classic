package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.item.GTItemComponent;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemFluidTube;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.material.GTMaterialGen;
import ic2.core.IC2;
import net.minecraft.item.Item;

public class GTItems {

	private GTItems() {
		throw new IllegalStateException("Utility class");
	}

	static List<Item> toRegister = new ArrayList<>();
	public static final GTItemComponent circuitEnergy = createItem(new GTItemComponent("energy_circuit", 6, 0));
	public static final GTItemComponent circuitData = createItem(new GTItemComponent("data_circuit", 7, 0));
	public static final GTItemComponent chipData = createItem(new GTItemComponent("data_chip", 9, 0));
	public static final GTItemComponent superConductor = createItem(new GTItemComponent("super_conductor", 10, 0));
	public static final GTItemComponent orbData = createItem(new GTItemComponent("data_orb", 11, 0));
	public static final GTItemDestructoPack destructoPack = createItem(new GTItemDestructoPack());
	public static final GTItemElectromagnet electroMagnet = createItem(new GTItemElectromagnet());
	public static final GTItemTeslaStaff teslaStaff = createItem(new GTItemTeslaStaff());
	public static final GTItemRockCutter rockCutter = createItem(new GTItemRockCutter());
	public static final GTItemSurvivalScanner portableScanner = createItem(new GTItemSurvivalScanner());
	public static final GTItemCreativeScanner debugScanner = createItem(new GTItemCreativeScanner());
	public static final GTItemFluidTube testTube = createItem(new GTItemFluidTube());

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
