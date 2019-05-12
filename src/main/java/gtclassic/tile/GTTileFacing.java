package gtclassic.tile;

import java.util.LinkedHashSet;
import java.util.Set;

import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class GTTileFacing extends TileEntityBlock {
	private Set<EnumFacing> validRotations;

	// yes this actually works in java to have method overloading with no arguments
	// and a varargs argument
	// https://stackoverflow.com/questions/48850699/java-function-with-one-varargs-argument-and-function-with-same-name-and-one-arg
	public GTTileFacing() {
		this(EnumFacing.VALUES);
	}

	public GTTileFacing(EnumFacing... validRotations) {
		super();
		setFacing(EnumFacing.NORTH);
		this.validRotations = new LinkedHashSet<>();
		for (EnumFacing rotation : validRotations) {
			this.validRotations.add(rotation);
		}
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
