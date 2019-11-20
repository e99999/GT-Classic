package gtclassic.common.container;

import gtclassic.common.tile.GTTileDisassembler;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerDisassembler extends ContainerTileComponent<GTTileDisassembler>{
	
	public static final Box2D machineProgressBox = new Box2D(78, 23, 20, 18);
	public static final Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerDisassembler(InventoryPlayer player, GTTileDisassembler tile) {
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 53, 25));
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotOutput(player.player, tile, 1 + x + y * 3, 107 + x * 18, 7 + y * 18));
			}
		}
		this.addSlotToContainer(new SlotDischarge(tile, Integer.MAX_VALUE, 10, 8, 62));
		this.addPlayerInventory(player);
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
