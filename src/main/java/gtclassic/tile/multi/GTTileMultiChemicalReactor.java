package gtclassic.tile.multi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.container.GTContainerChemicalReactor;
import gtclassic.gui.GTGuiMachine.GTChemicalReactorGui;
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
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GTTileMultiChemicalReactor extends GTTileMultiBaseMachine {

	protected static final int[] slotInputs = { 0, 1, 2, 3, 4, 5 };
	protected static final int[] slotOutputs = { 6, 7, 8, 9, 10, 11 };

	IFilter filter = new MachineFilter(this);

	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.chemicalreactor");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/chemicalreactor.png");

	public GTTileMultiChemicalReactor() {
		super(12, 2, 256, 512);
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
		return world.getTileEntity(dir.left(1).back(1).up(1).asBlockPos());
	}

	@Override
	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.right(1).back(1).up(1).asBlockPos());
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
		return new GTContainerChemicalReactor(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTChemicalReactorGui.class;
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

	@Override
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.compressorOp;
	}

	public static void init() {

		addRecipe(new IRecipeInput[] { input("dustBauxite", 1),
				input(GTMaterialGen.getFluid(GTMaterial.SodiumHydroxide, 3)),
				input(GTMaterialGen.getWater(1)) }, totalEu(16000), GTMaterialGen.getFluid(GTMaterial.BauxiteTailings, 1), GTMaterialGen.get(GTItems.testTube, 3));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getWater(1)),
				input(GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1)),
				input(GTMaterialGen.getFluid(GTMaterial.Oxygen, 1)) }, totalEu(16000), GTMaterialGen.getFluid(GTMaterial.SulfuricAcid, 3));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getFluid(GTMaterial.Hydrogen, 2)),
				input(GTMaterialGen.getFluid(GTMaterial.SulfurDioxide, 1)),
				input(GTMaterialGen.getFluid(GTMaterial.Oxygen, 2)) }, totalEu(16000), GTMaterialGen.getFluid(GTMaterial.SulfuricAcid, 3), GTMaterialGen.get(GTItems.testTube, 2));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getWater(1)),
				input(GTMaterialGen.getFluid(GTMaterial.Chlorine, 1)) }, totalEu(8000), GTMaterialGen.getFluid(GTMaterial.Hydrochloricacid, 2));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getWater(1)),
				input(GTMaterialGen.getFluid(GTMaterial.Nitrogen, 1)),
				input(GTMaterialGen.getFluid(GTMaterial.Oxygen, 1)) }, totalEu(8000), GTMaterialGen.getFluid(GTMaterial.NitricAcid, 3));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getFluid(GTMaterial.NitricAcid, 2)),
				input(GTMaterialGen.getFluid(GTMaterial.Hydrochloricacid, 1)) }, totalEu(8000), GTMaterialGen.getFluid(GTMaterial.Aquaregia, 3));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getFluid(GTMaterial.Sodium, 1)),
				input(GTMaterialGen.getFluid(GTMaterial.Oxygen, 1)),
				input(GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1)) }, totalEu(12000), GTMaterialGen.getFluid(GTMaterial.SodiumHydroxide, 3));

		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getFluid(GTMaterial.Sodium, 2)),
				input(GTMaterialGen.getFluid(GTMaterial.CarbonDioxide, 1)),
				input(GTMaterialGen.getFluid(GTMaterial.Oxygen, 2)) }, totalEu(12000), GTMaterialGen.getFluid(GTMaterial.SodiumCarbonate, 4), GTMaterialGen.get(GTItems.testTube, 1));

		addRecipe(new IRecipeInput[] { input("dustBauxiteTailings", 12),
				input(GTMaterialGen.getFluid(GTMaterial.SulfuricAcid, 8)) }, totalEu(150000), GTMaterialGen.getDust(GTMaterial.Titanium, 1), GTMaterialGen.getDust(GTMaterial.Alumina, 8), GTMaterialGen.getDust(GTMaterial.Silicon, 2), GTMaterialGen.getFluid(GTMaterial.Hydrogen, 5), GTMaterialGen.getFluid(GTMaterial.Oxygen, 3));

		addRecipe(new IRecipeInput[] { input("dustTungsticAcid", 4),
				input(GTMaterialGen.getFluid(GTMaterial.Hydrogen, 6)) }, totalEu(96000), GTMaterialGen.getDust(GTMaterial.Tungsten, 1), GTMaterialGen.getWater(4));

		addRecipe(new IRecipeInput[] { input("oreSheldonite", 1),
				input(GTMaterialGen.getFluid(GTMaterial.Aquaregia, 9)) }, totalEu(24000), GTMaterialGen.getSmallDust(GTMaterial.PlatinumGroupSludge, 1), GTMaterialGen.getFluid(GTMaterial.Chloroplatinicacid, 4), GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1), GTMaterialGen.getWater(3));

		addRecipe(new IRecipeInput[] { input("oreIridium", 1),
				input(GTMaterialGen.getFluid(GTMaterial.Aquaregia, 9)) }, totalEu(24000), GTMaterialGen.getSmallDust(GTMaterial.PlatinumGroupSludge, 1), GTMaterialGen.getFluid(GTMaterial.Chloroplatinicacid, 4), GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1), GTMaterialGen.getWater(4));
	}

	public static IRecipeModifier[] totalEu(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 256) - 100) };
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 256);
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
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen.getBlock(GTMaterial.StainlessSteel, GTMaterialFlag.CASING).getDefaultState();
	}

}
