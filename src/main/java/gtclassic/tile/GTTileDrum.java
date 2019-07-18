package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTValues;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IItemContainer;
import ic2.core.util.obj.ITankListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTTileDrum extends TileEntityMachine implements ITankListener, IItemContainer, IClickable {

	private IC2Tank tank;

	public GTTileDrum() {
		super(0);
		this.tank = new IC2Tank(32000);
		this.tank.addListener(this);
		this.addGuiFields("tank");
		this.setWorld(world);
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
		this.tank.readFromNBT(nbt.getCompoundTag("Tank"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.tank.writeToNBT(this.getTag(nbt, "Tank"));
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

	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		ItemStack stack = GTMaterialGen.get(GTBlocks.tileDrum);
		if (this.tank.getFluid() != null) {
			StackUtil.getOrCreateNbtData(stack).setTag("Fluid", this.tank.getFluid().writeToNBT(new NBTTagCompound()));
		}
		list.add(stack);
		return list;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public String getFluidName() {
		return !this.isTankEmpty() ? this.tank.getFluid().getLocalizedName() : "Empty";
	}

	public int getFluidAmount() {
		return !this.isTankEmpty() ? this.tank.getFluid().amount : 0;
	}

	public boolean isTankEmpty() {
		return this.tank.getFluid() == null || this.tank.getFluidAmount() == 0;
	}

	public FluidStack getFluid() {
		return this.tank.getFluid();
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
		ItemStack playerStack = player.getHeldItem(hand);
		if (playerStack.isEmpty()) {
			if (!IC2.platform.isSimulating()) {
				if (isTankEmpty()) {
					IC2.platform.messagePlayer(player, "Empty");
				} else {
					IC2.platform.messagePlayer(player, getFluidAmount() + "mB of " + getFluidName());
				}
			}
			return true;
		}
		if (isConsumable(playerStack) || GTValues.isBCShard(playerStack)) {
			if (!IC2.platform.isSimulating()) {
				IC2.platform.messagePlayer(player, "Consumable containers are temporarily disabled");
				world.playSound(player, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
			return true;
		}
		if (!playerStack.isEmpty()) {
			ItemStack stackEmpty = FluidUtil.tryEmptyContainer(playerStack, this.tank, this.tank.getCapacity()
					- this.tank.getFluidAmount(), player, true).getResult();
			ItemStack stackCopy = FluidUtil.tryEmptyContainer(playerStack, this.tank, this.tank.getCapacity()
					- this.tank.getFluidAmount(), player, false).getResult();
			if (!stackCopy.isEmpty()) {
				playerStack.shrink(1);
				ItemHandlerHelper.giveItemToPlayer(player, stackEmpty);
				return true;
			}
			ItemStack stackFilled = FluidUtil.tryFillContainer(playerStack, this.tank, 1000, player, true).getResult();
			if (!stackFilled.isEmpty()) {
				playerStack.shrink(1);
				ItemHandlerHelper.giveItemToPlayer(player, stackFilled);
				return true;
			}
		}
		return false;
	}

	public boolean isConsumable(ItemStack stack) {
		return stack.getItem().initCapabilities(stack, null) instanceof FluidHandlerItemStackSimple.Consumable;
	}
}
