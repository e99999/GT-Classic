package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.container.GTContainerBath;
import gtclassic.gui.GTGuiMachine.GTBathGui;
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
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileBath extends GTTileBaseMachinePassive {

	protected static final int[] slotInputs = { 0, 1, 2, 3, 4, 5 };
	protected static final int[] slotOutputs = { 6, 7, 8, 9, 10, 11 };

	IFilter filter = new MachineFilter(this);

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.bath");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/bath.png");

	public GTTileBath() {
		super(12);
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
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.left(1).asBlockPos());
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		return null;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBath(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTBathGui.class;
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
		return null;
	}

	// @formatter:off
	public static void init() {
		
		addRecipe(new IRecipeInput[] { 
				input("dustSalt", 1), 
				input(GTMaterialGen.getWater(1))}, 
				totalTicks(800), 
				GTMaterialGen.getFluid(GTMaterial.Brine, 1));
		
		addRecipe( new IRecipeInput[] { 
				input("dustTungstate", 7),
				input(GTMaterialGen.getFluid(GTMaterial.Hydrochloricacid, 2)) },
				totalTicks(1000), 
				GTMaterialGen.getDust(GTMaterial.LithiumChloride, 1),
				GTMaterialGen.getDust(GTMaterial.TungsticAcid, 7),
				GTMaterialGen.get(GTItems.testTube, 2));
		
		addRecipe(new IRecipeInput[] { 
				input("dustDarkAshes", 12), 
				input(GTMaterialGen.getFluid(GTMaterial.SulfuricAcid, 4))}, 
				totalTicks(1200), 
				GTMaterialGen.getFluid(GTMaterial.SodiumCarbonate, 4));
		
		addRecipe(new IRecipeInput[] { 
				input("dustCalcite", 4), 
				input(GTMaterialGen.getFluid(GTMaterial.Brine, 2))}, 
				totalTicks(400), 
				GTMaterialGen.getFluid(GTMaterial.SodiumCarbonate, 1),
				GTMaterialGen.get(GTItems.testTube, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("dustBrine", 4), 
				input(GTMaterialGen.getFluid(GTMaterial.SodiumCarbonate, 1))}, 
				totalTicks(800), 
				GTMaterialGen.getFluid(GTMaterial.LithiumCarbonate, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("dustPyrolusite", 2), 
				input(GTMaterialGen.getFluid(GTMaterial.Hydrochloricacid, 1))}, 
				totalTicks(800), 
				GTMaterialGen.getFluid(GTMaterial.Chlorine, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("dustSalt", 4), 
				input(GTMaterialGen.getFluid(GTMaterial.VitriolGreen, 1))}, 
				totalTicks(1200), 
				GTMaterialGen.getFluid(GTMaterial.Hydrochloricacid, 1));
		
		addRecipe(new IRecipeInput[] { 
				input("dustSaltpeter", 7), 
				input(GTMaterialGen.getFluid(GTMaterial.SulfuricAcid, 4))}, 
				totalTicks(1200), 
				GTMaterialGen.getFluid(GTMaterial.NitricAcid, 4));
		
		
	}
	// @formatter:on

	public static IRecipeModifier[] totalTicks(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create(total - 100) };
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 0);
	}

}
