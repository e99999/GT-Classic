package gtclassic.common.item;

import java.awt.Color;

import gtclassic.api.item.GTItemBlock;
import gtclassic.common.block.GTBlockBattery;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class GTItemBlockBattery extends GTItemBlock implements IDamagelessElectricItem {

	public int maxCharge;
	public int transferLimit;
	public int tier;

	public GTItemBlockBattery(Block block) {
		super(block);
		if (block instanceof GTBlockBattery) {
			GTBlockBattery battery = (GTBlockBattery) block;
			maxCharge = battery.maxCharge;
			transferLimit = battery.transferLimit;
			tier = battery.tier;
		}
		this.setNoRepair();
		this.setHasSubtypes(true);
		this.setCreativeTab(block.getCreativeTab());
	}

	@Override
	public boolean canProvideEnergy(ItemStack var1) {
		return true;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	@Override
	public double getMaxCharge(ItemStack var1) {
		return (double) this.maxCharge;
	}

	@Override
	public int getTier(ItemStack var1) {
		return this.tier;
	}

	@Override
	public double getTransferLimit(ItemStack var1) {
		return (double) this.transferLimit;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			ItemStack empty = new ItemStack(this, 1, 0);
			ItemStack full = new ItemStack(this, 1, 0);
			ElectricItem.manager.discharge(empty, 2.147483647E9D, Integer.MAX_VALUE, true, false, false);
			ElectricItem.manager.charge(full, 2.147483647E9D, Integer.MAX_VALUE, true, false);
			items.add(empty);
			items.add(full);
		}
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D - ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
	}
}
