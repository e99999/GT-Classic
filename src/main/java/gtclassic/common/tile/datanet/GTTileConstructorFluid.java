package gtclassic.common.tile.datanet;

import gtclassic.common.util.datanet.GTDataNet.DataType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class GTTileConstructorFluid extends GTTileBaseOutputNode {

	/** Literally just a pointer on the network to where an output pos is **/
	public GTTileConstructorFluid() {
		super(0);
	}

	@Override
	public DataType dataType() {
		return DataType.FLUID;
	}

	@Override
	public BlockPos inventoryPos() {
		return this.getPos().offset(this.getFacing());
	}

	@Override
	public EnumFacing inventoryFacing() {
		return this.getFacing().getOpposite();
	}
}
