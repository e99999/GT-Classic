package gtclassic.common.container;

import gtclassic.common.tile.GTTileBath;
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

public class GTContainerBath extends ContainerTileComponent<GTTileBath> {

	public static final Box2D machineProgressBox = new Box2D(78, 25, 20, 18);
	public static final Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerBath(InventoryPlayer player, GTTileBath tile) {
		super(tile);
		for (int y = 0; y < 2; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotCustom(tile, x + y * 3, 17 + x * 18, 17 + y * 18, null));
			}
		}
		for (int y = 0; y < 2; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotOutput(player.player, tile, 6 + x + y * 3, 107 + x * 18, 17 + y * 18));
			}
		}
		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerBath.machineProgressBox, GTContainerBath.machineProgressPos));
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
