package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.item.GTItemComponent;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemFluidTube;
import gtclassic.item.GTItemMachineSwitch;
import gtclassic.item.GTItemMagnifyingGlass;
import gtclassic.item.GTItemMatch;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolGen;
import ic2.core.IC2;
import net.minecraft.item.Item;

public class GTItems {

	private GTItems() {
		throw new IllegalStateException("Utility class");
	}

	static List<Item> toRegister = new ArrayList<>();

	// @formatter:off
	public static final GTItemComponent
	plasticFletching = createItem(new GTItemComponent("plastic_fletching", 15, 2, false)),
	woodPlate = createItem(new GTItemComponent("wood_plate", 10, 0, false)),
	magnesiaBrick = createItem(new GTItemComponent("magnesiacarbon_brick", 13, 2, false )),
	resinPCB = createItem(new GTItemComponent("resin_pcb", 11, 0, false)),
	plasticPCB = createItem(new GTItemComponent("plastic_pcb", 12, 0, false)),
	basicTransistor = createItem(new GTItemComponent("basic_transistor", 14, 0, false)),
	basicCapacitor = createItem(new GTItemComponent("basic_capacitor", 15, 0, false)),
	motorLV = createItem(new GTItemComponent("motor_lv", 0, 3, false)),
	motorMV = createItem(new GTItemComponent("motor_mv", 1, 3, false)),
	motorHV = createItem(new GTItemComponent("motor_hv", 2, 3, false)),
	partRobotArm = createItem(new GTItemComponent("part_robotarm", 3, 3, false)),
	
	moldBlank = createItem(new GTItemComponent("mold_blank", 0, 2, false)),
	moldBlock = createItem(new GTItemComponent("mold_block", 1, 2, true)),
	moldIngot = createItem(new GTItemComponent("mold_ingot", 2, 2, true)),
	moldNugget = createItem(new GTItemComponent("mold_nugget", 3, 2, true)),
	moldPlate = createItem(new GTItemComponent("mold_plate", 4, 2, true)),
	moldStick = createItem(new GTItemComponent("mold_stick", 5, 2, true)),
	moldCable = createItem(new GTItemComponent("mold_cable", 6, 2, true)),
	moldTube = createItem(new GTItemComponent("mold_tube", 7, 2, true)),
	moldGear = createItem(new GTItemComponent("mold_gear", 8, 2, true)),
	moldBottle = createItem(new GTItemComponent("mold_bottle", 9, 2, true));
	// @formatter:on

	public static final GTItemMachineSwitch machineSwitch = createItem(new GTItemMachineSwitch());

	public static final GTItemMatch match = createItem(new GTItemMatch());
	public static final GTItemMagnifyingGlass magnifyingGlass = createItem(new GTItemMagnifyingGlass());
	public static final GTItemDestructoPack destructoPack = createItem(new GTItemDestructoPack());
	public static final GTItemCraftingTablet craftingTablet = createItem(new GTItemCraftingTablet());
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

		GTToolGen.generateTools();

		for (Item item : toRegister) {
			IC2.getInstance().createItem(item);
		}
	}
}
