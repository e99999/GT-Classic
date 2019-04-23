package gtclassic.container;

import gtclassic.GTItems;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerIndustrialCentrifuge extends ContainerTileComponent<GTTileIndustrialCentrifuge> {

	public static Box2D machineProgressBoxRight = new Box2D(62, 29, 10, 10);
	public static Vec2i machineProgressPosRight = new Vec2i(176, 0);

	public GTContainerIndustrialCentrifuge(InventoryPlayer player, GTTileIndustrialCentrifuge tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 26, 26, null));
		this.addSlotToContainer(new SlotCustom(tile, 1, 44, 26, new BasicItemFilter(GTItems.testTube)));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 74, 26));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 92, 26));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 110, 26));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 128, 26));
		this.addSlotToContainer(new SlotDischarge(tile, Integer.MAX_VALUE, 6, 80, 62));

		for (int i = 0; i < 4; ++i) {
			this.addSlotToContainer(new SlotUpgrade(tile, 7 + i, 152, 8 + i * 18));
		}

		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerIndustrialCentrifuge.machineProgressBoxRight,
				GTContainerIndustrialCentrifuge.machineProgressPosRight));
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
