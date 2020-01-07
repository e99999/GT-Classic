package gtclassic.common.tile;

import gtclassic.GTMod;
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
import java.util.List;

public class GTTileWorktable extends GTTileBaseRecolorableTile implements IHasGui, INetworkClientTileEntityEventListener {

	public NonNullList<ItemStack> craftingInventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	public boolean inUse = false;

	public GTTileWorktable() {
		super(23);
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
			for (int j = 0; j < 9; j++){
				ItemStack stack = craftingInventory.get(j);
				if (stack.isEmpty()){
					continue;
				}
				player.inventory.addItemStackToInventory(stack);
			}

		}
		if (event == 1) {
			if (inUse){
				for (int j = 0; j < 9; j++){
					ItemStack stack = craftingInventory.get(j);
					if (stack.isEmpty()){
						continue;
					}
					insert(j, stack);
				}
			}
		}
	}

	static final int FIRST_SLOT = 1;
	static final int LAST_SLOT = 16;

	ItemStack insert(int slot, ItemStack aStack) {
		ItemStack craftingStack = aStack;
		int curSlot = FIRST_SLOT;
		int maxStackSize = craftingStack.getMaxStackSize();
		int count = craftingStack.getCount();
		int room;
		int toDeliver;

		// Try to first insert into same ItemStacks
		while (curSlot <= LAST_SLOT && count > 0) {
			ItemStack slotStack = this.getStackInSlot(curSlot);
			if (craftingStack.isEmpty()){
				count = 0;
			}
			if (GTHelperStack.isEqual(craftingStack, slotStack) && slotStack.getCount() < maxStackSize) {
				room = maxStackSize - slotStack.getCount();
				toDeliver = Math.min(room, count);
				GTMod.logger.info("todeliver: " + toDeliver);
				slotStack.grow(toDeliver);
				this.setStackInSlot(curSlot, slotStack);
				craftingStack.grow(-toDeliver);
				craftingInventory.set(slot, craftingStack);
				if (count >= room){
					count -= room;
				}
			}
			curSlot++;
		}

		curSlot = FIRST_SLOT;
		// Try to deliver into empty slot
		while (curSlot <= LAST_SLOT && count > 0) {
			GTMod.logger.info("slot " + curSlot + " empty? " + this.getStackInSlot(curSlot).isEmpty());
			if (this.getStackInSlot(curSlot).isEmpty()) {
				GTMod.logger.info("got to empty slot");
				GTMod.logger.info("count: " + count);
				this.setStackInSlot(curSlot, craftingStack.copy());
				craftingStack.grow(- count);
				craftingInventory.set(slot, craftingStack);
				count = 0;
			}
			curSlot++;
		}
		return craftingStack;
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
