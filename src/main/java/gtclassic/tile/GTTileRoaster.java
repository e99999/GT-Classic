package gtclassic.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.container.GTContainerRoaster;
import gtclassic.gui.GTGuiMachine.GTRoasterGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
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
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileRoaster extends GTTileBaseMachine {

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.roaster");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/roaster.png");

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput0 = 2;
	public static final int slotOutput1 = 3;
	public static final int slotOutput2 = 4;
	public static final int slotOutput3 = 5;
	public static final int slotFuel = 6;

	public static final int[] slotOutputs = { slotOutput0, slotOutput1, slotOutput2, slotOutput3 };
	public static final int[] slotInputs = { slotInput0, slotInput1 };

	public GTTileRoaster() {
		super(7, 2, 16, 200, 32);
		setFuelSlot(slotFuel);
		maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.DOWN, slotFuel);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
		handler.registerSlotType(SlotType.Fuel, slotFuel);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		return new LinkedHashSet<UpgradeType>(Arrays.asList(UpgradeType.values()));
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerRoaster(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTRoasterGui.class;
	}

	@Override
	public int[] getInputSlots() {
		return slotInputs;
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		return new IFilter[] { new MachineFilter(this) };
	}

	@Override
	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.left(1).asBlockPos());
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return slot != slotFuel;
	}

	@Override
	public int[] getOutputSlots() {
		return slotOutputs;
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
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.electricFurnaceLoop;
	}

	public static void init() {

		addRecipe("dustSulfur", 1, 2, GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 3));

		addRecipe("dustTetrahedrite", 2, 1, GTMaterialGen.getIc2(Ic2Items.copperDust, 1), GTMaterialGen.getSmallDust(GTMaterial.Antimony, 1), GTMaterialGen.getSmallDust(GTMaterial.Iron, 1), GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1));

		addRecipe("dustSphalerite", 2, 1, GTMaterialGen.getDust(GTMaterial.Zinc, 1), GTMaterialGen.getSmallDust(GTMaterial.Germanium, 2), GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1));

		addRecipe("dustGalena", 2, 1, GTMaterialGen.getDust(GTMaterial.Lead, 1), GTMaterialGen.getIc2(Ic2Items.silverDust, 1), GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1));

		addRecipe("dustSheldonite", 2, 1, GTMaterialGen.getDust(GTMaterial.Platinum, 1), GTMaterialGen.getSmallDust(GTMaterial.Nickel, 1), GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1));

		addRecipe("dustMolybdenite", 2, 1, GTMaterialGen.getDust(GTMaterial.Molybdenum, 1), GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1));

		addRecipe("dustChromite", 3, 1, GTMaterialGen.getDust(GTMaterial.Chrome, 1), GTMaterialGen.getSmallDust(GTMaterial.Iron, 1), GTMaterialGen.getFluid(GTMaterial.CarbonDioxide, 1));

		addRecipe("dustSaltpeter", 12, 4, GTMaterialGen.getFluid(GTMaterial.Nitrogen, 1), GTMaterialGen.getFluid(GTMaterial.CarbonDioxide, 3));
	}

	public static void addRecipe(String input, int amount, int oxygenCount, ItemStack... outputs) {
		if (oxygenCount > 0) {
			addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount),
					new RecipeInputItemStack(GTMaterialGen.getFluid(GTMaterial.Oxygen, oxygenCount)) }, euCost(8000), outputs);
		} else {
			addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount) }, euCost(6000), outputs);
		}
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 16);
	}

	public static IRecipeModifier[] euCost(int amount) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((amount / 16) - 200) };
	}

}
