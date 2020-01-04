package gtclassic.api.slot;

import gtclassic.api.tile.GTTileBaseFuelMachine;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;

public class GTFuelMachineFilter implements IFilter {

	GTTileBaseFuelMachine machine;

	public GTFuelMachineFilter(GTTileBaseFuelMachine machine) {
		this.machine = machine;
	}

	public boolean matches(ItemStack stack) {
		return !stack.isEmpty() && this.machine.isValidInput(stack);
	}
}
