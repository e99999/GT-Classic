package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.container.GTContainerAssemblyLine;
import gtclassic.gui.GTGuiMachine.GTAssemblyLineGui;
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
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2States;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTTileAssemblyLine extends GTTileBaseMultiInputMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotInput2 = 2;
	public static final int slotOutput0 = 3;

	boolean lastState;
	boolean firstCheck = true;

	public static final IBlockState casingGrate = GTBlocks.grateCasingBlock.getDefaultState();
	public static final IBlockState casingMachineSteel = GTMaterialGen.getBlock(GTMaterial.Steel, GTMaterialFlag.CASING)
			.getDefaultState();
	public static final IBlockState glass = Ic2States.reinforcedGlass;

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("assemblyline");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/assemblyline.png");

	public GTTileAssemblyLine() {
		super(4, 0, 120, 500, 512);
		maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1, slotInput2);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput0);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput0);
		handler.registerDefaultSlotsForSide(RotationList.WEST, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.EAST, slotInput2);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput0);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1, slotInput2);
		handler.registerSlotType(SlotType.Output, slotOutput0);
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
		return new GTContainerAssemblyLine(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTAssemblyLineGui.class;
	}

	@Override
	public int[] getInputSlots() {
		int[] input = { slotInput0, slotInput1, slotInput2 };
		return input;
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
		int[] output = { slotOutput0 };
		return output;
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

	@Override
	public boolean canWork() {
		boolean superCall = super.canWork();
		if (superCall) {
			if (world.getTotalWorldTime() % 600 == 0 || firstCheck) {
				lastState = checkStructure();
				firstCheck = false;
			}
			superCall = superCall && lastState;
		}
		return superCall;
	}

	public static void addRecipe(ItemStack input0, String string, ItemStack output0) {
		// TODO Auto-generated method stub

	}

	public static void addRecipe(ItemStack input0, ItemStack input1, ItemStack input2, ItemStack output0) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input0)));
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input1)));
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input2)));
		outputs.add(output0);
		addRecipe(inputs, new MachineOutput(null, outputs));
	}

	static void addRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3))
			return false;

		int3 dir = new int3(getPos(), getFacing());

		for (int i = 0; i < 6; i++) {
			if (!(isFactoryCasing(dir.right(1)))) {
				return false;
			}
		}
		if (!isGlass(dir.down(1))) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (!(isGlass(dir.left(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.down(1))) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.right(1)))) {
				return false;
			}
		}
		if (!isGlass(dir.up(1))) {
			return false;
		}
		if (!isMachineCasing(dir.back(1))) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.left(1)))) {
				return false;
			}
		}
		return true;
	}

	public boolean isFactoryCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == casingGrate;
	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == casingMachineSteel;
	}

	public boolean isGlass(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == glass;
	}

}
