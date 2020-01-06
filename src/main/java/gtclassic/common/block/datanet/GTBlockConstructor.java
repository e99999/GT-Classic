package gtclassic.common.block.datanet;

import gtclassic.api.interfaces.IGTDataNetBlock;
import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockMachineDirectionable;
import gtclassic.common.tile.datanet.GTTileConstructorFluid;
import gtclassic.common.tile.datanet.GTTileConstructorItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.world.World;

public class GTBlockConstructor extends GTBlockMachineDirectionable implements IGTDataNetBlock {

	public GTBlockConstructor(String name, LocaleComp comp, int additionalInfo) {
		super(name, comp, additionalInfo);
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
