package gtclassic.tile;

import com.google.common.collect.Lists;
import gtclassic.util.GTUtils;
import gtclassic.util.int3;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import gtclassic.util.recipe.GTMultiInputRecipeList.MultiRecipe;
import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.energy.EnergyNet;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.core.IC2;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.EnergyUsageInfo;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.block.base.util.output.MultiSlotOutput;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class GTTileBaseMultiInputMachine extends TileEntityElecMachine
	implements IOutputMachine, IProgressMachine, IEnergyUser, ITickable, IHasGui, INetworkTileEntityEventListener {
	@NetworkField(index = 7)
	public float progress = 0;

	// Import and Export Booleans
	public boolean allowImport = true;
	public boolean allowExport = true;

	// Defaults
	public int defaultEnergyConsume;
	public int defaultOperationLength;
	public int defaultMaxInput;
	public int defaultEnergyStorage;

	// Currents WithUpgrades
	public int energyConsume;
	public int operationLength;
	public float progressPerTick;
	@NetworkField(index = 8)
	public float soundLevel = 1F;

	// Current Usage & Time
	@NetworkField(index = 9)
	public int recipeOperation;
	@NetworkField(index = 10)
	public int recipeEnergy;

	@NetworkField(index = 11)
	public boolean redstoneInverted;
	@NetworkField(index = 12)
	public boolean redstoneSensitive;
	public boolean defaultSensitive;

	public MultiRecipe lastRecipe;
	public boolean shouldCheckRecipe;
	public final boolean supportsUpgrades;
	public final int upgradeSlots;

	public AudioSource audioSource;

	LinkedList<IStackOutput> outputs = new LinkedList<IStackOutput>();

	public GTTileBaseMultiInputMachine(int slots, int upgrades, int energyPerTick, int maxProgress, int maxinput) {
		super(slots + upgrades, maxinput);
		supportsUpgrades = upgrades > 0;
		upgradeSlots = upgrades;
		energyConsume = energyPerTick;
		defaultEnergyConsume = energyPerTick;
		operationLength = maxProgress;
		defaultOperationLength = maxProgress;
		defaultMaxInput = maxInput;
		defaultEnergyStorage = energyPerTick * maxProgress;
		defaultSensitive = false;
		progressPerTick = 1F;
		shouldCheckRecipe = true;
		addNetworkFields("soundLevel", "redstoneInverted", "redstoneSensitive");
		addGuiFields("recipeOperation", "recipeEnergy", "progress");
		addInfos(new EnergyUsageInfo(this), new ProgressInfo(this));
	}

	@Override
	public void update() {
		tryImportItems();
		handleRedstone();
		updateNeighbors();
		boolean noRoom = addToInventory();
		if (shouldCheckRecipe) {
			lastRecipe = getRecipe();
			shouldCheckRecipe = false;
		}
		boolean canWork = canWork() && !noRoom;
		boolean operate = (canWork && lastRecipe != null);
		if (operate) {
			handleChargeSlot(maxEnergy);
		}
		if (operate && energy >= energyConsume) {
			if (!getActive()) {
				getNetwork().initiateTileEntityEvent(this, 0, false);
			}
			setActive(true);
			progress += progressPerTick;
			useEnergy(recipeEnergy);
			if (progress >= recipeOperation) {
				process(lastRecipe);
				progress = 0;
				notifyNeighbors();
			}
			getNetwork().updateTileGuiField(this, "progress");
		} else {
			if (getActive()) {
				if (progress != 0) {
					getNetwork().initiateTileEntityEvent(this, 1, false);
				} else {
					getNetwork().initiateTileEntityEvent(this, 2, false);
				}
			}
			if (lastRecipe == null && progress != 0) {
				progress = 0;
				getNetwork().updateTileGuiField(this, "progress");
			}
			setActive(false);
		}
		if (supportsUpgrades) {
			for (int i = 0; i < upgradeSlots; i++) {
				ItemStack item = inventory.get(i + inventory.size() - upgradeSlots);
				if (item.getItem() instanceof IMachineUpgradeItem) {
					((IMachineUpgradeItem) item.getItem()).onTick(item, this);
				}
			}
		}
		updateComparators();
		tryExportItems();
	}

	public void process(MultiRecipe recipe) {
		for (ItemStack stack : recipe.getOutputs().getRecipeOutput(getWorld().rand, getTileData())) {
			outputs.add(new MultiSlotOutput(stack, getOutputSlots()));
		}

		//Consume recipe Inputs
		int[] inputs = getInputSlots();
		int consumedInputs = 0;
		List<ItemStack> toConsume = new ArrayList<>();
		recipe.getInputs().forEach(i -> toConsume.addAll(i.getInputs()));
		for (int i = 0; i < toConsume.size(); i++) {
			for (int j = 0; j < inputs.length; j++) {
				ItemStack stack = inventory.get(j);
				if (!toConsume.get(i).isItemEqual(stack)) continue;
				if (stack.getItem().hasContainerItem(stack)) {
					setStackInSlot(j, stack.getItem().getContainerItem(stack));
					consumedInputs++;
					break;
				} else {
					ItemStack newConsume = GTUtils.consumeAndReturn(stack, toConsume.get(i));
					System.out.println(newConsume.toString());
					if (newConsume.isEmpty()) {
						consumedInputs++;
						break;
					}
				}
			}
			if (consumedInputs == recipe.getInputSize()) break;
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
	}

	public boolean addToInventory() {
		if (outputs.isEmpty()) {
			return false;
		}
		for (Iterator<IStackOutput> iter = outputs.iterator(); iter.hasNext();) {
			IStackOutput output = iter.next();
			if (output.addToInventory(this)) {
				iter.remove();
			}
		}
		return outputs.size() > 0;
	}

	public List<ItemStack> getInputs() {
		ArrayList<ItemStack> inputs = new ArrayList<>();
		int[] inputSlots = getInputSlots();
		for (int i = 0; i < inputSlots.length; i++) {
			GTUtils.mergeItems(inputs, Lists.newArrayList(inventory.get(inputSlots[i]).copy()));
		}
		return inputs;
	}

	public MultiRecipe getRecipe() {
		System.out.println("checking");
		if (lastRecipe == GTMultiInputRecipeList.INVALID_RECIPE) {
			return null;
		}

		System.out.println(lastRecipe);
		//Check if previous recipe is valid
		List<ItemStack> inputs = getInputs();
		if (lastRecipe != null) {
			lastRecipe = checkRecipe(lastRecipe, inputs) ? lastRecipe : null;
			if (lastRecipe != null) {
				System.out.println("LAST RECIPE WAS VALID");
			}
		}

		//If previous is not valid, find a new one
		if (lastRecipe == null) {
			//Find list of valid recipes for inputs
			ArrayList<MultiRecipe> validRecipes = new ArrayList<>();
			getRecipeList().getRecipeList().forEach(r -> {
				if (checkRecipe(r, inputs)) {
					validRecipes.add(r);
				}
			});

			//Return the recipe with the most input stacks
			if (validRecipes.size() == 0) {
				return null;
			} else if (validRecipes.size() == 1) {
				lastRecipe = validRecipes.get(0);
			} else {
				int indexOfMostInputs = -1, lastBiggest = 0;
				for (int i = 0; i < validRecipes.size(); i++) {
					if (validRecipes.get(i).getInputSize() > lastBiggest) indexOfMostInputs = i;
				}
				lastRecipe = validRecipes.get(indexOfMostInputs);
			}
		}

		//If no recipe is found, return
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
				if (StackUtil.isStackEqual(inventory.get(outputSlot), output, false, true)) {
					if (inventory.get(outputSlot).getCount() + output.getCount() <= inventory.get(outputSlot)
						.getMaxStackSize()) {
						return lastRecipe;
					}
				}
			}
		}
		return null;
	}

	public boolean checkRecipe(MultiRecipe entry, List<ItemStack> inputs) {
		int matches = 0;
		int inputCount = inputs.size();
		for (int i = 0; i < entry.getInputSize(); i++) {
			for (int j = 0; j < inputCount; j++) {
				if (entry.matches(i, inputs.get(j))) {
					matches++;
					break;
				}
			}
		}
		return matches >= entry.getInputSize();
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		super.setStackInSlot(slot, stack);
		shouldCheckRecipe = true;
		if (isSimulating() && lastRecipe == GTMultiInputRecipeList.INVALID_RECIPE && isRecipeSlot(slot)) {
			lastRecipe = null;
		}
		markDirty();
	}

	public void applyRecipeEffect(MachineOutput output) {
		if (output == null || output.getMetadata() == null) {
			if (recipeEnergy != energyConsume) {
				recipeEnergy = energyConsume;
				if (recipeEnergy < 1) {
					recipeEnergy = 1;
				}
				getNetwork().updateTileGuiField(this, "recipeEnergy");
			}
			if (recipeOperation != operationLength) {
				recipeOperation = operationLength;
				if (recipeOperation < 1) {
					recipeOperation = 1;
				}
				getNetwork().updateTileGuiField(this, "recipeOperation");
			}
			return;
		}
		NBTTagCompound nbt = output.getMetadata();
		double energyMod = nbt.hasKey("RecipeEnergyModifier") ? nbt.getDouble("RecipeEnergyModifier") : 1F;
		int newEnergy = TileEntityBasicElectricMachine.applyModifier(energyConsume, nbt.getInteger("RecipeEnergy"),
			energyMod);
		if (newEnergy != recipeEnergy) {
			recipeEnergy = newEnergy;
			if (recipeEnergy < 1) {
				recipeEnergy = 1;
			}
			getNetwork().updateTileGuiField(this, "recipeEnergy");
		}
		double progMod = nbt.hasKey("RecipeTimeModifier") ? nbt.getDouble("RecipeTimeModifier") : 1F;
		int newProgress = TileEntityBasicElectricMachine.applyModifier(operationLength, nbt.getInteger("RecipeTime"),
			progMod);
		if (newProgress != recipeOperation) {
			recipeOperation = newProgress;
			if (recipeOperation < 1) {
				recipeOperation = 1;
			}
			getNetwork().updateTileGuiField(this, "recipeOperation");
		}
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (isSimulating()) {
			setOverclockRates();
		}
		shouldCheckRecipe = true;
	}

	public void setOverclockRates() {
		if (!supportsUpgrades) {
			return;
		}
		lastRecipe = null;
		int extraProcessSpeed = 0;
		double processingSpeedMultiplier = 1.0D;
		int extraProcessTime = 0;
		double processTimeMultiplier = 1.0D;
		int extraEnergyDemand = 0;
		double energyDemandMultiplier = 1.0D;
		int extraEnergyStorage = 0;
		double energyStorageMultiplier = 1.0D;
		int extraTier = 0;
		float soundModfier = 1.0F;
		boolean redstonePowered = false;
		redstoneSensitive = defaultSensitive;
		for (int i = 0; i < 4; i++) {
			ItemStack item = inventory.get(i + inventory.size() - 4);
			if (item.getItem() instanceof IMachineUpgradeItem) {
				IMachineUpgradeItem upgrade = (IMachineUpgradeItem) item.getItem();
				upgrade.onInstalling(item, this);
				extraProcessSpeed += upgrade.getExtraProcessSpeed(item, this) * item.getCount();
				processingSpeedMultiplier *= Math.pow(upgrade.getProcessSpeedMultiplier(item, this), item.getCount());
				extraProcessTime += upgrade.getExtraProcessTime(item, this) * item.getCount();
				processTimeMultiplier *= Math.pow(upgrade.getProcessTimeMultiplier(item, this), item.getCount());
				extraEnergyDemand += upgrade.getExtraEnergyDemand(item, this) * item.getCount();
				energyDemandMultiplier *= Math.pow(upgrade.getEnergyDemandMultiplier(item, this), item.getCount());
				extraEnergyStorage += upgrade.getExtraEnergyStorage(item, this) * item.getCount();
				energyStorageMultiplier *= Math.pow(upgrade.getEnergyStorageMultiplier(item, this), item.getCount());
				soundModfier *= Math.pow(upgrade.getSoundVolumeMultiplier(item, this), item.getCount());
				extraTier += upgrade.getExtraTier(item, this) * item.getCount();
				if (upgrade.useRedstoneInverter(item, this)) {
					redstonePowered = true;
				}
			}
		}
		redstoneInverted = redstonePowered;
		progressPerTick = TileEntityBasicElectricMachine.applyFloatModifier(1, extraProcessSpeed,
			processingSpeedMultiplier);
		energyConsume = TileEntityBasicElectricMachine.applyModifier(defaultEnergyConsume, extraEnergyDemand,
			energyDemandMultiplier);
		operationLength = TileEntityBasicElectricMachine.applyModifier(defaultOperationLength, extraProcessTime,
			processTimeMultiplier);
		setMaxEnergy(TileEntityBasicElectricMachine.applyModifier(defaultEnergyStorage, extraEnergyStorage,
			energyStorageMultiplier));
		tier = baseTier + extraTier;
		if (tier > 13) {
			tier = 13;
		}
		maxInput = (int) EnergyNet.instance.getPowerFromTier(tier);
		if (energy > maxEnergy) {
			energy = maxEnergy;
		}
		soundLevel = soundModfier;
		if (progressPerTick < 0.01F) {
			progressPerTick = 0.01F;
		}
		if (operationLength < 1) {
			operationLength = 1;
		}
		if (energyConsume < 1) {
			energyConsume = 1;
		}
		if (lastRecipe == null || lastRecipe == GTMultiInputRecipeList.INVALID_RECIPE) {
			applyRecipeEffect(null);
		} else {
			applyRecipeEffect(lastRecipe.getOutputs());
		}
		getNetwork().updateTileEntityField(this, "redstoneInverted");
		getNetwork().updateTileEntityField(this, "redstoneSensitive");
		getNetwork().updateTileEntityField(this, "soundLevel");
		getNetwork().updateTileGuiField(this, "maxInput");
		getNetwork().updateTileGuiField(this, "energy");
	}

	public abstract int[] getInputSlots();

	public abstract IFilter[] getInputFilters(int[] slots);

	public abstract boolean isRecipeSlot(int slot);

	public abstract int[] getOutputSlots();

	public abstract GTMultiInputRecipeList getRecipeList();

	public boolean canWork() {
		if (!redstoneSensitive) {
			return true;
		}
		return isRedstonePowered();
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public double getEnergy() {
		return energy;
	}

	@Override
	public boolean useEnergy(double amount, boolean simulate) {
		if (energy < amount) {
			return false;
		}
		if (!simulate) {
			useEnergy((int) amount);
		}
		return true;
	}

	@Override
	public void setRedstoneSensitive(boolean active) {
		if (redstoneSensitive != active) {
			redstoneSensitive = active;
		}
	}

	@Override
	public boolean isRedstoneSensitive() {
		return redstoneSensitive;
	}

	@Override
	public boolean isProcessing() {
		return getActive();
	}

	@Override
	public boolean isValidInput(ItemStack par1) {
		return getRecipeList().isValidRecipeInput(par1);
	}

	@Override
	public World getMachineWorld() {
		return getWorld();
	}

	@Override
	public BlockPos getMachinePos() {
		return getPos();
	}

	@Override
	public int getEnergyUsage() {
		return recipeEnergy;
	}

	@Override
	public float getProgress() {
		return progress;
	}

	@Override
	public float getMaxProgress() {
		return recipeOperation;
	}

	@Override
	public IHasInventory getOutputInventory() {
		return new RangedInventoryWrapper(this, getOutputSlots());
	}

	@Override
	public IHasInventory getInputInventory() {
		int[] input = getInputSlots();
		RangedInventoryWrapper result = new RangedInventoryWrapper(this, input).addFilters(getInputFilters(input));
		return result;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !isInvalid();
	}

	public void onUnloaded() {
		if (this.isRendering() && this.audioSource != null) {
			IC2.audioManager.removeSources(this);
			this.audioSource.remove();
			this.audioSource = null;
		}

		super.onUnloaded();
	}

	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.electricFurnaceLoop;
	}

	public ResourceLocation getInterruptSoundFile() {
		return Ic2Sounds.interruptingSound;
	}

	public void onNetworkEvent(int event) {
		if (this.audioSource != null && this.audioSource.isRemoved()) {
			this.audioSource = null;
		}

		if (this.audioSource == null && this.getStartSoundFile() != null) {
			this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, this.getStartSoundFile(), true,
				false, IC2.audioManager.defaultVolume * this.soundLevel);
		}

		if (event == 0) {
			if (this.audioSource != null) {
				this.audioSource.play();
			}
		} else if (event == 1) {
			if (this.audioSource != null) {
				this.audioSource.stop();
				if (this.getInterruptSoundFile() != null) {
					IC2.audioManager.playOnce(this, PositionSpec.Center, this.getInterruptSoundFile(), false,
						IC2.audioManager.defaultVolume * this.soundLevel);
				}
			}
		} else if (event == 2 && this.audioSource != null) {
			this.audioSource.stop();
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public void onGuiClosed(EntityPlayer player) {

	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return false;
	}

	/*
	 * Below I am experimenting with moving item into the multi block tile
	 */

	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.up(1).asBlockPos());
	}

	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.right(1).asBlockPos());
	}

	public void tryImportItems() {

		TileEntity te = getImportTile();

		if (this.allowImport) {
			if (world.getTotalWorldTime() % 20 == 0 && canWork() && te != null) {
				IItemTransporter slave = TransporterManager.manager.getTransporter(te, true);
				IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
				if (slave == null) {
					return;
				}

				IFilter filter = new MachineFilter(this);
				int limit = 64;

				for (int i = 0; i < limit; ++i) {
					ItemStack stack = slave.removeItem(filter, getFacing().getOpposite(), 1, false);
					if (stack.isEmpty()) {
						break;
					}

					ItemStack added = controller.addItem(stack, getFacing().UP, true);
					if (added.getCount() <= 0) {
						break;
					}

					slave.removeItem(filter, getFacing().getOpposite(), 1, true);
				}
			}
		}
	}

	public void tryExportItems() {

		TileEntity te = getExportTile();

		if (this.allowExport) {
			if (world.getTotalWorldTime() % 20 == 0 && te != null) {
				IItemTransporter slave = TransporterManager.manager.getTransporter(te, true);
				IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
				if (slave == null) {
					return;
				}

				int limit = 64;

				for (int i = 0; i < limit; ++i) {
					ItemStack stack = controller.removeItem(CommonFilters.Anything, getFacing().EAST, 1, false);
					if (stack.isEmpty()) {
						break;
					}

					ItemStack added = slave.addItem(stack, getFacing().UP, true);
					if (added.getCount() <= 0) {
						break;
					}

					controller.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
				}
			}
		}
	}

}
