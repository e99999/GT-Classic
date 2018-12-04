package gtclassic.tileentity;

import gtclassic.GTClassic;
import gtclassic.container.GTContainerSmallChest;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileEntitySmallChest extends TileEntityMachine implements IHasGui {

	public GTTileEntitySmallChest() 
	{
		super(27);
	}
	
	@Override
    public LocaleComp getBlockName() {
        return new LangComponentHolder.LocaleBlockComp("tile." + GTClassic.MODID + ".smallchest");
    }
	
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) 
	{
        return GuiComponentContainer.class;
    }
	
	public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new GTContainerSmallChest(player.inventory, this);
    }
	
	@Override
    protected void addSlots(InventoryHandler handler) 
	{
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        
        for (int i = 0; i < 26; i++) 
        {
        handler.registerDefaultSlotAccess(AccessRule.Both, i);
        handler.registerDefaultSlotsForSide(RotationList.ALL, i);
        handler.registerSlotType(SlotType.Input, i);
        handler.registerSlotType(SlotType.Output, i);
        }
    }
	
	@Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return !this.isInvalid();
    }

	@Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {
        //needed for construction
    }

    @Override
    public boolean hasGui(EntityPlayer player)
    {
        return true;
    }

}
