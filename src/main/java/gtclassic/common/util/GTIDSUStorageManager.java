package gtclassic.common.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import ic2.api.classic.network.INetworkFieldData;
import ic2.api.classic.network.adv.IInputBuffer;
import ic2.api.classic.network.adv.IOutputBuffer;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.WorldSavedData;

public class GTIDSUStorageManager extends WorldSavedData {

	Map<UUID, EnergyWrapper> wrappers = new Object2ObjectOpenHashMap<UUID, EnergyWrapper>();

	public GTIDSUStorageManager(String name) {
		super(name);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		wrappers.clear();
		NBTTagList list = nbt.getTagList("data", 10);
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = list.getCompoundTagAt(i);
			EnergyWrapper wrapper = new EnergyWrapper(this);
			wrapper.setEnergy(compound.getInteger("Energy"));
			wrappers.put(compound.getUniqueId("owner"), wrapper);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for (Entry<UUID, EnergyWrapper> entry : wrappers.entrySet()) {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setUniqueId("owner", entry.getKey());
			nbt.setInteger("Energy", entry.getValue().getStoredEnergy());
			list.appendTag(nbt);
		}
		compound.setTag("data", list);
		return compound;
	}

	public EnergyWrapper getWrapper(UUID owner) {
		EnergyWrapper wrapper = wrappers.get(owner);
		if (wrapper == null) {
			wrapper = new EnergyWrapper(this);
			wrappers.put(owner, wrapper);
		}
		return wrapper;
	}

	public static EnergyWrapper createDummy() {
		return new DummyWrapper();
	}

	public static class EnergyWrapper implements INetworkFieldData {

		GTIDSUStorageManager wrapper;
		int energy = 0;

		public EnergyWrapper(GTIDSUStorageManager wrapper) {
			this.wrapper = wrapper;
		}

		public int getStoredEnergy() {
			return energy;
		}

		public void addEnergy(int amount) {
			energy += amount;
			if (wrapper != null) {
				wrapper.markDirty();
			}
		}

		public void setEnergy(int amount) {
			energy = amount;
			if (wrapper != null) {
				wrapper.markDirty();
			}
		}

		public void removeEnergy(int amount) {
			energy -= amount;
			if (wrapper != null) {
				wrapper.markDirty();
			}
		}

		public boolean isDummy() {
			return false;
		}

		@Override
		public void read(IInputBuffer buffer) {
			energy = buffer.readInt();
		}

		@Override
		public void write(IOutputBuffer buffer) {
			buffer.writeInt(energy);
		}
	}

	public static class DummyWrapper extends EnergyWrapper {

		public DummyWrapper() {
			super(null);
		}

		@Override
		public boolean isDummy() {
			return true;
		}
	}
}
