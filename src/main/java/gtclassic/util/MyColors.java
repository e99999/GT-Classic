package gtclassic.util;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class MyColors implements IItemColor
{
    public int colorMultiplier(ItemStack stack, int index)
    {
        return ((MyColorInterface)stack.getItem()).getColor(stack, index).getRGB();
    }
}


