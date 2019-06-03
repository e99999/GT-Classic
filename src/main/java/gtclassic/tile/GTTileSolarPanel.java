package gtclassic.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import ic2.core.block.generator.tile.TileEntitySolarPanel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;

public class GTTileSolarPanel extends TileEntityMachine implements ITickable, IEnergySource, IEmitterTile {

	int ticker;
	int storage = 8;
	boolean enet = false;

	public GTTileSolarPanel() {
		super(0);
		this.ticker = 127;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing var2) {
		return var2 == EnumFacing.DOWN;
	}

	@Override
	public int getOutput() {
		return 8;
	}

	public void drawEnergy(double amount) {
		this.storage = (int) ((double) this.storage - amount);
	}

	@Override
	public double getOfferedEnergy() {
		return 8;
	}

	@Override
	public int getSourceTier() {
		return 1;
	}

	@Override
	public boolean supportsRotation() {
		return false;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.enet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.isSimulating() && this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.enet = false;
		}
		super.onUnloaded();
	}

	@Override
	public void update() {
		if (this.ticker++ % 128 == 0) {
			this.setActive(TileEntitySolarPanel.isSunVisible(this.world, this.getPos().up()));
		}
		if (this.getActive()) {
			this.storage = 8;
		}
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != EnumFacing.UP && facing != EnumFacing.DOWN;
	}
}
