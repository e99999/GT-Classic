package gtclassic.common.tile;

import java.util.List;
import java.util.Map;
import java.util.Random;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.common.GTBlocks;
import gtclassic.common.util.GTLogBlockFilter;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileCharcoalPit extends TileEntityMachine implements ITickable, IGTDebuggableTile, IGTDisplayTickTile {

	public List<BlockPos> logPos;
	public static AabbUtil.IBlockFilter filter = new GTLogBlockFilter();

	public GTTileCharcoalPit() {
		super(0);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 100 == 0) {
			if (isLog(pos.down())) {
				logPos = getLogs();
			}
			boolean allSidesCovered = true;
			boolean hasLogs = logPos != null && !logPos.isEmpty();
			if (hasLogs) {
				for (BlockPos tPos : logPos) {
					for (EnumFacing facing : EnumFacing.VALUES) {
						if (!isCovered(tPos.offset(facing))) {
							allSidesCovered = false;
						}
					}
				}
			}
			this.setActive(hasLogs && allSidesCovered);
		}
	}

	public boolean isLog(BlockPos pos) {
		return world.getBlockState(pos).getBlock().isWood(world, pos);
	}

	public boolean isCovered(BlockPos pos) {
		return world.getBlockState(pos).getMaterial() == Material.GROUND
				|| world.getBlockState(pos).getMaterial() == Material.GRASS
				|| world.getBlockState(pos).getBlock() == GTBlocks.tileCharcoalPit || isLog(pos);
	}

	public List<BlockPos> getLogs() {
		return AabbUtil.getTargets(world, this.pos.down(), 256, filter, true, false, RotationList.ALL);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isActive) {
			if (rand.nextInt(16) == 0) {
				worldIn.playSound((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY()
						+ 0.5F), (double) ((float) pos.getZ()
								+ 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F
										+ rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
			}
			for (int i = 0; i < 3; ++i) {
				double d0 = (double) pos.getX() + rand.nextDouble();
				double d1 = (double) pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
				double d2 = (double) pos.getZ() + rand.nextDouble();
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		String logs = logPos != null ? "Logs: " + logPos.size() : "No Logs Found";
		data.put(logs, false);
	}
}
