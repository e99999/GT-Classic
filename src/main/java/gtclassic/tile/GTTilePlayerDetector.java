package gtclassic.tile;

import java.util.UUID;

import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.personal.base.misc.IPersonalBlock;
import ic2.core.block.personal.base.misc.IPersonalInventory;
import ic2.core.util.obj.IRedstoneProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class GTTilePlayerDetector extends TileEntityElecMachine
		implements IPersonalBlock, ITickable, IRedstoneProvider {

	double range = 8.0D;
	AxisAlignedBB areaBB = null;
	@NetworkField(index = 7)
	private UUID owner;

	public GTTilePlayerDetector() {
		super(0, 32);
		maxEnergy = 1000;
		setWorld(world);
		this.addNetworkFields(new String[] { "owner" });
	}

	@Override
	public boolean supportsNotify() {
		return false;
	}

	@Override
	public void update() {
		checkEnergy();
		if (world.getTotalWorldTime() % 20 == 0) {
			this.setActive(checkArea());
			world.notifyNeighborsOfStateChange(pos, blockType, true);
		}
	}

	public void checkEnergy() {
		if (this.energy > 0) {
			useEnergy(1);
			this.range = 32.0D;
		} else {
			this.range = 8.0D;
		}
	}

	public boolean checkArea() {
		if (world.playerEntities.isEmpty()) {
			return false;
		}
		if (!world.isAreaLoaded(pos, 3)) {
			return false;
		}
		return world.isAnyPlayerWithinRangeAt(this.pos.getX(), this.pos.getY(), this.pos.getZ(), range);
	}

	public void setOwner(UUID user) {
		if (this.owner == null && user != null) {
			this.owner = user;
		}
	}

	public boolean canAccess(UUID user) {
		if (this.owner == null) {
			this.setOwner(user);
			return true;
		} else if (this.owner.equals(user)) {
			return true;
		} else if (IC2.platform.isOp(user)) {
			return true;
		} else {
			return false;
		}
	}

	public IPersonalInventory getInventory(UUID user) {
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasUniqueId("Owner")) {
			this.owner = nbt.getUniqueId("Owner");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.owner != null) {
			nbt.setUniqueId("Owner", this.owner);
		}
		return nbt;
	}

	@Override
	public int getRedstoneStrenght(EnumFacing var1) {
		return this.isActive ? 15 : 0;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return canAccess(player.getUniqueID()) && facing != getFacing() && facing.getAxis().isHorizontal();
	}
	
	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return canAccess(player.getUniqueID());
	}
}
