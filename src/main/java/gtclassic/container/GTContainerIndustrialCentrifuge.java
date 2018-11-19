package gtclassic.container;

import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import ic2.api.classic.tile.IMachine;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.CommonFilters;
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

public class GTContainerIndustrialCentrifuge extends ContainerTileComponent<GTTileEntityIndustrialCentrifuge> {
	public static Box2D machineProgressBox = new Box2D(78, 34, 20, 18);
    public static Vec2i machineProgressPos = new Vec2i(176, 0);

    public GTContainerIndustrialCentrifuge(InventoryPlayer player, GTTileEntityIndustrialCentrifuge tile){
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 36, 35, null)); //main input
        this.addSlotToContainer(new SlotCustom(tile, 1, 54, 35, CommonFilters.EmptyCell)); //cell input
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 2, 80, 62)); //battery
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 108, 26)); //output1
        this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 126, 26)); //etc
        this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 108, 44));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 6, 126, 44));

        for(int i = 0; i < 4; ++i)
        {
            this.addSlotToContainer(new SlotUpgrade((IMachine) tile, 7 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineProgressComp(tile, GTContainerIndustrialCentrifuge.machineProgressBox, GTContainerIndustrialCentrifuge.machineProgressPos));
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
