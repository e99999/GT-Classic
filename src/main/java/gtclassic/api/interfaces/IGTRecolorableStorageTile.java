package gtclassic.api.interfaces;

import net.minecraft.util.EnumFacing;

import java.awt.Color;

public interface IGTRecolorableStorageTile {

	void setTileColor(int color);

	default void setTileColor(int color, EnumFacing facing){
		setTileColor(color);
	}

	Color getTileColor();

	boolean isColored();
}
