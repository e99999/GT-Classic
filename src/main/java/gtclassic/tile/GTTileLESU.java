package gtclassic.tile;

import gtclassic.container.GTContainerLESU;
import gtclassic.util.LESUFilter;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class GTTileLESU extends TileEntityElectricBlock {

	private int blockCount;
	public static final int BASE_ENERGY = 10000000;
	public static AabbUtil.IBlockFilter filter = new LESUFilter();

	public GTTileLESU() {
		super(3, 512, BASE_ENERGY);
		this.blockCount = 0;
		this.addGuiFields(new String[] { "blockcount" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.blockCount = nbt.getInteger("blockcount");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("blockcount", this.blockCount);
		return nbt;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerLESU(player.inventory, this);
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
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

	private void checkArea() {
		if (world.getTotalWorldTime() % 256 == 0) {
			this.blockCount = countBlocks();
			this.getNetwork().updateTileGuiField(this, "blockcount");
			this.maxEnergy = BASE_ENERGY + (this.blockCount * 750000);
			setActive(this.blockCount > 0);
		}
	}

	public int getCount() {
		return blockCount;
	}

	public int countBlocks() {
		return AabbUtil.getBlockCount(world, this.pos, 256, filter, false, false, RotationList.ALL);
	}
}
