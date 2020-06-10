package gtclassic.common;

import gtclassic.api.helpers.GTValues;
import gtclassic.api.jei.GTJeiEntry;
import gtclassic.api.jei.GTJeiHandler;
import gtclassic.common.gui.GTGuiMachine;
import gtclassic.common.tile.GTTileCentrifuge;
import gtclassic.common.tile.multi.GTTileMultiFusionReactor;
import net.minecraftforge.fml.common.Loader;

public class GTJei {

	public static void initEntries() {
		GTJeiHandler.addEntry(new GTJeiEntry(GTTileCentrifuge.RECIPE_LIST, GTBlocks.tileCentrifuge, GTGuiMachine.GTIndustrialCentrifugeGui.class, 78, 24, 20, 18));
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)){
			GTJeiHandler.addEntry(new GTJeiEntry(GTTileMultiFusionReactor.RECIPE_LIST, GTBlocks.tileFusionReactor, GTGuiMachine.GTFusionComputerGui.class, 110, 34, 25, 17));
		}

	}
}
