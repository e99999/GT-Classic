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

	public static final Box2D machineProgressBox = new Box2D(78, 29, 20, 11);
	public static final Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerShredder(InventoryPlayer player, GTTileShredder tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 35, 25, null));
		this.addSlotToContainer(new SlotCustom(tile, 1, 53, 25, null));

		for (int y = 0; y < 2; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotOutput(player.player, tile, 2 + x + y * 3, 107 + x * 18, 17 + y * 18));
			}
		}

		this.addPlayerInventory(player);

		for (int i = 0; i < 2; ++i) {
			this.addSlotToContainer(new GTSlotUpgrade(tile, 8 + i, 80 + (i * 18), 62));
		}

		this.addComponent(new MachineProgressComp(tile, new Box2D(78, 28, 20, 11), GTContainerShredder.machineProgressPos));
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