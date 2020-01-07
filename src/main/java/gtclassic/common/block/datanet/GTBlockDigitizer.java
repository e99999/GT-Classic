package gtclassic.common.block.datanet;

import gtclassic.api.interfaces.IGTDataNetBlock;
import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockMachineDirectionable;
import gtclassic.common.tile.datanet.GTTileDigitizerFluid;
import gtclassic.common.tile.datanet.GTTileDigitizerItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.world.World;

public class GTBlockDigitizer extends GTBlockMachineDirectionable implements IGTDataNetBlock {

	public GTBlockDigitizer(String name, LocaleComp comp) {
		super(name, comp, 2);
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
