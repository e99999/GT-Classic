package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.GTBlocks;
import net.minecraft.tileentity.TileEntity;

public class GTTileConstructorFluid extends GTTileConstructorBase implements IGTDebuggableTile {

	public GTTileConstructorFluid() {
		super(0, GTBlocks.tileDigitizerFluid); // MAKE NODE TYPE
	}

	@Override
	public void handleNodes(TileEntity worldTile) {
		if (worldTile instanceof GTTileDigitizerFluid && ((GTTileDigitizerFluid) worldTile).outputNodes != null) {
			((GTTileDigitizerFluid) worldTile).outputNodes.add(this.getExportTilePos());
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Has Computer: " + this.hasComputer, false);
		data.put("Connected Fluid Input Nodes: " + this.blockCount, false);
	}
}
