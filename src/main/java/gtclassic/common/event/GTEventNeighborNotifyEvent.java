package gtclassic.common.event;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventNeighborNotifyEvent {

	@SubscribeEvent
	public void onNeighborNotified(BlockEvent.NeighborNotifyEvent event) {
		World world = event.getWorld();
		for (EnumFacing side : event.getNotifiedSides()) {
			BlockPos sidePos = event.getPos().offset(side);
			IBlockState sideState = world.getBlockState(sidePos);
			Block block = sideState.getBlock();
			if (block.isLeaves(sideState, world, sidePos))
				world.scheduleUpdate(sidePos, block, 8 + world.rand.nextInt(16));
		}
	}
}
