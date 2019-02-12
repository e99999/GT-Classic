package gtclassic.tile;

import ic2.api.classic.network.adv.IBitLevelOverride;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.block.base.tile.TileEntityTransformer;

public class GTTileDigitalTransformer extends TileEntityTransformer implements IBitLevelOverride {

	public GTTileDigitalTransformer(int high, int low) {
		super(low, high, high);
	}

	@Override
	public double getWrenchDropRate() {
		return 0.7D;
	}

	@Override
	public NetworkField.BitLevel getOverride(int fieldID, String fieldName) {
		return NetworkField.BitLevel.Bit16;
	}

	@Override
	public boolean hasOverride(int fieldID, String fieldName) {
		return fieldID == 3;
	}

}
