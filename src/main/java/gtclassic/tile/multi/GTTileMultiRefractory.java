package gtclassic.tile.multi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import gtclassic.GTMod;
import gtclassic.container.GTContainerRefractory;
import gtclassic.gui.GTGuiMachine.GTRefractoryGui;
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

public class GTTileMultiRefractory extends GTTileMultiBaseMachine {

	protected static final int[] slotInputs = { 0, 1, 2, 3 };
	protected static final int[] slotOutputs = { 4, 5, 6, 7 };
	IFilter filter = new MachineFilter(this);
	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.refractory");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/refractory.png");

	public GTTileMultiRefractory() {
		super(8, 2, 256, 512);
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
		return world.getTileEntity(dir.down(2).forward(1).asBlockPos());
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
		return new GTContainerRefractory(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTRefractoryGui.class;
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

	public static void init() {
		addRecipe(new IRecipeInput[] {
				metal("Copper", 1), }, totalEu(64000), GTMaterialGen.getHotIngot(GTMaterial.AnnealedCopper, 1));
		addRecipe(new IRecipeInput[] { metal("Tungsten", 1),
				metal("Steel", 1) }, totalEu(64000), GTMaterialGen.getHotIngot(GTMaterial.TungstenSteel, 2));
	}

	public static IRecipeModifier[] totalEu(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 256) - 100) };
	}

	public static void addRecipe(IRecipeInput[] inputs, int totalEu, ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		IRecipeModifier[] modifiers = totalEu(totalEu);
		for (IRecipeInput input : inputs) {
			inlist.add(input);
		}
		if (inlist.size() == 1) {
			inlist.add(basicswitch);
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

	public static void addRecipe(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		for (IRecipeInput input : inputs) {
			inlist.add(input);
		}
		if (inlist.size() == 1) {
			inlist.add(basicswitch);
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 256);
	}

	@Override
	public boolean checkStructure() {
		return GTConfig.harderRefractory ? checkStructureNew() : checkStructureOld();
	}

	public boolean checkStructureNew() {
		int3 dir = new int3(getPos(), getFacing());
		if (!(isMachineCasing(dir.left(1)) && isMachineCasing(dir.left(1)) && isMachineCasing(dir.right(3))
				&& isMachineCasing(dir.right(1)) && isMachineCasing(dir.down(1)))) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.left(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.down(1))) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.right(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.back(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.up(1))) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isMachineCasing(dir.forward(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.up(1))) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isMachineCasing(dir.back(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.left(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.down(1))) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isMachineCasing(dir.right(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.down(1))) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (!(isMachineCasing(dir.left(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (!(isMachineCasing(dir.forward(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.up(1))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isMachineCasing(dir.back(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.up(1))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isMachineCasing(dir.forward(1)))) {
				return false;
			}
		}
		// inner 3x3 layers
		if (!isStone(dir.right(1).down(2))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isStone(dir.back(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isStone(dir.right(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isStone(dir.forward(1)))) {
				return false;
			}
		}
		if (!isStone(dir.left(1))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isStone(dir.back(1)))) {
				return false;
			}
		}
		if (!isCoil(dir.up(1).forward(1))) {
			return false;
		}
		if (!isCoil(dir.forward(1))) {
			return false;
		}
		if (!isCoil(dir.left(1))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isCoil(dir.back(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isCoil(dir.right(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isCoil(dir.forward(1)))) {
				return false;
			}
		}
		if (!isCoil(dir.up(1).left(1).back(1))) {
			return false;
		}
		if (!isCoil(dir.forward(1))) {
			return false;
		}
		if (!isCoil(dir.left(1))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isCoil(dir.back(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isCoil(dir.right(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isCoil(dir.forward(1)))) {
				return false;
			}
		}
		return true;
	}

	public boolean checkStructureOld() {
		int3 dir = new int3(getPos(), getFacing());
		// layer 2
		if (!(isMachineCasing(dir.left(1)) && isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1))
				&& isMachineCasing(dir.right(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.right(1))
				&& isMachineCasing(dir.forward(1)) && isMachineCasing(dir.back(2)) &&
				// bottom layer
				isMachineCasing(dir.down(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1))
				&& isMachineCasing(dir.left(1)) && isMachineCasing(dir.back(1)) && isMachineCasing(dir.back(1))
				&& isMachineCasing(dir.left(1)) && isMachineCasing(dir.forward(1)) && isMachineCasing(dir.forward(1))
				// layer 0 stone layer
				&& isStone(dir.down(1)) && isStone(dir.back(1)) && isStone(dir.back(1)) && isStone(dir.right(1))
				&& isStone(dir.forward(1)) && isStone(dir.forward(1)) && isStone(dir.right(1)) && isStone(dir.back(1))
				&& isStone(dir.back(1)))) {
			return false;
		}
		return true;
	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen.getBlock(GTMaterial.Steel, GTMaterialFlag.CASING).getDefaultState();
	}

	public boolean isStone(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTBlocks.stoneMagnesia.getDefaultState();
	}

	public boolean isCoil(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen.getBlock(GTMaterial.Graphite, GTMaterialFlag.COIL).getDefaultState();
	}
}
