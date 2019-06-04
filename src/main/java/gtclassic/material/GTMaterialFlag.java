package gtclassic.material;

public enum GTMaterialFlag {
	DUST("_dust", 0, false),
	GEM("_gem", 2, false),
	INGOT("_ingot", 3, false),
	FLUID("", 13, true),
	GAS("", 13, true),
	BLOCK("_block", 48, false);

	private int mask;
	private String suffix;
	private int id;
	private boolean layered;

	GTMaterialFlag(String suffix, int id, boolean layered) {
		this.mask = 1 << ordinal();
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
