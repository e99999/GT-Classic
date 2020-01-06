package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.obj.ITankListener;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class GTTileDataImportFluid extends GTTileDataImportBase implements ITankListener, IGTDebuggableTile {

	private IC2Tank tank;
	public static final String NBT_TANK = "tank";

	public GTTileDataImportFluid() {
		super(0);
		this.tank = new IC2Tank(8000);
		this.tank.addListener(this);
	}

	public void onTankChanged(IFluidTank tank) {
		// We dont need to update any info in the tile
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
	public void onPipelineImport() {
		IFluidHandler start = GTHelperFluid.getFluidHandler(world, this.pos.offset(this.getFacing()), this.getFacing().getOpposite());
		IFluidHandler end = FluidUtil.getFluidHandler(world, this.pos, getFacing());
		if (start != null && end != null) {
			FluidUtil.tryFluidTransfer(end, start, this.tank.getCapacity() / 2, true);
		}
	}

	@Override
	public boolean onPipelineTick(BlockPos nodePos) {
		IFluidHandler fluidTile = GTHelperFluid.getFluidHandler(world, nodePos, EnumFacing.UP);
		boolean canExport = fluidTile != null && this.tank.getFluid() != null;
		if (canExport && FluidUtil.tryFluidTransfer(fluidTile, this.tank, this.tank.getCapacity() / 2, true) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		FluidStack fluid = this.tank.getFluid();
		data.put("Will Import: " + this.getActive(), false);
		data.put(fluid != null ? fluid.amount + "mB of " + fluid.getLocalizedName() : "Pipe is empty", false);
		if (this.outputNodes.isEmpty()) {
			data.put("No Endpoint Attached", false);
			return;
		}
		data.put("Endpoints found: " + this.outputNodes.size(), false);
	}
}
