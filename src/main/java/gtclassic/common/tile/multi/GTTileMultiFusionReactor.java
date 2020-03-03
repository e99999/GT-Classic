package gtclassic.common.tile.multi;

import java.util.HashSet;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.api.helpers.int3;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialElement;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeMachineHandler;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.tile.multi.GTTileMultiBaseMachine;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerFusionReactor;
import gtclassic.common.gui.GTGuiMachine.GTFusionComputerGui;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
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

public class GTTileMultiFusionReactor extends GTTileMultiBaseMachine implements IEnergySource, IEmitterTile {

	public static final int slotInput0 = 0;
	public static final int slotInput1 = 1;
	public static final int slotOutput = 2;
	public IFilter filter = new MachineFilter(this);
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.fusion", 8192);
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/fusionreactor.png");
	public String status;
	IBlockState coilState = GTBlocks.casingFusion.getDefaultState();
	@NetworkField(index = 13)
	public int energyOut = 0;

	public GTTileMultiFusionReactor() {
		super(3, 0, 8192, 8192);
		maxEnergy = 100000000;
		this.status = "No";
		this.addGuiFields(new String[] { "status" });
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput0, slotInput1);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInput0);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotInput1);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutput);
		handler.registerInputFilter(filter, slotInput0, slotInput1);
		handler.registerSlotType(SlotType.Input, slotInput0, slotInput1);
		handler.registerSlotType(SlotType.Output, slotOutput);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.FUSION_REACTOR;
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
		this.energyOut = nbt.getInteger("energyOut");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("status", this.status);
		nbt.setInteger("energyOut", this.energyOut);
		return nbt;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerFusionReactor(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTFusionComputerGui.class;
	}

	@Override
	public int[] getInputSlots() {
		return new int[] { slotInput0, slotInput1 };
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		return new IFilter[] { filter };
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return slot <= slotInput1;
	}

	@Override
	public int[] getOutputSlots() {
		return new int[] { slotOutput };
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
		return Ic2Sounds.compressorOp;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return side != this.getFacing() && side != this.getFacing().getOpposite();
	}

	@Override
	public void onRecipeComplete() {
		if (this.lastRecipe != null && this.lastRecipe != GTRecipeMultiInputList.INVALID_RECIPE) {
			int rTime = lastRecipe.getOutputs().getMetadata().getInteger("RecipeTime") + 100;
			if (rTime < 3000) {
				return;
			}
			int euOutput = rTime * 32000;
			if (this.energyOut + euOutput >= 134217728) {
				this.energyOut = 134217728;
			} else {
				this.energyOut = this.energyOut + euOutput;
			}
		}
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

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing side) {
		return side == this.getFacing() || side == this.getFacing().getOpposite();
	}

	@Override
	public int getOutput() {
		return 134217728;
	}

	@Override
	public double getOfferedEnergy() {
		return (double) this.energyOut;
	}

	@Override
	public void drawEnergy(double amount) {
		this.energyOut = (int) ((double) this.energyOut - amount);
	}

	@Override
	public int getSourceTier() {
		return 12;
	}

	public static void postInit() {
		/** Just regular recipes added manually **/
		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getTube(GTMaterial.Deuterium, 1)),
				input(GTMaterialGen.getTube(GTMaterial.Tritium, 1)) }, totalEu(40000000), GTMaterialGen.getTube(GTMaterial.Helium, 1));
		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getTube(GTMaterial.Deuterium, 1)),
				input(GTMaterialGen.getTube(GTMaterial.Helium3, 1)) }, totalEu(40000000), GTMaterialGen.getTube(GTMaterial.Helium, 1));
		/** This iterates the element objects to create all Fusion recipes **/
		Set<Integer> usedInputs = new HashSet<>();
		for (GTMaterialElement sum : GTMaterialElement.getElementList()) {
			for (GTMaterialElement input1 : GTMaterialElement.getElementList()) {
				for (GTMaterialElement input2 : GTMaterialElement.getElementList()) {
					int hash = input1.hashCode() + input2.hashCode();
					if ((input1.getNumber() + input2.getNumber() == sum.getNumber()) && input1 != input2
							&& !usedInputs.contains(hash)) {
						float ratio = (sum.getNumber() / 100.0F) * 7000000.0F;
						IRecipeInput recipeInput1 = input1.isFluid() ? input1.getOutputAsInput() : input1.getInput();
						IRecipeInput recipeInput2 = input2.isFluid() ? input2.getOutputAsInput() : input2.getInput();
						addRecipe(new IRecipeInput[] { recipeInput1,
								recipeInput2 }, totalEu(Math.round(ratio)), sum.getOutput());
						usedInputs.add(hash);
					}
				}
			}
		}
		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getIc2(Ic2Items.emptyCell, 1)),
				input(GTMaterialGen.getIc2(Ic2Items.uuMatter, 1)) }, totalEu(10000000), GTMaterialGen.getIc2(Ic2Items.plasmaCell, 1));
	}

	public static IRecipeModifier[] totalEu(int total) {
		return GTRecipeMachineHandler.totalEu(RECIPE_LIST, total);
	}

	public static void addRecipe(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
		GTRecipeMachineHandler.addRecipe(RECIPE_LIST, inputs, modifiers, outputs);
	}
}
