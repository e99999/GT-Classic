package gtclassic.common.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import gtclassic.common.container.GTContainerWorktable;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileWorktable extends TileEntityMachine
		implements IHasGui, IGTRecolorableStorageTile, IGTItemContainerTile {

	public NonNullList<ItemStack> craftingInventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	public boolean inUse = false;
	@NetworkField(index = 9)
	public int color;

	public GTTileWorktable() {
		super(26);
		this.color = 16383998;
		this.addNetworkFields(new String[] { "color" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey("color")) {
			this.color = nbt.getInteger("color");
		} else {
			this.color = 16383998;
		}
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
		nbt.setInteger("color", this.color);
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
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
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
		// handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		// for (int i = 9; i < 25; i++) {
		// handler.registerDefaultSlotAccess(AccessRule.Both, i);
		// handler.registerDefaultSlotsForSide(RotationList.ALL, i);
		// handler.registerSlotType(SlotType.Input, i);
		// handler.registerSlotType(SlotType.Output, i);
		// }
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		this.inUse = false;
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals("color")) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}

	@Override
	public void setTileColor(int color) {
		this.color = color;
	}

	@Override
	public Color getTileColor() {
		return new Color(this.color);
	}

	@Override
	public boolean isColored() {
		return this.color != 16383998;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> drops = new ArrayList<>();
		ItemStack block = GTMaterialGen.get(GTBlocks.tileWorktable);
		if (this.isColored()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(block);
			nbt.setInteger("color", this.color);
		}
		drops.addAll(getInventoryDrops());
		drops.add(block);
		return drops;
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
}
