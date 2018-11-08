package gtclassic.tileentity;

import gtclassic.GTClassic;
import gtclassic.container.GTContainerAlloySmelter;
import gtclassic.util.GTItems;
import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.INullableRecipeInput;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IRecipeMachine;
import ic2.api.classic.tile.MachineType;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.energy.EnergyNet;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.comparator.ComparatorManager;
import ic2.core.block.base.util.comparator.comparators.ComparatorProgress;
import ic2.core.block.base.util.info.EnergyUsageInfo;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.*;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.helpers.FilteredList;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class GTTileEntityAlloySmelter extends TileEntityElecMachine implements ITickable, IProgressMachine, IRecipeMachine, IOutputMachine, IHasGui, INetworkTileEntityEventListener, IEnergyUser {
    @NetworkField(index = 7)
    public float progress = 0.0F;

    public int defaultEnergyConsume;
    public int defaultOperationLength;
    public int defaultMaxInput;
    public int defaultEnergyStorage;
    public int energyConsume;
    public int operationLength;
    public float progressPerTick;

    @NetworkField(index = 8)
    public float soundLevel = 1.0F;

    public IMachineRecipeList.RecipeEntry lastRecipe;

    @NetworkField(index = 9)
    public int recipeOperation;

    @NetworkField(index = 10)
    public int recipeEnergy;

    @NetworkField(index = 11)
    public boolean redstoneInverted;

    @NetworkField(index = 12)
    public boolean redstoneSensitive;

    public boolean defaultSensitive;
    public List<ItemStack> results = new ArrayList();
    public AudioSource audioSource;
    public IFilter filter;

    public static final int slotInput1 = 0;
    public static final int slotInput2 = 1;
    public static final int slotFuel = 2;
    public static final int slotOutput = 3;
    public static final int slotOutput2 = 4;

    public GTTileEntityAlloySmelter() {
        this(9, 1, 400, 32);
    }

    public GTTileEntityAlloySmelter(int slots, int energyPerTick, int maxProgress, int maxInput)
    {
        super(slots, maxInput);
        this.setFuelSlot(slotFuel);
        this.setCustomName("tileOreWashingPlant");
        this.energyConsume = energyPerTick;
        this.defaultEnergyConsume = energyPerTick;
        this.operationLength = maxProgress;
        this.defaultOperationLength = maxProgress;
        this.defaultMaxInput = this.maxInput;
        this.defaultEnergyStorage = energyPerTick * maxProgress;
        this.defaultSensitive = false;
        this.addNetworkFields("soundLevel", "redstoneInverted", "redstoneSensitive");
        this.addGuiFields("recipeOperation", "recipeEnergy", "progress");
        this.addInfos(new EnergyUsageInfo(this), new ProgressInfo(this));
    }

    public MachineType getType() {
        return MachineType.macerator;
    }

    public static ResourceLocation getGuiTexture() {
        return new ResourceLocation(GTClassic.MODID, "textures/guisprites/guialloysmelter.png");
    }

    @Override
    public LocaleComp getBlockName() {
        return new LangComponentHolder.LocaleBlockComp("tile.alloySmelter");
    }
    public static IMachineRecipeList alloySmelter = new BasicMachineRecipeList("alloySmelter");

    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiComponentContainer.class;
    }

    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return alloySmelter.getRecipeInAndOutput(input, false);
    }

    @Override
    protected void addComparators(ComparatorManager manager)
    {
        super.addComparators(manager);
        manager.addComparatorMode(new ComparatorProgress(this));
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        this.filter = new MachineFilter(this);
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput1, slotInput2);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2);
        handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), 0, 2, 4);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.getOppositeList(), 1, 3);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
        handler.registerSlotType(SlotType.Fuel, slotFuel);
        handler.registerSlotType(SlotType.Input, slotInput1, slotInput2);
        handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2);
    }

    @Override
    public int getEnergyUsage()
    {
        return this.recipeEnergy;
    }

    @Override
    public float getProgress()
    {
        return this.progress;
    }

    @Override
    public float getMaxProgress()
    {
        return (float) this.recipeOperation;
    }

    public static ResourceLocation getStartSoundFile() {
        return Ic2Sounds.maceratorOp;
    }

    public static ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    @Override
    public double getWrenchDropRate() {
        return 0.8500000238418579D;
    }

    public boolean isValidInput(ItemStack par1) {
        return false;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes() {
        return new LinkedHashSet(Arrays.asList(IMachineUpgradeItem.UpgradeType.values()));
    }

    public boolean canWorkWithoutItems()
    {
        return false;
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public boolean needsInitialRedstoneUpdate()
    {
        return true;
    }

    @Override
    public String getName() {
        return "AlloySmelter";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return alloySmelter;
    }

    @Override
    public void update()
    {
        this.handleChargeSlot(500);
        this.updateNeighbors();

        boolean noRoom = this.addToInventory();
        IMachineRecipeList.RecipeEntry entry = this.getRecipe();
        boolean canWork = this.canWork() && !noRoom;
        boolean operate = canWork && entry != null;
        if (operate)
        {
            this.handleChargeSlot(this.maxEnergy);
        }

        if (operate && this.energy >= this.energyConsume)
        {
            if (!this.getActive())
            {
                this.getNetwork().initiateTileEntityEvent(this, 0, false);
            }

            this.setActive(true);
            this.progress += this.progressPerTick;
            this.useEnergy(this.recipeEnergy);
            if (this.progress >= (float) this.recipeOperation)
            {
                this.operate(entry);
                this.progress = 0.0F;
                this.notifyNeighbors();
            }

            this.getNetwork().updateTileGuiField(this, "progress");
        }
        else
        {
            if (this.getActive())
            {
                if (this.progress != 0.0F)
                {
                    this.getNetwork().initiateTileEntityEvent(this, 1, false);
                }
                else
                {
                    this.getNetwork().initiateTileEntityEvent(this, 2, false);
                }
            }

            if (entry == null && this.progress != 0.0F)
            {
                this.progress = 0.0F;
                this.getNetwork().updateTileGuiField(this, "progress");
            }

            this.setActive(false);
        }

        for (int i = 0; i < 4; ++i)
        {
            ItemStack item = this.inventory.get(i + this.inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem)
            {
                ((IMachineUpgradeItem) item.getItem()).onTick(item, this);
            }
        }

        this.updateComparators();
    }

    public void handleModifiers(IMachineRecipeList.RecipeEntry entry)
    {
        if (entry != null && entry.getOutput().getMetadata() != null)
        {
            NBTTagCompound nbt = entry.getOutput().getMetadata();
            double energyMod = nbt.hasKey("RecipeEnergyModifier") ? nbt.getDouble("RecipeEnergyModifier") : 1.0D;
            int newEnergy = applyModifier(this.energyConsume, nbt.getInteger("RecipeEnergy"), energyMod);
            if (newEnergy != this.recipeEnergy)
            {
                this.recipeEnergy = newEnergy;
                if (this.recipeEnergy < 1)
                {
                    this.recipeEnergy = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeEnergy");
            }

            double progMod = nbt.hasKey("RecipeTimeModifier") ? nbt.getDouble("RecipeTimeModifier") : 1.0D;
            int newProgress = applyModifier(this.operationLength, nbt.getInteger("RecipeTime"), progMod);
            if (newProgress != this.recipeOperation)
            {
                this.recipeOperation = newProgress;
                if (this.recipeOperation < 1)
                {
                    this.recipeOperation = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeOperation");
            }

        }
        else
        {
            if (this.recipeEnergy != this.energyConsume)
            {
                this.recipeEnergy = this.energyConsume;
                if (this.recipeEnergy < 1)
                {
                    this.recipeEnergy = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeEnergy");
            }

            if (this.recipeOperation != this.operationLength)
            {
                this.recipeOperation = this.operationLength;
                if (this.recipeOperation < 1)
                {
                    this.recipeOperation = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeOperation");
            }

        }
    }

    public void operate(IMachineRecipeList.RecipeEntry entry)
    {
        IRecipeInput input = entry.getInput();
        MachineOutput output = entry.getOutput().copy();

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemStack = this.inventory.get(i + this.inventory.size() - 4);
            if (itemStack.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem item = (IMachineUpgradeItem) itemStack.getItem();
                item.onProcessEndPre(itemStack, this, output);
            }
        }

        List<ItemStack> list = new FilteredList();
        this.operateOnce(input, output, list);

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemStack = this.inventory.get(i + this.inventory.size() - 4);
            if (itemStack.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem item = (IMachineUpgradeItem) itemStack.getItem();
                item.onProcessEndPost(itemStack, this, input, output, list);
            }
        }

        if (!list.isEmpty())
        {
            this.results.addAll(list);
            this.addToInventory();
        }

    }

    public void operateOnce(IRecipeInput input, MachineOutput output, List<ItemStack> list)
    {
        list.addAll(output.getRecipeOutput(this.getMachineWorld().rand, getTileData()));
        if (!(input instanceof INullableRecipeInput) || !this.inventory.get(slotInput1).isEmpty())
        {
            if (this.inventory.get(slotInput1).getItem().hasContainerItem(this.inventory.get(slotInput1)) && 
            		(this.inventory.get(slotInput2).getItem().hasContainerItem(this.inventory.get(slotInput2))))
            {
                this.inventory.set(slotInput1, this.inventory.get(slotInput1).getItem().getContainerItem(this.inventory.get(slotInput1)));
                this.inventory.set(slotInput2, this.inventory.get(slotInput2).getItem().getContainerItem(this.inventory.get(slotInput2)));
            }
            else
            {
                this.inventory.get(slotInput1).shrink(input.getAmount());
                this.inventory.get(slotInput2).shrink(input.getAmount());
            }

        }
    }

    public boolean addToInventory()
    {
        if (this.results.isEmpty())
        {
            return false;
        }
        else
        {
            for (int i = 0; i < this.results.size(); ++i)
            {
                ItemStack item = this.results.get(i);
                if (item.isEmpty())
                {
                    this.results.remove(i--);
                }
                else if (this.inventory.get(slotOutput).isEmpty())
                {
                    this.inventory.set(slotOutput, item.copy());
                    this.results.remove(i--);
                }
                else if (StackUtil.isStackEqual(this.inventory.get(slotOutput), item, false, false))
                {
                    int left = this.inventory.get(slotOutput).getMaxStackSize() - this.inventory.get(slotOutput).getCount();
                    if (left <= 0)
                    {
                        break;
                    }

                    if (left < item.getCount())
                    {
                        int itemLeft = item.getCount() - left;
                        item.setCount(itemLeft);
                        this.inventory.get(slotOutput).setCount(this.inventory.get(slotOutput).getMaxStackSize());
                        break;
                    }

                    this.inventory.get(slotOutput).grow(item.getCount());
                    this.results.remove(i--);
                }
                else if (this.inventory.get(slotOutput2).isEmpty())
                {
                    this.inventory.set(slotOutput2, item.copy());
                    this.results.remove(i--);
                }
                else if (StackUtil.isStackEqual(this.inventory.get(slotOutput2), item, false, false))
                {
                    int left = this.inventory.get(slotOutput2).getMaxStackSize() - this.inventory.get(slotOutput2).getCount();
                    if (left <= 0)
                    {
                        break;
                    }

                    if (left < item.getCount())
                    {
                        int itemLeft = item.getCount() - left;
                        item.setCount(itemLeft);
                        this.inventory.get(slotOutput2).setCount(this.inventory.get(slotOutput2).getMaxStackSize());
                        break;
                    }

                    this.inventory.get(slotOutput2).grow(item.getCount());
                    this.results.remove(i--);
                }
            }

            return !this.results.isEmpty();
        }
    }

    private IMachineRecipeList.RecipeEntry getRecipe()
    {
        if (this.inventory.get(slotInput1).isEmpty() &&  (this.inventory.get(slotInput2).isEmpty()) && !this.canWorkWithoutItems())
        {
            return null;
        }
        else
        {
            if (this.lastRecipe != null)
            {
                IRecipeInput recipe = this.lastRecipe.getInput();
                if (recipe instanceof INullableRecipeInput)
                {
                    if (!recipe.matches(this.inventory.get(slotInput1)) && (!recipe.matches(this.inventory.get(slotInput2))))
                    {
                        this.lastRecipe = null;
                    }
                }
                else if (!this.inventory.get(slotInput1).isEmpty() && (!this.inventory.get(slotInput2).isEmpty()) && recipe.matches(this.inventory.get(0)))
                {
                    if (recipe.getAmount() > this.inventory.get(slotInput1).getCount() && (recipe.getAmount() > this.inventory.get(slotInput2).getCount()))
                    {
                        return null;
                    }
                }
                else
                {
                    this.lastRecipe = null;
                }
            }

            if (this.lastRecipe == null)
            {
                IMachineRecipeList.RecipeEntry out = this.getOutputFor(this.inventory.get(slotInput1).copy());
                if (out == null)
                {
                    return null;
                }

                this.lastRecipe = out;
               
                //this.handleModifiers(out);
            }

            if (this.lastRecipe == null)
            {
                return null;
            }
            else if (this.inventory.get(slotOutput).getCount() >= this.inventory.get(slotOutput).getMaxStackSize())
            {
                return null;
            }
            else if (this.inventory.get(slotOutput2).getCount() >= this.inventory.get(slotOutput2).getMaxStackSize())
            {
                return null;
            }
            else if (this.inventory.get(slotOutput).isEmpty())
            {
                return this.lastRecipe;
            }
            else
            {
                Iterator var4 = this.lastRecipe.getOutput().getAllOutputs().iterator();

                ItemStack output;
                do
                {
                    if (!var4.hasNext())
                    {
                        return null;
                    }

                    output = (ItemStack) var4.next();
                }
                while (!StackUtil.isStackEqual(this.inventory.get(slotOutput), output, false, true));

                return this.lastRecipe;
            }
        }
    }

    public boolean canWork()
    {
        return !this.redstoneSensitive || this.isRedstonePowered();
    }

    @Override
    public boolean isRedstonePowered()
    {
        if (this.redstoneInverted)
        {
            return !super.isRedstonePowered();
        }
        else
        {
            return super.isRedstonePowered();
        }
    }

    @Override
    public void handleRedstone()
    {
        if (this.redstoneSensitive)
        {
            super.handleRedstone();
        }

    }

    public double getEnergy()
    {
        return (double) this.energy;
    }

    public boolean useEnergy(double amount, boolean simulate)
    {
        if ((double) this.energy < amount)
        {
            return false;
        }
        else
        {
            if (!simulate)
            {
                this.useEnergy((int) amount);
            }

            return true;
        }
    }

    public void setRedstoneSensitive(boolean active)
    {
        if (this.redstoneSensitive != active)
        {
            this.redstoneSensitive = active;
        }

    }

    public boolean isRedstoneSensitive()
    {
        return this.redstoneSensitive;
    }

    public boolean isProcessing()
    {
        return this.getActive();
    }

    public float getRecipeProgress()
    {
        float ret = this.progress / (float) this.recipeOperation;
        if (ret > 1.0F)
        {
            ret = 1.0F;
        }

        return ret;
    }

    public void setOverclockRates()
    {
        this.lastRecipe = null;
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
        this.redstoneSensitive = this.defaultSensitive;

        for (int i = 0; i < 4; ++i)
        {
            ItemStack item = this.inventory.get(i + this.inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem upgrade = (IMachineUpgradeItem) item.getItem();
                upgrade.onInstalling(item, this);
                extraProcessSpeed += upgrade.getExtraProcessSpeed(item, this) * item.getCount();
                processingSpeedMultiplier *= Math.pow(upgrade.getProcessSpeedMultiplier(item, this), (double) item.getCount());
                extraProcessTime += upgrade.getExtraProcessTime(item, this) * item.getCount();
                processTimeMultiplier *= Math.pow(upgrade.getProcessTimeMultiplier(item, this), (double) item.getCount());
                extraEnergyDemand += upgrade.getExtraEnergyDemand(item, this) * item.getCount();
                energyDemandMultiplier *= Math.pow(upgrade.getEnergyDemandMultiplier(item, this), (double) item.getCount());
                extraEnergyStorage += upgrade.getExtraEnergyStorage(item, this) * item.getCount();
                energyStorageMultiplier *= Math.pow(upgrade.getEnergyStorageMultiplier(item, this), (double) item.getCount());
                soundModfier = (float) ((double) soundModfier * Math.pow((double) upgrade.getSoundVolumeMultiplier(item, this), (double) item.getCount()));
                extraTier += upgrade.getExtraTier(item, this) * item.getCount();
                if (upgrade.useRedstoneInverter(item, this))
                {
                    redstonePowered = true;
                }
            }
        }

        this.redstoneInverted = redstonePowered;
        this.progressPerTick = applyFloatModifier(1, extraProcessSpeed, processingSpeedMultiplier);
        this.energyConsume = applyModifier(this.defaultEnergyConsume, extraEnergyDemand, energyDemandMultiplier);
        this.operationLength = applyModifier(this.defaultOperationLength, extraProcessTime, processTimeMultiplier);
        this.setMaxEnergy(applyModifier(this.defaultEnergyStorage, extraEnergyStorage, energyStorageMultiplier));
        this.tier = this.baseTier + extraTier;
        if (this.tier > 13)
        {
            this.tier = 13;
        }

        this.maxInput = (int) EnergyNet.instance.getPowerFromTier(this.tier);
        if (this.energy > this.maxEnergy)
        {
            this.energy = this.maxEnergy;
        }

        this.soundLevel = soundModfier;
        if (this.progressPerTick < 0.01F)
        {
            this.progressPerTick = 0.01F;
        }

        if (this.operationLength < 1)
        {
            this.operationLength = 1;
        }

        if (this.energyConsume < 1)
        {
            this.energyConsume = 1;
        }

        this.handleModifiers(this.lastRecipe);
        this.getNetwork().updateTileEntityField(this, "redstoneInverted");
        this.getNetwork().updateTileEntityField(this, "redstoneSensitive");
        this.getNetwork().updateTileEntityField(this, "soundLevel");
        this.getNetwork().updateTileGuiField(this, "maxInput");
        this.getNetwork().updateTileGuiField(this, "energy");
    }

    static int applyModifier(int base, int extra, double multiplier)
    {
        long ret = Math.round((double) (base + extra) * multiplier);
        return ret > 2147483647L ? 2147483647 : (int) ret;
    }

    static float applyFloatModifier(int base, int extra, double multiplier)
    {
        double ret = (double) Math.round((double) (base + extra) * multiplier);
        return ret > 2.147483648E9D ? 2.14748365E9F : (float) ret;
    }

    @Override
    public void onLoaded()
    {
        super.onLoaded();
        if (this.isSimulating())
        {
            this.setOverclockRates();
        }

    }

    @Override
    public void onUnloaded()
    {
        if (this.isRendering() && this.audioSource != null)
        {
            IC2.audioManager.removeSources(this);
            this.audioSource.remove();
            this.audioSource = null;
        }

        super.onUnloaded();
    }

    public void onNetworkEvent(int event)
    {
        if (this.audioSource != null && this.audioSource.isRemoved())
        {
            this.audioSource = null;
        }

        if (this.audioSource == null && this.getStartSoundFile() != null)
        {
            this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, this.getStartSoundFile(), true, false, IC2.audioManager.defaultVolume * this.soundLevel);
        }

        if (event == 0)
        {
            if (this.audioSource != null)
            {
                this.audioSource.play();
            }
        }
        else if (event == 1)
        {
            if (this.audioSource != null)
            {
                this.audioSource.stop();
                if (this.getInterruptSoundFile() != null)
                {
                    IC2.audioManager.playOnce(this, PositionSpec.Center, this.getInterruptSoundFile(), false, IC2.audioManager.defaultVolume * this.soundLevel);
                }
            }
        }
        else if (event == 2 && this.audioSource != null)
        {
            this.audioSource.stop();
        }

    }

    @Override
    public void onNetworkUpdate(String field)
    {
        if (field.equals("isActive") && this.getActive())
        {
            this.onNetworkEvent(0);
        }

        super.onNetworkUpdate(field);
        if (field.equals("soundLevel") && this.audioSource != null)
        {
            this.audioSource.setVolume(IC2.audioManager.defaultVolume * this.soundLevel);
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.progress = nbt.getFloat("progress");
        this.results.clear();
        NBTTagList list = nbt.getTagList("Results", 10);

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound data = list.getCompoundTagAt(i);
            this.results.add(new ItemStack(data));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("progress", this.progress);
        NBTTagList list = new NBTTagList();
        Iterator var3 = this.results.iterator();

        while (var3.hasNext())
        {
            ItemStack item = (ItemStack) var3.next();
            NBTTagCompound data = new NBTTagCompound();
            item.writeToNBT(data);
            list.appendTag(data);
        }

        nbt.setTag("Results", list);
        return nbt;
    }

    @Override
    public World getMachineWorld()
    {
        return this.getWorld();
    }

    @Override
    public BlockPos getMachinePos()
    {
        return this.getPos();
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return !this.isInvalid();
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new GTContainerAlloySmelter(player.inventory, this);
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {
    	//needed for construction
    }

    @Override
    public boolean hasGui(EntityPlayer player)
    {
        return true;
    }

    @Override
    public IHasInventory getOutputInventory()
    {
        return new RangedInventoryWrapper(this, slotOutput, slotOutput2);
    }

    @Override
    public IHasInventory getInputInventory()
    {
        return new RangedInventoryWrapper(this, slotInput1, slotInput2);
    }


    //not sure if we need this
//    public static void postInit() {
//        Set<String> oreBlacklist = new HashSet();
//        oreBlacklist.addAll(Arrays.asList("oreIron", "oreGold", "oreSilver", "oreCopper", "oreTin", "oreRedstone", "oreUranium"));
//        Set<String> ingotBlackList = new HashSet();
//        ingotBlackList.addAll(Arrays.asList("ingotIron", "ingotGold", "ingotSilver", "ingotCopper", "ingotTin", "ingotBronze"));
//        String[] var2 = OreDictionary.getOreNames();
//        int var3 = var2.length;
//
//        for(int var4 = 0; var4 < var3; ++var4) {
//            String id = var2[var4];
//            String dust;
//            NonNullList list;
//            if (id.startsWith("ore")) {
//                if (!oreBlacklist.contains(id)) {
//                    dust = "dust" + id.substring(3);
//                    if (OreDictionary.doesOreNameExist(dust)) {
//                        list = OreDictionary.getOres(dust, false);
//                        if (!list.isEmpty()) {
//                            addRecipe((String)id, 1, StackUtil.copyWithSize((ItemStack)list.get(0), 2));
//                        }
//                    }
//                }
//            } else if (id.startsWith("ingot") && !ingotBlackList.contains(id)) {
//                dust = "dust" + id.substring(3);
//                if (OreDictionary.doesOreNameExist(dust)) {
//                    list = OreDictionary.getOres(dust, false);
//                    if (!list.isEmpty()) {
//                        addRecipe((String)id, 1, ((ItemStack)list.get(0)).func_77946_l());
//                    }
//                }
//            }
//        }
//
//    }

    public static void init(){

        addRecipe((new RecipeInputOreDict("ingotCopper", 4)),  new ItemStack(GTItems.ingotBrass, 4, 0), 0.7f);
        //example recipe
//        addRecipe((new RecipeInputOreDict("ingotCopper", 3), new RecipeInputOreDict("ingotZinc", 1)),  new ItemStack(GTItems.ingotBrass, 4, 0), 0.7f);
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 1), 0.7f, "tinItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 2), 0.7f, "silverItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 3), 0.7f, "leadItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 4), 0.7f, "ironItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 5), 0.7f, "goldItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 8), 0.7f, "bronzeItemCasingRolling");
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input)), output);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input, stacksize)), output);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output) {
        addRecipe((new RecipeInputOreDict(input, stacksize)), output);
    }

    public static void addRecipe(ItemStack input, ItemStack output, float exp) {
        addRecipe((new RecipeInputItemStack(input)), output, exp);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output, float exp) {
        addRecipe((new RecipeInputItemStack(input, stacksize)), output, exp);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output, float exp) {
        addRecipe((new RecipeInputOreDict(input, stacksize)), output, exp);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output) {
        addRecipe(input, output, 0.0F);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output, float exp) {
        alloySmelter.addRecipe(input, output, exp, makeString(output));
    }

    private static String makeString(ItemStack stack) {
        return stack.getDisplayName();
    }
}
