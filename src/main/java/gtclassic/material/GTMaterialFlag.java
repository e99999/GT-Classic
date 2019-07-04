package gtclassic.material;

public class GTMaterialFlag {

	public static GTMaterialFlag
	DUST = new GTMaterialFlag("_dust", 0, false),
	INGOT = new GTMaterialFlag("_ingot", 1, false),
	RUBY = new GTMaterialFlag("_gem", 2, false),
	SAPPHIRE = new GTMaterialFlag("_gem", 3, false),
	FLUID = new GTMaterialFlag("", 13, true),
	GAS = new GTMaterialFlag("", 13, true),
	BLOCK = new GTMaterialFlag("_block", 16, false);


	private static int LAST_INTERNAL_ID;
	private int mask;
	private String suffix;
	private int id;
	private boolean layered;

	public GTMaterialFlag(String suffix, int id, boolean layered) {
		this.mask = 1 << LAST_INTERNAL_ID++;
		this.suffix = suffix;
		this.id = id;
		this.layered = layered;
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
