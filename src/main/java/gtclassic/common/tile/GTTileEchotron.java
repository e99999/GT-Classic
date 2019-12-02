package gtclassic.common.tile;

import java.util.List;
import java.util.UUID;

import gtclassic.api.helpers.int3;
import gtclassic.common.GTSounds;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.personal.base.misc.IPersonalBlock;
import ic2.core.block.personal.base.misc.IPersonalInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class GTTileEchotron extends TileEntityElecMachine
		implements IPersonalBlock, ITickable{

	AxisAlignedBB areaBB = null;
	@NetworkField(index = 7)
	private UUID owner;

	public GTTileEchotron() {
		super(0, 32);
		maxEnergy = 1000;
		this.addNetworkFields(new String[] { "owner" });
	}

	@Override
	public boolean supportsNotify() {
		return false;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 100 == 0 && this.energy >= 10 && !redstoneEnabled()) {
			getNetwork().initiateTileEntityEvent(this, 0, false);
			world.playSound((EntityPlayer) null, this.pos, GTSounds.SONAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
			AxisAlignedBB area = new AxisAlignedBB(new int3(pos, getFacing()).asBlockPos()).grow(32.0D);
			List<Entity> list = world.getEntitiesInAABBexcluding(world.getPlayerEntityByUUID(this.owner), area, null);
			if (!list.isEmpty()) {
				for (Entity thing : list) {
					if (thing instanceof EntityLiving) {
						((EntityLivingBase) thing).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 80, 0, false, false));
					}
					if (thing instanceof EntityPlayer) {
						((EntityPlayer) thing).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 80, 0, false, false));
					}
				}
			}
			this.useEnergy(10);
		}
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
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.owner != null) {
			nbt.setUniqueId("owner", this.owner);
		}
		return nbt;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return canAccess(player.getUniqueID()) && facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return canAccess(player.getUniqueID());
	}

	public boolean redstoneEnabled() {
		return this.world.isBlockPowered(this.getPos());
	}
}
