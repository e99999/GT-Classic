package gtclassic.item;

import net.minecraft.item.ItemStack;

public class GTItemMortar extends GTItemComponent {

	public GTItemMortar(String name, int x, int y, int durability) {
		super(name, x, y);
		this.setMaxDamage(durability);
		this.setNoRepair();
		this.maxStackSize = 1;
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}
}
