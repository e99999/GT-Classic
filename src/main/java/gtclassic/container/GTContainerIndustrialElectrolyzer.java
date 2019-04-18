package gtclassic.container;

import gtclassic.gui.GTGuiCompVerticalProgress;
import gtclassic.tile.GTTileIndustrialElectrolyzer;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerIndustrialElectrolyzer extends ContainerTileComponent<GTTileIndustrialElectrolyzer> {

	public static Box2D machineProgressBox = new Box2D(83, 34, 10, 10); // the progress bar and size
	public static Vec2i machineProgressPos = new Vec2i(176, 0); // where the overlay is

	public GTContainerIndustrialElectrolyzer(InventoryPlayer player, GTTileIndustrialElectrolyzer tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 62, 46, null));
		this.addSlotToContainer(new SlotCustom(tile, 1, 80, 46, null));
		this.addSlotToContainer(new SlotCustom(tile, 2, 98, 46, null));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 50, 16));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 70, 16));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 90, 16));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 6, 110, 16));
		this.addSlotToContainer(new SlotDischarge(tile, Integer.MAX_VALUE, 7, 8, 62));
		
		for (int i = 0; i < 2; ++i) {
			this.addSlotToContainer(new SlotUpgrade(tile, 8 + i, 152, 26 + i * 18));
		}
		
		this.addPlayerInventory(player);
		this.addComponent(new GTGuiCompVerticalProgress(tile, GTContainerIndustrialElectrolyzer.machineProgressBox,
				GTContainerIndustrialElectrolyzer.machineProgressPos));
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