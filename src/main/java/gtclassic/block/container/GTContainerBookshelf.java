package gtclassic.block.container;

import gtclassic.GTClassic;
import gtclassic.block.tileentity.GTTileEntityBookshelf;
import gtclassic.util.GTBookshelfFilter;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerBookshelf extends ContainerTileComponent<GTTileEntityBookshelf> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/bookshelf.png");

	public GTContainerBookshelf(InventoryPlayer player, GTTileEntityBookshelf tile) {
		super(tile);

		this.addSlotToContainer(new SlotCustom(tile, 0, 52, 24, new GTBookshelfFilter(tile)));
		this.addSlotToContainer(new SlotCustom(tile, 1, 70, 24, new GTBookshelfFilter(tile)));
		this.addSlotToContainer(new SlotCustom(tile, 2, 88, 24, new GTBookshelfFilter(tile)));
		this.addSlotToContainer(new SlotCustom(tile, 3, 106, 24, new GTBookshelfFilter(tile)));

		this.addSlotToContainer(new SlotCustom(tile, 4, 52, 42, new GTBookshelfFilter(tile)));
		this.addSlotToContainer(new SlotCustom(tile, 5, 70, 42, new GTBookshelfFilter(tile)));
		this.addSlotToContainer(new SlotCustom(tile, 6, 88, 42, new GTBookshelfFilter(tile)));
		this.addSlotToContainer(new SlotCustom(tile, 7, 106, 42, new GTBookshelfFilter(tile)));

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
		return 8;
	}

}
