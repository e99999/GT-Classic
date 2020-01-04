package gtclassic.common.tile.pipeline;

import java.util.List;

import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IClickable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

public abstract class GTTilePipelineBase extends GTTileBaseRecolorableTile implements ITickable, IClickable {

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
			tryImport();
			if (!world.isBlockLoaded(this.targetPos)) {
				return;
			}
			if (isEmpty()) {
				return;
			}
			onPipelineTick();
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

	public abstract void onPipelineTick();

	@Override
	public boolean hasRightClick() {
		return true;
	}

	public abstract ItemStack getUpgradeStack();

	@Override
	public List<ItemStack> getInventoryDrops() {
		List<ItemStack> drops = super.getInventoryDrops();
		if (this.getActive()) {
			drops.add(this.getUpgradeStack());
		}
		return drops;
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand var2, EnumFacing var3, Side var4) {
		if (player.isSneaking()) {
			this.setActive(!this.getActive());
			if (IC2.platform.isSimulating()) {
				IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			}
			return true;
		}
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
