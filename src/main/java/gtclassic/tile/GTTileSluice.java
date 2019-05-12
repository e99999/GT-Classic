package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.container.GTContainerSluice;
import gtclassic.gui.GTGuiMachine.GTSluiceGui;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.RotationList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.IHasHandler;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class GTTileSluice extends GTTileFacing
		implements ITickable, IHasGui, IProgressMachine, IHasInventory, IItemContainer, IHasHandler {

	@NetworkField(index = 7)
	float progress = 0;
	@NetworkField(index = 8)
	float recipeOperation = 200.0F;
	boolean processing = false;

	protected InventoryHandler handler = new InventoryHandler(this);
	public NonNullList<ItemStack> inventory;
	public int slotCount;

	public int[] slotOutputs = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public int slotInput = 0;

	public GTTileSluice(EnumFacing... validRotations) {
		super(validRotations);
		this.slotCount = 10;
		this.inventory = NonNullList.withSize(slotCount, ItemStack.EMPTY);
		this.addSlots(this.handler);
		this.handler.validateSlots();
	}

	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.getOppositeList(), slotInput);
		handler.registerDefaultSlotsForSide(RotationList.DOWN, slotOutputs);
		handler.registerSlotType(SlotType.Input, slotInput);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	public float getProgress() {
		return progress;
	}

	public float getMaxProgress() {
		return recipeOperation;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTSluiceGui.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerSluice(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
	}

	@Override
	public void update() {
		// TODO Everything!
	}

	public void setStackInSlot(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
	}

	public ItemStack getStackInSlot(int slot) {
		return (ItemStack) this.inventory.get(slot);
	}

	public int getSlotCount() {
		return this.slotCount;
	}

	public int getMaxStackSize(int slot) {
		return 64;
	}

	public boolean canInsert(int slot, ItemStack stack) {
		return true;
	}

	public InventoryHandler getHandler() {
		return this.handler;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList(this.inventory.size());

		for (int i = 0; i < this.inventory.size(); ++i) {
			ItemStack stack = (ItemStack) this.inventory.get(i);
			if (!stack.isEmpty()) {
				list.add(stack);
			}
		}

		InventoryHandler ihandler = this.getHandler();
		if (ihandler != null) {
			IHasInventory inv = ihandler.getUpgradeSlots();

			for (int i = 0; i < inv.getSlotCount(); ++i) {
				ItemStack result = inv.getStackInSlot(i);
				if (!result.isEmpty()) {
					list.add(result);
				}
			}
		}

		return list;
	}

}
