package gtclassic.container;

import gtclassic.tile.GTTileAlloySmelter;
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

public class GTContainerAlloySmelter extends ContainerTileComponent<GTTileAlloySmelter> {

	public static Box2D machineProgressBox = new Box2D(77, 24, 20, 18); // the progress bar and size
	public static Vec2i machineProgressPos = new Vec2i(176, 0); // where the overlay is

	public static Vec2i[] slots = new Vec2i[] {
		new Vec2i(35, 25), new Vec2i(53, 25), new Vec2i(107, 25)
	};

	public GTContainerAlloySmelter(InventoryPlayer player, GTTileAlloySmelter tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, slots[0].getX(), slots[1].getY(), null));// main slot
		this.addSlotToContainer(new SlotCustom(tile, 1, slots[0].getX(), slots[1].getY(), null)); // second slot
		this.addSlotToContainer(new SlotDischarge(tile, Integer.MAX_VALUE, 3, 80, 63)); // battery
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, slots[0].getX(), slots[1].getY())); // output
		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerAlloySmelter.machineProgressBox,
				GTContainerAlloySmelter.machineProgressPos));
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