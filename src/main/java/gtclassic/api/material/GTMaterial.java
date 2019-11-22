package gtclassic.api.material;

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
	public static final GTMaterial Aluminium = new GTMaterial(13, "Aluminium", 128, 200, 240, metalAll);
	public static final GTMaterial Argon = new GTMaterial(18, "Argon", 255, 100, 255, gas);
	public static final GTMaterial Bauxite = new GTMaterial("Bauxite", 200, 100, 0, dust);
	public static final GTMaterial Basalt = new GTMaterial("Basalt", 30, 20, 20, dust);
	public static final GTMaterial Beryllium = new GTMaterial(4, "Beryllium", 30, 80, 50, fluid);
	public static final GTMaterial Calcite = new GTMaterial("Calcite", 250, 230, 220, dust);
	public static final GTMaterial Calcium = new GTMaterial(20, "Calcium", 155, 96, 80, fluid);
	public static final GTMaterial Carbon = new GTMaterial(6, "Carbon", 0, 0, 0, dust);
	public static final GTMaterial Chlorine = new GTMaterial(17, "Chlorine", 50, 150, 150, fluid);
	public static final GTMaterial Chrome = new GTMaterial(24, "Chrome", 240, 210, 230, metalAll);
	public static final GTMaterial Deuterium = new GTMaterial("Deuterium", 255, 255, 0, gas);
	public static final GTMaterial Electrum = new GTMaterial("Electrum", 255, 255, 100, metalAll);
	public static final GTMaterial Emerald = new GTMaterial("Emerald", 80, 255, 80, dust);
	public static final GTMaterial EnderEye = new GTMaterial("EnderEye", 160, 250, 230, dust);
	public static final GTMaterial EnderPearl = new GTMaterial("EnderPearl", 108, 220, 200, dust);
	public static final GTMaterial Flint = new GTMaterial("Flint", 0, 32, 64, dust);
	public static final GTMaterial Helium = new GTMaterial(2, "Helium", 255, 255, 0, gas);
	public static final GTMaterial Helium3 = new GTMaterial("Helium3", 255, 255, 0, gas);
	public static final GTMaterial Hydrogen = new GTMaterial(1, "Hydrogen", 0, 38, 255, gas);
	public static final GTMaterial Iridium = new GTMaterial(77, "Iridium", 255, 255, 255, metalAll);
	public static final GTMaterial Lazurite = new GTMaterial("Lazurite", 100, 120, 255, dust);
	public static final GTMaterial Lithium = new GTMaterial(3, "Lithium", 87, 150, 204, dust);
	public static final GTMaterial Mercury = new GTMaterial(88, "Mercury", 250, 250, 250, fluid);
	public static final GTMaterial Methane = new GTMaterial("Methane", 255, 50, 130, gas);
	public static final GTMaterial Neon = new GTMaterial(10, "Neon", 255, 100, 100, gas);
	public static final GTMaterial Nitrogen = new GTMaterial(7, "Nitrogen", 0, 190, 190, gas);
	public static final GTMaterial Oil = new GTMaterial("Oil", 0, 0, 0, fluid);
	public static final GTMaterial Oxygen = new GTMaterial(8, "Oxygen", 100, 160, 220, gas);
	public static final GTMaterial Phosphorus = new GTMaterial(15, "Phosphorus", 190, 0, 0, dust);
	public static final GTMaterial Potassium = new GTMaterial(19, "Potassium", 250, 250, 250, fluid);
	public static final GTMaterial Platinum = new GTMaterial(78, "Platinum", 255, 255, 200, metalAll);
	public static final GTMaterial Plutonium = new GTMaterial(94, "Plutonium", 240, 50, 50, false, metalAll);
	public static final GTMaterial Pyrite = new GTMaterial("Pyrite", 150, 120, 40, dust);
	public static final GTMaterial Ruby = new GTMaterial("Ruby", 255, 75, 75, rubyAll);
	public static final GTMaterial Sapphire = new GTMaterial("Sapphire", 75, 75, 200, sapphireAll);
	public static final GTMaterial Silicon = new GTMaterial(14, "Silicon", 60, 60, 80, dust, ingot);
	public static final GTMaterial Sodalite = new GTMaterial("Sodalite", 20, 20, 255, dust);
	public static final GTMaterial Sodium = new GTMaterial(11, "Sodium", 0, 38, 255, fluid);
	public static final GTMaterial Sulfur = new GTMaterial(16, "Sulfur", 200, 200, 0, dust);
	public static final GTMaterial SulfuricAcid = new GTMaterial("SulfuricAcid", 255, 106, 0, fluid);
	public static final GTMaterial Thorium = new GTMaterial(90, "Thorium", 0, 30, 0, false, metalAll);
	public static final GTMaterial Titanium = new GTMaterial(22, "Titanium", 170, 143, 222, metalAll);
	public static final GTMaterial Tritium = new GTMaterial("Tritium", 255, 0, 0, gas);
	public static final GTMaterial Tungsten = new GTMaterial(74, "Tungsten", 50, 50, 50, metalAll);
	public static final GTMaterial Uranium = new GTMaterial("Uranium", 50, 240, 50, dust);
	/** Instance Members **/
	private int element;
	private String name, displayName;
	private int mask;
	private Color color;
	private boolean smeltable;

	public GTMaterial(int element, String displayName, int r, int g, int b, GTMaterialFlag... flags) {
		this(element, displayName, r, g, b, true, flags);
	}

	public GTMaterial(String displayName, int r, int g, int b, GTMaterialFlag... flags) {
		this(-1, displayName, r, g, b, true, flags);
	}

	public GTMaterial(String displayName, int r, int g, int b, boolean smeltable, GTMaterialFlag... flags) {
		this(-1, displayName, r, g, b, smeltable, flags);
	}

	/**
	 * @param element     - element number
	 * @param displayName - The name of the material
	 * @param r           - red value 0-255
	 * @param g           - green value 0-255
	 * @param b           - blue value 0-255
	 * @param smeltable   - Can it be smelted
	 * @param flags       - Types of items and blocks to generate from material
	 */
	public GTMaterial(int element, String displayName, int r, int g, int b, boolean smeltable,
			GTMaterialFlag... flags) {
		this.element = element;
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

	public int getElementNumber() {
		return element;
	}

	public static boolean isGem(GTMaterial mat) {
		return mat.hasFlag(GTMaterialFlag.RUBY) || mat.hasFlag(GTMaterialFlag.SAPPHIRE)
				|| mat.hasFlag(GTMaterialFlag.BLOCKGEM);
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
