package gtclassic.common.container;

import gtclassic.GTMod;
import gtclassic.api.gui.GTGuiCompFluidTank;
import gtclassic.common.tile.datanet.GTTileQuantumTank;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerQuantumTank extends ContainerTileComponent<GTTileQuantumTank> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/quantumtank.png");

	public GTContainerQuantumTank(InventoryPlayer player, GTTileQuantumTank tile) {
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 1, 80, 53));
		this.addSlotToContainer(new SlotDisplay(tile, 2, 59, 42));
		this.addComponent(new GTGuiCompFluidTank(tile.getTankInstance()));
		this.addPlayerInventory(player, 0, 0);
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
