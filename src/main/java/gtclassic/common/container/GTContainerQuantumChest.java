package gtclassic.common.container;

import javax.annotation.Nullable;

import gtclassic.GTMod;
import gtclassic.common.gui.GTGuiCompQuantumChest;
import gtclassic.common.tile.GTTileQuantumChest;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTContainerQuantumChest extends ContainerTileComponent<GTTileQuantumChest> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/quantumchest.png");
	InventoryPlayer activeplayer;

	public GTContainerQuantumChest(InventoryPlayer player, GTTileQuantumChest tile) {
		super(tile);
		this.activeplayer = player;
		this.addSlotToContainer(new SlotCustom(tile, 0, 80, 17, tile.filter));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 1, 80, 53));
		this.addSlotToContainer(new SlotDisplay(tile, 2, 59, 42));
		this.addComponent(new GTGuiCompQuantumChest(tile));
		this.addPlayerInventory(player, 0, 0);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return getGuiHolder().canInteractWith(player);
	}

	@Override
	public int guiInventorySize() {
		return 3;
	}

	@Nullable
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		if (slotId == 2) {
			return ItemStack.EMPTY;
		}
		if (slotId == 1) {
			this.activeplayer.player.openContainer.detectAndSendChanges();
			return super.slotClick(slotId, dragType, clickTypeIn, player);
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
