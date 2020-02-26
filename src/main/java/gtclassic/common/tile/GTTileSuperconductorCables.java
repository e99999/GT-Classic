package gtclassic.common.tile;

import gtclassic.api.tile.GTTileBaseSuperconductorCable;
import net.minecraft.init.Blocks;

public class GTTileSuperconductorCables {

	public static class SuperconductorMAX extends GTTileBaseSuperconductorCable {

		public SuperconductorMAX() {
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
			this.world.setBlockToAir(this.getPos());
			this.world.setBlockState(this.getPos(), Blocks.FIRE.getDefaultState());
		}
	}

	public static class SuperconductorIV extends GTTileBaseSuperconductorCable {

		public SuperconductorIV() {
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

	public static class SuperconductorHV extends GTTileBaseSuperconductorCable {

		public SuperconductorHV() {
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
}
