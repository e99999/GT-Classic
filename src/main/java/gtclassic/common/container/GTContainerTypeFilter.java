package gtclassic.common.container;

import javax.annotation.Nullable;

import gtclassic.GTMod;
import gtclassic.common.gui.GTGuiCompBuffer;
import gtclassic.common.tile.GTTileTypeFilter;
import ic2.core.IC2;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDisplay;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
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
		this.addSlotToContainer(new SlotDisplay(tile, 9, 35, 23));
		this.addComponent(new GTGuiCompBuffer(tile, player));
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
		return 10;
	}

	@Nullable
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		if ((slotId == 9)) {
			this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 3);
			if (!IC2.platform.isRendering()) {
				IC2.platform.messagePlayer(player, "Filter: " + this.tile.getCurrentFilter());
			}
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
