package gtclassic.util;

import java.awt.Color;

import ic2.core.platform.textures.obj.IColorEffectedTexture;
import net.minecraft.item.ItemStack;

public interface GTItemColorInterface extends IColorEffectedTexture{
	public Color getColor(ItemStack stack, int index);

}
