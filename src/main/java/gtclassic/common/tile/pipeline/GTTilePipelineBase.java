package gtclassic.common.tile.pipeline;

import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTilePipelineBase extends GTTileBaseRecolorableTile implements ITickable {

	public BlockPos targetPos;
	private static final String NBT_TARGETPOS = "targetPos";
	public static final int TICK_RATE = 10;

	public GTTilePipelineBase(int slots) {
		super(slots);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.targetPos != null) {
			nbt.setTag(NBT_TARGETPOS, NBTUtil.createPosTag(targetPos));
		}
		return nbt;
	}

	@Override
	public Block getBlockDrop() {
		return GTBlocks.pipelineItem;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	public abstract boolean isEmpty();

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 126 == 0) {
			this.targetPos = null;
		}
		if (world.getTotalWorldTime() % TICK_RATE == 0) {
			if (this.targetPos == null || this.targetPos == this.pos) {
				return;
			}
			if (!world.isBlockLoaded(this.targetPos)) {
				return;
			}
			if (isEmpty()) {
				return;
			}
			onPipelineTick();
		}
	}

	public abstract void onPipelineTick();
}
