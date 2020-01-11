package gtclassic.common.util.modeltest;

import java.util.Map;

import gtclassic.api.interfaces.IGTDataNetObject;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class GTTileBaseDataNode extends TileEntityMachine implements IGTDebuggableTile {

	@NetworkField(index = 8)
	public RotationList connection;
	@NetworkField(index = 9)
	public RotationList anchors;
	private static final String NBT_CONNECTION = "connection";
	private static final String NBT_ANCHORS = "anchors";

	public GTTileBaseDataNode() {
		super(0);
		this.connection = RotationList.EMPTY;
		this.anchors = RotationList.EMPTY;
		this.addNetworkFields(new String[] { NBT_CONNECTION, NBT_ANCHORS });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.anchors = RotationList.ofFacings(EnumFacing.getFront(nbt.getByte("Facing")));
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
		return this.getFacing() != facing;
	}

	@Override
	public void onBlockUpdate(Block block) {
		super.onBlockUpdate(block);
		if (!this.isRendering()) {
			RotationList newList = RotationList.EMPTY;
			EnumFacing[] var3 = EnumFacing.VALUES;
			int var4 = var3.length;
			for (int var5 = 0; var5 < var4; ++var5) {
				EnumFacing dir = var3[var5];
				BlockPos worldPos = this.pos.offset(dir);
				if (canConnect(worldPos)) {
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
	public void getData(Map<String, Boolean> data) {
		data.put("Facing: " + this.getFacing(), false);
	}
}
