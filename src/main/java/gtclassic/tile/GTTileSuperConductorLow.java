package gtclassic.tile;

import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.core.block.base.tile.TileEntityTransformer;
import net.minecraft.util.EnumFacing;

public class GTTileSuperConductorLow extends TileEntityTransformer {

	public GTTileSuperConductorLow() {
		super((int) EnergyNet.instance.getPowerFromTier(6), (int) EnergyNet.instance.getPowerFromTier(6), (int) EnergyNet.instance.getPowerFromTier(6));
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return this.getFacing() == side;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return this.getFacing() != side;
	}

	@Override
	public void handleRedstone() {
		// removing this from the base class
	}

	// @Override
	// public NetworkField.BitLevel getOverride(int fieldID, String fieldName) {
	// return BitLevel.Bit32;
	// }

	// @Override
	// public boolean hasOverride(int fieldID, String fieldName) {
	// return fieldID == 3;
	// }

	@Override
	public void update() {
		this.setActive(this.getStoredEU() > 0);
		super.update();
	}
}