package gtclassic.itemblock;

import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GTItemBlockRare extends ItemBlockRare {

	public GTItemBlockRare(Block block) {
		super(block);
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

	@Override
	public int getMetadata(ItemStack stack) {
		return 0;
	}
}
