package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.container.GTContainerQuantumChest;
import gtclassic.helpers.GTHelperStack;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTLang;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileQuantumChest extends TileEntityMachine implements IHasGui, ITickable, IItemContainer {

	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;
	int maxSize = Integer.MAX_VALUE;
	int digitalCount;
	ItemStack display;

	public GTTileQuantumChest() {
		super(3);
		this.digitalCount = 0;
		this.addGuiFields(new String[] { "digitalCount", "display" });
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.UP.invert());
		handler.registerDefaultSlotAccess(AccessRule.Import, 0);
		handler.registerDefaultSlotAccess(AccessRule.Export, 1);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), 0);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), 1);
		handler.registerSlotType(SlotType.Input, 0);
		handler.registerSlotType(SlotType.Output, 1);
	}

	@Override
	public int getMaxStackSize(int slot) {
		return (slot == 2) ? 1 : 64;
	}

	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, "digitalCount");
		this.display = StackUtil.copyWithSize(this.getStackInSlot(slotDisplay), 1);
		this.getNetwork().updateTileGuiField(this, "display");
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.QUANTUM_CHEST;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.digitalCount = nbt.getInteger("digitalCount");
		this.display = new ItemStack(nbt.getCompoundTag("display"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("digitalCount", this.digitalCount);
		if (this.display != null) {
			nbt.setTag("display", display.writeToNBT(new NBTTagCompound()));
		}
		return nbt;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		ItemStack stack = GTMaterialGen.get(GTBlocks.tileQuantumChest);
		if (this.display != null && this.digitalCount > 0) {
			StackUtil.getOrCreateNbtData(stack).setTag("display", display.writeToNBT(new NBTTagCompound()));
			StackUtil.getOrCreateNbtData(stack).setInteger("digitalCount", this.digitalCount);
		}
		list.add(stack);
		list.add(this.getStackInSlot(slotInput));
		list.add(this.getStackInSlot(slotOutput));
		return list;
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
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
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

	public int getQuantumCount() {
		return this.digitalCount;
	}

	@Override
	public void update() {
		// Logic for stack input
		if (!GTHelperStack.isSlotEmpty(this, slotInput) && canDigitizeStack(getStackInSlot(slotInput))) {
			setStackInSlot(slotDisplay, StackUtil.copyWithSize(getStackInSlot(slotInput), 1));
			this.digitalCount = this.digitalCount + 1;
			inventory.get(slotInput).shrink(1);
			updateGui();
		}
		// Logic for stack output
		if (!GTHelperStack.isSlotEmpty(this, slotDisplay)
				&& GTHelperStack.canOutputStack(this, getStackInSlot(slotDisplay), slotOutput)
				&& this.digitalCount > 0) {
			if (GTHelperStack.isSlotEmpty(this, slotOutput)) {
				setStackInSlot(slotOutput, getStackInSlot(slotDisplay).copy());
				this.digitalCount = this.digitalCount - 1;
			} else {
				getStackInSlot(slotOutput).grow(1);
				this.digitalCount = this.digitalCount - 1;
			}
			if (this.digitalCount == 0) {
				inventory.get(slotDisplay).shrink(1);
			}
			updateGui();
		}
	}

	/** Checks to see if the given stack can go into the digital storage **/
	public boolean canDigitizeStack(ItemStack stack) {
		if (inventory.get(slotDisplay).isEmpty()) {
			return true;
		}
		return this.digitalCount < maxSize && GTHelperStack.isEqual(getStackInSlot(slotDisplay), stack);
	}

	/** Sets the digital count, called when the block is placed for NBT stuff **/
	public void setDigtialCount(int count) {
		this.digitalCount = count;
	}

	public boolean canFit(int count) {
		return count + this.digitalCount <= Integer.MAX_VALUE;
	}
}
