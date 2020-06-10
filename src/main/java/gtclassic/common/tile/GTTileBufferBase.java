package gtclassic.common.tile;

import java.util.List;
import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.energy.EnergyNetLocal;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.util.obj.IRedstoneProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;

public abstract class GTTileBufferBase extends TileEntityMachine implements IEnergyConductor, IEnergySink,
		INetworkClientTileEntityEventListener, IRedstoneProvider, ITickable, IGTDebuggableTile {

	public int tier = 1;
	public int output = 32;
	public int maxEnergy = 32;
	@NetworkField(index = 3)
	public int energy;
	public boolean addedToEnet;
	@NetworkField(index = 4)
	public boolean conduct = false;
	public boolean outputRedstone = false;
	public boolean invertRedstone = false;
	public boolean invertFilter = false;
	public boolean hasInvertFilter = false;
	public boolean hasNbtFilter = false;
	public boolean ignoreNbt = false;
	public int redstoneStrength = 0;
	public boolean hasRedstone = true;
	public boolean isFluid = false;
	public static final String NBT_STOREDENERGY = "energy";
	public static final String NBT_CONDUCT = "conduct";
	public static final String NBT_OUTPUTREDSTONE = "outputRedstone";
	public static final String NBT_INVERTREDSTONE = "invertRedstone";
	public static final String NBT_INVERTFILTER = "invertFilter";
	public static final String NBT_IGNORENBT = "ignoreNbt";

	public GTTileBufferBase(int slots) {
		super(slots);
		this.addGuiFields(new String[] { NBT_STOREDENERGY, NBT_CONDUCT, NBT_OUTPUTREDSTONE, NBT_INVERTREDSTONE,
				NBT_INVERTFILTER, NBT_IGNORENBT });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.energy = nbt.getInteger(NBT_STOREDENERGY);
		this.conduct = nbt.getBoolean(NBT_CONDUCT);
		this.outputRedstone = nbt.getBoolean(NBT_OUTPUTREDSTONE);
		this.invertRedstone = nbt.getBoolean(NBT_INVERTREDSTONE);
		this.invertFilter = nbt.getBoolean(NBT_INVERTFILTER);
		this.ignoreNbt = nbt.getBoolean(NBT_IGNORENBT);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_STOREDENERGY, this.energy);
		nbt.setBoolean(NBT_CONDUCT, this.conduct);
		nbt.setBoolean(NBT_OUTPUTREDSTONE, this.outputRedstone);
		nbt.setBoolean(NBT_INVERTREDSTONE, this.invertRedstone);
		nbt.setBoolean(NBT_INVERTFILTER, this.invertFilter);
		nbt.setBoolean(NBT_IGNORENBT, this.ignoreNbt);
		return nbt;
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
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return this.getFacing() != facing;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return this.getFacing() != side;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		if (this.conduct) {
			return this.getFacing() == side;
		}
		return false;
	}

	@Override
	public double getDemandedEnergy() {
		return (double) (this.maxEnergy - this.energy);
	}

	@Override
	public int getSinkTier() {
		return 1;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if (amount <= (double) this.output && amount > 0.0D) {
			this.energy = (int) ((double) this.energy + amount);
			int left = 0;
			if (this.energy >= this.maxEnergy) {
				left = this.energy - this.maxEnergy;
				this.energy = this.maxEnergy;
			}
			this.getNetwork().updateTileGuiField(this, NBT_STOREDENERGY);
			return (double) left;
		} else {
			return 0.0D;
		}
	}

	@Override
	public double getConductionLoss() {
		return 0.2D;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 33.0D;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 9001.0D;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 32.0D;
	}

	@Override
	public void removeConductor() {
		boolean burn = EnergyNetLocal.burn && this.world.rand.nextFloat() < EnergyNetLocal.chance;
		this.world.setBlockToAir(this.getPos());
		this.getNetwork().initiateTileEntityEvent(this, 0, true);
		if (burn) {
			this.world.setBlockState(this.getPos(), Blocks.FIRE.getDefaultState());
		}
	}

	@Override
	public void removeInsulation() {
		// Empty, for interface implementation only
	}

	@Override
	public void onNetworkEvent(EntityPlayer var1, int event) {
		if (event == 0) {
			onUnloaded();
			this.conduct = !this.conduct;
			onLoaded();
			this.getNetwork().updateTileGuiField(this, NBT_CONDUCT);
		}
		if (this.hasRedstone) {
			if (event == 1) {
				this.outputRedstone = !this.outputRedstone;
				this.getNetwork().updateTileGuiField(this, NBT_OUTPUTREDSTONE);
			}
			if (event == 2) {
				this.invertRedstone = !this.invertRedstone;
				this.getNetwork().updateTileGuiField(this, NBT_INVERTREDSTONE);
				world.notifyNeighborsOfStateChange(pos, blockType, true);
			}
		}
		if (this.hasInvertFilter && event == 3) {
			this.invertFilter = !this.invertFilter;
			this.getNetwork().updateTileGuiField(this, NBT_INVERTFILTER);
		}
		if (this.hasNbtFilter && event == 4) {
			this.ignoreNbt = !this.ignoreNbt;
			this.getNetwork().updateTileGuiField(this, NBT_IGNORENBT);
		}
	}

	@Override
	public int getRedstoneStrenght(EnumFacing paramEnumFacing) {
		if (!this.outputRedstone) {
			return 0;
		}
		return this.invertRedstone ? 15 - this.redstoneStrength : this.redstoneStrength;
	}

	public abstract boolean isInventoryFull();

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 10 == 0) {
			if (this.hasRedstone) {
				int oldStrength = this.redstoneStrength;
				if (this.outputRedstone && isInventoryFull()) {
					this.redstoneStrength = 15;
				} else {
					this.redstoneStrength = 0;
				}
				if (this.redstoneStrength != oldStrength) {
					world.notifyNeighborsOfStateChange(pos, blockType, true);
				}
			}
			onBufferTick();
		}
	}

	/** Called twice a second for buffer operations **/
	public abstract void onBufferTick();

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = super.getDrops();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getItem() instanceof ItemDisplayIcon) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Emits Energy: " + this.conduct, true);
		data.put("Stored: " + this.energy + " EU", true);
		if (this.hasRedstone) {
			data.put("Redstone Strength: " + this.redstoneStrength, true);
			data.put("Inverted Redstone: " + this.invertRedstone, true);
		}
		if (this.hasInvertFilter) {
			data.put("Inverted Filter: " + this.invertFilter, true);
		}
		if (this.hasNbtFilter) {
			data.put("Ignore Nbt: " + this.ignoreNbt, true);
		}
	}
}
