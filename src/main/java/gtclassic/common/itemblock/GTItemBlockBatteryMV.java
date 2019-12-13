package gtclassic.common.itemblock;

import gtclassic.GTMod;
import gtclassic.api.itemblock.GTItemBlockBatteryBase;
import net.minecraft.block.Block;

public class GTItemBlockBatteryMV extends GTItemBlockBatteryBase {

	public GTItemBlockBatteryMV(Block block) {
		super(block);
		this.maxCharge = 1000000;
		this.transferLimit = 128;
		this.tier = 2;
		this.setCreativeTab(GTMod.creativeTabGT);
	}
}
