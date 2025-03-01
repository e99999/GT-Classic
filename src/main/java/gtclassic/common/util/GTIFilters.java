package gtclassic.common.util;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.tile.GTTileBaseFuelMachine;
import gtclassic.common.GTItems;
import gtclassic.common.tile.GTTileItemFilter;
import gtclassic.common.tile.GTTileQuantumChest;
import gtclassic.common.tile.GTTileQuantumTank;
import gtclassic.common.tile.GTTileTranslocator;
import gtclassic.common.tile.GTTileTypeFilter;
import ic2.api.item.ICustomDamageItem;
import ic2.api.item.IElectricItem;
import ic2.core.inventory.filters.IFilter;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;

public class GTIFilters {

	public static class DigitalChestFilter implements IFilter {

		@Override
		public boolean matches(ItemStack stack) {
			if (GTHelperStack.isEqual(stack, GTMaterialGen.get(GTItems.orbDataStorage))) {
				return false;
			}
			if (GTHelperStack.isEqual(stack, GTMaterialGen.getIc2(Ic2Items.personalSafe))) {
				return false;
			}
			if (stack.getTranslationKey().contains("tile.shulker")) {
				return false;
			}
			return true;
		}
	}

	public static class QuantumChestFilter implements IFilter {

		GTTileQuantumChest tile;

		public QuantumChestFilter(GTTileQuantumChest tile) {
			this.tile = tile;
		}

		@Override
		public boolean matches(ItemStack stack) {
			if (tile.display() == null || tile.display().isEmpty()) {
				return true;
			}
			return GTHelperStack.isEqual(tile.display(), stack);
		}
	}

	public static class QuantumTankFilter implements IFilter {

		GTTileQuantumTank tile;

		public QuantumTankFilter(GTTileQuantumTank tile) {
			this.tile = tile;
		}

		@Override
		public boolean matches(ItemStack stack) {
			if (FluidUtil.getFluidHandler(stack) == null) {
				return false;
			}
			if (this.tile.getTankInstance().getFluid() == null || this.tile.getTankInstance().getFluidAmount() == 0) {
				return true;
			}
			return this.tile.getTankInstance().getFluid().getFluid() == FluidUtil.getFluidContained(stack).getFluid();
		}
	}

	public static class FluidItemFilter implements IFilter {

		@Override
		public boolean matches(ItemStack stack) {
			return !stack.isEmpty() && FluidUtil.getFluidHandler(stack) != null;
		}
	}

	public static class TranslocatorFilter implements IFilter {

		GTTileTranslocator tile;

		public TranslocatorFilter(GTTileTranslocator tile) {
			this.tile = tile;
		}

		public boolean matches(ItemStack stack) {
			if (stack.isEmpty()) {
				return false; // InputStack is null so it does not match.
			}
			int noneEmptyStacks = 0; // Stacks that are not empty
			for (int i = 0; i < tile.inventory.size(); i++) {
				ItemStack inventoryStack = tile.inventory.get(i);
				if (inventoryStack.isEmpty()) {
					continue;// Skip because the inventory is empty we dont need to compare it.
				}
				noneEmptyStacks++;
				if (GTHelperStack.isEqual(stack, inventoryStack)) {
					return true; // Found
				}
			}
			return noneEmptyStacks == 0; // If all stacks are empty means no filter. If it is more then 1 stack in the
											// filter then return false because it didnt match the filter.
		}
	}

	public static class TypeFilter implements IFilter {

		GTTileTypeFilter tile;

		public TypeFilter(GTTileTypeFilter tile) {
			this.tile = tile;
		}

