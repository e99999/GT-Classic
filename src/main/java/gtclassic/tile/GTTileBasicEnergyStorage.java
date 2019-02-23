package gtclassic.tile;

import gtclassic.container.GTContainerBasicEnergyStorage;
import gtclassic.util.GTValues;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileBasicEnergyStorage extends TileEntityElectricBlock {

	public GTTileBasicEnergyStorage() {
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
		return GTValues.besu;
	}

	@Override
	public void update() {
		this.setActive(this.getStoredEU() > 0);
		super.update();
	}
}
