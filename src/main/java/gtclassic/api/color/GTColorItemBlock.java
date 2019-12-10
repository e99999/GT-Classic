package gtclassic.api.color;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.itemblock.GTItemBlockRare;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialBlock;
import gtclassic.api.material.GTMaterialFlag;
import ic2.core.platform.registry.Ic2Lang;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;
import java.awt.*;

public class GTColorItemBlock extends GTItemBlockRare implements IGTColorItem {

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
			if (flag.getComp() != Ic2Lang.nullKey){
				return flag.getComp().getLocalizedFormatted(I18n.translateToLocal("material." + material.getDisplayName() + ".name").trim());
			}
		}
		return super.getItemStackDisplayName(stack);
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (this.block instanceof IGTColorBlock) {
			return ((IGTColorBlock) block).getColor(this.block, index);
		} else {
			return null;
		}
	}
}
