package gtclassic.color;

import java.awt.Color;

import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GTColorItemBlock extends ItemBlockRare implements GTColorItemInterface {

	Block block;

	public GTColorItemBlock(Block block) {
		super(block);
		this.block = block;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (this.block instanceof GTColorBlockInterface) {
			return ((GTColorBlockInterface) block).getColor(this.block, index);
		} else {
			return null;
		}
	}

}
