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

	static MaterialFlag[] DUST_ALL = {
			SMALLDUST, DUST
	};

	/** Master Material Map **/
	private static HashMap<String, Material> generatedMap = new HashMap<>();

	/** Material Instances **/
	public static Material Almandine = new Material("Almandine", 1.0F, 0, new Color(255, 0, 0), DUST_ALL);
	public static Material Aluminium = new Material("Aluminium", 10.0F, 128, new Color(128, 200, 240), METAL_ALL);
	public static Material Andradite = new Material("Andradite", 1.0F, 0, new Color(150, 120, 0), DUST_ALL);
	public static Material Ashes = new Material("Ashes", 1.0F, 0, new Color(150, 150, 150), DUST_ALL);

	public static Material Basalt = new Material("Basalt", 1.0F, 0, new Color(30, 20, 20), DUST_ALL);
	public static Material Bauxite = new Material("Bauxite", 1.0F, 0, new Color(200, 100, 0), DUST_ALL);
	public static Material Brass = new Material("Brass", 7.0F, 96, new Color(255, 180, 0), METAL_ALL);
	public static Material Bronze = new Material("Bronze", 6.0F, 192, new Color(255, 180, 0), METAL_IC2);

	public static Material Calcite = new Material("Calcite", 1.0F, 0, new Color(250, 230, 220), DUST_ALL);
	public static Material Charocoal = new Material("Charcoal", 1.0F, 0, new Color(100, 70, 70), SMALLDUST);
	public static Material Chrome = new Material("Chrome", 11.0F, 256, new Color(245, 206, 227), METAL_ALL);
	public static Material Cinnabar = new Material("Cinnabar", 1.0F, 0, new Color(150, 0, 0), DUST_ALL);
	public static Material Clay = new Material("Clay", 1.0F, 0, new Color(200, 200, 220), SMALLDUST);
	public static Material Coal = new Material("Coal", 1.0F, 0, new Color(70, 70, 70), SMALLDUST);
	public static Material Copper = new Material("Copper", 1.0F, 0, new Color(255, 100, 0), METAL_IC2);

	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private float speed;
	private int durability;
	private Color rgb;
	// Expand for needs

	public Material(String displayName, float speed, int durability, Color rgb, MaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.speed = speed;
		this.durability = durability;
		this.rgb = rgb;
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

	public Color getRGB() {
		return rgb;
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
