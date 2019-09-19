package gtclassic.container;

import java.awt.Color;

import javax.annotation.Nullable;

import gtclassic.gui.GTGuiCompBasicString;
import gtclassic.gui.GTGuiCompUUMAssembler;
import gtclassic.helpers.GTHelperStack;
import gtclassic.tile.GTTileUUMAssembler;
import gtclassic.util.GTFilterUUMAssembler;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerUUMAssembler extends ContainerTileComponent<GTTileUUMAssembler> {

	GTTileUUMAssembler block;

	public GTContainerUUMAssembler(InventoryPlayer player, GTTileUUMAssembler tile) {
		super(tile);
		block = tile;
		// Slots for the stored recipes 0 - 8
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotDisplay(tile, x + y * 3, 98 + x * 17, 6 + y * 17));
			}
		}
		// Clear Grid Slot 9
		this.addSlotToContainer(new SlotDisplay(tile, 9, 98, 58));
		// Scanning Slot - 10
		this.addSlotToContainer(new SlotDisplay(tile, 10, 115, 58));
		// Replication Selection Slot 11
		this.addSlotToContainer(new SlotDisplay(tile, 11, 132, 58));
		// UU Input Slot - 12
		this.addSlotToContainer(new SlotCustom(tile, 12, 152, 5, CommonFilters.uuMatter));
		// Result/Output Slot - 13
		this.addSlotToContainer(new SlotOutput(player.player, tile, 13, 152, 41));
		this.addComponent(new GTGuiCompBasicString("UUM-Assembler", 12, 8, Color.cyan));
		this.addComponent(new GTGuiCompUUMAssembler(tile));
		this.addPlayerInventory(player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
	}

	@Override
	public ResourceLocation getTexture() {
		return this.getGuiHolder().getGuiTexture();
	}

	@Override
	public int guiInventorySize() {
		return this.getGuiHolder().slotCount;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.getGuiHolder().canInteractWith(playerIn);
	}

	@Nullable
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		// Clear Grid Slot
		if (slotId == 9) {
			for (int i = 0; i < 9; ++i) {
				this.block.inventory.set(i, ItemStack.EMPTY);
			}
			this.block.inventory.set(11, ItemStack.EMPTY);
			this.block.updateCost();
			return ItemStack.EMPTY;
		}
		// Scanning Slot Logic
		if (slotId == 10) {
			ItemStack playerStack = StackUtil.copyWithSize(player.inventory.getItemStack(), 1);
			if (GTFilterUUMAssembler.matches(playerStack)) {
				for (int i = 0; i < 9; ++i) {
					// If the Stack is saved already then break
					ItemStack savedStack = this.block.inventory.get(i);
					if (GTHelperStack.isEqual(playerStack, savedStack)) {
						break;
					}
					// If there is an emtpy slot attempt to save the scanned item
					if (savedStack.isEmpty()) {
						this.block.setStackInSlot(i, playerStack);
						break;
					}
				}
				return ItemStack.EMPTY;
			}
		}
		// Replication Selection Logic
		if (slotId >= 0 && slotId <= 9) {
			if (!this.block.getStackInSlot(slotId).isEmpty()) {
				this.block.setStackInSlot(11, this.block.getStackInSlot(slotId).copy());
				this.block.updateCost();
			}
			return ItemStack.EMPTY;
		}
		if (slotId == 11) {
			this.block.inventory.set(11, ItemStack.EMPTY);
			this.block.updateCost();
			return ItemStack.EMPTY;
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
