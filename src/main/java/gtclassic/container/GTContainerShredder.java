package gtclassic.container;

import gtclassic.tile.GTTileShredder;
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

public class GTContainerShredder extends ContainerTileComponent<GTTileShredder> {

	public static Box2D machineProgressBox = new Box2D(60, 32, 20, 11); // the progress bar and size
	public static Vec2i machineProgressPos = new Vec2i(176, 0); // where the overlay is

	public GTContainerShredder(InventoryPlayer player, GTTileShredder tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 35, 29, null));

		this.addSlotToContainer(new SlotOutput(player.player, tile, 1, 89, 20));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 107, 20));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 125, 20));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 89, 38));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 107, 38));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 6, 125, 38));

		this.addPlayerInventory(player);

		for (int i = 0; i < 2; ++i) {
			this.addSlotToContainer(new GTSlotUpgrade(tile, 7 + i, 80 + (i * 18), 64));
		}

		this.addComponent(new MachineProgressComp(tile, GTContainerShredder.machineProgressBox,
				GTContainerShredder.machineProgressPos));
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