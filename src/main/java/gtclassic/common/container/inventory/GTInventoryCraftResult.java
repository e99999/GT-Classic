package gtclassic.common.container.inventory;

import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

public class GTInventoryCraftResult extends InventoryCraftResult {
    public boolean crafted = false;

    @Override
    public ItemStack decrStackSize(int index, int count) {
        crafted = true;
        return super.decrStackSize(index, count);
    }

    public void setCrafted(boolean crafted) {
        this.crafted = crafted;
    }
}
