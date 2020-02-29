package gtclassic.common.tile;

import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class GTTileBlockExtender extends TileEntityMachine {

	TileEntity cacheTile;

	public GTTileBlockExtender() {
		super(0);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return this.getFacing() != facing;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return cacheTile != null ? cacheTile.hasCapability(capability, this.getFacing().getOpposite())
				: super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return cacheTile != null ? cacheTile.getCapability(capability, this.getFacing().getOpposite())
				: super.getCapability(capability, facing);
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		this.updateTile();
	}

	@Override
	public void onBlockUpdate(Block block) {
		this.updateTile();
	}

	public void updateTile() {
		cacheTile = world.getTileEntity(pos.offset(this.getFacing().getOpposite()));
		if (cacheTile instanceof GTTileBlockExtender) {
			cacheTile = null;
		}
	}
}
