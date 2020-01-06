package gtclassic.common.tile.pipeline;

import java.util.HashSet;

import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import ic2.core.IC2;
import ic2.core.block.base.util.info.misc.IWrench;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IClickable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

public abstract class GTTilePipelineBase extends GTTileBaseRecolorableTile implements ITickable, IClickable {

	public HashSet<BlockPos> outputNodes = new HashSet<>();
	// private static final String NBT_TARGETPOS = "targetPos";
	public static final int TICK_RATE = 10;

	public GTTilePipelineBase(int slots) {
		super(slots);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		// this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		// if (this.targetPos != null) {
		// nbt.setTag(NBT_TARGETPOS, NBTUtil.createPosTag(targetPos));
		// }
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

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		if (player.isSneaking() && player.getHeldItemMainhand().getItem() instanceof IWrench) {
			this.setActive(!this.getActive());
			if (IC2.platform.isSimulating()) {
				IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			}
			return false;
		}
		return true;
	}

	public abstract boolean isEmpty();

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 126 == 0) {
			this.outputNodes.clear();
		}
		if (world.getTotalWorldTime() % TICK_RATE == 0) {
			if (this.outputNodes.isEmpty()) {
				return;
			}
			tryImport();
			if (isEmpty()) {
				return;
			}
			for (BlockPos nodePos : outputNodes) {
				if (!world.isBlockLoaded(nodePos) || nodePos == this.pos) {
					continue;
				}
				if (onPipelineTick(nodePos)) {
					break;
				}
			}
		}
	}

	public void tryImport() {
		if (this.getActive()) {
			for (EnumFacing side : EnumFacing.VALUES) {
				if (!world.isBlockLoaded(this.pos.offset(side))
						|| world.getTileEntity(pos.offset(side)) instanceof GTTilePipelineBase) {
					continue;
				}
				if (onPipelineImport(side)) {
					break;
				}
			}
		}
	}

	public abstract boolean onPipelineImport(EnumFacing side);

	public abstract boolean onPipelineTick(BlockPos nodePos);

	@Override
	public boolean hasRightClick() {
		return false;
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand var2, EnumFacing var3, Side var4) {
		return false;
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public void onLeftClick(EntityPlayer var1, Side var2) {
	}
}
