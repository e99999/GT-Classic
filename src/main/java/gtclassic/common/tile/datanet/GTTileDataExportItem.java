package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.GTBlocks;
import net.minecraft.tileentity.TileEntity;

public class GTTileDataExportItem extends GTTileDataExportBase implements IGTDebuggableTile {

	public GTTileDataExportItem() {
		super(GTBlocks.tileDataItemImporter);
	}

	@Override
	public void handleNodes(TileEntity worldTile) {
		if (worldTile instanceof GTTileDataImportItem && ((GTTileDataImportItem) worldTile).outputNodes != null) {
			((GTTileDataImportItem) worldTile).outputNodes.add(this.getExportTilePos());
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Has Computer: " + this.hasComputer, false);
		data.put("Connected Item Input Nodes: " + this.blockCount, false);
	}
}
