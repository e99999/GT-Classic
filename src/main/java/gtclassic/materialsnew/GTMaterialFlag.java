package gtclassic.materialsnew;

public enum GTMaterialFlag {

	SMALLDUST,
	DUST,
	GEM,
	INGOT,
	NUGGET,
	PLATE,
	STICK,
	BLOCK,
	CASING;

	private int mask;

	GTMaterialFlag() {
		this.mask = 1 << ordinal();
	}

	public int getMask() {
		return mask;
	}
}
