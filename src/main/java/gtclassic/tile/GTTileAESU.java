package gtclassic.tile;

import gtclassic.container.GTContainerAESU;
import ic2.api.energy.EnergyNet;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class GTTileAESU extends TileEntityElectricBlock {
	
	

	public GTTileAESU() {
		super(4, (int) EnergyNet.instance.getPowerFromTier(4), 100000000);
		this.output = 2048;
		this.addGuiFields(new String[] { "output" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.output = nbt.getInteger("output");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("output", this.output);
		return nbt;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerAESU(player.inventory, this);
	}

	@Override
	public int getProcessRate() {
		return 128;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public void onNetworkEvent(EntityPlayer var1, int event) {
		if (event == 4) {
			if (this.output + 64 <= 2048) {
				this.output = this.output + 64;
				updateGui();
			}
		}
		if (event == 3) {
			if (this.output + 1 <= 2048) {
				this.output = this.output + 1;
				updateGui();
			}
		}
		if (event == 2) {
			if (this.output - 1 >= 0) {
				this.output = this.output - 1;
				updateGui();
			}
		}
		if (event == 1) {
			if (this.output - 64 >= 0) {
				this.output = this.output - 64;
				updateGui();
			}
		}
	}
	
	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, "output");
	}
}
