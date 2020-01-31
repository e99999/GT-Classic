package gtclassic.common.tile;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeMachineHandler;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.api.tile.GTTileBaseMachine;
import gtclassic.common.GTItems;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerCentrifuge;
import gtclassic.common.gui.GTGuiMachine.GTIndustrialCentrifugeGui;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.util.output.MultiSlotOutput;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;

public class GTTileCentrifuge extends GTTileBaseMachine implements ITankListener, IClickable {

	public static final int SLOT_FUEL = 9;
	public static final int SLOT_TANK = 8;
	public static final String NBT_TANK = "tank";
	protected static final int[] SLOT_INPUTS = { 0, 1 };
	protected static final int[] SLOT_OUTPUTS = { 2, 3, 4, 5, 6, 7 };
	@NetworkField(index = 13)
	private IC2Tank tank;
	public IFilter filter = new MachineFilter(this);
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/industrialcentrifuge.png");
	private static final int EU_TICK = 16;
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.centrifuge", EU_TICK);
	protected static final ItemStack BLOCK_RED_SAND = new ItemStack(Blocks.SAND, 32, 1);

	public GTTileCentrifuge() {
		super(10, 4, EU_TICK, 100, 32);
		setFuelSlot(SLOT_FUEL);
		this.tank = new IC2Tank(16000);
		this.tank.addListener(this);
		this.addGuiFields(NBT_TANK);
		this.maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, SLOT_FUEL);
		handler.registerDefaultSlotAccess(AccessRule.Import, SLOT_INPUTS);
		handler.registerDefaultSlotAccess(AccessRule.Export, SLOT_OUTPUTS);
		handler.registerDefaultSlotsForSide(RotationList.UP, 0);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, 1);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), SLOT_OUTPUTS);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), SLOT_FUEL);
		handler.registerInputFilter(filter, SLOT_INPUTS);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, SLOT_FUEL);
		handler.registerSlotType(SlotType.Fuel, SLOT_FUEL);
		handler.registerSlotType(SlotType.Input, SLOT_INPUTS);
		handler.registerSlotType(SlotType.Output, SLOT_OUTPUTS);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.INDUSTRIAL_CENTRIFUGE;
	}

	@Override
	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, NBT_TANK);
		this.setStackInSlot(SLOT_TANK, ItemDisplayIcon.createWithFluidStack(this.tank.getFluid()));
		shouldCheckRecipe = true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag(NBT_TANK));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.tank.writeToNBT(this.getTag(nbt, NBT_TANK));
		return nbt;
	}

	@Override
	public void process(MultiRecipe recipe) {
		MachineOutput output = recipe.getOutputs().copy();
		for (ItemStack stack : output.getRecipeOutput(getWorld().rand, getTileData())) {
			outputs.add(new MultiSlotOutput(stack, getOutputSlots()));
			onRecipeComplete();
		}
		NBTTagCompound nbt = recipe.getOutputs().getMetadata();
		boolean shiftContainers = nbt != null && nbt.getBoolean(MOVE_CONTAINER_TAG);
		boolean fluidExtracted = false;
		List<ItemStack> inputs = getInputs();
		List<IRecipeInput> recipeKeys = new LinkedList<IRecipeInput>(recipe.getInputs());
		for (Iterator<IRecipeInput> keyIter = recipeKeys.iterator(); keyIter.hasNext();) {
			IRecipeInput key = keyIter.next();
			if (key instanceof RecipeInputFluid && !fluidExtracted) {
				tank.drainInternal(((RecipeInputFluid) key).fluid, true);
				fluidExtracted = true;
				keyIter.remove();
				continue;
			}
			int count = key.getAmount();
			for (Iterator<ItemStack> inputIter = inputs.iterator(); inputIter.hasNext();) {
				ItemStack input = inputIter.next();
				if (key.matches(input)) {
					if (input.getCount() >= count) {
						if (input.getItem().hasContainerItem(input)) {
							if (!shiftContainers) {
								continue;
							}
							ItemStack container = input.getItem().getContainerItem(input);
							if (!container.isEmpty()) {
								container.setCount(count);
								outputs.add(new MultiSlotOutput(container, getOutputSlots()));
							}
						}
						input.shrink(count);
						count = 0;
						if (input.isEmpty()) {
							inputIter.remove();
						}
						keyIter.remove();
						break;
					}
					if (input.getItem().hasContainerItem(input)) {
						if (!shiftContainers) {
							continue;
						}
						ItemStack container = input.getItem().getContainerItem(input);
						if (!container.isEmpty()) {
							container.setCount(input.getCount());
							outputs.add(new MultiSlotOutput(container, getOutputSlots()));
						}
					}
					count -= input.getCount();
					input.setCount(0);
					inputIter.remove();
				}
			}
		}
		addToInventory();
		if (supportsUpgrades) {
			for (int i = 0; i < upgradeSlots; i++) {
				ItemStack item = inventory.get(i + inventory.size() - upgradeSlots);
				if (item.getItem() instanceof IMachineUpgradeItem) {
					((IMachineUpgradeItem) item.getItem()).onProcessFinished(item, this);
				}
			}
		}
		shouldCheckRecipe = true;
	}

	@Override
	public MultiRecipe getRecipe() {
		if (lastRecipe == GTRecipeMultiInputList.INVALID_RECIPE) {
			return null;
		}
		// Check if previous recipe is valid
		List<ItemStack> inputs = getInputs();
		FluidStack fluid = tank.getFluid();
		if (lastRecipe != null) {
			lastRecipe = checkRecipe(lastRecipe, fluid, StackUtil.copyList(inputs)) ? lastRecipe : null;
			if (lastRecipe == null) {
				progress = 0;
			}
		}
		// If previous is not valid, find a new one
		if (lastRecipe == null) {
			lastRecipe = getRecipeList().getPriorityRecipe(new Predicate<MultiRecipe>() {

				@Override
				public boolean test(MultiRecipe t) {
					return checkRecipe(t, fluid, StackUtil.copyList(inputs));
				}
			});
		}
		// If no recipe is found, return
		if (lastRecipe == null) {
			return null;
		}
		applyRecipeEffect(lastRecipe.getOutputs());
		int empty = 0;
		int[] outputSlots = getOutputSlots();
		for (int slot : outputSlots) {
			if (getStackInSlot(slot).isEmpty()) {
				empty++;
			}
		}
		if (empty == outputSlots.length) {
			return lastRecipe;
		}
		for (ItemStack output : lastRecipe.getOutputs().getAllOutputs()) {
			for (int outputSlot : outputSlots) {
				if (inventory.get(outputSlot).isEmpty()) {
					return lastRecipe;
				}
				if (StackUtil.isStackEqual(inventory.get(outputSlot), output, false, true)) {
					if (inventory.get(outputSlot).getCount()
							+ output.getCount() <= inventory.get(outputSlot).getMaxStackSize()) {
						return lastRecipe;
					}
				}
			}
		}
		return null;
	}

	public boolean checkRecipe(MultiRecipe entry, FluidStack inputFluid, List<ItemStack> inputs) {
		boolean hasCheckedFluid = false;
		List<IRecipeInput> recipeKeys = new LinkedList<IRecipeInput>(entry.getInputs());
		for (Iterator<IRecipeInput> keyIter = recipeKeys.iterator(); keyIter.hasNext();) {
			IRecipeInput key = keyIter.next();
			if (key instanceof RecipeInputFluid) {
				if (!hasCheckedFluid) {
					hasCheckedFluid = true;
					if (inputFluid != null && inputFluid.containsFluid(((RecipeInputFluid) key).fluid)) {
						keyIter.remove();
					}
				}
			}
			int toFind = key.getAmount();
			for (Iterator<ItemStack> inputIter = inputs.iterator(); inputIter.hasNext();) {
				ItemStack input = inputIter.next();
				if (key.matches(input)) {
					if (input.getCount() >= toFind) {
						input.shrink(toFind);
						keyIter.remove();
						if (input.isEmpty()) {
							inputIter.remove();
						}
						break;
					}
					toFind -= input.getCount();
					input.setCount(0);
					inputIter.remove();
				}
			}
		}
		return recipeKeys.isEmpty();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank)
				: super.getCapability(capability, facing);
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		return new LinkedHashSet<>(Arrays.asList(UpgradeType.values()));
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerCentrifuge(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTIndustrialCentrifugeGui.class;
	}

	@Override
	public int[] getInputSlots() {
		return SLOT_INPUTS;
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		return new IFilter[] { filter };
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return slot <= 1;
	}

	@Override
	public int[] getOutputSlots() {
		return SLOT_OUTPUTS;
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
		return Ic2Sounds.extractorOp;
	}

	public static void init() {
		Item tube = GTItems.testTube;
		addCustomRecipe(GTMaterialGen.getTube(GTMaterial.Oxygen, 1), GTMaterialGen.getIc2(Ic2Items.emptyCell, 2), totalEu(5000), GTMaterialGen.getIc2(Ic2Items.airCell, 2), GTMaterialGen.get(tube, 1));
		addRecipe(new IRecipeInput[] { input(GTMaterialGen.getIc2(Ic2Items.airCell, 16)),
				tubes(16) }, totalEu(1000000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 16), GTMaterialGen.getTube(GTMaterial.Nitrogen, 9), GTMaterialGen.getTube(GTMaterial.Oxygen, 4), GTMaterialGen.getTube(GTMaterial.Helium, 1), GTMaterialGen.getTube(GTMaterial.Neon, 1), GTMaterialGen.getTube(GTMaterial.Argon, 1));
		addRecipe(GTMaterialGen.getFluidStack("water", 6000), 6, totalEu(9000), GTMaterialGen.getTube(GTMaterial.Hydrogen, 4), GTMaterialGen.getTube(GTMaterial.Oxygen, 2));
		addRecipe(GTMaterialGen.getWater(6), 0, totalEu(9000), GTMaterialGen.getTube(GTMaterial.Hydrogen, 4), GTMaterialGen.getTube(GTMaterial.Oxygen, 2));
		addRecipe(GTMaterialGen.getIc2(Ic2Items.waterCell, 6), 6, totalEu(9000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 6), GTMaterialGen.getTube(GTMaterial.Hydrogen, 4), GTMaterialGen.getTube(GTMaterial.Oxygen, 2));
		addRecipe("dustCoal", 4, 0, totalEu(7500), GTMaterialGen.getDust(GTMaterial.Carbon, 8));
		addRecipe(GTMaterialGen.getIc2(Ic2Items.hydratedCoalDust, 8), 0, totalEu(22500), GTMaterialGen.getDust(GTMaterial.Carbon, 14), GTMaterialGen.getDust(GTMaterial.Thorium, 1));
		addRecipe("logRubber", 16, 4, totalEu(25000), GTMaterialGen.getDust(GTMaterial.Carbon, 8), GTMaterialGen.getIc2(Ic2Items.stickyResin, 8), GTMaterialGen.getIc2(Ic2Items.plantBall, 6), GTMaterialGen.getTube(GTMaterial.Methane, 4));
		addRecipe(GTMaterialGen.getFluidStack(GTMaterial.Hydrogen, 4000), 1, totalEu(6000), GTMaterialGen.getTube(GTMaterial.Deuterium, 1));
		addRecipe(GTMaterialGen.getTube(GTMaterial.Hydrogen, 4), 0, totalEu(6000), GTMaterialGen.get(tube, 3), GTMaterialGen.getTube(GTMaterial.Deuterium, 1));
		addRecipe(GTMaterialGen.getFluidStack(GTMaterial.Deuterium, 4000), 1, totalEu(6000), GTMaterialGen.getTube(GTMaterial.Tritium, 1));
		addRecipe(GTMaterialGen.getTube(GTMaterial.Deuterium, 4), 0, totalEu(6000), GTMaterialGen.get(tube, 3), GTMaterialGen.getTube(GTMaterial.Tritium, 1));
		addRecipe(GTMaterialGen.getFluidStack(GTMaterial.Helium, 16000), 1, totalEu(18000), GTMaterialGen.getTube(GTMaterial.Helium3, 1));
		addRecipe(GTMaterialGen.getTube(GTMaterial.Helium, 16), 0, totalEu(18000), GTMaterialGen.get(tube, 15), GTMaterialGen.getTube(GTMaterial.Helium3, 1));
		addRecipe("dustRuby", 9, 3, totalEu(25000), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getDust(GTMaterial.Chrome, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 3));
		addRecipe("dustSapphire", 8, 3, totalEu(20000), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getTube(GTMaterial.Oxygen, 3));
		addRecipe("dustEmerald", 29, 12, totalEu(30000), GTMaterialGen.getTube(GTMaterial.Oxygen, 9), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getTube(GTMaterial.Beryllium, 3), GTMaterialGen.getDust(GTMaterial.Silicon, 6));
		addRecipe("dustEnderPearl", 16, 16, totalEu(65000), GTMaterialGen.getTube(GTMaterial.Chlorine, 6), GTMaterialGen.getTube(GTMaterial.Nitrogen, 5), GTMaterialGen.getTube(GTMaterial.Beryllium, 1), GTMaterialGen.getTube(GTMaterial.Potassium, 4));
		addRecipe("dustEnderEye", 16, 0, totalEu(35000), GTMaterialGen.getDust(GTMaterial.EnderPearl, 8), GTMaterialGen.get(Items.BLAZE_POWDER, 8));
		addRecipe("dustLazurite", 28, 16, totalEu(295000), GTMaterialGen.getTube(GTMaterial.Sodium, 8), GTMaterialGen.getDust(GTMaterial.Aluminium, 6), GTMaterialGen.getDust(GTMaterial.Silicon, 6), GTMaterialGen.getTube(GTMaterial.Calcium, 8));
		addRecipe("dustPyrite", 2, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		addRecipe("dustCalcite", 10, 5, totalEu(50000), GTMaterialGen.getTube(GTMaterial.Calcium, 2), GTMaterialGen.getDust(GTMaterial.Carbon, 2), GTMaterialGen.getTube(GTMaterial.Oxygen, 3));
		addRecipe("dustSodalite", 11, 5, totalEu(115000), GTMaterialGen.getTube(GTMaterial.Chlorine, 1), GTMaterialGen.getTube(GTMaterial.Sodium, 4), GTMaterialGen.getDust(GTMaterial.Aluminium, 3), GTMaterialGen.getDust(GTMaterial.Silicon, 3));
		addRecipe("dustBauxite", 24, 16, totalEu(250000), GTMaterialGen.getTube(GTMaterial.Oxygen, 6), GTMaterialGen.getDust(GTMaterial.Aluminium, 16), GTMaterialGen.getDust(GTMaterial.Titanium, 1), GTMaterialGen.getTube(GTMaterial.Hydrogen, 10));
		addRecipe(GTMaterialGen.get(Items.MAGMA_CREAM, 1), 0, totalEu(2500), GTMaterialGen.get(Items.BLAZE_POWDER, 1), GTMaterialGen.get(Items.SLIME_BALL, 1));
		addRecipe("dirt", 64, 0, totalEu(50000), GTMaterialGen.get(Blocks.SAND, 32), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 2), GTMaterialGen.getIc2(Ic2Items.plantBall, 2), GTMaterialGen.get(Items.CLAY_BALL, 2));
		addRecipe("grass", 64, 0, totalEu(50000), GTMaterialGen.get(Blocks.SAND, 32), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 2), GTMaterialGen.getIc2(Ic2Items.plantBall, 4), GTMaterialGen.get(Items.CLAY_BALL, 2));
		addRecipe(GTMaterialGen.get(Blocks.MYCELIUM, 64), 0, totalEu(62500), GTMaterialGen.get(Blocks.SAND, 32), GTMaterialGen.get(Blocks.BROWN_MUSHROOM, 16), GTMaterialGen.get(Blocks.RED_MUSHROOM, 16), GTMaterialGen.get(Items.CLAY_BALL, 8));
		addRecipe("gemLapis", 64, 0, totalEu(125000), GTMaterialGen.getDust(GTMaterial.Sodalite, 8), GTMaterialGen.getDust(GTMaterial.Lazurite, 48), GTMaterialGen.getDust(GTMaterial.Pyrite, 4), GTMaterialGen.getDust(GTMaterial.Calcite, 4));
		addRecipe(GTMaterialGen.get(Items.BLAZE_POWDER, 8), 0, totalEu(15000), GTMaterialGen.getIc2(Ic2Items.coalDust, 2), GTMaterialGen.get(Items.GUNPOWDER, 1));
		addRecipe(GTMaterialGen.get(Blocks.SAND, 32), 1, totalEu(50000), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
		addRecipe(BLOCK_RED_SAND, 1, totalEu(50000), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getIc2(Ic2Items.clayDust), GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
		addRecipe("dustFlint", 8, 1, totalEu(5000), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
		addRecipe("dustClay", 7, 0, totalEu(19000), GTMaterialGen.getDust(GTMaterial.Lithium, 1), GTMaterialGen.getDust(GTMaterial.Silicon, 2), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getTube(GTMaterial.Sodium, 2));
		addRecipe(GTMaterialGen.getIc2(Ic2Items.stickyResin, 8), 0, totalEu(12500), GTMaterialGen.getIc2(Ic2Items.rubber, 28), GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 2), GTMaterialGen.getIc2(Ic2Items.plantBall, 2));
		addRecipe("dustGlowstone", 16, 1, totalEu(25000), GTMaterialGen.get(Items.REDSTONE, 8), GTMaterialGen.getIc2(Ic2Items.goldDust, 8), GTMaterialGen.getTube(GTMaterial.Helium, 1));
		addRecipe("dustRedstone", 10, 3, totalEu(35000), GTMaterialGen.getTube(GTMaterial.Mercury, 3), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getDust(GTMaterial.Pyrite, 5), GTMaterialGen.getDust(GTMaterial.Ruby, 1));
		addRecipe("dustNetherrack", 64, 0, totalEu(50000), GTMaterialGen.get(Items.GOLD_NUGGET, 4), GTMaterialGen.get(Items.REDSTONE, 4), GTMaterialGen.get(Items.GUNPOWDER, 8), GTMaterialGen.getIc2(Ic2Items.coalDust, 4), GTMaterialGen.getDust(GTMaterial.Sulfur, 4), GTMaterialGen.getDust(GTMaterial.Phosphorus, 2));
		addRecipe(GTMaterialGen.getFluidStack("lava", 16000), 0, totalEu(64000), GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.get(Items.IRON_INGOT, 4), GTMaterialGen.getDust(GTMaterial.Tungsten, 1), GTMaterialGen.getDust(GTMaterial.Basalt, 1));
		addRecipe(GTMaterialGen.getLava(16), 0, totalEu(64000), GTMaterialGen.get(tube, 16), GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.get(Items.IRON_INGOT, 4), GTMaterialGen.getDust(GTMaterial.Tungsten, 1), GTMaterialGen.getDust(GTMaterial.Basalt, 1));
		addRecipe(GTMaterialGen.getIc2(Ic2Items.lavaCell, 16), 0, totalEu(64000), GTMaterialGen.getIc2(Ic2Items.emptyCell, 16), GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.get(Items.IRON_INGOT, 4), GTMaterialGen.getDust(GTMaterial.Tungsten, 1), GTMaterialGen.getDust(GTMaterial.Basalt, 1));
		addRecipe("endstone", 64, 8, totalEu(100000), GTMaterialGen.get(Blocks.SAND, 48), GTMaterialGen.getTube(GTMaterial.Helium3, 4), GTMaterialGen.getTube(GTMaterial.Helium, 4), GTMaterialGen.getDust(GTMaterial.Tungsten, 1));
		/** New Recipes I added **/
		addRecipe("stoneGranite", 4, 0, totalEu(24000), GTMaterialGen.getDust(GTMaterial.Aluminium, 2), GTMaterialGen.getDust(GTMaterial.Flint, 1), GTMaterialGen.getIc2(Ic2Items.clayDust, 1));
		addRecipe("stoneDiorite", 16, 0, totalEu(36000), GTMaterialGen.getDust(GTMaterial.Nickel, 1));
		addRecipe("dustBasalt", 16, 0, totalEu(24000), GTMaterialGen.getDust(GTMaterial.Carbon, 1), GTMaterialGen.getDust(GTMaterial.Emerald, 1), GTMaterialGen.getDust(GTMaterial.Calcite, 3), GTMaterialGen.getDust(GTMaterial.Flint, 8));
		addRecipe("dustObsidian", 8, 6, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.ironDust, 2), GTMaterialGen.getDust(GTMaterial.Silicon, 2), GTMaterialGen.getTube(GTMaterial.Oxygen, 4));
		addRecipe(GTMaterialGen.get(Items.QUARTZ, 1), 2, totalEu(8000), GTMaterialGen.getDust(GTMaterial.Silicon, 1), GTMaterialGen.getTube(GTMaterial.Oxygen, 2));
		addRecipe(GTMaterialGen.get(Blocks.MAGMA, 64), 64, totalEu(3000), GTMaterialGen.getLava(64));
		addRecipe("dustElectrum", 2, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.silverDust, 1), GTMaterialGen.getIc2(Ic2Items.goldDust, 1));
		addRecipe("dustInvar", 3, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.ironDust, 2), GTMaterialGen.getDust(GTMaterial.Nickel, 1));
		addRecipe("dustBronze", 4, 0, totalEu(5000), GTMaterialGen.getIc2(Ic2Items.tinDust, 1), GTMaterialGen.getIc2(Ic2Items.copperDust, 3));
		addRecipe("dustPlatinum", 9, 0, totalEu(135000), GTMaterialGen.getDust(GTMaterial.Iridium, 1), GTMaterialGen.getDust(GTMaterial.Nickel, 2));
		addRecipe(GTMaterialGen.get(Items.ROTTEN_FLESH, 16), 4, totalEu(6000), GTMaterialGen.getTube(GTMaterial.Methane, 4), GTMaterialGen.get(Items.LEATHER, 4), GTMaterialGen.get(Items.SLIME_BALL, 1));
		addRecipe(GTMaterialGen.get(Blocks.SOUL_SAND, 12), 0, totalEu(12000), GTMaterialGen.get(Blocks.SAND, 11), GTMaterialGen.getTube(GTMaterial.Oil, 1));
		addRecipe(GTMaterialGen.getFluidStack(GTMaterial.Oil, 3000), 3, totalEu(96000), GTMaterialGen.getTube(GTMaterial.Fuel, 2), GTMaterialGen.getTube(GTMaterial.Lubricant, 1));
		addRecipe(GTMaterialGen.getTube(GTMaterial.Oil, 3), 0, totalEu(96000), GTMaterialGen.getTube(GTMaterial.Fuel, 2), GTMaterialGen.getTube(GTMaterial.Lubricant, 1));
		/*
		 * Recipes solely focused on getting methane from various things
		 */
		addMethaneRecipe("listAllmeatraw", 12);
		addMethaneRecipe("listAllmeatcooked", 12);
		addMethaneRecipe("listAllfishraw", 12);
		addMethaneRecipe("listAllfishcooked", 12);
		addMethaneRecipe("listAllfruit", 32);
		addMethaneRecipe("listAllveggie", 16);
		addMethaneRecipe("listAllcookie", 64);
		addMethaneRecipe(GTMaterialGen.get(Items.MUSHROOM_STEW, 16));
		addMethaneRecipe(GTMaterialGen.get(Items.BREAD, 64));
		addMethaneRecipe(GTMaterialGen.get(Items.MELON, 64));
		addMethaneRecipe(GTMaterialGen.get(Items.SPIDER_EYE, 32));
		addMethaneRecipe(GTMaterialGen.get(Items.POISONOUS_POTATO, 12));
		addMethaneRecipe(GTMaterialGen.get(Items.BAKED_POTATO, 24));
		addMethaneRecipe(GTMaterialGen.get(Items.CAKE, 8));
		addMethaneRecipe(GTMaterialGen.get(Blocks.BROWN_MUSHROOM_BLOCK, 12));
		addMethaneRecipe(GTMaterialGen.get(Blocks.RED_MUSHROOM_BLOCK, 12));
		addMethaneRecipe(GTMaterialGen.get(Blocks.BROWN_MUSHROOM, 32));
		addMethaneRecipe(GTMaterialGen.get(Blocks.RED_MUSHROOM, 32));
		addMethaneRecipe(GTMaterialGen.get(Items.NETHER_WART, 32));
		addMethaneRecipe(GTMaterialGen.getIc2(Ic2Items.terraWart, 16));
		addMethaneRecipe(GTMaterialGen.getIc2(Ic2Items.plantBall, 6));
		addMethaneRecipe(GTMaterialGen.getIc2(Ic2Items.compressedPlantBall, 4));
	}

	public static void addCustomRecipe(ItemStack stack0, ItemStack stack1, IRecipeModifier[] modifiers,
			ItemStack... outputs) {
		addRecipe(new IRecipeInput[] { new RecipeInputItemStack(stack0),
				new RecipeInputItemStack(stack1), }, modifiers, outputs);
	}

	public static void addCustomRecipe(String input, int amount, ItemStack stack, IRecipeModifier[] modifiers,
			ItemStack... outputs) {
		addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount),
				new RecipeInputItemStack(stack), }, modifiers, outputs);
	}

	public static void addMethaneRecipe(ItemStack stack) {
		addRecipe(stack, 1, totalEu(36000), GTMaterialGen.getTube(GTMaterial.Methane, 1));
	}

	public static void addMethaneRecipe(String input, int amount) {
		addRecipe(input, amount, 1, totalEu(36000), GTMaterialGen.getTube(GTMaterial.Methane, 1));
	}

	public static void addRecipe(ItemStack stack, int cells, IRecipeModifier[] modifiers, ItemStack... outputs) {
		if (cells > 0) {
			addRecipe(new IRecipeInput[] { new RecipeInputItemStack(stack),
					new RecipeInputItemStack(GTMaterialGen.get(GTItems.testTube, cells)) }, modifiers, outputs);
		} else {
			addRecipe(new IRecipeInput[] { new RecipeInputItemStack(stack) }, modifiers, outputs);
		}
	}

	public static void addRecipe(String input, int amount, int cells, IRecipeModifier[] modifiers,
			ItemStack... outputs) {
		if (cells > 0) {
			addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount),
					new RecipeInputItemStack(GTMaterialGen.get(GTItems.testTube, cells)) }, modifiers, outputs);
		} else {
			addRecipe(new IRecipeInput[] { new RecipeInputOreDict(input, amount) }, modifiers, outputs);
		}
	}

	public static void addRecipe(FluidStack fluid, int cells, IRecipeModifier[] modifiers, ItemStack... outputs) {
		if (cells > 0) {
			addRecipe(new IRecipeInput[] { new RecipeInputFluid(fluid),
					new RecipeInputItemStack(GTMaterialGen.get(GTItems.testTube, cells)) }, modifiers, outputs);
		} else {
			addRecipe(new IRecipeInput[] { new RecipeInputFluid(fluid) }, modifiers, outputs);
		}
	}

	public static IRecipeModifier[] totalEu(int total) {
		return GTRecipeMachineHandler.totalEu(RECIPE_LIST, total);
	}

	public static void addRecipe(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
		GTRecipeMachineHandler.addRecipe(RECIPE_LIST, inputs, modifiers, outputs);
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public void onLeftClick(EntityPlayer var1, Side var2) {
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing enumFacing, Side side) {
		return GTHelperFluid.doClickableFluidContainerThings(player, hand, world, pos, this.tank);
	}
}
