package gtclassic.tileentity;

import java.util.Set;

import gtclassic.container.GTContainerChemicalElectrolyzer;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.tile.IMachine;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTTileEntityChemicalElectrolyzer extends TileEntityElecMachine implements  ITickable, IProgressMachine, IMachine, IOutputMachine, IHasGui, INetworkTileEntityEventListener, IEnergyUser {

	public static final int slotInput = 0;
    public static final int slotCell = 1;
    public static final int slotFuel = 2;
    public static final int slotOutput = 3;
    public static final int slotOutput2 = 4;
    public static final int slotOutput3 = 5;
    public static final int slotOutput4 = 6;
	
	
	public GTTileEntityChemicalElectrolyzer() 
	{
		super(7, 32);
	}
	
	@Override
    protected void addSlots(InventoryHandler handler) 
	{
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput, slotCell);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2, slotOutput3, slotOutput4);
        handler.registerDefaultSlotsForSide(RotationList.UP, slotInput);
        handler.registerDefaultSlotsForSide(RotationList.DOWN, slotFuel);
        handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotCell);
        handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput, slotOutput2, slotOutput3, slotOutput4);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
        handler.registerSlotType(SlotType.Fuel, slotFuel);
        handler.registerSlotType(SlotType.Input, slotInput);
        handler.registerSlotType(SlotType.SecondInput, slotCell);
        handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2, slotOutput3, slotOutput4);
    }

	@Override
	public boolean supportsNotify() 
	{
		return true;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) 
	{
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) 
	{
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerChemicalElectrolyzer(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {
        //needed for construction
    }
	
	@Override
    public double getWrenchDropRate() 
	{
        return 0.8500000238418579D;
    }
	
	@Override
    public boolean needsInitialRedstoneUpdate()
    {
        return true;
    }

	@Override
	public float getMaxProgress() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getProgress() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean useEnergy(double var1, boolean var3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRedstoneSensitive(boolean var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRedstoneSensitive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProcessing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidInput(ItemStack var1) 
	{
		 return false;
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() 
	{
		return null;
	}

	@Override
    public World getMachineWorld()
    {
        return this.getWorld();
    }

    @Override
    public BlockPos getMachinePos()
    {
        return this.getPos();
    }

	@Override
	public int getEnergyUsage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onNetworkEvent(int var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public IHasInventory getOutputInventory()
    {
        return new RangedInventoryWrapper(this, slotOutput, slotOutput2, slotOutput3, slotOutput4);
    }

    @Override
    public IHasInventory getInputInventory()
    {
        return new RangedInventoryWrapper(this, slotInput, slotCell);
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
