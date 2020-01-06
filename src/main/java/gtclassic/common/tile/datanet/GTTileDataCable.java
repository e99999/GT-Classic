package gtclassic.common.tile.datanet;

import gtclassic.api.interfaces.IGTDataNetBlock;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class GTTileDataCable extends TileEntityBlock {

	@NetworkField(index = 8)
	public RotationList connection;

	public GTTileDataCable() {
		this.connection = RotationList.EMPTY;
		this.addNetworkFields(new String[] { "connection" });
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
		return new Vec3i(this.connection.getCode(), 0, 0);
	}

	@Override
	public boolean canUpdate() {
		return this.isSimulating();
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
				this.connection = newList;
				this.getNetwork().updateTileEntityField(this, "connection");
			}
		}
	}

	public boolean canConnect(BlockPos worldPos) {
		return this.getWorld().getBlockState(worldPos).getBlock() instanceof IGTDataNetBlock || this.getWorld().getTileEntity(worldPos) instanceof IGTDataNetBlock;
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals("connection")) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}
}
