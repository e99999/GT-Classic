package gtclassic.common.tile;

import gtclassic.api.tile.GTTileBatteryBase;

public class GTTileBattery {

	public static class GTTileBatteryMV extends GTTileBatteryBase {

		public GTTileBatteryMV() {
			this.tier = 2;
			this.maxEnergy = 1000000;
			this.maxInput = 128;
			this.output = 128;
		}
	}
}
