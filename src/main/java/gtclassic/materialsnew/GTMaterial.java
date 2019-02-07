package gtclassic.materialsnew;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

public class GTMaterial {

	static GTMaterialFlag SMALLDUST = GTMaterialFlag.SMALLDUST;
	static GTMaterialFlag DUST = GTMaterialFlag.DUST;
	static GTMaterialFlag GEM = GTMaterialFlag.GEM;
	static GTMaterialFlag INGOT = GTMaterialFlag.INGOT;
	static GTMaterialFlag NUGGET = GTMaterialFlag.NUGGET;
	static GTMaterialFlag PLATE = GTMaterialFlag.PLATE;
	static GTMaterialFlag STICK = GTMaterialFlag.STICK;
	static GTMaterialFlag BLOCK = GTMaterialFlag.BLOCK;
	static GTMaterialFlag CASING = GTMaterialFlag.CASING;

	static GTMaterialFlag[] DUST_ALL = {
			SMALLDUST, DUST
	};

	static GTMaterialFlag[] GEM_ALL = {
			SMALLDUST, DUST, GEM, BLOCK
	};

	static GTMaterialFlag[] METAL_ALL = {
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, BLOCK, CASING
	};

	static GTMaterialFlag[] METAL_IC2 = {
			SMALLDUST, NUGGET, PLATE, STICK, CASING
	};

	static GTMaterialFlag[] METAL_MC = {
			SMALLDUST, PLATE, STICK, CASING
	};

	/** Master Material Map **/
	private static HashMap<String, GTMaterial> generatedMap = new HashMap<>();

