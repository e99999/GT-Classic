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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class GTTileLESU extends TileEntityElectricBlock {

	private int blockCount;
	public boolean enabled = true;
	public List<BlockPos> lapotronBlockPos;
	public static final int BASE_ENERGY = 10000000;
	public static AabbUtil.IBlockFilter filter = new GTLapotronBlockFilter();

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
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage)
	  {
	    if ((amount > 32) || (amount <= 0.0D)) {
	      return 0.0D;
	    }
	    energy = ((int)(energy + amount));
	    int left = 0;
	    if (energy >= maxEnergy)
	    {
	      left = energy - maxEnergy;
	      energy = maxEnergy;
	    }
	    getNetwork().updateTileGuiField(this, "energy");
	    return left;
	  }

	private void checkArea() {
		if (world.getTotalWorldTime() % 256 == 0 && enabled) {
			if (!world.isAreaLoaded(pos, 3))
				return;
			this.lapotronBlockPos = getLapotronBlocks();
			this.blockCount = this.lapotronBlockPos.size();
			if (this.blockCount > 256) {
				this.blockCount = 256;
			}
			this.maxEnergy = BASE_ENERGY + (this.blockCount * 750000);
			this.getNetwork().updateTileGuiField(this, "blockCount");
			this.getNetwork().updateTileGuiField(this, "maxEnergy");
			setActive(this.blockCount > 0);
		}
	}

	public int getCount() {
		return blockCount;
	}

	public List<BlockPos> getLapotronBlocks() {
		return AabbUtil.getTargets(world, this.pos, 256, filter, false, false, RotationList.ALL);
	}
}
