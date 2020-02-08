package gtclassic.common.tile.datanet;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTHelperMath;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;

public class GTTileNetworkEnergizer extends GTTileBaseDataNode implements IEnergySink, IEUStorage, ITickable {

	@NetworkField(index = 10, override = true)
	public int energy = 0;
	private int maxEnergy = 512;
	private boolean addedToEnet;

	public GTTileNetworkEnergizer() {
		super(0);
		this.addGuiFields(new String[] { "energy" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.energy = nbt.getInteger("energy");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("energy", this.energy);
		return nbt;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter facing, EnumFacing side) {
		return side == this.getFacing();
	}

	@Override
	public int getMaxEU() {
		return this.maxEnergy;
	}

	@Override
	public int getStoredEU() {
		return this.energy;
	}

	@Override
	public double getDemandedEnergy() {
		return (double) (this.maxEnergy - this.energy);
	}

	@Override
	public int getSinkTier() {
		return 3;
	}

	@Override
	public int getCost() {
		return 0;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.addedToEnet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.isSimulating() && this.addedToEnet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnet = false;
		}
		super.onUnloaded();
	}

	@Override
	public void update() {
		if (this.energy > 0 && this.getDataManager() != null && this.getDataManager().getDemandedEnergy() > 0) {
			int demanded = this.getDataManager().getDemandedEnergy();
			demanded = GTHelperMath.clip(demanded, 1, this.maxEnergy);
			try {
				this.getDataManager().injectEnergy(demanded);
			} catch (Exception exception) {
				GTMod.logger.info("DATA NET FAILURE: Energy transfer to network manager has failed");
			}
			this.useEnergy(demanded);
		}
	}

	public void useEnergy(int use) {
		this.energy -= use;
		if (this.energy < 0) {
			this.energy = 0;
		}
		// this.getNetwork().updateTileGuiField(this, "energy");
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if (amount <= (double) this.maxEnergy && amount > 0.0D) {
			this.energy = (int) ((double) this.energy + amount);
			int left = 0;
			if (this.energy >= this.maxEnergy) {
				left = this.energy - this.maxEnergy;
				this.energy = this.maxEnergy;
			}
			this.getNetwork().updateTileGuiField(this, "energy");
			return (double) left;
		} else {
			return 0.0D;
		}
	}
}
