package gtclassic.common.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTMonkeyWrenchTile;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.pipe.GTTilePipeBase;
import gtclassic.common.GTBlocks;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IItemContainer;
import ic2.core.util.obj.ITankListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;

public class GTTileDrum extends TileEntityMachine implements ITankListener, IItemContainer, IClickable,
		IGTDebuggableTile, IGTMonkeyWrenchTile, ITickable, IGTRecolorableStorageTile, IGTItemContainerTile {

	private IC2Tank tank;
	private boolean flow = false;
	@NetworkField(index = 9)
	public int color;

	public GTTileDrum() {
		super(0);
		this.color = 16383998;
		this.tank = new IC2Tank(32000);
		this.tank.addListener(this);
		this.addGuiFields("tank");
		this.addNetworkFields(new String[] { "color" });
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, "tank");
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag("tank"));
		this.color = nbt.getInteger("color");
		this.flow = nbt.getBoolean("flow");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("color", this.color);
		this.tank.writeToNBT(this.getTag(nbt, "tank"));
		nbt.setBoolean("flow", this.flow);
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

	public void setFlow(boolean canFlow) {
		this.flow = canFlow;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public IC2Tank getTankInstance() {
		return this.tank;
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
	public void onLeftClick(EntityPlayer var1, Side var2) {
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing enumFacing, Side side) {
		return GTHelperFluid.doClickableFluidContainerThings(player, hand, world, pos, this.tank);
	}

	@Override
	public void update() {
		if (this.flow && world.getTotalWorldTime() % 10 == 0 && this.tank.getFluid() != null) {
			boolean isGas = this.tank.getFluid().getFluid().isGaseous();
			BlockPos direction = isGas ? getPos().up() : getPos().down();
			EnumFacing side = isGas ? EnumFacing.DOWN : EnumFacing.UP;
			IFluidHandler fluidTile = FluidUtil.getFluidHandler(world, direction, side);
			if (fluidTile != null && FluidUtil.tryFluidTransfer(fluidTile, this.tank, 500, true) != null) {
				TileEntity tile = this.getWorld().getTileEntity(direction);
				if (tile instanceof GTTilePipeBase) {
					((GTTilePipeBase) tile).lastRecievedFrom = side;
				}
			}
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		FluidStack fluid = this.tank.getFluid();
		boolean fluidNotNull = fluid != null;
		if (fluidNotNull) {
			String type = fluid.getFluid().isGaseous() ? "Is gaseous will flow upward" : "Is fluid will flow downward";
			data.put(fluid.amount + "mB of " + fluid.getLocalizedName(), false);
			data.put(type, false);
		} else {
			data.put("Empty", false);
		}
		String msg = this.flow ? "Will fill adjacent tanks" : "Wont fill adjacent tanks";
		data.put(msg, false);
	}

	@Override
	public boolean onMonkeyWrench(EntityPlayer player, World world, BlockPos pos, EnumFacing side, EnumHand hand) {
		this.flow = !this.flow;
		if (this.isSimulating()) {
			String msg = this.flow ? "Will fill adjacent tanks" : "Wont fill adjacent tanks";
			IC2.platform.messagePlayer(player, msg);
		}
		return true;
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals("color")) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}

	@Override
	public void setTileColor(int color) {
		this.color = color;
	}

	@Override
	public Color getTileColor() {
		return new Color(this.color);
	}

	@Override
	public boolean isColored() {
		return this.color != 16383998;
	}

	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<>();
		ItemStack stack = GTMaterialGen.get(GTBlocks.tileDrum);
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
		boolean data = false;
		if (this.tank.getFluid() != null) {
			nbt.setTag("Fluid", this.tank.getFluid().writeToNBT(new NBTTagCompound()));
			data = true;
		}
		if (isColored()) {
			nbt.setInteger("color", this.color);
			data = true;
		}
		if (this.flow) {
			nbt.setBoolean("flow", this.flow);
			data = true;
		}
		if (!data) {
			stack.setTagCompound(null);
		}
		list.add(stack);
		return list;
	}

	@Override
	public List<ItemStack> getInventoryDrops() {
		return getDrops();
	}
}
