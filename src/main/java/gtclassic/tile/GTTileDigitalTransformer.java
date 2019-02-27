package gtclassic.tile;

import ic2.api.classic.network.adv.IBitLevelOverride;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.network.adv.NetworkField.BitLevel;
import ic2.api.energy.EnergyNet;
import ic2.core.block.base.tile.TileEntityTransformer;

public class GTTileDigitalTransformer extends TileEntityTransformer implements IBitLevelOverride {

	public GTTileDigitalTransformer() {
		super((int) EnergyNet.instance.getPowerFromTier(6), 
				(int) EnergyNet.instance.getPowerFromTier(7),
				(int) EnergyNet.instance.getPowerFromTier(8));
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public NetworkField.BitLevel getOverride(int fieldID, String fieldName) {
		return BitLevel.Bit32;
	}

	@Override
	public boolean hasOverride(int fieldID, String fieldName) {
		return fieldID == 3;
	}

	@Override
	public void update() {
		this.setActive(this.getStoredEU() > 0);
		super.update();
	}

}
