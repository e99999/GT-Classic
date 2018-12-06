package gtclassic.tileentity;

import gtclassic.container.GTContainerFusionReactor;
import gtclassic.util.GTBlocks;
import gtclassic.util.GTItems;
import gtclassic.util.GTLang;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.IMachine;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class GTTileEntityFusionReactor extends TileEntityElecMachine implements  ITickable, IProgressMachine, IMachine, IOutputMachine, IHasGui, INetworkTileEntityEventListener, IEnergyUser {
	
	public static final int slotInput = 0;
    public static final int slotCell = 1;
    public static final int slotOutput = 2;
    
    public int status;
    
    public static final IBlockState coilState = GTBlocks.fusionMachineBlock.getDefaultState();
	
	public GTTileEntityFusionReactor() 
	{
		super(3, 2048);
		this.status = 0;
		this.setCustomName("tileFusionReactor");
	}
	
	@Override
    protected void addSlots(InventoryHandler handler) 
	{
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput, slotCell);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
        handler.registerDefaultSlotsForSide(RotationList.UP, slotInput);
        handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotCell);
        handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput);
        handler.registerInputFilter(new BasicItemFilter(GTItems.glassTube), slotCell);
        handler.registerSlotType(SlotType.Input, slotInput);
        handler.registerSlotType(SlotType.SecondInput, slotCell);
        handler.registerSlotType(SlotType.Output, slotOutput);
    }
	
	public int getStatus() {
		return this.status;
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
		return new GTContainerFusionReactor(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {
		if (this.checkStructure()) 
		{
			this.status = 1;
		}
		else
		{
			this.status = 666;
		}
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
        return new RangedInventoryWrapper(this, slotOutput);
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
	
	public boolean checkStructure() {
		//if (world.isRemote) return false; //Return false if on client side
		if (!world.isAreaLoaded(pos, 3)) return false; //Return false if area is not loaded

		BlockPos working;

		//Check line shapes
		working = pos.offset(EnumFacing.NORTH, 3);
		if (!(checkPos(working) && checkPos(working, EnumFacing.EAST, 1) && checkPos(working, EnumFacing.WEST, 1))) {
			return false;
		}
		working = pos.offset(EnumFacing.SOUTH, 3);
		if (!(checkPos(working) && checkPos(working, EnumFacing.EAST, 1) && checkPos(working, EnumFacing.WEST, 1))) {
			return false;
		}
		working = pos.offset(EnumFacing.EAST, 3);
		if (!(checkPos(working) && checkPos(working, EnumFacing.NORTH, 1) && checkPos(working, EnumFacing.SOUTH, 1))) {
			return false;
		}
		working = pos.offset(EnumFacing.WEST, 3);
		if (!(checkPos(working) && checkPos(working, EnumFacing.NORTH, 1) && checkPos(working, EnumFacing.SOUTH, 1))) {
			return false;
		}

		//Check corner shapes
		working = pos.offset(EnumFacing.NORTH, 2).offset(EnumFacing.EAST, 2);
		if (!(checkPos(working) && checkPos(working, EnumFacing.WEST, 1) && checkPos(working, EnumFacing.SOUTH, 1))) {
			return false;
		}
		working = pos.offset(EnumFacing.NORTH, 2).offset(EnumFacing.WEST, 2);
		if (!(checkPos(working) && checkPos(working, EnumFacing.EAST, 1) && checkPos(working, EnumFacing.SOUTH, 1))) {
			return false;
		}
		working = pos.offset(EnumFacing.SOUTH, 2).offset(EnumFacing.EAST, 2);
		if (!(checkPos(working) && checkPos(working, EnumFacing.WEST, 1) && checkPos(working, EnumFacing.NORTH, 1))) {
			return false;
		}
		working = pos.offset(EnumFacing.SOUTH, 2).offset(EnumFacing.WEST, 2);
		if (!(checkPos(working) && checkPos(working, EnumFacing.EAST, 1) && checkPos(working, EnumFacing.NORTH, 1))) {
			return false;
		}
		
		return true;
	}

	public boolean checkPos(BlockPos pos) {
		return world.getBlockState(pos) == coilState;
	}

	public boolean checkPos(BlockPos pos, EnumFacing facing, int offset) {
		return checkPos(pos.offset(facing, offset));
	}
	
	@Override
	public LocaleComp getBlockName() 
    {
    	return GTLang.fusion;
	}
}