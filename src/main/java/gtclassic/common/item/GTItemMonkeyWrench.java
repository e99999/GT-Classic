package gtclassic.common.item;

import gtclassic.api.interfaces.IGTMonkeyWrenchItem;
import gtclassic.api.pipe.GTTilePipeItemBase;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemMonkeyWrench extends GTItemComponent implements IGTMonkeyWrenchItem {

	public GTItemMonkeyWrench() {
		super("monkey_wrench", 5, 2);
		this.setMaxDamage(127);
		this.setNoRepair();
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		if (player.isSneaking()) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof GTTilePipeItemBase) {
				GTTilePipeItemBase pipe = (GTTilePipeItemBase) tileEntity;
				pipe.toggleRestrict();
				if (IC2.platform.isSimulating()) {
					IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
					String msg = pipe.restrict ? "Will only flow into other pipes" : "Flow unrestricted";
					IC2.platform.messagePlayer(player, msg);
				}
				player.getHeldItem(hand).damageItem(1, player);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public boolean isMonkeyWrench() {
		return true;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public void onUse(EntityPlayer player) {
		//TODO check for off hand
		player.getHeldItemMainhand().damageItem(1, player);
	}
}