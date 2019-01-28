package gtclassic.tileentity;

import gtclassic.container.GTContainerBasicEnergyStorage;
import gtclassic.util.GTValues;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileEntityBasicEnergyStorage extends TileEntityElectricBlock {

	public GTTileEntityBasicEnergyStorage() {
		super(4, 2048, 100000000);
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBasicEnergyStorage(player.inventory, this);
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
		return GTValues.hesu;
	}

	@Override
	public void update() {
		if (this.getStoredEU() > 0) {
			this.setActive(true);
			super.update();
		} else {
			this.setActive(false);
			super.update();

		}
	}
}
