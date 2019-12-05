package gtclassic.api.pipe;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.obj.ITankListener;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class GTTilePipeFluid extends GTTilePipeBase implements ITankListener, IGTDebuggableTile, ITickable {

	private IC2Tank tank;

	public GTTilePipeFluid() {
		this.tank = new IC2Tank(1000);
		this.tank.addListener(this);
		this.addGuiFields("tank");
	}

	@Override
	public boolean canConnect(TileEntity tile, EnumFacing dir) {
		if (tile == null) {
			return false;
		}
		if (tile instanceof GTTilePipeFluid) {
			return true;
		}
		return tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir);
	}

	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, "tank");
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag("tank"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.tank.writeToNBT(this.getTag(nbt, "tank"));
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
	public String[] debugInfo() {
		FluidStack fluid = this.tank.getFluid();
		if (fluid != null) {
			return new String[] { fluid.amount + "mB of " + fluid.getLocalizedName() };
		} else {
			return new String[] { "Empty" };
		}
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 2 == 0) {
			for (EnumFacing side : this.connection.getRandomIterator()) {
				IFluidHandler tile = FluidUtil.getFluidHandler(world, this.pos.offset(side), side);
				boolean canExport = tile != null && this.tank.getFluid() != null;
				if (canExport) {
					if (FluidUtil.tryFluidTransfer(tile, this.tank, this.tank.getCapacity() / 10, true) != null) {
						//GTMod.logger.info("Pipe moved fluids");
					}
				}
			}
		}
	}
}
