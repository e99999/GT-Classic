package gtclassic.api.color;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.itemblock.GTItemBlockRare;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialBlock;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.pipe.GTBlockPipeFluid;
import gtclassic.api.pipe.GTBlockPipeItem;
import gtclassic.api.pipe.GTHelperPipes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.awt.Color;

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
			return net.minecraft.util.text.translation.I18n.translateToLocalFormatted("part." + flag.getPrefix(), net.minecraft.util.text.translation.I18n.translateToLocal("material." + material.getDisplayName()).trim());
		}
		if (block instanceof GTBlockPipeFluid){
			GTBlockPipeFluid block1 = (GTBlockPipeFluid)block;
			GTHelperPipes.GTPipeModel type = block1.getPipeType();
			GTMaterial mat = block1.getMaterial();
			return net.minecraft.util.text.translation.I18n.translateToLocalFormatted("part.fluid_pipe" + type.getSuffix(), net.minecraft.util.text.translation.I18n.translateToLocal("material." + mat.getDisplayName()).trim());
		}
		if (block instanceof GTBlockPipeItem){
			GTBlockPipeItem block1 = (GTBlockPipeItem)block;
			GTHelperPipes.GTPipeModel type = block1.getPipeType();
			GTMaterial mat = block1.getMaterial();
			return net.minecraft.util.text.translation.I18n.translateToLocalFormatted("part.item_pipe" + type.getSuffix(), net.minecraft.util.text.translation.I18n.translateToLocal("material." + mat.getDisplayName()).trim());
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
