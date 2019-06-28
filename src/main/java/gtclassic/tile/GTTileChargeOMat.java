package gtclassic.tile;

import gtclassic.container.GTContainerChargeOMat;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.item.ElectricItem;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;

public class GTTileChargeOMat extends TileEntityMachine implements IHasGui, IEnergySink, IEUStorage, ITickable {

	public int tier = 4;
	@NetworkField(index = 3)
	public int energy;
	@NetworkField(index = 4, override = true)
	public int maxEnergy = 10000000;
	@NetworkField(index = 5)
	public int maxInput = 2048;
	public int output = 2048;
	public boolean addedToEnergyNet;

	public GTTileChargeOMat() {
		super(18);
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
	public void onLoaded() {
		super.onLoaded();
		if (!this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnergyNet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnergyNet = false;
		}
		super.onUnloaded();
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerChargeOMat(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
		// Needed but unused
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
	public boolean acceptsEnergyFrom(IEnergyEmitter var1, EnumFacing side) {
		// Dont do the invert shit or else youll fuck connections
		return true;
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
		//Only place invert is actuall called for energy input
		if (amount <= (double) this.maxInput && amount > 0.0D && !this.isInverted()) {
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

	public boolean isInverted() {
		return this.world.isBlockPowered(this.getPos());
	}

	@Override
	public void update() {
		// TODO the is empty check or else speiger will blow his top
		if (this.energy > 0 && !this.isInverted()) {
			tryCharge();
		}
		if (this.energy < this.maxEnergy && this.isInverted()) {
			tryDischarge();
		}
	}

	public void tryCharge() {
		int newState;
		for (int i = 0; i < 9; ++i) {
			if (!((ItemStack) this.inventory.get(i)).isEmpty()) {
				newState = (int) ElectricItem.manager.charge((ItemStack) this.inventory.get(i), (double) this.energy, this.tier, false, false);
				this.energy -= newState;
				if (newState > 0) {
					this.getNetwork().updateTileGuiField(this, "energy");
				}
				if (ElectricItem.manager.getCharge(this.inventory.get(i)) == ElectricItem.manager.getMaxCharge(this.inventory.get(i))) {
					// if the charge is full move the item to an output slot
					for (int j = 9; j < 18; ++j) {
						if (this.inventory.get(j).isEmpty()) {
							this.inventory.set(j, StackUtil.copyWithSize(this.inventory.get(i), 1));
							this.inventory.get(i).shrink(1);
						}
					}
				}
			}
		}
	}

	public void tryDischarge() {
	}
}
