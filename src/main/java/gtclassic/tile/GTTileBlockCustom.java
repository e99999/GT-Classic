package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTTileBlockCustom extends TileEntityBlock implements IItemContainer {

	/*
	 * This class is extremely simple and designed todo one thing only. Store the
	 * integer and itemstack info from a GT ItemBlock instance whenever the block is
	 * placed.
	 */
	private ItemStack drop = ItemStack.EMPTY;

	public GTTileBlockCustom() {
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("drop", drop.writeToNBT(new NBTTagCompound()));
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.drop = new ItemStack(nbt.getCompoundTag("drop"));
	}

	public ItemStack getItem() {
		return this.drop;
	}

	public void setItem(ItemStack item) {
		this.drop = item.copy();
	}

	@Override
	public List<ItemStack> getDrops() {
		ArrayList<ItemStack> drops = new ArrayList<>();
		Item item = drop.getItem();
		drops.add(new ItemStack(item, 1).copy());
		return drops;
	}
}
