package gtclassic.material;

import gtclassic.GTMod;

public class GTMaterialFlag {

	public static GTMaterialFlag DUST = new GTMaterialFlag("_dust", 0, false),
			INGOT = new GTMaterialFlag("_ingot", 1, false), 
			RUBY = new GTMaterialFlag("_gem", 2, false),
			SAPPHIRE = new GTMaterialFlag("_gem", 3, false), 
			FLUID = new GTMaterialFlag("", 13, true),
			GAS = new GTMaterialFlag("", 13, true), 
			BLOCKMETAL = new GTMaterialFlag("_block", 16, false),
			BLOCKGEM = new GTMaterialFlag("_block", 17, false);
	private static int LAST_INTERNAL_ID;
	private int mask;
	private String suffix;
	private String texture;
	private int id;
	private boolean layered;

	public GTMaterialFlag(String suffix, int id, boolean layered) {
		this.mask = 1 << LAST_INTERNAL_ID++;
		this.suffix = suffix;
		this.texture = GTMod.MODID + "_materials";
		this.id = id;
		this.layered = layered;
	}

	public GTMaterialFlag(String suffix, String texture, int id, boolean layered) {
		this.mask = 1 << LAST_INTERNAL_ID++;
		this.suffix = suffix;
		this.texture = texture;
		this.id = id;
		this.layered = layered;
	}

	public String getTexture() {
		return texture;
	}

	public int getMask() {
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
}
