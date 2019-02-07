package gtclassic.materialsnew;

public enum GTMaterialFlag {

	SMALLDUST("_dustsmall", 1),
	DUST("_dust", 0),
	GEM("_gem", 2),
	INGOT( "_ingot", 3),
	NUGGET("_nugget", 4),
	PLATE("_plate", 5),
	STICK("_stick", 6),
	BLOCK("_block", 62),
	CASING("_casing", 61);

	private int mask;
	private String suffix;
	private int id;

	GTMaterialFlag(String suffix, int id) {
		this.mask = 1 << ordinal();

		this.suffix = suffix;
		this.id = id;
	}

	public int getMask() {
		return mask;
	}

	public String getSuffix() {
		return suffix;

	}

	public int getTextureID() {
		return id;

	}
}
