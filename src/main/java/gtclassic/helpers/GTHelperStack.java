package gtclassic.helpers;

import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.misc.StackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTHelperStack {

	/** Checks if a stack can merge with default stack size **/
	public static boolean canMerge(ItemStack stack, ItemStack toCompare) {
		return canMerge(stack, toCompare, 64);
	}

	/** Checks if a stack can merge **/
	public static boolean canMerge(ItemStack stack, ItemStack toCompare, int maxCount) {
		return (isEqual(stack, toCompare) && (toCompare.getCount() + stack.getCount() <= maxCount)) || toCompare.isEmpty();
	}

	/** Just an easy place for me to call this over and over **/
	public static boolean isEqual(ItemStack stack, ItemStack toCompare) {
		return StackUtil.isStackEqual(stack, toCompare, false, false);
	}

	/** Checks if a machine slot can accept a stack **/
	public static boolean canOutputStack(TileEntityMachine tile, ItemStack stack, int slot) {
		if (tile.inventory.get(slot).isEmpty()) {
			return true;
		}
		int count = tile.inventory.get(slot).getCount();
		boolean hasRoom = count < tile.inventory.get(slot).getMaxStackSize();
		return hasRoom && isEqual(tile.getStackInSlot(slot), stack);
	}

	/** Checks if a machine slot is empty **/
	public static boolean isSlotEmpty(TileEntityMachine tile, int slot) {
		return tile.inventory.get(slot).isEmpty();
	}

	/** Checks if a machine slot is full **/
	public static boolean isSlotFull(TileEntityMachine tile, int slot) {
		if (tile.inventory.get(slot).isEmpty()) {
			return false;
		}
		return tile.inventory.get(slot).getCount() == tile.inventory.get(slot).getMaxStackSize();
	}

	/** Gets the stacksize of a slot **/
	public int getSlotStackCount(TileEntityMachine tile, int slot) {
		if (tile.inventory.get(slot).isEmpty()) {
			return 0;
		}
		return tile.getStackInSlot(slot).getCount();
	}

	/** Checks if an itemstack has an oredict entry **/
	public static boolean matchOreDict(ItemStack stack, String entry) {
		if (!stack.isEmpty() && (OreDictionary.getOreIDs(stack).length > 0)) {
			for (int i = 0; i < OreDictionary.getOreIDs(stack).length; i++) {
				if (OreDictionary.getOreName(OreDictionary.getOreIDs(stack)[i]).contains(entry)) {
					return true;
				}
			}
		}
		return false;
	}
}