		public boolean matches(ItemStack stack) {
			if (stack.isEmpty() || this.tile.getCurrentFilter() == null) {
				return false;
			}
			if (this.tile.getCurrentFilter().equals("dust")) {
				return this.tile.invertFilter
						? !GTHelperStack.oreDictStartsWith(stack, this.tile.getCurrentFilter())
								|| GTHelperStack.oreDictStartsWith(stack, "dustTiny")
								|| GTHelperStack.oreDictStartsWith(stack, "dustSmall")
						: GTHelperStack.oreDictStartsWith(stack, this.tile.getCurrentFilter())
								&& !GTHelperStack.oreDictStartsWith(stack, "dustTiny")
								&& !GTHelperStack.oreDictStartsWith(stack, "dustSmall");
			}
			if (this.tile.getCurrentFilter().equals("ingot")) {
				return this.tile.invertFilter
						? !GTHelperStack.oreDictStartsWith(stack, this.tile.getCurrentFilter())
								|| GTHelperStack.oreDictStartsWith(stack, "ingotHot")
						: GTHelperStack.oreDictStartsWith(stack, this.tile.getCurrentFilter())
								&& !GTHelperStack.oreDictStartsWith(stack, "ingotHot");
			}
			if (this.tile.getCurrentFilter().equals("crushed")) {
				return this.tile.invertFilter
						? !GTHelperStack.oreDictStartsWith(stack, this.tile.getCurrentFilter())
								|| GTHelperStack.oreDictStartsWith(stack, "crushedPurified")
								|| GTHelperStack.oreDictStartsWith(stack, "crushedCentrifuged")
						: GTHelperStack.oreDictStartsWith(stack, this.tile.getCurrentFilter())
								&& !GTHelperStack.oreDictStartsWith(stack, "crushedPurified")
								&& !GTHelperStack.oreDictStartsWith(stack, "crushedCentrifuged");
			}
			return this.tile.invertFilter != GTHelperStack.oreDictStartsWith(stack, this.tile.getCurrentFilter());
		}
	}

	public static class ItemFilter implements IFilter {

		GTTileItemFilter tile;

		public ItemFilter(GTTileItemFilter tile) {
			this.tile = tile;
		}

		public boolean matches(ItemStack stack) {
			if (stack.isEmpty()) {
				return false; // InputStack is null so it does not match.
			}
			int noneEmptyStacks = 0; // Stacks that are not empty
			for (int i = 0; i < 9; i++) {
				ItemStack inventoryStack = tile.inventory.get(i);
				if (inventoryStack.isEmpty()) {
					continue;// Skip because the inventory is empty we dont need to compare it.
				}
				noneEmptyStacks++;
				if (this.tile.invertFilter) {
					if (!StackUtil.isStackEqual(stack, inventoryStack, false, this.tile.ignoreNbt)) {
						return true; // Found
					}
				} else {
					if (StackUtil.isStackEqual(stack, inventoryStack, false, this.tile.ignoreNbt)) {
						return true; // Found
					}
				}
			}
			return noneEmptyStacks == 0; // If all stacks are empty means no filter. If it is more then 1 stack in the
											// filter then return false because it didnt match the filter.
		}
	}

	public static class ToolFilter implements IFilter {

		@Override
		public boolean matches(ItemStack stack) {
			return !stack.isEmpty() && (stack.getItem().isDamageable() || stack.getItem() instanceof IElectricItem
					|| stack.getItem() instanceof ICustomDamageItem);
		}
	}

	public static class FuelMachineFilter implements IFilter {

		GTTileBaseFuelMachine machine;

		public FuelMachineFilter(GTTileBaseFuelMachine machine) {
			this.machine = machine;
		}

		public boolean matches(ItemStack stack) {
			return !stack.isEmpty() && this.machine.isValidInput(stack);
		}
	}

	public static class BetterBasicItemFilter implements IFilter {

		ItemStack item;

		public BetterBasicItemFilter(Item item) {
			this(new ItemStack(item));
		}

		public BetterBasicItemFilter(Block block) {
			this(new ItemStack(block));
		}

		public BetterBasicItemFilter(ItemStack par1) {
			this.item = par1;
		}

		public boolean matches(ItemStack stack) {
			return GTHelperStack.isEqual(this.item.copy(), stack);
		}
	}
}
