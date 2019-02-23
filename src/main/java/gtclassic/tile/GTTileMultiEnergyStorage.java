package gtclassic.tile;

import gtclassic.container.GTContainerMultiEnergyStorage;
import gtclassic.util.GTValues;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileMultiEnergyStorage extends TileEntityElectricBlock {

	int casingCount = 0;
	int casingAddition = 1000000;
	static int defaultLESU = 1000000;

	public GTTileMultiEnergyStorage() {
		super(2, 128, defaultLESU);
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerMultiEnergyStorage(player.inventory, this);
	}

	@Override
	public int getProcessRate() {
		return 32;
	}

	@Override
	public double getWrenchDropRate() {
		return 0.70D;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTValues.mesu;
	}
}
