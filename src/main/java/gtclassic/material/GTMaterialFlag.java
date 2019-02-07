package gtclassic.material;

public enum GTMaterialFlag {

	SMALLDUST("_dustsmall", 1, false),
	DUST("_dust", 0, false),
	GEM("_gem", 2, false),
	INGOT( "_ingot", 3, false),
	NUGGET("_nugget", 4, false),
	PLATE("_plate", 5, false),
	STICK("_stick", 6, false),
	CHEMICAL("_tube", 13 , true), 
	PLASMA("_plasma_tube", 13, true),
	PARTICLE("_particle", 15, false),
	BLOCK("_block", 62, false),
	CASING("_casing", 61, false);

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

	public int getTextureID() {
		return id;

	}
	
	public boolean isLayered() {
		return layered;
	}
}
