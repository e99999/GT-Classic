package gtclassic.common.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;

public class GTTileSuperconductorCable extends TileEntityBlock implements IEnergyConductor {

	protected boolean addedToEnergyNet;

	public GTTileSuperconductorCable() {
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (!this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnergyNet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnergyNet = false;
		}
		super.onUnloaded();
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
