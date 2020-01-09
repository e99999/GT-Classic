package gtclassic.common.container;

import gtclassic.GTMod;
import gtclassic.api.gui.GTGuiCompFluidTank;
import gtclassic.common.gui.GTGuiCompBuffer;
import gtclassic.common.tile.GTTileBufferFluid;
import gtclassic.common.util.GTFilterFluidCapability;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerBufferFluid extends ContainerTileComponent<GTTileBufferFluid> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/bufferfluid.png");

	public GTContainerBufferFluid(InventoryPlayer player, GTTileBufferFluid tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 80, 17, new GTFilterFluidCapability()));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 1, 80, 53));
		this.addSlotToContainer(new SlotDisplay(tile, 2, 59, 42));
		this.addComponent(new GTGuiCompFluidTank(tile.getTankInstance()));
		this.addComponent(new GTGuiCompBuffer(tile, player));
		this.addPlayerInventory(player, 0, 0);
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
		return 3;
	}
}
