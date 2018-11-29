package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntitySmallBuffer;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerSmallBuffer extends ContainerTileComponent<GTTileEntitySmallBuffer> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/electricbuffersmall.png");
	
	public GTContainerSmallBuffer(InventoryPlayer player, GTTileEntitySmallBuffer tile) {
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 80, 23));
		this.addPlayerInventory(player, 0, 0);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) 
    {
    	gui.dissableInvName();
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
		return 1;
	}
	
}
