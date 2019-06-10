package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.tile.GTTileBufferSmall;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerBufferSmall extends ContainerTileComponent<GTTileBufferSmall> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/buffersmall.png");

	public GTContainerBufferSmall(InventoryPlayer player, GTTileBufferSmall tile) {
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 80, 23));
		this.addPlayerInventory(player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
		gui.dissableInvName();
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
