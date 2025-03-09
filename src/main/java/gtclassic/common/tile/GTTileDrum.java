package gtclassic.common.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.helpers.GTUtility;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IItemContainer;
import ic2.core.util.obj.ITankListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;

public class GTTileDrum extends TileEntityMachine implements ITankListener, IItemContainer, IClickable,
		IGTDebuggableTile, ITickable, IGTRecolorableStorageTile, IGTItemContainerTile {

	private IC2Tank tank;
	private boolean flow = false;
	@NetworkField(index = 9)
	public int color;
	public static final String NBT_COLOR = "color";
	public static final String NBT_TANK = "tank";
	public static final String NBT_FLOW = "flow";

	public GTTileDrum() {
		super(0);
		this.color = 16383998;
		this.tank = new IC2Tank(32000);
		this.tank.addListener(this);
		this.addNetworkFields(new String[] { NBT_COLOR });
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	@Override
	public void onTankChanged(IFluidTank tank) {
		world.updateComparatorOutputLevel(pos, this.blockType);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag(NBT_TANK));
		if (nbt.hasKey(NBT_COLOR)) {
			this.color = nbt.getInteger(NBT_COLOR);
		} else {
			this.color = 16383998;
		}
		this.flow = nbt.getBoolean(NBT_FLOW);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_COLOR, this.color);
		this.tank.writeToNBT(this.getTag(nbt, NBT_TANK));
		nbt.setBoolean(NBT_FLOW, this.flow);
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
		if (player.isSneaking() && player.getHeldItemMainhand().isEmpty()) {
			this.flow = !this.flow;
			if (this.isSimulating()) {
				String msg = this.flow ? "Auto output enabled" : "Auto output disabled";
				FluidStack fluid = this.tank.getFluid();
				IC2.platform.messagePlayer(player, msg);
				if (fluidNotNull(fluid)) {
					IC2.platform.messagePlayer(player, getFluidDirection(fluid));
				} else {
					IC2.platform.messagePlayer(player, "Empty");
				}
				IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			}
		} else {
			GTHelperFluid.doClickableFluidContainerThings(player, hand, world, pos, this.tank);
		}
		return true;
	}

	@Override
	public void update() {
		if (this.flow && world.getTotalWorldTime() % 10 == 0) {
			EnumFacing side = updateSideForOutput();
			GTUtility.exportFluidFromMachineToSide(this, this.tank, side, 500);
		}
	}
	
	public String getFluidDirection(FluidStack fluid) {
		return fluid.getFluid().isGaseous() ? "Is gaseous will flow upward if set to output"
				: "Is fluid will flow downward if set to output";
	}
	
	public Boolean fluidNotNull(FluidStack fluid) {
		return fluid != null;
	}

	private EnumFacing updateSideForOutput() {
		if (this.tank.getFluid() != null && this.tank.getFluid().getFluid().isGaseous()) {
			return EnumFacing.UP;
		}
		return EnumFacing.DOWN;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		FluidStack fluid = this.tank.getFluid();
		String msg = this.flow ? "Will output automatically" : "Will not output automatically";
		if (fluidNotNull(fluid)) {
			data.put(fluid.amount + "mB of " + fluid.getLocalizedName(), false);
			data.put(msg, false);
			data.put(getFluidDirection(fluid), false);
		} else {
			data.put("Empty", false);
			data.put(msg, false);
		}
		
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals(NBT_COLOR)) {
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
			nbt.setInteger(NBT_COLOR, this.color);
			data = true;
		}
		if (this.flow) {
			nbt.setBoolean(NBT_FLOW, this.flow);
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