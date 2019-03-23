package gtclassic.material;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

import gtclassic.GTConfig;

public class GTMaterial {

	static GTMaterialFlag PARTICLE = GTMaterialFlag.PARTICLE;
	static GTMaterialFlag CHEM = GTMaterialFlag.CHEMICAL;
	static GTMaterialFlag PLASMA = GTMaterialFlag.PLASMA;
	static GTMaterialFlag SMALLDUST = GTMaterialFlag.SMALLDUST;
	static GTMaterialFlag DUST = GTMaterialFlag.DUST;
	static GTMaterialFlag GEM = GTMaterialFlag.GEM;
	static GTMaterialFlag INGOT = GTMaterialFlag.INGOT;
	static GTMaterialFlag NUGGET = GTMaterialFlag.NUGGET;
	static GTMaterialFlag PLATE = GTMaterialFlag.PLATE;
	static GTMaterialFlag STICK = GTMaterialFlag.STICK;
	static GTMaterialFlag GEAR = GTMaterialFlag.GEAR;
	static GTMaterialFlag BLOCK = GTMaterialFlag.BLOCK;
	static GTMaterialFlag CASING = GTMaterialFlag.CASING;

	static GTMaterialFlag[] DUST_ALL = { SMALLDUST, DUST };

	static GTMaterialFlag[] GEM_ALL = { SMALLDUST, DUST, GEM, BLOCK };

	static GTMaterialFlag[] METAL_ALL = { SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, GEAR, BLOCK, CASING };

	static GTMaterialFlag[] METAL_IC2 = { SMALLDUST, NUGGET, PLATE, STICK, GEAR, CASING };

	static GTMaterialFlag[] METAL_MC = { SMALLDUST, PLATE, STICK, GEAR, CASING };

	/** Master Material Map **/
	private static HashMap<String, GTMaterial> generatedMap = new HashMap<>();

	public static GTMaterial Hydrogen = new GTMaterial("Hydrogen", 1.0F, 0, 1, Color.blue, false, CHEM);
	public static GTMaterial Dueterium = new GTMaterial("Dueterium", 1.0F, 0, 1, Color.yellow, false, CHEM);
	public static GTMaterial Tritium = new GTMaterial("Tritium", 1.0F, 0, 1, Color.red, false, CHEM);
	public static GTMaterial Helium = new GTMaterial("Helium", 1.0F, 0, 1, Color.yellow, false, CHEM, PLASMA);
	public static GTMaterial Helium3 = new GTMaterial("Helium3", 1.0F, 0, 1, Color.yellow, false, CHEM);
	public static GTMaterial Lithium = new GTMaterial("Lithium", 1.0F, 0, 1, Color.lightGray, false, CHEM);
	public static GTMaterial Carbon = new GTMaterial("Carbon", 1.0F, 0, 1, Color.black, false, CHEM);
	public static GTMaterial Methane = new GTMaterial("Methane", 1.0F, 0, 1, Color.pink, false, CHEM);
	public static GTMaterial Mercury = new GTMaterial("Mercury", 1.0F, 0, 1, Color.gray, false, CHEM);
	public static GTMaterial Berilium = new GTMaterial("Berilium", 1.0F, 0, 1, Color.green, false, CHEM);
	public static GTMaterial Calcium = new GTMaterial("Calcium", 1.0F, 0, 1, Color.white, false, CHEM);
	public static GTMaterial Sodium = new GTMaterial("Sodium", 1.0F, 0, 1, Color.blue, false, CHEM);
	public static GTMaterial Chlorine = new GTMaterial("Chlorine", 1.0F, 0, 1, Color.cyan, false, CHEM);
	public static GTMaterial Potassium = new GTMaterial("Potassium", 1.0F, 0, 1, Color.white, false, CHEM);
	public static GTMaterial Nitrogen = new GTMaterial("Nitrogen", 1.0F, 0, 1, Color.white, false, CHEM, PLASMA);
	public static GTMaterial Oxygen = new GTMaterial("Oxygen", 1.0F, 0, 1, Color.white, false, CHEM, PLASMA);
	public static GTMaterial SulfuricAcid = new GTMaterial("SulfuricAcid", 1.0F, 0, 1, Color.orange, false, CHEM);

