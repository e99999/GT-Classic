package gtclassic.common.tile;

import java.util.Map;

import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IClickable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTTileRedstoneTransmitter extends TileEntityMachine
		implements IGTCoordinateTile, ITickable, IGTDebuggableTile, IClickable {

	private BlockPos targetPos;
	private static final String NBT_TARGETPOS = "targetPos";
	private int redstoneLevel = -1;
	private boolean shouldUpdate;

	public GTTileRedstoneTransmitter() {
		super(1);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public void onBlockBreak() {
		if (this.targetPos != null && world.isBlockLoaded(this.targetPos)) {
			TileEntity targetTile = world.getTileEntity(this.targetPos);
			if (targetTile instanceof GTTileRedstoneReceiver) {
				GTTileRedstoneReceiver targetReceiver = (GTTileRedstoneReceiver) targetTile;
				targetReceiver.setRedstoneLevel(0);
			}
		}
	}

	@Override
	public void onBlockUpdate(Block block) {
		this.shouldUpdate = true;
	}

	@Override
	public void update() {
		if (this.shouldUpdate || this.redstoneLevel == -1) {
			int newLevel = world.getRedstonePower(this.pos.offset(this.getFacing()), this.getFacing());
			if (newLevel != this.redstoneLevel) {
				this.redstoneLevel = newLevel;
			}
			this.setActive(this.redstoneLevel > 0);
			if (this.targetPos != null && world.isBlockLoaded(this.targetPos)) {
				TileEntity targetTile = world.getTileEntity(this.targetPos);
				if (targetTile instanceof GTTileRedstoneReceiver) {
					GTTileRedstoneReceiver targetReceiver = (GTTileRedstoneReceiver) targetTile;
					targetReceiver.setRedstoneLevel(this.redstoneLevel);
				}
			}
			this.shouldUpdate = false;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey(NBT_TARGETPOS)) {
			this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
		}
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
	public boolean applyCoordinates(BlockPos pos, int dimensionId) {
		if (!pos.toString().equals(this.pos.toString())) {
			this.targetPos = pos;
			this.redstoneLevel = -1;
			return true;
		}
		return false;
	}

	@Override
	public boolean isInterdimensional() {
		return false;
	}

	@Override
	public BlockPos getAppliedPos() {
		return this.targetPos;
	}

	@Override
	public int getAppliedDimId() {
		return this.world.provider.getDimension();
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Redstone Level: " + this.redstoneLevel, true);
		if (this.targetPos != null) {
			data.put("Connected to: " + world.getBlockState(targetPos).getBlock().getLocalizedName(), true);
		} else {
			data.put("No Target Block", true);
		}
	}

	@Override
	public boolean insertSensorStick(ItemStack card) {
		if (!this.getStackInSlot(0).isEmpty()) {
			return false;
		}
		this.setStackInSlot(0, card.copy());
		return true;
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public void onLeftClick(EntityPlayer arg0, Side arg1) {
		// neeeded by interface, unused by tile
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand arg1, EnumFacing arg2, Side arg3) {
		ItemStack slotStack = this.getStackInSlot(0);
		if (slotStack.isEmpty() || !player.isSneaking()) {
			return false;
		}
		ItemHandlerHelper.giveItemToPlayer(player, slotStack.copy());
		slotStack.shrink(1);
		this.onBlockBreak();
		this.targetPos = null;
		IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
		return true;
	}
}
