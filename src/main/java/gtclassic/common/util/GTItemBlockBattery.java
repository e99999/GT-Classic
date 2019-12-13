package gtclassic.common.util;

import gtclassic.GTMod;
import gtclassic.api.itemblock.GTItemBlockRare;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class GTItemBlockBattery extends GTItemBlockRare implements IDamagelessElectricItem {

	public int maxCharge;
	public int transferLimit;
	public int tier;

	public GTItemBlockBattery(Block block) {
		super(block);
		maxCharge = 1000000;
		transferLimit = 128;
		tier = 2;
		this.setCreativeTab(GTMod.creativeTabGT);
		this.setNoRepair();
		this.setHasSubtypes(true);
	}

	@Override
	public boolean canProvideEnergy(ItemStack var1) {
		return true;
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
