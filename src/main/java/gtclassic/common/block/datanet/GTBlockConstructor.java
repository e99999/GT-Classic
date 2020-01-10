package gtclassic.common.block.datanet;

import java.util.List;

import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockMachineDirectionable;
import gtclassic.common.tile.datanet.GTTileConstructorFluid;
import gtclassic.common.tile.datanet.GTTileConstructorItem;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GTBlockConstructor extends GTBlockMachineDirectionable {

	public GTBlockConstructor(String name, LocaleComp comp, int additionalInfo) {
		super(name, comp, additionalInfo);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + I18n.format(GTDataNet.TOOLTIP));
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileConstructorItem) {
			return new GTTileConstructorItem();
		}
		if (this == GTBlocks.tileConstructorFluid) {
			return new GTTileConstructorFluid();
		}
		return new TileEntityBlock();
	}
}
