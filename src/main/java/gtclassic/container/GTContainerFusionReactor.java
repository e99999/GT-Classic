package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityFusionReactor;
import gtclassic.util.GTItems;
import gtclassic.util.guicomponents.GTGuiCompEnergyStorage;
import gtclassic.util.guicomponents.GTGuiCompFusion;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.InvertedFilter;
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

public class GTContainerFusionReactor extends ContainerTileComponent<GTTileEntityFusionReactor> {
	
	public static Box2D machineProgressBox = new Box2D(127, 38, 10, 10);
    public static Vec2i machineProgressPos = new Vec2i(208, 38);
   

    public GTContainerFusionReactor(InventoryPlayer player, GTTileEntityFusionReactor tile)
    {
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 88, 17, null));//main slot
        this.addSlotToContainer(new SlotCustom(tile, 1, 88, 53, new BasicItemFilter(GTItems.glassTube)));//second slot
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 148, 35)); //output

        this.addPlayerInventory(player);
        this.addComponent(new MachineProgressComp(tile, GTContainerFusionReactor.machineProgressBox, GTContainerFusionReactor.machineProgressPos));
        
        this.addComponent(new GTGuiCompFusion(tile));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) 
    {
    	gui.disableName();
	}

    @Override
    public ResourceLocation getTexture() 
    {
        return this.getGuiHolder().getGuiTexture();
    }

    @Override
    public int guiInventorySize() 
    {
        return this.getGuiHolder().slotCount;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) 
    {
        return this.getGuiHolder().canInteractWith(playerIn);
    }

}