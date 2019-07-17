package gtclassic.tile;

import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class GTTileFacing extends TileEntityBlock {

	private RotationList validRotations;

	// yes this actually works in java to have method overloading with no arguments
	// and a varargs argument
	// https://stackoverflow.com/questions/48850699/java-function-with-one-varargs-argument-and-function-with-same-name-and-one-arg
	public GTTileFacing() {
		this(EnumFacing.VALUES);
	}

	public GTTileFacing(EnumFacing... validRotations) {
		super();
		setFacing(EnumFacing.NORTH);
		this.validRotations = RotationList.ofFacings(validRotations);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && validRotations.contains(facing);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}
}
