package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.helpers.int3;
import gtclassic.common.container.GTContainerDisassembler;
import gtclassic.common.gui.GTGuiMachine.GTDisassemblerGui;
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
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GTTileDisassembler extends TileEntityElecMachine implements ITickable, IProgressMachine, IHasGui {

	protected static final int[] slotInputs = { 0 };
	protected static final int[] slotOutputs = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/disassembler.png");
	@NetworkField(index = 7)
	float progress = 0;

	public GTTileDisassembler() {
		super(11, 32);
		maxEnergy = 2000;
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
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GTDisassemblerGui.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer var1) {
		return new GTContainerDisassembler(var1.inventory, this);
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
		return 100;
	}

	@Override
	public float getProgress() {
		return this.progress;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 20 == 0) {
			if (this.isActive) {
				this.setActive(false);
			}
			this.progress = 0;
			this.getNetwork().updateTileGuiField(this, "progress");
			tryImportItems();
			tryExportItems();
			ItemStack slot0 = this.getStackInSlot(0);
			if (slot0.isEmpty()) {
				return;
			}
			if (slot0.isItemDamaged()) {
				return;
			}
			for (int x = 1; x < 10; ++x) {
				ItemStack stack = inventory.get(x);
				if (!stack.isEmpty()) {
					return;
				}
			}
			for (IRecipe recipe : ForgeRegistries.RECIPES) {
				ItemStack input = recipe.getRecipeOutput().copy();
				int amount = recipe.getRecipeOutput().getCount();
				if (GTHelperStack.isEqual(input, slot0) && slot0.getCount() >= amount) {
					this.setActive(true);
					this.progress = this.getMaxProgress();
					this.getNetwork().updateTileGuiField(this, "progress");
					List<ItemStack> outputList = new ArrayList<>();
					for (int i = 0; i < recipe.getIngredients().size(); ++i) {
						List<ItemStack> tempList = new ArrayList<>();
						Collections.addAll(tempList, recipe.getIngredients().get(i).getMatchingStacks());
						if (!tempList.isEmpty()) {
							outputList.add(tempList.get(0).copy());
						}
					}
					// i think the recipe ingredients being a nonnulllist breaks this
					// if (outputList.size() < 2) {
					// return;
					// }
					for (int j = 0; j < outputList.size(); ++j) {
						ItemStack outputStack = outputList.get(j).copy();
						if (canItemBeReturned(outputStack) && world.rand.nextFloat() > .20F) {
							this.setStackInSlot(j + 1, outputStack);
						}
					}
					slot0.shrink(amount);
					world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.5F, 1.0F);
					return;
				}
			}
		}
	}

	public static boolean canItemBeReturned(ItemStack stack) {
		return !GTHelperStack.isEqual(stack, Ic2Items.uuMatter.copy()) && !stack.isItemStackDamageable()
				&& !stack.getUnlocalizedName().contains("bucket");
	}

	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.back(1).asBlockPos());
	}

	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.forward(1).asBlockPos());
	}

	public void tryImportItems() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(getImportTile(), true);
		if (slave == null) {
			return;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
		int limit = 64;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = slave.removeItem(CommonFilters.Anything, getFacing(), 1, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = controller.addItem(stack, getFacing().getOpposite(), true);
			if (added.getCount() <= 0) {
				break;
			}
			slave.removeItem(CommonFilters.Anything, getFacing(), 1, true);
		}
	}

	public void tryExportItems() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(getExportTile(), true);
		if (slave == null) {
			return;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
		int limit = 64;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = controller.removeItem(CommonFilters.Anything, getFacing(), 1, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = slave.addItem(stack, getFacing().getOpposite(), true);
			if (added.getCount() <= 0) {
				break;
			}
			controller.removeItem(CommonFilters.Anything, getFacing(), 1, true);
		}
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}
}
