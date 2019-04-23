package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.item.GTItemComponent;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemFluidTube;
import gtclassic.item.GTItemGrinder;
import gtclassic.item.GTItemMagnifyingGlass;
import gtclassic.item.GTItemMatch;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.material.GTMaterialItem;
import gtclassic.tool.GTToolGen;
import ic2.core.IC2;
import net.minecraft.item.Item;

public class GTItems {

	static List<Item> toRegister = new ArrayList<Item>();

	public static final GTItemMagnifyingGlass magnifyingGlass = createItem(new GTItemMagnifyingGlass());

	public static final GTItemMatch match = createItem(new GTItemMatch());
	public static final GTItemComponent plasticFletching = createItem(
			new GTItemComponent("plastic_fletching", 15, 2, false));
	public static final GTItemComponent woodPlate = createItem(new GTItemComponent("wood_plate", 10, 0, false));
	public static final GTItemComponent resinPCB = createItem(new GTItemComponent("resin_pcb", 11, 0, false));
	public static final GTItemComponent plasticPCB = createItem(new GTItemComponent("plastic_pcb", 12, 0, false));

	public static final GTMaterialItem foilTantalum = createItem(
			new GTMaterialItem(GTMaterial.Tantalum, GTMaterialFlag.FOIL));
	public static final GTMaterialItem foilNiobium = createItem(
			new GTMaterialItem(GTMaterial.Niobium, GTMaterialFlag.FOIL));
	public static final GTMaterialItem smallPlateGermanium = createItem(
			new GTMaterialItem(GTMaterial.Germanium, GTMaterialFlag.SMALLPLATE));
	public static final GTMaterialItem wireTin = createItem(new GTMaterialItem(GTMaterial.Tin, GTMaterialFlag.WIRE));
	public static final GTMaterialItem wireLead = createItem(new GTMaterialItem(GTMaterial.Lead, GTMaterialFlag.WIRE));
	public static final GTMaterialItem wireRedAlloy = createItem(
			new GTMaterialItem(GTMaterial.RedAlloy, GTMaterialFlag.WIRE));

	public static final GTItemComponent basicTransistor = createItem(
			new GTItemComponent("basic_transistor", 14, 0, false));
	public static final GTItemComponent basicCapacitor = createItem(
			new GTItemComponent("basic_capacitor", 15, 0, false));

	public static final GTItemComponent moldBlank = createItem(new GTItemComponent("mold_blank", 0, 2, false));
	public static final GTItemComponent moldBlock = createItem(new GTItemComponent("mold_block", 1, 2, true));
	public static final GTItemComponent moldIngot = createItem(new GTItemComponent("mold_ingot", 2, 2, true));
	public static final GTItemComponent moldNugget = createItem(new GTItemComponent("mold_nugget", 3, 2, true));
	public static final GTItemComponent moldPlate = createItem(new GTItemComponent("mold_plate", 4, 2, true));
	public static final GTItemComponent moldStick = createItem(new GTItemComponent("mold_stick", 5, 2, true));
	public static final GTItemComponent moldCable = createItem(new GTItemComponent("mold_cable", 6, 2, true));
	public static final GTItemComponent moldTube = createItem(new GTItemComponent("mold_tube", 7, 2, true));
	public static final GTItemComponent moldGear = createItem(new GTItemComponent("mold_gear", 8, 2, true));

	public static final GTItemGrinder grinderSteel = createItem(new GTItemGrinder(GTMaterial.Steel));
	public static final GTItemGrinder grinderTitanium = createItem(new GTItemGrinder(GTMaterial.Titanium));
	public static final GTItemGrinder grinderTungstensteel = createItem(new GTItemGrinder(GTMaterial.TungstenSteel));

	public static final GTItemDestructoPack destructoPack = createItem(new GTItemDestructoPack());
	public static final GTItemCraftingTablet craftingTablet = createItem(new GTItemCraftingTablet());
	public static final GTItemElectromagnet electroMagnet = createItem(new GTItemElectromagnet());
	public static final GTItemTeslaStaff teslaStaff = createItem(new GTItemTeslaStaff());
	public static final GTItemRockCutter rockCutter = createItem(new GTItemRockCutter());
	public static final GTItemSurvivalScanner portableScanner = createItem(new GTItemSurvivalScanner());
	public static final GTItemCreativeScanner debugScanner = createItem(new GTItemCreativeScanner());

	public static final GTItemFluidTube testTube = createItem(new GTItemFluidTube()); // This is my testing fluid
																						// container

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
