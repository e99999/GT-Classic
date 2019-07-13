package gtclassic.tile;

import gtclassic.container.GTContainerLESU;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class GTTileLESU extends TileEntityElectricBlock {
	
	private int blockCount;

	public GTTileLESU() {
		super(3, 512, 10000000);
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
			//count blocks
			setActive(this.blockCount > 0);
		}
	}
	
	
	public int getBlockCount() {
		return this.blockCount;
	}
}
