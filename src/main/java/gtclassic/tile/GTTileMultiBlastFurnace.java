package gtclassic.tile;

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
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileMultiBlastFurnace extends GTTileBaseMultiBlockMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotInput2 = 2;
	public static final int slotOutput0 = 3;
	public static final int slotOutput1 = 4;
	public static final int slotOutput2 = 5;

	public static final IBlockState casingMachine = GTMaterialGen
			.getBlock(GTMaterial.RefinedIron, GTMaterialFlag.CASING).getDefaultState();

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("blastfurnace");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/blastfurnace.png");

	public GTTileMultiBlastFurnace() {
		super(6, 2, 20, 32);
		maxEnergy = 1000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1, slotInput2);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput0, slotOutput1, slotOutput2);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput0, slotInput1, slotInput2);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1, slotInput2);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput0, slotOutput1, slotOutput2);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1, slotInput2);
		handler.registerSlotType(SlotType.Output, slotOutput0, slotOutput1, slotOutput2);
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
		return new int[] { slotInput0, slotInput1, slotInput2 };
	}

	/**
	 * This is no longer needed for the edited multi recipe input checking.
	 * The code just picks the first int[] from the int[][], so you only need one element.
	 * Example below commented out. You can do this for any machine using MultiRecipe
	 * **/
	@Override
	public int[][] getRecipeMutations() {
		return new int[][] { { slotInput0, slotInput1, slotInput2 }, { slotInput0, slotInput2, slotInput1 },
				{ slotInput2, slotInput1, slotInput0 }, { slotInput1, slotInput2, slotInput0 },
				{ slotInput1, slotInput0, slotInput2 }, { slotInput2, slotInput0, slotInput1 } };
	}
//	@Override
//	public int[][] getRecipeMutations() {
//		return new int[][] { {slotInput0, slotInput1, slotInput2} };
//	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		IFilter[] filter = { new MachineFilter(this) };
		return null;
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return true;
	}

	@Override
	public int[] getOutputSlots() {
		return new int[] { slotOutput0, slotOutput1, slotOutput2 };
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
