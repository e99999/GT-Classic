package gtclassic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gtclassic.item.GTItemComponent;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemMagnifyingGlass;
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolChainsaw;
import gtclassic.tool.GTToolFile;
import gtclassic.tool.GTToolHammer;
import gtclassic.tool.GTToolKnife;
import gtclassic.tool.GTToolMiningDrill;
import gtclassic.tool.GTToolWrench;
import ic2.core.IC2;
import net.minecraft.item.Item;

public class GTItems {

	static List<Item> toRegister = new ArrayList<Item>();

	public static final GTToolKnife ironKnife = createItem(new GTToolKnife(GTMaterial.Iron));

	public static final GTItemComponent glassTube = createItem(new GTItemComponent("glass_tube", 0, 0, false));
	public static final GTItemMagnifyingGlass magnifyingGlass = createItem(new GTItemMagnifyingGlass());

	public static final GTItemComponent resinPCB = createItem(new GTItemComponent("resin_pcb", 12, 0, false));
	public static final GTItemComponent germaniumSubstrate = createItem(
			new GTItemComponent("germanium_substrate", 13, 0, false));
	public static final GTItemComponent basicTransistor = createItem(
			new GTItemComponent("basic_transistor", 14, 0, false));
	public static final GTItemComponent basicCapacitor = createItem(
			new GTItemComponent("basic_capacitor", 15, 0, false));

	public static final GTItemComponent moldBlank = createItem(new GTItemComponent("mold_blank", 0, 1, false));
	public static final GTItemComponent moldBlock = createItem(new GTItemComponent("mold_block", 1, 1, true));
	public static final GTItemComponent moldIngot = createItem(new GTItemComponent("mold_ingot", 2, 1, true));
	public static final GTItemComponent moldNugget = createItem(new GTItemComponent("mold_nugget", 3, 1, true));
	public static final GTItemComponent moldPlate = createItem(new GTItemComponent("mold_plate", 4, 1, true));
	public static final GTItemComponent moldStick = createItem(new GTItemComponent("mold_stick", 5, 1, true));
	public static final GTItemComponent moldCable = createItem(new GTItemComponent("mold_cable", 6, 1, true));
	public static final GTItemComponent moldTube = createItem(new GTItemComponent("mold_tube", 7, 1, true));

	public static final GTItemDestructoPack destructoPack = createItem(new GTItemDestructoPack());
	public static final GTItemCraftingTablet craftingTablet = createItem(new GTItemCraftingTablet());
	public static final GTItemElectromagnet electroMagnet = createItem(new GTItemElectromagnet());
	public static final GTItemTeslaStaff teslaStaff = createItem(new GTItemTeslaStaff());
	public static final GTItemRockCutter rockCutter = createItem(new GTItemRockCutter());
	public static final GTItemSurvivalScanner portableScanner = createItem(new GTItemSurvivalScanner());
	public static final GTItemCreativeScanner debugScanner = createItem(new GTItemCreativeScanner());

	private static Set<GTMaterial> powerMaterials = new HashSet<GTMaterial>();

	public static <T extends Item> T createItem(T item) {
		toRegister.add(item);
		return item;
	}

	public static void registerItems() {
		for (Item item : GTMaterialGen.itemMap.values()) {
			IC2.getInstance().createItem(item);
		}

		generateTools();

		for (Item item : toRegister) {
			IC2.getInstance().createItem(item);
		}
	}

	/*
	 * This is a very dirty way to generate tools from the material registry without
	 * having tools be part of the registry, it will eventually be refactored but
	 * for now it makes adding and removing tools easy.
	 * 
	 */

	public static void generateTools() {
		powerMaterials.addAll(
				Arrays.asList(GTMaterial.Silicon, GTMaterial.Gold, GTMaterial.Tin, GTMaterial.Copper, GTMaterial.Lead,
						GTMaterial.Iron, GTMaterial.Bronze, GTMaterial.Brass, GTMaterial.Zinc, GTMaterial.RefinedIron,
						GTMaterial.Graphite, GTMaterial.Germanium, GTMaterial.Tantalum, GTMaterial.Manganese));
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.PLATE) && !mat.equals(GTMaterial.Silicon) && !mat.equals(GTMaterial.Graphite)
					&& !mat.equals(GTMaterial.Tantalum) && !mat.equals(GTMaterial.Manganese)) {
				IC2.getInstance().createItem(new GTToolFile(mat));
			}

		}

		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.PLATE) && !mat.equals(GTMaterial.Silicon) && !mat.equals(GTMaterial.Graphite)
					&& !mat.equals(GTMaterial.Tantalum) && !mat.equals(GTMaterial.Manganese)) {
				IC2.getInstance().createItem(new GTToolHammer(mat));
			}
		}

		// for (GTMaterial mat : GTMaterial.values()) {
		// if (mat.hasFlag(GTMaterialFlag.PLATE) && !mat.equals(GTMaterial.Silicon) &&
		// !mat.equals(GTMaterial.Graphite)
		// && !mat.equals(GTMaterial.Tantalum) && !mat.equals(GTMaterial.Manganese)) {
		// IC2.getInstance().createItem(new GTToolKnife(mat));
		// }
		// }

		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.PLATE) && !mat.equals(GTMaterial.Silicon) && !mat.equals(GTMaterial.Graphite)
					&& !mat.equals(GTMaterial.Tantalum) && !mat.equals(GTMaterial.Manganese)) {
				IC2.getInstance().createItem(new GTToolWrench(mat));
			}
		}

		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.PLATE) && canBePowerTool(mat)) {
				IC2.getInstance().createItem(new GTToolMiningDrill(mat));
			}
		}

		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.PLATE) && canBePowerTool(mat)) {
				IC2.getInstance().createItem(new GTToolChainsaw(mat));
			}
		}

	}

	public static boolean canBePowerTool(GTMaterial mat) {
		return !powerMaterials.contains(mat);
	}

}
