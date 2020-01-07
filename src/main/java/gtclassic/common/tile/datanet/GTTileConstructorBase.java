package gtclassic.common.tile.datanet;

import gtclassic.api.helpers.int3;
import gtclassic.common.GTBlocks;
import gtclassic.common.util.datanet.GTBlockFilterData;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.helpers.AabbUtil;
import ic2.core.util.helpers.AabbUtil.BoundingBox;
import ic2.core.util.helpers.AabbUtil.Processor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileConstructorBase extends TileEntityMachine implements ITickable {

	private Processor task = null;
	private AabbUtil.IBlockFilter filter;
	public int blockCount;
	public boolean hasComputer;

	/**
	 * This tile does not move anything, it merely provides the location of an
	 * output point to input nodes
	 **/
	public GTTileConstructorBase(int slots, Block nodeBlock) {
		super(slots);
		filter = new GTBlockFilterData(nodeBlock);
		this.blockCount = 0;
		this.hasComputer = false;
	}

	@Override
	public void update() {
		if (this.hasComputer) {
			if (world.getTotalWorldTime() % GTDataNet.RESET_RATE == 0) {
				this.hasComputer = false;
			}
			if (task != null && world.isAreaLoaded(pos, 16)) {
				task.update();
				if (!task.isFinished()) {
					return;
				}
				this.blockCount = 0;
				for (BlockPos pPos : task.getResults()) {
					if (world.getBlockState(pPos) != GTBlocks.dataCable.getDefaultState()) {
						this.blockCount++;
					}
					if (this.blockCount > 256) {
						break;
					}
					TileEntity worldTile = world.getTileEntity(pPos);
					handleNodes(worldTile);
				}
			}
			if (world.getTotalWorldTime() % GTDataNet.SEARCH_RATE == 0) {
				if (!world.isAreaLoaded(pos, 16))
					return;
				task = AabbUtil.createBatchTask(world, new BoundingBox(this.pos, 256), this.pos, RotationList.ALL, filter, 64, false, false, false);
				task.update();
			}
		}
	}

	public abstract void handleNodes(TileEntity tile);

	public BlockPos getExportTilePos() {
		int3 dir = new int3(getPos(), getFacing());
		return dir.forward(1).asBlockPos();
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
