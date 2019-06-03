package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.tile.GTTileSmallChest;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerSmallChest extends ContainerTileComponent<GTTileSmallChest> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/smallchest.png");

	public GTContainerSmallChest(InventoryPlayer player, GTTileSmallChest tile) {
		super(tile);
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new SlotBase(tile, x + y * 9, 8 + x * 18, 18 + y * 18));
			}
		}
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
		return 27;
	}
}
