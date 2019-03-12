package gtclassic.itemblock;

import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.tile.GTTileResinChunk;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemBlockResinBottle extends GTItemBlockRare {

	public GTItemBlockResinBottle(Block block) {
		super(block);
		this.setCreativeTab(GTMod.creativeTabGT);
		this.setMaxStackSize(1);
		this.setMaxDamage(7);
		this.setHasSubtypes(false);
		this.setNoRepair();
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// TODO this shit
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking()) {
			super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
			return EnumActionResult.PASS;
		} else {
			if (!block.isReplaceable(worldIn, pos)) {
				pos = pos.offset(facing);
			}
			ItemStack itemstack = player.getHeldItem(hand);
			if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack)
					&& worldIn.mayPlace(this.block, pos, false, facing, (Entity) null)) {
				if (this.placeBlockAt(itemstack, player, worldIn, pos, EnumFacing.SOUTH, hitX, hitY, hitZ,
						GTBlocks.resinChunk.getDefaultState())) {
					// TODO clean up this stupid boolean crap
				}
				player.getHeldItem(hand).damageItem(1, player);
				worldIn.playSound(player, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
				TileEntity tile = worldIn.getTileEntity(pos);
				if (tile instanceof GTTileResinChunk) {
					TileEntityBlock block = (TileEntityBlock) tile;
					block.setFacing(EnumFacing.SOUTH);
				}
				return EnumActionResult.SUCCESS;

			} else {
				return EnumActionResult.FAIL;
			}
		}
	}

}
