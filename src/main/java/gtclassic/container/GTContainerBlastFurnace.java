package gtclassic.container;

import gtclassic.tile.GTTileMultiBlastFurnace;
import gtclassic.util.GTSlotUpgrade;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerBlastFurnace extends ContainerTileComponent<GTTileMultiBlastFurnace> {

	public static Box2D machineProgressBox = new Box2D(58, 28, 20, 11); // the progress bar and size
	public static Vec2i machineProgressPos = new Vec2i(176, 0); // where the overlay is

	public GTContainerBlastFurnace(InventoryPlayer player, GTTileMultiBlastFurnace tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 34, 16, null));// main slot
		this.addSlotToContainer(new SlotCustom(tile, 1, 34, 34, null)); // second slot
		this.addSlotToContainer(new SlotCustom(tile, 2, 34, 52, null)); // second slot
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 86, 25)); // output
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 104, 25)); // output
		this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 122, 25)); // output

		for (int i = 0; i < 2; ++i) {
			this.addSlotToContainer(new GTSlotUpgrade(tile, 6 + i, 80 + (i * 18), 64));
		}

		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerBlastFurnace.machineProgressBox,
				GTContainerBlastFurnace.machineProgressPos));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.dissableInvName();
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