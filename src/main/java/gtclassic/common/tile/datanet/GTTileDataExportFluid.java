package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.GTBlocks;
import net.minecraft.tileentity.TileEntity;

public class GTTileDataExportFluid extends GTTileDataExportBase implements IGTDebuggableTile {

	public GTTileDataExportFluid() {
		super(GTBlocks.tileDataFluidImporter);
	}

	@Override
	public void handleNodes(TileEntity worldTile) {
		if (worldTile instanceof GTTileDataImportFluid && ((GTTileDataImportFluid) worldTile).outputNodes != null) {
			((GTTileDataImportFluid) worldTile).outputNodes.add(this.getExportTilePos());
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Connected Fluid Input Nodes: " + this.blockCount, false);
	}
}
