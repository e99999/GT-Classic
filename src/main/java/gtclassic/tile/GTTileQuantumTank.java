package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.container.GTContainerQuantumTank;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTLang;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.misc.FluidHelper;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileQuantumTank extends TileEntityMachine implements ITankListener, IItemContainer, ITickable, IHasGui {

	private IC2Tank tank;
	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;

	public GTTileQuantumTank() {
		super(3);
		this.tank = new IC2Tank(Integer.MAX_VALUE);
		this.tank.addListener(this);
		this.addGuiFields("tank");
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.QUANTUM_TANK;
	}

	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, "tank");
		this.inventory.set(2, ItemDisplayIcon.createWithFluidStack(this.tank.getFluid()));
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

	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		ItemStack stack = GTMaterialGen.get(GTBlocks.tileQuantumTank);
		if (this.tank.getFluid() != null) {
			StackUtil.getOrCreateNbtData(stack).setTag("Fluid", this.tank.getFluid().writeToNBT(new NBTTagCompound()));
		}
		list.add(this.getStackInSlot(slotInput));
		list.add(this.getStackInSlot(slotOutput));
		list.add(stack);
		return list;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
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

	public boolean isTankFull() {
		return this.getFluidAmount() == Integer.MAX_VALUE;
	}

	public FluidStack getFluid() {
		return this.tank.getFluid();
	}

	public IC2Tank getTankInstance() {
		return this.tank;
	}

	public boolean isConsumable(ItemStack stack) {
		return stack.getItem().initCapabilities(stack, null) instanceof FluidHandlerItemStackSimple.Consumable;
	}

	@Override
	public void update() {
		if (!isSlotEmpty(slotInput)) {
			// emptying items
			ItemStack emptyStack = FluidUtil.tryEmptyContainer(getStackInSlot(slotInput), this.tank, this.tank.getCapacity()
					- this.tank.getFluidAmount(), null, false).getResult();
			boolean didEmpty = FluidUtil.tryEmptyContainer(getStackInSlot(slotInput), this.tank, this.tank.getCapacity()
					- this.tank.getFluidAmount(), null, false) != FluidActionResult.FAILURE;
			if (!isTankFull() && !isSlotFull(slotOutput) && canOutputStack(emptyStack) && didEmpty) {
				FluidUtil.tryEmptyContainer(getStackInSlot(slotInput), this.tank, this.tank.getCapacity()
						- this.tank.getFluidAmount(), null, true);
				if (isSlotEmpty(slotOutput)) {
					this.setStackInSlot(slotOutput, emptyStack);
				} else {
					this.getStackInSlot(slotOutput).grow(1);
				}
				this.getStackInSlot(slotInput).shrink(1);
			}
			// filling items
			Tuple<ItemStack, FluidStack> filled = FluidHelper.fillContainer(this.getStackInSlot(slotInput), this.tank.getFluid(), true, true);
			if (filled != null && canOutputStack(filled.getFirst())) {
				if (isSlotEmpty(slotOutput)) {
					this.setStackInSlot(slotOutput, filled.getFirst());
				} else {
					this.getStackInSlot(slotOutput).grow(1);
				}
				this.getStackInSlot(slotInput).shrink(1);
				this.tank.drainInternal((FluidStack) filled.getSecond(), true);
			}
		}
	}

	/**
	 * Checks to see if the stack can merge or override the output slot
	 * 
	 * @param stack - the stack to compare to the output slot
	 * @return
	 */
	public boolean canOutputStack(ItemStack stack) {
		return inventory.get(slotOutput).getCount() < 64
				&& (StackUtil.isStackEqual(getStackInSlot(slotOutput), stack, false, false)
						|| inventory.get(slotOutput).isEmpty());
	}

	public boolean isSlotEmpty(int slot) {
		return inventory.get(slot).isEmpty();
	}

	public boolean isSlotFull(int slot) {
		return inventory.get(slot).getCount() > 64;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerQuantumTank(player.inventory, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public void onGuiClosed(EntityPlayer paramEntityPlayer) {
	}

	@Override
	public boolean canInteractWith(EntityPlayer paramEntityPlayer) {
		return !this.isInvalid();
	}

	@Override
	public boolean hasGui(EntityPlayer paramEntityPlayer) {
		return true;
	}
}