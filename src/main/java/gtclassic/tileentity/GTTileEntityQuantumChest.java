package gtclassic.tileentity;

import gtclassic.container.GTContainerQuantumChest;
import gtclassic.util.GTValues;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileEntityQuantumChest extends TileEntityMachine implements IHasGui, ITickable {

	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;

	int maxSize = Integer.MAX_VALUE;
	int quantumCount;

	public GTTileEntityQuantumChest() {
		super(3);
		this.quantumCount = 0;
		this.addGuiFields(new String[] { "quantumCount" });
	}

	@Override
	public int getMaxStackSize(int slot) {
		if (slot == 2) {
			return 1;
		} else {
			return 64;
		}
	}

	public boolean isSpace() {
		if (this.quantumCount < maxSize) {
			return true;
		} else {
			return false;
		}
	}

	public void updateGUI() {
		this.getNetwork().updateTileGuiField(this, "quantumCount");
	}

	public void initQuantum(int slot) {
		this.quantumCount = (this.inventory.get(slot).getCount());
		updateGUI();
	}

	public void copySlotToQuantum(int slot) {
		this.quantumCount = this.quantumCount + inventory.get(slot).getCount();
		updateGUI();
	}

	public void removeQuantumToSlot(int size) {
		this.quantumCount = this.quantumCount - size;
		if (quantumCount < 0) {
			quantumCount = 0;
		}
		updateGUI();
	}

	@Override
	public void update() {
		//TODO new data based quantum logic
	}

	public int getDisplayCount() {
		return inventory.get(slotDisplay).getCount();
	}

	public int getQuantumCount() {
		return this.quantumCount;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTValues.quantumchest;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.quantumCount = nbt.getInteger("quantumCount");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("quantumCount", this.quantumCount);
		return nbt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerQuantumChest(player.inventory, this);
	}

	@Override
	public boolean supportsRotation() {
		return false;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, 0);
		handler.registerDefaultSlotAccess(AccessRule.Export, 1);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 0);
		handler.registerSlotType(SlotType.Input, 0);
		handler.registerSlotType(SlotType.Output, 1);
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

}
