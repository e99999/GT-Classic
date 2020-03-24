package gtclassic.common.tile;

import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerAESU;
import ic2.api.energy.EnergyNet;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.obj.IClickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;

public class GTTileAESU extends TileEntityElectricBlock implements IClickable {

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
		return GTLang.AESU;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if (amount <= 0.0D) {
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

	@Override
	public void onNetworkEvent(EntityPlayer player, int event) {
		super.onNetworkEvent(player, event);
		if (event == 4) {
			this.output = GTHelperMath.clip(this.output + 64, 0, 2048);
			updateGui();
		}
		if (event == 3) {
			this.output = GTHelperMath.clip(this.output + 1, 0, 2048);
			updateGui();
		}
		if (event == 2) {
			this.output = GTHelperMath.clip(this.output - 1, 0, 2048);
			updateGui();
		}
		if (event == 1) {
			this.output = GTHelperMath.clip(this.output - 64, 0, 2048);
			updateGui();
		}
	}

	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, "output");
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public void onLeftClick(EntityPlayer var1, Side var2) {
		// Needed for interface
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing facing, Side side) {
		if (player.isSneaking() && player.getHeldItemMainhand().isEmpty()) {
			this.onNetworkEvent(player, 0);
			if (this.isSimulating()) {
				IC2.platform.messagePlayer(player, this.getRedstoneMode());
			}
			player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0F, 1.0F);
			return true;
		}
		return false;
	}
}
