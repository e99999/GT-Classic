package gtclassic.tile.multi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.container.GTContainerFusionComputer;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
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
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileMultiFusion extends GTTileMultiBaseMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput = 2;

	public static final IBlockState coilState = GTBlocks.casingFusion.getDefaultState();

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.fusion");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/fusioncomputer.png");

	public GTTileMultiFusion() {
		super(3, 0, 2048, 8196);
		maxEnergy = 100000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput0, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutput);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1);
		handler.registerSlotType(SlotType.Output, slotOutput);
	}

	@Override
	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.left(1).asBlockPos());
	}

	@Override
	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.right(1).asBlockPos());
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerFusionComputer(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTFusionComputerGui.class;
	}

	@Override
	public int[] getInputSlots() {
		int[] input = { slotInput0, slotInput1 };
		return input;
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		return new IFilter[] { new MachineFilter(this) };
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return true;
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
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.compressorOp;
	}

	public static void init() {
		/*
		 * Just a few test fusion recipes
		 */
		addRecipe(new IRecipeInput[] { input("dustTungsten", 1),
				input("dustLithium", 1) }, totalEu(16775168), GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1));

		addRecipe(new IRecipeInput[] { input("dustTungsten", 1),
				input(GTMaterialGen.getFluid(GTMaterial.Beryllium, 1)) }, totalEu(16775168), GTMaterialGen.getDust(GTMaterial.Platinum, 1));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getIc2(Ic2Items.emptyCell, 1)),
				input(GTMaterialGen.getIc2(Ic2Items.uuMatter, 1)) }, totalEu(10000000), GTMaterialGen.getIc2(Ic2Items.plasmaCell, 1));
	}

	public static IRecipeModifier[] totalEu(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 2048) - 100) };
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 2048);
	}

	@Override
	public boolean checkStructure() {
		int3 dir = new int3(getPos(), getFacing());

		if (!(isMachineCasing(dir.left(1)) && isMachineCasing(dir.left(1)) && isMachineCasing(dir.right(3))
				&& isMachineCasing(dir.right(1)) && isMachineCasing(dir.up(1)))) {
			return false;
		}

		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.left(1)))) {
				return false;
			}
		}

		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.back(1)))) {
				return false;
			}
		}

		if (!isMachineCasing(dir.down(1))) {
			return false;
		}

		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.forward(1)))) {
				return false;
			}
		}

		if (!isMachineCasing(dir.right(4))) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.back(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.up(1))) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (!(isMachineCasing(dir.forward(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.back(4).left(1))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isMachineCasing(dir.left(1)))) {
				return false;
			}
		}
		if (!isMachineCasing(dir.down(1))) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (!(isMachineCasing(dir.right(1)))) {
				return false;
			}
		}

		// inner iridium casings
		for (int i = 0; i < 3; i++) {
			if (!(isInnerCasing(dir.forward(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isInnerCasing(dir.left(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isInnerCasing(dir.back(1)))) {
				return false;
			}
		}

		if (!isInnerCasing(dir.right(1))) {
			return false;
		}
		if (!isInnerCasing(dir.forward(1))) {
			return false;
		}
		if (!isInnerCasing(dir.up(1).back(1).right(1))) {
			return false;
		}

		for (int i = 0; i < 2; i++) {
			if (!(isInnerCasing(dir.forward(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isInnerCasing(dir.left(1)))) {
				return false;
			}
		}
		for (int i = 0; i < 2; i++) {
			if (!(isInnerCasing(dir.back(1)))) {
				return false;
			}
		}

		if (!isInnerCasing(dir.right(1))) {
			return false;
		}
		if (!isInnerCasing(dir.forward(1))) {
			return false;
		}

		return true;
	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen.getBlock(GTMaterial.Titanium, GTMaterialFlag.CASING).getDefaultState();

	}

	public boolean isInnerCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen.getBlock(GTMaterial.Iridium, GTMaterialFlag.CASING).getDefaultState();

	}
}
