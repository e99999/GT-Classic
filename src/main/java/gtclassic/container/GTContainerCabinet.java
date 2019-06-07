package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.tile.GTTileCabinet;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerCabinet extends ContainerTileComponent<GTTileCabinet> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/cabinet.png");

	public GTContainerCabinet(InventoryPlayer player, GTTileCabinet tile) {
		super(tile);
		for (int y = 0; y < 6; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new SlotBase(tile, x + y * 9, 8 + x * 18, 18 + y * 18));
			}
		}
		this.addPlayerInventory(player, 0, 56);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.setMaxGuiY(222);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return getGuiHolder().canInteractWith(player);
	}

	@Override
	public int guiInventorySize() {
		return 54;
	}
}
