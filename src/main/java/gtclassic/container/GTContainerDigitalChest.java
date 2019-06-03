package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.gui.GTGuiCompQuantumChest;
import gtclassic.tile.GTTileDigitalChest;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerDigitalChest extends ContainerTileComponent<GTTileDigitalChest> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/digitalchest.png");

	public GTContainerDigitalChest(InventoryPlayer player, GTTileDigitalChest tile) {
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 26, 30));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 1, 134, 30));
		this.addSlotToContainer(new SlotDisplay(tile, 2, 104, 41));
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
}
