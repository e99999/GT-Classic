package gtclassic.materialsnew;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

public class Material {

	static MaterialFlag SMALLDUST = MaterialFlag.SMALLDUST;
	static MaterialFlag DUST = MaterialFlag.DUST;
	static MaterialFlag GEM = MaterialFlag.GEM;
	static MaterialFlag INGOT = MaterialFlag.INGOT;
	static MaterialFlag NUGGET = MaterialFlag.NUGGET;
	static MaterialFlag PLATE = MaterialFlag.PLATE;
	static MaterialFlag STICK = MaterialFlag.STICK;
	static MaterialFlag BLOCK = MaterialFlag.BLOCK;
	static MaterialFlag CASING = MaterialFlag.CASING;

	static MaterialFlag[] GEM_ALL = {
			SMALLDUST, DUST, GEM, BLOCK
	};

	static MaterialFlag[] METAL_ALL = {
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, BLOCK, CASING
	};

	static MaterialFlag[] METAL_IC2 = {
			SMALLDUST, NUGGET, PLATE, STICK, CASING
	};

	static MaterialFlag[] METAL_MC = {
			SMALLDUST, PLATE, STICK, CASING
	};

	static MaterialFlag[] DUST_ALL = {
			SMALLDUST, DUST
	};

	/** Master Material Map **/
	private static HashMap<String, Material> generatedMap = new HashMap<>();

