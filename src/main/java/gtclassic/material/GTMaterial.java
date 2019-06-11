package gtclassic.material;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

public class GTMaterial {

	static GTMaterialFlag fluid = GTMaterialFlag.FLUID;
	static GTMaterialFlag gas = GTMaterialFlag.GAS;
	static GTMaterialFlag dust = GTMaterialFlag.DUST;
	static GTMaterialFlag ruby = GTMaterialFlag.RUBY;
	static GTMaterialFlag sapphire = GTMaterialFlag.SAPPHIRE;
	static GTMaterialFlag ingot = GTMaterialFlag.INGOT;
	static GTMaterialFlag block = GTMaterialFlag.BLOCK;
	static GTMaterialFlag[] rubyAll = { dust, ruby, block };
	static GTMaterialFlag[] sapphireAll = { dust, sapphire, block };
	static GTMaterialFlag[] metalAll = { dust, ingot, block };
	/** Master Material Map **/
	private static HashMap<String, GTMaterial> generatedMap = new HashMap<>();
	/** Material Instances **/
	public static final GTMaterial Aluminium = new GTMaterial("Aluminium", 128, 200, 240, metalAll),
			Bauxite = new GTMaterial("Bauxite", 200, 100, 0, dust),
			Beryllium = new GTMaterial("Beryllium", 30, 80, 50, fluid),
			Calcite = new GTMaterial("Calcite", 250, 230, 220, dust),
			Calcium = new GTMaterial("Calcium", 155, 96, 80, fluid), 
			Carbon = new GTMaterial("Carbon", 0, 0, 0, dust),
			Chlorine = new GTMaterial("Chlorine", 50, 150, 150, fluid),
			Chrome = new GTMaterial("Chrome", 255, 230, 230, false, metalAll),
			Deuterium = new GTMaterial("Deuterium", 255, 255, 0, gas),
			Electrum = new GTMaterial("Electrum", 255, 255, 100, metalAll),
			Emerald = new GTMaterial("Emerald", 80, 255, 80, dust),
			EnderEye = new GTMaterial("EnderEye", 160, 250, 230, dust),
			EnderPearl = new GTMaterial("EnderPearl", 108, 220, 200, dust),
			Flint = new GTMaterial("Flint", 0, 32, 64, dust), 
			Helium = new GTMaterial("Helium", 255, 255, 0, gas),
			Helium3 = new GTMaterial("Helium3", 255, 255, 0, gas),
			Hydrogen = new GTMaterial("Hydrogen", 0, 38, 255, gas),
			Iridium = new GTMaterial("Iridium", 255, 255, 255, false, metalAll),
			Lazurite = new GTMaterial("Lazurite", 100, 120, 255, dust),
			Lithium = new GTMaterial("Lithium", 87, 150, 204, dust),
			Mercury = new GTMaterial("Mercury", 250, 250, 250, fluid),
			Methane = new GTMaterial("Methane", 255, 50, 130, gas),
			Nitrogen = new GTMaterial("Nitrogen", 0, 190, 190, gas), 
			Oil = new GTMaterial("Oil", 0, 0, 0, fluid),
			Oxygen = new GTMaterial("Oxygen", 100, 160, 220, gas),
			Potassium = new GTMaterial("Potassium", 250, 250, 250, fluid),
			Pyrite = new GTMaterial("Pyrite", 150, 120, 40, dust), 
			Ruby = new GTMaterial("Ruby", 255, 100, 100, rubyAll),
			Sapphire = new GTMaterial("Sapphire", 100, 100, 200, sapphireAll),
			SapphireGreen = new GTMaterial("GreenSapphire", 100, 200, 130, dust),
			Silicon = new GTMaterial("Silicon", 60, 60, 80, dust, ingot),
			Sodalite = new GTMaterial("Sodalite", 20, 20, 255, dust),
			Sodium = new GTMaterial("Sodium", 0, 38, 255, fluid),
			Steel = new GTMaterial("Steel", 128, 128, 128, false, metalAll),
			Titanium = new GTMaterial("Titanium", 170, 143, 222, false, metalAll),
			Tritium = new GTMaterial("Tritium", 255, 0, 0, gas),
			Tungsten = new GTMaterial("Tungsten", 50, 50, 50, false, metalAll),
			Uranium = new GTMaterial("Uranium", 50, 240, 50, dust);
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
