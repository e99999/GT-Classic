package gtclassic.material;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

public class GTMaterial {

	static GTMaterialFlag fluid = GTMaterialFlag.FLUID;
	static GTMaterialFlag gas = GTMaterialFlag.GAS;
	static GTMaterialFlag dust = GTMaterialFlag.DUST;
	static GTMaterialFlag gem = GTMaterialFlag.GEM;
	static GTMaterialFlag ingot = GTMaterialFlag.INGOT;
	static GTMaterialFlag nugget = GTMaterialFlag.NUGGET;
	static GTMaterialFlag block = GTMaterialFlag.BLOCK;
	static GTMaterialFlag[] gemAll = { dust, gem, block };
	static GTMaterialFlag[] metalAll = { dust, ingot, nugget, block };
	/** Master Material Map **/
	private static HashMap<String, GTMaterial> generatedMap = new HashMap<>();
	/** Material Instances **/
	public static final GTMaterial Aluminium = new GTMaterial("Aluminium", 128, 200, 240, 933, 10.0F, 128, 2, metalAll),
			Bauxite = new GTMaterial("Bauxite", 200, 100, 0, 2800, 3.0F, 0, 1, dust),
			Beryllium = new GTMaterial("Beryllium", 30, 80, 50, 1560, 1.0F, 0, 1, fluid),
			Calcite = new GTMaterial("Calcite", 250, 230, 220, 1612, 3.0F, 0, 1, dust),
			Calcium = new GTMaterial("Calcium", 155, 96, 80, 1115, 1.0F, 0, 1, fluid),
			Carbon = new GTMaterial("Carbon", 0, 0, 0, 3800, 1.0F, 0, 1, dust),
			Chlorine = new GTMaterial("Chlorine", 50, 150, 150, 171, 1.0F, 0, 1, fluid),
			Chrome = new GTMaterial("Chrome", 255, 230, 230, 2180, 11.0F, 256, 3, metalAll),
			Deuterium = new GTMaterial("Deuterium", 255, 255, 0, 14, 1.0F, 0, 1, gas),
			Emerald = new GTMaterial("Emerald", 80, 255, 80, 1803, 7.0F, 256, 3, dust),
			EnderEye = new GTMaterial("EnderEye", 160, 250, 230, 3447, 1.0F, 0, 1, dust),
			EnderPearl = new GTMaterial("Enderpearl", 108, 220, 200, 2723, 1.0F, 0, 1, dust),
			Flint = new GTMaterial("Flint", 0, 32, 64, 1986, 2.5F, 64, 1, dust),
			Helium = new GTMaterial("Helium", 255, 255, 0, 1, 1.0F, 0, 1, gas),
			Helium3 = new GTMaterial("Helium3", 255, 255, 0, 1, 1.0F, 0, 1, gas),
			Hydrogen = new GTMaterial("Hydrogen", 0, 38, 255, 14, 1.0F, 0, 1, gas),
			Iridium = new GTMaterial("Iridium", 255, 255, 255, 2719, 6.0F, 5120, 4, metalAll),
			Lazurite = new GTMaterial("Lazurite", 100, 120, 255, 1352, 1.0F, 0, 1, dust),
			Lithium = new GTMaterial("Lithium", 87, 150, 204, 453, 1.0F, 0, 1, dust),
			Mercury = new GTMaterial("Mercury", 250, 250, 250, 234, 1.0F, 0, 1, fluid),
			Methane = new GTMaterial("Methane", 255, 50, 130, 100, 1.0F, 0, 1, gas),
			Nitrogen = new GTMaterial("Nitrogen", 0, 190, 190, 63, 1.0F, 0, 1, gas),
			Potassium = new GTMaterial("Potassium", 250, 250, 250, 336, 1.0F, 0, 1, fluid),
			Pyrite = new GTMaterial("Pyrite", 150, 120, 40, 862, 3.0F, 0, 1, dust),
			Ruby = new GTMaterial("Ruby", 255, 100, 100, 2317, 7.0F, 256, 2, gemAll),
			Sapphire = new GTMaterial("Sapphire", 100, 100, 200, 2345, 7.0F, 256, 2, gemAll),
			SapphireGreen = new GTMaterial("GreenSapphire", 100, 200, 130, 2108, 7.0F, 256, 2, gemAll),
			Silicon = new GTMaterial("Silicon", 60, 60, 80, 1687, 1.0F, 0, 1, fluid, dust, nugget, ingot),
			Sodalite = new GTMaterial("Sodalite", 20, 20, 255, 1331, 3.0F, 0, 2, dust),
			Sodium = new GTMaterial("Sodium", 0, 38, 255, 370, 1.0F, 0, 1, fluid),
			Titanium = new GTMaterial("Titanium", 170, 143, 222, 1941, 8.0F, 2560, 3, metalAll),
			Tritium = new GTMaterial("Tritium", 255, 0, 0, 14, 1.0F, 0, 1, gas),
			Tungsten = new GTMaterial("Tungsten", 50, 50, 50, 3695, 8.0F, 5120, 3, metalAll),
			Uranium = new GTMaterial("Uranium", 50, 240, 50, 1405, 6.0F, 512, 3, dust);
	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private float speed;
	private int temp;
	private int durability;
	private int level;
	private Color color;
	// Expand for needs

	/**
	 * 
	 * @param displayName - The name of the material
	 * @param r           - red value 0-255
	 * @param g           - green value 0-255
	 * @param b           - blue value 0-255
	 * @param temp        - The melting point of the material
	 * @param speed       - Speed the material for tools
	 * @param durability  - Durability for material tools
	 * @param level       - Harvest Level for material tools
	 * @param flags       - Types of items and blocks to generate from material
	 */
	public GTMaterial(String displayName, int r, int g, int b, int temp, float speed, int durability, int level,
			GTMaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.color = new Color(r, g, b);
		this.speed = speed;
		this.temp = temp;
		this.durability = durability;
		this.level = level;
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

	public int getTemp() {
		return temp;
	}

	public float getSpeed() {
		return speed;
	}

	public int getDurability() {
		return durability;
	}

	public int getLevel() {
		return level;
	}

	public Color getColor() {
		return color;
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
