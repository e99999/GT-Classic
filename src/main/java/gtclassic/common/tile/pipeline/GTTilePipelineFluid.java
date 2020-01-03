package gtclassic.common.tile.pipeline;

import java.util.Map;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.GTBlocks;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.obj.ITankListener;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class GTTilePipelineFluid extends GTTilePipelineBase implements ITankListener, IGTDebuggableTile {
	
	private IC2Tank tank;
	public static final String NBT_TANK = "tank";

	public GTTilePipelineFluid() {
		super(0);
		this.tank = new IC2Tank(8000);
		this.tank.addListener(this);
		//this.addGuiFields(NBT_TANK);
	}

	@Override
	public Block getBlockDrop() {
		return GTBlocks.pipelineFluid;
	}

	public void onTankChanged(IFluidTank tank) {
		//this.getNetwork().updateTileGuiField(this, NBT_TANK);
		//this.inventory.set(0, ItemDisplayIcon.createWithFluidStack(this.tank.getFluid()));
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
	public void onPipelineTick() {
		IFluidHandler fluidTile = GTHelperFluid.getFluidHandler(world, this.targetPos, EnumFacing.UP);
		boolean canExport = fluidTile != null && this.tank.getFluid() != null;
		if (canExport && FluidUtil.tryFluidTransfer(fluidTile, this.tank, this.tank.getCapacity()
				/ 2, true) != null) {
			// Empty on true method
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		FluidStack fluid = this.tank.getFluid();
		data.put(fluid != null ? fluid.amount + "mB of " + fluid.getLocalizedName() : "Pipe is empty", false);
		if (this.targetPos == null) {
			data.put("No Endpoint Attached", false);
			return;
		}
		String block = world.isBlockLoaded(this.targetPos)
				? "Destination: " + world.getBlockState(this.targetPos).getBlock().getLocalizedName()
				: "Destination is not loaded!";
		data.put(block, false);
	}
}
