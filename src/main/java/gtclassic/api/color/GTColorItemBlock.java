package gtclassic.api.color;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.item.GTItemBlock;
import gtclassic.common.block.GTBlockSuperconductorCable;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.awt.Color;

public class GTColorItemBlock extends GTItemBlock implements IGTColorItem {

	Block block;

	public GTColorItemBlock(Block block) {
		super(block);
		this.block = block;
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (block instanceof GTBlockSuperconductorCable){
			if (nbt.hasKey("color")){
				return new Color(nbt.getInteger("color"));
			} else {
				return Color.WHITE;
			}
		}
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
