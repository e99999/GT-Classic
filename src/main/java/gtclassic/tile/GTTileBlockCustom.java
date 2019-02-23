package gtclassic.tile;

import gtclassic.block.GTBlockBattery;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.nbt.NBTTagCompound;

public class GTTileBlockCustom extends TileEntityBlock {

	/*
	 * This class is extremely simple and designed todo one thing only. Store the
	 * integer from a GT ItemBlock instance whenever the block is placed.
	 */

	private int data;

	public GTTileBlockCustom(int data) {
		this.data = data;
		this.addNetworkFields("data");
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.data = nbt.getInteger("data");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("data", this.data);
		return nbt;
	}

	@Override
	public void onLoaded() {
		updateActive();
		super.onLoaded();
	}

	public int getData() {
		return this.data;
	}

	public void updateActive() {
		if (this.data > 1000 && this.getBlockType() instanceof GTBlockBattery) {
			this.setActive(true);
		}
	}

}
