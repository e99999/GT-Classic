package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.container.GTContainerArcFurnace;
import gtclassic.gui.GTGuiMachine.GTArcFurnaceGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GTTileMultiArcFurnace extends GTTileBaseMultiBlockMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotInput2 = 2;
	public static final int slotOutput0 = 3;
	public static final int slotOutput1 = 4;
	public static final int slotOutput2 = 5;

	public static final IBlockState casingHeat = GTBlocks.heatCasingBlock.getDefaultState();
	public static final IBlockState casingMachine = GTMaterialGen.getBlock(GTMaterial.Ultimet, GTMaterialFlag.CASING)
			.getDefaultState();

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("arcfurnace");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/arcfurnace.png");

	public GTTileMultiArcFurnace() {
		super(6, 0, 256, 512);
		maxEnergy = 10000;
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
		return new GTContainerArcFurnace(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTArcFurnaceGui.class;
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

	public static void addRecipe(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();

		for (IRecipeInput input : inputs) {
			inlist.add(input);
		}
		NBTTagCompound mods = new NBTTagCompound();
		for (IRecipeModifier modifier : modifiers) {
			modifier.apply(mods);
		}
		for (ItemStack output : outputs) {
			outlist.add(output);
		}
		addRecipe(inlist, new MachineOutput(mods, outlist));
	}

	static void addRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

	@Override
	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3)) {
			return false;
		}

		int3 dir = new int3(getPos(), getFacing());

		// layer 0
		if (!(isHeatCasing(dir.left(1)) && isHeatCasing(dir.back(1)) && isHeatCasing(dir.back(1))
				&& isHeatCasing(dir.back(1)) && isHeatCasing(dir.right(1)) && isHeatCasing(dir.forward(1))
				&& isHeatCasing(dir.forward(1)) && isHeatCasing(dir.right(1)) && isHeatCasing(dir.back(1))
				&& isHeatCasing(dir.back(1)) && isHeatCasing(dir.forward(3))
				// layer 1
				&& isMachineCasing(dir.up(1)) && isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1))
				&& isMachineCasing(dir.back(1)) && isMachineCasing(dir.left(1)) && isAir(dir.forward(1))
				&& isAir(dir.forward(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.left(1))
				&& isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1))
				// layer 2
				&& isMachineCasing(dir.up(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.right(1)) && isMachineCasing(dir.back(1))
				&& isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1)) && isMachineCasing(dir.right(1))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1))
				&& isMachineCasing(dir.forward(1)))) {
			return false;
		}

		return true;

	}

	public boolean isHeatCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == casingHeat;
	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == casingMachine;
	}

	public boolean isAir(int3 pos) {
		return world.isAirBlock(pos.asBlockPos());
	}

}
