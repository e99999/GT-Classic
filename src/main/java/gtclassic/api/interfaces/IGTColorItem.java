package gtclassic.api.interfaces;

import java.awt.Color;

import ic2.core.platform.textures.obj.IColorEffectedTexture;
import net.minecraft.item.ItemStack;

public interface IGTColorItem extends IColorEffectedTexture {

	public Color getColor(ItemStack stack, int index);
}
