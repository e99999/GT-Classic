package gtclassic.common.block.datanet;

import java.util.List;

import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockMachineDirectionable;
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

public class GTBlockDigitizer extends GTBlockMachineDirectionable {

	public GTBlockDigitizer(String name, LocaleComp comp) {
		super(name, comp, 2);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + I18n.format(GTDataNet.TOOLTIP));
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this == GTBlocks.tileDigitizerItem) {
			return new GTTileDigitizerItem();
		}
		if (this == GTBlocks.tileDigitizerFluid) {
			return new GTTileDigitizerFluid();
		}
		return new TileEntityBlock();
	}
}
