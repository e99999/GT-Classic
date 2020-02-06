package gtclassic.common.tile.datanet;

import java.util.Map;

import javax.annotation.Nullable;

import gtclassic.api.helpers.GTUtility;
import gtclassic.api.interfaces.IGTDataNetObject;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.obj.IClickable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.relauncher.Side;

public class GTTileBaseDataNode extends TileEntityMachine implements IGTDebuggableTile, IGTDataNetObject, IClickable {

	@NetworkField(index = 8)
	public RotationList connection;
	@NetworkField(index = 9)
	public RotationList anchors;
	private GTTileComputerCube computer;
	private int channel;
	private static final String NBT_CONNECTION = "connection";
	private static final String NBT_ANCHORS = "anchors";

	public GTTileBaseDataNode(int slots) {
		super(slots);
		this.connection = RotationList.EMPTY;
		this.anchors = RotationList.EMPTY;
		this.addNetworkFields(new String[] { NBT_CONNECTION, NBT_ANCHORS });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.anchors = RotationList.ofFacings(EnumFacing.getFront(nbt.getByte("Facing")));
		this.channel = nbt.getInteger(GTUtility.DATA_NET_NBT_CHANNEL);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(GTUtility.DATA_NET_NBT_CHANNEL, this.channel);
		return nbt;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		updateConnections();
	}

	public void updateConnections() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			BlockPos sidedPos = pos.offset(facing);
			if (world.isBlockLoaded(sidedPos)) {
				world.neighborChanged(sidedPos, Blocks.AIR, pos);
			}
		}
		if (world.isBlockLoaded(pos)) {
			world.neighborChanged(pos, Blocks.AIR, pos);
		}
	}

	public Vec3i getConnections() {
		return new Vec3i(this.connection.getCode(), this.anchors.getCode(), this.connection.getCode() << 6
				| this.anchors.getCode());
	}

	@Override
	public void setFacing(EnumFacing face) {
		super.setFacing(face);
		this.anchors = RotationList.ofFacings(this.getFacing());
		updateConnections();
	}

	@Override
	public boolean canUpdate() {
		return this.isSimulating();
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public void onBlockUpdate(Block block) {
		super.onBlockUpdate(block);
		if (!this.isRendering()) {
			RotationList newList = RotationList.EMPTY;
			EnumFacing[] facings = EnumFacing.VALUES;
			int facingsLength = facings.length;
			for (int i = 0; i < facingsLength; ++i) {
				EnumFacing dir = facings[i];
				BlockPos sidePos = this.pos.offset(dir);
				if (canConnect(sidePos)) {
					newList = newList.add(dir);
				}
			}
			if (this.connection.getCode() != newList.getCode()) {
				this.connection = newList.add(this.getFacing());
				this.getNetwork().updateTileEntityField(this, NBT_CONNECTION);
				this.getNetwork().updateTileEntityField(this, NBT_ANCHORS);
			}
		}
	}

	public boolean canConnect(BlockPos worldPos) {
		return this.getWorld().getBlockState(worldPos).getBlock() instanceof IGTDataNetObject
				|| this.getWorld().getTileEntity(worldPos) instanceof IGTDataNetObject;
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals(NBT_CONNECTION) || field.equals(NBT_ANCHORS)) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public void onLeftClick(EntityPlayer var1, Side var2) {
		// Empty because its unused but required by interface implementation
	}

	/**
	 * Get the computer cube associated with this tile.
	 * 
	 * @return GTTileComputer - the computer or null depending on the status
	 */
	@Nullable
	public GTTileComputerCube getComputer() {
		return this.computer;
	}

	/**
	 * Set the computer cube associated with this tile.
	 * 
	 * @param GTTileComputer - the computer to set, or can be null if you want to
	 *                       remove the linked computer
	 */
	public void setComputer(@Nullable GTTileComputerCube computer) {
		this.computer = computer;
	}

	/**
	 * Get the channel set for this node
	 * 
	 * @return int - the channel
	 */
	public int getChannel() {
		return this.channel;
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand var2, EnumFacing var3, Side var4) {
		if (player.isSneaking()) {
			this.channel++;
			this.channel = this.channel > 15 ? 0 : this.channel;
			if (this.isSimulating()) {
				IC2.platform.messagePlayer(player, "Channel: " + this.channel);
			}
			player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.5F, 1.0F);
			return true;
		}
		return false;
	}

	@Override
	public int getCost() {
		return 1;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Node Facing: " + this.getFacing().toString().toUpperCase(), false);
		if (this.computer != null && this.computer.hasDataNetwork()) {
			data.put("Connected to network", false);
		} else {
			data.put("No network found or network is not powered", false);
		}
		data.put("Channel: " + this.channel, false);
	}
}
