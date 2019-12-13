package gtclassic.common.tile;

import gtclassic.api.tile.GTTileBatteryBase;

public class GTTileBattery {

	public static class GTTileBatteryLV extends GTTileBatteryBase {

		public GTTileBatteryLV() {
			this.tier = 1;
			this.maxEnergy = 80000;
			this.maxInput = 32;
			this.output = 32;
		}
	}
}
