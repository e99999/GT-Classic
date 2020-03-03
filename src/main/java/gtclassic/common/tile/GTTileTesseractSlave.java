package gtclassic.common.tile;

import java.util.Map;

import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IClickable;
import net.minecraft.entity.player.EntityPlayer;
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
	GTTileTesseractMaster targetMaster;

	public GTTileTesseractSlave() {
		super(1, 128);
		this.maxEnergy = 10000;
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
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return canExtendCapabilites() ? this.targetMaster.tesseractTile.hasCapability(capability, facing)
				: super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return canExtendCapabilites() ? this.targetMaster.tesseractTile.getCapability(capability, facing)
				: super.getCapability(capability, facing);
	}

	private boolean canExtendCapabilites() {
		return this.targetMaster != null && this.hasEnergy(32) && this.targetMaster.tesseractTile != null;
	}

	@Override
	public void update() {
		this.handleEnergy();
		if (this.targetPos != null) {
			WorldServer targetWorld = DimensionManager.getWorld(this.targetDim);
			if (targetWorld == null) {
				this.targetMaster = null;
				return;
			}
			TileEntity destination = targetWorld.getTileEntity(this.targetPos);
			if (destination instanceof GTTileTesseractMaster) {
				GTTileTesseractMaster tesseract = (GTTileTesseractMaster) destination;
				this.targetMaster = tesseract;
				return;
			}
			this.targetMaster = null;
		}
	}

	private void handleEnergy() {
		if (this.hasEnergy(32) && this.targetMaster != null) {
			this.useEnergy(32);
			return;
		}
		this.targetMaster = null;
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
		this.targetMaster = null;
		this.targetPos = null;
		IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
		return true;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		String status = this.canExtendCapabilites() ? "Connected to Tesseract Generator"
				: "Failed to connect Tesseract Generator";
		data.put("Target Destination: " + status, true);
	}
}
