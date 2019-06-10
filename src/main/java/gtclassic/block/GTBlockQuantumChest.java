package gtclassic.block;

import gtclassic.tile.GTTileQuantumChest;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTBlockQuantumChest extends GTBlockTile {

	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;

	public GTBlockQuantumChest() {
		super("quantumchest");
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileQuantumChest();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileQuantumChest) {
			GTTileQuantumChest chest = (GTTileQuantumChest) tile;
			ItemStack playerStack = playerIn.getHeldItemMainhand();
			boolean isChestEmpty = chest.isSlotEmpty(slotInput) && chest.isSlotEmpty(slotDisplay);
			boolean isPlayerStackValid = chest.isSlotEmpty(slotInput)
					&& StackUtil.isStackEqual(chest.getStackInSlot(slotDisplay), playerStack, false, false);
			if (facing != EnumFacing.UP) {
				if (!playerIn.isSneaking() && isChestEmpty || isPlayerStackValid) {
					if (playerStack.getCount() > 0) {
						chest.setStackInSlot(slotInput, playerStack.copy());
						playerStack.shrink(playerStack.getCount());
						chest.updateGUI();
						return true;
					}
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
}
