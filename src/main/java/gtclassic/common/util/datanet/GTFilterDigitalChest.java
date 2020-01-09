package gtclassic.common.util.datanet;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTItems;
import ic2.core.inventory.filters.IFilter;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTFilterDigitalChest implements IFilter {

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
