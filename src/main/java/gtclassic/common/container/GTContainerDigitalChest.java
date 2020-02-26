package gtclassic.common.container;

import gtclassic.GTMod;
import gtclassic.common.gui.GTGuiCompDigitalChest;
import gtclassic.common.tile.GTTileDigitalChest;
import gtclassic.common.util.GTIFilters;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerDigitalChest extends ContainerTileComponent<GTTileDigitalChest> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/digitalchest.png");

	public GTContainerDigitalChest(InventoryPlayer player, GTTileDigitalChest tile) {
		super(tile);
		for (int y = 0; y < 6; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new SlotCustom(tile, x + y * 9, 8 + x * 18, 8
						+ y * 18, new GTIFilters.DigitalChestFilter()));
			}
		}
		this.addSlotToContainer(new SlotBase(tile, 54, 80, 119));
		this.addComponent(new GTGuiCompDigitalChest(tile));
		this.addPlayerInventory(player, 0, 56);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.setMaxGuiY(221);
		gui.disableName();
		gui.dissableInvName();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return getGuiHolder().canInteractWith(player);
	}

	@Override
	public int guiInventorySize() {
		return 55;
	}
}
