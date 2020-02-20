package gtclassic.api.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public interface IGTCoordinateTile {

	public boolean applyCoordinates(BlockPos pos, int dimensionId);

	public boolean isInterdimensional();

	public BlockPos getAppliedPos();

	public int getAppliedDimId();

	public boolean insertSensorStick(ItemStack card);
}
