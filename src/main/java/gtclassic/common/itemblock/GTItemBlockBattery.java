package gtclassic.common.itemblock;

import gtclassic.GTMod;
import gtclassic.api.itemblock.GTItemBlockBatteryBase;
import net.minecraft.block.Block;

public class GTItemBlockBattery {

	public static class GTItemBlockBatteryLV extends GTItemBlockBatteryBase {

		public GTItemBlockBatteryLV(Block block) {
			super(block);
			this.maxCharge = 80000;
			this.transferLimit = 32;
			this.tier = 1;
			this.setCreativeTab(GTMod.creativeTabGT);
		}
	}
}
