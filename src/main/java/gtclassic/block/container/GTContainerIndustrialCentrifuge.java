package gtclassic.block.container;

import gtclassic.GTItems;
import gtclassic.block.tileentity.GTTileEntityIndustrialCentrifuge;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.gui.GuiIC2;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerIndustrialCentrifuge extends ContainerTileComponent<GTTileEntityIndustrialCentrifuge> {

	public static Box2D machineProgressBoxRight = new Box2D(98, 38, 10, 10);
	public static Vec2i machineProgressPosRight = new Vec2i(208, 38);
	public static Box2D machineProgressBoxLeft = new Box2D(68, 38, 10, 10);
	public static Vec2i machineProgressPosLeft = new Vec2i(178, 38);
	public static Box2D machineProgressBoxUp = new Box2D(83, 23, 10, 10);
	public static Vec2i machineProgressPosUp = new Vec2i(193, 23);
	public static Box2D machineProgressBoxDown = new Box2D(83, 53, 10, 10);
	public static Vec2i machineProgressPosDown = new Vec2i(193, 53);

	public GTContainerIndustrialCentrifuge(InventoryPlayer player, GTTileEntityIndustrialCentrifuge tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 80, 35, null));
		this.addSlotToContainer(new SlotCustom(tile, 1, 50, 5, new BasicItemFilter(GTItems.glassTube)));
		this.addSlotToContainer(new SlotDischarge(tile, Integer.MAX_VALUE, 2, 32, 5)); // battery
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 50, 35));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 80, 5));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 110, 35));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 6, 80, 65));

		for (int i = 0; i < 4; ++i) {
			this.addSlotToContainer(new SlotUpgrade(tile, 7 + i, 152, 8 + i * 18));
		}

		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerIndustrialCentrifuge.machineProgressBoxRight,
				GTContainerIndustrialCentrifuge.machineProgressPosRight));
		this.addComponent(new MachineProgressComp(tile, GTContainerIndustrialCentrifuge.machineProgressBoxLeft,
				GTContainerIndustrialCentrifuge.machineProgressPosLeft));
		this.addComponent(new MachineProgressComp(tile, GTContainerIndustrialCentrifuge.machineProgressBoxUp,
				GTContainerIndustrialCentrifuge.machineProgressPosUp));
		this.addComponent(new MachineProgressComp(tile, GTContainerIndustrialCentrifuge.machineProgressBoxDown,
				GTContainerIndustrialCentrifuge.machineProgressPosDown));
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
