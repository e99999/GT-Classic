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
import gtclassic.item.GTItemRockCutter;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolChainsaw;
import gtclassic.tool.GTToolFile;
import gtclassic.tool.GTToolHammer;
import gtclassic.tool.GTToolMiningDrill;
import ic2.core.IC2;
import net.minecraft.item.Item;

public class GTItems {

	static List<Item> toRegister = new ArrayList<Item>();
	public static final GTItemComponent glassTube = createItem(new GTItemComponent("glass_tube", 0, false));
	public static final GTItemComponent bouleSilicon = createItem(new GTItemComponent("silicon_boule", 1, false));
	public static final GTItemComponent boardSteel = createItem(new GTItemComponent("steel_circuit_board", 2, false));
	public static final GTItemComponent boardAluminium = createItem(
			new GTItemComponent("aluminium_circuit_board", 3, false));
	public static final GTItemComponent boardElectrum = createItem(
			new GTItemComponent("electrum_circuit_board", 4, false));
	public static final GTItemComponent boardPlatinum = createItem(
			new GTItemComponent("platinum_circuit_board", 5, false));
	public static final GTItemComponent circuitParts = createItem(new GTItemComponent("circuit_parts", 6, false));
	public static final GTItemComponent circuitDiamond = createItem(new GTItemComponent("circuit_diamond", 7, false));
	public static final GTItemComponent circuitRuby = createItem(new GTItemComponent("circuit_ruby", 8, false));
	public static final GTItemComponent circuitEmerald = createItem(new GTItemComponent("circuit_emerald", 9, false));
	public static final GTItemComponent circuitSapphire = createItem(new GTItemComponent("circut_sapphire", 10, false));
	public static final GTItemComponent moldBlank = createItem(new GTItemComponent("mold_blank", 48, false));
	public static final GTItemComponent moldBlock = createItem(new GTItemComponent("mold_block", 49, true));
	public static final GTItemComponent moldIngot = createItem(new GTItemComponent("mold_ingot", 50, true));
	public static final GTItemComponent moldNugget = createItem(new GTItemComponent("mold_nugget", 51, true));
	public static final GTItemComponent moldPlate = createItem(new GTItemComponent("mold_plate", 52, true));
	public static final GTItemComponent moldStick = createItem(new GTItemComponent("mold_stick", 53, true));
	public static final GTItemComponent moldCable = createItem(new GTItemComponent("mold_cable", 54, true));
	public static final GTItemComponent moldTube = createItem(new GTItemComponent("mold_tube", 55, true));

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

	public static void generateTools() {
		powerMaterials.addAll(Arrays.asList(GTMaterial.Silicon, GTMaterial.Tin, GTMaterial.Copper, GTMaterial.Lead, GTMaterial.Iron, GTMaterial.Bronze, GTMaterial.Brass, GTMaterial.Zinc, GTMaterial.RefinedIron, GTMaterial.Graphite));
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.PLATE) && !mat.equals(GTMaterial.Silicon)) {
				IC2.getInstance().createItem(new GTToolFile(mat));
			}

		}

		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.PLATE) && !mat.equals(GTMaterial.Silicon)) {
				IC2.getInstance().createItem(new GTToolHammer(mat));
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
		return powerMaterials.contains(mat);
	}

}
