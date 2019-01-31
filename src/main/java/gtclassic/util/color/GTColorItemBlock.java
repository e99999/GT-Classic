package gtclassic.util.color;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GTColorItemBlock extends ItemBlock implements GTColorItemInterface {

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
