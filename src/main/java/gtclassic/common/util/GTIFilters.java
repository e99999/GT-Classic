package gtclassic.common.util;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTItems;
import gtclassic.common.tile.GTTileQuantumChest;
import gtclassic.common.tile.GTTileQuantumTank;
import gtclassic.common.tile.GTTileTranslocator;
import gtclassic.common.tile.datanet.GTTileDigitizerItem;
import ic2.core.inventory.filters.IFilter;
import ic2.core.platform.registry.Ic2Items;
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
			if (stack.getUnlocalizedName().contains("tile.shulker")) {
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

	public static class ItemDigitizerFilter implements IFilter {

		GTTileDigitizerItem tile;

		public ItemDigitizerFilter(GTTileDigitizerItem tile) {
			this.tile = tile;
		}

		public boolean matches(ItemStack stack) {
			if (stack.isEmpty()) {
				return true;
			}
			if (!this.tile.blacklist.isEmpty()) {
				for (ItemStack bStack : this.tile.blacklist) {
					if (GTHelperStack.isEqual(bStack, stack.copy())) {
						return true;
					}
				}
			}
			return false;
		}
	}
}