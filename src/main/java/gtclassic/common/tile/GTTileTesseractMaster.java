package gtclassic.common.tile;

import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.core.block.base.tile.TileEntityElecMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class GTTileTesseractMaster extends TileEntityElecMachine implements ITickable, IGTDebuggableTile {

	public GTTileTesseractMaster() {
		super(0, 128);
		this.maxEnergy = 10000;
	}

	public BlockPos getInventoryPos() {
		return this.pos.offset(this.getFacing().getOpposite());
	}

	public EnumFacing getInventorySide() {
		return this.getFacing();
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

	private void handleEnergy() {
		if (this.energy >= 128) {
			this.setActive(true);
			this.useEnergy(128);
		} else {
			this.setActive(false);
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		String status = this.energy >= 128 ? "Tesseract is being generated" : "Not enough power to generate tesseract";
		data.put(status, true);
	}
}
