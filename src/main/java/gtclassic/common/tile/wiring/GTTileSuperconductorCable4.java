package gtclassic.common.tile.wiring;

import net.minecraft.init.Blocks;

public class GTTileSuperconductorCable4 extends GTTileSuperconductorCable {

	public GTTileSuperconductorCable4() {
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 513.0D;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 513.0D;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 513.0D;
	}

	@Override
	public void removeConductor() {
		this.world.setBlockToAir(this.getPos());
		this.world.setBlockState(this.getPos(), Blocks.FIRE.getDefaultState());
	}
}
