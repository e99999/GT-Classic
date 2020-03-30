package gtclassic.common.tile;

import java.util.Map;

import javax.annotation.Nullable;

import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IClickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTTileTesseractSlave extends TileEntityElecMachine
		implements IGTCoordinateTile, IGTDebuggableTile, ITickable, IClickable {

	private BlockPos targetPos;
	private int targetDim;
	private static final String NBT_TARGETPOS = "targetPos";
	private static final String NBT_TARGETDIM = "targetDim";
	private static final String NBT_TARGET = "targetMaster";
	@NetworkField(index = 8)
	GTTileTesseractMaster targetMaster;

	public GTTileTesseractSlave() {
		super(1, 128);
		this.maxEnergy = 10000;
		this.addNetworkFields(new String[] { NBT_TARGET });
	}

	public BlockPos getInventoryPos() {
		return this.pos.offset(this.getFacing().getOpposite());
	}

	public EnumFacing getInventorySide() {
		return this.getFacing();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey(NBT_TARGETPOS)) {
			this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
		}
		this.targetDim = nbt.getInteger(NBT_TARGETDIM);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.targetPos != null) {
			nbt.setTag(NBT_TARGETPOS, NBTUtil.createPosTag(targetPos));
		}
		nbt.setInteger(NBT_TARGETDIM, targetDim);
		return nbt;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing();
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
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public boolean applyCoordinates(BlockPos pos, int dimensionId) {
		this.targetPos = pos;
		this.targetDim = dimensionId;
		return true;
	}

	@Override
	public boolean isInterdimensional() {
		return true;
	}

	@Override
	public BlockPos getAppliedPos() {
		return this.targetPos;
	}

	@Override
	public int getAppliedDimId() {
		return this.targetDim;
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
	public void onLoaded() {
		super.onLoaded();
		updateConnections();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return canExtendCapabilites() && this.targetMaster.tesseractTile.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return canExtendCapabilites() ? this.targetMaster.tesseractTile.getCapability(capability, facing)
				: null;
	}

	private boolean canExtendCapabilites() {
		if (this.targetMaster == null || this.targetMaster.tesseractTile == null) {
			return false;
		}
		if (this.targetMaster.isInvalid()) {
			setTarget(null);
			return false;
		}
		if (this.targetMaster.tesseractTile.isInvalid()) {
			setTarget(null);
			return false;
		}
		return this.hasEnergy(32);
	}

	@Override
	public void update() {
		this.handleEnergy();
		if (world.getTotalWorldTime() % 20 == 0 && this.targetPos != null) {
			WorldServer targetWorld = DimensionManager.getWorld(this.targetDim);
			if (targetWorld == null) {
				setTarget(null);
				return;
			}
			TileEntity destination = targetWorld.getTileEntity(this.targetPos);
			if (destination instanceof GTTileTesseractMaster && ((GTTileTesseractMaster) destination).getActive()) {
				setTarget((GTTileTesseractMaster) destination);
				return;
			}
			setTarget(null);
		}
	}

	private void handleEnergy() {
		if (this.hasEnergy(32) && this.targetMaster != null && !this.targetMaster.isInvalid()) {
			this.useEnergy(32);
			return;
		}
		setTarget(null);
	}

	private void setTarget(@Nullable GTTileTesseractMaster newTargetMaster) {
		if (newTargetMaster != this.targetMaster) {
			this.targetMaster = newTargetMaster;
			this.updateConnections();
		}
	}

	public void updateConnections() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			BlockPos sidedPos = pos.offset(facing);
			if (world.isBlockLoaded(sidedPos)) {
				world.neighborChanged(sidedPos, Blocks.AIR, pos);
			}
		}
		if (world.isBlockLoaded(pos)) {
			world.neighborChanged(pos, Blocks.AIR, pos);
		}
		this.getNetwork().updateTileEntityField(this, NBT_TARGET);
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals(NBT_TARGET)) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
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
		setTarget(null);
		this.targetPos = null;
		IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
		return true;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		String status = this.canExtendCapabilites() ? "Connected to Tesseract Generator"
				: "Failed to connect Tesseract Generator";
		data.put(status, true);
	}
}
