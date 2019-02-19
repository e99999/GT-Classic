package gtclassic.block;

import gtclassic.itemblock.GTItemBlockDuctTape;
import gtclassic.itemblock.GTItemBlockInterface;
import ic2.core.item.block.ItemBlockRare;

public class GTBlockDuctTape extends GTBlockTileCustom implements GTItemBlockInterface {

	public GTBlockDuctTape(String name, int width, int height, boolean light) {
		super(name, width, height, light);
	}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockDuctTape.class;
	}

}
