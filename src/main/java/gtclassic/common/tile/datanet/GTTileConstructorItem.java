package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.GTBlocks;
import net.minecraft.tileentity.TileEntity;

public class GTTileConstructorItem extends GTTileConstructorBase implements IGTDebuggableTile {

	public GTTileConstructorItem() {
		super(0, GTBlocks.tileDigitizerItem); // MAKE NODE TYPE
	}

	@Override
	public void handleNodes(TileEntity worldTile) {
		if (worldTile instanceof GTTileDigitizerItem && ((GTTileDigitizerItem) worldTile).outputNodes != null) {
			((GTTileDigitizerItem) worldTile).outputNodes.add(this.getExportTilePos());
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Has Computer: " + this.hasComputer, false);
		data.put("Connected Item Input Nodes: " + this.blockCount, false);
	}
}
