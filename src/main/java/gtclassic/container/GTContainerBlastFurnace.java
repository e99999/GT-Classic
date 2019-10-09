package gtclassic.container;

import gtclassic.gui.GTGuiCompMachinePower;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.util.GTSlotUpgrade;
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

public class GTContainerBlastFurnace extends ContainerTileComponent<GTTileMultiBlastFurnace> {

	public static final Box2D machineProgressBox = new Box2D(78, 23, 20, 18);
	public static final Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerBlastFurnace(InventoryPlayer player, GTTileMultiBlastFurnace tile) {
		super(tile);
		for (int y = 0; y < 2; ++y) {
			for (int x = 0; x < 2; ++x) {
				this.addSlotToContainer(new SlotCustom(tile, x + y * 2, 35 + x * 18, 17 + y * 18, tile.filter));
			}
		}
		for (int y = 0; y < 2; ++y) {
			for (int x = 0; x < 2; ++x) {
				this.addSlotToContainer(new SlotOutput(player.player, tile, 4 + x + y * 2, 107 + x * 18, 17 + y * 18));
			}
		}
		for (int i = 0; i < 2; ++i) {
			this.addSlotToContainer(new GTSlotUpgrade(tile, 8 + i, 80 + (i * 18), 62));
		}
		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, machineProgressBox, machineProgressPos));
		this.addComponent(new GTGuiCompMachinePower(tile));
		if (tile.isSimulating()) {
		IC2.platform.messagePlayer(player.player, "WARNING: this tile will be moved to be part of GTC Expansion next release");
		}
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