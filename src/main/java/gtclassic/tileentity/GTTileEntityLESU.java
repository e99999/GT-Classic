package gtclassic.tileentity;

import gtclassic.container.GTContainerLESU;
import gtclassic.util.GTLang;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileEntityLESU extends TileEntityElectricBlock {

	int casingCount = 0;
	int casingAddition = 1000000;
	static int defaultLESU = 1000000;

	public GTTileEntityLESU() {
		super(3, 512, defaultLESU);
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerLESU(player.inventory, this);
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
		return GTLang.lesu;
	}
}
