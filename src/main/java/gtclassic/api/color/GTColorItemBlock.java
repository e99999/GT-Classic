package gtclassic.api.color;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialBlock;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.item.GTItemBlock;
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
		if (block instanceof GTMaterialBlock){
			GTMaterialBlock block1 = (GTMaterialBlock)block;
			GTMaterialFlag flag = block1.getFlag();
			GTMaterial material = block1.getMaterial();
			return I18n.format("part." + flag.getPrefix(), I18n.format("material." + material.getDisplayName()).trim());
		}
		return super.getItemStackDisplayName(stack);
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