	/** Material Instances **/
	public static GTMaterial Almandine = new GTMaterial("Almandine", 1.0F, 0, 1, new Color(255, 0, 0), false, DUST_ALL);
	public static GTMaterial Aluminium = new GTMaterial("Aluminium", 10.0F, 128, 2, new Color(128, 200, 240),
			!GTConfig.harderAluminium, METAL_ALL);
	public static GTMaterial Andradite = new GTMaterial("Andradite", 1.0F, 0, 1, new Color(150, 120, 0), false,
			DUST_ALL);
	public static GTMaterial Ashes = new GTMaterial("Ashes", 1.0F, 0, 1, new Color(150, 150, 150), false, DUST_ALL);
	public static GTMaterial Basalt = new GTMaterial("Basalt", 1.0F, 0, 1, new Color(30, 20, 20), false, DUST_ALL);//
	public static GTMaterial Bauxite = new GTMaterial("Bauxite", 1.0F, 0, 1, new Color(200, 100, 0), false, DUST_ALL);
	public static GTMaterial Brass = new GTMaterial("Brass", 7.0F, 96, 1, new Color(255, 180, 0), true, METAL_ALL);
	public static GTMaterial Bronze = new GTMaterial("Bronze", 6.0F, 192, 2, new Color(230, 83, 34), true, METAL_IC2);
	public static GTMaterial Calcite = new GTMaterial("Calcite", 1.0F, 0, 1, new Color(250, 230, 220), false, DUST_ALL);
	public static GTMaterial Charcoal = new GTMaterial("Charcoal", 1.0F, 0, 1, new Color(100, 70, 70), false,
			SMALLDUST);
	public static GTMaterial Chrome = new GTMaterial("Chrome", 11.0F, 256, 3, new Color(245, 206, 227), false,
			METAL_ALL);
	public static GTMaterial Cinnabar = new GTMaterial("Cinnabar", 1.0F, 0, 1, new Color(150, 0, 0), false, DUST_ALL);
	public static GTMaterial Clay = new GTMaterial("Clay", 1.0F, 0, 1, new Color(200, 200, 220), false, SMALLDUST);
	public static GTMaterial Coal = new GTMaterial("Coal", 1.0F, 0, 1, new Color(70, 70, 70), false, SMALLDUST);
	public static GTMaterial Constantan = new GTMaterial("Constantan", 8.0F, 128, 2, new Color(196, 116, 77), true,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK);
	public static GTMaterial Copper = new GTMaterial("Copper", 1.0F, 0, 1, new Color(180, 113, 61), true, METAL_IC2);
	public static GTMaterial DarkAshes = new GTMaterial("DarkAshes", 1.0F, 0, 1, new Color(50, 50, 50), false,
			DUST_ALL);
	public static GTMaterial Diamond = new GTMaterial("Diamond", 8.0F, 1280, 3, new Color(51, 235, 203), false,
			DUST_ALL);
	public static GTMaterial Electrum = new GTMaterial("Electrum", 12.0F, 64, 2, new Color(255, 255, 100), true,
			METAL_ALL);
	public static GTMaterial Emerald = new GTMaterial("Emerald", 7.0F, 256, 3, new Color(80, 255, 80), false, DUST_ALL);
	public static GTMaterial EnderEye = new GTMaterial("EnderEye", 1.0F, 0, 1, new Color(160, 250, 230), false,
			DUST_ALL);
	public static GTMaterial EnderPearl = new GTMaterial("Enderpearl", 1.0F, 0, 1, new Color(108, 220, 200), false,
			DUST_ALL);
	public static GTMaterial Endstone = new GTMaterial("Endstone", 1.0F, 0, 1, new Color(250, 250, 198), false,
			DUST_ALL);
	public static GTMaterial Flint = new GTMaterial("Flint", 2.5F, 64, 1, new Color(0, 32, 64), false, DUST_ALL);
	public static GTMaterial Galena = new GTMaterial("Galena", 1.0F, 0, 1, new Color(100, 60, 100), false, DUST_ALL);
	public static GTMaterial GarnetRed = new GTMaterial("RedGarnet", 7.0F, 128, 2, new Color(200, 80, 80), false,
			SMALLDUST, DUST, GEM);
	public static GTMaterial GarnetYellow = new GTMaterial("YellowGarnet", 7.0F, 128, 2, new Color(200, 200, 80), false,
			SMALLDUST, DUST, GEM);
	public static GTMaterial Germanium = new GTMaterial("Germanium", 8.0F, 64, 1, new Color(250, 250, 250), true,
			METAL_ALL);
	public static GTMaterial Glowstone = new GTMaterial("Glowstone", 1.0F, 0, 1, new Color(255, 255, 0), false,
			SMALLDUST);
	public static GTMaterial Gold = new GTMaterial("Gold", 12.0F, 64, 2, new Color(255, 255, 30), true, METAL_MC);
	public static GTMaterial Granite = new GTMaterial("Granite", 1.0F, 0, 1, new Color(165, 89, 39), false, DUST_ALL);
	public static GTMaterial Graphite = new GTMaterial("Graphite", 5.0F, 32, 2, new Color(96, 96, 96), false, SMALLDUST,
			DUST, NUGGET, INGOT, STICK, PLATE, STICK);
	public static GTMaterial Grossular = new GTMaterial("Grossular", 1.0F, 0, 1, new Color(200, 100, 0), false,
			DUST_ALL);
	public static GTMaterial Gunpowder = new GTMaterial("Gunpowder", 1.0F, 0, 1, new Color(128, 128, 128), false,
			SMALLDUST);
	public static GTMaterial Iridium = new GTMaterial("Iridium", 6.0F, 5120, 4, new Color(255, 255, 255), false, NUGGET,
			INGOT, GEAR, STICK, CASING, BLOCK);
	public static GTMaterial Iron = new GTMaterial("Iron", 6.0F, 256, 2, new Color(184, 184, 184), true, PLASMA,
			SMALLDUST, PLATE, STICK, CASING);
	public static GTMaterial Invar = new GTMaterial("Invar", 6.0F, 256, 2, new Color(180, 180, 120), true, METAL_ALL);
	public static GTMaterial Lazurite = new GTMaterial("Lazurite", 1.0F, 0, 1, new Color(100, 120, 255), false,
			DUST_ALL);
	public static GTMaterial Lead = new GTMaterial("Lead", 8.0F, 64, 1, new Color(140, 100, 140), true, METAL_ALL);
	public static GTMaterial Maganlium = new GTMaterial("Magnalium", 6.0F, 256, 2, new Color(200, 190, 255), false,
			INGOT, NUGGET, PLATE);
	public static GTMaterial Magnesium = new GTMaterial("Magnesium", 1.0F, 0, 1, new Color(255, 200, 200), false,
			DUST_ALL);
	public static GTMaterial Magnetite = new GTMaterial("Magnetite", 1.0F, 0, 1, new Color(0, 0, 0), true, DUST_ALL);
	public static GTMaterial Manganese = new GTMaterial("Manganese", 1.0F, 0, 1, new Color(250, 235, 250), true,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, BLOCK);
	public static GTMaterial Marble = new GTMaterial("Marble", 1.0F, 0, 1, new Color(200, 200, 200), false, DUST_ALL);//
	public static GTMaterial Netherrack = new GTMaterial("Netherrack", 1.0F, 0, 1, new Color(200, 0, 0), false,
			SMALLDUST);
	public static GTMaterial Nichrome = new GTMaterial("Nichrome", 10.0F, 256, 3, new Color(88, 133, 188), false,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK);
	public static GTMaterial Nickel = new GTMaterial("Nickel", 6.0F, 64, 2, new Color(200, 200, 250), true, METAL_ALL);
	public static GTMaterial Niobium = new GTMaterial("Niobium", 1.0F, 0, 1, new Color(200, 200, 200), false, DUST_ALL);
	public static GTMaterial Obsidian = new GTMaterial("Obsidian", 1.0F, 0, 1, new Color(80, 50, 100), false,
			SMALLDUST);
	public static GTMaterial Olivine = new GTMaterial("Olivine", 7.0F, 256, 2, new Color(150, 255, 150), false,
			GEM_ALL);
	public static GTMaterial Osmium = new GTMaterial("Osmium", 16.0F, 1280, 4, new Color(50, 50, 255), false,
			METAL_ALL);
	public static GTMaterial Phosphor = new GTMaterial("Phosphor", 1.0F, 0, 1, new Color(255, 255, 0), false, DUST_ALL);
	public static GTMaterial Plastic = new GTMaterial("Plastic", 1.0F, 0, 1, new Color(235, 235, 235), false, DUST,
			SMALLDUST);
	public static GTMaterial Platinum = new GTMaterial("Platinum", 12.0F, 64, 2, new Color(255, 255, 200), true,
			METAL_ALL);
	public static GTMaterial Plutonium = new GTMaterial("Plutonium", 6.0F, 512, 3, new Color(240, 50, 50), false,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE);
	public static GTMaterial Pyrite = new GTMaterial("Pyrite", 1.0F, 0, 1, new Color(150, 120, 40), false, DUST_ALL);
	public static GTMaterial Pyrope = new GTMaterial("Pyrope", 1.0F, 0, 1, new Color(120, 50, 100), false, DUST_ALL);
	public static GTMaterial RedRock = new GTMaterial("RedRock", 1.0F, 0, 1, new Color(255, 80, 50), false, DUST_ALL);//
	public static GTMaterial Redstone = new GTMaterial("Redstone", 1.0F, 0, 1, new Color(200, 0, 0), false, SMALLDUST);
	public static GTMaterial RefinedIron = new GTMaterial("RefinedIron", 6.0F, 384, 2, new Color(220, 235, 235), true,
			STICK, PLATE, CASING);
	public static GTMaterial Ruby = new GTMaterial("Ruby", 7.0F, 256, 2, new Color(255, 100, 100), false, GEM_ALL);
	public static GTMaterial Rutile = new GTMaterial("Rutile", 1.0F, 0, 1, new Color(212, 13, 92), false, DUST_ALL);
	public static GTMaterial Saltpeter = new GTMaterial("Saltpeter", 1.0F, 0, 1, new Color(230, 230, 230), false,
			DUST_ALL);
	public static GTMaterial Sapphire = new GTMaterial("Sapphire", 7.0F, 256, 2, new Color(100, 100, 200), false,
			GEM_ALL);
	public static GTMaterial SapphireGreen = new GTMaterial("GreenSapphire", 7.0F, 256, 2, new Color(100, 200, 130),
			false, GEM_ALL);
	public static GTMaterial Sheldonite = new GTMaterial("Sheldonite", 1.0F, 0, 1, new Color(215, 212, 137), false,
			DUST_ALL);
	public static GTMaterial Silicon = new GTMaterial("Silicon", 1.0F, 0, 1, new Color(60, 60, 80), false, PLATE, CHEM);
	public static GTMaterial Silver = new GTMaterial("Silver", 10.0F, 64, 2, new Color(215, 225, 230), true, METAL_IC2);
	public static GTMaterial Slag = new GTMaterial("Slag", 1.0F, 0, 1, new Color(64, 48, 0), false, DUST_ALL);
	public static GTMaterial Sodalite = new GTMaterial("Sodalite", 1.0F, 0, 1, new Color(20, 20, 255), false, DUST_ALL);
	public static GTMaterial Spessartine = new GTMaterial("Spessartine", 1.0F, 0, 1, new Color(255, 100, 100), false,
			DUST_ALL);
	public static GTMaterial Sphalerite = new GTMaterial("Sphalerite", 1.0F, 0, 1, new Color(200, 140, 40), false,
			DUST_ALL);
	public static GTMaterial Steel = new GTMaterial("Steel", 6.0F, 512, 2, new Color(128, 128, 128), false, METAL_ALL);
	public static GTMaterial Stone = new GTMaterial("Stone", 1.0F, 0, 1, new Color(196, 196, 196), false, DUST_ALL);
	public static GTMaterial Sulfur = new GTMaterial("Sulfur", 1.0F, 0, 1, new Color(200, 200, 0), false, SMALLDUST,
			DUST, CHEM, PLASMA);
	public static GTMaterial Tantalite = new GTMaterial("Tantalite", 1.0F, 0, 1, new Color(102, 77, 61), false,
			DUST_ALL);
	public static GTMaterial Tantalum = new GTMaterial("Tantalum", 8.0F, 5120, 3, new Color(96, 96, 96), false, DUST,
			SMALLDUST, NUGGET, INGOT, STICK, PLATE, BLOCK);
	public static GTMaterial Thorium = new GTMaterial("Thorium", 6.0F, 512, 2, new Color(0, 30, 0), false, SMALLDUST,
			DUST, NUGGET, INGOT);
	public static GTMaterial Tin = new GTMaterial("Tin", 1.0F, 0, 1, new Color(220, 220, 220), true, METAL_IC2);
	public static GTMaterial Titanium = new GTMaterial("Titanium", 8.0F, 2560, 3, new Color(170, 143, 222), true,
			METAL_ALL);
	public static GTMaterial Tungstate = new GTMaterial("Tungstate", 1.0F, 0, 1, new Color(60, 60, 60), false,
			DUST_ALL);
	public static GTMaterial Tungsten = new GTMaterial("Tungsten", 8.0F, 5120, 3, new Color(50, 50, 50), false,
			METAL_ALL);
	public static GTMaterial TungstenSteel = new GTMaterial("Tungstensteel", 10.0F, 5120, 4, new Color(100, 100, 160),
			false, METAL_ALL);
	public static GTMaterial Uranium = new GTMaterial("Uranium", 6.0F, 512, 3, new Color(50, 240, 50), false, DUST_ALL);
	public static GTMaterial Uvarovite = new GTMaterial("Uvarovite", 1.0F, 0, 1, new Color(180, 255, 180), false,
			DUST_ALL);
	public static GTMaterial Wood = new GTMaterial("Wood", 1.0F, 0, 1, new Color(137, 103, 39), false, DUST_ALL);
	public static GTMaterial Zinc = new GTMaterial("Zinc", 1.0F, 0, 1, new Color(250, 240, 240), true, METAL_ALL);

	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private float speed;
	private int durability;
	private int level;
	private boolean smeltable;
	private Color color;
	// Expand for needs

	public GTMaterial(String displayName, float speed, int durability, int level, Color color, boolean smeltable,
			GTMaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.speed = speed;
		this.durability = durability;
		this.level = level;
		this.smeltable = smeltable;
		this.color = color;
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

	public float getSpeed() {
		return speed;
	}

	public int getDurability() {
		return durability;
	}

	public int getLevel() {
		return level;
	}

	public boolean getSmeltable() {
		return smeltable;
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
