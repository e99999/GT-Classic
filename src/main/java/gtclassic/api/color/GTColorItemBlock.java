package gtclassic.api.color;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.item.GTItemBlock;
import gtclassic.api.material.GTMaterialBlock;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import java.awt.Color;

public class GTColorItemBlock extends GTItemBlock implements IGTColorItem {

	Block block;

	public GTColorItemBlock(Block block) {
		super(block);
		this.block = block;
	}

	@Nonnull
	@Override
	public String getItemStackDisplayName(@Nonnull ItemStack stack) {
		String key = block.getUnlocalizedName() + ".name";
		String localized = I18n.format(key).trim();
		if (block instanceof GTMaterialBlock && localized.equals(key)){
			GTMaterialBlock block1 = (GTMaterialBlock)block;
			return I18n.format(block1.getUnlocalizedFlag(), I18n.format(block1.getUnlocalizedMaterial()).trim());
		}
		return localized;
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
