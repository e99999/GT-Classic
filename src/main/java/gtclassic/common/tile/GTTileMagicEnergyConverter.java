package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerMagicEnergyConverter;
import gtclassic.common.gui.GTGuiMachine.GTMagicEnergyConverterGui;
import ic2.api.classic.energy.tile.IEnergySourceInfo;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileMagicEnergyConverter extends TileEntityMachine
		implements ITankListener, ITickable, IHasGui, IEUStorage, IEmitterTile, IEnergySourceInfo, IGTDebuggableTile {

	@NetworkField(index = 4)
	public int storage = 0;
	public int maxStorage = 1000000;
	public int production = 24;
	private IC2Tank tank;
	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;
	int fuel = 0;
	boolean enet = false;
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.magicfuels");

	public GTTileMagicEnergyConverter() {
		super(3);
		this.tank = new IC2Tank(1000);
		this.tank.addListener(this);
		this.addGuiFields("tank", "fuel");
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.UP.invert());
		handler.registerDefaultSlotAccess(AccessRule.Import, 0);
		handler.registerDefaultSlotAccess(AccessRule.Export, 1);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), 0);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), 1);
		handler.registerSlotType(SlotType.Input, 0);
		handler.registerSlotType(SlotType.Output, 1);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.MAGIC_ENERGY_CONVERTER;
	}

	@Override
	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, "tank");
		this.inventory.set(2, ItemDisplayIcon.createWithFluidStack(this.tank.getFluid()));
		if (this.tank.getFluidAmount() == 0) {
			this.tank.setFluid(null);
			this.setStackInSlot(slotDisplay, ItemStack.EMPTY);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag("tank"));
		this.storage = nbt.getInteger("storage");
		this.fuel = nbt.getInteger("fuel");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.tank.writeToNBT(this.getTag(nbt, "tank"));
		nbt.setInteger("storage", this.storage);
		nbt.setInteger("fuel", this.fuel);
		return nbt;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.enet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.isSimulating() && this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.enet = false;
		}
		super.onUnloaded();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? true
				: super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank)
				: super.getCapability(capability, facing);
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<>();
		list.add(this.getStackInSlot(slotInput));
		list.add(this.getStackInSlot(slotOutput));
		return list;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public IC2Tank getTankInstance() {
		return this.tank;
	}

	@Override
	public void update() {
		GTHelperFluid.doFluidContainerThings(this, this.tank, slotInput, slotOutput);
		processFuelValue();
		this.setActive(this.fuel > 0 && this.storage < this.maxStorage);
		if (this.fuel == 0) {
			processMagicFuel();
		}
	}

	private void processFuelValue() {
		if (this.fuel - 1 >= 0 && this.storage + this.production <= this.maxStorage) {
			this.fuel = this.fuel - 1;
			this.storage = this.storage + this.production;
			this.getNetwork().updateTileGuiField(this, "fuel");
		}
	}

	/** Logic for processing of magic fuels **/
	private void processMagicFuel() {
		if (this.tank.getFluid() != null && this.tank.getFluidAmount() >= 1000) {
			int newFuelFromFluid = getMagicFuelValue(this.tank.getFluid().getFluid());
			if (newFuelFromFluid > 0 && newFuelFromFluid + this.fuel < Integer.MAX_VALUE) {
				this.fuel = this.fuel + newFuelFromFluid;
				this.tank.setFluid(new FluidStack(this.getContained(), this.tank.getFluid().amount - 1000));
				this.onTankChanged(this.tank);
				return;
			}
		}
		if (!this.getStackInSlot(slotInput).isEmpty()) {
			int newFuelFromStack = getMagicFuelValue(this.getStackInSlot(slotInput));
			if (newFuelFromStack > 0 && newFuelFromStack + this.fuel < Integer.MAX_VALUE) {
				this.fuel = this.fuel + newFuelFromStack;
				this.getStackInSlot(slotInput).shrink(1);
			}
		}
	}

	/** Checks for compatible fluids **/
	public int getMagicFuelValue(Fluid fluid) {
		if (RECIPE_LIST.getRecipeList().isEmpty()) {
			return 0;
		}
		for (MultiRecipe map : RECIPE_LIST.getRecipeList()) {
			IRecipeInput input = map.getInput(0);
			if (input instanceof RecipeInputFluid) {
				Fluid fluidinput = ((RecipeInputFluid) input).fluid.getFluid();
				if (fluidinput == fluid) {
					return map.getOutputs().getMetadata().getInteger("RecipeTime");
				}
			}
		}
		return 0;
	}

	/** Checks for compatible ItemStacks **/
	public int getMagicFuelValue(ItemStack stack) {
		if (RECIPE_LIST.getRecipeList().isEmpty()) {
			return 0;
		}
		for (MultiRecipe map : RECIPE_LIST.getRecipeList()) {
			IRecipeInput input = map.getInput(0);
			if (!(input instanceof RecipeInputFluid) && input.matches(stack)) {
				return map.getOutputs().getMetadata().getInteger("RecipeTime");
			}
		}
		return 0;
	}

	public FluidStack getContained() {
		return this.tank.getFluid();
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerMagicEnergyConverter(player.inventory, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTMagicEnergyConverterGui.class;
	}

	@Override
	public void onGuiClosed(EntityPlayer paramEntityPlayer) {
	}

	@Override
	public boolean canInteractWith(EntityPlayer paramEntityPlayer) {
		return !this.isInvalid();
	}

	@Override
	public boolean hasGui(EntityPlayer paramEntityPlayer) {
		return true;
	}

	@Override
	public int getStoredEU() {
		return this.storage;
	}

	@Override
	public int getMaxEU() {
		return this.maxStorage;
	}

	@Override
	public int getOutput() {
		return this.production;
	}

	@Override
	public void drawEnergy(double amount) {
		this.storage = (int) ((double) this.storage - amount);
	}

	@Override
	public double getOfferedEnergy() {
		return (double) Math.min(this.storage, this.production);
	}

	@Override
	public int getSourceTier() {
		return 1;
	}

	public int getMaxSendingEnergy() {
		return this.production + 1;
	}

	public int getFuel() {
		return this.fuel;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing facing) {
		return true;
	}

	public static void init() {
		addRecipe(GTMaterialGen.getFluid(GTMaterial.Mercury));
		addRecipe(GTMaterialGen.getFluid(GTMaterial.Beryllium));
		addRecipe(GTMaterialGen.getFluid(GTMaterial.Neon));
		addRecipe(GTMaterialGen.getFluid(GTMaterial.Argon));
		addRecipe(GTMaterialGen.getFluid(GTMaterial.MagicDye));
		addModRecipe("xpjuice"); // OpenBlocks
		addModRecipe("redstone"); // Thermal Foundation
		addModRecipe("glowstone"); // Thermal Foundation
		addModRecipe("pyrotheum"); // Thermal Foundation
		addModRecipe("cryotheum"); // Thermal Foundation
		addModRecipe("aerotheum"); // Thermal Foundation
		addModRecipe("mana"); // Thermal Foundation
		addModRecipe("experience"); // Thermal Foundation
		addModRecipe("potion"); // Thermal Foundation
		addModRecipe("potion_splash"); // Thermal Foundation
		addModRecipe("potion_lingering"); // Thermal Foundation
		addModRecipe("ender"); // Thermal Foundation
		addModRecipe("astralsorcery.liquidstarlight"); // Astral Sorcery
		addModRecipe("flux_goo"); // Thaumcraft
		addModRecipe("liquid_death");// Thaumcraft
		addModRecipe("purifying_fluid");// Thaumcraft
		addModRecipe("lifeessence");// Blood Magic
		addModRecipe("menrilresin"); // Integrated Dynamics
		addModRecipe("liquidchorus"); // Integrated Dynamics
		addModRecipe("liquidcoralium"); // AbsyssalCraft
		addModRecipe("liquidantimatter"); // AbsyssalCraft
		addModRecipe("mana_fluid"); // Wizardry
		addModRecipe("nacre_fluid"); // Wizardry
		addRecipe(GTMaterialGen.get(Items.NETHER_WART), 200);
		addRecipe(GTMaterialGen.getIc2(Ic2Items.terraWart), 300);
		addRecipe(GTMaterialGen.get(Items.ENDER_PEARL), 8000);
		addRecipe("dustEnderPearl", 8000);
		addRecipe(GTMaterialGen.get(Items.ENDER_EYE), 10000);
		addRecipe("dustEnderEye", 10000);
		addRecipe(GTMaterialGen.get(Items.GHAST_TEAR), 10000);
		addRecipe(GTMaterialGen.get(Items.END_CRYSTAL), 20000);
		addRecipe(GTMaterialGen.get(Items.DRAGON_BREATH), 32000);
		addRecipe(GTMaterialGen.get(Items.NETHER_STAR), 124000);
		addRecipe(GTMaterialGen.get(Blocks.BEACON), 124000);
	}

	public static IRecipeModifier[] value(int amount) {
		int varAmount = amount > 24 ? amount : 24;
		return new IRecipeModifier[] { ModifierType.RECIPE_LENGTH.create(varAmount / 24) };
	}

	public static void addModRecipe(String name) {
		Fluid input = FluidRegistry.getFluid(name);
		if (input != null) {
			addRecipe(input);
		}
	}

	public static void addRecipe(Fluid fluid) {
		addRecipe(new RecipeInputFluid(new FluidStack(fluid, 1000)), value(8000));
	}

	public static void addRecipe(ItemStack stack, int energy) {
		addRecipe(new RecipeInputItemStack(stack), value(energy));
	}

	public static void addRecipe(String stack, int energy) {
		addRecipe(new RecipeInputOreDict(stack, 1), value(energy));
	}

	public static void addRecipe(IRecipeInput input, int energy) {
		addRecipe(input, value(energy));
	}

	private static void addRecipe(IRecipeInput input, IRecipeModifier[] modifiers) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		NBTTagCompound mods = new NBTTagCompound();
		for (IRecipeModifier modifier : modifiers) {
			modifier.apply(mods);
		}
		inlist.add(input);
		outlist.add(GTMaterialGen.get(Items.REDSTONE));
		addRecipe(inlist, new MachineOutput(mods, outlist));
	}

	private static void addRecipe(List<IRecipeInput> input, MachineOutput output) {
		if (!input.isEmpty()) {
			RECIPE_LIST.addRecipe(input, output, input.get(0).getInputs().get(0).getUnlocalizedName(), 24);
		}
	}

	public static void removeRecipe(String id) {
		RECIPE_LIST.removeRecipe(id);
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Stored Fuel Value: " + this.fuel + "/" + this.fuel * 24 + " EU", true);
	}
}