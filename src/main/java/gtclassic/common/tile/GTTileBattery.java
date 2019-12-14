package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;

import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.network.adv.NetworkField.BitLevel;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;

public class GTTileBattery extends TileEntityBlock
		implements IItemContainer, IEnergySink, IEUStorage, IEnergyEmitter, IEnergySource {

	private ItemStack drop = ItemStack.EMPTY;
	@NetworkField(index = 3)
	public int energy;
	public int tier;
	public int maxEnergy;
	public int maxInput;
	public int output;
	public boolean addedToEnergyNet;
	@NetworkField(index = 5, compression = BitLevel.Bit8)
	public int state = 0;

	public GTTileBattery() {
		this.addNetworkFields(new String[] { "state" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.drop = new ItemStack(nbt.getCompoundTag("drop"));
		this.energy = nbt.getInteger("energy");
		this.tier = nbt.getInteger("tier");
		this.maxEnergy = nbt.getInteger("max");
		this.maxInput = nbt.getInteger("output");
		this.output = nbt.getInteger("output");
		this.state = nbt.getInteger("state");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("drop", drop.writeToNBT(new NBTTagCompound()));
		nbt.setInteger("energy", this.energy);
		nbt.setInteger("state", this.state);
		nbt.setInteger("tier", this.tier);
		nbt.setInteger("max", this.maxEnergy);
		nbt.setInteger("output", this.output);
		return nbt;
	}

	@Override
	public void onNetworkUpdate(String field) {
		super.onNetworkUpdate(field);
		if (field.equals("state")) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (!this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnergyNet = true;
		}
		updateActive();
	}

	@Override
	public void onUnloaded() {
		if (this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnergyNet = false;
		}
		super.onUnloaded();
	}

	public ItemStack getItem() {
		return this.drop;
	}

	public void setItem(ItemStack item) {
		this.drop = item.copy();
		this.energy = (int) ElectricItem.manager.getCharge(this.drop);
	}

	public void setElectricTileInfo(int tier, int maxEnergy, int transferRate) {
		this.tier = tier;
		this.maxEnergy = maxEnergy;
		this.maxInput = transferRate;
		this.output = transferRate;
	}

	public void updateActive() {
		updateState(getStateFromCharge(this.energy, this.maxEnergy));
	}

	public int getStateFromCharge(int energy, int maxEnergy) {
		double half = maxEnergy * .5;
		double partial = maxEnergy * .75;
		if (energy == 0) {
			return 0;
		}
		if (energy > 0 && energy < half) {
			return 1;
		}
		if (energy >= half && energy < partial) {
			return 2;
		}
		if (energy >= partial && energy < maxEnergy) {
			return 3;
		}
		return 4;
	}

	public void updateState(int newState) {
		if (newState != state) {
			this.state = newState;
			this.getNetwork().updateTileEntityField(this, "state");
		}
	}

	@Override
	public List<ItemStack> getDrops() {
		ArrayList<ItemStack> drops = new ArrayList<>();
		ItemStack newDrop = drop.copy();
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(newDrop);
		nbt.setInteger("charge", this.energy);
		drops.add(newDrop);
		return drops;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return side != EnumFacing.UP;
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
		return this.tier;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if (amount <= (double) this.maxInput && amount > 0.0D) {
			this.energy = (int) ((double) this.energy + amount);
			int left = 0;
			if (this.energy >= this.maxEnergy) {
				left = this.energy - this.maxEnergy;
				this.energy = this.maxEnergy;
			}
			this.updateActive();
			return (double) left;
		} else {
			return 0.0D;
		}
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing side) {
		return side == EnumFacing.UP;
	}

	@Override
	public double getOfferedEnergy() {
		// Only place invert is called for output
		if (this.energy < this.output) {
			return 0.0D;
		}
		return this.output;
	}

	@Override
	public void drawEnergy(double amount) {
		this.energy = (int) ((double) this.energy - amount);
		this.updateActive();
	}

	@Override
	public int getSourceTier() {
		return this.tier;
	}
}
