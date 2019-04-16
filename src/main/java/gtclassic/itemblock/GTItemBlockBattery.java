package gtclassic.itemblock;

import gtclassic.GTMod;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemBlockBattery extends GTItemBlockRare implements IDamagelessElectricItem {

	public int maxCharge;
	public int transferLimit;
	public int tier;

	public GTItemBlockBattery(Block block) {
		super(block);
		if (block instanceof GTBlockBattery) {
			GTBlockBattery battery = (GTBlockBattery) block;
			maxCharge = battery.max;
			transferLimit = battery.trans;
			tier = battery.tier;
		}
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
		return this.shouldBeStackable(stack) ? 16 : 1;
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return !this.shouldBeStackable(stack);
	}

	private boolean shouldBeStackable(ItemStack stack) {
		return ElectricItem.manager.getCharge(stack) == 0.0D;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D - ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 20000;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking()) {
			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		} else {
			player.setActiveHand(hand);
			return EnumActionResult.SUCCESS;
		}
	}

	@Override
	public void onUsingTick(ItemStack itemStackIn, EntityLivingBase entity, int count) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			EnumHand playerHand = player.getActiveHand();
			boolean charged = false;
			int i;
			ItemStack stack;
			IElectricItem item;
			double transfer;
			if (playerHand == EnumHand.OFF_HAND) {
				for (i = 0; i < 9; ++i) {
					stack = player.inventory.getStackInSlot(i);
					if (stack.getItem() instanceof IElectricItem) {
						item = (IElectricItem) stack.getItem();
						transfer = ElectricItem.manager.discharge(itemStackIn, (double) (2 * this.transferLimit),
								item.getTier(itemStackIn), true, false, true);
						transfer = ElectricItem.manager.charge(stack, transfer, this.tier, true, false);
						ElectricItem.manager.discharge(itemStackIn, transfer, item.getTier(itemStackIn), true, false,
								false);
						if (transfer == 0.0D) {
							break;
						}

						charged = true;
					}
				}
			} else {
				for (i = 0; i < 9; ++i) {
					if (i != player.inventory.currentItem) {
						stack = player.inventory.getStackInSlot(i);
						if (stack.getItem() instanceof IElectricItem) {
							item = (IElectricItem) stack.getItem();
							transfer = ElectricItem.manager.discharge(itemStackIn, (double) (2 * this.transferLimit),
									item.getTier(itemStackIn), true, false, true);
							transfer = ElectricItem.manager.charge(stack, transfer, this.tier, true, false);
							ElectricItem.manager.discharge(itemStackIn, transfer, item.getTier(itemStackIn), true,
									false, false);
							if (transfer == 0.0D) {
								break;
							}

							charged = true;
						}
					}
				}

				if (!charged) {
					stack = player.getHeldItem(EnumHand.OFF_HAND);
					if (stack.getItem() instanceof IElectricItem) {
						item = (IElectricItem) stack.getItem();
						transfer = ElectricItem.manager.discharge(itemStackIn, (double) (2 * this.transferLimit),
								item.getTier(itemStackIn), true, false, true);
						transfer = ElectricItem.manager.charge(stack, transfer, this.tier, true, false);
						ElectricItem.manager.discharge(itemStackIn, transfer, item.getTier(itemStackIn), true, false,
								false);
						if (transfer > 0.0D) {
							charged = true;
						}
					}
				}
			}

			if (!charged) {
				player.resetActiveHand();
			}

			IC2.platform.updatePlayerUsingItem(player, itemStackIn);
		}
	}

}
