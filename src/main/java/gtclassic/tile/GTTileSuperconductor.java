package gtclassic.tile;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.util.EnumFacing;

public class GTTileSuperconductor extends TileEntityBlock implements IEnergyConductor{
	
	public GTTileSuperconductor() {
		
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter var1, EnumFacing var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getConductionLoss() {
		 return 0.001D;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 134217728.0D;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 134217728.0D;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 134217728.0D;
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
