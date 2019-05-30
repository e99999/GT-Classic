package gtclassic.tile.multi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.container.GTContainerLeadChamber;
import gtclassic.gui.GTGuiMachine.GTLeadChamberGui;
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
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileMultiLeadChamber extends GTTileMultiBasePassive {

	protected static final int[] slotInputs = { 0, 1, 2, 3, 4, 5 };
	protected static final int[] slotOutputs = { 6, 7, 8, 9, 10, 11 };

	IFilter filter = new MachineFilter(this);

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.leadchamber");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/bath.png");

	public GTTileMultiLeadChamber() {
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
		return world.getTileEntity(dir.up(2).asBlockPos());
	}

	@Override
	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.back(6).asBlockPos());
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		return null;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerLeadChamber(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTLeadChamberGui.class;
	}

	@Override
	public int[] getInputSlots() {
		return slotInputs;
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
	public ResourceLocation getStartSoundFile() {
		return null;
	}

	// @formatter:off
	public static void init() {
		
		addRecipe(new IRecipeInput[] { 
				input("dustGold", 1), 
				input("dustCoal", 1)}, 
				totalTicks(2000), 
				GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1));
		
		
		
		
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
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen.getBlock(GTMaterial.Lead, GTMaterialFlag.CASING)
				.getDefaultState();
	}

}
