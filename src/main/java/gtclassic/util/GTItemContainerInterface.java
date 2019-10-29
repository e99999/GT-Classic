package gtclassic.util;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface GTItemContainerInterface {

	List<ItemStack> getDrops();

	List<ItemStack> getInventoryDrops();
}
