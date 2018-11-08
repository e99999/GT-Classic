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
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
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
import ic2.core.inventory.slots.SlotCustom;
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
import net.minecraft.entity.player.InventoryPlayer;
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

    public static final int slotInput = 0;
    public static final int slotInput2 = 1;
    public static final int slotFuel = 2;
    public static final int slotOutput = 3;
    public static final int slotOutput2 = 4;

    public GTTileEntityAlloySmelter() {
        this(5, 1, 400, 32);
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
        this.addNetworkFields(new String[]{"soundLevel", "redstoneInverted", "redstoneSensitive"});
        this.addGuiFields(new String[]{"recipeOperation", "recipeEnergy", "progress"});
        this.addInfos(new InfoComponent[]{new EnergyUsageInfo(this), new ProgressInfo(this)});
    }

    public MachineType getType() {
        return MachineType.macerator;
    }

    public ResourceLocation getGuiTexture() {
        return new ResourceLocation(GTClassic.MODID, "textures/guisprites/guialloysmelter.png");
    }

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
        handler.registerDefaultSlotAccess(AccessRule.Both, new int[]{slotFuel});
        handler.registerDefaultSlotAccess(AccessRule.Import, new int[]{slotInput, slotInput2});
        handler.registerDefaultSlotAccess(AccessRule.Export, new int[]{slotOutput, slotOutput2});
        handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), new int[]{0, 2, 4});
        handler.registerDefaultSlotsForSide(RotationList.DOWN.getOppositeList(), new int[]{1, 3});
        handler.registerInputFilter(new ArrayFilter(new IFilter[]{CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)}), new int[]{slotFuel});
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, new int[]{slotFuel});
        handler.registerSlotType(SlotType.Fuel, new int[]{slotFuel});
        handler.registerSlotType(SlotType.Input, new int[]{slotInput, slotInput2});
        handler.registerSlotType(SlotType.Output, new int[]{slotOutput, slotOutput2});
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

    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.maceratorOp;
    }

    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

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
            ItemStack item = (ItemStack) this.inventory.get(i + this.inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem)
            {
                ((IMachineUpgradeItem) item.getItem()).onTick(item, this);
            }
        }

        this.updateComparators();
    }

    public void operate(IMachineRecipeList.RecipeEntry entry)
    {
        IRecipeInput input = entry.getInput();
        MachineOutput output = entry.getOutput().copy();

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemStack = (ItemStack) this.inventory.get(i + this.inventory.size() - 4);
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
            ItemStack itemStack = (ItemStack) this.inventory.get(i + this.inventory.size() - 4);
            if (itemStack.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem item = (IMachineUpgradeItem) itemStack.getItem();
                item.onProcessEndPost(itemStack, this, input, output, list);
            }
        }

        if (list.size() > 0)
        {
            this.results.addAll(list);
            this.addToInventory();
        }

    }

    public void operateOnce(IRecipeInput input, MachineOutput output, List<ItemStack> list)
    {
        //list.addAll(output.getRecipeOutput(this.getMachineWorld().rand));
        if (!(input instanceof INullableRecipeInput) || !((ItemStack) this.inventory.get(slotInput)).isEmpty())
        {
            if (((ItemStack) this.inventory.get(slotInput)).getItem().hasContainerItem((ItemStack) this.inventory.get(slotInput)))
            {
                this.inventory.set(slotInput, ((ItemStack) this.inventory.get(slotInput)).getItem().getContainerItem((ItemStack) this.inventory.get(slotInput)));
            }
            else
            {
                ((ItemStack) this.inventory.get(slotInput)).shrink(input.getAmount());
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
                ItemStack item = (ItemStack) this.results.get(i);
                if (item.isEmpty())
                {
                    this.results.remove(i--);
                }
                else if (((ItemStack) this.inventory.get(slotOutput)).isEmpty())
                {
                    this.inventory.set(slotOutput, item.copy());
                    this.results.remove(i--);
                }
                else if (StackUtil.isStackEqual((ItemStack) this.inventory.get(slotOutput), item, false, false))
                {
                    int left = ((ItemStack) this.inventory.get(slotOutput)).getMaxStackSize() - ((ItemStack) this.inventory.get(slotOutput)).getCount();
                    if (left <= 0)
                    {
                        break;
                    }

                    if (left < item.getCount())
                    {
                        int itemLeft = item.getCount() - left;
                        item.setCount(itemLeft);
                        ((ItemStack) this.inventory.get(slotOutput)).setCount(((ItemStack) this.inventory.get(slotOutput)).getMaxStackSize());
                        break;
                    }

                    ((ItemStack) this.inventory.get(slotOutput)).grow(item.getCount());
                    this.results.remove(i--);
                }
                else if (((ItemStack) this.inventory.get(slotOutput2)).isEmpty())
                {
                    this.inventory.set(slotOutput2, item.copy());
                    this.results.remove(i--);
                }
                else if (StackUtil.isStackEqual((ItemStack) this.inventory.get(slotOutput2), item, false, false))
                {
                    int left = ((ItemStack) this.inventory.get(slotOutput2)).getMaxStackSize() - ((ItemStack) this.inventory.get(slotOutput2)).getCount();
                    if (left <= 0)
                    {
                        break;
                    }

                    if (left < item.getCount())
                    {
                        int itemLeft = item.getCount() - left;
                        item.setCount(itemLeft);
                        ((ItemStack) this.inventory.get(slotOutput2)).setCount(((ItemStack) this.inventory.get(slotOutput2)).getMaxStackSize());
                        break;
                    }

                    ((ItemStack) this.inventory.get(slotOutput2)).grow(item.getCount());
                    this.results.remove(i--);
                }
            }

            return this.results.size() > 0;
        }
    }

    private IMachineRecipeList.RecipeEntry getRecipe()
    {
        if (((ItemStack) this.inventory.get(slotInput)).isEmpty() && !this.canWorkWithoutItems())
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
                    if (!recipe.matches((ItemStack) this.inventory.get(slotInput)))
                    {
                        this.lastRecipe = null;
                    }
                }
                else if (!((ItemStack) this.inventory.get(slotInput)).isEmpty() && recipe.matches((ItemStack) this.inventory.get(0)))
                {
                    if (recipe.getAmount() > ((ItemStack) this.inventory.get(slotInput)).getCount())
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
                IMachineRecipeList.RecipeEntry out = this.getOutputFor(((ItemStack) this.inventory.get(slotInput)).copy());
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
            else if (((ItemStack) this.inventory.get(slotOutput)).getCount() >= ((ItemStack) this.inventory.get(slotOutput)).getMaxStackSize())
            {
                return null;
            }
            else if (((ItemStack) this.inventory.get(slotOutput2)).getCount() >= ((ItemStack) this.inventory.get(slotOutput2)).getMaxStackSize())
            {
                return null;
            }
            else if (((ItemStack) this.inventory.get(slotOutput)).isEmpty())
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
                while (!StackUtil.isStackEqual((ItemStack) this.inventory.get(slotOutput), output, false, true));

                return this.lastRecipe;
            }
        }
    }

    public boolean canWork()
    {
        return !this.redstoneSensitive ? true : this.isRedstonePowered();
    }

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

    public void onLoaded()
    {
        super.onLoaded();
//        if (this.isSimulating())
//        {
//            this.setOverclockRates();
//        }

    }

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

    }

    @Override
    public boolean hasGui(EntityPlayer player)
    {
        return true;
    }

    @Override
    public IHasInventory getOutputInventory()
    {
        return new RangedInventoryWrapper(this, new int[]{slotOutput, slotOutput2});
    }

    @Override
    public IHasInventory getInputInventory()
    {
        return new RangedInventoryWrapper(this, new int[]{slotInput, slotInput2});
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

        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotCopper", 4)),  new ItemStack(GTItems.ingotBrassGT, 4, 0), 0.7f, "brassIngots");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 1), 0.7f, "tinItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 2), 0.7f, "silverItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 3), 0.7f, "leadItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 4), 0.7f, "ironItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 5), 0.7f, "goldItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 8), 0.7f, "bronzeItemCasingRolling");
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output);
    }

    public static void addRecipe(ItemStack input, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output, exp);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output, exp);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output, exp);
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
