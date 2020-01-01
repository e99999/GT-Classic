package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterialElement;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.common.container.GTContainerMatterFabricator;
import gtclassic.common.gui.GTGuiMachine.GTMatterFabricatorGui;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;

public class GTTileMatterFabricator extends TileEntityElecMachine implements ITickable, IProgressMachine, IHasGui {

	protected static final int[] slotInputs = { 0, 1, 2, 3, 4, 5, 6, 7 };
	protected static final int[] slotOutputs = { 8 };
	@NetworkField(index = 7)
	float progress = 0;
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.uuamplifier");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/matterfabricator.png");

	public GTTileMatterFabricator() {
		super(9, 134217728);
		maxEnergy = 10000000;
		addGuiFields("progress");
		addInfos(new ProgressInfo(this));
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerInputFilter(CommonFilters.Anything, slotInputs);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.progress = nbt.getFloat("progress");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setFloat("progress", this.progress);
		return nbt;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GTMatterFabricatorGui.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerMatterFabricator(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
	}

	@Override
	public float getMaxProgress() {
		return 1000000;
	}

	@Override
	public float getProgress() {
		return progress;
	}

	public static void postInit() {
		/** Collecting ic2 entries **/
		for (RecipeEntry var : ClassicRecipes.massfabAmplifier.getRecipeMap()) {
			addAmplifier(new IRecipeInput[] {
					new RecipeInputItemStack(var.getInput().getInputs().get(0)) }, value(var.getOutput().getMetadata().getInteger("amplification")), GTMaterialGen.getIc2(Ic2Items.uuMatter, 1));
		}
		/** Adding my elements **/
		for (GTMaterialElement element : GTMaterialElement.getElementList()) {
			int value = element.getNumber() * 1000;
			if (value < 5000) {
				value = 5000;
			}
			if (element.getNumber() != 77) {
				addAmplifier(new IRecipeInput[] {
						element.getInput() }, value(value), GTMaterialGen.getIc2(Ic2Items.uuMatter, 1));
			}
		}
	}

	/**
	 * Stuff below for actual amp recipes and JEI iterators
	 **/
	public static IRecipeModifier[] value(int amount) {
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create((amount / 1) - 100) };
	}

	public static void addAmplifier(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
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
		addAmplifier(inlist, new MachineOutput(mods, outlist));
	}

	static void addAmplifier(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getUnlocalizedName(), 1);
	}

	public static void removeRecipe(String id) {
		RECIPE_LIST.removeRecipe(id);
	}

	@Override
	public void update() {
		this.setActive(hasPower() && !redstoneEnabled());
		// Below i try to iterate the input slots to check for valid amplifier
		ItemStack output = this.inventory.get(8);
		// Redstone check last because its the most CPU intensive.
		if (hasPower() && output.getCount() < output.getMaxStackSize() && !redstoneEnabled()) {
			// Checking ItemStacks first because it reduces iteration.
			for (int i = 0; i < 8; ++i) {
				ItemStack stack = inventory.get(i);
				if (stack.isEmpty()) {
					// If stack is null then we do not need to check the recipe list for it.
					continue;
				}
				for (MultiRecipe map : RECIPE_LIST.getRecipeList()) {
					IRecipeInput input = map.getInput(0);
					// Doing a input Check this way because it allows the RecipeInput to define what
					// it compares with.
					// Not the inhouse ItemStack compare.
					if (input.matches(stack) && stack.getCount() >= input.getAmount()) {
						int uuValue = map.getOutputs().getMetadata().getInteger("RecipeTime") + 100;
						if (energy - uuValue < 0) {
							// Using break because it found the matching item but it does not have enough
							// energy for i t.
							// No need to further compare.
							break;
						}
						stack.shrink(input.getAmount()); // Allowing multi item usage
						energy -= uuValue;
						progress += uuValue;
						updateGui();
						checkProgress();
						return;
					}
				}
			}
		}
	}

	public boolean redstoneEnabled() {
		return this.world.isBlockPowered(this.getPos());
	}

	public void checkProgress() {
		// If the progress is full, produce a UU-Matter
		ItemStack output = this.inventory.get(8);
		if (progress >= getMaxProgress()) {
			if (output.isEmpty()) {
				this.inventory.set(8, GTMaterialGen.getIc2(Ic2Items.uuMatter, 1));
			} else if (StackUtil.isStackEqual(output, Ic2Items.uuMatter)) {
				output.grow(1);// Do not copy the stack just grow it. Thats why the functions exist. Also
								// prevents cheating.
			}
			// Not wasting extra EU when overcharging.
			progress -= getMaxProgress();
		}
	}

	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, "progress");
		this.getNetwork().updateTileGuiField(this, "energy");
	}

	public boolean hasPower() {
		return energy >= 5000;
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public boolean needsInitialRedstoneUpdate() {
		return true;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	public int getMaxEnergy() {
		return this.maxEnergy;
	}
	
	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}
}
