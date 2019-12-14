package gtclassic.common.tile;

import ic2.api.classic.energy.tile.IAnchorConductor;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.energy.tile.IMetaDelegate;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.wiring.tile.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.common.MinecraftForge;

public class GTTileSuperconductorCable extends TileEntityBlock implements IEnergyConductor {

	@NetworkField(index = 8)
	public RotationList connection;
	protected boolean addedToEnergyNet;

	public GTTileSuperconductorCable() {
		this.connection = RotationList.EMPTY;
		this.addNetworkFields(new String[] { "connection" });
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		updateConnections();
		if (!this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnergyNet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.addedToEnergyNet && this.isSimulating()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnergyNet = false;
		}
		super.onUnloaded();
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return this.connection.contains(side);
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return this.connection.contains(side);
	}

	@Override
	public double getConductionLoss() {
		return 0.001D;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 134217728.0D;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 134217728.0D;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 134217728.0D;
	}

	@Override
	public void removeConductor() {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeInsulation() {
		// TODO Auto-generated method stub
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
				IEnergyTile tile = EnergyNet.instance.getSubTile(this.getWorld(), this.getPos().offset(dir));
				if (tile == null || !(tile instanceof IEnergyAcceptor) && !(tile instanceof IEnergyEmitter)) {
					tile = EnergyNet.instance.getTile(this.getWorld(), this.getPos().offset(dir));
				}
				if (tile != null && this.canConnect(tile, dir)) {
					newList = newList.add(dir);
				}
			}
			if (this.connection.getCode() != newList.getCode()) {
				this.connection = newList;
				this.getNetwork().updateTileEntityField(this, "connection");
			}
		}
	}

	public boolean canConnect(IEnergyTile tile, EnumFacing side) {
		if (tile instanceof TileEntityCable) {
			return true;
		}else if (tile instanceof GTTileSuperconductorCable) {
			return true;
		} else if (tile instanceof IAnchorConductor && ((IAnchorConductor) tile).hasAnchor(side.getOpposite())) {
			return false;
		} else if (tile instanceof IEnergyAcceptor && !(tile instanceof IEnergyEmitter)) {
			return ((IEnergyAcceptor) tile).acceptsEnergyFrom(this, side.getOpposite());
		} else if (tile instanceof IEnergyEmitter && !(tile instanceof IEnergyAcceptor)) {
			return ((IEnergyEmitter) tile).emitsEnergyTo(this, side.getOpposite());
		} else if (tile instanceof IEnergyAcceptor && tile instanceof IEnergyEmitter) {
			return ((IEnergyEmitter) tile).emitsEnergyTo(this, side.getOpposite())
					|| ((IEnergyAcceptor) tile).acceptsEnergyFrom(this, side.getOpposite());
		} else {
			return tile instanceof IMetaDelegate || tile instanceof INetworkTileEntityEventListener;
		}
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals("connection")) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}
}
