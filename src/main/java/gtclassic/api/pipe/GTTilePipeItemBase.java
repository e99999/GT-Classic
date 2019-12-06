package gtclassic.api.pipe;

import java.util.ArrayList;
import java.util.List;

import gtclassic.common.GTLang;
import ic2.core.RotationList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.IHasHandler;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.math.MathUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class GTTilePipeItemBase extends GTTilePipeBase implements IItemContainer, IHasInventory, IHasHandler, IHasGui {

	protected InventoryHandler handler = new InventoryHandler(this);
	public NonNullList<ItemStack> inventory;
	public int slotCount;

	public GTTilePipeItemBase() {
		this.slotCount = 1;
		this.inventory = NonNullList.withSize(this.slotCount, ItemStack.EMPTY);
	    this.addSlots(this.handler);
	    this.handler.validateSlots();
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.PIPE_LANG;
	}

	protected void addSlots(InventoryHandler handler) {
		int[] array = MathUtil.fromTo(0, this.inventory.size());
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, array);
		handler.registerDefaultSlotsForSide(RotationList.ALL, array);
		handler.registerSlotType(SlotType.Storage, array);
	}

	@Override
	public boolean canConnect(TileEntity tile, EnumFacing dir) {
		if (tile == null) {
			return false;
		}
		if (tile instanceof GTTilePipeItemBase) {
			return true;
		}
		return tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir);
	}

	@Override
	public InventoryHandler getHandler() {
		return this.handler;
	}

	public boolean canUpdate() {
		return this.isSimulating();
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<>(this.inventory.size());
		for (int i = 0; i < this.inventory.size(); ++i) {
			ItemStack stack = (ItemStack) this.inventory.get(i);
			if (!stack.isEmpty()) {
				list.add(stack);
			}
		}
		InventoryHandler handler = this.getHandler();
		if (handler != null) {
			IHasInventory inv = handler.getUpgradeSlots();
			for (int i = 0; i < inv.getSlotCount(); ++i) {
				ItemStack result = inv.getStackInSlot(i);
				if (!result.isEmpty()) {
					list.add(result);
				}
			}
		}
		return list;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.handler.readFromNBT(nbt.getCompoundTag("HandlerNBT"));
		this.inventory = NonNullList.withSize(this.slotCount, ItemStack.EMPTY);
		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound data = list.getCompoundTagAt(i);
			byte byte0 = data.getByte("Slot");
			if (byte0 >= 0 && byte0 < this.inventory.size()) {
				this.inventory.set(byte0, new ItemStack(data));
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.handler.writeToNBT(this.getTag(nbt, "HandlerNBT"));
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.inventory.size(); ++i) {
			ItemStack stack = this.inventory.get(i);
			if (!stack.isEmpty()) {
				NBTTagCompound data = new NBTTagCompound();
				data.setByte("Slot", (byte) i);
				stack.writeToNBT(data);
				list.appendTag(data);
			}
		}
		nbt.setTag("Items", list);
		return nbt;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (getHandler() == null) {
				return false;
			}
			return this.handler.hasInventory(facing);
		}
		return super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (getHandler() == null) {
				return null;
			}
			return (T) this.handler.getInventory(facing);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory.get(slot);
	}

	@Override
	public int getSlotCount() {
		return this.slotCount;
	}

	@Override
	public int getMaxStackSize(int slot) {
		return 64;
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerPipeItem(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer player) {
	}
}
