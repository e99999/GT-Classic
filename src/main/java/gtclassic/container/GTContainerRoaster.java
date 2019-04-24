package gtclassic.container;

import gtclassic.tile.GTTileRoaster;
import gtclassic.util.GTSlotUpgrade;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerRoaster extends ContainerTileComponent<GTTileRoaster> {

	public static Box2D machineProgressBox = new Box2D(58, 28, 21, 10);
	public static Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerRoaster(InventoryPlayer player, GTTileRoaster tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 15, 25, null));
		this.addSlotToContainer(new SlotCustom(tile, 1, 33, 25, null));

		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 86, 25));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 104, 25));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 122, 25));

		this.addSlotToContainer(new SlotDischarge(tile, Integer.MAX_VALUE, 5, 80, 63));

		for (int i = 0; i < 2; ++i) {
			this.addSlotToContainer(new GTSlotUpgrade(tile, 4 + i, 152, 26 + i * 18));
		}

		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerRoaster.machineProgressBox,
				GTContainerRoaster.machineProgressPos));
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