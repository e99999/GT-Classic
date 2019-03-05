package gtclassic.container;

import gtclassic.tile.GTTileAssemblyLine;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerAssemblyLine extends ContainerTileComponent<GTTileAssemblyLine> {

	public static Box2D machineProgressBox = new Box2D(88, 24, 20, 18); // the progress bar and size
	public static Vec2i machineProgressPos = new Vec2i(176, 0); // where the overlay is

	public GTContainerAssemblyLine(InventoryPlayer player, GTTileAssemblyLine tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 26, 25, null));// main slot
		this.addSlotToContainer(new SlotCustom(tile, 1, 44, 25, null)); // second slot
		this.addSlotToContainer(new SlotCustom(tile, 2, 62, 25, null)); // third slot
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 116, 25)); // output
		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerAssemblyLine.machineProgressBox,
				GTContainerAssemblyLine.machineProgressPos));
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