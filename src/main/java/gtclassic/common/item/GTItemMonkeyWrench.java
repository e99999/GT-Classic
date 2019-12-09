package gtclassic.common.item;

import java.util.List;

import gtclassic.api.interfaces.IGTMonkeyWrenchTile;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemMonkeyWrench extends GTItemComponent {

	public GTItemMonkeyWrench() {
		super("monkey_wrench", 5, 2);
		this.setMaxDamage(127);
		this.setNoRepair();
		this.maxStackSize = 1;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// empty atm
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof IGTMonkeyWrenchTile) {
			if (((IGTMonkeyWrenchTile) tileEntity).onMonkeyWrench(player, world, pos, side, hand)) {
				if (IC2.platform.isSimulating()) {
					IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
				}
				player.getHeldItem(hand).damageItem(1, player);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	public void onUse(EntityPlayer player) {
		if (doesPlayerHaveMonkeyWrench(player)) {
			player.getHeldItemMainhand().damageItem(1, player);
		}
	}

	/** Do not call any methods below, they will probably be moved **/
	/**
	 * To check if a player is using a monkey wrench in their main hand
	 * 
	 * @param player - Player to check
	 * @return - returns true if player has monkey wrench in their main hand
	 */
	public static boolean doesPlayerHaveMonkeyWrench(EntityPlayer player) {
		return player.getHeldItemMainhand().getItem() instanceof GTItemMonkeyWrench;
	}

	/** public static helper to use a monkey wrench **/
	public static void useMonkeyWrench(EntityPlayer player) {
		Item itemMain = player.getHeldItemMainhand().getItem();
		if (itemMain instanceof GTItemMonkeyWrench) {
			((GTItemMonkeyWrench) itemMain).onUse(player);
		}
	}
}