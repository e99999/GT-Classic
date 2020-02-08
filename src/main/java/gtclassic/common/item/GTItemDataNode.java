package gtclassic.common.item;

import gtclassic.common.GTBlocks;
import gtclassic.common.block.GTBlockDataNode;
import gtclassic.common.tile.datanet.GTTileBaseDataNode;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemDataNode extends GTItemComponent {

	GTBlockDataNode node;

	public GTItemDataNode(GTBlockDataNode node, int x, int y) {
		super(node.getShortName() + "cover", x, y);
		this.node = node;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (block == GTBlocks.dataCable) {
			worldIn.setBlockToAir(pos);
			worldIn.removeTileEntity(pos);
			worldIn.setBlockState(pos, this.node.getDefaultState());
			block.onBlockPlacedBy(worldIn, pos, state, player, ItemStack.EMPTY);
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof GTTileBaseDataNode) {
				((GTTileBaseDataNode) tile).setDrop(new ItemStack(this));// ???
			}
			if (IC2.platform.isSimulating()) {
				IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
