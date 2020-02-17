package gtclassic.common.tile;

import java.util.Map;

import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class GTTileTesseractSlave extends TileEntityElecMachine
		implements IGTCoordinateTile, IGTDebuggableTile, ITickable {

	private BlockPos targetPos;
	private int targetDim;
	private static final String NBT_TARGETPOS = "targetPos";
	private static final String NBT_TARGETDIM = "targetDim";

	public GTTileTesseractSlave() {
		super(0, 128);
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
		this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
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
	public void update() {
		this.handleEnergy();
		if (this.getActive() && this.targetPos != null) {
			WorldServer targetWorld = DimensionManager.getWorld(this.targetDim);
			if (targetWorld == null) {
				return;
			}
			TileEntity destination = targetWorld.getTileEntity(this.targetPos);
			if (destination instanceof GTTileTesseractMaster) {
				GTTileTesseractMaster output = (GTTileTesseractMaster) destination;
				if (!output.getActive()) {
					return;
				}
				onTesseractTick(output);
			}
		}
	}

	private void onTesseractTick(GTTileTesseractMaster tesseract) {
		// Fluids
		IFluidHandler start = FluidUtil.getFluidHandler(world, this.getInventoryPos(), this.getInventorySide());
		IFluidHandler end = FluidUtil.getFluidHandler(DimensionManager.getWorld(this.targetDim), tesseract.getInventoryPos(), tesseract.getInventorySide());
		boolean canExport = start != null && end != null;
		if (canExport && FluidUtil.tryFluidTransfer(end, start, 4000, true) != null) {
			return;
		}
		// Items
		IItemTransporter in = TransporterManager.manager.getTransporter(world.getTileEntity(this.getInventoryPos()), true);
		if (in == null) {
			return;
		}
		IItemTransporter out = TransporterManager.manager.getTransporter(world.getTileEntity(tesseract.getInventoryPos()), true);
		if (out == null) {
			return;
		}
		int limit = in.getSizeInventory(this.getInventorySide());
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = in.removeItem(CommonFilters.Anything, this.getInventorySide(), 64, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = out.addItem(stack, tesseract.getInventorySide(), true);
			if (added.getCount() <= 0) {
				break;
			}
			in.removeItem(new BasicItemFilter(added), this.getInventorySide(), added.getCount(), true);
		}
	}

	private void handleEnergy() {
		if (this.energy >= 32) {
			this.setActive(true);
			this.useEnergy(32);
		} else {
			this.setActive(false);
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		String status = this.targetPos != null ? "Pos: " + this.targetPos.toString() + "Dimension: " + this.targetDim
				: "None";
		data.put("Target Destination: " + status, true);
	}
}
