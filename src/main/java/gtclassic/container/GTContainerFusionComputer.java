package gtclassic.container;

import gtclassic.gui.GTGuiCompBasicString;
import gtclassic.gui.GTGuiCompFusion;
import gtclassic.tile.multi.GTTileMultiFusion;
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

public class GTContainerFusionComputer extends ContainerTileComponent<GTTileMultiFusion> {

	public static Box2D machineProgressBox = new Box2D(110, 34, 25, 17);
	public static Vec2i machineProgressPos = new Vec2i(176, 14);

	public GTContainerFusionComputer(InventoryPlayer player, GTTileMultiFusion tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 88, 17, tile.filter));
		this.addSlotToContainer(new SlotCustom(tile, 1, 88, 53, tile.filter));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 148, 35));
		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerFusionComputer.machineProgressBox, GTContainerFusionComputer.machineProgressPos));
		this.addComponent(new GTGuiCompFusion(tile));
		this.addComponent(new GTGuiCompBasicString("Fusion Computer", 88, 5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
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