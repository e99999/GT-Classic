package gtclassic.container;

import javax.annotation.Nullable;

import gtclassic.tile.GTTileUUMAssembler;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.platform.registry.Ic2Items;
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
		//Slots for the stored recipes 0 - 19
		for (int y = 0; y < 4; ++y) {
			for (int x = 0; x < 5; ++x) {
				this.addSlotToContainer(new SlotDisplay(tile, x + y * 5, 9 + x * 16, 6 + y * 16));
			}
		}
		//Crafting Slots 20 - 28
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotDisplay(tile, 20 + (x + y * 3), 98 + x * 17, 6 + y * 17));
			}
		}
		//Crafting Output Slot - 29
		this.addSlotToContainer(new SlotDisplay(tile, 29, 115, 58));
		//UU Input Slot - 30
		this.addSlotToContainer(new SlotCustom(tile, 30, 152, 5, CommonFilters.uuMatter));
		//Result - 31
		this.addSlotToContainer(new SlotOutput(player.player, tile, 31, 152, 41));
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
		if ((slotId >= 20) && (slotId <= 28)) {
			this.block.setStackInSlot(slotId, this.block.getStackInSlot(slotId).isEmpty() ? StackUtil.copyWithSize(Ic2Items.uuMatter, 1) : ItemStack.EMPTY);
			this.block.updateRecipe();
			return ItemStack.EMPTY;
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
