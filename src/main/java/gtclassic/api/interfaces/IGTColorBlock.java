package gtclassic.api.interfaces;

import java.awt.Color;

import ic2.core.platform.textures.obj.IColorEffectedTexture;
import net.minecraft.block.Block;

public interface IGTColorBlock extends IColorEffectedTexture {

	public Color getColor(Block block, int index);
}
