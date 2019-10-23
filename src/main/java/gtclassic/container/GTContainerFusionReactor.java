package gtclassic.container;

import gtclassic.gui.GTGuiCompBasicString;
import gtclassic.gui.GTGuiCompFusion;
import gtclassic.gui.GTGuiCompMachinePower;
import gtclassic.tile.multi.GTTileMultiFusionReactor;
import ic2.core.IC2;
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

public class GTContainerFusionReactor extends ContainerTileComponent<GTTileMultiFusionReactor> {

	public static Box2D machineProgressBox = new Box2D(110, 34, 25, 17);
	public static Vec2i machineProgressPos = new Vec2i(176, 14);

	public GTContainerFusionReactor(InventoryPlayer player, GTTileMultiFusionReactor tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 88, 17, tile.filter));
		this.addSlotToContainer(new SlotCustom(tile, 1, 88, 53, tile.filter));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 148, 35));
		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerFusionReactor.machineProgressBox, GTContainerFusionReactor.machineProgressPos));
		this.addComponent(new GTGuiCompFusion(tile));
		this.addComponent(new GTGuiCompBasicString("Fusion Reactor", 92, 5));
		this.addComponent(new GTGuiCompMachinePower(tile, 112, 52));
		if (tile.isSimulating()) {
			IC2.platform.messagePlayer(player.player, "Note: the Fusion Reactor has had some changes");
			IC2.platform.messagePlayer(player.player, "Power now goes directly into the tile, left, right, up, or down");
			IC2.platform.messagePlayer(player.player, "Power now comes out of the front or back!");
		}
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