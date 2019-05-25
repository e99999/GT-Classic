package gtclassic.tile;

import gtclassic.container.GTContainerDigitalChest;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileDigitalChest extends TileEntityMachine implements IHasGui, ITickable {

	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;

	int maxSize = Integer.MAX_VALUE;
	int digitalCount;

	public GTTileDigitalChest() {
		super(3);
		this.digitalCount = 0;
		this.addGuiFields(new String[] { "digitalCount" });
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.UP.getOppositeList());
		handler.registerDefaultSlotAccess(AccessRule.Import, 0);
		handler.registerDefaultSlotAccess(AccessRule.Export, 1);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 0);
		handler.registerSlotType(SlotType.Input, 0);
		handler.registerSlotType(SlotType.Output, 1);
	}

	@Override
	public int getMaxStackSize(int slot) {
		return (slot == 2) ? 1 : 64;
	}

	public void updateGUI() {
		this.getNetwork().updateTileGuiField(this, "digitalCount");
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.digitalCount = nbt.getInteger("digitalCount");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("digitalCount", this.digitalCount);
		return nbt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerDigitalChest(player.inventory, this);
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

	/**
	 * Now that the tile stuff is out of the way we can handle the real logic
	 */

	@Override
	public void update() {
		// if the digital storage is null, and the input slot has a stack, and that
		// stack can merge into the digtal storage...
		if (!isSlotEmpty(slotInput) && canDigitizeStack(getStackInSlot(slotInput))) {
			// set the display slot to show the input item
			setStackInSlot(slotDisplay, StackUtil.copyWithSize(getStackInSlot(slotInput), 1));
			// add the stack count to the digital count
			int count = getSlotStackCount(slotInput);
			this.digitalCount = this.digitalCount + count;
			// remove the input slot stack completely
			inventory.get(slotInput).shrink(count);
			updateGUI();
		}

		// now the inverse for outputting to the output slot
		if (!isSlotEmpty(slotDisplay) && canOutputStack(getStackInSlot(slotDisplay)) && this.digitalCount > 0) {
			// set the output slot to be the display item + max possible size
			setStackInSlot(slotOutput, StackUtil.copyWithSize(getStackInSlot(slotDisplay), getOutputCount()));
			// subtract from the digital storage
			this.digitalCount = this.digitalCount - getOutputCount();
			// clear the display slot if the digital count is drained
			if (this.digitalCount == 0) {
				inventory.get(slotDisplay).shrink(1);
			}
			updateGUI();
		}
	}

	/**
	 * This checks to see if the remaining digital count is above 64, to give the
	 * stack size for the output slot. If its above 64 it just shoves a full stack
	 * in, if less it returns the remainder.
	 */
	public int getOutputCount() {
		return (this.digitalCount >= 64) ? 64 : this.digitalCount;
	}

	/**
	 * Just a util for getting the stack size in a given slot.
	 * 
	 * @param slot - slot to retrive slot count from
	 */
	public int getSlotStackCount(int slot) {
		return getStackInSlot(slot).getCount();
	}

	/**
	 * Another util to see if the slot is empty or not.
	 * 
	 * @param slot - slot to isEmpty check
	 * @return
	 */
	public boolean isSlotEmpty(int slot) {
		return (inventory.get(slot).isEmpty());
	}

	/**
	 * Checks to see if the stack can fit into the digital storage
	 * 
	 * @param stack - the stack to compare for the display slot/currently stored
	 *              stack
	 * @return
	 */
	public boolean canDigitizeStack(ItemStack stack) {
		return (this.digitalCount + getStackInSlot(slotInput).getCount()) < maxSize
				&& (StackUtil.isStackEqual(getStackInSlot(slotDisplay), stack, false, false)
						|| inventory.get(slotDisplay).isEmpty());
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

}
