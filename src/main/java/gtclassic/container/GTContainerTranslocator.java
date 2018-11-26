package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityTranslocator;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerTranslocator extends ContainerTileComponent<GTTileEntityTranslocator> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/translocator.png");
	
	public GTContainerTranslocator(InventoryPlayer player, GTTileEntityTranslocator tile) 
	{
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 63, 6));
		this.addSlotToContainer(new SlotBase(tile, 1, 80, 6));
		this.addSlotToContainer(new SlotBase(tile, 2, 97, 6));
		this.addSlotToContainer(new SlotBase(tile, 3, 63, 23));
		this.addSlotToContainer(new SlotBase(tile, 4, 80, 23));
		this.addSlotToContainer(new SlotBase(tile, 5, 97, 23));
		this.addSlotToContainer(new SlotBase(tile, 6, 63, 40));
		this.addSlotToContainer(new SlotBase(tile, 7, 80, 40));
		this.addSlotToContainer(new SlotBase(tile, 8, 97, 40));
		this.addPlayerInventory(player, 0, 0);
	}
	
	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
    public boolean canInteractWith(EntityPlayer player)
    {
		return getGuiHolder().canInteractWith(player);
    }

	@Override
	public int guiInventorySize() {
		return 9;
	}
	
}
