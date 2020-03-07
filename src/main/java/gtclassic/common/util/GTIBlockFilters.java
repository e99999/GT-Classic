package gtclassic.common.util;

import gtclassic.common.GTBlocks;
import gtclassic.common.tile.multi.GTTileMultiLESU;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTIBlockFilters {

	public static class BasicBlockFilter implements AabbUtil.IBlockFilter {

		Block block;

		public BasicBlockFilter(Block block) {
			this.block = block;
		}

		@Override
		public boolean isValidBlock(IBlockState state) {
			return state.equals(this.block.getDefaultState());
		}

		@Override
		public boolean isValidBlock(World world, BlockPos blockPos) {
			Block worldBlock = world.getBlockState(blockPos).getBlock();
			return worldBlock == this.block;
		}

		@Override
		public boolean forceChunkLoad() {
			return false;
		}
	}

	public static class LESUCasingFilter implements AabbUtil.IBlockFilter {

		TileEntity tile;

		public LESUCasingFilter(TileEntity tile) {
			this.tile = tile;
		}

		@Override
		public boolean isValidBlock(IBlockState state) {
			return state.equals(GTBlocks.casingLapotron.getDefaultState());
		}

		@Override
		public boolean isValidBlock(World world, BlockPos blockPos) {
			Block block = world.getBlockState(blockPos).getBlock();
			if (block == GTBlocks.tileLESU) {
				if (blockPos.equals(this.tile.getPos())) {
					return false;
				}
				TileEntity worldTile = world.getTileEntity(blockPos);
				if (worldTile instanceof GTTileMultiLESU) {
					((GTTileMultiLESU) worldTile).enabled = false;
				}
				return false;
			}
			return block == GTBlocks.casingLapotron;
		}

		@Override
		public boolean forceChunkLoad() {
			return false;
		}
	}

	public static class LogFilter implements AabbUtil.IBlockFilter {

		@SuppressWarnings("deprecation")
		@Override
		public boolean isValidBlock(IBlockState state) {
			return state.getBlock().getMaterial(state) == Material.WOOD;
		}

		@Override
		public boolean isValidBlock(World world, BlockPos blockPos) {
			return world.getBlockState(blockPos).getBlock().isWood(world, blockPos);
		}

		@Override
		public boolean forceChunkLoad() {
			return false;
		}
	}

	public static class EndPortalFilter implements AabbUtil.IBlockFilter {

		@Override
		public boolean forceChunkLoad() {
			return false;
		}

		@Override
		public boolean isValidBlock(IBlockState state) {
			return state.getBlock() == Blocks.END_PORTAL || state.getBlock() == Blocks.END_PORTAL_FRAME;
		}

		@Override
		public boolean isValidBlock(World world, BlockPos pos) {
			return world.getBlockState(pos).getBlock() == Blocks.END_PORTAL
					|| world.getBlockState(pos).getBlock() == Blocks.END_PORTAL_FRAME;
		}
	}
}
