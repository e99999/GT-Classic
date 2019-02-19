package gtclassic.block;

import gtclassic.itemblock.GTItemBlockBattery;
import gtclassic.itemblock.GTItemBlockInterface;
import ic2.core.item.block.ItemBlockRare;

public class GTBlockBattery extends GTBlockTileCustom implements GTItemBlockInterface {
	public int max;
	public int trans;
	public int tier;

	public GTBlockBattery(String name, int width, int height, boolean light, int max, int trans, int tier) {
		super(name, width, height, light);
		this.max = max;
		this.trans = trans;
		this.tier = tier;
	}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockBattery.class;
	}
}
