package gtclassic.common.tile;

import gtclassic.api.tile.GTTileBaseSuperconductorCable;
import gtclassic.common.GTBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class GTTileSuperconductorCables {

	public static class SuperconductorMAX extends GTTileBaseSuperconductorCable {

		public SuperconductorMAX() {
			super(12);
		}

		@Override
		public double getConductorBreakdownEnergy() {
			return 134217729.0D;
		}

		@Override
		public double getInsulationBreakdownEnergy() {
			return 134217729.0D;
		}

		@Override
		public double getInsulationEnergyAbsorption() {
			return 134217729.0D;
		}

		@Override
		public void removeConductor() {
			this.world.setBlockToAir(this.getPos());
			this.world.setBlockState(this.getPos(), Blocks.FIRE.getDefaultState());
		}

		@Override
		public Block getBlockDrop() {
			return GTBlocks.tileSuperconductorCableMAX;
		}
	}

	public static class SuperconductorIV extends GTTileBaseSuperconductorCable {

		public SuperconductorIV() {
			super(6);
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

		@Override
		public Block getBlockDrop() {
			return GTBlocks.tileSuperconductorCableIV;
		}
	}

	public static class SuperconductorHV extends GTTileBaseSuperconductorCable {

		public SuperconductorHV() {
			super(4);
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

		@Override
		public Block getBlockDrop() {
			return GTBlocks.tileSuperconductorCableHV;
		}
	}
}
