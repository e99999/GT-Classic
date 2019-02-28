package gtclassic.tile;

import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.container.GTContainerFusionComputer;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTValues;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTBasicMachineRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;

public class GTTileFusionComputer extends TileEntityBasicElectricMachine {

	public static final int slotInput = 0;
	public static final int slotCell = 1;
	public static final int slotOutput = 2;
	
	boolean lastState;
	boolean firstCheck = true;

	public static final IBlockState coilState = GTBlocks.fusionCasingBlock.getDefaultState();

	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/fusioncomputer.png");

	public static final String CELL_REQUIREMENT = "recipe-cells";
	public static final IMachineRecipeList RECIPE_LIST = new GTBasicMachineRecipeList("fusion");

	public GTTileFusionComputer() {
		super(3, 8192, 10000, 8192);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput, slotCell);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotCell);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput);
		handler.registerInputFilter(new BasicItemFilter(GTMaterialGen.getChemical(GTMaterial.Dueterium, 1)), slotCell);
		handler.registerSlotType(SlotType.Input, slotInput);
		handler.registerSlotType(SlotType.SecondInput, slotCell);
		handler.registerSlotType(SlotType.Output, slotOutput);
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerFusionComputer(player.inventory, this);
	}

	@Override
	public IMachineRecipeList getRecipeList() {
		return RECIPE_LIST;
	}

	@Override
	public MachineType getType() {
		return null;
	}

	@Override
	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTValues.fusion;
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTFusionComputerGui.class;
	}

	@Override
	public boolean canWork() {
		boolean superCall = super.canWork();
		if (superCall) {
			if (world.getTotalWorldTime() % 1200 == 0 || firstCheck) {
				lastState = checkStructure();
				firstCheck = false;
			}
			superCall = superCall && lastState;
		}
		return superCall;
	}

	@Override
	public RecipeEntry getOutputFor(ItemStack input) {
		RecipeEntry entry = RECIPE_LIST.getRecipeInAndOutput(input, false);
		if (entry != null && getStackInSlot(slotCell).getCount() >= getRequiredCells(entry.getOutput())) {
			return entry;
		}
		return null;
	}

	@Override
	public void operateOnce(IRecipeInput input, MachineOutput output, List<IStackOutput> list) {
		super.operateOnce(input, output, list);
		getStackInSlot(slotCell).shrink(getRequiredCells(output));
	}

	@Override
	protected EnumActionResult isRecipeStillValid(RecipeEntry entry) {
		if (getStackInSlot(slotCell).getCount() >= getRequiredCells(entry.getOutput())) {
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	@Override
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.generatorLoop;
	}

	@Override
	public ResourceLocation getInterruptSoundFile() {
		return Ic2Sounds.interruptingSound;
	}

	@Override
	public double getWrenchDropRate() {
		return 0.8500000238418579D;
	}

	@Override
	public boolean isValidInput(ItemStack par1) {
		return RECIPE_LIST.getRecipeInAndOutput(par1, true) != null ? super.isValidInput(par1) : false;
	}

	public static int getRequiredCells(MachineOutput output) {
		if (output == null || output.getMetadata() == null) {
			return 0;
		}
		return output.getMetadata().getInteger(CELL_REQUIREMENT);
	}

	protected static NBTTagCompound createCellRequirement(int amount) {
		if (amount <= 0) {
			return null;
		}
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(CELL_REQUIREMENT, amount);
		return nbt;
	}

	public static void init() {
		// empty method for internal recipes
	}

	public static void addRecipe(ItemStack stack, int cellRequirement, ItemStack output) {
		addRecipe(new RecipeInputItemStack(stack), cellRequirement, output);
	}

	public static void addRecipe(String id, int amount, int cellRequirement, ItemStack output) {
		addRecipe(new RecipeInputOreDict(id, amount), cellRequirement, output);
	}

	public static void addRecipe(IRecipeInput input, int cellRequirement, ItemStack output) {
		addRecipe(input, new MachineOutput(createCellRequirement(cellRequirement), output));
	}

	static void addRecipe(IRecipeInput input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, input.getInputs().get(0).getDisplayName());
	}

	public boolean checkStructure() {

		if (!world.isAreaLoaded(pos, 3))
			return false;

		int3 working = new int3(getPos(), getFacing());
		if (!(checkPos(working.forward(3)) && checkPos(working.right(1)) && checkPos(working.back(1))
				&& checkPos(working.right(1)) && checkPos(working.back(1)) && checkPos(working.right(1))
				&& checkPos(working.back(1)) && checkPos(working.back(1)) && checkPos(working.left(1))
				&& checkPos(working.back(1)) && checkPos(working.left(1)) && checkPos(working.back(1))
				&& checkPos(working.left(1)) && checkPos(working.left(1)) && checkPos(working.forward(1))
				&& checkPos(working.left(1)) && checkPos(working.forward(1)) && checkPos(working.left(1))
				&& checkPos(working.forward(1)) && checkPos(working.forward(1)) && checkPos(working.right(1))
				&& checkPos(working.forward(1)) && checkPos(working.right(1)) && checkPos(working.forward(1)))) {
			return false;
		}
		return true;
	}

	public boolean checkPos(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == coilState;
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		// needed for construction
	}
}
