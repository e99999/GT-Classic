package gtclassic.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.container.GTContainerElectrolyzer;
import gtclassic.gui.GTGuiMachine.GTIndustrialElectrolyzerGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
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
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GTTileElectrolyzer extends GTTileBase {

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.electrolyzer");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/industrialelectrolyzer.png");

	IFilter filter = new MachineFilter(this);

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotInput2 = 2;
	public static final int slotInput3 = 3;
	public static final int slotOutput0 = 4;
	public static final int slotOutput1 = 5;
	public static final int slotOutput2 = 6;
	public static final int slotOutput3 = 7;
	public static final int slotOutput4 = 8;
	public static final int slotOutput5 = 9;
	public static final int slotFuel = 10;

	protected static final int[] slotInputs = { 0, 1, 2, 3 };
	protected static final int[] slotOutputs = { 4, 5, 6, 7, 8, 9 };

	public GTTileElectrolyzer() {
		super(11, 2, 64, 192, 512);
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
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput2, slotInput3);
		handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), slotOutputs);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE),
				new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
		handler.registerInputFilter(filter, slotInputs);
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
		return new LinkedHashSet(Arrays.asList(UpgradeType.values()));
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerElectrolyzer(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTIndustrialElectrolyzerGui.class;
	}

	@Override
	public int[] getInputSlots() {
		return slotInputs;
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		return new IFilter[] { filter };
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
		return Ic2Sounds.magnetizerOp;
	}

	// @formatter:off
	public static void init() {
		
		addRecipe(new IRecipeInput[] { 
				input(GTMaterialGen.getModFluid("water", 6)) }, 
				totalEu(93000),
				GTMaterialGen.getFluid(GTMaterial.Hydrogen, 4), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 2));

		// TODO blah electrified water blah blah

		addRecipe(new IRecipeInput[] { 
				input(new ItemStack(Items.DYE, 1, 15)), 
				tubes(1) }, 
				totalEu(3000),
				GTMaterialGen.getFluid(GTMaterial.Calcium, 1));

		addRecipe(new IRecipeInput[] { 
				input(GTMaterialGen.get(Items.SUGAR, 32)), 
				tubes(5) }, 
				totalEu(6000),
				GTMaterialGen.getDust(GTMaterial.Carbon, 2), 
				GTMaterialGen.getModFluid("water", 5));

		addRecipe(new IRecipeInput[] { 
				input(GTMaterialGen.get(Items.BLAZE_POWDER, 4)) }, 
				totalEu(7500),
				GTMaterialGen.getDust(GTMaterial.DarkAshes, 1), 
				GTMaterialGen.getDust(GTMaterial.Sulfur, 1));

		addRecipe(new IRecipeInput[] { 
				input("sand", 16), 
				tubes(1) }, totalEu(25000),
				GTMaterialGen.getDust(GTMaterial.Silicon, 1), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustClay", 8), 
				tubes(3) }, 
				totalEu(10000),
				GTMaterialGen.getSmallDust(GTMaterial.Lithium, 1), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 2), 
				GTMaterialGen.getDust(GTMaterial.Alumina, 2),
				GTMaterialGen.getFluid(GTMaterial.Sodium, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustCoal", 1) }, 
				totalEu(2000), 
				GTMaterialGen.getDust(GTMaterial.Carbon, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustCharcoal", 1) }, 
				totalEu(2000), 
				GTMaterialGen.getDust(GTMaterial.Carbon, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustObsidian", 4), 
				tubes(3) }, 
				totalEu(2500),
				GTMaterialGen.getSmallDust(GTMaterial.Magnesium, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Iron, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 1),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustAshes", 2) }, 
				totalEu(1250), 
				GTMaterialGen.getDust(GTMaterial.Carbon, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustSaltpeter", 10), 
				tubes(7) }, 
				totalEu(6000),
				GTMaterialGen.getFluid(GTMaterial.Potassium, 2), 
				GTMaterialGen.getFluid(GTMaterial.Nitrogen, 2), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 3));

		addRecipe(new IRecipeInput[] { 
				input("dustEnderpearl", 10), 
				tubes(16) }, 
				totalEu(65000),
				GTMaterialGen.getFluid(GTMaterial.Nitrogen, 5), 
				GTMaterialGen.getFluid(GTMaterial.Beryllium, 1), 
				GTMaterialGen.getFluid(GTMaterial.Potassium, 4),
				GTMaterialGen.getFluid(GTMaterial.Chlorine, 6));

		addRecipe(new IRecipeInput[] { 
				input("dustLazurite", 29), 
				tubes(8) }, 
				totalEu(150000),
				GTMaterialGen.getDust(GTMaterial.Alumina, 3), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3), 
				GTMaterialGen.getFluid(GTMaterial.Calcium, 4),
				GTMaterialGen.getFluid(GTMaterial.Sodium, 4));

		addRecipe(new IRecipeInput[] { 
				input("dustPyrite", 3) }, 
				totalEu(16000),
				GTMaterialGen.getIc2(Ic2Items.ironDust, 1), 
				GTMaterialGen.getDust(GTMaterial.Sulfur, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustCalcite", 10), 
				tubes(5) }, 
				totalEu(56000),
				GTMaterialGen.getFluid(GTMaterial.Calcium, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 2), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 3));

		addRecipe(new IRecipeInput[] { 
				input("dustSodalite", 23), tubes(5) }, 
				totalEu(121500),
				GTMaterialGen.getFluid(GTMaterial.Sodium, 4), 
				GTMaterialGen.getDust(GTMaterial.Alumina, 3), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3),
				GTMaterialGen.getFluid(GTMaterial.Chlorine, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustFlint", 8) }, 
				totalEu(5000), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 1),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustCinnabar", 3), 
				tubes(1) }, 
				totalEu(16000),
				GTMaterialGen.getFluid(GTMaterial.Mercury, 1), 
				GTMaterialGen.getDust(GTMaterial.Sulfur, 1), 
				GTMaterialGen.get(Items.REDSTONE, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustSphalerite", 3) }, 
				totalEu(15000), 
				GTMaterialGen.getDust(GTMaterial.Zinc, 1),
				GTMaterialGen.getDust(GTMaterial.Germanium, 1), 
				GTMaterialGen.getDust(GTMaterial.Sulfur, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustRuby", 6), 
				tubes(3) }, 
				totalEu(25000),
				GTMaterialGen.getDust(GTMaterial.Alumina, 2), 
				GTMaterialGen.getDust(GTMaterial.Chrome, 1), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 3));

		addRecipe(new IRecipeInput[] { 
				input("dustSapphire", 5), 
				tubes(3) }, 
				totalEu(20000),
				GTMaterialGen.getDust(GTMaterial.Alumina, 2), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 3));

		addRecipe(new IRecipeInput[] { 
				input("dustGreenSapphire", 1) }, 
				totalEu(5000),
				GTMaterialGen.getDust(GTMaterial.Sapphire, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustEmerald", 21), 
				tubes(12) }, 
				totalEu(30000),
				GTMaterialGen.getDust(GTMaterial.Alumina, 5), 
				GTMaterialGen.getFluid(GTMaterial.Beryllium, 1), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 6),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 9));

		addRecipe(new IRecipeInput[] { 
				input("dustOlivine", 7), 
				tubes(2) }, 
				totalEu(36000),
				GTMaterialGen.getDust(GTMaterial.Magnesium, 2), 
				GTMaterialGen.getIc2(Ic2Items.ironDust, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 1),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustGalena", 3) }, 
				totalEu(120000),
				GTMaterialGen.getIc2(Ic2Items.silverDust, 1), 
				GTMaterialGen.getDust(GTMaterial.Lead, 1), 
				GTMaterialGen.getDust(GTMaterial.Sulfur, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustTantalite", 3) }, 
				totalEu(120000),
				GTMaterialGen.getDust(GTMaterial.Tantalum, 1), 
				GTMaterialGen.getDust(GTMaterial.Niobium, 1), 
				GTMaterialGen.getDust(GTMaterial.Manganese, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustPyrope", 14), 
				tubes(6) }, 
				totalEu(90000),
				GTMaterialGen.getDust(GTMaterial.Magnesium, 3), 
				GTMaterialGen.getDust(GTMaterial.Alumina, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 6));

		addRecipe(new IRecipeInput[] { 
				input("dustAlmandine", 14), 
				tubes(6) }, 
				totalEu(82000),
				GTMaterialGen.getIc2(Ic2Items.ironDust, 3), 
				GTMaterialGen.getDust(GTMaterial.Alumina, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 6));

		addRecipe(new IRecipeInput[] { 
				input("dustSpessartine", 14), 
				tubes(6) }, 
				totalEu(92000),
				GTMaterialGen.getDust(GTMaterial.Alumina, 2), 
				GTMaterialGen.getDust(GTMaterial.Manganese, 3), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 6));

		addRecipe(new IRecipeInput[] { 
				input("dustAndradite", 14), 
				tubes(9) }, 
				totalEu(64000),
				GTMaterialGen.getFluid(GTMaterial.Calcium, 3), 
				GTMaterialGen.getIc2(Ic2Items.ironDust, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 6));

		addRecipe(new IRecipeInput[] { 
				input("dustGrossular", 14), 
				tubes(9) }, 
				totalEu(64000),
				GTMaterialGen.getFluid(GTMaterial.Calcium, 3), 
				GTMaterialGen.getDust(GTMaterial.Alumina, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 6));

		addRecipe(new IRecipeInput[] { 
				input("dustUvarovite", 14), 
				tubes(9) }, 
				totalEu(110000),
				GTMaterialGen.getFluid(GTMaterial.Calcium, 3), 
				GTMaterialGen.getDust(GTMaterial.Chrome, 2), 
				GTMaterialGen.getDust(GTMaterial.Silicon, 3), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 6));

		addRecipe(new IRecipeInput[] { 
				input(GTMaterialGen.getFluid(GTMaterial.Methane, 5)) }, 
				totalEu(7500),
				GTMaterialGen.getFluid(GTMaterial.Hydrogen, 4), 
				GTMaterialGen.getDust(GTMaterial.Carbon, 1));

		addRecipe(new IRecipeInput[] {
				input("dustGarnierite", 2) }, 
				totalEu(16000),
				GTMaterialGen.getDust(GTMaterial.Nickel, 1), 
				GTMaterialGen.getSmallDust(GTMaterial.Nickel, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Cobalt, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustPyrolusite", 3), 
				tubes(1) }, 
				totalEu(16000),
				GTMaterialGen.getDust(GTMaterial.Manganese, 2), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustMolybdenite", 3), 
				tubes(2) }, 
				totalEu(16000),
				GTMaterialGen.getDust(GTMaterial.Molybdenum, 2), 
				GTMaterialGen.getDust(GTMaterial.Sulfur, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustChromite", 7), 
				tubes(3) }, 
				totalEu(16000),
				GTMaterialGen.getIc2(Ic2Items.ironDust, 2), 
				GTMaterialGen.getDust(GTMaterial.Chrome, 2), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 3));

		addRecipe(new IRecipeInput[] { 
				input("dustTantalite", 3), 
				tubes(1) }, 
				totalEu(12000),
				GTMaterialGen.getDust(GTMaterial.Tantalum, 1), 
				GTMaterialGen.getDust(GTMaterial.Niobium, 1), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustLimonite", 3), 
				tubes(2) }, 
				totalEu(12000),
				GTMaterialGen.getIc2(Ic2Items.ironDust, 1), 
				GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustMagnetite", 2), 
				tubes(1) }, 
				totalEu(8000),
				GTMaterialGen.getIc2(Ic2Items.ironDust, 1), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustCassiterite", 2), 
				tubes(1) }, 
				totalEu(6000),
				GTMaterialGen.getIc2(Ic2Items.tinDust, 1), 
				GTMaterialGen.getSmallDust(GTMaterial.Tantalum, 1), 
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustMalachite", 10), 
				tubes(7) }, 
				totalEu(12000),
				GTMaterialGen.getIc2(Ic2Items.copperDust, 2), 
				GTMaterialGen.getDust(GTMaterial.Calcite, 1), 
				GTMaterialGen.getModFluid("water", 3),
				GTMaterialGen.getFluid(GTMaterial.Oxygen, 4));
		
		addRecipe(new IRecipeInput[] { 
				input("dustLithiumChloride", 2), 
				tubes(1) }, 
				totalEu(96000),
				GTMaterialGen.getDust(GTMaterial.Lithium, 1), 
				GTMaterialGen.getFluid(GTMaterial.Chlorine, 1));

		// Aluminium recipes

		addRecipe(new IRecipeInput[] { 
				input("dustGraphite", 3), 
				input("dustAlumina", 10),
				input(GTMaterialGen.getFluid(GTMaterial.Cryolite, 1)) }, 
				totalEu(256000), 
				GTMaterialGen.getDust(GTMaterial.Aluminium, 4));
		
		addRecipe(new IRecipeInput[] { 
				input("dustCarbon", 1), 
				input("dustAlumina", 10),
				input(GTMaterialGen.getFluid(GTMaterial.Cryolite, 1)) }, 
				totalEu(256000), 
				GTMaterialGen.getDust(GTMaterial.Aluminium, 4));
	}
	// @formatter:on

	public static IRecipeModifier[] totalEu(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 192) - 100) };
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

}