	/** Material Instances **/
	public static Material Almandine = new Material("Almandine", 1.0F, 0, 1, new Color(255, 0, 0), DUST_ALL);
	public static Material Aluminium = new Material("Aluminium", 10.0F, 128, 2, new Color(128, 200, 240), METAL_ALL);
	public static Material Andradite = new Material("Andradite", 1.0F, 0, 1, new Color(150, 120, 0), DUST_ALL);
	public static Material Ashes = new Material("Ashes", 1.0F, 0, 1, new Color(150, 150, 150), DUST_ALL);
	public static Material Basalt = new Material("Basalt", 1.0F, 0, 1, new Color(30, 20, 20), DUST_ALL);
	public static Material Bauxite = new Material("Bauxite", 1.0F, 0, 1, new Color(200, 100, 0), DUST_ALL);
	public static Material Brass = new Material("Brass", 7.0F, 96, 1, new Color(255, 180, 0), METAL_ALL);
	public static Material Bronze = new Material("Bronze", 6.0F, 192, 2, new Color(255, 180, 0), METAL_IC2);
	public static Material Calcite = new Material("Calcite", 1.0F, 0, 1, new Color(250, 230, 220), DUST_ALL);
	public static Material Charcoal = new Material("Charcoal", 1.0F, 0, 1, new Color(100, 70, 70), SMALLDUST);
	public static Material Chrome = new Material("Chrome", 11.0F, 256, 3, new Color(245, 206, 227), METAL_ALL);
	public static Material Cinnabar = new Material("Cinnabar", 1.0F, 0, 1, new Color(150, 0, 0), DUST_ALL);
	public static Material Clay = new Material("Clay", 1.0F, 0, 1, new Color(200, 200, 220), SMALLDUST);
	public static Material Coal = new Material("Coal", 1.0F, 0, 1, new Color(70, 70, 70), SMALLDUST);
	public static Material Copper = new Material("Copper", 1.0F, 0, 1, new Color(255, 100, 0), METAL_IC2);
	public static Material DarkAshes = new Material("DarkAshes", 1.0F, 0, 1, new Color(50, 50, 50), DUST_ALL);
	public static Material Diamond = new Material("Diamond", 8.0F, 1280, 3, new Color(100, 240, 245), DUST_ALL);
	public static Material Electrum = new Material("Electrum", 12.0F, 64, 2, new Color(255, 255, 100), METAL_ALL);
	public static Material Emerald = new Material("Emerald", 7.0F, 256, 3, new Color(80, 255, 80), DUST_ALL);
	public static Material EnderEye = new Material("Endereye", 1.0F, 0, 1, new Color(160, 250, 230), DUST_ALL);
	public static Material EnderPearl = new Material("Enderpearl", 1.0F, 0, 1, new Color(108, 220, 200), DUST_ALL);
	public static Material Endstone = new Material("Endstone", 1.0F, 0, 1, new Color(255, 255, 255), DUST_ALL);
	public static Material Flint = new Material("Flint", 1.0F, 0, 1, new Color(0, 32, 64), DUST_ALL);
	public static Material Galena = new Material("Galena", 1.0F, 0, 1, new Color(100, 60, 100), DUST_ALL);
	public static Material GalvenizedSteel = new Material("GalvenizedSteel", 8.0F, 512, 3, new Color(250, 240, 240),
			METAL_ALL);
	public static Material GarnetRed = new Material("RedGarnet", 7.0F, 128, 2, new Color(200, 80, 80), SMALLDUST, DUST,
			GEM);
	public static Material GarnetYellow = new Material("YellowGarnet", 7.0F, 128, 2, new Color(200, 200, 80), SMALLDUST,
			DUST,
			GEM);
	public static Material Glowstone = new Material("Glowstone", 1.0F, 0, 1, new Color(255, 255, 0), SMALLDUST);
	public static Material Gold = new Material("Gold", 12.0F, 64, 2, new Color(255, 255, 30), METAL_MC);
	public static Material Grossular = new Material("Grossular", 1.0F, 0, 1, new Color(200, 100, 0), DUST_ALL);
	public static Material Gunpowder = new Material("Gunpowder", 1.0F, 0, 1, new Color(128, 128, 128), SMALLDUST);
	public static Material Iridium = new Material("Iridium", 6.0F, 5120, 4, new Color(255, 255, 255), NUGGET, INGOT,
			STICK, PLATE, CASING, BLOCK);
	public static Material Iron = new Material("Iron", 6.0F, 256, 2, new Color(184, 184, 184), METAL_MC);
	public static Material Invar = new Material("Invar", 6.0F, 256, 2, new Color(180, 180, 120), METAL_ALL);
	public static Material Lazurite = new Material("Lazurite", 1.0F, 0, 1, new Color(100, 120, 255), DUST_ALL);
	public static Material Lead = new Material("Lead", 8.0F, 64, 1, new Color(140, 100, 140), METAL_ALL);
	public static Material Maganlium = new Material("Magnalium", 6.0F, 256, 2, new Color(200, 190, 255), INGOT, PLATE);
	public static Material Magnesium = new Material("Magnesium", 1.0F, 0, 1, new Color(255, 200, 200), DUST_ALL);
	public static Material Manganese = new Material("Manganese", 1.0F, 0, 1, new Color(250, 250, 250), DUST_ALL);
	public static Material Marble = new Material("Marble", 1.0F, 0, 1, new Color(200, 200, 200), DUST_ALL);
	public static Material Netherrack = new Material("Netherrack", 1.0F, 0, 1, new Color(200, 0, 0), SMALLDUST);
	public static Material Nickel = new Material("Nickel", 6.0F, 64, 2, new Color(200, 200, 250), METAL_ALL);
	public static Material Obsidian = new Material("Obsidian", 1.0F, 0, 1, new Color(80, 50, 100), SMALLDUST);
	public static Material Olivine = new Material("Olivine", 7.0F, 256, 2, new Color(150, 255, 150), GEM_ALL);
	public static Material Osmium = new Material("Osmium", 16.0F, 1280, 4, new Color(50, 50, 255), METAL_ALL);
	public static Material Phosphor = new Material("Phosphor", 1.0F, 0, 1, new Color(255, 255, 0), DUST_ALL);
	public static Material Plastic = new Material("Plastic", 1.0F, 0, 1, new Color(200, 200, 200), DUST_ALL);
	public static Material Platinum = new Material("Platinum", 12.0F, 64, 2, new Color(215, 212, 137), METAL_ALL);
	public static Material Plutonium = new Material("Plutonium", 6.0F, 512, 3, new Color(240, 50, 50), SMALLDUST, DUST,
			NUGGET, INGOT, PLATE);
	public static Material Pyrite = new Material("Pyrite", 1.0F, 0, 1, new Color(150, 120, 40), DUST_ALL);
	public static Material Pyrope = new Material("Pyrope", 1.0F, 0, 1, new Color(120, 50, 100), DUST_ALL);
	public static Material RedRock = new Material("RedRock", 1.0F, 0, 1, new Color(255, 80, 50), DUST_ALL);
	public static Material Redstone = new Material("Redstone", 1.0F, 0, 1, new Color(200, 0, 0), SMALLDUST);
	public static Material RefinedIron = new Material("RefinedIron", 6.0F, 384, 2, new Color(185, 215, 217), PLATE);
	public static Material Ruby = new Material("Ruby", 7.0F, 256, 2, new Color(255, 100, 100), GEM_ALL);
	public static Material Saltpeter = new Material("Saltpeter", 1.0F, 0, 1, new Color(230, 230, 230), DUST_ALL);
	public static Material Sapphire = new Material("Sapphire", 7.0F, 256, 2, new Color(100, 100, 200), GEM_ALL);
	public static Material SapphireGreen = new Material("GreenSapphire", 7.0F, 256, 2, new Color(100, 200, 130),
			GEM_ALL);
	public static Material Silicon = new Material("Silicon", 1.0F, 0, 1, new Color(60, 60, 80), PLATE);
	public static Material Silver = new Material("Silver", 10.0F, 64, 2, new Color(220, 220, 255), METAL_IC2);
	public static Material Sodalite = new Material("Sodalite", 1.0F, 0, 1, new Color(20, 20, 255), DUST_ALL);
	public static Material Spessartine = new Material("Spessartine", 1.0F, 0, 1, new Color(255, 100, 100), DUST_ALL);
	public static Material Sphalerite = new Material("Sphalerite", 1.0F, 0, 1, new Color(200, 140, 40), DUST_ALL);
	public static Material Steel = new Material("Steel", 6.0F, 512, 2, new Color(128, 128, 128), METAL_ALL);
	public static Material Sulfur = new Material("Sulfur", 1.0F, 0, 1, new Color(200, 200, 0), DUST_ALL);
	public static Material Thorium = new Material("Thorium", 6.0F, 512, 2, new Color(0, 30, 0), SMALLDUST, DUST, NUGGET,
			INGOT);
	public static Material Tin = new Material("Tin", 1.0F, 0, 1, new Color(220, 220, 220), METAL_IC2);
	public static Material Titanium = new Material("Titanium", 8.0F, 2560, 3, new Color(170, 143, 222), METAL_ALL);
	public static Material Tungsten = new Material("Tungsten", 8.0F, 5120, 3, new Color(50, 50, 50), METAL_ALL);
	public static Material TungstenSteel = new Material("TungstenSteel", 10.0F, 5120, 4, new Color(100, 100, 160),
			METAL_ALL);
	public static Material Uranium = new Material("Uranium", 6.0F, 512, 3, new Color(50, 240, 50), DUST_ALL);
	public static Material Uvarovite = new Material("Uvarovite", 1.0F, 0, 1, new Color(180, 255, 180), DUST_ALL);
	public static Material Wood = new Material("Wood", 1.0F, 0, 1, new Color(100, 50, 0), DUST_ALL);
	public static Material Zinc = new Material("Zinc", 1.0F, 0, 1, new Color(250, 240, 240), METAL_ALL);

	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private float speed;
	private int durability;
	private int level;
	private Color color;
	// Expand for needs

	public Material(String displayName, float speed, int durability, int level, Color color, MaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.speed = speed;
		this.durability = durability;
		this.level = level;
		this.color = color;
		for (MaterialFlag flag : flags) {
			mask |= flag.getMask();
		}
		generatedMap.put(name, this);
	}

	public boolean hasFlag(MaterialFlag flag) {
		return (mask & flag.getMask()) != 0;
	}

	/** Getters/Setters **/

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
	public Material get(String name) {
		// This is where you could do error handling if you want
		return generatedMap.get(name);
	}

	/** Helper for looping **/
	public Collection<Material> values() {
		return generatedMap.values();
	}
}
