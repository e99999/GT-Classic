package gtclassic.block;

import java.util.List;

import gtclassic.util.GTValues;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockBatteryBase extends ItemBlockRare implements IDamagelessElectricItem, ITexturedBlock {

	public int maxCharge;
	public int transferLimit;
	public int tier;
	public boolean provider;

	protected GTBlockBatteryBase(Block block) {
		super(block);
	}

	public GTBlockBatteryBase(Block block, int max, int trans, int tier) {
		super(block);
		this.maxCharge = max;
		this.tier = tier;
		this.transferLimit = trans;
		this.provider = true;
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public boolean canProvideEnergy(ItemStack var1) {
		return this.provider;
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
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D - ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return GTValues.FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures("gtclassic_blocks")[50];// placeholder/null texture
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public List<IBlockState> getValidStates() {
		return this.getValidStates();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromStack(stack);
	}

	// weird shit starts here

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 20000;
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

			}

			if (!charged) {
				player.resetActiveHand();
			}

			IC2.platform.updatePlayerUsingItem(player, itemStackIn);
		}
	}

}
