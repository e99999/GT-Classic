package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;

public class GTTileConstructorFluid extends GTTileOutputNodeBase implements IGTDebuggableTile {

	public GTTileConstructorFluid() {
		super(0);
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found", false);
		}
	}
}
