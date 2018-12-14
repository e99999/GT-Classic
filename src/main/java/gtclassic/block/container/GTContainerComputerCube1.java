package gtclassic.block.container;

import gtclassic.GTClassic;
import gtclassic.block.tileentity.GTTileEntityComputerCube;
import gtclassic.util.guicomponent.GTGuiCompComputerCube;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerComputerCube1 extends ContainerTileComponent<GTTileEntityComputerCube> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/computercube1.png");

	public GTContainerComputerCube1(InventoryPlayer player, GTTileEntityComputerCube tile) {
		super(tile);
		this.addPlayerInventory(player, 0, 0);
		this.addComponent(new GTGuiCompComputerCube(tile));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
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
		return 0;
	}

}
