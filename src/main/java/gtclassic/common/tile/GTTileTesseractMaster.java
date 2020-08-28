package gtclassic.common.tile;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.block.base.tile.TileEntityElecMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class GTTileTesseractMaster extends TileEntityElecMachine implements ITickable, IGTDebuggableTile {

	TileEntity tesseractTile;
	private int redstoneLevel = -1;

	public GTTileTesseractMaster() {
		super(0, 128);
		this.maxEnergy = 10000;
	}

	public BlockPos getInventoryPos() {
		return this.pos.offset(this.getFacing().getOpposite());
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
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public void update() {
		this.handleEnergy();
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

	private void handleEnergy() {
		if (this.energy >= 64 && tesseractTile != null && this.redstoneLevel < 1) {
			this.setActive(true);
			this.useEnergy(64);
		} else {
			this.setActive(false);
		}
	}

	public void updateTile() {
		tesseractTile = world.getTileEntity(getInventoryPos());
		if (tesseractTile instanceof GTTileBlockExtender || tesseractTile instanceof GTTileTesseractSlave) {
			tesseractTile = null;
		}
		this.redstoneLevel = world.getRedstonePower(this.pos.offset(this.getFacing()), this.getFacing());
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		String status = this.energy >= 128 && this.tesseractTile != null ? "Tesseract is being generated"
				: "Not enough power to generate tesseract or missing tile";
		data.put(status, true);
		data.put("Redstone Level: " + this.redstoneLevel, true);
	}
}
