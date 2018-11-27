package gtclassic.tileentity;

import gtclassic.container.GTContainerSmallBuffer;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileEntitySmallBuffer extends TileEntityMachine implements IHasGui, IEnergyConductor{

	public GTTileEntitySmallBuffer() 
	{
		super(1);
	}
	
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) 
	{
        return GuiComponentContainer.class;
    }
	
	public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new GTContainerSmallBuffer(player.inventory, this);
    }
	
	@Override
    protected void addSlots(InventoryHandler handler) 
	{
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, 0);
        handler.registerDefaultSlotsForSide(RotationList.ALL, 0);
        handler.registerSlotType(SlotType.Input, 0);
        handler.registerSlotType(SlotType.Output, 0);
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

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter var1, EnumFacing var2) 
	{
		if (var2 == EnumFacing.NORTH)
		{
		return true;
		}
	
			return false;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing var2)
	{
		if (var2 == EnumFacing.SOUTH)
		{
		return true;
		}
	
			return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub for IEnergyConductor
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub for IEnergyConductor
		return false;
	}

	@Override
	public double getConductionLoss() {
		// TODO Auto-generated method stub for IEnergyConductor
		return 0;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		// TODO Auto-generated method stub for IEnergyConductor
		return 0;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		// TODO Auto-generated method stub for IEnergyConductor
		return 0;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		// TODO Auto-generated method stub for IEnergyConductor
		return 0;
	}

	@Override
	public void removeConductor() {
		// TODO Auto-generated method stub for IEnergyConductor
		
	}

	@Override
	public void removeInsulation() {
		// TODO Auto-generated method stub for IEnergyConductor
		
	}

}
