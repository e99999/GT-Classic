package gtclassic.common.item;

import gtclassic.api.interfaces.IGTMonkeyWrenchItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GTItemMonkeyWrench extends GTItemComponent implements IGTMonkeyWrenchItem {

	public GTItemMonkeyWrench() {
		super("monkey_wrench", 5, 2);
		this.setMaxDamage(127);
		this.setNoRepair();
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public boolean isMonkeyWrench() {
		return true;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public void onUse(EntityPlayer player) {
		player.getHeldItemMainhand().damageItem(1, player);
	}
}