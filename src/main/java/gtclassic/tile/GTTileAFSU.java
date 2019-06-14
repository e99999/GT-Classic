package gtclassic.tile;

import gtclassic.container.GTContainerAFSU;
import ic2.api.energy.EnergyNet;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileAFSU extends TileEntityElectricBlock {

	public GTTileAFSU() {
		super(5, (int) EnergyNet.instance.getPowerFromTier(5), 1000000000);
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerAFSU(player.inventory, this);
	}

	@Override
	public int getProcessRate() {
		return 256;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}
}
