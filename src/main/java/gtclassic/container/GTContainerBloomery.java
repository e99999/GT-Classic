package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.tile.GTTileBloomery;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerBloomery extends ContainerTileComponent<GTTileBloomery> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/bloomery.png");

	public GTContainerBloomery(InventoryPlayer player, GTTileBloomery tile) {
		super(tile);
		this.addSlotToContainer(new SlotOutput(player.player, tile, 0, 80, 23));
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
		return 1;
	}

}
