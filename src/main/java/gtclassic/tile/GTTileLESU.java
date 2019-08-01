package gtclassic.tile;

import java.util.List;

import gtclassic.container.GTContainerLESU;
import gtclassic.util.GTLang;
import gtclassic.util.GTLapotronBlockFilter;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.helpers.AabbUtil;
import ic2.core.util.helpers.AabbUtil.BoundingBox;
import ic2.core.util.helpers.AabbUtil.Processor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class GTTileLESU extends TileEntityElectricBlock {

	private int blockCount;
	public List<BlockPos> lapotronBlockPos;
	public static final int BASE_ENERGY = 10000000;
	public static AabbUtil.IBlockFilter filter = new GTLapotronBlockFilter();
	public Processor task = null;

	public GTTileLESU() {
		super(3, 512, BASE_ENERGY);
		this.blockCount = 0;
		this.addGuiFields(new String[] { "blockCount", "maxEnergy" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.blockCount = nbt.getInteger("blockCount");
		this.maxEnergy = nbt.getInteger("maxEnergy");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("blockCount", this.blockCount);
		nbt.setInteger("maxEnergy", this.maxEnergy);
		return nbt;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerLESU(player.inventory, this);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.LESU;
	}

	public int getProcessRate() {
		return 32;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public void update() {
		checkArea();
		super.update();
	}

	@Override
	public int getSinkTier() {
		return 1;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if ((amount > 32) || (amount <= 0.0D)) {
			return 0.0D;
		}
		energy = ((int) (energy + amount));
		int left = 0;
		if (energy >= maxEnergy) {
			left = energy - maxEnergy;
			energy = maxEnergy;
		}
		getNetwork().updateTileGuiField(this, "energy");
		return left;
	}

	private void checkArea() {
		if (task != null && world.isAreaLoaded(pos, 16)) {
			task.update();
			if (!task.isFinished()) {
				return;
			}
			this.lapotronBlockPos = task.getResults();
			this.blockCount = task.getResultCount();
			if (this.blockCount > 256) {
				this.blockCount = 256;
			}
			this.maxEnergy = BASE_ENERGY + (this.blockCount * 750000);
			this.getNetwork().updateTileGuiField(this, "blockCount");
			this.getNetwork().updateTileGuiField(this, "maxEnergy");
		}
		if (world.getTotalWorldTime() % 128 == 0) {
			if (!world.isAreaLoaded(pos, 16))
				return;
			task = AabbUtil.createBatchTask(world, new BoundingBox(this.pos, 256), this.pos, RotationList.ALL, filter, 64, false, false, false);
			task.update();
		}
	}

	public int getCount() {
		return blockCount;
	}
}
