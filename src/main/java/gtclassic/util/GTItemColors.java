package gtclassic.util;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class GTItemColors implements IItemColor
{
    public int colorMultiplier(ItemStack stack, int index)
    {
        return ((GTItemColorInterface)stack.getItem()).getColor(stack, index).getRGB();
    }
}


