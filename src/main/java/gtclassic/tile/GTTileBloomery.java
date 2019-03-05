package gtclassic.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gtclassic.container.GTContainerBloomery;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBloomery extends TileEntityMachine implements ITickable, IHasGui, IProgressMachine {

	IBlockState brick = Blocks.BRICK_BLOCK.getDefaultState();
	IBlockState door = Blocks.IRON_TRAPDOOR.getDefaultState();
	IBlockState lava = Blocks.FLOWING_LAVA.getDefaultState();
	IBlockState steel = GTMaterialGen.getBlock(GTMaterial.Steel, GTMaterialFlag.BLOCK).getDefaultState();

	AxisAlignedBB recipeBB = null;

	ItemStack ironblock = new ItemStack(Blocks.IRON_BLOCK);
	ItemStack coalblock = new ItemStack(Blocks.COAL_BLOCK);
	ItemStack charcoalblock = GTMaterialGen.getIc2(Ic2Items.charcoalBlock, 1);
	ItemStack iron = new ItemStack(Items.IRON_INGOT, 9);
	ItemStack steeldust = GTMaterialGen.getDust(GTMaterial.Steel, 9);
	ItemStack coal = new ItemStack(Items.COAL, 9);
	ItemStack charcoal = new ItemStack(Items.COAL, 9, 1);

	public static final BloomeryRecipeList RECIPE_LIST = new BloomeryRecipeList();
	
	
	BloomeryRecipe activeRecipe = null;
	@NetworkField(index = 7)
	float progress = 0;
	@NetworkField(index = 8)
	float recipeOperation = 600.0F; // TODO extend the time after testing - bear recommends 4.5 minutes
	boolean processing = false;
	int slotOutput = 0;

	public GTTileBloomery() {
		super(1);
		setWorld(world);
		addGuiFields("progress", "recipeOperation");
		RECIPE_LIST.addRecipe("Test", GTMaterialGen.getBlock(GTMaterial.Steel, GTMaterialFlag.BLOCK).getDefaultState(), 1, 1200, new RecipeInputItemStack(ironblock), new RecipeInputItemStack(coalblock));
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBloomery(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSlotAccess(AccessRule.Export, 0);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 0);
		handler.registerSlotType(SlotType.Output, 0);
	}

	@Override
	public boolean hasGui(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer arg0) {
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.processing = nbt.getBoolean("processing");
		this.setActive(nbt.getBoolean("active"));
		this.progress = nbt.getFloat("progress");
		this.recipeOperation = nbt.getFloat("RecipeTime");
		if(nbt.hasKey("RecipeID"))
		{
			activeRecipe = RECIPE_LIST.getFromID(nbt.getString("RecipeID"));
		}
		FMLLog.getLogger().info(activeRecipe);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("processing", this.processing);
		nbt.setBoolean("active", this.getActive());
		nbt.setFloat("progress", this.progress);
		nbt.setFloat("RecipeTime", recipeOperation);
		if(activeRecipe != null)
		{
			nbt.setString("RecipeID", activeRecipe.getId());
		}
		return nbt;
	}

	@Override
	public void update() {
		/*
		 * if the bloomery canWork() the following logic executes
		 */
		if(activeRecipe == null)
		{
			processing = false;
			progress = 0;
			this.setActive(false);
		}
		if (processing) {
			progress = progress + 1.0F;
			getNetwork().updateTileGuiField(this, "progress");
			if (world.getTotalWorldTime() % 120 == 0) {
				isLavaStillPresent();
			}
			if (progress >= recipeOperation && isLavaStillPresent()) {
				recipeLastTick();
				outputDarkAsh();
				processing = false;
				progress = 0;
				activeRecipe = null;
				getNetwork().updateTileGuiField(this, "progress");
				this.setActive(false);
			}
		}
	}

	public boolean canWork() {
		/*
		 * Checks the structure then the recipe area to see if it can execute the recipe
		 * logic, called only once when the block is activated with a flint & steel.
		 */
		if (!processing && checkStructure() && isRecipeValid() && canOutputDarkAsh()) {
			recipeFirstTick();
			processing = true;
			setActive(true);
			return true;
		}
		return false;
	}

	public void recipeFirstTick() {
		for(EntityItem item : world.getEntitiesWithinAABB(EntityItem.class, recipeBB))
		{
			world.removeEntity(item);
		}
		recipeOperation = activeRecipe.getRecipeTime();
		getNetwork().updateTileGuiField(this, "recipeOperation");
		int3 dir = new int3(pos, getFacing());
		setLava(dir.back(1));
		setLava(dir.up(1));
		setLava(dir.up(1));
		setLava(dir.up(1));
	}

	public void recipeLastTick() {
		/*
		 * The inverse of recipeFirstTick, executed in update() to remove the lava
		 * pillar and place a block of steel
		 */
		int3 dir = new int3(pos, getFacing());
		setSteel(dir.back(1));
		setAir(dir.up(1));
		setAir(dir.up(1));
		setAir(dir.up(1));
	}

	public boolean isLavaStillPresent() {
		/*
		 * This checks if lava is still present inside the bloomery
		 */
		int3 dir = new int3(pos, getFacing());
		if (!(isLava(dir.back(1)) && isLava(dir.up(1)) && isLava(dir.up(1)) && isLava(dir.up(1)))) {
			processing = false;
			progress = 0;
			getNetwork().updateTileGuiField(this, "progress");
			this.setActive(false);
			return false;

		}
		return true;
	}

	public boolean isRecipeValid() {
		/*
		 * checks blocks space directly behind the tile for correct recipe stacks
		 */
		recipeBB = new AxisAlignedBB(new int3(pos, getFacing()).back(1).asBlockPos());
		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, recipeBB);
		activeRecipe = RECIPE_LIST.getRecipe(items);
		return activeRecipe != null;
	}

	public boolean checkStructure() {
		/*
		 * Multi Block Structure check, Important to remember this structure check steps
		 * to the last position, so the next step is based on the previous pos position.
		 */
		if (!world.isAreaLoaded(pos, 3))
			return false;
		int3 dir = new int3(getPos(), getFacing());
		if (!(isBrick(dir.left(1)) &&
		// layer -1
				isBrick(dir.down(1)) && isBrick(dir.back(1)) && isBrick(dir.back(1)) && isBrick(dir.right(1))
				&& isBrick(dir.forward(1)) && isBrick(dir.back(1)) && isBrick(dir.right(1))
				// layer 0
				&& isBrick(dir.up(1)) && isBrick(dir.forward(2)) && isBrick(dir.down(1)) && isBrick(dir.up(1))
				&& isBrick(dir.left(2)) && isBrick(dir.back(1)) && isBrick(dir.back(1)) && isBrick(dir.right(1))
				&& isBrick(dir.up(1))
				// layer 1
				&& isBrick(dir.forward(2)) && isBrick(dir.left(1)) && isBrick(dir.back(1)) && isBrick(dir.back(1))
				&& isBrick(dir.right(1)) && isBrick(dir.right(1)) && isBrick(dir.forward(2)) && isBrick(dir.back(1))
				// chimney
				&& isBrick(dir.up(1)) && isBrick(dir.up(1)) && isBrick(dir.left(2)) && isBrick(dir.down(1))
				&& isBrick(dir.down(1)) && isBrick(dir.forward(1)) && isBrick(dir.right(1)) && isBrick(dir.up(1))
				&& isBrick(dir.up(1)) && isBrick(dir.back(2)) && isBrick(dir.down(1)))) {

			return false;
		}
		return true;
	}

	public boolean canOutputDarkAsh() {
		return ((this.getStackInSlot(0).getCount() + activeRecipe.getAshOutput()) <= 64
				|| ItemStack.areItemStackTagsEqual(this.getStackInSlot(slotOutput), ItemStack.EMPTY));
	}

	public void outputDarkAsh() {
		int count = this.getStackInSlot(slotOutput).getCount() + activeRecipe.getAshOutput();
		this.setStackInSlot(0, GTMaterialGen.getDust(GTMaterial.DarkAshes, count));
	}

	public boolean isEntityValid(EntityItem entity, ItemStack stack) {
		return StackUtil.isStackEqual(entity.getItem(), stack) && entity.getItem().getCount() >= stack.getCount();
	}

	public boolean isBrick(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == brick;
	}

	public boolean isLava(int3 pos) {
		return world.getBlockState(pos.asBlockPos()).getMaterial() == Material.LAVA;
	}

	public void setLava(int3 pos) {
		world.setBlockState(pos.asBlockPos(), lava);
	}

	public void setSteel(int3 pos) {
		world.setBlockState(pos.asBlockPos(), activeRecipe == null ? Blocks.AIR.getDefaultState() : activeRecipe.getState());
	}

	public void setAir(int3 pos) {
		world.setBlockState(pos.asBlockPos(), Blocks.AIR.getDefaultState());
	}

	public float getProgress() {
		return progress;
	}

	public float getMaxProgress() {
		return recipeOperation;
	}
	
	
	public static class BloomeryRecipeList
	{
		List<BloomeryRecipe> recipes = new ArrayList<BloomeryRecipe>();
		Map<String, BloomeryRecipe> recipeMap = new HashMap<String, BloomeryRecipe>();
		
		public void addRecipe(String id, IBlockState output, int ashAmount, int recipeTime, IRecipeInput...inputs)
		{
			if(output == null || output.getBlock() == Blocks.AIR)
			{
				FMLLog.getLogger().info("Bloomery Recipe Invalid: "+id);
				return;
			}
			if(recipeMap.containsKey(id))	
			{
				FMLLog.getLogger().info("Bloomery Recipe Invalid: "+id);
				return;
			}
			for(IRecipeInput input : inputs)
			{
				if(isListInvalid(input.getInputs()))
				{
					FMLLog.getLogger().info("Bloomery Recipe Invalid: "+id);
					return;
				}
			}
			BloomeryRecipe recipe = new BloomeryRecipe(id, output, ashAmount, recipeTime, inputs);
			recipes.add(recipe);
			recipeMap.put(id, recipe);
		}
		
		public BloomeryRecipe getFromID(String id)
		{
			return recipeMap.get(id);
		}
		
		public BloomeryRecipe getRecipe(List<EntityItem> entities)
		{
			if(entities.isEmpty())
			{
				return null;
			}
			List<ItemStack> list = new ArrayList<ItemStack>();
			for(EntityItem item : entities)
			{
				list.add(item.getItem());
			}
			for(BloomeryRecipe recipe : recipes)
			{
				if(matches(recipe, new LinkedList<ItemStack>(list)) == 0)
				{
					return recipe;
				}
			}
			return null;
		}
		
		public int matches(BloomeryRecipe recipe, LinkedList<ItemStack> list)
		{
			List<IRecipeInput> inputs = new ArrayList<IRecipeInput>(recipe.getItems());
			for(Iterator<IRecipeInput> iter = inputs.iterator();iter.hasNext();)
			{
				IRecipeInput input = iter.next();
				Iterator<ItemStack> stacks = list.iterator();
				while(stacks.hasNext())
				{
					ItemStack stack = stacks.next();
					if(input.matches(stack))
					{
						if(input.getAmount() != stack.getCount())
						{
							return input.getAmount() > stack.getCount() ? -3 : -4;
						}
						stacks.remove();
						iter.remove();
						break;
					}
				}
			}
			if(inputs.isEmpty())
			{
				return list.isEmpty() ? 0 : -1;
			}
			return -2;
		}
		
		private boolean isListInvalid(List<ItemStack> list) {
			if (list.isEmpty()) {
				return true;
			}
			for (ItemStack stack : list) {
				if (stack.isEmpty()) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static class BloomeryRecipe
	{
		List<IRecipeInput> items = new ArrayList<IRecipeInput>();
		int ashOutput;
		int recipeTime;
		IBlockState state;
		String id;
		
		public BloomeryRecipe(String id, IBlockState output, int ash, int recipeTime, IRecipeInput...inputs)
		{
			items.addAll(Arrays.asList(inputs));
			state = output;
			ashOutput = ash;
			this.id = id;
			this.recipeTime = recipeTime;
		}
		
		public String getId()
		{
			return id;
		}
		
		public int getAshOutput()
		{
			return ashOutput;
		}
		
		public int getRecipeTime()
		{
			return recipeTime;
		}
		
		public List<IRecipeInput> getItems()
		{
			return items;
		}
		
		public IBlockState getState()
		{
			return state;
		}
	}
}
