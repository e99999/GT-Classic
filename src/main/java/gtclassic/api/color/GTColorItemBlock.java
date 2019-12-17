package gtclassic.api.color;

import java.awt.Color;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.itemblock.GTItemBlockRare;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GTColorItemBlock extends GTItemBlockRare implements IGTColorItem {

	Block block;

	public GTColorItemBlock(Block block) {
		super(block);
		this.block = block;
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("color")) {
			return new Color(nbt.getInteger("color"));
		}
		if (this.block instanceof IGTColorBlock) {
			return ((IGTColorBlock) block).getColor(null, null, null, this.block, index);
		} else {
			return null;
		}
	}
}
