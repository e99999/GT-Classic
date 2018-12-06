package gtclassic.tileentity;

import gtclassic.GTClassic;
import gtclassic.container.GTContainerFusionReactor;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge.OutputItem;
import gtclassic.util.GTBlocks;
import gtclassic.util.GTItems;
import gtclassic.util.GTLang;
import gtclassic.util.misc.GTBasicMachineRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.tile.IMachine;
import ic2.api.classic.tile.MachineType;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
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
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public class GTTileEntityFusionReactor extends TileEntityBasicElectricMachine {
	
	public static final int slotInput = 0;
    public static final int slotCell = 1;
    public static final int slotOutput = 2;
    
    public int status;
    
    public static final IBlockState coilState = GTBlocks.fusionMachineBlock.getDefaultState();
    
    public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTClassic.MODID, "textures/gui/fusionreactor.png");
    
    public static final String CELL_REQUIREMENT = "recipe-cells";
    public static final IMachineRecipeList RECIPE_LIST = new GTBasicMachineRecipeList("fusion");
	
	public GTTileEntityFusionReactor() 
	{
		super(3, 1, 1000, 2048);
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
	
	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) 
	{
		return new GTContainerFusionReactor(player.inventory, this);
	}

	public IMachineRecipeList getRecipeList() 
		{
	      return RECIPE_LIST;
	    }
	 
	 
	 
	    public MachineType getType() 
	    {
	    	return null;
	    }
	 
	 
	 
	    public ResourceLocation getGuiTexture() 
	    {
	    	return GUI_LOCATION;
	    }
	 
	 
	    @Override
	    public LocaleComp getBlockName() 
	    {
	      return GTLang.fusion;
	    }
	 
	 
	    @Override
	    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) 
	    {
	    	return GuiComponentContainer.class;
	    }
	 
	 
	 
	    @Override
		public RecipeEntry getOutputFor(ItemStack input)
		{
			RecipeEntry entry = RECIPE_LIST.getRecipeInAndOutput(input, false);
			if(entry != null && getStackInSlot(slotCell).getCount() >= getRequiredCells(entry.getOutput()))
			{
				return entry;
			}
			return null;
		}
	    
	    @Override
		public void operateOnce(IRecipeInput input, MachineOutput output, List<ItemStack> list)
		{
			super.operateOnce(input, output, list);
			getStackInSlot(slotCell).shrink(getRequiredCells(output));
		}
	    
	    @Override
		protected EnumActionResult isRecipeStillValid(RecipeEntry entry)
		{
			if(getStackInSlot(slotCell).getCount() >= getRequiredCells(entry.getOutput()))
			{
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.PASS;
		}
	 
	 
	    @Override
	    public ResourceLocation getStartSoundFile() 
	    {
	     return Ic2Sounds.electrolyzerOp;
	    }
	 
	 
	    @Override
	    public ResourceLocation getInterruptSoundFile() 
	    {
	      return Ic2Sounds.interruptingSound;
	    }
	 
	 
	    @Override
	    public double getWrenchDropRate() {
	     return 0.8500000238418579D;
	    }
	 
	 
	    @Override
	    public boolean isValidInput(ItemStack par1) 
	    {
	      if (par1 == null) {
	 
	          return false;
	       } else {
	         return RECIPE_LIST.getRecipeInAndOutput(par1, true) != null ? super.isValidInput(par1) : false;
	       }
	    }
	    
	    protected static int getRequiredCells(MachineOutput output)
		{
			if(output == null || output.getMetadata() == null)
			{
				return 0;
			}
			return output.getMetadata().getInteger(CELL_REQUIREMENT);
		}
		
		protected static NBTTagCompound createCellRequirement(int amount)
		{
			if(amount <= 0)
			{
				return null;
			}
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger(CELL_REQUIREMENT, amount);
			return nbt;
		}
	  
	    public static void init() 
	    {
	    	//addRecipe(new ItemStack(Blocks.STONE),10 ,new ItemStack(Blocks.GRAVEL));
	    }
	 
	 
	    public static void addRecipe(ItemStack stack, int cellRequirement, ItemStack output)
		{
			addRecipe(new RecipeInputItemStack(stack), cellRequirement, output);
		}
		
		public static void addRecipe(String id, int amount, int cellRequirement, ItemStack output)
		{
			addRecipe(new RecipeInputOreDict(id, amount), cellRequirement, output);
		}
		
		public static void addRecipe(IRecipeInput input, int cellRequirement, ItemStack output)
		{
			addRecipe(input, new MachineOutput(createCellRequirement(cellRequirement), output));
		}
		
		static void addRecipe(IRecipeInput input, MachineOutput output)
		{
			RECIPE_LIST.addRecipe(input, output, input.getInputs().get(0).getDisplayName());
		}
	
	//###################################################STOP!#############################################################
	
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
	
	public int getStatus() {
		return this.status;
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

}