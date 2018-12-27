package gtclassic.block.container;

import gtclassic.GTItems;
import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import gtclassic.util.gui.GTGuiCompFusion;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.BasicItemFilter;
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

public class GTContainerFusionComputer extends ContainerTileComponent<GTTileEntityFusionComputer> {

	public static Box2D machineProgressBox = new Box2D(110, 35, 20, 16);
	public static Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerFusionComputer(InventoryPlayer player, GTTileEntityFusionComputer tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 88, 26, null));// main slot
		this.addSlotToContainer(new SlotCustom(tile, 1, 88, 44, new BasicItemFilter(GTItems.dueterium)));// second slot
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 148, 35)); // output

		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerFusionComputer.machineProgressBox,
				GTContainerFusionComputer.machineProgressPos));

		this.addComponent(new GTGuiCompFusion(tile));
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