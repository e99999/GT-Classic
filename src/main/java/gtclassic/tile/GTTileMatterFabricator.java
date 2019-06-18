package gtclassic.tile;

import gtclassic.GTMod;
import gtclassic.container.GTContainerMatterFabricator;
import gtclassic.gui.GTGuiMachine.GTMatterFabricatorGui;
import gtclassic.material.GTMaterialGen;
import gtclassic.recipe.GTRecipeUUAmplifier;
import gtclassic.util.recipe.GTRecipeMultiInputList.MultiRecipe;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
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
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/matterfabricator.png");

	public GTTileMatterFabricator() {
		super(9, 131072);
		maxEnergy = 10000000;
		addGuiFields("progress");
		addInfos(new ProgressInfo(this));
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.VERTICAL, slotInputs);
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
		return 7000000;
	}

	@Override
	public float getProgress() {
		return progress;
	}

	public static void init() {
	}

	@Override
	public void update() {
		this.setActive(hasPower());
		// Below i try to iterate the input slots to check for valid amplifier
		ItemStack output = this.inventory.get(8);
		if (!redstoneEnabled() && hasPower() && output.getCount() < output.getMaxStackSize()) {
			for (MultiRecipe map : GTRecipeUUAmplifier.RECIPE_LIST.getRecipeList()) {
				// For loop for the input slots
				for (int i = 0; i < 8; ++i) {
					// Comparing the input slot with UU matter recipe list entries
					// TODO might have to iterate this to get all inputs for ore dicts and stuff
					ItemStack entryStack = map.getInput(0).getInputs().get(0).copy();
					ItemStack machineStack = this.inventory.get(i).copy();
					if (entryStack != null && StackUtil.isStackEqual(machineStack, entryStack, false, false)) {
						int uuValue = map.getOutputs().getMetadata().getInteger("RecipeTime") + 100;
						// Checking if the machine has power to perform the operation
						if (this.energy - uuValue >= 0) {
							this.energy = this.energy - uuValue;
							this.inventory.get(i).shrink(1);
							this.progress = this.progress + uuValue;
							updateGui();
							checkProgress();
						}
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
			} else if (!output.isEmpty()) {
				int amount = output.getCount() + 1;
				inventory.set(8, GTMaterialGen.getIc2(Ic2Items.uuMatter, amount));
			}
			progress = 0;
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
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing != EnumFacing.UP && facing != EnumFacing.DOWN;
	}
}
