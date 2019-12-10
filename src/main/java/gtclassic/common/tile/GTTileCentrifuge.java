package gtclassic.common.tile;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeMachineHandler;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.tile.GTTileBaseMachine;
import gtclassic.common.GTItems;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerCentrifuge;
import gtclassic.common.gui.GTGuiMachine.GTIndustrialCentrifugeGui;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
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
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTTileCentrifuge extends GTTileBaseMachine {

	public static final int slotFuel = 8;
	protected static final int[] slotInputs = { 0, 1 };
	protected static final int[] slotOutputs = { 2, 3, 4, 5, 6, 7 };
	public IFilter filter = new MachineFilter(this);
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/industrialcentrifuge.png");
	private static final int defaultEu = 16;
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.centrifuge", defaultEu);

	public GTTileCentrifuge() {
		super(9, 4, defaultEu, 100, 32);
		this.maxEnergy = 10000;
		setFuelSlot(slotFuel);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.UP, 0);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, 1);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
		handler.registerInputFilter(filter, slotInputs);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
		handler.registerSlotType(SlotType.Fuel, slotFuel);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.INDUSTRIAL_CENTRIFUGE;
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		return new LinkedHashSet<>(Arrays.asList(UpgradeType.values()));
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerCentrifuge(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTIndustrialCentrifugeGui.class;
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
		return slot <= 1;
	}

	@Override
	public int[] getOutputSlots() {
		return slotOutputs;
	}

	@Override
	public GTRecipeMultiInputList getRecipeList() {
		return RECIPE_LIST;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	@Override
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.extractorOp;
	}

	public static void init() {
		Item tube = GTItems.testTube;
		addCustomRecipe(GTMaterialGen.getTube(GTMaterial.Oxygen, 1), GTMaterialGen.getIc2(Ic2Items.emptyCell, 2), totalEu(5000), GTMaterialGen.getIc2(Ic2Items.airCell, 2), GTMaterialGen.get(tube, 1));
		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getIc2(Ic2Items.airCell, 16)),
				tubes(16) }, totalEu(1000000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 16), GTMaterialGen.getTube(GTMaterial.Nitrogen, 9), GTMaterialGen.getTube(GTMaterial.Oxygen, 4), GTMaterialGen.getTube(GTMaterial.Helium, 1), GTMaterialGen.getTube(GTMaterial.Neon, 1), GTMaterialGen.getTube(GTMaterial.Argon, 1));
		addRecipe(GTMaterialGen.getWater(6), 0, totalEu(90000), GTMaterialGen.getTube(GTMaterial.Hydrogen, 4), GTMaterialGen.getTube(GTMaterial.Oxygen, 2));
		addRecipe(GTMaterialGen.getIc2(Ic2Items.waterCell, 6), 6, totalEu(90000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 6), GTMaterialGen.getTube(GTMaterial.Hydrogen, 4), GTMaterialGen.getTube(GTMaterial.Oxygen, 2));
		addRecipe("dustCoal", 4, 0, totalEu(7500), GTMaterialGen.getDust(GTMaterial.Carbon, 8));
		addRecipe("logRubber", 16, 4, totalEu(25000), GTMaterialGen.getDust(GTMaterial.Carbon, 8), GTMaterialGen.getIc2(Ic2Items.stickyResin, 8), GTMaterialGen.getIc2(Ic2Items.plantBall, 6), GTMaterialGen.getTube(GTMaterial.Methane, 4));
		addRecipe(GTMaterialGen.getTube(GTMaterial.Hydrogen, 4), 0, totalEu(6000), GTMaterialGen.get(tube, 3), GTMaterialGen.getTube(GTMaterial.Deuterium, 1));
		addRecipe(GTMaterialGen.getTube(GTMaterial.Deuterium, 4), 0, totalEu(6000), GTMaterialGen.get(tube, 3), GTMaterialGen.getTube(GTMaterial.Tritium, 1));
		addRecipe(GTMaterialGen.getTube(GTMaterial.Helium, 16), 0, totalEu(18000), GTMaterialGen.get(tube, 15), GTMaterialGen.getTube(GTMaterial.Helium3, 1));
		addRecipe("dustRuby", 9, 3, totalEu(25000), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getDust(GTMaterial.Chrome, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 3));
		addRecipe("dustSapphire", 8, 3, totalEu(20000), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getTube(GTMaterial.Oxygen, 3));
		addRecipe("dustEmerald", 29, 12, totalEu(30000), GTMaterialGen.getTube(GTMaterial.Oxygen, 9), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getTube(GTMaterial.Beryllium, 3), GTMaterialGen.getDust(GTMaterial.Silicon, 6));
		addRecipe("dustEnderPearl", 16, 16, totalEu(65000), GTMaterialGen.getTube(GTMaterial.Chlorine, 6), GTMaterialGen.getTube(GTMaterial.Nitrogen, 5), GTMaterialGen.getTube(GTMaterial.Beryllium, 1), GTMaterialGen.getTube(GTMaterial.Potassium, 4));
		addRecipe("dustEnderEye", 16, 0, totalEu(35000), GTMaterialGen.getDust(GTMaterial.EnderPearl, 8), GTMaterialGen.get(Items.BLAZE_POWDER, 8));
		addRecipe("dustLazurite", 28, 16, totalEu(295000), GTMaterialGen.getTube(GTMaterial.Sodium, 8), GTMaterialGen.getDust(GTMaterial.Aluminium, 6), GTMaterialGen.getDust(GTMaterial.Silicon, 6), GTMaterialGen.getTube(GTMaterial.Calcium, 8));
		addRecipe("dustPyrite", 2, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		addRecipe("dustCalcite", 10, 5, totalEu(50000), GTMaterialGen.getTube(GTMaterial.Calcium, 2), GTMaterialGen.getDust(GTMaterial.Carbon, 2), GTMaterialGen.getTube(GTMaterial.Oxygen, 3));
		addRecipe("dustSodalite", 11, 5, totalEu(115000), GTMaterialGen.getTube(GTMaterial.Chlorine, 1), GTMaterialGen.getTube(GTMaterial.Sodium, 4), GTMaterialGen.getDust(GTMaterial.Aluminium, 3), GTMaterialGen.getDust(GTMaterial.Silicon, 3));
		addRecipe("dustBauxite", 24, 16, totalEu(250000), GTMaterialGen.getTube(GTMaterial.Oxygen, 6), GTMaterialGen.getDust(GTMaterial.Aluminium, 16), GTMaterialGen.getDust(GTMaterial.Titanium, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 10));
		addRecipe(GTMaterialGen.get(Items.MAGMA_CREAM, 1), 0, totalEu(2500), GTMaterialGen.get(Items.BLAZE_POWDER, 1), GTMaterialGen.get(Items.SLIME_BALL, 1));
		addRecipe("dirt", 64, 0, totalEu(50000), GTMaterialGen.get(Blocks.SAND, 32), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 2), GTMaterialGen.getIc2(Ic2Items.plantBall, 2), GTMaterialGen.get(Items.CLAY_BALL, 2));
		addRecipe("grass", 64, 0, totalEu(50000), GTMaterialGen.get(Blocks.SAND, 32), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 2), GTMaterialGen.getIc2(Ic2Items.plantBall, 4), GTMaterialGen.get(Items.CLAY_BALL, 2));
		addRecipe(GTMaterialGen.get(Blocks.MYCELIUM, 64), 0, totalEu(62500), GTMaterialGen.get(Blocks.SAND, 32), GTMaterialGen.get(Blocks.BROWN_MUSHROOM, 16), GTMaterialGen.get(Blocks.RED_MUSHROOM, 16), GTMaterialGen.get(Items.CLAY_BALL, 8));
		addRecipe("gemLapis", 64, 0, totalEu(125000), GTMaterialGen.getDust(GTMaterial.Sodalite, 8), GTMaterialGen.getDust(GTMaterial.Lazurite, 48), GTMaterialGen.getDust(GTMaterial.Pyrite, 4), GTMaterialGen.getDust(GTMaterial.Calcite, 4));
		addRecipe(GTMaterialGen.get(Items.BLAZE_POWDER, 8), 0, totalEu(15000), GTMaterialGen.getIc2(Ic2Items.coalDust, 2), GTMaterialGen.get(Items.GUNPOWDER, 1));
		addRecipe(GTMaterialGen.get(Blocks.SAND, 32), 1, totalEu(50000), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
		addRecipe("dustFlint", 8, 1, totalEu(5000), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
		addRecipe("dustClay", 7, 0, totalEu(19000), GTMaterialGen.getDust(GTMaterial.Lithium, 1), GTMaterialGen.getDust(GTMaterial.Silicon, 2), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getTube(GTMaterial.Sodium, 2));
		addRecipe(GTMaterialGen.getIc2(Ic2Items.stickyResin, 8), 0, totalEu(12500), GTMaterialGen.getIc2(Ic2Items.rubber, 28), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 2), GTMaterialGen.getIc2(Ic2Items.plantBall, 2));
		addRecipe("dustGlowstone", 16, 1, totalEu(25000), GTMaterialGen.get(Items.REDSTONE, 8), GTMaterialGen.getIc2(Ic2Items.goldDust, 8), GTMaterialGen.getTube(GTMaterial.Helium, 1));
		addRecipe("dustRedstone", 10, 3, totalEu(35000), GTMaterialGen.getTube(GTMaterial.Mercury, 3), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getDust(GTMaterial.Pyrite, 5), GTMaterialGen.getDust(GTMaterial.Ruby, 1));
		addRecipe("dustNetherrack", 64, 0, totalEu(50000), GTMaterialGen.get(Items.GOLD_NUGGET, 4), GTMaterialGen.get(Items.REDSTONE, 4), GTMaterialGen.get(Items.GUNPOWDER, 8), GTMaterialGen.getIc2(Ic2Items.coalDust, 4), GTMaterialGen.getDust(GTMaterial.Sulfur, 4), GTMaterialGen.getDust(GTMaterial.Phosphorus, 2));
		addRecipe(GTMaterialGen.getLava(64), 0, totalEu(250000), GTMaterialGen.get(tube, 64), GTMaterialGen.getIngot(GTMaterial.Electrum, 4), GTMaterialGen.get(Items.IRON_INGOT, 16), GTMaterialGen.getDust(GTMaterial.Tungsten, 4), GTMaterialGen.getDust(GTMaterial.Basalt, 4));
		addRecipe(GTMaterialGen.get(Blocks.LAVA, 64), 0, totalEu(250000), GTMaterialGen.getIngot(GTMaterial.Electrum, 4), GTMaterialGen.get(Items.IRON_INGOT, 16), GTMaterialGen.getDust(GTMaterial.Tungsten, 4), GTMaterialGen.getDust(GTMaterial.Basalt, 4));
		addRecipe(GTMaterialGen.getIc2(Ic2Items.lavaCell, 64), 0, totalEu(250000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 64), GTMaterialGen.getIngot(GTMaterial.Electrum, 4), GTMaterialGen.get(Items.IRON_INGOT, 16), GTMaterialGen.getDust(GTMaterial.Tungsten, 4), GTMaterialGen.getDust(GTMaterial.Basalt, 4));
		addRecipe("endstone", 64, 8, totalEu(100000), GTMaterialGen.get(Blocks.SAND, 48), GTMaterialGen.getTube(GTMaterial.Helium3, 4), GTMaterialGen.getTube(GTMaterial.Helium, 4), GTMaterialGen.getDust(GTMaterial.Tungsten, 1));
		/** New Recipes I added **/
		addRecipe("stoneGranite", 4, 0, totalEu(24000), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getDust(GTMaterial.Flint, 1), GTMaterialGen.getIc2(Ic2Items.clayDust, 1));
		addRecipe("stoneDiorite", 16, 0, totalEu(36000), GTMaterialGen.getDust(GTMaterial.Nickel, 1));
		addRecipe("dustBasalt", 16, 0, totalEu(24000), GTMaterialGen.getDust(GTMaterial.Carbon, 1), GTMaterialGen.getDust(GTMaterial.Emerald, 1), GTMaterialGen.getDust(GTMaterial.Calcite, 3), GTMaterialGen.getDust(GTMaterial.Flint, 8));
		addRecipe("dustObsidian", 8, 6, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.ironDust, 2), GTMaterialGen.getDust(GTMaterial.Silicon, 2), GTMaterialGen.getTube(GTMaterial.Oxygen, 4));
		addRecipe(GTMaterialGen.get(Items.QUARTZ, 1), 2, totalEu(8000), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 2));
		addRecipe(GTMaterialGen.get(Blocks.MAGMA, 64), 64, totalEu(3000), GTMaterialGen.getLava(64));
		addRecipe("dustElectrum", 2, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.silverDust, 1), GTMaterialGen.getIc2(Ic2Items.goldDust, 1));
		addRecipe("dustInvar", 3, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.ironDust, 2), GTMaterialGen.getDust(GTMaterial.Nickel, 1));
		addRecipe("dustBronze", 4, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.tinDust, 1), GTMaterialGen.getIc2(Ic2Items.copperDust, 3));
		addRecipe("dustPlatinum", 9, 0, totalEu(135000), GTMaterialGen.getDust(GTMaterial.Iridium, 1), GTMaterialGen.getDust(GTMaterial.Nickel, 2));
		addRecipe(GTMaterialGen.get(Items.ROTTEN_FLESH, 16), 4, totalEu(6000), GTMaterialGen.getTube(GTMaterial.Methane, 4), GTMaterialGen.get(Items.LEATHER, 4), GTMaterialGen.get(Items.SLIME_BALL, 1));
		addRecipe(GTMaterialGen.get(Blocks.SOUL_SAND, 12), 0, totalEu(12000), GTMaterialGen.get(Blocks.SAND, 11), GTMaterialGen.getTube(GTMaterial.Oil, 1));
		/*
		 * Recipes solely focused on getting methane from various things
		 */
		addMethaneRecipe("listAllmeatraw", 12);
		addMethaneRecipe("listAllmeatcooked", 12);
		addMethaneRecipe("listAllfishraw", 12);
		addMethaneRecipe("listAllfishcooked", 12);
		addMethaneRecipe("listAllfruit", 32);
		addMethaneRecipe("listAllveggie", 16);
		addMethaneRecipe("listAllcookie", 64);
		addMethaneRecipe(GTMaterialGen.get(Items.MUSHROOM_STEW, 16));
		addMethaneRecipe(GTMaterialGen.get(Items.BREAD, 64));
		addMethaneRecipe(GTMaterialGen.get(Items.MELON, 64));
		addMethaneRecipe(GTMaterialGen.get(Items.SPIDER_EYE, 32));
		addMethaneRecipe(GTMaterialGen.get(Items.POISONOUS_POTATO, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.BAKED_POTATO, 24));
		addMethaneRecipe(GTMaterialGen.get(Items.CAKE, 8));
		addMethaneRecipe(GTMaterialGen.get(Blocks.BROWN_MUSHROOM_BLOCK, 12));
		addMethaneRecipe(GTMaterialGen.get(Blocks.RED_MUSHROOM_BLOCK, 12));
		addMethaneRecipe(GTMaterialGen.get(Blocks.BROWN_MUSHROOM, 32));
		addMethaneRecipe(GTMaterialGen.get(Blocks.RED_MUSHROOM, 32));
		addMethaneRecipe(GTMaterialGen.get(Items.NETHER_WART, 32));
		addMethaneRecipe(GTMaterialGen.getIc2(Ic2Items.terraWart, 16));
		addMethaneRecipe(GTMaterialGen.getIc2(Ic2Items.plantBall, 6));
		addMethaneRecipe(GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 4));
	}

	public static void addCustomRecipe(ItemStack stack0, ItemStack stack1, IRecipeModifier[] modifiers,
			ItemStack... outputs) {
		addRecipe(new IRecipeInput[] { new RecipeInputItemStack(stack0),
				new RecipeInputItemStack(stack1), }, modifiers, outputs);
	}

	public static void addCustomRecipe(String input, int amount, ItemStack stack, IRecipeModifier[] modifiers,
			ItemStack... outputs) {
		addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount),
				new RecipeInputItemStack(stack), }, modifiers, outputs);
	}

	public static void addMethaneRecipe(ItemStack stack) {
		addRecipe(stack, 1, totalEu(50000), GTMaterialGen.getTube(GTMaterial.Methane, 1));
	}

	public static void addMethaneRecipe(String input, int amount) {
		addRecipe(input, amount, 1, totalEu(50000), GTMaterialGen.getTube(GTMaterial.Methane, 1));
	}

	public static void addRecipe(ItemStack stack, int cells, IRecipeModifier[] modifiers, ItemStack... outputs) {
		if (cells > 0) {
			addRecipe(new IRecipeInput[] { new RecipeInputItemStack(stack),
					new RecipeInputItemStack(GTMaterialGen.get(GTItems.testTube, cells)) }, modifiers, outputs);
		} else {
			addRecipe(new IRecipeInput[] { new RecipeInputItemStack(stack) }, modifiers, outputs);
		}
	}

	public static void addRecipe(String input, int amount, int cells, IRecipeModifier[] modifiers,
			ItemStack... outputs) {
		if (cells > 0) {
			addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount),
					new RecipeInputItemStack(GTMaterialGen.get(GTItems.testTube, cells)) }, modifiers, outputs);
		} else {
			addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount) }, modifiers, outputs);
		}
	}

	public static IRecipeModifier[] totalEu(int total) {
		return GTRecipeMachineHandler.totalEu(RECIPE_LIST, total);
	}

	public static void addRecipe(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
		GTRecipeMachineHandler.addRecipe(RECIPE_LIST, inputs, modifiers, outputs);
	}
}
