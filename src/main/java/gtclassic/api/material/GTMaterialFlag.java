package gtclassic.api.material;

import gtclassic.GTMod;

import java.util.LinkedHashMap;
import java.util.Map;

public class GTMaterialFlag {

	private static final Map<String, GTMaterialFlag> FLAG_MAP = new LinkedHashMap<>();
	public static GTMaterialFlag DUST = new GTMaterialFlag("_dust", 0, false);
	public static GTMaterialFlag INGOT = new GTMaterialFlag("_ingot", 1, false);
	public static GTMaterialFlag INGOTHOT = new GTMaterialFlag("_ingothot", 1, true);
	public static GTMaterialFlag RUBY = new GTMaterialFlag("_gem", 3, false);
	public static GTMaterialFlag SAPPHIRE = new GTMaterialFlag("_gem", 4, false);
	public static GTMaterialFlag FLUID = new GTMaterialFlag("_fluid", -1, true);
	public static GTMaterialFlag GAS = new GTMaterialFlag("_gas", -1, true);
	public static GTMaterialFlag BLOCKMETAL = new GTMaterialFlag("_block", 14, false);
	public static GTMaterialFlag BLOCKGEM = new GTMaterialFlag("_block", 15, false);
	public static GTMaterialFlag NULL = new GTMaterialFlag("", -1, false);
	public static GTMaterialFlag MAGICDYE = new GTMaterialFlag("_magicdye", -1, true);
	private static int LAST_INTERNAL_ID;
	private long mask;
	private String suffix;
	private String texture;
	private int id;
	private boolean layered;
	private String modid;
	private boolean crafttweaker;

	public GTMaterialFlag(String suffix, int id, boolean layered) {
		this(suffix, GTMod.MODID + "_materials", id, layered, GTMod.MODID);
	}

	public GTMaterialFlag(String suffix, String texture, int id, boolean layered, String modid) {
		if (LAST_INTERNAL_ID >= 63) {
			throw new IllegalArgumentException("GregTech Classic material flags overloaded! Limit is 64");
		}
		this.mask = 1L << LAST_INTERNAL_ID++;
		this.suffix = suffix;
		this.texture = texture;
		this.id = id;
		this.layered = layered;
		this.modid = modid;
		this.crafttweaker = false;
		if (!suffix.isEmpty()){
			FLAG_MAP.put(this.getPrefix(), this);
		}
	}

	public GTMaterialFlag setCraftweaker(boolean craftweaker){
		this.crafttweaker = true;
		return this;
	}

	public boolean isCrafttweaker() {
		return crafttweaker;
	}

	public String getTexture() {
		return texture;
	}

	public long getMask() {
		return mask;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getPrefix() {
		return suffix.replace("_", "");
	}

	public int getTextureID() {
		return id;
	}

	public boolean isLayered() {
		return layered;
	}

	public String getModID() {
		return this.modid;
	}

	public static boolean hasFlag(String prefix){
		return FLAG_MAP.containsKey(prefix);
	}

	public static GTMaterialFlag getFlag(String prefix){
		return FLAG_MAP.get(prefix);
	}

	public static int getMapSize(){
		return FLAG_MAP.size();
	}
}
