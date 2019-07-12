package gtclassic.material;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

public class GTMaterial {

	static GTMaterialFlag fluid = GTMaterialFlag.FLUID;
	static GTMaterialFlag gas = GTMaterialFlag.GAS;
	static GTMaterialFlag dust = GTMaterialFlag.DUST;
	static GTMaterialFlag gemRuby = GTMaterialFlag.RUBY;
	static GTMaterialFlag gemSapphire = GTMaterialFlag.SAPPHIRE;
	static GTMaterialFlag ingot = GTMaterialFlag.INGOT;
	static GTMaterialFlag blockMetal = GTMaterialFlag.BLOCKMETAL;
	static GTMaterialFlag blockGem = GTMaterialFlag.BLOCKGEM;
	static GTMaterialFlag[] rubyAll = { dust, gemRuby, blockGem };
	static GTMaterialFlag[] sapphireAll = { dust, gemSapphire, blockGem };
	static GTMaterialFlag[] metalAll = { dust, ingot, blockMetal };
	/** Master Material Map **/
	private static HashMap<String, GTMaterial> generatedMap = new HashMap<>();
	/** Material Instances **/
	public static final GTMaterial Aluminium = new GTMaterial("Aluminium", 128, 200, 240, metalAll);
	public static final GTMaterial Bauxite = new GTMaterial("Bauxite", 200, 100, 0, dust);
	public static final GTMaterial Beryllium = new GTMaterial("Beryllium", 30, 80, 50, fluid);
	public static final GTMaterial Calcite = new GTMaterial("Calcite", 250, 230, 220, dust);
	public static final GTMaterial Calcium = new GTMaterial("Calcium", 155, 96, 80, fluid);
	public static final GTMaterial Carbon = new GTMaterial("Carbon", 0, 0, 0, dust);
	public static final GTMaterial Chlorine = new GTMaterial("Chlorine", 50, 150, 150, fluid);
	public static final GTMaterial Chrome = new GTMaterial("Chrome", 240, 210, 230, false, metalAll);
	public static final GTMaterial Deuterium = new GTMaterial("Deuterium", 255, 255, 0, gas);
	public static final GTMaterial Electrum = new GTMaterial("Electrum", 255, 255, 100, metalAll);
	public static final GTMaterial Emerald = new GTMaterial("Emerald", 80, 255, 80, dust);
	public static final GTMaterial EnderEye = new GTMaterial("EnderEye", 160, 250, 230, dust);
	public static final GTMaterial EnderPearl = new GTMaterial("EnderPearl", 108, 220, 200, dust);
	public static final GTMaterial Flint = new GTMaterial("Flint", 0, 32, 64, dust);
	public static final GTMaterial Helium = new GTMaterial("Helium", 255, 255, 0, gas);
	public static final GTMaterial Helium3 = new GTMaterial("Helium3", 255, 255, 0, gas);
	public static final GTMaterial Hydrogen = new GTMaterial("Hydrogen", 0, 38, 255, gas);
	public static final GTMaterial Iridium = new GTMaterial("Iridium", 255, 255, 255, false, metalAll);
	public static final GTMaterial Lazurite = new GTMaterial("Lazurite", 100, 120, 255, dust);
	public static final GTMaterial Lithium = new GTMaterial("Lithium", 87, 150, 204, dust);
	public static final GTMaterial Mercury = new GTMaterial("Mercury", 250, 250, 250, fluid);
	public static final GTMaterial Methane = new GTMaterial("Methane", 255, 50, 130, gas);
	public static final GTMaterial Nitrogen = new GTMaterial("Nitrogen", 0, 190, 190, gas);
	public static final GTMaterial Oil = new GTMaterial("Oil", 0, 0, 0, fluid);
	public static final GTMaterial Oxygen = new GTMaterial("Oxygen", 100, 160, 220, gas);
	public static final GTMaterial Potassium = new GTMaterial("Potassium", 250, 250, 250, fluid);
	public static final GTMaterial Pyrite = new GTMaterial("Pyrite", 150, 120, 40, dust);
	public static final GTMaterial Ruby = new GTMaterial("Ruby", 255, 100, 100, rubyAll);
	public static final GTMaterial Sapphire = new GTMaterial("Sapphire", 100, 100, 200, sapphireAll);
	public static final GTMaterial SapphireGreen = new GTMaterial("GreenSapphire", 100, 200, 130, dust);
	public static final GTMaterial Silicon = new GTMaterial("Silicon", 60, 60, 80, dust, ingot);
	public static final GTMaterial Sodalite = new GTMaterial("Sodalite", 20, 20, 255, dust);
	public static final GTMaterial Sodium = new GTMaterial("Sodium", 0, 38, 255, fluid);
	public static final GTMaterial Steel = new GTMaterial("Steel", 128, 128, 128, false, metalAll);
	public static final GTMaterial Titanium = new GTMaterial("Titanium", 170, 143, 222, false, metalAll);
	public static final GTMaterial Tritium = new GTMaterial("Tritium", 255, 0, 0, gas);
	public static final GTMaterial Tungsten = new GTMaterial("Tungsten", 50, 50, 50, false, metalAll);
	public static final GTMaterial Uranium = new GTMaterial("Uranium", 50, 240, 50, dust);
	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private Color color;
	private boolean smeltable;
	// Expand for needs

	/**
	 * 
	 * @param displayName - The name of the material
	 * @param r           - red value 0-255
	 * @param g           - green value 0-255
	 * @param b           - blue value 0-255
	 * @param smeltable   - Can it be smelted
	 * @param flags       - Types of items and blocks to generate from material
	 */
	public GTMaterial(String displayName, int r, int g, int b, GTMaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.color = new Color(r, g, b, 255);
		this.smeltable = true;
		for (GTMaterialFlag flag : flags) {
			mask |= flag.getMask();
		}
		generatedMap.put(name, this);
	}

	public GTMaterial(String displayName, int r, int g, int b, boolean smeltable, GTMaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.color = new Color(r, g, b, 255);
		this.smeltable = smeltable;
		for (GTMaterialFlag flag : flags) {
			mask |= flag.getMask();
		}
		generatedMap.put(name, this);
	}

	public static void removeMapEntries(String name) {
		generatedMap.remove(name);
	}

	public boolean hasFlag(GTMaterialFlag flag) {
		return (mask & flag.getMask()) != 0;
	}

	/** Getters/Setters **/
	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return name;
	}

	public boolean getSmeltable() {
		return smeltable;
	}

	public Color getColor() {
		return color;
	}

	public static boolean isGem(GTMaterial mat) {
		return mat.hasFlag(GTMaterialFlag.RUBY) || mat.hasFlag(GTMaterialFlag.SAPPHIRE);
	}

	/** Map Get **/
	public GTMaterial get(String name) {
		// This is where you could do error handling if you want
		return generatedMap.get(name);
	}

	/** Helper for looping **/
	public static Collection<GTMaterial> values() {
		return generatedMap.values();
	}
}
