package gtclassic.common.block.datanet;

import java.util.List;

import gtclassic.common.GTBlocks;
import gtclassic.common.tile.datanet.GTTileConstructorFluid;
import gtclassic.common.tile.datanet.GTTileConstructorItem;
import gtclassic.common.tile.datanet.GTTileDigitizerFluid;
import gtclassic.common.tile.datanet.GTTileDigitizerItem;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GTBlockDataNode extends GTBlockBaseDataNode {

	public GTBlockDataNode(String name, int id, LocaleComp comp) {
		super(name, id, comp);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + I18n.format(GTDataNet.TOOLTIP));
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileReconstructorItem) {
			return new GTTileConstructorItem();
		}
		if (this == GTBlocks.tileReconstructorFluid) {
			return new GTTileConstructorFluid();
		}
		if (this == GTBlocks.tileDigitizerItem) {
			return new GTTileDigitizerItem();
		}
		if (this == GTBlocks.tileDigitizerFluid) {
			return new GTTileDigitizerFluid();
		}
		return new TileEntityBlock();
	}
}
