package gtclassic.util;

import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.misc.StackUtil;
import net.minecraft.item.ItemStack;

public class GTStackUtil {

	/** Checks if a stack can merge with default stack size **/
	public static boolean canMerge(ItemStack stack, ItemStack toCompare) {
		return canMerge(stack, toCompare, 64);
	}

	/** Checks if a stack can merge **/
	public static boolean canMerge(ItemStack stack, ItemStack toCompare, int maxCount) {
		return isEqual(stack, toCompare) && (toCompare.getCount() + stack.getCount() < maxCount);
	}

	/** Just an easy place for me to call this over and over **/
	public static boolean isEqual(ItemStack stack, ItemStack toCompare) {
		return StackUtil.isStackEqual(stack, toCompare, false, false);
	}

	/** Checks if a machine slot can accept a stack **/
	public static boolean canOutputStack(TileEntityMachine tile, ItemStack stack, int slotOutput) {
		return tile.inventory.get(slotOutput).getCount() < 64
				&& (isEqual(tile.getStackInSlot(slotOutput), stack) || tile.inventory.get(slotOutput).isEmpty());
	}

	/** Checks if a machine slot is empty **/
	public static boolean isSlotEmpty(TileEntityMachine tile, int slot) {
		return tile.inventory.get(slot).isEmpty();
	}

	/** Checks if a machine slot is full **/
	public static boolean isSlotFull(TileEntityMachine tile, int slot) {
		return tile.inventory.get(slot).getCount() == tile.inventory.get(slot).getMaxStackSize();
	}
}
