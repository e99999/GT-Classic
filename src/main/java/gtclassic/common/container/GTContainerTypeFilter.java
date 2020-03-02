package gtclassic.common.container;

import gtclassic.GTMod;
import gtclassic.common.gui.GTGuiCompTypeFilter;
import gtclassic.common.tile.GTTileTypeFilter;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerTypeFilter extends ContainerTileComponent<GTTileTypeFilter> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/typefilter.png");
	GTTileTypeFilter tile;

	public GTContainerTypeFilter(InventoryPlayer player, GTTileTypeFilter tile) {
		super(tile);
		this.tile = tile;
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotCustom(tile, x + y * 3, 98 + x * 18, 5 + y * 18, this.tile.filter));
			}
		}
		this.addComponent(new GTGuiCompTypeFilter(tile, player));
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
		return getGuiHolder().slotCount;
	}
}
