package gtclassic.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.container.GTContainerSmelter;
import gtclassic.gui.GTGuiMachine.GTElectricSmelterGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileSmelter extends GTTileBaseMachine {

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.smelter");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/electricsmelter.png");

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput = 2;
	public static final int slotFuel = 3;

	public GTTileSmelter() {
		super(4, 2, 16, 200, 32);
		setFuelSlot(slotFuel);
		maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput0, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.DOWN, slotFuel);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), slotOutput);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE),
				new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
		handler.registerSlotType(SlotType.Fuel, slotFuel);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1);
		handler.registerSlotType(SlotType.Output, slotOutput);
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
		return new GTContainerSmelter(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTElectricSmelterGui.class;
	}

	@Override
	public int[] getInputSlots() {
		return new int[] { slotInput0, slotInput1 };
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

	@Override
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.electricFurnaceLoop;
	}

	/**
	 * Recipes not handled by the iterator class
	 */
	// @formatter:off
	public static void init() {
		//Alloy recipes
		addAlloyRecipe("Tin", 1, "Copper", 3, 
				GTMaterialGen.getIc2(Ic2Items.bronzeIngot, 4));
		
		addAlloyRecipe("Zinc", 1, "Copper", 3, 
				GTMaterialGen.getIngot(GTMaterial.Brass, 4));
		
		addAlloyRecipe("Bismuth", 1, "Brass", 3, 
				GTMaterialGen.getIngot(GTMaterial.BismuthBronze, 4));
		
		addAlloyRecipe("Iron", 2, "Nickel", 1, 
				GTMaterialGen.getIngot(GTMaterial.Invar, 3));
		
		addAlloyRecipe("Gold", 1, "Silver", 1, 
				GTMaterialGen.getIngot(GTMaterial.Electrum, 2));
		
		addAlloyRecipe("Copper", 1, "Nickel", 1, 
				GTMaterialGen.getIngot(GTMaterial.Constantan, 2));
		
		addAlloyRecipe("Nickel", 4, "Chrome", 1, 
				GTMaterialGen.getIngot(GTMaterial.Nichrome, 5));
		
		addRecipe("dustRedstone", 4, "ingotCopper", 1,
				GTMaterialGen.getIngot(GTMaterial.RedAlloy, 1));
		
		addRecipe("dustRedstone", 4, "dustCopper", 1,
				GTMaterialGen.getIngot(GTMaterial.RedAlloy, 1));

		//Smelting oriented recipes
		addRecipe("sand", 1, GTMaterialGen.get(GTItems.moldBlock),
				GTMaterialGen.get(Blocks.GLASS, 1));
		
		addRecipe("sand", 5, GTMaterialGen.get(GTItems.moldTube),
				GTMaterialGen.get(GTItems.testTube, 32));
		
		addRecipe("blockGlass", 5, GTMaterialGen.get(GTItems.moldTube),
				GTMaterialGen.get(GTItems.testTube, 32));

		addRecipe("dustResin", 1, GTMaterialGen.get(GTItems.woodPlate),
				GTMaterialGen.get(GTBlocks.driedResin, 1));
		
		addRecipe("dustResin", 1, "dustSulfur", 1, 
				GTMaterialGen.getIc2(Ic2Items.rubber, 1));
		
		addRecipe("dustPlastic", 9, GTMaterialGen.get(GTItems.moldBlock),
				GTMaterialGen.get(GTBlocks.casingPlastic1x, 1));

		addRecipe("dustSilicon", 1, GTMaterialGen.get(GTItems.testTube),
				GTMaterialGen.getFluid(GTMaterial.Silicon, 1));
		
		addRecipe("dustCryolite", 1, GTMaterialGen.get(GTItems.testTube),
				GTMaterialGen.getFluid(GTMaterial.Cryolite, 1));
		
		addRecipe("dustCinnabar", 4, GTMaterialGen.get(GTItems.testTube),
				GTMaterialGen.getFluid(GTMaterial.Mercury, 1));

		addRecipe("ingotRefinedIron", 1, GTMaterialGen.get(GTItems.moldPlate),
				GTMaterialGen.getPlate(GTMaterial.RefinedIron, 1));
		
		addRecipe("ingotRefinedIron", 1, GTMaterialGen.get(GTItems.moldStick),
				GTMaterialGen.getStick(GTMaterial.RefinedIron, 2));

		//Molding recipes not covered by iteration
		addMoldingRecipe("Iron", 9, GTMaterialGen.get(GTItems.moldBlock), 
				GTMaterialGen.get(Blocks.IRON_BLOCK, 1));
		
		addMoldingRecipe("Gold", 9, GTMaterialGen.get(GTItems.moldBlock), 
				GTMaterialGen.get(Blocks.GOLD_BLOCK, 1));
		
		addMoldingRecipe("Bronze", 9, GTMaterialGen.get(GTItems.moldBlock), 
				GTMaterialGen.getIc2(Ic2Items.bronzeBlock, 1));
		
		addMoldingRecipe("Silver", 9, GTMaterialGen.get(GTItems.moldBlock), 
				GTMaterialGen.getIc2(Ic2Items.silverBlock, 1));
		
		addMoldingRecipe("Copper", 9, GTMaterialGen.get(GTItems.moldBlock), 
				GTMaterialGen.getIc2(Ic2Items.copperBlock, 1));
		
		addMoldingRecipe("Tin", 9, GTMaterialGen.get(GTItems.moldBlock), 
				GTMaterialGen.getIc2(Ic2Items.tinBlock, 1));

		addMoldingRecipe("Iron", 1, GTMaterialGen.get(GTItems.moldNugget), 
				GTMaterialGen.get(Items.IRON_NUGGET, 9));
		
		addMoldingRecipe("Gold", 1, GTMaterialGen.get(GTItems.moldNugget), 
				GTMaterialGen.get(Items.GOLD_NUGGET, 9));

		addMoldingRecipe("Tin", 1, GTMaterialGen.get(GTItems.moldCable),
				GTMaterialGen.getIc2(Ic2Items.tinCable, 3));
		
		addMoldingRecipe("Copper", 1, GTMaterialGen.get(GTItems.moldCable),
				GTMaterialGen.getIc2(Ic2Items.copperCable, 2));
		
		addMoldingRecipe("Bronze", 1, GTMaterialGen.get(GTItems.moldCable),
				GTMaterialGen.getIc2(Ic2Items.bronzeCable, 2));
		
		addMoldingRecipe("Gold", 1, GTMaterialGen.get(GTItems.moldCable),
				GTMaterialGen.getIc2(Ic2Items.goldCable, 4));
		
		addRecipe("ingotRefinedIron", 1, GTMaterialGen.get(GTItems.moldCable),
				GTMaterialGen.getIc2(Ic2Items.ironCable, 4));
	}
	// @formatter:on

	/**
	 * Simple utility to generate recipe variants for the Alloy Smelter
	 */
	public static void addAlloyRecipe(String input1, int amount1, String input2, int amount2, ItemStack output) {
		addRecipe("ingot" + input1, amount1, "ingot" + input2, amount2, output);
		addRecipe("dust" + input1, amount1, "dust" + input2, amount2, output);
		addRecipe("dust" + input1, amount1, "ingot" + input2, amount2, output);
		addRecipe("ingot" + input1, amount1, "dust" + input2, amount2, output);
	}

	/**
	 * Simple utility to generate mold recipe variants for the Alloy Smelter
	 */
	public static void addMoldingRecipe(String input1, int amount1, ItemStack input2, ItemStack output) {
		addRecipe("ingot" + input1, amount1, input2, output);
		addRecipe("dust" + input1, amount1, input2, output);
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

}
