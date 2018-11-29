package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityHESU;
import gtclassic.util.guicomponents.GTGuiCompEnergyStorage;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotArmor;
import ic2.core.inventory.slots.SlotCharge;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.components.special.DisplayLocaleComp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static ic2.core.block.wiring.container.ContainerElectricBlock.VALID_EQUIPMENT_SLOTS;

import java.awt.Container;

public class GTContainerHESU extends ContainerTileComponent<GTTileEntityHESU> {
	
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/energystorage.png");
    
	public GTContainerHESU(InventoryPlayer player, GTTileEntityHESU tile)
    {
		
		super(tile);
        this.addSlotToContainer(new SlotDischarge(tile, tile.tier, 1, 128, 50)); //battery
        this.addSlotToContainer(new SlotCharge(tile, tile.tier, 0, 128, 14));

        for(int i = 0; i < 4; ++i) 
        {
            this.addSlotToContainer(new SlotArmor(player, 3 - i, VALID_EQUIPMENT_SLOTS[i], 152, 5 + i * 18));
        }

        this.addComponent(new GTGuiCompEnergyStorage(tile));
        this.addPlayerInventory(player);
    }

	@Override
    public ResourceLocation getTexture() 
    {
        return TEXTURE;
    }

    @Override
    public int guiInventorySize() 
    {
    	return 2;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) 
    {
    	gui.disableName();
    	gui.dissableInvName();
	}

    @Override
    protected boolean moveIntoInventoryInverted() 
    {
		return false;
	}

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) 
    {
        return this.getGuiHolder().canInteractWith(playerIn);
    }
}
