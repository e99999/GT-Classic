package gtclassic.tile;

import java.util.UUID;

import gtclassic.container.GTContainerIDSU;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.energy.EnergyNet;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class GTTileIDSU extends TileEntityElectricBlock {

	@NetworkField(index = 7)
	private UUID owner;

	public GTTileIDSU() {
		super(4, (int) EnergyNet.instance.getPowerFromTier(4), 400000000);
		this.addNetworkFields(new String[] { "owner" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasUniqueId("owner")) {
			this.owner = nbt.getUniqueId("owner");
			this.getNetwork().updateTileGuiField(this, "owner");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.owner != null) {
			nbt.setUniqueId("owner", this.owner);
		}
		return nbt;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerIDSU(player.inventory, this);
	}

	@Override
	public int getProcessRate() {
		return 128;
	}

	@Override
	public double getWrenchDropRate() {
		return 0.70D;
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}
	
	public void setOwner(UUID user) {
		if (this.owner == null && user != null) {
			this.owner = user;
		}
		this.getNetwork().updateTileGuiField(this, "owner");
	}

	public UUID getOwner() {
		return this.owner;
	}
}
