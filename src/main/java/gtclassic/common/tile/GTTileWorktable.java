package gtclassic.common.tile;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import gtclassic.common.container.GTContainerWorktable;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.InventoryHandler;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GTTileWorktable extends GTTileBaseRecolorableTile implements IHasGui, INetworkClientTileEntityEventListener {

	public NonNullList<ItemStack> craftingInventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	public boolean inUse = false;

	public GTTileWorktable() {
		super(32);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Crafting", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound data = list.getCompoundTagAt(i);
			int slot = data.getInteger("Slot");
			if (slot >= 0 && slot < 9) {
				craftingInventory.set(slot, new ItemStack(data));
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.craftingInventory.size(); ++i) {
			if (i <= 9) {
				NBTTagCompound data = new NBTTagCompound();
				((ItemStack) craftingInventory.get(i)).writeToNBT(data);
				data.setInteger("Slot", i);
				list.appendTag(data);
			}
		}
		nbt.setTag("Crafting", list);
		return nbt;
	}

	@Override
	public void onNetworkEvent(EntityPlayer player, int event) {
		if (event == 2) {
			for (int i = 0; i < player.inventory.mainInventory.size(); i++){
				if (craftingInventory.isEmpty()){
					break;
				}
				for (Iterator<ItemStack> j = craftingInventory.iterator(); j.hasNext();){
					ItemStack stack = j.next();
					int count = stack.getCount();
					if (GTHelperStack.canOutputStack(player, stack, i)){
						if (player.inventory.mainInventory.get(i).isEmpty()){
							player.inventory.mainInventory.set(i, stack);
						} else {
							player.inventory.mainInventory.get(i).grow(count);
						}
						stack.shrink(count);
					}
				}

			}

		}
		if (event == 1) {
			for (int i = 1; i < 17; i++){
				if (craftingInventory.isEmpty()){
					break;
				}
				for (Iterator<ItemStack> j = craftingInventory.iterator(); j.hasNext();){
					ItemStack stack = j.next();
					int count = stack.getCount();
					if (GTHelperStack.canOutputStack(this, stack, i)){
						if (this.getStackInSlot(i).isEmpty()){
							this.setStackInSlot(i, stack);
						} else {
							this.getStackInSlot(i).grow(count);
						}
						stack.shrink(count);
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerWorktable(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		this.inUse = false;
		this.setActive(false);
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	@Override
	public List<ItemStack> getInventoryDrops() {
		List<ItemStack> drops = new ArrayList<>();
		for (ItemStack stack : craftingInventory) {
			if (!stack.isEmpty()) {
				drops.add(stack);
			}
		}
		for (ItemStack stack : this.inventory) {
			if (!stack.isEmpty()) {
				drops.add(stack);
			}
		}
		return drops;
	}

	@Override
	public Block getBlockDrop() {
		return GTBlocks.tileWorktable;
	}
}
