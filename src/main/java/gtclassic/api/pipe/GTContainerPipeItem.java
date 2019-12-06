package gtclassic.api.pipe;

import gtclassic.GTMod;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerPipeItem extends ContainerTileComponent<GTTilePipeItemBase> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/single.png");

	public GTContainerPipeItem(InventoryPlayer player, GTTilePipeItemBase tile) {
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 80, 23));
		this.addPlayerInventory(player);
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
