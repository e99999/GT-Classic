package gtclassic.common.tile;

import java.util.List;
import java.util.Map;
import java.util.Random;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.common.GTBlocks;
import gtclassic.common.util.GTIBlockFilters;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.helpers.AabbUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileCharcoalPit extends TileEntityMachine
		implements ITickable, IProgressMachine, IGTDebuggableTile, IGTDisplayTickTile {

	float progress = 0;
	float recipeOperation = 6000.0F;
	List<BlockPos> logPositions;
	static AabbUtil.IBlockFilter filter = new GTIBlockFilters.LogFilter();

	public GTTileCharcoalPit() {
		super(0);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.setActive(nbt.getBoolean("active"));
		this.progress = nbt.getFloat("progress");
		this.recipeOperation = nbt.getFloat("operation");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("active", this.getActive());
		nbt.setFloat("progress", this.progress);
		nbt.setFloat("operation", recipeOperation);
		return nbt;
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
		// if active do thing
		if (this.getActive()) {
			updateProgress();
		}
		// structure check sets to active if true
		if (world.getTotalWorldTime() % 100 == 0) {
			if (isLog(pos.down())) {
				logPositions = getLogs();
				if (logPositions.size() > 800) {
					logPositions = logPositions.subList(0, 799);
				}
			}
			boolean allSidesCovered = true;
			if (hasLogs()) {
				this.recipeOperation = (int) Math.sqrt(logPositions.size() * 240000.0D);
				if (this.recipeOperation < 1) {
					this.recipeOperation = 6000.0F;
				}
				for (BlockPos tPos : logPositions) {
					for (EnumFacing facing : EnumFacing.VALUES) {
						if (!isCovered(tPos.offset(facing))) {
							allSidesCovered = false;
						}
					}
				}
			}
			boolean canWork = hasLogs() && allSidesCovered;
			this.setActive(canWork);
			if (!canWork) {
				this.progress = 0;
			}
		}
	}

	private void updateProgress() {
		progress = progress + 1.0F;
		if (progress >= recipeOperation) {
			if (hasLogs()) {
				for (BlockPos logs : logPositions) {
					if (isLog(logs)) {
						world.setBlockState(logs, GTBlocks.brittleCharcoal.getDefaultState());
					}
				}
			}
			this.progress = 0;
			this.setActive(false);
			logPositions.clear();
			world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}

	private boolean hasLogs() {
		return logPositions != null && !logPositions.isEmpty();
	}

	private boolean isLog(BlockPos pos) {
		return world.getBlockState(pos).getBlock().isWood(world, pos);
	}

	private boolean isCovered(BlockPos pos) {
		return world.getBlockState(pos).getMaterial() == Material.GROUND
				|| world.getBlockState(pos).getMaterial() == Material.GRASS
				|| world.getBlockState(pos).getBlock() == GTBlocks.tileCharcoalPit || isLog(pos);
	}

	private List<BlockPos> getLogs() {
		return AabbUtil.getTargets(world, this.pos, 256, filter, true, false, RotationList.ALL);
	}

	@Override
	public float getProgress() {
		return progress;
	}

	@Override
	public float getMaxProgress() {
		return recipeOperation;
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
		String logs = this.hasLogs() ? "Logs: " + logPositions.size() : "No Logs Found";
		data.put(logs, false);
		if (this.hasLogs()) {
			int time = (int) (this.recipeOperation / 20);
			data.put("Will take " + time + " seconds to burn at this size", false);
		}
	}
}
