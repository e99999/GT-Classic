package gtclassic.api.tile;

import net.minecraft.nbt.NBTTagCompound;

public abstract class GTTileBaseSuperconductorCable extends GTTileBaseCable {

	int size;

	public GTTileBaseSuperconductorCable(int size) {
		super();
		this.size = size;
	}

	@Override
	public double getConductionLoss() {
		return 0.001D;
	}

	@Override
	public NBTTagCompound getBlockNBT(NBTTagCompound nbt) {
		if (this.isColored()) {
			nbt.setInteger("color", this.color);
		}
		return nbt;
	}

	@Override
	public int getSize() {
		return size;
	}
}
