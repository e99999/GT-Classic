package gtclassic.common.block.datanet;

import gtclassic.api.interfaces.IGTDataNetBlock;
import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockMachineDirectionable;
import gtclassic.common.tile.datanet.GTTileDataExportFluid;
import gtclassic.common.tile.datanet.GTTileDataExportItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.world.World;

public class GTBlockDataExporter extends GTBlockMachineDirectionable implements IGTDataNetBlock {

	public GTBlockDataExporter(String name, LocaleComp comp, int additionalInfo) {
		super(name, comp, additionalInfo);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileDataItemExporter) {
			return new GTTileDataExportItem();
		}
		if (this == GTBlocks.tileDataFluidExporter) {
			return new GTTileDataExportFluid();
		}
		return new TileEntityBlock();
	}
}
