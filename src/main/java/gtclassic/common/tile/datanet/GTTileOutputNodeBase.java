package gtclassic.common.tile.datanet;

import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public abstract class GTTileOutputNodeBase extends TileEntityMachine {

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
}
