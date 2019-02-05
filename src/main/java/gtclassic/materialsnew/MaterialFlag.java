package gtclassic.materialsnew;

public enum MaterialFlag {

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

	MaterialFlag() {
		this.mask = 1 << ordinal();
	}

	public int getMask() {
		return mask;
	}
}
