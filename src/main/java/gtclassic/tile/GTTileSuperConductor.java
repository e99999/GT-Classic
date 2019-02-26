package gtclassic.tile;

import ic2.core.block.base.tile.TileEntityElectricBlock;

public class GTTileSuperConductor extends TileEntityElectricBlock {

	public GTTileSuperConductor(int tier, int energy) {
		super(tier, energy, energy * 2);
	}

	@Override
	public int getProcessRate() {
		return 512;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public void update() {
		this.setActive(this.getStoredEU() > 0);
		super.update();
	}
}