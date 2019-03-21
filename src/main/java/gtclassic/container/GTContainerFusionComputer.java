package gtclassic.container;

import gtclassic.tile.GTTileMultiFusionComputer;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerFusionComputer extends ContainerTileComponent<GTTileMultiFusionComputer> {

	public static Box2D machineProgressBox = new Box2D(69, 35, 25, 17);
	public static Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerFusionComputer(InventoryPlayer player, GTTileMultiFusionComputer tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 46, 26, null));
		this.addSlotToContainer(new SlotCustom(tile, 1, 46, 44, null));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 106, 35));

		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerFusionComputer.machineProgressBox,
				GTContainerFusionComputer.machineProgressPos));
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