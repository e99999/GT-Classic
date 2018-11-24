package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityComputerCube;
import ic2.core.inventory.container.ContainerTileComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerComputerCube0 extends ContainerTileComponent<GTTileEntityComputerCube> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/computercube0.png");
	
	public GTContainerComputerCube0(InventoryPlayer player, GTTileEntityComputerCube tile) {
		super(tile);
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
		return 0;
	}
	
}
