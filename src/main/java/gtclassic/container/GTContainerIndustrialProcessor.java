package gtclassic.container;

import gtclassic.tile.GTTileMultiIndustrialProcessor;
import gtclassic.util.GTSlotUpgrade;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerIndustrialProcessor extends ContainerTileComponent<GTTileMultiIndustrialProcessor> {

	public static Box2D machineProgressBox = new Box2D(50, 28, 20, 11); // the progress bar and size
	public static Vec2i machineProgressPos = new Vec2i(176, 0); // where the overlay is

	public GTContainerIndustrialProcessor(InventoryPlayer player, GTTileMultiIndustrialProcessor tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 26, 16, null));// main slot
		this.addSlotToContainer(new SlotCustom(tile, 1, 26, 34, null)); // second slot
		this.addSlotToContainer(new SlotCustom(tile, 2, 26, 52, null)); // second slot
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 78, 25)); // output
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 96, 25)); // output
		this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 114, 25)); // output
		this.addSlotToContainer(new SlotOutput(player.player, tile, 6, 132, 25)); // output
		this.addPlayerInventory(player);
		
		for (int i = 0; i < 2; ++i) {
			this.addSlotToContainer(new GTSlotUpgrade(tile, 7 + i, 80 + (i * 18), 64));
		}
		
		this.addComponent(new MachineProgressComp(tile, GTContainerIndustrialProcessor.machineProgressBox,
				GTContainerIndustrialProcessor.machineProgressPos));
	}

	@Override
	public ResourceLocation getTexture() {
		return this.getGuiHolder().getGuiTexture();
	}

	@Override
	public int guiInventorySize() {
		return this.getGuiHolder().slotCount;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.getGuiHolder().canInteractWith(playerIn);
	}

}