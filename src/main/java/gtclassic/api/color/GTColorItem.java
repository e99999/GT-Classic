package gtclassic.api.color;

import gtclassic.api.interfaces.IGTColorItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class GTColorItem implements IItemColor {

	public int colorMultiplier(ItemStack stack, int index) {
		return ((IGTColorItem) stack.getItem()).getColor(stack, index).getRGB();
	}
}
