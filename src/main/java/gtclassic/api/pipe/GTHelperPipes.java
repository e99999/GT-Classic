package gtclassic.api.pipe;

public class GTHelperPipes {

	public enum GTPipeModel {
		SMALL("_small", new int[] { 6, 10 }),
		MED("", new int[] { 4, 12 }),
		LARGE("_large", new int[] { 1, 15 });

		String suffix;
		int[] sizes;

		GTPipeModel(String suffix, int[] sizes) {
			this.suffix = suffix;
			this.sizes = sizes;
		}

		public String getPrefix() {
			return suffix.replace("_", "");
		}

		public String getSuffix() {
			return this.suffix.toLowerCase();
		}

		public int[] getSizes() {
			return this.sizes;
		}
	}

	public enum GTPipeFluidCapacity {
		S800(800),
		S1600(1600),
		S3200(3200),
		S6400(6400),
		S12800(12800),
		SMAX1(96000),
		SMAX2(144000),
		SMAX3(192000);

		int size;

		GTPipeFluidCapacity(int size) {
			this.size = size;
		}

		public int getSize() {
			return this.size;
		}
	}

	private static final GTPipeFluidCapacity[] TIER_1 = { GTPipeFluidCapacity.S800, GTPipeFluidCapacity.S1600,
			GTPipeFluidCapacity.S3200 };
	private static final GTPipeFluidCapacity[] TIER_2 = { GTPipeFluidCapacity.S1600, GTPipeFluidCapacity.S3200,
			GTPipeFluidCapacity.S6400 };
	private static final GTPipeFluidCapacity[] TIER_3 = { GTPipeFluidCapacity.S3200, GTPipeFluidCapacity.S6400,
			GTPipeFluidCapacity.S12800 };
	private static final GTPipeFluidCapacity[] TIER_MAX = { GTPipeFluidCapacity.SMAX1, GTPipeFluidCapacity.SMAX2,
			GTPipeFluidCapacity.SMAX3 };

	public static GTPipeFluidCapacity[] getPipeCapacityFromTier(int tier) {
		switch (tier) {
		case 2:
			return TIER_2;
		case 3:
			return TIER_3;
		case Integer.MAX_VALUE:
			return TIER_MAX;
		default:
			return TIER_1;
		}
	}
}
