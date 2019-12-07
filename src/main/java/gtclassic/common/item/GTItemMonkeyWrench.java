package gtclassic.common.item;

import java.util.List;

import gtclassic.api.interfaces.IGTMonkeyWrenchItem;
import gtclassic.api.pipe.GTTilePipeBase;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemMonkeyWrench extends GTItemComponent implements IGTMonkeyWrenchItem {

	static String[] modes = { "Inspection", "Restriction", "Connections", "Directions", "Toggle" };

	public GTItemMonkeyWrench() {
		super("monkey_wrench", 5, 2);
		this.setMaxDamage(127);
		this.setNoRepair();
		this.maxStackSize = 1;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt;
		nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("mode")) {
			tooltip.add(I18n.format("Mode: " + nbt.getInteger("mode") + " - " + modes[nbt.getInteger("mode")]));
		}
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.isSneaking()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(playerIn.getHeldItemMainhand());
			int mode = nbt.getInteger("mode");
			int result = mode + 1;
			if (result > 4) {
				result = 0;
			}
			nbt.setInteger("mode", result);
			if (IC2.platform.isSimulating()) {
				IC2.audioManager.playOnce(playerIn, Ic2Sounds.wrenchUse);
				IC2.platform.messagePlayer(playerIn, "Mode: " + nbt.getInteger("mode") + " - "
						+ modes[nbt.getInteger("mode")]);
			}
			return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);
		// we skip 0 because its used for inspection mode handled in the tiles/container
		// mode 1 is restriction mode
		if (tileEntity instanceof GTTilePipeBase && !player.isSneaking()) {
			GTTilePipeBase pipe = (GTTilePipeBase) tileEntity;
			if (getMode(player) == 1) {
				return doRestrictionThings(pipe, player, world, pos, side, hitX, hitY, hitZ, hand);
			}
			if (getMode(player) == 2) {
				return doConnectionThings(pipe, player, world, pos, side, hitX, hitY, hitZ, hand);
			}
			if (getMode(player) == 4) {
				return doToggleThings(pipe, player, world, pos, side, hitX, hitY, hitZ, hand);
			}
		}
		return EnumActionResult.PASS;
	}

	public static EnumActionResult doRestrictionThings(GTTilePipeBase pipe, EntityPlayer player, World world,
			BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		pipe.toggleRestrict();
		if (IC2.platform.isSimulating()) {
			IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			String msg = pipe.restrict ? "Will only flow into other pipes" : "Will flow into anything";
			IC2.platform.messagePlayer(player, msg);
			player.getHeldItem(hand).damageItem(1, player);
		}
		return EnumActionResult.SUCCESS;
	}

	public static EnumActionResult doConnectionThings(GTTilePipeBase pipe, EntityPlayer player, World world,
			BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		pipe.pipemode = !pipe.pipemode;
		pipe.onBlockUpdate(world.getBlockState(pos).getBlock());
		if (IC2.platform.isSimulating()) {
			IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			String msg = pipe.pipemode ? "Will only connect to other pipes" : "Will connect to anything";
			IC2.platform.messagePlayer(player, msg);
			player.getHeldItem(hand).damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.SUCCESS;
	}

	public static EnumActionResult doToggleThings(GTTilePipeBase pipe, EntityPlayer player, World world, BlockPos pos,
			EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		pipe.toggleConnection(side);
		pipe.onBlockUpdate(world.getBlockState(pos).getBlock());
		return EnumActionResult.SUCCESS;
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
		if (doesPlayerHaveMonkeyWrench(player)) {
			player.getHeldItemMainhand().damageItem(1, player);
		}
	}

	/** Do not call any methods below, they will probably be moved **/
	/** public static helper for getting the mode of a monkeyWrench **/
	public static int getMode(EntityPlayer player) {
		if (doesPlayerHaveMonkeyWrench(player)) {
			NBTTagCompound nbt = StackUtil.getNbtData(player.getHeldItemMainhand());
			if (nbt.hasKey("mode")) {
				return nbt.getInteger("mode");
			}
		}
		return -1;
	}

	/**
	 * To check if a player is using a monkey wrench in their main hand
	 * 
	 * @param player - Player to check
	 * @return - returns true if player has monkey wrench in their main hand
	 */
	public static boolean doesPlayerHaveMonkeyWrench(EntityPlayer player) {
		return player.getHeldItemMainhand().getItem() instanceof IGTMonkeyWrenchItem;
	}

	/** public static helper to use a monkey wrench **/
	public static void useMonkeyWrench(EntityPlayer player) {
		Item itemMain = player.getHeldItemMainhand().getItem();
		if (itemMain instanceof IGTMonkeyWrenchItem) {
			((IGTMonkeyWrenchItem) itemMain).onUse(player);
		}
	}
}