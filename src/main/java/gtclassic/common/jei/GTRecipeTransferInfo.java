package gtclassic.common.jei;

import java.util.ArrayList;
import java.util.List;

import gtclassic.common.container.GTContainerWorktable;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.Slot;

public class GTRecipeTransferInfo implements IRecipeTransferInfo<GTContainerWorktable> {

	@Override
	public Class<GTContainerWorktable> getContainerClass() {
		return GTContainerWorktable.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return VanillaRecipeCategoryUid.CRAFTING;
	}

	@Override
	public boolean canHandle(GTContainerWorktable gtContainerWorktable) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(GTContainerWorktable gtContainerWorktable) {
		List<Slot> slots = new ArrayList<>();
		for (int i = 17; i < 26; i++) {
			slots.add(gtContainerWorktable.getSlot(i));
		}
		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(GTContainerWorktable gtContainerWorktable) {
		List<Slot> slots = new ArrayList<>();
		for (int i = 1; i < 17; i++) {
			slots.add(gtContainerWorktable.getSlot(i));
		}
		for (int i = 26; i < 68; i++) {
			slots.add(gtContainerWorktable.getSlot(i));
		}
		return slots;
	}
}
