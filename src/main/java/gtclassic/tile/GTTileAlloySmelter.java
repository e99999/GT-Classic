package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.container.GTContainerAlloySmelter;
import gtclassic.gui.GTGuiMachine.GTAlloySmelterGui;
import gtclassic.tile.base.GTTileBaseMultiInputMachine;
import gtclassic.util.GTValues;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTTileAlloySmelter extends GTTileBaseMultiInputMachine {

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("alloysmelter");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/alloysmelter.png");

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotFuel = 3;
	public static final int slotOutput = 2;

	public GTTileAlloySmelter() {
		super(4, 0, 16, 1000, 32);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput0);
		handler.registerDefaultSlotsForSide(RotationList.DOWN, slotFuel);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), slotOutput);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE),
				new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
		handler.registerInputFilter(new MachineFilter(this), slotInput0, slotInput1);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
		handler.registerSlotType(SlotType.Fuel, slotFuel);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1);
		handler.registerSlotType(SlotType.Output, slotOutput);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTValues.alloysmelter;
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerAlloySmelter(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTAlloySmelterGui.class;
	}

	@Override
	public int[] getInputSlots() {
		int[] input = { slotInput0, slotInput1 };
		return input;
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		IFilter[] filter = { new MachineFilter(this) };
		return filter;
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		if (slot == slotFuel) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int[] getOutputSlots() {
		int[] output = { slotOutput };
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

	public static void addRecipe(String input1, int amount1, String input2, int amount2, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		inputs.add((IRecipeInput)(new RecipeInputOreDict(input1, amount1)));
		inputs.add((IRecipeInput)(new RecipeInputOreDict(input2, amount2)));
		addRecipe(inputs, new MachineOutput(null, output));
	}
	
	public static void addRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		inputs.add((IRecipeInput)(new RecipeInputItemStack(input1)));
		inputs.add((IRecipeInput)(new RecipeInputItemStack(input2)));
		addRecipe(inputs, new MachineOutput(null, output));
	}

	static void addRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

}
