package gtclassic.color;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class GTColorItem implements IItemColor {

	public int colorMultiplier(ItemStack stack, int index) {
		return ((GTColorItemInterface) stack.getItem()).getColor(stack, index).getRGB();
	}
}