	/** Material Instances **/
	public static GTMaterial Almandine = new GTMaterial("Almandine", 1.0F, 0, 1, new Color(255, 0, 0), DUST_ALL);
	public static GTMaterial Aluminium = new GTMaterial("Aluminium", 10.0F, 128, 2, new Color(128, 200, 240),
			METAL_ALL);
	public static GTMaterial Andradite = new GTMaterial("Andradite", 1.0F, 0, 1, new Color(150, 120, 0), DUST_ALL);
	public static GTMaterial Ashes = new GTMaterial("Ashes", 1.0F, 0, 1, new Color(150, 150, 150), DUST_ALL);
	public static GTMaterial Basalt = new GTMaterial("Basalt", 1.0F, 0, 1, new Color(30, 20, 20), DUST_ALL);
	public static GTMaterial Bauxite = new GTMaterial("Bauxite", 1.0F, 0, 1, new Color(200, 100, 0), DUST_ALL);
	public static GTMaterial Brass = new GTMaterial("Brass", 7.0F, 96, 1, new Color(255, 180, 0), METAL_ALL);
	public static GTMaterial Bronze = new GTMaterial("Bronze", 6.0F, 192, 2, new Color(255, 180, 0), METAL_IC2);
	public static GTMaterial Calcite = new GTMaterial("Calcite", 1.0F, 0, 1, new Color(250, 230, 220), DUST_ALL);
	public static GTMaterial Charcoal = new GTMaterial("Charcoal", 1.0F, 0, 1, new Color(100, 70, 70), SMALLDUST);
	public static GTMaterial Chrome = new GTMaterial("Chrome", 11.0F, 256, 3, new Color(245, 206, 227), METAL_ALL);
	public static GTMaterial Cinnabar = new GTMaterial("Cinnabar", 1.0F, 0, 1, new Color(150, 0, 0), DUST_ALL);
	public static GTMaterial Clay = new GTMaterial("Clay", 1.0F, 0, 1, new Color(200, 200, 220), SMALLDUST);
	public static GTMaterial Coal = new GTMaterial("Coal", 1.0F, 0, 1, new Color(70, 70, 70), SMALLDUST);
	public static GTMaterial Copper = new GTMaterial("Copper", 1.0F, 0, 1, new Color(255, 100, 0), METAL_IC2);
	public static GTMaterial DarkAshes = new GTMaterial("DarkAshes", 1.0F, 0, 1, new Color(50, 50, 50), DUST_ALL);
	public static GTMaterial Diamond = new GTMaterial("Diamond", 8.0F, 1280, 3, new Color(100, 240, 245), DUST_ALL);
	public static GTMaterial Electrum = new GTMaterial("Electrum", 12.0F, 64, 2, new Color(255, 255, 100), METAL_ALL);
	public static GTMaterial Emerald = new GTMaterial("Emerald", 7.0F, 256, 3, new Color(80, 255, 80), DUST_ALL);
	public static GTMaterial EnderEye = new GTMaterial("Endereye", 1.0F, 0, 1, new Color(160, 250, 230), DUST_ALL);
	public static GTMaterial EnderPearl = new GTMaterial("Enderpearl", 1.0F, 0, 1, new Color(108, 220, 200), DUST_ALL);
	public static GTMaterial Endstone = new GTMaterial("Endstone", 1.0F, 0, 1, new Color(255, 255, 255), DUST_ALL);
	public static GTMaterial Flint = new GTMaterial("Flint", 1.0F, 0, 1, new Color(0, 32, 64), DUST_ALL);
	public static GTMaterial Galena = new GTMaterial("Galena", 1.0F, 0, 1, new Color(100, 60, 100), DUST_ALL);
	public static GTMaterial GalvenizedSteel = new GTMaterial("GalvenizedSteel", 8.0F, 512, 3, new Color(250, 240, 240),
			METAL_ALL);
	public static GTMaterial GarnetRed = new GTMaterial("RedGarnet", 7.0F, 128, 2, new Color(200, 80, 80), SMALLDUST,
			DUST,
			GEM);
	public static GTMaterial GarnetYellow = new GTMaterial("YellowGarnet", 7.0F, 128, 2, new Color(200, 200, 80),
			SMALLDUST,
			DUST,
			GEM);
	public static GTMaterial Glowstone = new GTMaterial("Glowstone", 1.0F, 0, 1, new Color(255, 255, 0), SMALLDUST);
	public static GTMaterial Gold = new GTMaterial("Gold", 12.0F, 64, 2, new Color(255, 255, 30), METAL_MC);
	public static GTMaterial Grossular = new GTMaterial("Grossular", 1.0F, 0, 1, new Color(200, 100, 0), DUST_ALL);
	public static GTMaterial Gunpowder = new GTMaterial("Gunpowder", 1.0F, 0, 1, new Color(128, 128, 128), SMALLDUST);
	public static GTMaterial Iridium = new GTMaterial("Iridium", 6.0F, 5120, 4, new Color(255, 255, 255), NUGGET, INGOT,
			STICK, PLATE, CASING, BLOCK);
	public static GTMaterial Iron = new GTMaterial("Iron", 6.0F, 256, 2, new Color(184, 184, 184), METAL_MC);
	public static GTMaterial Invar = new GTMaterial("Invar", 6.0F, 256, 2, new Color(180, 180, 120), METAL_ALL);
	public static GTMaterial Lazurite = new GTMaterial("Lazurite", 1.0F, 0, 1, new Color(100, 120, 255), DUST_ALL);
	public static GTMaterial Lead = new GTMaterial("Lead", 8.0F, 64, 1, new Color(140, 100, 140), METAL_ALL);
	public static GTMaterial Maganlium = new GTMaterial("Magnalium", 6.0F, 256, 2, new Color(200, 190, 255), INGOT,
			PLATE);
	public static GTMaterial Magnesium = new GTMaterial("Magnesium", 1.0F, 0, 1, new Color(255, 200, 200), DUST_ALL);
	public static GTMaterial Manganese = new GTMaterial("Manganese", 1.0F, 0, 1, new Color(250, 250, 250), DUST_ALL);
	public static GTMaterial Marble = new GTMaterial("Marble", 1.0F, 0, 1, new Color(200, 200, 200), DUST_ALL);
	public static GTMaterial Netherrack = new GTMaterial("Netherrack", 1.0F, 0, 1, new Color(200, 0, 0), SMALLDUST);
	public static GTMaterial Nickel = new GTMaterial("Nickel", 6.0F, 64, 2, new Color(200, 200, 250), METAL_ALL);
	public static GTMaterial Obsidian = new GTMaterial("Obsidian", 1.0F, 0, 1, new Color(80, 50, 100), SMALLDUST);
	public static GTMaterial Olivine = new GTMaterial("Olivine", 7.0F, 256, 2, new Color(150, 255, 150), GEM_ALL);
	public static GTMaterial Osmium = new GTMaterial("Osmium", 16.0F, 1280, 4, new Color(50, 50, 255), METAL_ALL);
	public static GTMaterial Phosphor = new GTMaterial("Phosphor", 1.0F, 0, 1, new Color(255, 255, 0), DUST_ALL);
	public static GTMaterial Plastic = new GTMaterial("Plastic", 1.0F, 0, 1, new Color(200, 200, 200), DUST_ALL);
	public static GTMaterial Platinum = new GTMaterial("Platinum", 12.0F, 64, 2, new Color(215, 212, 137), METAL_ALL);
	public static GTMaterial Plutonium = new GTMaterial("Plutonium", 6.0F, 512, 3, new Color(240, 50, 50), SMALLDUST,
			DUST,
			NUGGET, INGOT, PLATE);
	public static GTMaterial Pyrite = new GTMaterial("Pyrite", 1.0F, 0, 1, new Color(150, 120, 40), DUST_ALL);
	public static GTMaterial Pyrope = new GTMaterial("Pyrope", 1.0F, 0, 1, new Color(120, 50, 100), DUST_ALL);
	public static GTMaterial RedRock = new GTMaterial("RedRock", 1.0F, 0, 1, new Color(255, 80, 50), DUST_ALL);
	public static GTMaterial Redstone = new GTMaterial("Redstone", 1.0F, 0, 1, new Color(200, 0, 0), SMALLDUST);
	public static GTMaterial RefinedIron = new GTMaterial("RefinedIron", 6.0F, 384, 2, new Color(185, 215, 217), PLATE);
	public static GTMaterial Ruby = new GTMaterial("Ruby", 7.0F, 256, 2, new Color(255, 100, 100), GEM_ALL);
	public static GTMaterial Saltpeter = new GTMaterial("Saltpeter", 1.0F, 0, 1, new Color(230, 230, 230), DUST_ALL);
	public static GTMaterial Sapphire = new GTMaterial("Sapphire", 7.0F, 256, 2, new Color(100, 100, 200), GEM_ALL);
	public static GTMaterial SapphireGreen = new GTMaterial("GreenSapphire", 7.0F, 256, 2, new Color(100, 200, 130),
			GEM_ALL);
	public static GTMaterial Silicon = new GTMaterial("Silicon", 1.0F, 0, 1, new Color(60, 60, 80), PLATE);
	public static GTMaterial Silver = new GTMaterial("Silver", 10.0F, 64, 2, new Color(220, 220, 255), METAL_IC2);
	public static GTMaterial Sodalite = new GTMaterial("Sodalite", 1.0F, 0, 1, new Color(20, 20, 255), DUST_ALL);
	public static GTMaterial Spessartine = new GTMaterial("Spessartine", 1.0F, 0, 1, new Color(255, 100, 100),
			DUST_ALL);
	public static GTMaterial Sphalerite = new GTMaterial("Sphalerite", 1.0F, 0, 1, new Color(200, 140, 40), DUST_ALL);
	public static GTMaterial Steel = new GTMaterial("Steel", 6.0F, 512, 2, new Color(128, 128, 128), METAL_ALL);
	public static GTMaterial Sulfur = new GTMaterial("Sulfur", 1.0F, 0, 1, new Color(200, 200, 0), DUST_ALL);
	public static GTMaterial Thorium = new GTMaterial("Thorium", 6.0F, 512, 2, new Color(0, 30, 0), SMALLDUST, DUST,
			NUGGET,
			INGOT);
	public static GTMaterial Tin = new GTMaterial("Tin", 1.0F, 0, 1, new Color(220, 220, 220), METAL_IC2);
	public static GTMaterial Titanium = new GTMaterial("Titanium", 8.0F, 2560, 3, new Color(170, 143, 222), METAL_ALL);
	public static GTMaterial Tungsten = new GTMaterial("Tungsten", 8.0F, 5120, 3, new Color(50, 50, 50), METAL_ALL);
	public static GTMaterial TungstenSteel = new GTMaterial("TungstenSteel", 10.0F, 5120, 4, new Color(100, 100, 160),
			METAL_ALL);
	public static GTMaterial Uranium = new GTMaterial("Uranium", 6.0F, 512, 3, new Color(50, 240, 50), DUST_ALL);
	public static GTMaterial Uvarovite = new GTMaterial("Uvarovite", 1.0F, 0, 1, new Color(180, 255, 180), DUST_ALL);
	public static GTMaterial Wood = new GTMaterial("Wood", 1.0F, 0, 1, new Color(100, 50, 0), DUST_ALL);
	public static GTMaterial Zinc = new GTMaterial("Zinc", 1.0F, 0, 1, new Color(250, 240, 240), METAL_ALL);

	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private float speed;
	private int durability;
	private int level;
	private Color color;
	// Expand for needs

	public GTMaterial(String displayName, float speed, int durability, int level, Color color,
			GTMaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.speed = speed;
		this.durability = durability;
		this.level = level;
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
