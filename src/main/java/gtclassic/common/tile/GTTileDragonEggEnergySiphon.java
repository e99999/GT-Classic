package gtclassic.common.tile;

import gtclassic.api.helpers.GTUtility;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;

public class GTTileDragonEggEnergySiphon extends TileEntityMachine implements IEnergySource, IEmitterTile {

	protected double production = 128.0D;
	int storage;
	boolean enet = false;

	public GTTileDragonEggEnergySiphon() {
		super(0);
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing facing) {
		return true;
	}

	@Override
	public int getOutput() {
		return 128;
	}

	@Override
	public void drawEnergy(double amount) {
	}

	@Override
	public double getOfferedEnergy() {
		return this.getActive() ? this.production : 0.0D;
	}

	@Override
	public int getSourceTier() {
		return 2;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.enet = true;
		}
		this.checkForEgg();
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
	public void onBlockUpdate(Block block) {
		this.checkForEgg();
	}

	private void checkForEgg() {
		int trophyValue = GTUtility.getTrophyProductionValue(world, pos.up());
		this.production = trophyValue;
		this.setActive(this.production > 0);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}
}
