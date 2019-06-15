package gtclassic.tile;

import gtclassic.GTMod;
import gtclassic.container.GTContainerMatterFabricator;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.recipe.GTRecipeElementObject;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.machine.high.TileEntityMassFabricator;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
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
		super(9, 32768);
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
		handler.registerInputFilter(CommonFilters.MassFab, slotInputs);
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
		return GuiComponentContainer.class;
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
		// instead of recipes i will iterate my element list as valid scrap
		for (GTRecipeElementObject element : GTRecipeElementObject.fusionObjects) {
			String id = "Element" + element.getNumber() + "Amplifier";
			int amplifier = element.getNumber() * 1000;
			if (amplifier < 5000) {
				amplifier = 5000;
			}
			TileEntityMassFabricator.addAmplifier(element.getOutput(), amplifier, id);
			GTMod.debugLogger("Added " + element.getOutput().getDisplayName() + " as UU amplifier");
		}
	}

	@Override
	public void update() {
		this.setActive(activeCheck());
		// Below i try to iterate the input slots to check for valid amplifier
		for (RecipeEntry var : ClassicRecipes.massfabAmplifier.getRecipeMap()) {
			for (int i = 0; i < 8; ++i) {
				ItemStack stackEntry = var.getInput().getInputs().get(0);
				if (stackEntry != null && StackUtil.isStackEqual(this.inventory.get(i), stackEntry)) {
					int uuValue = var.getOutput().getMetadata().getInteger("amplification");
					if (this.energy - uuValue >= 0) {
						this.energy = this.energy - uuValue;
						this.inventory.get(i).shrink(1);
						this.progress = this.progress + uuValue;
					}
				}
			}
		}
		progressCheck();
		this.getNetwork().updateTileGuiField(this, "progress");
		this.getNetwork().updateTileGuiField(this, "energy");
	}

	public void progressCheck() {
		ItemStack output = this.inventory.get(8);
		if (progress >= getMaxProgress()) {
			if (output.isEmpty()) {
				this.inventory.set(8, GTMaterialGen.getIc2(Ic2Items.uuMatter, 1));
			} else if (!output.isEmpty() && output.getCount() < output.getMaxStackSize()) {
				int amount = output.getCount() + 1;
				inventory.set(8, GTMaterialGen.getIc2(Ic2Items.uuMatter, amount));
			}
			progress = 0;
		}
	}

	public boolean activeCheck() {
		return energy > 0;
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
