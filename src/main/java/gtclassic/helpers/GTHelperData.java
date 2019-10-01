package gtclassic.helpers;

import ic2.core.block.base.tile.TileEntityElecMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;

public class GTHelperData {

	/** Reads an inventory list from NBT**/
	public static void readFromNBT(NBTTagCompound nbt, TileEntityElecMachine tile) {
		NBTTagList list = nbt.getTagList("Items", 10);
		tile.inventory = NonNullList.withSize(tile.slotCount, ItemStack.EMPTY);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound data = list.getCompoundTagAt(i);
			int slot = data.getInteger("Slot");
			if (slot >= 0 && slot < tile.slotCount) {
				tile.inventory.set(slot, new ItemStack(data));
			}
		}
	}

	/** Saves a tiles inventory to NBT with a slot max **/
	public static void writeToNBT(NBTTagCompound nbt, TileEntityElecMachine tile, int slotMax) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < tile.slotCount; ++i) {
			if (i <= slotMax) {
				NBTTagCompound data = new NBTTagCompound();
				((ItemStack) tile.inventory.get(i)).writeToNBT(data);
				data.setInteger("Slot", i);
				list.appendTag(data);
			}
		}
		nbt.setTag("Items", list);
	}
}
