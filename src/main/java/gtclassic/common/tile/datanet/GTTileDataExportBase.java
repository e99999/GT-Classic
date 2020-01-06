package gtclassic.common.tile.datanet;

import gtclassic.api.helpers.int3;
import gtclassic.common.GTBlocks;
import gtclassic.common.util.GTDataBlockFilter;
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

public abstract class GTTileDataExportBase extends TileEntityMachine implements ITickable {

	private Processor task = null;
	private AabbUtil.IBlockFilter filter;
	public int blockCount;
	public boolean hasComputer;

	public GTTileDataExportBase(Block nodeBlock) {
		super(0);
		filter = new GTDataBlockFilter(nodeBlock);
		this.blockCount = 0;
		this.hasComputer = false;
	}

	@Override
	public void update() {
		if (this.hasComputer) {
			if (world.getTotalWorldTime() % 126 == 0) {
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
			if (world.getTotalWorldTime() % 128 == 0) {
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
}
