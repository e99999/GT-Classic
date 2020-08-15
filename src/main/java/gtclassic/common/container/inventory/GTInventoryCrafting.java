package gtclassic.common.container.inventory;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.common.container.GTContainerWorktable;
import gtclassic.common.tile.GTTileWorktable;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GTInventoryCrafting extends InventoryCrafting {

	GTContainerWorktable containerWorktable;
	GTTileWorktable worktable;

	public GTInventoryCrafting(GTContainerWorktable eventHandlerIn, GTTileWorktable tile) {
		super(eventHandlerIn, 3, 3);
		this.containerWorktable = eventHandlerIn;
		this.worktable = tile;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		super.setInventorySlotContents(index, stack);
		worktable.craftingInventory.set(index, stack);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		int currentCount = count;
		ItemStack craftingStackCopy = this.getStackInSlot(index).copy();
		ItemStack craftingStack = super.decrStackSize(index, count);
		ItemStack craftingSlotStack = this.getStackInSlot(index);
		boolean pulled = false;
		if (containerWorktable.craftResult.crafted) {
			for (int i = 1; i < 17; i++) {
				Slot slot = containerWorktable.getSlot(i);
				ItemStack stack = slot.getStack();
				if (stack.isEmpty()) {
					continue;
				}
				if (GTHelperStack.isEqual(stack, craftingStackCopy)
						&& craftingSlotStack.getCount() < craftingStackCopy.getMaxStackSize()) {
					int room = craftingStackCopy.getMaxStackSize() - craftingSlotStack.getCount();
					if (room < 0)
						room = 0;
					if (room == 0) {
						break;
					}
					if (room < currentCount) {
						currentCount = room;
					}
					if (stack.getCount() >= currentCount) {
						if (craftingSlotStack.isEmpty()) {
							this.setInventorySlotContents(index, GTHelperStack.copyWithSize(stack, currentCount));
						} else {
							craftingSlotStack.grow(currentCount);
						}
						stack.shrink(currentCount);
						currentCount = 0;
						if (stack.getCount() == 0) {
							containerWorktable.setStackInSlot(i, ItemStack.EMPTY);
						}
					} else {
						currentCount -= stack.getCount();
						if (craftingSlotStack.isEmpty()) {
							this.setInventorySlotContents(index, GTHelperStack.copyWithSize(stack, stack.getCount()));
						} else {
							craftingSlotStack.grow(stack.getCount());
						}
						containerWorktable.setStackInSlot(i, ItemStack.EMPTY);
					}
					if (!pulled) {
						pulled = true;
					}
				}
				if (currentCount == 0) {
					break;
				}
			}
			if (index == 8) {
				containerWorktable.craftResult.setCrafted(false);
			}
		}
		if (pulled) {
			this.containerWorktable.onCraftMatrixChanged(this);
		}
		return craftingStack;
	}
}
