package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.container.GTContainerBlastFurnace;
import gtclassic.gui.GTGuiMachine.GTBlastFurnaceGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
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

public class GTTileMultiBlastFurnace extends GTTileBaseMultiBlockMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotInput2 = 2;
	public static final int slotOutput0 = 3;
	public static final int slotOutput1 = 4;
	public static final int slotOutput2 = 5;

	boolean lastState;
	boolean firstCheck = true;

	public static final IBlockState casingMachine = GTMaterialGen
			.getBlock(GTMaterial.RefinedIron, GTMaterialFlag.CASING).getDefaultState();

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("blastfurnace");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/blastfurnace.png");

	public GTTileMultiBlastFurnace() {
		super(6, 0, 20, 800, 32);
		maxEnergy = 100;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1, slotInput2);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput0, slotOutput1, slotOutput2);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput0);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1, slotInput2);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput0, slotOutput1, slotOutput2);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1, slotInput2);
		handler.registerSlotType(SlotType.Output, slotOutput0, slotOutput1, slotOutput2);
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
		return new GTContainerBlastFurnace(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTBlastFurnaceGui.class;
	}

	@Override
	public int[] getInputSlots() {
		return new int[] { slotInput0, slotInput1, slotInput2 };
	}

	@Override
	public int[][] getRecipeMutations() {
		return new int[][] { { slotInput0, slotInput1, slotInput2 }, { slotInput0, slotInput2, slotInput1 },
				{ slotInput2, slotInput1, slotInput0 }, { slotInput1, slotInput2, slotInput0 },
				{ slotInput1, slotInput0, slotInput2 }, { slotInput2, slotInput0, slotInput1 } };
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
		return new int[] { slotOutput0, slotOutput1, slotOutput2 };
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

	public static void addRecipe(String input0, int amount0, ItemStack output0) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input0, amount0)));
		outputs.add(output0);
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(String input0, int amount0, String input1, int amount1, ItemStack output0) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input0, amount0)));
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input1, amount1)));
		outputs.add(output0);
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(String input0, int amount0, String input1, int amount1, ItemStack output0,
			ItemStack output1) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input0, amount0)));
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input1, amount1)));
		outputs.add(output0);
		outputs.add(output1);
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(ItemStack input0, ItemStack input1, ItemStack input2, ItemStack output0,
			ItemStack output1, ItemStack output2) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input0)));
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input1)));
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input2)));
		outputs.add(output0);
		outputs.add(output1);
		outputs.add(output2);
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(IRecipeInput input0, ItemStack... output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add(input0);
		for (ItemStack stack : output){
			outputs.add(stack);
		}
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(IRecipeInput input0, IRecipeInput input1, ItemStack... output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add(input0);
		inputs.add(input1);
		for (ItemStack stack : output){
			outputs.add(stack);
		}
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(IRecipeInput input0, IRecipeInput input1, IRecipeInput input2, ItemStack... output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add(input0);
		inputs.add(input1);
		inputs.add(input2);
		for (ItemStack stack : output){
			outputs.add(stack);
		}
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(IRecipeInput[] input, ItemStack[] output){
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		int i;
		for (i = 0; i == input.length; i++){
			inputs.add(input[i]);
		}
		for (i = 0; i == output.length; i++){
			outputs.add(output[i]);
		}
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

	@Override
	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3)) {
			return false;
		}
		// im not doing this layer by layer, instead ill loop up and over the structure
		// vertically then finish the base layers last
		int3 dir = new int3(getPos(), getFacing());

		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isAir(dir.back(1))) {// peak
			return false;
		}
		for (int i = 0; i < 5; i++) {
			if (!(isAir(dir.down(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.down(1))) {
			return false;
		}
		if (!isMachineCasing(dir.back(1))) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isAir(dir.forward(1))) {// peak again
			return false;
		}
		if (!isMachineCasing(dir.left(1))) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.down(1)))) {
				return false;
			}
		}
		if (!(isMachineCasing(dir.right(2)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.left(2))
				&& isMachineCasing(dir.back(2)) && isMachineCasing(dir.right(2)) && isMachineCasing(dir.up(1))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.left(2))
				&& isMachineCasing(dir.back(2)) && isMachineCasing(dir.up(1)) && isMachineCasing(dir.right(2))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.left(2))
				&& isMachineCasing(dir.back(1)) && isMachineCasing(dir.right(2)))) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.up(1)))) {
				return false;
			}
		}

		return true;

	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == casingMachine;
	}

	public boolean isAir(int3 pos) {
		return world.isAirBlock(pos.asBlockPos());
	}

}
