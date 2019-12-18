package gtclassic.api.pipe;

import java.util.Map;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.common.tile.GTTileTranslocatorFluid;
import ic2.core.fluid.IC2Tank;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.util.obj.ITankListener;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public abstract class GTTilePipeFluidBase extends GTTilePipeBase implements ITankListener {

	private IC2Tank tank;
	public static final String NBT_TANK = "tank";

	public GTTilePipeFluidBase(int capacity) {
		super(1);
		this.tank = new IC2Tank(capacity);
		this.tank.addListener(this);
		this.addGuiFields(NBT_TANK);
	}

	@Override
	public boolean canConnect(TileEntity tile, EnumFacing dir) {
		if (tile == null) {
			return false;
		}
		if (tile instanceof GTTilePipeFluidBase) {
			GTTilePipeFluidBase pipe = (GTTilePipeFluidBase) tile;
			return (!pipe.isColored() || !this.isColored()) || (this.color == pipe.color);
		}
		if (tile instanceof GTTileTranslocatorFluid) {
			return true;
		}
		return tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir);
	}

	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, NBT_TANK);
		this.inventory.set(0, ItemDisplayIcon.createWithFluidStack(this.tank.getFluid()));
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag(NBT_TANK));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.tank.writeToNBT(this.getTag(nbt, NBT_TANK));
		return nbt;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? true
				: super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank)
				: super.getCapability(capability, facing);
	}

	public IC2Tank getTankInstance() {
		return this.tank;
	}

	@Override
	public boolean isEmpty() {
		return this.tank.getFluid() == null;
	}

	@Override
	public void onPipeTick() {
		for (EnumFacing side : this.connection.getRandomIterator()) {
			BlockPos sidePos = this.pos.offset(side);
			if (world.isBlockLoaded(sidePos) && !isBlacklistSide(side)) {
				TileEntity tile = world.getTileEntity(sidePos);
				if (this.onlyPipes && !(tile instanceof GTTilePipeFluidBase)) {
					continue;
				}
				IFluidHandler fluidTile = GTHelperFluid.getFluidHandler(world, sidePos, side);
				boolean canExport = fluidTile != null && this.tank.getFluid() != null;
				if (canExport && FluidUtil.tryFluidTransfer(fluidTile, this.tank, this.tank.getCapacity()
						/ 2, true) != null) {
					// Empty on true method
				}
			}
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		FluidStack fluid = this.tank.getFluid();
		String last = this.blacklistSide != null ? this.blacklistSide.toString().toUpperCase() : "None";
		data.put(fluid != null ? fluid.amount + "mB of " + fluid.getLocalizedName() : "Pipe is empty", false);
		data.put("Restricted only to pipes: " + this.onlyPipes, false);
		data.put("Blacklisted side: " + last, true);
	}
}
