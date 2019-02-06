package gtclassic.materialsnew;

public enum GTMaterialFlag {

	SMALLDUST("dustSmall", "_dustsmall", 1),
	DUST("dust", "_dust", 0),
	GEM("gem", "_gem", 2),
	INGOT("ingot", "_ingot", 3),
	NUGGET("nugget", "_nugget", 4),
	PLATE("plate", "_plate", 5),
	STICK("stick", "_stick", 6),
	BLOCK("block", "_block", 62),
	CASING("casing", "_casing", 61);

	private int mask;
	private String prefix;
	private String suffix;
	private int id;

	GTMaterialFlag(String prefix, String suffix, int id) {
		this.mask = 1 << ordinal();
		this.prefix = prefix;
		this.suffix = suffix;
		this.id = id;
	}

	public int getMask() {
		return mask;
	}

	public String getPrefix() {
		return prefix;

	}

	public String getSuffix() {
		return suffix;

	}

	public int getTextureID() {
		return id;

	}
}
