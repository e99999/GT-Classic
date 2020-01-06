package gtclassic.common.tile.pipeline;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import gtclassic.api.helpers.int3;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import gtclassic.common.util.GTIBlockFilter;
import ic2.core.RotationList;
import ic2.core.util.helpers.AabbUtil;
import ic2.core.util.helpers.AabbUtil.BoundingBox;
import ic2.core.util.helpers.AabbUtil.Processor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class GTTilePipelineItemEnd extends GTTileBaseRecolorableTile implements ITickable, IGTDebuggableTile {

	private Processor task = null;
	private AabbUtil.IBlockFilter filter = new GTIBlockFilter(GTBlocks.pipelineItem);
	private int blockCount;

	public GTTilePipelineItemEnd() {
		super(0);
		this.blockCount = 0;
	}

	@Override
	public void update() {
		if (task != null && world.isAreaLoaded(pos, 16)) {
			task.update();
			if (!task.isFinished()) {
				return;
			}
			this.blockCount = task.getResults().size();
			int i = 0;
			for (BlockPos pPos : task.getResults()) {
				i++;
				if (i > 256) {
					break;
				}
				TileEntity worldTile = world.getTileEntity(pPos);
				if (worldTile instanceof GTTilePipelineItem && ((GTTilePipelineItem) worldTile).outputNodes != null) {
					((GTTilePipelineItem) worldTile).outputNodes.add(this.getExportTilePos());
				}
			}
		}
		if (world.getTotalWorldTime() % 128 == 0) {
			if (!world.isAreaLoaded(pos, 16))
				return;
			task = AabbUtil.createBatchTask(world, new BoundingBox(this.pos, 256), this.pos, RotationList.ALL, filter, 64, false, false, false);
			task.update();
		}
	}

	public BlockPos getExportTilePos() {
		int3 dir = new int3(getPos(), getFacing());
		return dir.forward(1).asBlockPos();
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing();
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Connected Pipes: " + this.blockCount, false);
	}

	@Override
	public List<ItemStack> getInventoryDrops() {
		return Collections.emptyList();
	}

	@Override
	public Block getBlockDrop() {
		return GTBlocks.tilePipelineItemEnd;
	}
}
