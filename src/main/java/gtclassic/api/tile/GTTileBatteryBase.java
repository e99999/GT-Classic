package gtclassic.api.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
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

public abstract class GTTileBatteryBase extends TileEntityBlock
		implements IItemContainer, IEnergySink, IEUStorage, IEnergyEmitter, IEnergySource, IGTDebuggableTile {

	private ItemStack drop = ItemStack.EMPTY;
	@NetworkField(index = 3)
	public int energy;
	public int tier;
	public int maxEnergy;
	public int maxInput;
	public int output;
	public boolean addedToEnergyNet;

	public GTTileBatteryBase() {
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.drop = new ItemStack(nbt.getCompoundTag("drop"));
		this.energy = nbt.getInteger("energy");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("drop", drop.writeToNBT(new NBTTagCompound()));
		nbt.setInteger("energy", this.energy);
		return nbt;
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

	public void updateActive() {
		if (this.energy > (this.maxEnergy * .01)) {
			if (this.isActive) {
				return;
			}
			this.setActive(true);
		} else {
			this.setActive(false);
		}
	}

	@Override
	public List<ItemStack> getDrops() {
		ArrayList<ItemStack> drops = new ArrayList<>();
		ItemStack newDrop = drop.copy();
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(newDrop);
		if (this.energy > 0) {
			nbt.setInteger("charge", this.energy);
		}
		drops.add(newDrop);
		return drops;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Stored Energy: " + this.energy, false);
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
		// Only place invert is actually called for energy input
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
