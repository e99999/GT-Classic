package gtclassic.tile.multi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.container.GTContainerFusionComputer;
import gtclassic.gui.GTGuiMachine.GTFusionComputerGui;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTFusionRecipeObject;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.energy.tile.IEnergyEmitter;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GTTileMultiFusion extends GTTileMultiBaseMachine {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput = 2;
	public static final GTMultiInputRecipeList RECIPE_LIST = new GTMultiInputRecipeList("gt.fusion");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/fusioncomputer.png");
	public String status;
	IBlockState coilState = GTBlocks.casingFusion.getDefaultState();

	public GTTileMultiFusion() {
		super(3, 0, 8196, 32784);
		maxEnergy = 100000000;
		this.status = "No";
		this.addGuiFields(new String[] { "status" });
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
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.status = nbt.getString("status");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("status", this.status);
		return nbt;
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

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return false;
	}

	public static void init() {
		/** This iterates the element objects to create all Fusion recipes **/
		Set<Integer> usedInputs = new HashSet<>();
		for (GTFusionRecipeObject sum : GTFusionRecipeObject.fusionObjects) {
			for (GTFusionRecipeObject input1 : GTFusionRecipeObject.fusionObjects) {
				for (GTFusionRecipeObject input2 : GTFusionRecipeObject.fusionObjects) {
					int hash = input1.hashCode() + input2.hashCode();
					if ((input1.getNumber() + input2.getNumber() == sum.getNumber()) && input1 != input2
							&& !usedInputs.contains(hash)) {
						float ratio = (sum.getNumber() / 100.0F) * 20000000.0F;
						addRecipe(new IRecipeInput[] { input1.getInput(),
								input2.getInput() }, totalEu(Math.round(ratio)), sum.getOutput());
						usedInputs.add(hash);
					}
				}
			}
		}
		/** Just regular recipes added manually **/
		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getIc2(Ic2Items.emptyCell, 1)),
				input(GTMaterialGen.getIc2(Ic2Items.uuMatter, 1)) }, totalEu(10000000), GTMaterialGen.getIc2(Ic2Items.plasmaCell, 1));
	}

	public static IRecipeModifier[] totalEu(int total) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((total / 8196) - 100) };
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
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 8196);
	}

	@Override
	public Map<BlockPos, IBlockState> provideStructure() {
		Map<BlockPos, IBlockState> states = super.provideStructure();
		int3 dir = new int3(getPos(), getFacing());
		// first line
		states.put(dir.forward(3).asBlockPos(), coilState);
		states.put(dir.right(1).asBlockPos(), coilState);
		states.put(dir.back(1).asBlockPos(), coilState);
		states.put(dir.right(1).asBlockPos(), coilState);
		// second line
		states.put(dir.back(1).asBlockPos(), coilState);
		states.put(dir.right(1).asBlockPos(), coilState);
		states.put(dir.back(1).asBlockPos(), coilState);
		states.put(dir.back(1).asBlockPos(), coilState);
		// third line
		states.put(dir.left(1).asBlockPos(), coilState);
		states.put(dir.back(1).asBlockPos(), coilState);
		states.put(dir.left(1).asBlockPos(), coilState);
		states.put(dir.back(1).asBlockPos(), coilState);
		// fourth line
		states.put(dir.left(1).asBlockPos(), coilState);
		states.put(dir.left(1).asBlockPos(), coilState);
		states.put(dir.forward(1).asBlockPos(), coilState);
		states.put(dir.left(1).asBlockPos(), coilState);
		// fifth
		states.put(dir.forward(1).asBlockPos(), coilState);
		states.put(dir.left(1).asBlockPos(), coilState);
		states.put(dir.forward(1).asBlockPos(), coilState);
		states.put(dir.forward(1).asBlockPos(), coilState);
		// sixth
		states.put(dir.right(1).asBlockPos(), coilState);
		states.put(dir.forward(1).asBlockPos(), coilState);
		states.put(dir.right(1).asBlockPos(), coilState);
		states.put(dir.forward(1).asBlockPos(), coilState);
		return states;
	}

	@Override
	public boolean checkStructure() {
		int3 dir = new int3(getPos(), getFacing());
		// first line
		if (!(isCoil(dir.forward(3)) && isCoil(dir.right(1)) && isCoil(dir.back(1)) && isCoil(dir.right(1))
		// second line
				&& isCoil(dir.back(1)) && isCoil(dir.right(1)) && isCoil(dir.back(1)) && isCoil(dir.back(1))
				// third line
				&& isCoil(dir.left(1)) && isCoil(dir.back(1)) && isCoil(dir.left(1)) && isCoil(dir.back(1))
				// fourth
				&& isCoil(dir.left(1)) && isCoil(dir.left(1)) && isCoil(dir.forward(1)) && isCoil(dir.left(1))
				// fifth
				&& isCoil(dir.forward(1)) && isCoil(dir.left(1)) && isCoil(dir.forward(1)) && isCoil(dir.forward(1))
				// sixth
				&& isCoil(dir.right(1)) && isCoil(dir.forward(1)) && isCoil(dir.right(1)) && isCoil(dir.forward(1)))) {
			this.status = "No";
			this.getNetwork().updateTileGuiField(this, "status");
			return false;
		}
		this.status = "Yes";
		this.getNetwork().updateTileGuiField(this, "status");
		return true;
	}

	public boolean isCoil(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == coilState;
	}
}
