package gtclassic.common.tile.datanet;

import java.util.Map;

import gtclassic.api.interfaces.IGTDataNetObject;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.IC2;
import ic2.core.block.base.util.info.misc.IWrench;
import ic2.core.inventory.filters.IFilter;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileOutputNodeBase extends GTTileBaseDataNode
		implements IGTDebuggableTile, IGTDataNetObject, ITickable {

	public GTTileComputerCube computer;
	public int channel;

	/**
	 * This tile does not move anything, it merely provides the location of an
	 * output point to input nodes
	 **/
	public GTTileOutputNodeBase(int slots) {
		super(slots);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey(GTDataNet.NBT_CHANNEL)) {
			this.channel = nbt.getInteger(GTDataNet.NBT_CHANNEL);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(GTDataNet.NBT_CHANNEL, this.channel);
		return nbt;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && !player.isSneaking();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		if (player.isSneaking() && player.getHeldItemMainhand().getItem() instanceof IWrench) {
			this.channel++;
			this.channel = this.channel > 15 ? 0 : this.channel;
			if (this.isSimulating()) {
				IC2.platform.messagePlayer(player, "Channel: " + this.channel);
				IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			}
			return false;
		}
		return true;
	}

	/**
	 * This returns the position for digitizer/input nodes to check for a valid
	 * inventory
	 **/
	public abstract BlockPos inventoryPos();

	/** This returns the side the above inventory should be interacted with **/
	public abstract EnumFacing inventoryFacing();

	public abstract GTDataNet.DataType dataType();

	@Override
	public void update() {
		if (world.getTotalWorldTime() % GTDataNet.RESET_RATE == 0) {
			this.computer = null;
		}
	}

	public IFilter inventoryFilter() {
		return null;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		if (this.computer != null && this.computer.dataNet != null) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found or network is not powered", false);
		}
		data.put("Channel: " + this.channel, false);
	}
}
