package gtclassic.material;

public enum GTMaterialFlag {

	// @formatter:off
	SMALLDUST("_dustsmall", 1, false), 
	DUST("_dust", 0, false), 
	GEM("_gem", 2, false), 
	INGOT("_ingot", 3, false),
	HOTINGOT("_hotingot", 3, true), 
	NUGGET("_nugget", 5, false), 
	PLATE("_plate", 6, false),
	SMALLPLATE("_smallplate", 7, false), 
	STICK("_stick", 8, false), 
	GEAR("_gear", 9, false), 
	FOIL("_foil", 10, false),
	WIRE("_finewire", 11, false), 
	BOULE("_boule", 12, false), 
	FLUID("", 13, true), 
	PLASMA("plasma", 13, true),
	PARTICLE("_particle", 15, false), 
	BLOCK("_block", 48, false), 
	CASING("_casing", 50, false),
	COIL("_coil", 52, false);
	// @formatter:on

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
