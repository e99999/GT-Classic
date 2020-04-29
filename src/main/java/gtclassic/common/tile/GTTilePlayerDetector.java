package gtclassic.common.tile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import gtclassic.api.helpers.int3;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.personal.base.misc.IPersonalBlock;
import ic2.core.block.personal.base.misc.IPersonalInventory;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IRedstoneProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;

public class GTTilePlayerDetector extends TileEntityElecMachine
		implements IPersonalBlock, ITickable, IRedstoneProvider, IGTDebuggableTile, IClickable {

	double range = 8.0D;
	AxisAlignedBB areaBB = null;
	@NetworkField(index = 7)
	private UUID owner;
	int mode = 0;

	public GTTilePlayerDetector() {
		super(0, 32);
		maxEnergy = 1000;
		this.addNetworkFields(new String[] { "owner" });
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public void update() {
		checkEnergy();
		if (world.getTotalWorldTime() % 10 == 0) {
			boolean oldState = this.getActive();
			boolean newState = checkArea();
			if (oldState != newState) {
				this.setActive(newState);
				world.notifyNeighborsOfStateChange(pos, this.getBlockType(), true);
			}
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
		if (this.mode == 0) {// any player check
			return world.isAnyPlayerWithinRangeAt(this.pos.getX(), this.pos.getY(), this.pos.getZ(), range);
		}
		if (this.mode == 1) { // owner check
			return ownerCheck();
		}
		if (this.mode == 2) {// enemy check
			return !ownerCheck();
		}
		return false;
	}

	public void advanceMode() {
		++this.mode;
		if (this.mode >= 3) {
			this.mode = 0;
		}
	}

	public String getModeName() {
		switch (mode) {
		case 0:
			return "Any Players";
		case 1:
			return "Owner";
		case 2:
			return "Not Owner";
		default:
			return "Error";
		}
	}

	public boolean ownerCheck() {
		AxisAlignedBB area = new AxisAlignedBB(new int3(pos, getFacing()).asBlockPos()).grow(this.range);
		List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, area);
		return (players.contains(world.getPlayerEntityByUUID(this.owner)));
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
		}
		if (this.owner.equals(user)) {
			return true;
		}
		return IC2.platform.isOp(user);
	}

	public IPersonalInventory getInventory(UUID user) {
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasUniqueId("owner")) {
			this.owner = nbt.getUniqueId("owner");
		}
		this.mode = nbt.getInteger("mode");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.owner != null) {
			nbt.setUniqueId("owner", this.owner);
		}
		nbt.setInteger("mode", this.mode);
		return nbt;
	}

	@Override
	public int getRedstoneStrenght(EnumFacing var1) {
		return this.getActive() ? 15 : 0;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return canAccess(player.getUniqueID());
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand var2, EnumFacing var3, Side var4) {
		if (player.isSneaking() && player.getHeldItemMainhand().isEmpty() && this.canAccess(player.getUniqueID())) {
			this.advanceMode();
			if (this.isSimulating()) {
				IC2.platform.messagePlayer(player, this.getModeName());
			}
			player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0F, 1.0F);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public void onLeftClick(EntityPlayer var1, Side var2) {
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Mode: " + this.getModeName(), false);
		data.put("Range: " + this.range, false);
	}
}
