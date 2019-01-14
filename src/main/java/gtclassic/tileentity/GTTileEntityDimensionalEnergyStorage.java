package gtclassic.tileentity;

import gtclassic.container.GTContainerIDSU;
import gtclassic.util.GTValues;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileEntityDimensionalEnergyStorage extends TileEntityElectricBlock {

	public GTTileEntityDimensionalEnergyStorage() {
		super(4, 2048, 400000000);
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerIDSU(player.inventory, this);
	}

	@Override
	public int getProcessRate() {
		return 128;
	}

	@Override
	public double getWrenchDropRate() {
		return 0.70D;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTValues.idsu;
	}
}
