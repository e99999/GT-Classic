package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.GTItemBlockBattery;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.item.ElectricItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class GTTileBattery extends TileEntityBlock implements IItemContainer, IGTDebuggableTile {

	private ItemStack drop = ItemStack.EMPTY;
	@NetworkField(index = 3)
	public int energy;
	public int tier = 2;
	public int maxEnergy = 1000000;
	public int maxInput = 128;
	public int output = 128;
	public boolean addedToEnergyNet;

	public GTTileBattery() {
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.drop = new ItemStack(nbt.getCompoundTag("drop"));
		this.energy = nbt.getInteger("energy");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("drop", drop.writeToNBT(new NBTTagCompound()));
		nbt.setInteger("energy", this.energy);
		return nbt;
	}

	@Override
	public void onLoaded() {
		updateActive();
		super.onLoaded();
	}

	public ItemStack getItem() {
		return this.drop;
	}

	public void setItem(ItemStack item) {
		this.drop = item.copy();
		this.energy = (int) ElectricItem.manager.getCharge(this.drop);
	}

	public void updateActive() {
		if (this.drop.getItem() instanceof GTItemBlockBattery && ElectricItem.manager.getCharge(this.drop) > 1000) {
			this.setActive(true);
		} else {
			this.setActive(false);
		}
	}

	@Override
	public List<ItemStack> getDrops() {
		ArrayList<ItemStack> drops = new ArrayList<>();
		ItemStack newDrop = drop.copy();
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(newDrop);
		if (nbt.hasKey("charge") && this.energy > 0) {
			nbt.setInteger("charge", this.energy);
		}
		drops.add(newDrop);
		return drops;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Stored Energy: " + this.energy, false);
	}
}
