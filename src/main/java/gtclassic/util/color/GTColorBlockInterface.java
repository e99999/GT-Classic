package gtclassic.util.color;

import java.awt.Color;

import ic2.core.platform.textures.obj.IColorEffectedTexture;
import net.minecraft.block.Block;

public interface GTColorBlockInterface extends IColorEffectedTexture {
	public Color getColor(Block block, int index);

}
