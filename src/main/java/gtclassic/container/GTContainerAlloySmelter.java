package gtclassic.container;

import gtclassic.tileentity.GTTileEntityAlloySmelter;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerAlloySmelter extends ContainerTileComponent<GTTileEntityAlloySmelter> {
    public static Box2D machineProgressBox = new Box2D(78, 43, 20, 18);
    public static Vec2i machineProgressPos = new Vec2i(176, 0);

    public GTContainerAlloySmelter(InventoryPlayer player, GTTileEntityAlloySmelter tile){
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 35, 25, null));
        this.addSlotToContainer(new SlotCustom(tile, 1, 53, 25, null));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 2, 80, 63));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 107, 25));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 125, 25));
        this.addPlayerInventory(player);
        this.addComponent(new MachineProgressComp(tile, GTContainerAlloySmelter.machineProgressBox, GTContainerAlloySmelter.machineProgressPos));
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
