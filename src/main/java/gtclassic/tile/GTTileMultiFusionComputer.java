package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.container.GTContainerFusionComputer;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTTileMultiFusionComputer extends GTTileBaseMultiBlockMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput = 2;

	boolean lastState;
	boolean firstCheck = true;

	public static final IBlockState coilState = GTBlocks.fusionCasingBlock.getDefaultState();

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("fusion");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/fusioncomputer.png");

	public GTTileMultiFusionComputer() {
		super(3, 0, 8192, 4096, 8192);
		maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput0);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1);
		handler.registerSlotType(SlotType.Output, slotOutput);
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerFusionComputer(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTFusionComputerGui.class;
	}

	@Override
	public int[] getInputSlots() {
		int[] input = { slotInput0, slotInput1 };
		return input;
	}

	@Override
	public int[][] getRecipeMutations() {
		return new int[][] { { slotInput0, slotInput1 }, { slotInput1, slotInput0 } };
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		IFilter[] filter = { new MachineFilter(this) };
		return null;
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return true;
	}

	@Override
	public int[] getOutputSlots() {
		return new int[] { slotOutput };
	}

	@Override
	public GTMultiInputRecipeList getRecipeList() {
		return RECIPE_LIST;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	public static void addRecipe(String input1, int amount1, ItemStack input2, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input1, amount1)));
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input2)));
		addRecipe(inputs, new MachineOutput(null, output));
	}

	public static void addRecipe(ItemStack input1, String input2, int amount2, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input1)));
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input2, amount2)));
		addRecipe(inputs, new MachineOutput(null, output));
	}

	public static void addRecipe(String input1, int amount1, String input2, int amount2, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input1, amount1)));
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input2, amount2)));
		addRecipe(inputs, new MachineOutput(null, output));
	}

	public static void addRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input1)));
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input2)));
		addRecipe(inputs, new MachineOutput(null, output));
	}

	static void addRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

	@Override
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
}
