package gtclassic.tool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import ic2.core.IC2;
import net.minecraft.item.Item.ToolMaterial;

public class GTToolGen {

	/*
	 * This is a very dirty way to generate tools from the material registry without
	 * having tools be part of the registry, it will eventually be refactored but
	 * for now it makes adding and removing tools easy.
	 * 
	 */

	private static Set<ToolMaterial> blacklistPowerTools = new HashSet<ToolMaterial>();
	private static Set<ToolMaterial> blacklistVanillaTools = new HashSet<ToolMaterial>();

	public static void generateTools() {
		blacklistPowerTools.addAll(Arrays.asList(GTToolMaterial.Flint, GTToolMaterial.Iron, GTToolMaterial.Bronze,
				GTToolMaterial.Gold, GTToolMaterial.Brass, GTToolMaterial.RefinedIron, GTToolMaterial.BismuthBronze));

		blacklistVanillaTools.addAll(
				Arrays.asList(GTToolMaterial.Iron, GTToolMaterial.Bronze, GTToolMaterial.Gold, GTToolMaterial.Diamond));

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (!tmat.equals(GTToolMaterial.Flint)) {
				IC2.getInstance().createItem(new GTToolFile(tmat));
			}
		}

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (!tmat.equals(GTToolMaterial.Flint)) {
				IC2.getInstance().createItem(new GTToolHammer(tmat));
			}
		}

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (!tmat.equals(GTToolMaterial.Flint) && !tmat.equals(GTToolMaterial.Bronze)) {
				IC2.getInstance().createItem(new GTToolWrench(tmat));
			}
		}

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (!tmat.equals(GTToolMaterial.Flint)) {
				IC2.getInstance().createItem(new GTToolKnife(tmat));
			}
		}

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (canBeVanillaTool(tmat)) {
				IC2.getInstance().createItem(new GTToolSword(tmat));
			}
		}
		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (canBeVanillaTool(tmat)) {
				IC2.getInstance().createItem(new GTToolShovel(tmat));
			}
		}
		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (canBeVanillaTool(tmat)) {
				IC2.getInstance().createItem(new GTToolAxe(tmat));
			}
		}
		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (canBeVanillaTool(tmat)) {
				IC2.getInstance().createItem(new GTToolPickaxe(tmat));
			}
		}

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (canBePowerTool(tmat)) {
				IC2.getInstance().createItem(new GTToolMiningDrill(tmat));
			}
		}

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (canBePowerTool(tmat)) {
				IC2.getInstance().createItem(new GTToolChainsaw(tmat));
			}

		}

		for (ToolMaterial tmat : GTToolMaterial.toolMaterial) {
			if (canBePowerTool(tmat)) {
				IC2.getInstance().createItem(new GTToolElectricWrench(tmat));
			}

		}

	}

	public static boolean canBePowerTool(ToolMaterial tmat) {
		return !blacklistPowerTools.contains(tmat);
	}

	public static boolean canBeVanillaTool(ToolMaterial tmat) {
		return !blacklistVanillaTools.contains(tmat);
	}

}
