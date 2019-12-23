package gtclassic.common.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IItemContainer;
import ic2.core.util.obj.ITankListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;

public class GTTileDrum extends TileEntityMachine implements ITankListener, IItemContainer, IClickable,
		IGTDebuggableTile, IGTRecolorableStorageTile, IGTItemContainerTile {

	private IC2Tank tank;
	@NetworkField(index = 9)
	public int color;
	public static final String NBT_COLOR = "color";
	public static final String NBT_TANK = "tank";

	public GTTileDrum() {
		super(0);
		this.color = 16383998;
		this.tank = new IC2Tank(32000);
		this.tank.addListener(this);
		this.addGuiFields(NBT_TANK);
		this.addNetworkFields(new String[] { NBT_COLOR });
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, NBT_TANK);
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
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_COLOR, this.color);
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
	public void getData(Map<String, Boolean> data) {
		FluidStack fluid = this.tank.getFluid();
		boolean fluidNotNull = fluid != null;
		if (fluidNotNull) {
			data.put(fluid.amount + "mB of " + fluid.getLocalizedName(), false);
		} else {
			data.put("Drum is Empty", false);
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
