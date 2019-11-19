package gtclassic.api.interfaces;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IGTItemContainerTile {

	List<ItemStack> getDrops();

	List<ItemStack> getInventoryDrops();
}
