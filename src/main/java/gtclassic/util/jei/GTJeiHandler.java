package gtclassic.util.jei;

import java.util.ArrayList;

import gtclassic.GTBlocks;
import gtclassic.gui.GTGuiMachine;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.multi.GTTileMultiFusionReactor;

public class GTJeiHandler {
	
	public static ArrayList<GTJeiEntry> registry = new ArrayList<>();
	
	public static void collectJeiEntries() {
		registry.add(new GTJeiEntry(GTTileCentrifuge.RECIPE_LIST, GTBlocks.tileCentrifuge, GTGuiMachine.GTIndustrialCentrifugeGui.class, 78, 24, 20, 18));
		registry.add(new GTJeiEntry(GTTileMultiFusionReactor.RECIPE_LIST, GTBlocks.tileFusionReactor, GTGuiMachine.GTFusionComputerGui.class, 110, 34, 25, 17));
	}
}
