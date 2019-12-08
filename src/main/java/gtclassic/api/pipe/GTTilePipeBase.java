package gtclassic.api.pipe;

import ic2.api.classic.network.adv.NetworkField;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class GTTilePipeBase extends TileEntityBlock {

	@NetworkField(index = 8)
	public RotationList connection;
	public EnumFacing lastIn;
	public int mode;
	public String [] info = {"Flow unrestricted","Will only flow into pipes", "Will only flow and connect into pipes"};
	

	public GTTilePipeBase() {
		this.mode = 0;
		this.connection = RotationList.EMPTY;
		this.addNetworkFields(new String[] { "connection" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.mode = nbt.getInteger("mode");
		if (nbt.getInteger("lastIn") != -1) {
			this.lastIn = EnumFacing.getFront(nbt.getInteger("lastIn"));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("mode", this.mode);
		nbt.setInteger("lastIn", lastIn != null ? this.lastIn.getIndex() : -1);
		return nbt;
	}

	public Vec3i getConnections() {
		return new Vec3i(this.connection.getCode(), 0, 0);
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

	@Override
	public void onBlockUpdate(Block block) {
		super.onBlockUpdate(block);
		if (!this.isRendering()) {
			RotationList newList = RotationList.EMPTY;
			EnumFacing[] var3 = EnumFacing.VALUES;
			int var4 = var3.length;
			for (int var5 = 0; var5 < var4; ++var5) {
				EnumFacing dir = var3[var5];
				TileEntity tile = this.getWorld().getTileEntity(this.getPos().offset(dir));
				if (canConnect(tile, dir)) {
					newList = newList.add(dir);
				}
			}
			if (this.connection.getCode() != newList.getCode()) {
				this.connection = newList;
				this.getNetwork().updateTileEntityField(this, "connection");
			}
		}
	}

	/** This is what you override for different types of pipes **/
	public boolean canConnect(TileEntity tile, EnumFacing dir) {
		if (tile == null) {
			return false;
		}
		return tile instanceof GTTilePipeBase;
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals("connection")) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public void toggleMode() {
		if (this.mode + 1 > 2) {
			this.mode = 0;
			return;
		}
		this.mode = this.mode + 1;
	}
}
