package gtclassic.tile.multi;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import gtclassic.GTMod;
import gtclassic.container.GTContainerBlastFurnace;
import gtclassic.gui.GTGuiMachine.GTBlastFurnaceGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.recipe.GTRecipeProcessing;
import gtclassic.util.GTLang;
import gtclassic.util.GTValues;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTRecipeMultiInputList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GTTileMultiBlastFurnace extends GTTileMultiBaseMachine {

	protected static final int[] slotInputs = { 0, 1, 2, 3 };
	protected static final int[] slotOutputs = { 4, 5, 6, 7 };
	public IFilter filter = new MachineFilter(this);
	public static final IBlockState casingState = GTBlocks.casingReinforced.getDefaultState();
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.blastfurnace");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/blastfurnace.png");
	private static final int defaultEu = 120;
	public static final int COST_TINY = 4000;
	public static final int COST_SMALL = 16000;
	public static final int COST_MED = 32000;
	public static final int COST_HIGH = 64000;

	public GTTileMultiBlastFurnace() {
		super(8, 2, defaultEu, 128);
		maxEnergy = 8192;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerInputFilter(filter, slotInputs);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.BLAST_FURNACE;
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		return new LinkedHashSet<>(Arrays.asList(UpgradeType.values()));
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
		return slotInputs;
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		return new IFilter[] { filter };
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return slot <= 3;
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
		return Ic2Sounds.generatorLoop;
	}

	public static void init() {
		/** Iron Processing **/
		addRecipe(new IRecipeInput[] { input("oreIron", 1),
				input("dustCalcite", 1) }, 12800, GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 3));
		addRecipe(new IRecipeInput[] { input("dustPyrite", 3),
				input("dustCalcite", 1) }, 12800, GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 2));
		/** Bronze **/
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Copper", 3),
				metal("Tin", 1) }, COST_TINY, GTMaterialGen.getIc2(Ic2Items.bronzeIngot, 4));
		/** Electrum **/
		GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Silver", 1),
				metal("Gold", 1) }, COST_TINY, GTMaterialGen.getIngot(GTMaterial.Electrum, 2));
		/** Steel **/
		addRecipe(new IRecipeInput[] { input("dustSteel", 1) }, COST_MED, GTMaterialGen.getIngot(GTMaterial.Steel, 1));
		addRecipe(new IRecipeInput[] { input("ingotRefinedIron", 1),
				input("dustCoal", 2) }, COST_MED, GTMaterialGen.getIngot(GTMaterial.Steel, 1));
		addRecipe(new IRecipeInput[] { input("ingotRefinedIron", 1),
				input("dustCarbon", 1) }, COST_MED, GTMaterialGen.getIngot(GTMaterial.Steel, 1));
	}

	public static void removals() {
		// Remove smelting from mods who dont respect my authority
		if (GTConfig.ingotsRequireBlastFurnace) {
			for (Item item : Item.REGISTRY) {
				NonNullList<ItemStack> items = NonNullList.create();
				item.getSubItems(CreativeTabs.SEARCH, items);
				for (ItemStack stack : items) {
					String oreNames = GTValues.getOreName(stack);
					if (oreNames.contains("ingotIrdium") || oreNames.contains("ingotTungsten")
							|| oreNames.contains("ingotChrome") || oreNames.contains("ingotTitanium")) {
						GTRecipeProcessing.removeSmelting(GTMaterialGen.get(item));
					}
				}
			}
		}
	}

	public static void postInit() {
		/** Titanium **/
		addRecipe(new IRecipeInput[] {
				input("dustTitanium", 1) }, COST_MED, GTMaterialGen.getIngot(GTMaterial.Titanium, 1));
		/** Chrome **/
		addRecipe(new IRecipeInput[] {
				input("dustChrome", 1) }, COST_HIGH, GTMaterialGen.getIngot(GTMaterial.Chrome, 1));
		/** Iridium **/
		addRecipe(new IRecipeInput[] {
				input("dustIridium", 1) }, COST_HIGH, GTMaterialGen.getIngot(GTMaterial.Iridium, 1));
		addRecipe(new IRecipeInput[] {
				input("oreIridium", 1) }, COST_HIGH, GTMaterialGen.getIngot(GTMaterial.Iridium, 1));
		addRecipe(new IRecipeInput[] {
				input(GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1)) }, COST_HIGH, GTMaterialGen.getIngot(GTMaterial.Iridium, 1));
		/** Tungsten **/
		addRecipe(new IRecipeInput[] {
				input("dustTungsten", 1) }, COST_HIGH, GTMaterialGen.getIngot(GTMaterial.Tungsten, 1));
	}

	public static IRecipeModifier[] totalEu(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / defaultEu) - 100) };
	}

	public static void addRecipe(IRecipeInput[] inputs, int totalEu, ItemStack... outputs) {
		IRecipeModifier[] modifiers = totalEu(totalEu);
		NBTTagCompound mods = new NBTTagCompound();
		for (IRecipeModifier modifier : modifiers) {
			modifier.apply(mods);
		}
		addRecipe(ObjectArrayList.wrap(inputs), new MachineOutput(mods, ObjectArrayList.wrap(outputs)));
	}

	static void addRecipe(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getUnlocalizedName(), defaultEu);
	}

	public static void removeRecipe(String id) {
		RECIPE_LIST.removeRecipe(id);
	}

	@Override
	public Map<BlockPos, IBlockState> provideStructure() {
		Map<BlockPos, IBlockState> states = super.provideStructure();
		int3 dir = new int3(getPos(), getFacing());
		for (int i = 0; i < 3; i++) {// above tile
			states.put(dir.up(1).asBlockPos(), casingState);
		}
		states.put(dir.left(1).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.down(1).asBlockPos(), casingState);
		}
		states.put(dir.back(1).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.up(1).asBlockPos(), casingState);
		}
		states.put(dir.right(1).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.down(1).asBlockPos(), casingState);
		}
		states.put(dir.right(1).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.up(1).asBlockPos(), casingState);
		}
		states.put(dir.back(1).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.down(1).asBlockPos(), casingState);
		}
		states.put(dir.left(1).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.up(1).asBlockPos(), casingState);
		}
		states.put(dir.left(1).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.down(1).asBlockPos(), casingState);
		}
		states.put(dir.forward(2).right(2).asBlockPos(), casingState);
		for (int i = 0; i < 3; i++) {
			states.put(dir.up(1).asBlockPos(), casingState);
		}
		return states;
	}

	@Override
	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3)) {
			return false;
		}
		// we doing it "big math" style not block by block
		int3 dir = new int3(getPos(), getFacing());
		for (int i = 0; i < 3; i++) {// above tile
			if (!(isCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.left(1))) {// left
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.down(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.back(1))) {// back
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.right(1))) {// right
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.down(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.right(1))) {// right
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.back(1))) {// back
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.down(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.left(1))) {// left
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.left(1))) {// left
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.down(1)))) {
				return false;
			}
		}
		if (!isCasing(dir.forward(2).right(2))) {// missing front right column
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isCasing(dir.up(1)))) {
				return false;
			}
		}
		return true;
	}

	public boolean isCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == casingState;
	}
}