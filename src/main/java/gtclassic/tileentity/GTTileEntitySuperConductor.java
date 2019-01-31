package gtclassic.tileentity;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.util.EnumFacing;

public class GTTileEntitySuperConductor extends TileEntityBlock implements IEnergyConductor {

	double loss;
	double energy;

	public GTTileEntitySuperConductor(double loss, double energy) {
		this.loss = loss;
		this.energy = energy;

	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter var1, EnumFacing var2) {
		return true;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing var2) {
		return true;
	}

	@Override
	public double getConductionLoss() {
		return this.loss;
		// return 1.5D;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return this.energy;
		// return 32769.0D;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return this.energy;
		// return 32769.0D;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return this.energy;
		// return 32769.0D;
	}

	@Override
	public void removeConductor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeInsulation() {
		// TODO Auto-generated method stub

	}

}
