package gtclassic.common.block;

import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileDisplayScreen;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.world.World;

public class GTBlockDisplayScreen extends GTBlockMachine {

	public GTBlockDisplayScreen() {
		super("displayscreen", GTLang.DISPLAY_SCREEN);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileDisplayScreen();
	}
}
