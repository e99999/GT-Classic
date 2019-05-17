package gtclassic.tile.multi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.container.GTContainerBlastFurnace;
import gtclassic.gui.GTGuiMachine.GTBlastFurnaceGui;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
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
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileMultiBlastFurnace extends GTTileMultiBaseMachine {

	protected static final int[] slotInputs = { 0, 1, 2, 3 };
	protected static final int[] slotOutputs = { 4, 5, 6, 7 };

	public static final IBlockState casingMachine = GTMaterialGen
			.getBlock(GTMaterial.RefinedIron, GTMaterialFlag.CASING).getDefaultState();

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.blastfurnace");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/blastfurnace.png");

	public GTTileMultiBlastFurnace() {
		super(8, 2, 20, 32);
		maxEnergy = 1000;
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
		return world.getTileEntity(dir.left(1).forward(1).asBlockPos());
	}

	@Override
	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.right(1).forward(1).asBlockPos());
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
		IFilter[] filter = { new MachineFilter(this) };
		return null;
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

	@Override
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.generatorLoop;
	}

	// @formatter:off
	public static void init() {
		
		int LOW_TIME = 4000;
		int MED_TIME = 8000;
		int LONG_TIME = 16000;

		addRecipe(new IRecipeInput[] { 
				input("ingotIron", 1) }, 
				totalEu(LOW_TIME),
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("dustIron", 1) }, 
				totalEu(LOW_TIME),
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 1));

		addRecipe(new IRecipeInput[] { 
				input("oreIron", 1), 
				input("dustCalcite", 1) }, 
				totalEu(LOW_TIME),
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 3), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustPyrite", 1), 
				input("dustCalcite", 1) }, totalEu(LOW_TIME),
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("orePyrite", 1), 
				input("dustCalcite", 1) }, totalEu(LOW_TIME),
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustMagnetite", 1), 
				input("dustCalcite", 1) },
				totalEu(LOW_TIME), 
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("oreMagnetite", 1), 
				input("dustCalcite", 1) },
				totalEu(LOW_TIME), 
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustLimonite", 1), 
				input("dustCalcite", 1) },
				totalEu(LOW_TIME), 
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("oreLimonite", 1), 
				input("dustCalcite", 1) },
				totalEu(LOW_TIME), 
				GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 2), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 2));

		addRecipe(new IRecipeInput[] { 
				input("ingotRefinedIron", 1), 
				input("dustCoal", 2) },
				totalEu(MED_TIME), 
				GTMaterialGen.getIngot(GTMaterial.Steel, 1), 
				GTMaterialGen.getDust(GTMaterial.DarkAshes, 2));
		
		addRecipe(new IRecipeInput[] { 
				input("ingotRefinedIron", 1), 
				input("dustCarbon", 1) },
				totalEu(MED_TIME), 
				GTMaterialGen.getIngot(GTMaterial.Steel, 1));

		addRecipe(new IRecipeInput[] { 
				input("dustChromite", 1) }, 
				totalEu(LONG_TIME),
				GTMaterialGen.getNugget(GTMaterial.Chrome, 6), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("dustSmallChromite", 4) }, 
				totalEu(LONG_TIME),
				GTMaterialGen.getNugget(GTMaterial.Chrome, 6), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("oreChromite", 1) }, 
				totalEu(LONG_TIME), 
				GTMaterialGen.getNugget(GTMaterial.Chrome, 6),
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 2));

		addRecipe(new IRecipeInput[] { 
				input("dustMolybdenite", 3) }, 
				totalEu(LONG_TIME),
				GTMaterialGen.getHotIngot(GTMaterial.Molybdenum, 1), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 3));
		
		addRecipe(new IRecipeInput[] { 
				input("dustSmallMolybdenite", 12) }, 
				totalEu(LONG_TIME),
				GTMaterialGen.getHotIngot(GTMaterial.Molybdenum, 1), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 3));
		
		addRecipe(new IRecipeInput[] { 
				input("oreMolybdenite", 3) }, 
				totalEu(LONG_TIME),
				GTMaterialGen.getHotIngot(GTMaterial.Molybdenum, 1), 
				GTMaterialGen.getSmallDust(GTMaterial.Slag, 6));

		addRecipe(new IRecipeInput[] { 
				input(GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1)), }, 
				totalEu(LONG_TIME),
				GTMaterialGen.getHotIngot(GTMaterial.Iridium, 1));

		addRecipe(new IRecipeInput[] { 
				input("oreIridium", 1), }, 
				totalEu(LONG_TIME),
				GTMaterialGen.getHotIngot(GTMaterial.Iridium, 1));
		
		//alloy recipes
		
		IRecipeInput anyIron = new RecipeInputCombined(4,new IRecipeInput[] { new RecipeInputOreDict("ingotIron", 4), new RecipeInputOreDict("ingotRefinedIron", 4),
						new RecipeInputOreDict("ingotSteel", 4) });
		
		addRecipe(new IRecipeInput[] { 
				anyIron, 
				input("ingotInvar", 3), 
				input("ingotManganese", 1), 
				input("ingotChrome", 1), 
				},
				totalEu(MED_TIME), 
				GTMaterialGen.getIngot(GTMaterial.StainlessSteel, 9));
		
		addRecipe(new IRecipeInput[] { 
				anyIron, 
				input("ingotInvar", 3), 
				input("ingotNiobium", 1), 
				input("ingotChrome", 1), 
				},
				totalEu(MED_TIME), 
				GTMaterialGen.getIngot(GTMaterial.StainlessSteel, 9));
		
		addRecipe(new IRecipeInput[] { 
				input("ingotCobalt", 5), 
				input("ingotChrome", 2), 
				input("ingotNickel", 1), 
				input("ingotMolybdenum", 1), 
				},
				totalEu(MED_TIME), 
				GTMaterialGen.getIngot(GTMaterial.Ultimet, 9));
		
		addRecipe(new IRecipeInput[] { 
				input("ingotNiobium", 1), 
				input("ingotTitanium", 1), 
				},
				totalEu(MED_TIME), 
				GTMaterialGen.getIngot(GTMaterial.NiobiumTitanium, 2));
		
		

	}
	// @formatter:on

	public static IRecipeModifier[] totalEu(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 20) - 100) };
	}

	/**
	 * Adds a recipe not only to the blast furnace but to the refractory as well
	 */
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 20);
	}

	@Override
	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3)) {
			return false;
		}
		// im not doing this layer by layer, instead ill loop up and over the structure
		// vertically then finish the base layers last
		int3 dir = new int3(getPos(), getFacing());

		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isAir(dir.back(1))) {// peak
			return false;
		}
		for (int i = 0; i < 5; i++) {
			if (!(isAir(dir.down(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.down(1))) {
			return false;
		}
		if (!isMachineCasing(dir.back(1))) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.up(1)))) {
				return false;
			}
		}
		if (!isAir(dir.forward(1))) {// peak again
			return false;
		}
		if (!isMachineCasing(dir.left(1))) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (!(isMachineCasing(dir.down(1)))) {
				return false;
			}
		}
		if (!(isMachineCasing(dir.right(2)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.left(2))
				&& isMachineCasing(dir.back(2)) && isMachineCasing(dir.right(2)) && isMachineCasing(dir.up(1))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.left(2))
				&& isMachineCasing(dir.back(2)) && isMachineCasing(dir.up(1)) && isMachineCasing(dir.right(2))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.left(2))
				&& isMachineCasing(dir.back(1)) && isMachineCasing(dir.right(2)))) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.up(1)))) {
				return false;
			}
		}

		return true;

	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == casingMachine;
	}

	public boolean isAir(int3 pos) {
		return world.isAirBlock(pos.asBlockPos());
	}

}
