package gtclassic.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.tile.GTTileQuantumChest;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.tool.ItemToolWrench;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTBlockQuantumChest extends GTBlockTile {

	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;
	String display = "display";
	String count = "digitalCount";

	public GTBlockQuantumChest() {
		super("quantumchest");
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileQuantumChest();
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt;
			nbt = StackUtil.getNbtData(stack);
			if (nbt.hasKey(display) && nbt.hasKey(count)) {
				tooltip.add(TextFormatting.AQUA + I18n.format((nbt.getInteger(count) - 64) + " of "
						+ StackUtil.copyWithSize(new ItemStack(nbt.getCompoundTag(display)), 1).getDisplayName()));
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<ItemStack>();
		if (te instanceof IItemContainer) {
			items.addAll(((IItemContainer) te).getDrops());
		}
		return items;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileQuantumChest) {
			GTTileQuantumChest chest = (GTTileQuantumChest) tile;
			ItemStack playerStack = playerIn.getHeldItemMainhand();
			if (facing != EnumFacing.UP && !(playerStack.getItem() instanceof ItemToolWrench)) {
				boolean isChestEmpty = chest.isSlotEmpty(slotInput) && chest.isSlotEmpty(slotDisplay);
				boolean isPlayerStackValid = chest.isSlotEmpty(slotInput) && StackUtil.isStackEqual(chest.getStackInSlot(slotDisplay), playerStack, false, false);
				boolean validInput = isChestEmpty || isPlayerStackValid;
				if (!playerIn.isSneaking() && validInput && !playerStack.isEmpty()) {
					chest.setStackInSlot(slotInput, playerStack.copy());
					playerStack.shrink(playerStack.getCount());
					chest.updateGUI();
					return true;
				}
				if (playerIn.isSneaking() && !chest.isSlotEmpty(slotOutput)) {
					ItemHandlerHelper.giveItemToPlayer(playerIn, chest.getStackInSlot(slotOutput));
					chest.setStackInSlot(slotOutput, ItemStack.EMPTY);
					return true;
				}
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt;
		if (tile instanceof GTTileQuantumChest) {
			GTTileQuantumChest chest = (GTTileQuantumChest) tile;
			if (stack.hasTagCompound()) {
				nbt = StackUtil.getNbtData(stack);
				if (nbt.hasKey(display) && nbt.hasKey(count)) {
					chest.setDigtialCount(nbt.getInteger(count));
					chest.setStackInSlot(slotDisplay, StackUtil.copyWithSize(new ItemStack(nbt.getCompoundTag(display)), 1));
				}
			}
		}
	}
}
