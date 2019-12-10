package gtclassic.api.pipe;

import gtclassic.api.pipe.GTPipeTypes.GTPipeFluidCapacity;

public class GTPipeHandler {

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
