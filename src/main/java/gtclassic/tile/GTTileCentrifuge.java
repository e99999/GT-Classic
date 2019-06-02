package gtclassic.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.container.GTContainerCentrifuge;
import gtclassic.gui.GTGuiMachine.GTIndustrialCentrifugeGui;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileCentrifuge extends GTTileBaseMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput0 = 2;
	public static final int slotOutput1 = 3;
	public static final int slotOutput2 = 4;
	public static final int slotOutput3 = 5;
	public static final int slotOutput4 = 6;
	public static final int slotOutput5 = 7;
	public static final int slotFuel = 8;

	protected static final int[] slotInputs = { 0, 1 };
	protected static final int[] slotOutputs = { 2, 3, 4, 5, 6, 7 };

	IFilter filter = new MachineFilter(this);

	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/industrialcentrifuge.png");

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.centrifuge");

	public GTTileCentrifuge() {
		super(9, 2, 12, 100, 32);
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
		handler.registerInputFilter(filter, slotInputs);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
		handler.registerSlotType(SlotType.Fuel, slotFuel);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	@Override
	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.left(1).asBlockPos());
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
		return Ic2Sounds.extractorOp;
	}

	public static void init() {

		Item tube = GTItems.testTube;

		addRecipe("logRubber", 16, 4, totalEu(25000), GTMaterialGen.getDust(GTMaterial.Wood, 8), GTMaterialGen.getIc2(Ic2Items.stickyResin, 8), GTMaterialGen.getIc2(Ic2Items.plantBall, 6), GTMaterialGen.getFluid(GTMaterial.Methane, 4));

		addRecipe(GTMaterialGen.getFluid(GTMaterial.Hydrogen, 4), 0, totalEu(6000), GTMaterialGen.get(tube, 3), GTMaterialGen.getFluid(GTMaterial.Deuterium, 1));

		addRecipe(GTMaterialGen.getFluid(GTMaterial.Deuterium, 4), 0, totalEu(6000), GTMaterialGen.get(tube, 3), GTMaterialGen.getFluid(GTMaterial.Tritium, 1));

		addRecipe(GTMaterialGen.getFluid(GTMaterial.Helium, 16), 0, totalEu(18000), GTMaterialGen.get(tube, 15), GTMaterialGen.getFluid(GTMaterial.Helium3, 1));

		addRecipe("dustGreenSapphire", 4, 0, totalEu(15000), GTMaterialGen.getDust(GTMaterial.Sapphire, 4));

		addRecipe("dustEnderEye", 16, 0, totalEu(35000), GTMaterialGen.getDust(GTMaterial.EnderPearl, 8), GTMaterialGen.get(Items.BLAZE_POWDER, 8));

		addRecipe(GTMaterialGen.get(Items.MAGMA_CREAM, 1), 0, totalEu(2500), GTMaterialGen.get(Items.BLAZE_POWDER, 1), GTMaterialGen.get(Items.SLIME_BALL, 1));

		addRecipe("dirt", 16, 0, totalEu(12500), GTMaterialGen.get(Blocks.SAND, 8), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 1), GTMaterialGen.getIc2(Ic2Items.plantBall, 1), GTMaterialGen.get(Items.CLAY_BALL, 1));

		addRecipe("grass", 16, 0, totalEu(12500), GTMaterialGen.get(Blocks.SAND, 8), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 2), GTMaterialGen.getIc2(Ic2Items.plantBall, 2), GTMaterialGen.get(Items.CLAY_BALL, 1));

		addRecipe(GTMaterialGen.get(Blocks.MYCELIUM, 8), 0, totalEu(8250), GTMaterialGen.get(Blocks.SAND, 4), GTMaterialGen.get(Blocks.BROWN_MUSHROOM, 2), GTMaterialGen.get(Blocks.RED_MUSHROOM, 2), GTMaterialGen.get(Items.CLAY_BALL, 1));

		addRecipe(GTMaterialGen.get(Items.BLAZE_POWDER, 8), 0, totalEu(15000), GTMaterialGen.getIc2(Ic2Items.coalDust, 2), GTMaterialGen.get(Items.GUNPOWDER, 1));

		addRecipe("dustGlowstone", 16, 1, totalEu(25000), GTMaterialGen.get(Items.REDSTONE, 8), GTMaterialGen.getIc2(Ic2Items.goldDust, 8));

		addRecipe(GTMaterialGen.get(Items.GOLDEN_APPLE, 1), 1, totalEu(50000), GTMaterialGen.getFluid(GTMaterial.Methane, 1), GTMaterialGen.get(Items.GOLD_INGOT, 8));

		addRecipe(GTMaterialGen.get(Items.GOLDEN_CARROT, 1), 1, totalEu(50000), GTMaterialGen.getFluid(GTMaterial.Methane, 1), GTMaterialGen.get(Items.GOLD_NUGGET, 6));

		addRecipe(GTMaterialGen.get(Items.SPECKLED_MELON, 1), 1, totalEu(50000), GTMaterialGen.getFluid(GTMaterial.Methane, 1), GTMaterialGen.get(Items.GOLD_NUGGET, 6));

		addRecipe("dustNetherrack", 16, 0, totalEu(12000), GTMaterialGen.get(Items.GOLD_NUGGET, 1), GTMaterialGen.get(Items.REDSTONE, 1), GTMaterialGen.getDust(GTMaterial.Sulfur, 4), GTMaterialGen.getIc2(Ic2Items.coalDust, 1));

		addRecipe(GTMaterialGen.get(Blocks.LAVA, 16), 0, totalEu(75000), GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.getIc2(Ic2Items.copperIngot, 4), GTMaterialGen.getDust(GTMaterial.Tungstate, 1));

		addRecipe(GTMaterialGen.getIc2(Ic2Items.lavaCell, 16), 0, totalEu(75000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 16), GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.getIc2(Ic2Items.copperIngot, 4), GTMaterialGen.getDust(GTMaterial.Tungstate, 1));

		addRecipe(GTMaterialGen.getModFluid("lava", 16), 0, totalEu(75000), GTMaterialGen.get(GTItems.testTube, 16), GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.getIc2(Ic2Items.copperIngot, 4), GTMaterialGen.getDust(GTMaterial.Tungstate, 1));

		addRecipe(GTMaterialGen.get(Blocks.MAGMA, 16), 0, totalEu(75000), GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.getIc2(Ic2Items.copperIngot, 4), GTMaterialGen.getDust(GTMaterial.Tungstate, 1));

		addRecipe(GTMaterialGen.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 2), 0, totalEu(2500), GTMaterialGen.getIc2(Ic2Items.emptyCell, 2), GTMaterialGen.getDust(GTMaterial.Thorium, 1));

		addRecipe("dustUranium", 4, 0, totalEu(250000), GTMaterialGen.getDust(GTMaterial.Tungstate, 1), GTMaterialGen.getIc2(Ic2Items.reactorUraniumRodSingle, 4), GTMaterialGen.getSmallDust(GTMaterial.Plutonium, 1), GTMaterialGen.getDust(GTMaterial.Thorium, 2));

		addRecipe(GTMaterialGen.getIc2(Ic2Items.reactorReEnrichedUraniumRod, 22), 0, totalEu(100000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 5), GTMaterialGen.getIc2(Ic2Items.reactorNearDepletedUraniumRod, 3), GTMaterialGen.getDust(GTMaterial.Plutonium, 1), GTMaterialGen.getDust(GTMaterial.Thorium, 4));

		addRecipe(GTMaterialGen.getIc2(Ic2Items.stickyResin, 4), 0, totalEu(6500), GTMaterialGen.getIc2(Ic2Items.rubber, 14), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 1), GTMaterialGen.getIc2(Ic2Items.plantBall, 1));

		addRecipe("dustBrass", 1, 0, totalEu(7500), GTMaterialGen.getSmallDust(GTMaterial.Copper, 3), GTMaterialGen.getSmallDust(GTMaterial.Zinc, 1));

		addRecipe("dustBronze", 2, 0, totalEu(7500), GTMaterialGen.getSmallDust(GTMaterial.Copper, 6), GTMaterialGen.getSmallDust(GTMaterial.Tin, 2));

		addRecipe("dustSheldonite", 4, 0, totalEu(32000), GTMaterialGen.getDust(GTMaterial.Platinum, 2), GTMaterialGen.getDust(GTMaterial.Nickel, 1));

		addRecipe("dustElectrum", 2, 0, totalEu(5000), GTMaterialGen.getSmallDust(GTMaterial.Gold, 2), GTMaterialGen.getSmallDust(GTMaterial.Silver, 2));

		addRecipe("dustInvar", 2, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.ironDust, 2), GTMaterialGen.getDust(GTMaterial.Nickel, 1));

		addRecipe("gemLapis", 4, 0, totalEu(7500), GTMaterialGen.getSmallDust(GTMaterial.Sodalite, 2), GTMaterialGen.getDust(GTMaterial.Lazurite, 3), GTMaterialGen.getSmallDust(GTMaterial.Pyrite, 1), GTMaterialGen.getSmallDust(GTMaterial.Calcite, 1));

		addRecipe("dustRedstone", 16, 3, totalEu(35000), GTMaterialGen.getFluid(GTMaterial.Mercury, 3), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getDust(GTMaterial.Pyrite, 5), GTMaterialGen.getDust(GTMaterial.Ruby, 1));

		addRecipe("dustEndstone", 16, 2, totalEu(250000), GTMaterialGen.get(Blocks.SAND, 12), GTMaterialGen.getFluid(GTMaterial.Helium3, 1), GTMaterialGen.getFluid(GTMaterial.Helium, 1), GTMaterialGen.getDust(GTMaterial.Tungstate, 1));

		addRecipe("dustDarkAshes", 2, 0, totalEu(1250), GTMaterialGen.getDust(GTMaterial.Ashes, 1), GTMaterialGen.getDust(GTMaterial.Carbon, 1));

		addRecipe("dustGranite", 4, 0, totalEu(1000), GTMaterialGen.getDust(GTMaterial.Calcite, 2), GTMaterialGen.getDust(GTMaterial.Flint, 1), GTMaterialGen.getIc2(Ic2Items.clayDust, 1));

		addRecipe("dustRedRock", 4, 0, totalEu(1000), GTMaterialGen.getDust(GTMaterial.Calcite, 2), GTMaterialGen.getDust(GTMaterial.Flint, 1), GTMaterialGen.getIc2(Ic2Items.clayDust, 1));

		addRecipe("dustMarble", 8, 0, totalEu(6000), GTMaterialGen.getDust(GTMaterial.Magnesium, 1), GTMaterialGen.getDust(GTMaterial.Calcite, 7));

		addRecipe("dustBasalt", 16, 0, totalEu(10000), GTMaterialGen.getDust(GTMaterial.DarkAshes, 4), GTMaterialGen.getDust(GTMaterial.Olivine, 1), GTMaterialGen.getDust(GTMaterial.Calcite, 3), GTMaterialGen.getDust(GTMaterial.Flint, 8));

		addRecipe("oreBasalt", 12, 0, totalEu(15000), GTMaterialGen.getDust(GTMaterial.Basalt, 9), GTMaterialGen.getDust(GTMaterial.GarnetRed, 1), GTMaterialGen.getDust(GTMaterial.GarnetYellow, 1), GTMaterialGen.getDust(GTMaterial.Zirconium, 1));

		addRecipe("dustRedGarnet", 16, 0, totalEu(15000), GTMaterialGen.getDust(GTMaterial.Pyrope, 3), GTMaterialGen.getDust(GTMaterial.Almandine, 5), GTMaterialGen.getDust(GTMaterial.Spessartine, 8));

		addRecipe("dustYellowGarnet", 16, 0, totalEu(15000), GTMaterialGen.getDust(GTMaterial.Andradite, 5), GTMaterialGen.getDust(GTMaterial.Grossular, 8), GTMaterialGen.getDust(GTMaterial.Uvarovite, 3));

		addRecipe("dustDirtyResin", 4, 0, totalEu(8000), GTMaterialGen.getDust(GTMaterial.Resin, 3), GTMaterialGen.getDust(GTMaterial.Wood, 1));

		addRecipe(GTMaterialGen.get(Blocks.SOUL_SAND, 16), 4, totalEu(6000), GTMaterialGen.get(Blocks.SAND, 12), GTMaterialGen.getFluid(GTMaterial.OilCrude, 4));

		addRecipe(GTMaterialGen.getFluid(GTMaterial.OilCrude, 12), 0, totalEu(12000), GTMaterialGen.getFluid(GTMaterial.Oil, 10), GTMaterialGen.getFluid(GTMaterial.Helium, 1), GTMaterialGen.getDust(GTMaterial.Plastic, 1), GTMaterialGen.get(tube));

		addRecipe("dustPlatinumGroupSludge", 1, 0, totalEu(2500), GTMaterialGen.getSmallDust(GTMaterial.Iridium, 1), GTMaterialGen.getSmallDust(GTMaterial.Osmium, 1), GTMaterialGen.getSmallDust(GTMaterial.Platinum, 2));

		addRecipe("dustRareEarth", 2, 1, totalEu(32000), GTMaterialGen.getDust(GTMaterial.Neodymium, 1), GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1));

		addRecipe(GTMaterialGen.get(Items.ROTTEN_FLESH, 16), 1, totalEu(25000), GTMaterialGen.getFluid(GTMaterial.Methane, 1), GTMaterialGen.get(Items.LEATHER, 6), GTMaterialGen.get(Items.SLIME_BALL, 1));

		/*
		 * Recipes solely focused on getting methane from various things, at some point
		 * i will probably create some sort of dataset to collect all food and similar
		 * items from other mods.
		 */

		addMethaneRecipe(GTMaterialGen.get(Items.APPLE, 32));
		addMethaneRecipe(GTMaterialGen.get(Items.MUSHROOM_STEW, 16));
		addMethaneRecipe(GTMaterialGen.get(Items.BREAD, 64));
		addMethaneRecipe(GTMaterialGen.get(Items.PORKCHOP, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.COOKED_PORKCHOP, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.BEEF, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.COOKED_BEEF, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.FISH, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.COOKED_FISH, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.CHICKEN, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.COOKED_CHICKEN, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.MUTTON, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.COOKED_MUTTON, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.MELON, 64));
		addMethaneRecipe(GTMaterialGen.get(Blocks.PUMPKIN, 16));
		addMethaneRecipe(GTMaterialGen.get(Items.SPIDER_EYE, 32));
		addMethaneRecipe(GTMaterialGen.get(Items.CARROT, 16));
		addMethaneRecipe(GTMaterialGen.get(Items.POTATO, 16));
		addMethaneRecipe(GTMaterialGen.get(Items.POISONOUS_POTATO, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.BAKED_POTATO, 24));
		addMethaneRecipe(GTMaterialGen.get(Items.COOKIE, 64));
		addMethaneRecipe(GTMaterialGen.get(Items.CAKE, 8));
		addMethaneRecipe(GTMaterialGen.get(Blocks.BROWN_MUSHROOM_BLOCK, 12));
		addMethaneRecipe(GTMaterialGen.get(Blocks.RED_MUSHROOM_BLOCK, 12));
		addMethaneRecipe(GTMaterialGen.get(Blocks.BROWN_MUSHROOM, 32));
		addMethaneRecipe(GTMaterialGen.get(Blocks.RED_MUSHROOM, 32));
		addMethaneRecipe(GTMaterialGen.get(Items.NETHER_WART, 32));
		addMethaneRecipe(GTMaterialGen.getIc2(Ic2Items.terraWart, 16));
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
		addRecipe(stack, 1, totalEu(25000), GTMaterialGen.getFluid(GTMaterial.Methane, 1));
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

	public static IRecipeModifier[] totalEu(int amount) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((amount / 12) - 100) };
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 12);
	}

}
