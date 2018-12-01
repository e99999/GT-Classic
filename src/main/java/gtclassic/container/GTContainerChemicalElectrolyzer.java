package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityChemicalElectrolyzer;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerChemicalElectrolyzer extends ContainerTileComponent<GTTileEntityChemicalElectrolyzer> {
    
    
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/electrolyzer.png");
	
	public static Box2D machineProgressBoxUp = new Box2D(83, 23, 10, 10);
    public static Vec2i machineProgressPosUp = new Vec2i(193, 23);
   

    public GTContainerChemicalElectrolyzer(InventoryPlayer player, GTTileEntityChemicalElectrolyzer tile)
    {
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 80, 46, null));//main slot
        this.addSlotToContainer(new SlotCustom(tile, 1, 50, 46, null));//second slot
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 2, 110, 46)); //battery
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 50, 16));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 70, 16));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 90, 16));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 6, 110, 16));

        this.addPlayerInventory(player);
        this.addComponent(new MachineProgressComp(tile, GTContainerChemicalElectrolyzer.machineProgressBoxUp, GTContainerChemicalElectrolyzer.machineProgressPosUp));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) 
    {
    	gui.disableName();
	}

    @Override
    public ResourceLocation getTexture() {
    	return TEXTURE;
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
