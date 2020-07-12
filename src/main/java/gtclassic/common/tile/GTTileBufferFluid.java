package gtclassic.common.tile;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.helpers.GTUtility;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerBufferFluid;
import gtclassic.common.util.GTIFilters;
import ic2.core.RotationList;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBufferFluid extends GTTileBufferBase implements ITankListener, IHasGui, IClickable {

	private IC2Tank tank;
	int slotInput = 0;
	int slotOutput = 1;
	int[] slotAll = { 0, 1 };
	int slotDisplay = 2;
	public static final String NBT_TANK = "tank";

	public GTTileBufferFluid() {
		super(3);
		this.tank = new IC2Tank(16000);
		this.tank.addListener(this);
		this.addGuiFields(NBT_TANK);
		this.isFluid = true;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, 0);
		handler.registerDefaultSlotAccess(AccessRule.Export, 1);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 0);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 1);
		handler.registerSlotType(SlotType.Input, 0);
		handler.registerSlotType(SlotType.Output, 1);
		handler.registerInputFilter(new GTIFilters.FluidItemFilter(), 0);
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

	@Override
	public LocaleComp getBlockName() {
		return GTLang.BUFFER_FLUID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBufferFluid(player.inventory, this);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		// needed for construction
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	public IC2Tank getTankInstance() {
		return this.tank;
	}

	@Override
	public void onTankChanged(IFluidTank paramIFluidTank) {
		this.getNetwork().updateTileGuiField(this, NBT_TANK);
		this.inventory.set(2, ItemDisplayIcon.createWithFluidStack(this.tank.getFluid()));
	}

	@Override
	public void update() {
		GTHelperFluid.doFluidContainerThings(this, this.tank, slotInput, slotOutput);
		super.update();
	}

	@Override
	public void onBufferTick() {
		GTUtility.exportFluidFromMachineToSide(this, this.tank, this.getFacing(), 10000);
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
	public boolean isInventoryFull() {
		return this.tank.getFluid() != null && this.tank.getFluidAmount() > 0;
	}
}
