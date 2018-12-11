package gtclassic.tileentity;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.util.EnumFacing;

public class GTTileEntityLightningRod extends TileEntityMachine implements ITickable, IEnergySource, IEmitterTile {

	public GTTileEntityLightningRod() {
		super(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing var2) {
		return true;
	}

	@Override
	public int getOutput() {
		return 8192;
	}

	@Override
	public void drawEnergy(double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getOfferedEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSourceTier() {
		return 4;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
