package gtclassic.common.tile.datanet;

import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;

public abstract class GTTileBaseOutputNode extends GTTileBaseDataNode implements ITickable {

	/**
	 * This tile does not move anything, it merely provides the location of an
	 * output point to input nodes
	 **/
	public GTTileBaseOutputNode(int slots) {
		super(slots);
	}

	/**
	 * This returns the position for digitizer/input nodes to check for a valid
	 * inventory
	 **/
	public abstract BlockPos inventoryPos();

	/** This returns the side the above inventory should be interacted with **/
	public abstract EnumFacing inventoryFacing();

	public abstract GTDataNet.DataType dataType();

	@Override
	public void update() {
		if (world.getTotalWorldTime() % GTDataNet.RESET_RATE == 0) {
			this.computer = null;
		}
	}

	public IFilter inventoryFilter() {
		return null;
	}

	public FluidStack tankFilter() {
		return null;
	}
}
