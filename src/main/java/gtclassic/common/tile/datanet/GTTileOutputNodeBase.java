package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public abstract class GTTileOutputNodeBase extends TileEntityMachine implements IGTDebuggableTile {

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

	public abstract GTDataNet.DataType dataType();

	@Override
	public void getData(Map<String, Boolean> data) {
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found", false);
		}
	}
}
