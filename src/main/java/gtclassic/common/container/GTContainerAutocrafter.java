package gtclassic.common.container;

import javax.annotation.Nullable;

import gtclassic.api.helpers.GTHelperMath;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.common.tile.GTTileAutocrafter;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotGhoest;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerAutocrafter extends ContainerTileComponent<GTTileAutocrafter> {

	GTTileAutocrafter block;

	public GTContainerAutocrafter(InventoryPlayer player, GTTileAutocrafter tile) {
		super(tile);
		this.block = tile;
		// inventory - 0-8
		for (int k = 0; k < 3; ++k) {
			for (int l = 0; l < 3; ++l) {
				this.addSlotToContainer(new SlotBase(tile, (k + l * 3), 8 + l * 18, 5 + k * 18));
			}
		}
		// container output - 9-17
		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new SlotOutput(player.player, tile, l + 9, 8 + l * 18, 60));
		}
		// crafting slots 18-26
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new SlotGhoest(tile, 18 + (j + i * 3), 64 + j * 17, 6 + i * 17));
			}
		}
		// crafting result display - 27
		this.addSlotToContainer(new SlotGhoest(tile, 27, 143, 5));
		// crafting result output - 28
		this.addSlotToContainer(new SlotOutput(player.player, tile, 28, 143, 41));
		// player inventory
		this.addPlayerInventory(player, 0, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
		gui.dissableInvName();
	}

	@Nullable
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		// GTMod.logger.info("Slot: " + slotId);
		if (GTHelperMath.within(slotId, 18, 26)) {
			ItemStack stack = player.inventory.getItemStack();
			// I need to offset the slot by -20 here because normal stack methods freak out
			this.block.inventory.set(slotId, doWeirdStackCraftingStuff(stack, slotId));
			return ItemStack.EMPTY;
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}

	// this increases a stack size if its valid to handle stack crafting
	public ItemStack doWeirdStackCraftingStuff(ItemStack stack, int slotId) {
		if (stack.isEmpty()) {
			return ItemStack.EMPTY;
		}
		ItemStack slotStack = this.block.getStackInSlot(slotId);
		if (GTHelperStack.isEqual(stack, slotStack) && slotStack.getCount() < slotStack.getMaxStackSize()) {
			return StackUtil.copyWithSize(slotStack, slotStack.getCount() + 1);
		}
		return StackUtil.copyWithSize(stack, 1);
	}

	@Override
	public ResourceLocation getTexture() {
		return this.getGuiHolder().getGuiTexture();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return getGuiHolder().canInteractWith(player);
	}

	@Override
	public int guiInventorySize() {
		return this.getGuiHolder().slotCount;
	}
}
