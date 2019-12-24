package gtclassic.common.tile.wiring;

import net.minecraft.init.Blocks;

public class GTTileSuperconductorCable2 extends GTTileSuperconductorCable {

	public GTTileSuperconductorCable2() {
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 32769.0D;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 32769.0D;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 32769.0D;
	}

	@Override
	public void removeConductor() {
		this.world.setBlockToAir(this.getPos());
		this.world.setBlockState(this.getPos(), Blocks.FIRE.getDefaultState());
	}
}
