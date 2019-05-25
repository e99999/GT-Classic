package gtclassic.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.container.GTContainerShredder;
import gtclassic.gui.GTGuiMachine.GTShredderGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.multi.GTTileMultiBaseMachine;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
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
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public class GTTileShredder extends GTTileMultiBaseMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput0 = 2;
	public static final int slotOutput1 = 3;
	public static final int slotOutput2 = 4;
	public static final int slotOutput3 = 5;
	public static final int slotOutput4 = 6;
	public static final int slotOutput5 = 7;

	protected static final int[] slotInputs = { 0, 1 };
	protected static final int[] slotOutputs = { 2, 3, 4, 5, 6, 7 };

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.shredder");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/shredder.png");

	public GTTileShredder() {
		super(8, 2, 96, 128);
		maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutputs);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	@Override
	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.back(1).up(1).asBlockPos());
	}

	@Override
	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.down(1).forward(1).asBlockPos());
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
		return new GTContainerShredder(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTShredderGui.class;
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
	public boolean isRecipeSlot(int slot) {
		for (int i : this.getInputSlots()) {
			if (slot <= i) {
				return true;
			}
		}
		return false;
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

	// @formatter:off
	public static void init() {
		if (!Loader.isModLoaded("ic2c_extras")) {
			/*
			 * This is for Trinsdar so he can use his own crushed ores in this without
			 * removing all my recipes one by one. Cause I am such a nice guy : )
			 */
			addRecipe("oreBauxite", 1, 
					GTMaterialGen.getDust(GTMaterial.Bauxite, 4),
					GTMaterialGen.getDust(GTMaterial.Alumina, 1));

			addRecipe("oreChromite", 1, 
					GTMaterialGen.getDust(GTMaterial.Chromite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Chrome, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Iron, 1));

			addRecipe("oreGalena", 1, 
					GTMaterialGen.getDust(GTMaterial.Galena, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Silver, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Sulfur, 2));

			addRecipe("oreGarnierite", 1, 
					GTMaterialGen.getDust(GTMaterial.Garnierite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Platinum, 1),
					GTMaterialGen.getSmallDust(GTMaterial.Nickel, 1));

			addRecipe("oreLimonite", 1, 
					GTMaterialGen.getDust(GTMaterial.Limonite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Iron, 2));

			addRecipe("oreMalachite", 1, 
					GTMaterialGen.getDust(GTMaterial.Malachite, 2),
					GTMaterialGen.getDust(GTMaterial.Calcite, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Copper, 2));

			addRecipe("orePyrite", 1, 
					GTMaterialGen.getDust(GTMaterial.Pyrite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Iron, 2), 
					GTMaterialGen.getSmallDust(GTMaterial.Sulfur, 2));

			addRecipe("oreMagnetite", 1, 
					GTMaterialGen.getDust(GTMaterial.Magnetite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Iron, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Gold, 1));

			addRecipe("oreSheldonite", 1, 
					GTMaterialGen.getDust(GTMaterial.Sheldonite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Nickel, 1),
					GTMaterialGen.getSmallDust(GTMaterial.Platinum, 1),
					GTMaterialGen.getSmallDust(GTMaterial.Sulfur, 2));

			addRecipe("oreSphalerite", 1, 
					GTMaterialGen.getDust(GTMaterial.Sphalerite, 2),
					GTMaterialGen.getDust(GTMaterial.Zinc, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.GarnetYellow, 1),
					GTMaterialGen.getSmallDust(GTMaterial.Sulfur, 2));

			addRecipe("oreTantalite", 1, 
					GTMaterialGen.getDust(GTMaterial.Tantalite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Niobium, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Tantalum, 1));

			addRecipe("oreTetrahedrite", 1, 
					GTMaterialGen.getDust(GTMaterial.Tetrahedrite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Copper, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Antimony, 1),
					GTMaterialGen.getSmallDust(GTMaterial.Sulfur, 2));

			addRecipe("oreTungstate", 1, 
					GTMaterialGen.getDust(GTMaterial.Tungstate, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Iron, 3),
					GTMaterialGen.getSmallDust(GTMaterial.Manganese, 3));

			addRecipe("orePyrolusite", 1, 
					GTMaterialGen.getDust(GTMaterial.Pyrolusite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Manganese, 2));

			addRecipe("oreMolybdenite", 1, 
					GTMaterialGen.getDust(GTMaterial.Molybdenite, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Molybdenum, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Sulfur, 2));

			addRecipe("oreIridium", 1, 
					GTMaterialGen.getIc2(Ic2Items.iridiumOre, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Platinum, 2));

			addRecipe("oreCassiterite", 1, 
					GTMaterialGen.getDust(GTMaterial.Cassiterite, 2),
					GTMaterialGen.getIc2(Ic2Items.tinDust, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Tantalum, 1));

			addRecipe("oreBismuthtine", 1, 
					GTMaterialGen.getDust(GTMaterial.Bismuthtine, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Bismuth, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Antimony, 1));

			addRecipe("oreCopper", 1, 
					GTMaterialGen.getIc2(Ic2Items.copperDust, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Gold, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Nickel, 1));

			addRecipe("oreUranium", 1, 
					GTMaterialGen.getDust(GTMaterial.Uranium, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Uranium, 2));

			addRecipe("oreIron", 1, 
					GTMaterialGen.getIc2(Ic2Items.ironDust, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Iron, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Nickel, 1));

			addRecipe("oreGold", 1, 
					GTMaterialGen.getIc2(Ic2Items.goldDust, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Copper, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Nickel, 1));

			addRecipe("oreTin", 1, 
					GTMaterialGen.getIc2(Ic2Items.tinDust, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Iron, 1), 
					GTMaterialGen.getSmallDust(GTMaterial.Zinc, 1));

			addRecipe("oreSilver", 1, 
					GTMaterialGen.getIc2(Ic2Items.silverDust, 2),
					GTMaterialGen.getSmallDust(GTMaterial.Lead, 2));

		}

		/*
		 * These recipes will be added regardless of Ic2 extras
		 */

		addRecipe("netherrack", 16, 
				GTMaterialGen.getIc2(Ic2Items.netherrackDust, 16),
				GTMaterialGen.get(Items.GOLD_NUGGET, 1), 
				GTMaterialGen.getDust(GTMaterial.Phosphorus, 1));

		addRecipe("obsidian", 1, 
				GTMaterialGen.getIc2(Ic2Items.obsidianDust, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Iron, 1), 
				GTMaterialGen.getSmallDust(GTMaterial.Magnesium, 1));

		addRecipe("oreQuartz", 1, 
				GTMaterialGen.get(Items.QUARTZ, 3), 
				GTMaterialGen.getIc2(Ic2Items.netherrackDust, 1));

		addRecipe("oreSaltpeter", 1, 
				GTMaterialGen.getDust(GTMaterial.Saltpeter, 4));

		addRecipe("oreSalt", 1, 
				GTMaterialGen.getDust(GTMaterial.Salt, 4));

		addRecipe("oreCalcite", 1, 
				GTMaterialGen.getDust(GTMaterial.Calcite, 3),
				GTMaterialGen.getDust(GTMaterial.Phosphorus, 1));

		addRecipe("oreCryolite", 1, 
				GTMaterialGen.getDust(GTMaterial.Cryolite, 4));

		addRecipe("oreSulfur", 1, 
				GTMaterialGen.getDust(GTMaterial.Sulfur, 4));

		addRecipe("oreGraphite", 1, 
				GTMaterialGen.getDust(GTMaterial.Graphite, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Carbon, 1), 
				GTMaterialGen.getSmallDust(GTMaterial.Diamond, 1));

		addRecipe("oreRuby", 1, 
				GTMaterialGen.getGem(GTMaterial.Ruby, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Ruby, 6), 
				GTMaterialGen.getSmallDust(GTMaterial.GarnetRed, 2));

		addRecipe("oreSapphire", 1, 
				GTMaterialGen.getGem(GTMaterial.Sapphire, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Sapphire, 6),
				GTMaterialGen.getSmallDust(GTMaterial.SapphireGreen, 2));

		addRecipe("oreCoal", 1, 
				GTMaterialGen.get(Items.COAL, 2), 
				GTMaterialGen.getIc2(Ic2Items.coalDust, 1),
				GTMaterialGen.getSmallDust(GTMaterial.Germanium, 1),
				GTMaterialGen.getSmallDust(GTMaterial.Thorium, 1));

		addRecipe("oreLapis", 1, 
				new ItemStack(Items.DYE, 12, 4), 
				GTMaterialGen.getDust(GTMaterial.Lazurite, 3));

		addRecipe("oreRedstone", 1, 
				GTMaterialGen.get(Items.REDSTONE, 16),
				GTMaterialGen.getSmallDust(GTMaterial.Glowstone, 2));

		addRecipe("oreDiamond", 1, 
				GTMaterialGen.get(Items.DIAMOND, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Diamond, 6), 
				GTMaterialGen.getIc2(Ic2Items.hydratedCoalDust, 1));

		addRecipe("oreEmerald", 1, 
				GTMaterialGen.get(Items.EMERALD, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Emerald, 6), 
				GTMaterialGen.getSmallDust(GTMaterial.Olivine, 2));

		addRecipe("oreCinnabar", 1, GTMaterialGen.getDust(GTMaterial.Cinnabar, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Redstone, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Glowstone, 1));

		addRecipe("oreOlivine", 1, 
				GTMaterialGen.getGem(GTMaterial.Olivine, 2),
				GTMaterialGen.getSmallDust(GTMaterial.Olivine, 6), 
				GTMaterialGen.getSmallDust(GTMaterial.Emerald, 2));

		addRecipe("oreSodalite", 1, 
				GTMaterialGen.getDust(GTMaterial.Lazurite, 12),
				GTMaterialGen.getDust(GTMaterial.Alumina, 3));
	}
	// @formatter:on

	public static void collectMaceratorRecipe() {
		// Grabs everything from the ic2 classic macerator list
		// Separate method so it can be done last in post init
		for (RecipeEntry entry : ClassicRecipes.macerator.getRecipeMap()) {
			List<IRecipeInput> inlist = new ArrayList<>();
			inlist.add(entry.getInput());
			inlist.add(basicswitch);
			List<ItemStack> output = entry.getOutput().getAllOutputs();
			IRecipeModifier[] modifiers = euCost(4000);
			NBTTagCompound mods = new NBTTagCompound();
			for (IRecipeModifier modifier : modifiers) {
				modifier.apply(mods);
			}
			addRecipe(inlist, new MachineOutput(mods, output));
		}
	}

	public static void addRecipe(String input, int amount, ItemStack... outputs) {
		addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount) }, euCost(32000), outputs);
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 96);
	}

	public static IRecipeModifier[] euCost(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 96) - 100) };
	}

	@Override
	public boolean checkStructure() {
		int3 dir = new int3(getPos(), getFacing());
		// layer 0
		if (!(isMachineCasing(dir.left(1)) && isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1))
				&& isMachineCasing(dir.right(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.right(1))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.back(2)) &&
				// bottom layer
				isMachineCasing(dir.down(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1))
				&& isMachineCasing(dir.left(1)) && isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1))
				&& isMachineCasing(dir.left(1)) && isMachineCasing(dir.forward(1))
				&& isMachineCasing(dir.forward(1)))) {
			return false;
		}
		return true;
	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen.getBlock(GTMaterial.Steel, GTMaterialFlag.CASING)
				.getDefaultState();
	}
}
