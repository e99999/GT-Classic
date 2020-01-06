package gtclassic.common.block.datanet;

import gtclassic.api.interfaces.IGTDataNetBlock;
import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockMachineDirectionable;
import gtclassic.common.tile.datanet.GTTileDataImportFluid;
import gtclassic.common.tile.datanet.GTTileDataImportItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.world.World;

public class GTBlockDataImporter extends GTBlockMachineDirectionable implements IGTDataNetBlock {

	public GTBlockDataImporter(String name, LocaleComp comp) {
		super(name, comp, 3);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this == GTBlocks.tileDataItemImporter) {
			return new GTTileDataImportItem();
		}
		return new GTTileDataImportFluid();
	}
}
