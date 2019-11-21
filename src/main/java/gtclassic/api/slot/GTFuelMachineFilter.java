package gtclassic.api.slot;

import gtclassic.api.tile.GTTileFuelBaseMachine;
import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;

public class GTFuelMachineFilter implements IFilter {

	GTTileFuelBaseMachine machine;

	public GTFuelMachineFilter(GTTileFuelBaseMachine machine) {
		this.machine = machine;
	}

	public boolean matches(ItemStack stack) {
		return !stack.isEmpty() && this.machine.isValidInput(stack);
	}
}
