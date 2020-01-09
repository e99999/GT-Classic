package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDataNetObject;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileOutputNodeBase extends TileEntityMachine
		implements IGTDebuggableTile, IGTDataNetObject, ITickable {

	public GTTileComputerCube computer;

	/**
	 * This tile does not move anything, it merely provides the location of an
	 * output point to input nodes
	 **/
	public GTTileOutputNodeBase(int slots) {
		super(slots);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
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
	public void getData(Map<String, Boolean> data) {
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found or network is not powered", false);
		}
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % GTDataNet.RESET_RATE == 0) {
			this.computer = null;
		}
	}

	public IFilter inventoryFilter() {
		return null;
	}
}
