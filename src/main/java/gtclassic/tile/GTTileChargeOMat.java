package gtclassic.tile;

import java.util.List;

import gtclassic.container.GTContainerChargeOMat;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;

public class GTTileChargeOMat extends TileEntityMachine
		implements IHasGui, IEnergySink, IEUStorage, ITickable, IEnergyEmitter, IEnergySource {

	@NetworkField(index = 3)
	public int energy;
	public int tier = 4;
	public int maxEnergy = 10000000;
	public int maxInput = 2048;
	public int output = 2048;
	public boolean addedToEnergyNet;
	protected static final int[] slotInputs = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	protected static final int[] slotOutputs = new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 };

	public GTTileChargeOMat() {
		super(18);
		this.addGuiFields(new String[] { "energy" });
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.VERTICAL, slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
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
	// @Override
	// public int getMaxStackSize(int slot) {
	// return 1;
	// }

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
		// Only place invert is actually called for energy input
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

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing var2) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		// Only place invert is called for output
		if (this.energy < this.output) {
			return 0.0D;
		}
		return this.isInverted() ? (double) this.output : 0.0D;
	}

	@Override
	public void drawEnergy(double amount) {
		this.energy = (int) ((double) this.energy - amount);
		this.getNetwork().updateTileGuiField(this, "energy");
	}

	@Override
	public int getSourceTier() {
		return this.tier;
	}

	public boolean isInverted() {
		return this.world.isBlockPowered(this.getPos());
	}

	@Override
	public void update() {
		if (!this.isInverted()) {
			this.setActive(false);
			tryCharge();
			tryWirelessCharge();
		} else if (this.isInverted()) {
			this.setActive(true);
			tryDischarge();
		}
		if (this.energy > this.maxEnergy) {
			this.energy = this.maxEnergy;
		}
	}

	public boolean hasEnergy() {
		return this.energy > 0;
	}

	public boolean canFill() {
		return this.energy < this.maxEnergy;
	}

	public void tryCharge() {
		// Here I iterate the input slots to try to charge items
		for (int i = 0; i < 9; ++i) {
			if (!this.inventory.get(i).isEmpty()) {
				if (hasEnergy()) {
					int removed = (int) ElectricItem.manager.charge((ItemStack) this.inventory.get(i), (double) this.energy, this.tier, false, false);
					this.energy -= removed;
					if (removed > 0) {
						this.getNetwork().updateTileGuiField(this, "energy");
					}
				}
				// If the Item is fully charged attempt to move it to the first open slot
				if (ElectricItem.manager.getCharge(this.inventory.get(i)) == ElectricItem.manager.getMaxCharge(this.inventory.get(i))) {
					for (int j = 9; j < 18; ++j) {
						if (this.inventory.get(j).isEmpty()) {
							this.inventory.set(j, StackUtil.copyWithSize(this.inventory.get(i), 1));
							this.inventory.get(i).shrink(1);
						}
					}
				}
				// MABYEDO limit charge to one item at a time?
			}
		}
	}

	public void tryWirelessCharge() {
		// I opted to make this slower rather than 100 lines long, its not the main
		// feature of the tile
		if (world.getTotalWorldTime() % 20 == 0 && this.hasEnergy()) {
			if (world.playerEntities.isEmpty()) {
				return;
			}
			if (!world.isAreaLoaded(pos, 3)) {
				return;
			}
			if (!getEntitiesInRange().isEmpty()) {
				for (EntityLivingBase entity : getEntitiesInRange()) {
					for (ItemStack armor : entity.getArmorInventoryList()) {
						this.drawEnergy(ElectricItem.manager.charge((ItemStack) armor, (double) this.energy, this.tier, false, false));
					}
				}
			}
		}
	}

	public void tryDischarge() {
		// Try to discharge
		for (int i = 0; i < 9; ++i) {
			if (!this.inventory.get(i).isEmpty()) {
				if (this.canFill()) {
					int added = (int) ElectricItem.manager.discharge(this.inventory.get(i), (double) (this.maxEnergy
							- this.energy), this.tier, false, true, false);
					this.energy += added;
					if (added > 0) {
						this.getNetwork().updateTileGuiField(this, "energy");
					}
				}
				// If the Item is fully discharged attempt to move it to the first open slot
				if (ElectricItem.manager.getCharge(this.inventory.get(i)) == 0.0D) {
					for (int j = 9; j < 18; ++j) {
						if (this.inventory.get(j).isEmpty()) {
							this.inventory.set(j, StackUtil.copyWithSize(this.inventory.get(i), 1));
							this.inventory.get(i).shrink(1);
						}
					}
				}
				// MABYEDO limit discharge to one item at a time?
			}
		}
	}

	public List<EntityLivingBase> getEntitiesInRange() {
		return this.world.getEntitiesWithinAABB(EntityLivingBase.class, (new AxisAlignedBB(this.getPos())).grow(5.0D));
	}
}
